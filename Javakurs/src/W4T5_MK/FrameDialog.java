package W4T5_MK;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameDialog extends JFrame implements WindowListener, ActionListener
{

	private JTextArea textArea;
	private JScrollPane textAreaScrollPane;

	private JButton btnBeenden, btnDialog, btnDatei;
	private JCheckBox CBbreak, CBbreakWord;

	private File fcFile;

	private boolean Break;
	private boolean BreakWord;

	public FrameDialog()
	{

		initializeComponents();

	}

	private void initializeComponents()
	{
		this.setTitle("Hauptfenster des Programms");
		this.setSize(760, 400);

		// Keine Grössenänderung des Frames
		this.setResizable(false);

		// Layout Manager ausschalten
		this.setLayout(null);

		// Das Schließen des Fensters wird vom WindowListener überwacht.
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);

		textArea = new JTextArea();

		// Automatischer Zeilenumbruch
		textArea.setLineWrap(true);

		// Automatischer Umbruch auf Wortgrenze,
		// wenn autom. Zeilenumbruch gesetzt wurde.
		textArea.setWrapStyleWord(true);

		// Freien Bereich zwischen Rahmen und Text definieren
		textArea.setMargin(new Insets(3, 3, 3, 3));

		// Stellt horizontale und vertikale Laufleisten zum Blättern
		// zur Verfügung, wenn der Inhalt der Komponente größer
		// ist als die definierte Sicht (Grösse).

		textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setBounds(5, 5, 580, 360);
		this.add(textAreaScrollPane);

		btnDatei = new JButton("Datei öffnen...");
		btnDatei.setBounds(595, 275, 150, 25);
		btnDatei.addActionListener(this);
		this.add(btnDatei);

		btnDialog = new JButton("Dialog anzeigen");
		btnDialog.setBounds(595, 305, 150, 25);
		btnDialog.addActionListener(this);
		this.add(btnDialog);

		btnBeenden = new JButton("Beenden");
		btnBeenden.setBounds(595, 335, 150, 25);
		btnBeenden.addActionListener(this);
		this.add(btnBeenden);

		CBbreak = new JCheckBox("Zeilenumbruch");
		CBbreak.setBounds(600, 10, 200, 15);
		CBbreak.addItemListener(new CheckBoxBreakListener());
		this.add(CBbreak);
		CBbreak.setSelected(true);

		CBbreakWord = new JCheckBox("Zeilenumbruch (Wort)");
		CBbreakWord.setBounds(600, 30, 200, 15);
		CBbreakWord.addItemListener(new CheckBoxBreakWordListener());
		this.add(CBbreakWord);
		CBbreakWord.setSelected(true);

	}

	private void initFrame()
	{
		// Inder Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);

		textArea.setText("Mit dem JTextArea-Steuerelement kann der Benutzer Text in einer Anwendung eingeben. "
				+ "Dieses Steuerelement bietet eine Funktionalität, die über das Standard-JTextField-Steuerelement von Java hinausgeht. "
				+ "Dazu gehören Mehrzeilenbearbeitung und Zeichenmaskierung für Kennwörter. "
				+ "Normalerweise wird ein JTextField-Steuerelement für die Anzeige oder Eingabe einer einzelnen Textzeile verwendet.");

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

	private void dateiWaehlen()
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
			fcFile = fc.getSelectedFile();
			dateiLesen();
		}

	}

	private void dateiLesen()
	{

		long StartTime = 0;
		long EndTime = 0;

		String line;
		StringBuffer text = new StringBuffer();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		// Aktuellen Inhalt der TextArea löschen
		textArea.setText(null);

		try
		{

			fileReader = new FileReader(fcFile);
			bufferedReader = new BufferedReader(fileReader);

			StartTime = System.currentTimeMillis();

			while ((line = bufferedReader.readLine()) != null)
				text.append(line + "\n");
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}

		textArea.append(text.toString());
		EndTime = System.currentTimeMillis();

		try
		{
			if (fileReader != null)
				fileReader.close();
			if (bufferedReader != null)
				bufferedReader.close();
		}
		catch (Exception ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		textArea.requestFocusInWindow();

		JOptionPane.showMessageDialog(this, "Die Zeit zum Laden der Textdatei betrug: " + (EndTime - StartTime) + " ms", "Zeitmessung",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private boolean queryExit()
	{

		boolean retValue = false;

		String[] options =
		{ "Ja", "Nein" };

		int dlgValue = JOptionPane.showOptionDialog(this, "Soll das Programm beendet werden", "Program beenden", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (dlgValue == JOptionPane.YES_OPTION)
			retValue = true;

		return retValue;

	}

	public static void main(String[] args)
	{

		FrameDialog f = new FrameDialog();
		f.showFrame();

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnBeenden)
			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		else if (e.getSource() == btnDialog)
			anzeigeDialog();
		else if (e.getSource() == btnDatei)
			dateiWaehlen();

	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e)
	{

		System.out.println("windowClosed()");

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

	private class CheckBoxBreakListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
				textArea.setLineWrap(true);
			else
			{
				textArea.setLineWrap(false);
				textArea.setWrapStyleWord(false);
				CBbreakWord.setSelected(false);
			}

		}

	}

	private class CheckBoxBreakWordListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{

			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				textArea.setWrapStyleWord(true);
				textArea.setLineWrap(true);
				CBbreak.setSelected(true);;
			}
			else
			{
				textArea.setWrapStyleWord(false);
			}
		}

	}

}
