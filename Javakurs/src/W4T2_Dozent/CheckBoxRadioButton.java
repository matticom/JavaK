package W4T2_Dozent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class CheckBoxRadioButton extends JFrame
{

	private JPanel backGroundPanel, foreGroundPanel, fontPanel;
	private JLabel backGroundLabel, foreGroundLabel, fontLabel, fontGroesseLabel;
	
	private JButton btnBeenden;
	private JRadioButton rbHGGruen, rbHGBlau, rbHGRot, rbHGGelb, rbHGGrau;
	private JRadioButton rbVGBlau, rbVGRot, rbVGGelb, rbVGRosa, rbVGWeiss;
	private JCheckBox 	 checkFett, checkKursiv;
	private JTextField 	 tfGroesse;
	
	private final int	MIN_FONTSIZE = 5;
	private final int   MAX_FONTSIZE = 20;
	
	
	private ItemListener hgRadioButtonListener, vgRadioButtonListener, checkBoxListener;

	public CheckBoxRadioButton()
	{
		initializeComponents();
	}

	private void initializeComponents()
	{

		this.setTitle("CheckBox und RadioButton");
		// this.setBounds(10, 10, 480, 280);

		this.setSize(480, 300);

		// Kein Grössenänderung des Frames
		this.setResizable(false);

		// Layout Manager ausschalten
		this.setLayout(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// Opazität = Lichtundurchlässigkeit;
		// Opaque = Undurchsichtig, nicht transparent...
		// Standardwert = true (Lichtundurchlässig, nicht transparent).
		// 'false' bedeutet transparent, die Hintergrundfarbe des Steuerelements
		// auf dem sich die Komponente befindet scheint durch.
		
		// Panel für die Hintergrundfarbe
		backGroundPanel = new JPanel();
		backGroundPanel.setOpaque(false);
		
		
		// GridLayout: 6 Zeilen, 1 Spalte
		backGroundPanel.setLayout(new GridLayout(6, 1));
		backGroundPanel.setBounds(20,  20,  140, 160);
		backGroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(backGroundPanel);
		
		backGroundLabel = new JLabel("Hintergrund");
		backGroundLabel.setHorizontalAlignment(JLabel.CENTER);
		backGroundPanel.add(backGroundLabel);
		
		hgRadioButtonListener = new HGRadioButtonItemListener();

		// ButtonGroup um die RadioButtons für die Hintergrundfarbe
		// zu gruppieren.

		ButtonGroup hgButtonGroup = new ButtonGroup();

		rbHGGruen = new JRadioButton("Grün");
		rbHGGruen.setOpaque(false);
		hgButtonGroup.add(rbHGGruen);
		rbHGGruen.addItemListener(hgRadioButtonListener);
		backGroundPanel.add(rbHGGruen);

		rbHGBlau = new JRadioButton("Blau");
		rbHGBlau.setOpaque(false);
		hgButtonGroup.add(rbHGBlau);
		rbHGBlau.addItemListener(hgRadioButtonListener);
		backGroundPanel.add(rbHGBlau);

		rbHGRot = new JRadioButton("Rot");
		rbHGRot.setOpaque(false);
		hgButtonGroup.add(rbHGRot);
		rbHGRot.addItemListener(hgRadioButtonListener);
		backGroundPanel.add(rbHGRot);

		rbHGGelb = new JRadioButton("Gelb");
		rbHGGelb.setOpaque(false);
		hgButtonGroup.add(rbHGGelb);
		rbHGGelb.addItemListener(hgRadioButtonListener);
		backGroundPanel.add(rbHGGelb);
		
		rbHGGrau = new JRadioButton("Grau");
		rbHGGrau.setOpaque(false);
		hgButtonGroup.add(rbHGGrau);
		rbHGGrau.addItemListener(hgRadioButtonListener);
		backGroundPanel.add(rbHGGrau);

		
		// Panel für die Vordergrundfarbe
		foreGroundPanel = new JPanel();
		foreGroundPanel.setOpaque(false);
		
		
		// GridLayout: 6 Zeilen, 1 Spalte
		foreGroundPanel.setLayout(new GridLayout(6, 1));
		foreGroundPanel.setBounds(170,  20,  140, 160);
		foreGroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(foreGroundPanel);
		
		foreGroundLabel = new JLabel("Vordergrund");
		foreGroundLabel.setHorizontalAlignment(JLabel.CENTER);
		foreGroundPanel.add(foreGroundLabel);
		
		vgRadioButtonListener = new VGRadioButtonItemListener();
		
		// ButtonGroup um die RadioButtons für die Vordergrundfarbe
		// zu gruppieren.

		ButtonGroup vgButtonGroup = new ButtonGroup();
		
		rbVGBlau = new JRadioButton("Blau");
		rbVGBlau.setOpaque(false);
		vgButtonGroup.add(rbVGBlau);
		rbVGBlau.addItemListener(vgRadioButtonListener);
		foreGroundPanel.add(rbVGBlau);
		
		rbVGRot = new JRadioButton("Rot");
		rbVGRot.setOpaque(false);
		vgButtonGroup.add(rbVGRot);
		rbVGRot.addItemListener(vgRadioButtonListener);
		foreGroundPanel.add(rbVGRot);
		
		rbVGGelb = new JRadioButton("Gelb");
		rbVGGelb.setOpaque(false);
		vgButtonGroup.add(rbVGGelb);
		rbVGGelb.addItemListener(vgRadioButtonListener);
		foreGroundPanel.add(rbVGGelb);
		
		rbVGRosa = new JRadioButton("Rosa");
		rbVGRosa.setOpaque(false);
		vgButtonGroup.add(rbVGRosa);
		rbVGRosa.addItemListener(vgRadioButtonListener);
		foreGroundPanel.add(rbVGRosa);
		
		rbVGWeiss = new JRadioButton("Weiss");
		rbVGWeiss.setOpaque(false);
		vgButtonGroup.add(rbVGWeiss);
		rbVGWeiss.addItemListener(vgRadioButtonListener);
		foreGroundPanel.add(rbVGWeiss);
		
		// Panel für die Schriftart
		fontPanel = new JPanel();
		fontPanel.setOpaque(false);
		
		// Layout Manager ausschalten
		fontPanel.setLayout(null);
		
		fontPanel.setBounds(320, 20, 140, 160);
		fontPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(fontPanel);
		
		fontLabel = new JLabel("Schriftart");
		fontLabel.setHorizontalAlignment(JLabel.CENTER);
		fontLabel.setBounds(0, 2, 140, 25);
		fontPanel.add(fontLabel);
		
		// Die CheckBoxen verwenden ebenfalls einen ItemListener
		checkBoxListener = new CheckBoxItemListener();
				
		checkFett = new JCheckBox("Fett");
		checkFett.setBounds(10,  30,  100,  25);
		checkFett.setOpaque(false);
		checkFett.addItemListener(checkBoxListener);
		fontPanel.add(checkFett);
		
		checkKursiv = new JCheckBox("Kursiv");
		checkKursiv.setBounds(10,  55,  100,  25);
		checkKursiv.setOpaque(false);
		checkKursiv.addItemListener(checkBoxListener);
		fontPanel.add(checkKursiv);
		
		fontGroesseLabel = new JLabel("Grösse");
		fontGroesseLabel.setBounds(10,  80,  100,  25);
		fontPanel.add(fontGroesseLabel);
		
		tfGroesse = new JTextField();
		tfGroesse.setBounds(10, 100, 60, 25);
		tfGroesse.setHorizontalAlignment(JTextField.CENTER);
		tfGroesse.addKeyListener(new TextFieldKeyListener());
		tfGroesse.addFocusListener(new TextFieldFocusListener());
		fontPanel.add(tfGroesse);
		
		// Beenden Button
		btnBeenden = new JButton("Beenden");
		
		// Tooltip hinzufügen
		btnBeenden.setToolTipText("Programm beenden");
		
		// Tastaturkürzel
		btnBeenden.setMnemonic('e');
		
		btnBeenden.setBounds(170, 210, 140, 30);
		btnBeenden.addActionListener(new ButtonBeendenActionListener());
		this.add(btnBeenden);
		
	}

	private void initFrame()
	{

		// Anzeige Maximiert.
		// this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		// Das Festlegen der Position wird dem jeweiligen Betriebssystem überlassen.
		// this.setLocationByPlatform(true);

		// Ermittlung der Festergrösse/Auflösung des Desktops
		// Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);

		rbHGGrau.setSelected(true);
		rbVGWeiss.setSelected(true);
		
		// Die Schriftart aller Komponenten der Panels auf die Schriftart
		// der ContentPane setzen.
		setFont(this.getContentPane().getFont(), backGroundPanel);
		setFont(this.getContentPane().getFont(), foreGroundPanel);
		setFont(this.getContentPane().getFont(), fontPanel);
		
		//tfGroesse.setText(Integer.toString(this.getContentPane().getFont().getSize()));
		// oder
		tfGroesse.setText(String.valueOf(this.getContentPane().getFont().getSize()));
	}

	
	private void setBackColor()
	{
		
		Color backColor = Color.LIGHT_GRAY;
		
		// Alle Komponenten des Vordergrund Panels aktivieren
		for (int i = 0; i < foreGroundPanel.getComponentCount(); i++)
			foreGroundPanel.getComponent(i).setEnabled(true);
		
		if (rbHGGruen.isSelected())
			backColor = Color.GREEN;
		else if (rbHGBlau.isSelected())
		{
			backColor = Color.BLUE;
			rbVGBlau.setEnabled(false);
		}
		else if (rbHGRot.isSelected())
		{
			backColor = Color.RED;
			rbVGRot.setEnabled(false);
		}
		else if (rbHGGelb.isSelected())
		{
			backColor = Color.YELLOW;
			rbVGGelb.setEnabled(false);
			rbVGWeiss.setEnabled(false);
		}
		else if (rbHGGrau.isSelected())
			backColor = Color.LIGHT_GRAY;
		
		// Hintergrundfarbe des Frames setzen
		this.getContentPane().setBackground(backColor);
		
	}
	
	
	private void setForeColor()
	{
		
		Color foreColor = Color.BLACK;
		
		// Alle Komponenten des Hintergrund-Panels aktivieren
		for (int i = 0; i < backGroundPanel.getComponentCount(); i++)
			backGroundPanel.getComponent(i).setEnabled(true);
		
		
		if (rbVGBlau.isSelected())
		{
			foreColor = Color.BLUE;
			rbHGBlau.setEnabled(false);
		}
		else if (rbVGRot.isSelected())
		{
			foreColor = Color.RED;
			rbHGRot.setEnabled(false);
		}
		else if (rbVGGelb.isSelected())
			foreColor = Color.YELLOW;
		else if (rbVGRosa.isSelected())
			foreColor = Color.PINK;
		else if (rbVGWeiss.isSelected()) 
			foreColor = Color.WHITE;
		
		if (foreColor == Color.WHITE || foreColor == Color.YELLOW)
			rbHGGelb.setEnabled(false);
		
		
		// Vordergrundfarbe für alle Komponenten des Hintergrund-Panels setzen
		// inklusive des Panel-Rahmens.
		backGroundPanel.setBorder(BorderFactory.createLineBorder(foreColor));
		for (int i = 0; i < backGroundPanel.getComponentCount(); i++)
			backGroundPanel.getComponent(i).setForeground(foreColor);
		
		// Vordergrundfarbe für alle Komponenten des Vordergrund-Panels setzen
		// inklusive des Panel-Rahmens.
		foreGroundPanel.setBorder(BorderFactory.createLineBorder(foreColor));
		for (int i = 0; i < foreGroundPanel.getComponentCount(); i++)
			foreGroundPanel.getComponent(i).setForeground(foreColor);
		
		// Vordergrundfarbe für alle Komponenten des Font-Panels setzen
		// inklusive des Panel-Rahmens.
		fontPanel.setBorder(BorderFactory.createLineBorder(foreColor));
		
		Component c;
		for (int i = 0; i < fontPanel.getComponentCount(); i++)
		{
			c = fontPanel.getComponent(i);
			if (c instanceof JTextField)
				continue;
				
			c.setForeground(foreColor);
		}
		
	}
	
	
	private void setFont(Font font, JPanel panel)
	{
		
		for (int i = 0; i < panel.getComponentCount(); i++)
			panel.getComponent(i).setFont(font);
		
	}
	
	
	
	private void setFont()
	{
		
		Font font;
		
		// Definition der Standard-Schriftstile
		// 0000 0000 	PLAIN		0
		// 0000 0001    BOLD        1
		// 0000 0010    ITALIC      2
		// 0000 0011    BOLD/ITALIC 3
		
		String fontFamily = this.getContentPane().getFont().getFamily();
		int fontStyle = Font.PLAIN;
		int fontSize = this.getContentPane().getFont().getSize();
		int newSize = 0;
		
		
		
//		if (checkFett.isSelected())
//			fontStyle |= Font.BOLD;
//		
//		if (checkKursiv.isSelected())
//			fontStyle |= Font.ITALIC;
		
		// oder
		
		if (checkFett.isSelected())
			fontStyle += Font.BOLD;
		
		if (checkKursiv.isSelected())
			fontStyle += Font.ITALIC;
		
		
		try
		{
			newSize = Integer.parseInt(tfGroesse.getText());
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			tfGroesse.setText(Integer.toString(fontSize));
			newSize = fontSize;
			
		}
		
		if (newSize < MIN_FONTSIZE)
		{
			JOptionPane.showMessageDialog(this, "Die Mindestgröße der Schriftart beträgt " + MIN_FONTSIZE, "Fehler", JOptionPane.ERROR_MESSAGE);
			tfGroesse.setText(Integer.toString(fontSize));
		}
		else if (newSize > MAX_FONTSIZE)
		{
			JOptionPane.showMessageDialog(this, "Die maximale Größe der Schriftart beträgt " + MAX_FONTSIZE, "Fehler", JOptionPane.ERROR_MESSAGE);
			tfGroesse.setText(Integer.toString(fontSize));
		}
		else
			fontSize = newSize;
		
	
		font = new Font(fontFamily, fontStyle, fontSize);
		
		// Font der Contentpane als Basis für alle anderen
		// Steuerelemente setzen.
		this.getContentPane().setFont(font);
		
		
		// Font für alle Komponenten der Panels setzen
		setFont(this.getContentPane().getFont(), backGroundPanel);
		setFont(this.getContentPane().getFont(), foreGroundPanel);
		setFont(this.getContentPane().getFont(), fontPanel);
	
		
		
	}
	

	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
	}

	private class ButtonBeendenActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}

	}

	private class HGRadioButtonItemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{

			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				setBackColor();
			}

		}

	}

	private class VGRadioButtonItemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{

			if (e.getStateChange() == ItemEvent.SELECTED)
				setForeColor();
			
		}

	}
	
	private class CheckBoxItemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			
			setFont();
		}
		
	}
	
	private class TextFieldKeyListener implements KeyListener
	{

		// KeyPressed Ereignis: zum Abfangen von Kommando- oder Steuertasten.
		// Eingaben, die kein darstellbares Zeichen an das Steuerelement
		// übergeben.
		@Override
		public void keyPressed(KeyEvent e)
		{
			//System.out.println("Key pressed: " + e.getKeyChar());
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				//setFont();
				//btnBeenden.requestFocusInWindow();
				// oder
				KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
				
			}
			
			
		}

		// KeyReleased Ereignis: wenn eine beliebige Taste losgelassen wird.
		@Override
		public void keyReleased(KeyEvent e)
		{
			//System.out.println("Key released: " + e.getKeyChar());
		}

		// KeyTyped Ereignis: zum Abfangen von Zeichen für das Steuerelement.
		
		@Override
		public void keyTyped(KeyEvent e)
		{
			//System.out.println("Key typed: " + e.getKeyChar());
		}
		
	}
	
	private class TextFieldFocusListener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e)
		{
			
			if (e.getSource() instanceof JTextField)
				((JTextField)e.getSource()).selectAll();
			
			
			
		}

		@Override
		public void focusLost(FocusEvent e)
		{
			
			setFont();
			
		}

	}
	
	public static void main(String[] args)
	{

		CheckBoxRadioButton f = new CheckBoxRadioButton();
		f.showFrame();

	}

}
