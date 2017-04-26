package W4T4_Dozent;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Farbmischer extends JFrame implements ChangeListener, ActionListener, KeyListener, FocusListener, ClipboardOwner
{
	
	private static final int COLOR_MIN = 0;
	private static final int COLOR_MAX = 255;
	private static final int COLOR_INIT = 238;
	
	
	private JLabel labelRed, labelGreen, labelBlue, labelTransparency;
	private JSlider sliderRed, sliderGreen, sliderBlue, sliderTransparency;
	
	private JTextField tfRed, tfHexRed, tfGreen, tfHexGreen, tfBlue, tfHexBlue, tfTransparency;
	private JButton btnBeenden, btnZwischenablage;
	
	
	private Hashtable<Integer, JLabel> sliderLabelTabelle;
	
	
	public Farbmischer()
	{
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		
		this.setTitle("Farbmischer");
		this.setSize(520,  340);
		
		// Keine Grössenänderung des Frames
		this.setResizable(false);
		
		// Layout Manager ausschalten
		this.setLayout(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		labelRed = new JLabel("Rot");
		labelRed.setBounds(30,  10,  30,  25);
		this.add(labelRed);
		
				
		sliderRed = new JSlider(JSlider.HORIZONTAL, COLOR_MIN, COLOR_MAX, COLOR_INIT);
		sliderRed.setBounds(120,  10,  300,  50);
		
		// Einstellungen für die Skala des Schiebereglers
		
		// Distanz zwischen den Hauptmarkierungen festlegen
		sliderRed.setMajorTickSpacing(15);
		
		// Distanz zwischen den Zwischenmarkierungen festlegen
		sliderRed.setMinorTickSpacing(5);
		
		// Skala anzeigen
		sliderRed.setPaintTicks(true);
		
		// Zu viele Labels; können nicht vernünftig angezeigt werden.
		//sliderRed.setPaintLabels(true);
		
		// Stattdessen:
		// Erstellen einer Label-Tabelle.
		
		sliderLabelTabelle = new Hashtable<>();
		sliderLabelTabelle.put(COLOR_MIN, new JLabel(Integer.toString(COLOR_MIN)));
		sliderLabelTabelle.put(COLOR_MAX, new JLabel(Integer.toString(COLOR_MAX)));
		
		sliderRed.setLabelTable(sliderLabelTabelle);
		sliderRed.setPaintLabels(true);
		sliderRed.addChangeListener(this);
		this.add(sliderRed);
		
		tfHexRed = new JTextField();
		tfHexRed.setBounds(440,  10,  40, 25);
		tfHexRed.setFocusable(false);
		tfHexRed.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(tfHexRed);
		
		tfRed = new JTextField();
		tfRed.setBounds(440,  35,  40, 25);
		tfRed.setHorizontalAlignment(SwingConstants.CENTER);
		tfRed.addKeyListener(this);
		tfRed.addFocusListener(this);
		this.add(tfRed);
		
		labelGreen = new JLabel("Grün");
		labelGreen.setBounds(30, 60, 30, 25);
		this.add(labelGreen);
		
		sliderGreen = new JSlider(JSlider.HORIZONTAL, COLOR_MIN, COLOR_MAX, COLOR_INIT);
		sliderGreen.setBounds(120, 60, 300, 50);
		sliderGreen.setMajorTickSpacing(15);
		sliderGreen.setMinorTickSpacing(5);
		sliderGreen.setPaintTicks(true);
		sliderGreen.setLabelTable(sliderLabelTabelle);
		sliderGreen.setPaintLabels(true);
		sliderGreen.addChangeListener(this);
		this.add(sliderGreen);
		
		tfHexGreen = new JTextField();
		tfHexGreen.setBounds(440, 60, 40, 25);
		tfHexGreen.setHorizontalAlignment(JTextField.CENTER);
		tfHexGreen.setFocusable(false);
		this.add(tfHexGreen);

		tfGreen = new JTextField();
		tfGreen.setBounds(440, 85, 40, 25);
		tfGreen.setHorizontalAlignment(JTextField.CENTER);
		tfGreen.addKeyListener(this);
		tfGreen.addFocusListener(this);
		this.add(tfGreen);

		labelBlue = new JLabel("Blau");
		labelBlue.setBounds(30, 110, 30, 25);
		this.add(labelBlue);
		
		sliderBlue = new JSlider(JSlider.HORIZONTAL, COLOR_MIN, COLOR_MAX, COLOR_INIT);
		sliderBlue.setBounds(120, 110, 300, 50);
		sliderBlue.setMajorTickSpacing(15);
		sliderBlue.setMinorTickSpacing(5);
		sliderBlue.setPaintTicks(true);
		sliderBlue.setLabelTable(sliderLabelTabelle);
		sliderBlue.setPaintLabels(true);
		sliderBlue.addChangeListener(this);
		this.add(sliderBlue);
		
		tfHexBlue = new JTextField();
		tfHexBlue.setBounds(440, 110, 40, 25);
		tfHexBlue.setHorizontalAlignment(JTextField.CENTER);
		tfHexBlue.setFocusable(false);
		this.add(tfHexBlue);

		tfBlue = new JTextField();
		tfBlue.setBounds(440, 135, 40, 25);
		tfBlue.setHorizontalAlignment(JTextField.CENTER);
		tfBlue.addKeyListener(this);
		tfBlue.addFocusListener(this);
		this.add(tfBlue);

		// Transparenz
		
		labelTransparency = new JLabel("Transparenz");
		labelTransparency.setBounds(30, 200, 80, 25);
		this.add(labelTransparency);

		sliderTransparency = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		sliderTransparency.setBounds(120, 200, 300, 50);
		sliderTransparency.setMajorTickSpacing(10);
		sliderTransparency.setMinorTickSpacing(5);
		sliderTransparency.setPaintTicks(true);
		sliderTransparency.setOpaque(false);
		sliderTransparency.addChangeListener(this);
		this.add(sliderTransparency);
		
		tfTransparency = new JTextField();
		tfTransparency.setBounds(440, 200, 40, 25);
		tfTransparency.setHorizontalAlignment(JTextField.CENTER);
		tfTransparency.setFocusable(false);
		this.add(tfTransparency);
		
		
		btnZwischenablage = new JButton("Zwischenablage");
		btnZwischenablage.setMnemonic('Z');
		btnZwischenablage.setBounds(120, 260, 140, 30);
		btnZwischenablage.addActionListener(this);
		this.add(btnZwischenablage);
		
		btnBeenden = new JButton("Beenden");
		btnBeenden.setMnemonic('e');
		btnBeenden.setBounds(280, 260, 140, 30);
		btnBeenden.addActionListener(this);
		this.add(btnBeenden);
		
	}

	
	private void initFrame()
	{
		// In der Mitte des Desktops positionieren
		this.setLocationRelativeTo(null);
		
		scrollSlider();
		setTransparency();
		
	}
	

	private void scrollSlider()
	{
		
		Color labelForeColor = Color.BLACK;
		
		int valRed = sliderRed.getValue();
		int valGreen = sliderGreen.getValue();
		int valBlue = sliderBlue.getValue();
		
		
		// Jeder einzelne Schieberegler bekommt seine  aktuellen spezifischen
		// Farbanteil als Hintergrundfarbe zugewiesen.
		
		sliderRed.setBackground(new Color(valRed, 0, 0));
		sliderGreen.setBackground(new Color(0, valGreen, 0));
		sliderBlue.setBackground(new Color(0, 0, valBlue));
		
		// Die Frame-Hintergrundfarbe soll sich aus der Zusammensetzung
		// der aktuellen RGB-Anteile der Schieberegler ergeben.
		this.getContentPane().setBackground(new Color(valRed, valGreen, valBlue));
			
		
		tfHexRed.setText(String.format("X'%02X'", valRed));
		tfRed.setText(String.valueOf(valRed));
		
		tfHexGreen.setText(String.format("X'%02X'", valGreen));
		tfGreen.setText(String.valueOf(valGreen));
		
		tfHexBlue.setText(String.format("X'%02X'", valBlue));
		tfBlue.setText(String.valueOf(valBlue));
		
		
		// Die Vordergrundfarbe der Labels innerhalb der SLider grundsätzlich auf weiß setzen
		sliderLabelTabelle.get(COLOR_MIN).setForeground(Color.WHITE);
		sliderLabelTabelle.get(COLOR_MAX).setForeground(Color.WHITE);
		
		// Textfarbe der Labels auf dem Frame so einstellen, dass sie immer lesbar sind.
		
		// 1.
//		if (valRed + valGreen + valBlue < 300)
//			labelForeColor = Color.WHITE;
//		else
//			labelForeColor = Color.BLACK;
		
		// oder
		
		// 2.
//		labelForeColor = new Color(255 - valRed, 255 - valGreen, 255 - valBlue);
		
		
		// oder
		//labelForeColor = new Color((valRed + 128) % 256, (valGreen + 128) % 256, (valBlue + 128) % 256);
		
		// oder
		labelForeColor = new Color(valRed ^ 0x80, valGreen ^ 0x80, valBlue ^ 0x80);
		
		
		
		for (Component c : this.getContentPane().getComponents())
		{
			if (c instanceof JLabel)
				c.setForeground(labelForeColor);
			
		}
		
		
	}
	
	
	
	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
	}
	
	
	private void setSliderValue(JTextField tf)
	{
		
		// Standardmässig den Schieberegler der Farbe Rot
		// setzen.
		JSlider slider = sliderRed;
		
		int value = 0;
		
		if (tf == tfGreen)
			slider = sliderGreen;
		else if (tf == tfBlue)
			slider = sliderBlue;
		
		
		try
		{
			
			value = Integer.parseInt("0" + tf.getText());
				
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Ungültige Eingabe", "Fehler", JOptionPane.ERROR_MESSAGE);
			tf.setText(Integer.toString(slider.getValue()));
			value = slider.getValue();
		}
		
		
		if (value > COLOR_MAX)
		{
			JOptionPane.showMessageDialog(this, "Der maximale Farbwert beträgt " + COLOR_MAX, "Fehler", JOptionPane.ERROR_MESSAGE);
			tf.setText(Integer.toString(slider.getValue()));
			value = slider.getValue();
		}
		
		
		// Löst das stateChanged-Ereignis des Schieberegler aus,
		// wenn sich der Wert des Schiebereglers geändert hat.
		slider.setValue(value);
		
		
	}
	
	private int convertTextFieldToValue(JTextField tf, KeyEvent e)
	{
		// Das Zeichen kann irgendwo innerhalb des TextFeldes hinzufegügt worden sein.
		
		// Position, an der das zeichen eingefügt wurde, ermiteln.
		int charPos = tf.getCaretPosition();
		String tempString = tf.getText().substring(0, charPos) + e.getKeyChar() + tf.getText().substring(charPos); 
		
		// Umwandlung der Zeichenkette in einen Integer-Wert
		return Integer.parseInt(tempString);
		
		
	}
	
	
	private void saveToClipBoard()
	{
		String htmlColor = String.format("#%02X%02X%02X", sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue());
		
		String tempString = "// Farbmischer(Rot, Grün, Blau):\n";
		tempString += "// Color newColor = new Color(" + sliderRed.getValue() + ", " + 
		                                                 sliderGreen.getValue() + ", " + 
				                                         sliderBlue.getValue() +");\n";
		tempString += "// " + htmlColor;
		
		// setContents() erwartet als erstes Argument ein Objekt, welches die
		// Schnittstelle 'Transferable' implementiert.
		// Für Zeichenketten kann die Klasse StringSelection verwendet werden.		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(tempString), 
				// Eigentümer der Zwischenablage oder 'null'
				// Implementierung der Schnittstelle 'ClipBoardOwner'
				this);
		
	}
	
	
	private void setTransparency()
	{
		
		// Notwendige Anpassungen des Frames um die Methode setOpacity()
		// verwenden zu können
		// siehe Methode initializeComponents().
		
		this.setOpacity(1 - (sliderTransparency.getValue() / 100.0F))	;
		tfTransparency.setText(String.format("%d", sliderTransparency.getValue()) + " %");
		
		
	}
	
	
	
	
	public static void main(String[] args)
	{
		
		Farbmischer f = new Farbmischer();
		f.showFrame();

	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		
		if (e.getSource() instanceof JSlider)
		{
			
			if (e.getSource() == sliderTransparency)
				setTransparency();
			else
				scrollSlider();
		}
		
		
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		
		if (e.getSource() instanceof JTextField)
			((JTextField)e.getSource()).selectAll();
		
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		
		if (e.getSource() instanceof JTextField)
			setSliderValue((JTextField)e.getSource());
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
		JTextField tf = null;
		
		if (e.getSource() instanceof JTextField)
			tf = (JTextField)e.getSource();
		else 
			return;
		
		// Steuertasten ignorieren
		if (Character.isISOControl(e.getKeyChar()))
			return;
		
		// Überprüfung auf Ziffer 0 - 9
		if (!Character.isDigit(e.getKeyChar()))
		{
			Toolkit.getDefaultToolkit().beep();
			e.consume();
			return;
		}
				
		
		// Zuerst evtl. markierte Zeichen löschen
		tf.replaceSelection(null);
		
		if (tf.getText().length() >= 3)
		{
			Toolkit.getDefaultToolkit().beep();
			e.consume();
			return;
		}

		if (convertTextFieldToValue(tf, e) > COLOR_MAX)
		{
			Toolkit.getDefaultToolkit().beep();
			e.consume();
		}
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource() == btnBeenden)
			System.exit(0);
		else if (e.getSource() == btnZwischenablage)
			saveToClipBoard();
				
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents)
	{
		
		System.out.println("Der Inhalt der Zwischenablage wurde von einem anderen Programm überschrieben.");
		
		
		if (contents.isDataFlavorSupported(DataFlavor.stringFlavor))
		{
			try
			{
				String str = (String)contents.getTransferData(DataFlavor.stringFlavor);
				System.out.println(str);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			
		}
	
	}

}
