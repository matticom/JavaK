package W4T3_MK;

import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Farbmischer extends JFrame implements ChangeListener
{

	private static final int COLOR_MIN = 0;
	private static final int COLOR_MAX = 255;
	private static final int COLOR_INIT = 238;
	private static final int MAX_LENGTH = 3;
	private static final int MAX_HEX_LENGTH = 5;

	private JLabel labelRed, labelGreen, labelBlue;
	private JSlider sliderRed, sliderGreen, sliderBlue;

	private JTextField tfRed, tfHexRed, tfGreen, tfHexGreen, tfBlue, tfHexBlue, tf;

	private JButton exitBtn;

	private Hashtable<Integer, JLabel> sliderLabelTabelle;
	
	private CaretListener red, green, blue;

	
	public Farbmischer()
	{
		initializeComponents();
	}

	private void initializeComponents()
	{

		this.setTitle("Farbmischer");
		this.setSize(480, 280);

		// Keine Grössenänderung des Frames
		this.setResizable(false);

		// Layout Manager ausschalten
		this.setLayout(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		labelRed = new JLabel("Rot");
		labelRed.setBounds(30, 10, 30, 25);
		this.add(labelRed);

		sliderRed = new JSlider(JSlider.HORIZONTAL, COLOR_MIN, COLOR_MAX, COLOR_INIT);
		sliderRed.setBounds(80, 10, 300, 50);

		// Einstellungen für die Skala des Schiebereglers

		// Distanz zwischen den Hauptmarkierungen festlegen
		sliderRed.setMajorTickSpacing(15);

		// Distanz zwischen den Zwischenmarkierungen festlegen
		sliderRed.setMinorTickSpacing(5);

		// Skala anzeigen
		sliderRed.setPaintTicks(true);

		// Zu viele Labels; können nicht vernünftig angezeigt werden.
		// sliderRed.setPaintLabels(true);

		// Stattdessen:
		// Erstellen einer Label-Tabelle.

		// Slider Green....
		labelGreen = new JLabel("Grün");
		labelGreen.setBounds(30, 80, 30, 25);
		this.add(labelGreen);

		sliderGreen = new JSlider(JSlider.HORIZONTAL, COLOR_MIN, COLOR_MAX, COLOR_INIT);
		sliderGreen.setBounds(80, 80, 300, 50);
		sliderGreen.setMajorTickSpacing(15);
		sliderGreen.setMinorTickSpacing(5);
		sliderGreen.setPaintTicks(true);

		// Slider Blue....
		labelBlue = new JLabel("Blau");
		labelBlue.setBounds(30, 150, 30, 25);
		this.add(labelBlue);

		sliderBlue = new JSlider(JSlider.HORIZONTAL, COLOR_MIN, COLOR_MAX, COLOR_INIT);
		sliderBlue.setBounds(80, 150, 300, 50);
		sliderBlue.setMajorTickSpacing(15);
		sliderBlue.setMinorTickSpacing(5);
		sliderBlue.setPaintTicks(true);

		// HashMap
		sliderLabelTabelle = new Hashtable<>();
		sliderLabelTabelle.put(COLOR_MIN, new JLabel(Integer.toString(COLOR_MIN)));
		sliderLabelTabelle.put(COLOR_MAX, new JLabel(Integer.toString(COLOR_MAX)));

		// 1. Regler
		sliderRed.setLabelTable(sliderLabelTabelle);
		sliderRed.setPaintLabels(true);
		sliderRed.addChangeListener(this);
		this.add(sliderRed);

		tfHexRed = new JTextField();
		tfHexRed.setBounds(400, 10, 40, 25);
		tfHexRed.setHorizontalAlignment(SwingConstants.CENTER);
		tfHexRed.addFocusListener(new HexTextFieldFocusListener());
		tfHexRed.addKeyListener(new HexColorValueTextFieldListener());
//		tfHexRed.addCaretListener(new caretListenerClass());
		this.add(tfHexRed);

		tfRed = new JTextField();
		tfRed.setBounds(400, 35, 40, 25);
		tfRed.setHorizontalAlignment(SwingConstants.CENTER);
		tfRed.addFocusListener(new TextFieldFocusListener());
		tfRed.addKeyListener(new ColorValueTextFieldListener());
		this.add(tfRed);

		// 2. Regler
		sliderGreen.setLabelTable(sliderLabelTabelle);
		sliderGreen.setPaintLabels(true);
		sliderGreen.addChangeListener(this);
		this.add(sliderGreen);

		tfHexGreen = new JTextField();
		tfHexGreen.setBounds(400, 80, 40, 25);
		tfHexGreen.addFocusListener(new HexTextFieldFocusListener());
		tfHexGreen.addKeyListener(new HexColorValueTextFieldListener());
//		tfHexGreen.addCaretListener(new caretListenerClass());
		tfHexGreen.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(tfHexGreen);

		tfGreen = new JTextField();
		tfGreen.setBounds(400, 105, 40, 25);
		tfGreen.setHorizontalAlignment(SwingConstants.CENTER);
		tfGreen.addFocusListener(new TextFieldFocusListener());
		tfGreen.addKeyListener(new ColorValueTextFieldListener());
		this.add(tfGreen);

		// 3. Regler
		sliderBlue.setLabelTable(sliderLabelTabelle);
		sliderBlue.setPaintLabels(true);
		sliderBlue.addChangeListener(this);
		this.add(sliderBlue);

		tfHexBlue = new JTextField();
		tfHexBlue.setBounds(400, 150, 40, 25);
		tfHexBlue.setHorizontalAlignment(SwingConstants.CENTER);
		tfHexBlue.addFocusListener(new HexTextFieldFocusListener());
		tfHexBlue.addKeyListener(new HexColorValueTextFieldListener());
//		tfHexBlue.addCaretListener(new caretListenerClass());
		this.add(tfHexBlue);

		tfBlue = new JTextField();
		tfBlue.setBounds(400, 175, 40, 25);
		tfBlue.setHorizontalAlignment(SwingConstants.CENTER);
		tfBlue.addFocusListener(new TextFieldFocusListener());
		tfBlue.addKeyListener(new ColorValueTextFieldListener());
		this.add(tfBlue);

		exitBtn = new JButton("Beenden");
		exitBtn.setBounds(170, 210, 140, 30);
		exitBtn.addActionListener(new ButtonBeendenActionListener());
		this.add(exitBtn);
		
		red = new caretListenerClass();
		green = new caretListenerClass();
		blue = new caretListenerClass();
	}

	private class ButtonBeendenActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);

		}

	}

	private class ColorValueTextFieldListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				// setFont();
				// btnBeenden.requestFocusInWindow();
				// oder
				KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();

			}

		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e)
		{

			if (e.getSource() instanceof JTextField)
			{

				tf = ((JTextField) e.getSource());

			}

			if (Character.isISOControl(e.getKeyChar()))
				return;

			// Nur Ziffern 0 - 9
			if (!Character.isDigit(e.getKeyChar()))
			{
				Toolkit.getDefaultToolkit().beep();
				e.consume();
				return;
			}

			// Zuerst einen evtl. markierten Text löschen
			tf.replaceSelection(null);

			// Überprüfung auf maximale Eingabelänge
			if (tf.getText().length() >= MAX_LENGTH)
			{
				Toolkit.getDefaultToolkit().beep();
				e.consume();
			}

		}

	}

	private class HexColorValueTextFieldListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e)
		{
						
			
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				// setFont();
				// btnBeenden.requestFocusInWindow();
				// oder
				KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();

			}

		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e)
		{

//			tfHexRed.addCaretListener(red);
//			tfHexBlue.addCaretListener(green);
//			tfHexGreen.addCaretListener(blue);
			
			if (e.getSource() instanceof JTextField)
			{
				tf = ((JTextField) e.getSource());
				if (tf == tfHexRed)
					System.out.println("Sie sind gleich HEX!!!");
			}

			if (Character.isISOControl(e.getKeyChar()))
				return;

			// Nur Ziffern 0 - 9
			if (Character.isDigit(e.getKeyChar()) || (e.getKeyChar() >= ('A') && e.getKeyChar() <= ('F'))
					|| (e.getKeyChar() >= ('a') && e.getKeyChar() <= ('f')) || e.getKeyChar() == (39))
				System.out.println("Ist Okay Hex");
			else
			{
				Toolkit.getDefaultToolkit().beep();
				System.out.println("Problem mit Charakter!!!");
				e.consume();
				return;
			}

			// Zuerst einen evtl. markierten Text löschen
			tf.replaceSelection(null);

			// Überprüfung auf maximale Eingabelänge
			if (tf.getText().length() >= MAX_HEX_LENGTH)
			{
				Toolkit.getDefaultToolkit().beep();
				System.out.println("Problem mit Länge!!!");
				e.consume();
			}

		}

	}

	private class HexTextFieldFocusListener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e)
		{
			
			if (e.getSource() instanceof JTextField)
				((JTextField) e.getSource()).select(2, 4);
		}

		@Override
		public void focusLost(FocusEvent e)
		{
			if (tf == tfHexRed)
				System.out.println("Focus Lost Hex:" +tfHexRed.getText());
			
//			tfHexRed.removeCaretListener(red);
//			tfHexBlue.removeCaretListener(green);
//			tfHexGreen.removeCaretListener(blue);
			setHexColor();
			
		}

	}

	private class TextFieldFocusListener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e)
		{

			if (e.getSource() instanceof JTextField)
				((JTextField) e.getSource()).selectAll();
		}

		@Override
		public void focusLost(FocusEvent e)
		{

			setColor();
			
			
		}

	}

	private void setHexColor()
	{
		
		String hexString;
		int newSize = 0;

		if (tf == tfHexRed)
			System.out.println("setHexColor (nach Lost)" +tfHexRed.getText());
		
		if (tf.getText().length() == 5) //&& tf.getText().substring(0, 1).equalsIgnoreCase("X"))
		{
			System.out.println("setHexColor (nach Lost nach)" +tfHexRed.getText());
			hexString = (tf.getText().substring(2, 4));
			System.out.println("setHexColor (nach Lost nach)" +hexString);
			try
			{
				newSize = Integer.parseInt(hexString, 16);
				System.out.println("in Try block" +newSize);
			}
			catch (Exception ex)
			{
				System.out.println("in catch block" +newSize);
				JOptionPane.showMessageDialog(this, ex.getMessage(), "FehlerHex", JOptionPane.ERROR_MESSAGE);
				tf.setText("X'" + Integer.toHexString(COLOR_INIT).toUpperCase() + "'");
				newSize = COLOR_INIT;

			}
		}
		System.out.println("setHexColor:"+newSize);
		if (newSize < COLOR_MIN)
		{
			JOptionPane.showMessageDialog(this, "Die Mindestwert beträgt " + COLOR_MIN, "Fehler",
					JOptionPane.ERROR_MESSAGE);
			tf.setText("X'" + Integer.toHexString(COLOR_INIT).toUpperCase() + "'");
		}
		else if (newSize > COLOR_MAX)
		{
			JOptionPane.showMessageDialog(this, "Der maximale Wert beträgt " + COLOR_MIN, "Fehler",
					JOptionPane.ERROR_MESSAGE);
			tf.setText("X'" + Integer.toHexString(COLOR_INIT).toUpperCase() + "'");
		}
		else
		{
			// System.out.println("X'"+Integer.toHexString(newSize).toUpperCase()+"'");
			// tf.setText("X'"+Integer.toHexString(newSize).toUpperCase()+"'");
			System.out.println("ganz am Ende" +tfHexRed.getText());
			whichHexTfToTf().setText(Integer.toString(newSize));
			whichJSliderFromTf().setValue(newSize);
			this.getContentPane()
					.setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
		}

	}

	private void setColor()
	{

		int newSize = 0;
		System.out.println("getText von setColor:" + tf.getText());
		try
		{
			newSize = Integer.parseInt(tf.getText());
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, ex.getMessage(), "FehlerDez", JOptionPane.ERROR_MESSAGE);
			tf.setText(Integer.toString(COLOR_INIT));
			newSize = COLOR_INIT;
		}
		System.out.println("setHexColor:"+newSize);
		if (newSize < COLOR_MIN)
		{
			JOptionPane.showMessageDialog(this, "Die Mindestwert beträgt " + COLOR_MIN, "Fehler",
					JOptionPane.ERROR_MESSAGE);
			tf.setText(Integer.toString(COLOR_INIT));
		}
		else if (newSize > COLOR_MAX)
		{
			JOptionPane.showMessageDialog(this, "Der maximale Wert beträgt " + COLOR_MIN, "Fehler",
					JOptionPane.ERROR_MESSAGE);
			tf.setText(Integer.toString(COLOR_INIT));
		}
		else
		{
			tf.setText(Integer.toString(newSize));
			whichTfToTfHex().setText(Integer.toHexString(newSize));
			whichJSliderFromTf().setValue(newSize);
			this.getContentPane().setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
		}

	}

	private JTextField whichTfToTfHex()
	{

		if (tf == tfRed)
			return tfHexRed;
		if (tf == tfGreen)
			return tfHexGreen;

		return tfHexBlue;
	}

	private JTextField whichHexTfToTf()
	{
		if (tf == tfHexRed)
			return tfRed;
		if (tf == tfHexGreen)
			return tfGreen;

		return tfBlue;
	}

	private JSlider whichJSliderFromTf()
	{
		if (tf == tfRed || tf == tfHexRed)
			return sliderRed;
		if (tf == tfGreen || tf == tfHexGreen)
			return sliderGreen;

		return sliderBlue;
	}

	private class caretListenerClass implements CaretListener
	{

		@Override
		public void caretUpdate(CaretEvent e)
		{
			if (e.getSource() instanceof JTextField)
			{
				tf = ((JTextField) e.getSource());
				if (e.getDot() < 2 || e.getDot() > tf.getText().length() - 1)
					tf.setCaretPosition(2);
			}
		}

	}

	private void initFrame()
	{
		// In der Mitte des Desktops positionieren
		this.setLocationRelativeTo(null);

		scrollSlider();
		
	}

	private void scrollSlider()
	{

		// Jeder einzelne Schieberegler bekommt seine aktuellen spezifischen
		// Farbanteil als Hintergrundfarbe zugewiesen.

		sliderRed.setBackground(new Color(sliderRed.getValue(), 0, 0));
		sliderGreen.setBackground(new Color(0, sliderGreen.getValue(), 0));
		sliderBlue.setBackground(new Color(0, 0, sliderBlue.getValue()));
		
		
		tfHexRed.setText("X'" + Integer.toHexString(sliderRed.getValue()).toUpperCase() + "'");
		tfRed.setText(String.valueOf(sliderRed.getValue()));

		tfHexGreen.setText(String.format("X'%02X'", sliderGreen.getValue()));
		tfGreen.setText(String.valueOf(sliderGreen.getValue()));

		tfHexBlue.setText(String.format("X'%02X'", sliderBlue.getValue()));
		tfBlue.setText(String.valueOf(sliderBlue.getValue()));
		this.getContentPane().setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
		
		
	}

	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
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
			scrollSlider();
		}

	}

}
