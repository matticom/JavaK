package W5T2_Dozent;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Utilities;

public class FrameDialog extends JFrame implements WindowListener, ActionListener, ItemListener
{

	private JTextArea textArea;
	private JScrollPane textAreaScrollPane;
	private JCheckBox checkLineWrap, checkWordWrap;
	
	private JButton btnBeenden, btnDialog, btnDatei, btnSuchenUndErsetzen, btnDrucken;
	
	private File fcFile;
	
	public FrameDialog()
	{
		
		initializeComponents();
		
	}
	
	private void initializeComponents()
	{
		this.setTitle("Hauptfenster des Programms");
		this.setSize(760,  400);
		
		// Keine Grössenänderung des Frames
		this.setResizable(false);
		
		// Layout Manager ausschalten
		this.setLayout(null);
		
		// Das Schließen des Fensters wird vom WindowListener überwacht.
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		checkLineWrap = new JCheckBox("Automatischer Zeilenumbruch");
		checkLineWrap.setFont(checkLineWrap.getFont().deriveFont(Font.PLAIN, 9F));
		checkLineWrap.setBounds(590, 5, 200, 20);
		checkLineWrap.addItemListener(this);
		this.add(checkLineWrap);
		
		checkWordWrap = new JCheckBox("Auf Wortgrenze");
		checkWordWrap.setFont(checkWordWrap.getFont().deriveFont(Font.PLAIN, 9F));
		checkWordWrap.setBounds(590, 25, 200, 20);
		checkWordWrap.addItemListener(this);
		this.add(checkWordWrap);
		
		textArea = new JTextArea();
		
		// Automatischer Zeilenumbruch
		//textArea.setLineWrap(true);
		
		// Automatischer Umbruch auf Wortgrenze,
		// wenn autom. Zeilenumbruch gesetzt wurde.
		//textArea.setWrapStyleWord(true);
		
		
		// Freien Bereich zwischen Rahmen und Text definieren
		textArea.setMargin(new Insets(3, 3, 3, 3));
		
		
		
		// Stellt horizontale und vertikale Laufleisten zum Blättern
		// zur Verfügung, wenn der Inhalt der Komponente größer
		// ist als die definierte Sicht (Grösse).
		
		textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setBounds(5, 5, 580, 360);
		this.add(textAreaScrollPane);
		
		btnDatei = new JButton("Datei öffnen...");
		btnDatei.setFont(btnDatei.getFont().deriveFont(Font.PLAIN, 11F));
		btnDatei.setBounds(595, 215, 150, 25);
		btnDatei.addActionListener(this);
		this.add(btnDatei);
		
		btnDialog = new JButton("Dialog anzeigen");
		btnDialog.setFont(btnDialog.getFont().deriveFont(Font.PLAIN, 11F));
		btnDialog.setBounds(595, 245, 150, 25);
		btnDialog.addActionListener(this);
		this.add(btnDialog);
		
		btnSuchenUndErsetzen = new JButton("Suchen und ersetzen...");
		btnSuchenUndErsetzen.setFont(btnDialog.getFont().deriveFont(Font.PLAIN, 11F));
		btnSuchenUndErsetzen.setBounds(595, 275, 150, 25);
		btnSuchenUndErsetzen.addActionListener(this);
		this.add(btnSuchenUndErsetzen);
		
		
		// Druck Button mit Image
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/print-icon.png"));
				
		btnDrucken = new JButton("Drucken");
		btnDrucken.setIcon(icon);
		
		btnDrucken.setFont(btnDialog.getFont().deriveFont(Font.PLAIN, 11F));
		btnDrucken.setBounds(595, 305, 150, 25);
		btnDrucken.addActionListener(this);
		this.add(btnDrucken);
		
		btnBeenden = new JButton("Beenden");
		btnBeenden.setFont(btnBeenden.getFont().deriveFont(Font.PLAIN, 11F));
		btnBeenden.setBounds(595, 335, 150, 25);
		btnBeenden.addActionListener(this);
		this.add(btnBeenden);
		
		
	}
	
