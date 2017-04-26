package W3T5_Dozent;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.Popup;

public class Grafik1
{

	private JFrame frame;	
	private JLabel lbl1, lbl2;
	private JTextField tf1, tf2;
	private JButton btnBeenden, btnKopieren, btnLoeschen;
	private JOptionPane confirm;
	private Popup pop;

	
	
	public Grafik1()
	{
		initializeComponents();
		
	}
	
	private void initializeComponents()
	{
		
		frame = new JFrame("Erstes Grafikprogramm");
		
		// Layout
		// Layouts sind Strukturen, welche grafische Elemente in einem JFrame
		// oder einer anderen Containerklasse so anordnen,
		// wie man es gerne m�chte.
		// Standard Layout der ContentPane des JFrames: BorderLayout
		// Der BorderLayout Manager unterteilt Container in f�nf Bereiche, in
		// denen Grafikkomponenten untergebracht werden k�nnen.
		// Einem Bereich kann dabei immer nur eine Komponente zugerodnet werden.
		
		// Kein Layout
		frame.setLayout(null);
	
		
		// Muss angegeben werden, da sonst das Fenster nur geschlossen wird
		// (es wird unsichtbar), die Klasse selbst bleibt jedoch aktiv!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		// Position des Frames
//		frame.setLocation(200,  100);
//		
//		// Gr�sse des Frames
//		frame.setSize(350, 150);
		
		// Position und Gr�sse des Frames
		frame.setBounds(200, 100, 350, 150);
		
		// Keine Gr�ssen�nderung des Frames
		frame.setResizable(false);
		
		
		
		// Beschriftung hinzuf�gen
		lbl1 = new JLabel("Eingabe");
		
		// Gr�sse und Position festlegen
		lbl1.setBounds(10, 10, 90, 20);
		
		// Beschriftung zum Frame hinzuf�gen
		frame.add(lbl1);
		
		// Zur �berpr�fung von Breite und H�he
		// eine Umrandung f�r den Label verwenden.
		//lbl1.setBorder(LineBorder.createBlackLineBorder());
		
		// Textfeld hinzuf�gen
		tf1 = new JTextField();
		tf1.setBounds(100,  10,  200,  20);
		frame.add(tf1);
		
		// Beschriftung hinzuf�gen
		lbl2 = new JLabel("Kopie");
		
		// Gr�sse und Position festlegen
		lbl2.setBounds(10, 40, 90, 20);
		
		// Beschriftung zum Frame hinzuf�gen
		frame.add(lbl2);
		
		// Textfeld hinzuf�gen
		tf2 = new JTextField("Kopie");
		tf2.setBounds(100,  40,  200,  20);
		
		// Nicht editierbar, keine Eingabe m�glich.
		// Der Hintergrund des Textfeldes bleibt unver�ndert, die Schriftfarbe
		// wird grau.
		// Die Komponente kann auch den Eingabefokus nicht erhalten.
		//tf2.setEnabled(false);
		
		// Der Hintergrund wird grau, die Schriftfarbe bleibt schwarz.
		// Die Komponente kann jedoch immer noch den Eingabefokus erhalten.
		//tf2.setEditable(false);
		
		// Hintergund- und Schriftfarbe bleiben unver�ndert, eine Eingabe ist
		// nicht m�glich.
		// Die Komponente kann auch den Eingabefokus nicht erhalten.
		tf2.setFocusable(false);
		frame.add(tf2);
		
		// Buttons zum Frame hinzuf�gen
		btnBeenden = new JButton("Beenden");
		btnBeenden.setBounds(230,  80,  100,  25);
		btnBeenden.addActionListener(new ButtonBeendenActionListener());
		
		frame.add(btnBeenden);
		
		btnKopieren = new JButton("Kopieren");
		btnKopieren.setBounds(10,  80,  100,  25);
		btnKopieren.addActionListener(new ButtonKopierenActionListener());
		
		frame.add(btnKopieren);
		
		btnLoeschen = new JButton("L�schen");
		btnLoeschen.setBounds(120,  80,  100,  25);
		btnLoeschen.addActionListener(new ButtonLoeschenActionListener());
				
		frame.add(btnLoeschen);
		
		tf1.addCaretListener(new caretListenerClass());
		//tf1.addFocusListener(l);
	}
	
	public void showFrame()
	{
		
		frame.setVisible(true);
		
		
	}
	
	
	private class ButtonBeendenActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			System.out.println(((JButton)e.getSource()).getText() + ": Button wurde geklickt.");
			System.exit(0);
			
			
		}
		
	}
	
	private class ButtonKopierenActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			Grafik1.this.tf2.setText(Grafik1.this.tf1.getText());
			Grafik1.this.tf1.setText("");
			Grafik1.this.tf1.requestFocusInWindow();
			Grafik1.this.tf1.setText("'");
			//Grafik1.this.tf1.setCaretPosition(21);
			System.out.println((int)Grafik1.this.tf1.getText().toCharArray()[0]);
				
			JOptionPane.showMessageDialog(btnKopieren, "Wurde erfolgreich kopiert :)","Best�tigung",JOptionPane.INFORMATION_MESSAGE);
	
		}
		
	}
	
	private class ButtonLoeschenActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			Grafik1.this.tf1.setText("");
			Grafik1.this.tf2.setText("");
			Grafik1.this.tf1.requestFocus();
					
		}
		
	}
	
	private class caretListenerClass implements CaretListener
	{

		@Override
		public void caretUpdate(CaretEvent e)
		{
			System.out.println("Aktuelle Position des Cursors in Textfeld 1: " + e.getMark());
			
		}
		
	}
	
	
}
