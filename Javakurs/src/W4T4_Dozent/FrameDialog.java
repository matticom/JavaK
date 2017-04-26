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
		
		// Keine Grössenänderung des Frames
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
		
		
		
		// Stellt horizontale und vertikale Laufleisten zum Blättern
		// zur Verfügung, wenn der Inhalt der Komponente größer
		// ist als die definierte Sicht (Grösse).
		
		textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setBounds(5, 5, 580, 360);
		this.add(textAreaScrollPane);
		
		
		
		
	
		
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