	private void initFrame()
	{
		// Inder Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);
		
		textArea.setText("Mit dem JTextArea-Steuerelement kann der Benutzer Text in einer Anwendung eingeben. "
				+ "Dieses Steuerelement bietet eine Funktionalität, die über das Standard-JTextField-Steuerelement von Java hinausgeht. "
				+ "Dazu gehören Mehrzeilenbearbeitung und Zeichenmaskierung für Kennwörter. "
				+ "Normalerweise wird ein JTextField-Steuerelement für die Anzeige oder Eingabe einer einzelnen Textzeile verwendet.");
		
		
		// Automatische Zeilenschaltung auf Wortgrenze setzen.
		checkWordWrap.setSelected(true);
		
	}
	
	
	private void anzeigeDialog()
	{
		
		FrameDialogModal dlg = new FrameDialogModal();
		
		// Mit Übergabe des Eigentümers
		dlg.showDialog(this);
		
		System.out.println("Ende von anzeigeDialog()");
		
		
	}
	
	
	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
		
	}
	
	private void dateiLesen()
	{
	
		JFileChooser fc = new JFileChooser();
		
		fc.setFileFilter(new FileNameExtensionFilter("Textdokument (*.txt)", "txt"));
		fc.setDialogTitle("Textdokument öffnen");
		
		fc.setCurrentDirectory(fcFile);
		
		
		// Alle Dateien (*.*) als Dateifilter wird nicht angeboten. 
		// Standard = true.
		fc.setAcceptAllFileFilterUsed(false);
		
		
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			// Zuletzt ausgewählte Datei merken.
			fcFile = fc.getSelectedFile();
			//dateiLesen(fc.getSelectedFile().toString());
			//dateiLesenStringBuilder(fc.getSelectedFile().toString());
			//dateiLesenPuffer(fc.getSelectedFile().toString());
			//dateiLesenPuffer1(fc.getSelectedFile().toString());
			//dateiLesenFileChannel(fc.getSelectedFile().toString());
			dateiLesenTextAreaRead(fc.getSelectedFile().toString());
		}
		
	}
	
	
	private void dateiLesen(String Dateiname)
	{
		
		Scanner in = null;
		long start = 0, ende = 0;
		
		// Aktuellen Inhalt der TextArea löschen
		textArea.setText(null);
		
		try
		{
			in = new Scanner(new FileInputStream(Dateiname));
			
			start = System.currentTimeMillis();
			
			while(in.hasNextLine())
				textArea.append(in.nextLine() + "\n");
			
			ende = System.currentTimeMillis();
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
		if (in != null)
			in.close();
		
		JOptionPane.showMessageDialog(this, "Dauer: " + NumberFormat.getInstance().format(ende - start) + " Millisekunden", "Lesen zeilenweise mit Scanner",
										JOptionPane.INFORMATION_MESSAGE);
		
		
		textArea.requestFocusInWindow();
	
		
	}
	
	private void dateiLesenStringBuilder(String Dateiname)
	{
		
		Scanner in = null;
		long start = 0, ende = 0;
		StringBuilder sb = new StringBuilder();
		
		// Aktuellen Inhalt der TextArea löschen
		textArea.setText(null);
		
		try
		{
			in = new Scanner(new FileInputStream(Dateiname));
			
			start = System.currentTimeMillis();
			
			while(in.hasNextLine())
				sb.append(in.nextLine()).append("\n");
			
			
			textArea.setText(sb.toString());
			
			ende = System.currentTimeMillis();
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
		if (in != null)
			in.close();
		
		JOptionPane.showMessageDialog(this, "Dauer: " + NumberFormat.getInstance().format(ende - start) + " Millisekunden", "Lesen zeilenweise mit StringBuilder",
										JOptionPane.INFORMATION_MESSAGE);
		
		
		textArea.requestFocusInWindow();
	
		
	}
	
	private void dateiLesenPuffer(String Dateiname)
	{
		
		boolean retValue = false;
		FileReader in = null;
		BufferedReader br = null;
		long start = 0, ende = 0;
		char[] chars = new char[8192];
		int length = 0;
		
		StringBuilder sb = new StringBuilder();
		
		// Inhalt der TextArea löschen
		textArea.setText(null);
				
		try
		{
			in = new FileReader(Dateiname);
			br = new BufferedReader(in);
			
			start = System.currentTimeMillis();
			
			while (true)
			{
				length = br.read(chars, 0, chars.length);
				sb.append(new String(chars, 0, length));
				if (length < chars.length)
					break;
			}
			
			textArea.setText(sb.toString());
			
			ende = System.currentTimeMillis();
			
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
		
			if (br != null)
				br.close();
		}
		catch (Exception ex)
		{
			
		}
		
		
		JOptionPane.showMessageDialog(this, "Dauer: " + NumberFormat.getInstance().format(ende - start) + " Millisekunden", "Lesen mit Puffer",
										JOptionPane.INFORMATION_MESSAGE);
		
		
		textArea.requestFocusInWindow();
		
		
	}
	
	private void dateiLesenPuffer1(String Dateiname)
	{
		
		FileReader in = null;
		long start = 0, ende = 0;
		
		File file = new File(Dateiname);
		
		// Für diese Methode ist es notwendig, dass die Dateilänge in einen
		// Integer-Wert umgewandelt werden kann!
		if (file.length() > Integer.MAX_VALUE)
		{
			
			JOptionPane.showMessageDialog(this, "Die Datei ist zu groß um mit dieser Methode eingelesen werden zu können", "E/A Fehler",
					JOptionPane.ERROR_MESSAGE);
			return;
			
		}
		
		// Inhalt der TextArea löschen
		textArea.setText(null);
			
		try
		{

			in = new FileReader(Dateiname);
			
			start = System.currentTimeMillis();
			
			// Ein Array von Zeichen in der Größe der Datei anlegen.
			// Die gesamte Datei soll in einem Lesevorgang eingelesen werden.
			// Achtung: Die Dateilänge muss allerding in einen Integer-Wert
			// umgewandelt werden können!
			// Das ist die maximale Anzahl von Elementen in einem Array.
			char[] chars = new char[(int) file.length()];
					
			in.read(chars);
			textArea.setText(new String(chars));
			
			ende = System.currentTimeMillis();
			
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
		try
		{
		
			if (in != null)
				in.close();
		}
		catch (Exception ex)
		{
			
		}
		
		
		JOptionPane.showMessageDialog(this, "Dauer: " + NumberFormat.getInstance().format(ende - start) + " Millisekunden", "Lesen mit einem Puffer",
										JOptionPane.INFORMATION_MESSAGE);
		
		
		textArea.requestFocusInWindow();
		
		
	}
	
	private void dateiLesenFileChannel(String Dateiname)
	{
		
		long start = 0, ende = 0;

		FileChannel fileChannel = null;
		
		File file = new File(Dateiname);

		// Für diese Methode ist es notwendig, dass die Dateilänge in einen
		// Integer-Wert umgewandelt werden kann!
		if (file.length() > Integer.MAX_VALUE)
		{
			JOptionPane.showMessageDialog(this, "Die Datei ist zu groß um mit dieser Methode eingelesen werden zu können", "E/A Fehler",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Inhalt der TextArea löschen
		textArea.setText(null);
				
		try
		{

			Path path = Paths.get(Dateiname);
			fileChannel = FileChannel.open(path);
			
			start = System.currentTimeMillis();
			
			ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
			fileChannel.read(buffer);
			textArea.setText(new String(buffer.array()));
			
			ende = System.currentTimeMillis();
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			fileChannel.close();
		}
		catch (Exception ex)
		{
			
		}
		
		JOptionPane.showMessageDialog(this, "Dauer: " + NumberFormat.getInstance().format(ende - start) + " Millisekunden", "Lesen mit FileChannel",
				JOptionPane.INFORMATION_MESSAGE);

		textArea.requestFocusInWindow();
		
		
	}
	
	
	private void dateiLesenTextAreaRead(String Dateiname)
	{
		
		long start = 0, ende = 0;

		FileReader fr = null;
		
		// Inhalt der TextArea löschen
		textArea.setText(null);
		
		try
		{
			fr = new FileReader(Dateiname);
			start = System.currentTimeMillis();

			textArea.read(fr, null);

			ende = System.currentTimeMillis();

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			fr.close();
		}
		catch (Exception ex)
		{
			
		}
		
		JOptionPane.showMessageDialog(this, "Dauer: " + NumberFormat.getInstance().format(ende - start) + " Millisekunden", "Lesen mit der TextArea",
				JOptionPane.INFORMATION_MESSAGE);

		textArea.requestFocusInWindow();
		
	}
	
	
	private void anzeigeSuchenUndErsetzen()
	{
		
		FrameDialogSuchenUndErsetzen dlg = new FrameDialogSuchenUndErsetzen(textArea);
		dlg.showDialog(this);
	
	}
	
	
	private void druckeText()
	{
		// Ausdruck der TextArea mit der Methode print() der TextArea.
		//druckeText(textArea);
		
		
		
		
		
		
		
	}
	
	
	private void druckeText(JTextArea textArea)
	{
		try
		{
			boolean complete = textArea.print();
			if (complete)
			{
				// Anzeige einer Meldung, dass der Druck erfolgreich war
			}
			
			
		}
		catch (Exception ex)
		{
			// Anzeige der Fehlermeldung
		}
		
	}
	
	
	
	private boolean queryExit()
	{
		
		boolean retValue = false;
		
		
		String[] options = {"Ja", "Nein"};
		
		int dlgValue = JOptionPane.showOptionDialog(this, "Soll das Programm beendet werden", "Program beenden", 
				                     JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
		if (dlgValue == JOptionPane.YES_OPTION)
			retValue = true;
		
		
		return retValue;
		
	}
	
	
	public static void main(String[] args)
	{
		
		FrameDialog f = new FrameDialog();
		f.showFrame();
		
		
	}

	
	private class Drucken implements Printable
	{

		private JTextArea textArea;
		
		private String[] lines = new String[0];
		
		
		public Drucken(JTextArea textArea)
		{
			
			preparePrintout(textArea);
			
		}
		
		
		private void preparePrintout(JTextArea textArea)
		{
			this.textArea = textArea;
			
			try
			{
				lines = convertToStringArray(textArea);
			}
			catch (Exception ex)
			{}
		}
		
		private String[] convertToStringArray(JTextArea textArea) throws Exception
		{
			
			String[] lines;
			
			int lineEnd = 0;
			int lineStart = 0;
			int zeile = 0;
			
			// Zuerst eine dynamische Datenstruktur verwenden, da die Anzahl der
			// Zeilen nicht bekannt ist.
			ArrayList<String> alText = new ArrayList<>();
			
			
			for (int i = 0; i < textArea.getText().length(); i++)
			{
				
				// Indexposition für Zeilenende ermitteln
				lineEnd = Utilities.getRowEnd(textArea, i);
				
				// Indexposition für den Beginn der nächsten Zeile ermitteln.
				lineStart = Utilities.getRowStart(textArea, i + 1);
				
				// Laufvariable i auf das Zeilenende setzen
				i = lineEnd;
				
				// Wenn die Indexposition für das Zeilenende grösser ist als der
				// Zeilenanfang bedeuted das, dass eine Textzeile gefunden
				// wurde.
				if (lineEnd > lineStart)
				{
					// Wenn die Indexposition für Zeilenende kleiner ist als die
					// Gesamtlänge des Textes, Zeile in das Array übertragen.
					if (lineEnd < textArea.getText().length())
						alText.add(textArea.getText().substring(lineStart, lineEnd + 1));
					else
						// Textzeile nur bis zu Ende des Textes in das Array
						// übertragen.
						alText.add(textArea.getText().substring(lineStart));
				}
				else
					// lineEnd ist identisch mit lineStart = Leerzeile
					alText.add("");
				
				
				lineStart = lineEnd;

			}
			
			// Übernahme aller Elemente der Arraylist in ein Array vom Typ String, welches
			// dann von der Methode print() zum Drucken verwendet wird.
			
			lines = new String[alText.size()];
			
			zeile = 0;
			for (String s : alText)
			{
				lines[zeile++] = s;
			}
			
			// Rückgabe des String-Arrays
			
			return lines;
		}
		
		
		
		// Die Methode print(Graphics g, PageFormat pageFormat, int pageIndex) wird über die Methode
		// print() der Klasse 'PrintJob' so lange aufgerufen, bis NO_SUCH_PAGE zurückgegeben wird.
		// Die Variable pageIndex beginnt bei 0 und wird vor jedem Aufruf um 1
		// inkrementiert.
		
		@Override
		public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException
		{
			
			int retValue = Printable.NO_SUCH_PAGE;
			
			// Zeilenabstand definieren
			double lineSpacing = 1.2;
					
			int pageHeight, pageWidth;
			
			// Gleichen Font wie in TextArea verwenden.
			g.setFont(textArea.getFont());
						
			// Beginn der Druckausgabe auf die linke obere Ecke (x, y) des
			// druckbaren Bereichs setzen.
			Point drawPoint = new Point((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
			
			// Druckbaren Bereich der Seite ermitteln.
			pageWidth = (int) pageFormat.getImageableWidth();
			pageHeight = (int) pageFormat.getImageableHeight();
						
			// Ermitteln der Anzahl von Zeilen über das String-Array.
			int anzahlZeilen = lines.length;
						
			// Berechnung der Zeilen pro Seite unter Berücksichtigung von etwas
			// vertikalem Abstand zwischen den Zeilen (2/10 der Zeile).
			int zeilenProSeite = (int) (pageHeight / (lineSpacing * g.getFont().getSize()));
			
			// Berechnung der Anzahl der Seiten (0 basierend)
			// Math.ceil() gibt den kleinsten ganzzahligen Wert zurück, der
			// größer oder gleich der angegebenen Gleitkommazahl mit doppelter
			// Genauigkeit ist.
			// Rückgabetyp ist double.
			int anzahlSeiten = (int) Math.ceil(anzahlZeilen / zeilenProSeite);
			
			if (pageIndex <= anzahlSeiten)
			{
				
				
				
				
				
				
				retValue = Printable.PAGE_EXISTS;
			}
			
		
			return retValue;
		}
		
		
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnBeenden)
			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		else if (e.getSource() == btnDialog)
			anzeigeDialog();
		else if (e.getSource() == btnDatei)
			dateiLesen();
		else if (e.getSource() == btnSuchenUndErsetzen)
			anzeigeSuchenUndErsetzen();
		else if (e.getSource() == btnDrucken)
			druckeText();
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		
		//System.out.println("windowClosed()");
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
	
		if (queryExit())
			// Ruft die Methode windowClosed() auf
			this.dispose();
		
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		
		if (e.getSource() == checkLineWrap)
		{
			
			textArea.setLineWrap(checkLineWrap.isSelected());
			// Wenn autom. Zeilenumbruch nicht gesetzt ist,
			// evtl. gesetzten Wert für Umruch auf Wortgrenze ebenfalls
			// ausschalten.
			if (!checkLineWrap.isSelected())
				checkWordWrap.setSelected(false);
						
		}
		else if (e.getSource() == checkWordWrap)
		{
			textArea.setWrapStyleWord(checkWordWrap.isSelected());
			// Wenn autom. Zeilenumbruch nicht gesetzt ist und Umbruch auf
			// Wortgrenze eingeschaltet wurde , den autom. Zeilenumbruch ebenfalls setzen.
			if (checkWordWrap.isSelected())
				checkLineWrap.setSelected(true);
			
		}
		
		
	}

}
