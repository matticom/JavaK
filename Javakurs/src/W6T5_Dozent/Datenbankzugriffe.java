package W6T5_Dozent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
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
//2. 	Innerhalb des Eclipse Workspaces im Projekt Javakurs ein Verzeichnis 'lib' anlegen und
//		das Java-Archiv mysql-connector-java-5.1.xx-bin.jar aus dem heruntergeladenen
//		Verzeichnis in dieses Verzeichnis kopieren.
//
//3. 	In Eclipse das Menü Project->Properties aufrufen. In den Properties 'Java Build Path'
//		auswählen und dort die Registerkarte 'Libraries'. Über den Button
//		[Add External JARs...] das Java-Archiv im Verzeichnis 'lib' hinzufügen.     
//		Alternativ:
//		Verzeichnis 'lib' im Package Explorer öffnen, mit rechter Maustaste auf den Connector
//		klicken und  anschliessend 'Build Path' -> Add to Build Path' auswählen. 

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Util.ProgressWindow;
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
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(("/images/Server.png"))));
		
		
		// Das Schließen des Frames wird vom WindowListener überwacht
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		
		menuBar = new JMenuBar();
		
		menuDatei = WinUtil.createMenu(menuBar, "Datei", null, 'D');
		miBeenden = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Beenden", null, 'e', null);
		
		menuStammdaten = WinUtil.createMenu(menuBar, "Stammdaten", null, 'S');
		miPostleitzahlen = WinUtil.createMenuItem(menuStammdaten, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Postleitzahlen", null, 'P', null);
		
		menuExtras = WinUtil.createMenu(menuBar, "Extras", null, 'x');
		miPostleitzahlenImportieren = WinUtil.createMenuItem(menuExtras, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Postleitzahlen importieren...", 
				 new ImageIcon(this.getClass().getResource("/images/Server.png")), 'i', null);
		
		this.setJMenuBar(menuBar);
		
		
		// Statusleiste
		statusBar = new StatusBar();
		statusBar.setPreferredSize(new Dimension(0, 25));
		statusBar.setStatusLabelFont(statusBar.getFont().deriveFont(Font.PLAIN));
		statusBar.setIcon(new ImageIcon(this.getClass().getResource("/images/Server.png")));
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
		
		//readFile(fcFile.toString());
		readFileAsThread(fcFile.toString());
			
	}
	
	
	private void readFileAsThread(String Dateiname)
	{
		
		// Thread zum Einlesen der Postleitzahlen verwenden.
		// Nur so kann die Statusanzeige aktualisiert werden.
		Thread t = new Thread(new ReadFileIntoDatabase(Dateiname));
		t.start();
		
		
	}
	
	private boolean queryCancel()
	{
		
		boolean retValue = false;
		
		Toolkit.getDefaultToolkit().beep();
		String[] options = { "Ja", "Nein" };
		
		if (JOptionPane.showOptionDialog(null, "Soll der laufende Prozess abgebrochen werden", "Abbruch", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[1]) == JOptionPane.YES_OPTION)
			retValue = true;
		
		return retValue;
	}
	
	private void readFile(String Dateiname)
	{
		
		String zeile = null;
		int readCounter = 0;
		int insertCounter = 0;
		int dlgValue;
		long PrimaryKey;
		boolean errFlag = false;
		ProgressWindow 	progressWindow;
		
		String tempString;
		String[] split;
		// Benutzerdefinierte Button Texte
		String[] options = {"Ja", "Nein"};
		
		PreparedStatement prepStatementInsert, prepStatementPLZOrtExists;
		
		dlgValue = JOptionPane.showOptionDialog(this, "Sollen die Einträge der vorhandenenen Tabelle der Postleitzahlen vorher gelöscht werden", 
				                                "Postleitzahlen importieren", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				                                 null, options, options[1]);
		
		if (dlgValue == JOptionPane.YES_OPTION)
			deletePLZEntries();
			
		
		prepStatementInsert = Globals.prepareInsertPLZOrt();
		if (prepStatementInsert == null)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Vorbereiten der SQL-Anweisung", "Postleitzahlen importieren", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		prepStatementPLZOrtExists = Globals.preparePLZOrtExists();
		if (prepStatementPLZOrtExists == null)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Vorbereiten der SQL-Anweisung", "Postleitzahlen importieren", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		// Inhalt der Statusanzeige sichern
		tempString = statusBar.getText();
		
		// Fortschrittsanzeige vorbereiten und sichtbar machen
		progressWindow = new ProgressWindow(Datenbankzugriffe.this);
		progressWindow.setValue(0);
		progressWindow.showCancelButton(true);
		
		long fileLength = new File(Dateiname).length();
		if (fileLength <= Integer.MAX_VALUE)
			progressWindow.setMaxValue((int)fileLength);
		else
		{
			progressWindow.setMaxValue(getRecordCount(Dateiname));
			fileLength = -1;
		}
		
		Thread t = new Thread(progressWindow);
		t.start();
		
	
		PrimaryKey = Globals.getNextKey();
		
		
		try (Scanner scanner = new Scanner(new FileInputStream(Dateiname)))
		{
	
			// Transaktionsschleife öffnen: Auto Commit = false
			// Tabellen die innerhalb dieser Transaktionsschleife verwendet
			// werden müssen im Tabellenformat 'InnoDB'
			// angelegt worden sein!
			// Der Hauptvorteil von InnoDB gegenüber anderen Speichersubsystemen
			// für MySQL ist, dass Transaktionssicherheit
			// und referenzielle Integrität über Fremdschlüssel gewährleistet
			// werden.

			// Für das Tabellenformat MyISAM können Transaktionsschleifen nicht
			// verwendet werden. Sie sind wirkungslos.
			

			DBConnection.beginTransaction();
			
			
			while(scanner.hasNextLine() && !errFlag)
			{
				
				
				if (progressWindow.cancelRequest())
				{
					if (queryCancel())
					{
						errFlag = true;
						break;
					}
					else
						// Abruchanforderung wieder zurücksetzen
						progressWindow.setCancelRequest(false);

				}				
				
				zeile = scanner.nextLine();
				readCounter++;
				if (readCounter % 10 == 0)
					statusBar.setText(String.format("Datensätze werden gelesen...    [%s]", NumberFormat.getInstance().format(readCounter)));
				
				
				if (fileLength < 0)
					progressWindow.setValue(readCounter);
				else
					progressWindow.setValue(progressWindow.getValue() + zeile.length() + System.lineSeparator().length());
				
				// Methode 'split' mit maximaler Anzahl zurückzuliefernder
				// Zeichenketten
				split = zeile.split(";", 2);
				if (split.length == 2)
				{	
//					if (Globals.istPLZOrtVorhanden(split[0], split[1]))
//						continue;
					
					if (Globals.istPLZOrtVorhandenPrepared(prepStatementPLZOrtExists, split[0], split[1]))
						continue;
					
//					if (Globals.insertPLZOrt(PrimaryKey, split[0], split[1]))
//					{
//						insertCounter++;
//						PrimaryKey++;
//					}
					
					if (Globals.insertPLZOrtPrepared(prepStatementInsert, PrimaryKey, split[0], split[1]))
					{
						insertCounter++;
						PrimaryKey++;
					}
				}
			}
			
		}
		catch (Exception ex)
		{
			
			JOptionPane.showMessageDialog(this,  "Fehler beim Einlesen der Datei: " + ex.getMessage(),
					                      "E/A Fehler", JOptionPane.ERROR_MESSAGE );
			errFlag = true;
		}
		
	
		if (errFlag)
		{
			DBConnection.rollbackTransaction();
			insertCounter = 0;
		}
		else
		
			DBConnection.commitTransaction();
		
		
		// Inhalt der Statusanzeige wiederherstellen
		statusBar.setText(tempString);

		// Fortschrittsanzeige wieder unsichtbar machen
		progressWindow.close();
		
		JOptionPane.showMessageDialog(this, String.format("Es wurden %s Datensätze erfolgreich eingelesen",
				                      NumberFormat.getInstance().format(insertCounter)));
		
	}
	
	
	private int getRecordCount(String Dateiname)
	{
		
		int retValue = 0;
		
		try (Scanner scanner = new Scanner(new FileInputStream(Dateiname)))
		{
			while (scanner.hasNextLine())
			{
				scanner.nextLine();
				retValue++;
			}
		}
		catch (Exception ex)
		{}
		
		return retValue;
	}
	
	
	private void deletePLZEntries()
	{
		
		//String SQL = "DELETE FROM POSTLEITZAHLEN";
		
		// oder
		String SQL = "TRUNCATE TABLE POSTLEITZAHLEN";
		DBConnection.executeNonQuery(SQL);
		
	}
	
	
	private void showPLZTable()
	{
		PLZTable dlg = new PLZTable();
		dlg.showDialog(this);
		
	}
	
	
	public static void main(String[] args)
	{
		
		Datenbankzugriffe f = new Datenbankzugriffe();
		f.showFrame();
		
	}

	
	private class ReadFileIntoDatabase implements Runnable
	{

		private String Dateiname;
		
		public ReadFileIntoDatabase(String Dateiname)
		{
			
			this.Dateiname = Dateiname;
		}
		
		
		@Override
		public void run()
		{
			readFile(Dateiname);
			
		}
		
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
		
		if (!menuDatei.isEnabled())
		{
			Toolkit.getDefaultToolkit().beep();
			return;

		}
			
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
		else if (e.getSource() == miPostleitzahlen)
			showPLZTable();
		
	}

}
