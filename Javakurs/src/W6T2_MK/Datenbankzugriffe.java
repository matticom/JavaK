package W6T2_MK;

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
//		Plattformunabh�ngiges ZIP-Archiv (mysql-connector-java-5.1.xx.zip)  
//		Nach bet�tigen des Download-Buttons keine Eingabe von Zugangsdaten
//		sondern Auswahl von [� No thanks, just take me to the downloads!]
//
//		Spezifischer MariaDB-Treiber:
//		https://downloads.mariadb.org/connector-java/
//
//2. 	Innerhalb des Eclipse Workspaces im Projekt Javakurs ein Verzeichnis 'lib' anlegen und
//		das Java-Archiv mysql-connector-java-5.1.xx-bin.jar aus dem heruntergeladenen
//		Verzeichnis in dieses Verzeichnis kopieren.
//
//3. 	In Eclipse das Men� Project->Properties aufrufen. In den Properties 'Java Build Path'
//		ausw�hlen und dort die Registerkarte 'Libraries'. �ber den Button
//		[Add External JARs...] das Java-Archiv im Verzeichnis 'lib' hinzuf�gen.     
//		Alternativ:
//		Verzeichnis 'lib' im Package Explorer �ffnen, mit rechter Maustaste auf den Connector
//		klicken und  anschliessend 'Build Path' -> Add to Build Path' ausw�hlen. 

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Util.StatusBar;
import Util.WinUtil;

public class Datenbankzugriffe extends JFrame implements WindowListener, ActionListener
{

	private JMenuBar menuBar;
	private JMenu menuDatei, menuStammdaten, menuExtras;
	private JMenuItem miBeenden, miPostleitzahlen, miPostleitzahlenImportieren;
	private JProgressBar progressBar;
	private StatusBar statusBar;

	private File fcFile;
	private int currentProgress;

	public Datenbankzugriffe()
	{

		initializeComponents();
	}

	private void initializeComponents()
	{

		this.setTitle("Datenbankzugriffe");
		this.setSize(800, 480);

		// Das Schlie�en des Frames wird vom WindowListener �berwacht
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);

		menuBar = new JMenuBar();

		menuDatei = WinUtil.createMenu(menuBar, "Datei", null, 'D');
		miBeenden = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Beenden", null, 'e', null);

		menuStammdaten = WinUtil.createMenu(menuBar, "Stammdaten", null, 'S');
		miPostleitzahlen = WinUtil.createMenuItem(menuStammdaten, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Postleitzahlen", null, 'P', null);

		menuExtras = WinUtil.createMenu(menuBar, "Extras", null, 'x');
		miPostleitzahlenImportieren = WinUtil.createMenuItem(menuExtras, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Postleitzahlen importieren...", null,
				'i', null);

		this.setJMenuBar(menuBar);

		// Statusleiste
		statusBar = new StatusBar();
		statusBar.setPreferredSize(new Dimension(0, 25));
		statusBar.setStatusLabelFont(statusBar.getFont().deriveFont(Font.PLAIN));
		this.add(statusBar, BorderLayout.PAGE_END);

		// Statusleiste: Fortschrittsbalken hinzuf�gen
		progressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
		statusBar.add(progressBar, BorderLayout.LINE_END);
		progressBar.setAlignmentY(RIGHT_ALIGNMENT);
		progressBar.setBorderPainted(true);
		progressBar.setPreferredSize(new Dimension(300,statusBar.getHeight()));
		progressBar.setStringPainted(true);
		// progressBar.setVisible(true);

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

		// Speichern der zuletzt ausgew�hlten Datei
		fcFile = fc.getSelectedFile();

		// readFile(fcFile.toString());
		readFileAsThread(fcFile.toString());

	}

	private void readFileAsThread(String Dateiname)
	{

		// Thread zum Einlesen der Postleitzahlen verwenden.
		// Nur so kann die Statusanzeige aktualisiert werden.
		Thread t = new Thread(new ReadFileIntoDatabase(Dateiname));
		t.start();

	}

	private void readFile(String Dateiname)
	{

		String zeile = null;
		int readCounter = 0;
		int insertCounter = 0;
		int dlgValue;
		long PrimaryKey;

		String tempString;
		String[] split;
		// Benutzerdefinierte Button Texte
		String[] options =
		{ "Ja", "Nein" };

		dlgValue = JOptionPane.showOptionDialog(this, "Sollen die Eintr�ge der vorhandenenen Tabelle der Postleitzahlen vorher gel�scht werden",
				"Postleitzahlen importieren", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (dlgValue == JOptionPane.YES_OPTION)
			deletePLZEntries();

		int zeilen = getRows(Dateiname);
		
		// Inhalt der Statusanzeige sichern
		tempString = statusBar.getText();

		PrimaryKey = Globals.getNextKey();

		try (Scanner scanner = new Scanner(new FileInputStream(Dateiname)))
		{
			
			while (scanner.hasNextLine())
			{
				zeile = scanner.nextLine();
				readCounter++;
				
				progressBar.setValue(100 * readCounter / zeilen);
				 
				if (readCounter % 10 == 0)
					statusBar.setText(String.format("Datens�tze werden gelesen...    [%s]", NumberFormat.getInstance().format(readCounter)));

				// Methode 'split' mit maximaler Anzahl zur�ckzuliefernder
				// Zeichenketten
				split = zeile.split(";", 2);
				if (split.length == 2)
				{

					if (Globals.istPLZOrtVorhanden(split[0], split[1]))
						continue;

					if (Globals.insertPLZOrt(PrimaryKey, split[0], split[1]))
					{
						insertCounter++;
						PrimaryKey++;
					}
				}
			}

		}
		catch (Exception ex)
		{

			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}

		// Inhalt der Statusanzeige wiederherstellen
		statusBar.setText(tempString);

		JOptionPane.showMessageDialog(this, String.format("Es wurden %s Datens�tze erfolgreich eingelesen", NumberFormat.getInstance().format(insertCounter)));

	}

	private void deletePLZEntries()
	{

		// String SQL = "DELETE FROM POSTLEITZAHLEN";

		// oder
		String SQL = "TRUNCATE TABLE POSTLEITZAHLEN";
		DBConnection.executeNonQuery(SQL);

	}

	private int getRows(String Dateiname)
	{
		int counter = 0;
		String s;
		try (Scanner scanner = new Scanner(new FileInputStream(Dateiname)))
		{
			while (scanner.hasNextLine())
			{
				s = scanner.nextLine();
				counter++;
			}
		}
		catch (Exception ex)
		{}
		return counter;
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
