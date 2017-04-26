package W6T1_Dozent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.Scanner;

//1. 	Herunterladen des mySQL-Datenbank-Treibers (Connector/J) von
//		http://www.mysql.de/downloads/connector/j/
//		Plattformunabhängiges ZIP-Archiv (mysql-connector-java-5.1.xx.zip)  
//		Nach betätigen des Download-Buttons keine Eingabe von Zugangsdaten
//		sondern Auswahl von [» No thanks, just take me to the downloads!]
//
//		Spezifischer MariaDB-Treiber:
//		https://downloads.mariadb.org/connector-java/
//

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Util.StatusBar;
import Util.WinUtil;

public class Datenbankzugriffe extends JFrame implements WindowListener, ActionListener
{

	private JMenuBar 		menuBar;
	private JMenu 			menuDatei, menuStammdaten, menuExtras;
	private JMenuItem		miBeenden, miPostleitzahlen, miPostleitzahlenImportieren;
	
	private StatusBar	    statusBar;
	
	private File 			fcFile;
	
	public Datenbankzugriffe()
	{
		
		initializeComponents();
	}
	
	
	private void initializeComponents()
	{
		
		this.setTitle("Datenbankzugriffe");
		this.setSize(800,  480);
		
		// Das Schließen des Frames wird vom WindowListener überwacht
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		
		menuBar = new JMenuBar();
		
		menuDatei = WinUtil.createMenu(menuBar, "Datei", null, 'D');
		miBeenden = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Beenden", null, 'e', null);
		
		menuStammdaten = WinUtil.createMenu(menuBar, "Stammdaten", null, 'S');
		miPostleitzahlen = WinUtil.createMenuItem(menuStammdaten, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Postleitzahlen", null, 'P', null);
		
		menuExtras = WinUtil.createMenu(menuBar, "Extras", null, 'x');
		miPostleitzahlenImportieren = WinUtil.createMenuItem(menuExtras, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Postleitzahlen importieren...", null, 'i', null);
		
		this.setJMenuBar(menuBar);
		
		
		// Statusleiste
		statusBar = new StatusBar();
		statusBar.setPreferredSize(new Dimension(0, 25));
		statusBar.setStatusLabelFont(statusBar.getFont().deriveFont(Font.PLAIN));
		this.add(statusBar, BorderLayout.PAGE_END);
		
	}
	
	
	private void initFrame()
	{
		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);
		
		openMySQLDatabase();
		
		
	}
	
	
	private void openMySQLDatabase()
	{
		
		String connectionString, classForName;
		String server = "localhost";
		String dataBase = "alfatraining";
		classForName = "com.mysql.jdbc.Driver";
		
		connectionString = "jdbc:mysql://" + server + ":3306/";
		connectionString += dataBase;
		
		dbEnabled(DBConnection.connectToDatabase(classForName, connectionString, "root", null));
			
	}
	
	
	
	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
	}
	
	
	private void dbEnabled(boolean enabled)
	{
		
		menuStammdaten.setEnabled(enabled);
		menuExtras.setEnabled(enabled);
		
		if (!enabled)
			statusBar.setText("Datenbank: keine");
		else
			statusBar.setText("Datenbank: " + DBConnection.getCatalog());
		
	}
	
	
	private void openFileDialog()
	{
		
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(fcFile);
		fc.setFileFilter(new FileNameExtensionFilter("Textdokument (*.txt)", "txt"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("CSV-Datei (*.csv)", "csv"));
		
		if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		
		
		// Speichern der zuletzt ausgewählten Datei
		fcFile = fc.getSelectedFile();
		
		readFile(fcFile.toString());
		
		
	}
	
	
	private void readFile(String Dateiname)
	{
		
		String zeile = null;
		int readCounter = 0;
		String tempString;
		
		// Inhalt der Statusanzeige sichern
		tempString = statusBar.getText();
		
		try (Scanner scanner = new Scanner(new FileInputStream(Dateiname)))
		{
			
			while(scanner.hasNextLine())
			{
				zeile = scanner.nextLine();
				readCounter++;
				
				statusBar.setText(zeile);
				
				// Nur wenn die Methode nicht als nicht als Thread läuft.
				// Aktualisiert den angebenen Bereich
				// Muss angegeben werden, wenn der Inhalt eines Steuerlements in sehr kurzen Abständen vom eigenen Prozess geändert werden soll.
				// Funktioniert aber nicht mehr, wenn z.B. während der Laufzeit auch Grössenänderungen am Frame vorgenommen werden und sich
				// damit auch die Grösse des zu aktualisierenden Steuerlements ändert. 
				//statusBar.paintImmediately(statusBar.getVisibleRect());
				
			}
			
		}
		catch (Exception ex)
		{
			
			JOptionPane.showMessageDialog(this,  "Fehler beim Einlesen der Datei: " + ex.getMessage(),
					                      "E/A Fehler", JOptionPane.ERROR_MESSAGE );
		}
		
		
		// Inhalt der Statusanzeige wiederherstellen
		statusBar.setText(tempString);

		JOptionPane.showMessageDialog(this, String.format("Es wurden %s Datensätze erfolgreich eingelesen",
				                      NumberFormat.getInstance().format(readCounter)));
		
		
	}
	
	
	
	public static void main(String[] args)
	{
		
		Datenbankzugriffe f = new Datenbankzugriffe();
		f.showFrame();
		
	}


	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e)
	{
		
		
		DBConnection.closeConnection();
		
		
	}


	@Override
	public void windowClosing(WindowEvent e)
	{
		
		// Ruft die Methode windowClosed()
		this.dispose();
		
	}


	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == miBeenden)
			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		else if (e.getSource() == miPostleitzahlenImportieren)
			openFileDialog();
	}

}
