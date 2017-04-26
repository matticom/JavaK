package W4T4_Dozent;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FrameDialog extends JFrame implements ActionListener
{

	private JTextArea textArea;
	private JScrollPane textAreaScrollPane;
	
	
	
	
	
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
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
	
	
	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
		
	}
	
	
	
	public static void main(String[] args)
	{
		
		FrameDialog f = new FrameDialog();
		f.showFrame();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
