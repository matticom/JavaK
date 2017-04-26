package W6T1_Blaszczyk;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Util.WinUtil.MenuItemType;
import W6T3_Dozent.PLZTable;
import Util.StatusBar;
import Util.WinUtil;

@SuppressWarnings("serial")
public class Datenbankzugriffe extends JFrame implements WindowListener, ActionListener
{

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuDatei;
	private JMenu menuStammdaten;
	private JMenu menuExtras;

	private JMenuItem miBeenden;
	private JMenuItem miPostleitzahlen;
	private JMenuItem miPostleitzahlenImportieren;
	
	private StatusBar statusBar;
	
	private File fcFile;
	
	
	public Datenbankzugriffe()
	{
		super("Datenbankzugriffe");
		initComponents();
	}

	private void initComponents()
	{
		setSize(800, 480);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		menuDatei = WinUtil.createMenu(menuBar, "Datei", null, 'D');
		miBeenden = WinUtil.createMenuItem(menuDatei, null , MenuItemType.ITEM_PLAIN , this, "Beenden", null, 'e', null);
		
		menuStammdaten = WinUtil.createMenu(menuBar, "Stammdaten", null, 'S');
		miPostleitzahlen = WinUtil.createMenuItem(menuStammdaten, null, MenuItemType.ITEM_PLAIN, this, "Postleitzahlen", null, 'P', null);
		
		menuExtras = WinUtil.createMenu(menuBar, "Extras", null, 'E');
		miPostleitzahlenImportieren = WinUtil.createMenuItem(menuExtras, null, MenuItemType.ITEM_PLAIN, this, "Postleitzahlen Importieren", null, 'I', null);
		
		setJMenuBar(menuBar);
		
		statusBar = new StatusBar();
		statusBar.setPreferredSize(new Dimension(0, 25));
		statusBar.setStatusLabelFont(statusBar.getFont().deriveFont(Font.PLAIN));
		add(statusBar,BorderLayout.PAGE_END);
		
		dbEnabled(false);
	}
	

	public void showFrame()
	{
		setLocationRelativeTo(null);
		openmySQLDatabase();
		setVisible(true);
	}
	
	private void openmySQLDatabase()
	{
		String connectionString, classForName = "com.mysql.jdbc.Driver";
		String server = "localhost";
		String dataBase = "alfatraining";
		
		connectionString = "jdbc:mysql://" + server + ":3306/";
		connectionString += dataBase;
		
		if(DBConnection.connectToDataBase(classForName, connectionString, "root", null))
		{
			System.out.println("DB Connection sucessful.");
			dbEnabled(true);
		}
		else
		{
			System.out.println("DB Connection failed.");
			dbEnabled(false);
		}
	}
	
	private void dbEnabled(boolean enabled)
	{
		menuExtras.setEnabled(enabled);
		menuStammdaten.setEnabled(enabled);
		if(enabled)
			statusBar.setText("Datenbank: " + DBConnection.getCatalog());
		else
			statusBar.setText("Keine Datenbank");
	}
	
	private void openFileDialog()
	{
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(fcFile);
		fc.setFileFilter(new FileNameExtensionFilter("Textdokument (*.txt)", "txt"));
		
		if(fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		fcFile = fc.getSelectedFile();
		readFile(fcFile);
	}
	
	
	private void readFile(File file)
	{
		new Thread(()->{
			String line = null;
			int readCounter = 0;
			int insertCounter = 0;
			String tempString = statusBar.getText();
			String[] split;

			int dialVal = JOptionPane.showConfirmDialog(null, "Vorhandene Tablle löschen?", "Frage", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
			if( dialVal == JOptionPane.YES_OPTION)
				Globals.deletePLZEntries();
			long primaryKey = Globals.getMaxPrimaryKey()+1;
			
			PreparedStatement prepStatInsert = Globals.prepareInsertPLZOrt();
			PreparedStatement prepStatExists = Globals.preparePLZOrtExists();
			
			
			//ProgressDialog erstellen und anzeigen
			ProgressDialog progressDialog = new ProgressDialog(this, getLineCount(file), "", true);
//			SwingUtilities.invokeLater( ()->progressDialog.showDialog() );
			SwingUtilities.invokeLater( new Runnable()
			{
				@Override
				public void run()
				{
					progressDialog.showDialog();
				}
			});
			
			try(Scanner scanner = new Scanner(file))
			{
				//Aufhören wenn der ProgressDialog es erfragt.
				while(scanner.hasNextLine() && !progressDialog.hasRequestCancel())
				{
					line = scanner.nextLine();
					split = line.split(";", 2);
					readCounter++;
					final String fString = String.format("Datensätze werden gelesen...    [%d]", readCounter);
					statusBar.setText(fString);
					
					//ProgressDialog informieren
					progressDialog.setInfoAndValue(fString, readCounter);
					
					if (Globals.istPLZOrtVorhandenPrepared(prepStatExists, split[0].trim(), split[1].trim()))
						continue;
					if(split.length == 2)
						if(Globals.insertPLZOrtPrepared(prepStatInsert, primaryKey++, split[0].trim(), split[1].trim()))
							insertCounter++;
				}
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			//ProgressDialog schließen
			progressDialog.disposeDialog();
			
			SwingUtilities.invokeLater(() -> statusBar.setText(tempString));
			JOptionPane.showMessageDialog(this, 
					String.format("Von %6d Datensätzen wurden %6d inseriert.", readCounter, insertCounter ));
		}).start();
	}
	
	public static int getLineCount(File file)
	{
		try(LineNumberReader lnr = new LineNumberReader(new FileReader(file)))
		{
			lnr.skip(Long.MAX_VALUE);
	        return lnr.getLineNumber();
		}
		catch (IOException e)
		{
			return -1;
		}
		
	}
	

	private void showPLZTable()
	{
		PLZTable dlg = new PLZTable();
		dlg.showDialog(this);
	}

	
	public static void main(String[] args)
	{
		Datenbankzugriffe d = new Datenbankzugriffe();
		d.showFrame();
	}
	
	@Override
	public void windowActivated(WindowEvent e)
	{
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		DBConnection.closeConnection();
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == miBeenden)
			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		else if(e.getSource() == miPostleitzahlenImportieren)
			openFileDialog();
		else if (e.getSource() == miPostleitzahlen)
			showPLZTable();
	}


}
