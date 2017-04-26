package W4T5_Dozent;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameDialog extends JFrame implements WindowListener, ActionListener
{

	private JTextArea textArea;
	private JScrollPane textAreaScrollPane;
	
	private JButton btnBeenden, btnDialog, btnDatei;
	
	private File fcFile;
	
	public FrameDialog()
	{
		
		initializeComponents();
		
	}
	
	private void initializeComponents()
	{
		this.setTitle("Hauptfenster des Programms");
		this.setSize(760,  400);
		
		// Keine Gr�ssen�nderung des Frames
		this.setResizable(false);
		
		// Layout Manager ausschalten
		this.setLayout(null);
		
		// Das Schlie�en des Fensters wird vom WindowListener �berwacht.
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
		
		
		
		// Stellt horizontale und vertikale Laufleisten zum Bl�ttern
		// zur Verf�gung, wenn der Inhalt der Komponente gr��er
		// ist als die definierte Sicht (Gr�sse).
		
		textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setBounds(5, 5, 580, 360);
		this.add(textAreaScrollPane);
		
		btnDatei = new JButton("Datei �ffnen...");
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
		
		
	}
	
	private void initFrame()
	{
		// Inder Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);
		
		textArea.setText("Mit dem JTextArea-Steuerelement kann der Benutzer Text in einer Anwendung eingeben. "
				+ "Dieses Steuerelement bietet eine Funktionalit�t, die �ber das Standard-JTextField-Steuerelement von Java hinausgeht. "
				+ "Dazu geh�ren Mehrzeilenbearbeitung und Zeichenmaskierung f�r Kennw�rter. "
				+ "Normalerweise wird ein JTextField-Steuerelement f�r die Anzeige oder Eingabe einer einzelnen Textzeile verwendet.");
		
		
	}
	
	
	private void anzeigeDialog()
	{
		
		FrameDialogModal dlg = new FrameDialogModal();
		
		// Mit �bergabe des Eigent�mers
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
		fc.setDialogTitle("Textdokument �ffnen");
		
		fc.setCurrentDirectory(fcFile);
		
		
		// Alle Dateien (*.*) als Dateifilter wird nicht angeboten. 
		// Standard = true.
		fc.setAcceptAllFileFilterUsed(false);
		
		
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			fcFile = fc.getSelectedFile();
			dateiLesen(fc.getSelectedFile().toString());
		}
		
		
		
		
	}
	
	
	private void dateiLesen(String Dateiname)
	{
		
		Scanner in = null;
		
		
		// Aktuellen Inhalt der TextArea l�schen
		textArea.setText(null);
		
		try
		{
			in = new Scanner(new FileInputStream(Dateiname));
			
			while(in.hasNextLine())
				textArea.append(in.nextLine() + "\n");
			
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
		if (in != null)
			in.close();
		
		
		
		textArea.requestFocusInWindow();
	
		
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnBeenden)
			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		else if (e.getSource() == btnDialog)
			anzeigeDialog();
		else if (e.getSource() == btnDatei)
			dateiLesen();
		
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

}
