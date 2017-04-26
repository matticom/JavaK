package W4T1_MK;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class CheckBoxRadioButton extends JFrame
{

	private JPanel backGroundPanel, foreGroundPanel;
	private JLabel backGroundLabel, foreGroundLabel;
	
	private JButton btnBeenden;
	private ButtonGroup hgButtonGroup, vgButtonGroup;
	private JRadioButton rbHGGruen, rbHGBlau, rbHGRot, rbHGGelb, rbHGGrau;
	private JRadioButton rbVGBlau, rbVGRot, rbVGGelb, rbVGRosa, rbVGWeiss;

	private ItemListener hgRadioButtonListener, vgRadioButtonListener;
	
	private JRadioButton currentBgRB, currentFgRB;
	private Color backColor, foreColor;
	private static float[] HSBArray = new float[3];
	

	public CheckBoxRadioButton()
	{
		initializeComponents();
	}

	private void initializeComponents()
	{
		System.out.println();
		this.setTitle("CheckBox und RadioButton");
		// this.setBounds(10, 10, 480, 280);

		this.setSize(480, 300);

		// Kein Grössenänderung des rames
		// this.setResizable(false);

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

		hgButtonGroup = new ButtonGroup();

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
		
		
		// Beenden Button
		btnBeenden = new JButton("Beenden");
		btnBeenden.setBounds(170, 210, 140, 30);
		btnBeenden.addActionListener(new ButtonBeendenActionListener());
		this.add(btnBeenden);

		
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

		vgButtonGroup = new ButtonGroup();
		
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

		rbHGGrau.doClick();
		rbVGWeiss.doClick();
			
	}

	
	private void setBackColor()
	{
		
		backColor = Color.LIGHT_GRAY;
		
		if (rbHGGruen.isSelected())
		{
			backColor = Color.GREEN;
			currentBgRB = rbHGGruen;
		}
		else if (rbHGBlau.isSelected())
		{
			backColor = Color.BLUE;
			currentBgRB = rbHGBlau;
		}
		else if (rbHGRot.isSelected())
		{
			backColor = Color.RED;
			currentBgRB = rbHGRot;
		}
		else if (rbHGGelb.isSelected())
		{
			backColor = Color.YELLOW;
			currentBgRB = rbHGGelb;
		}
		else if (rbHGGrau.isSelected())
		{
			backColor = Color.LIGHT_GRAY;
			currentBgRB = rbHGGrau;
		}
		this.getContentPane().setBackground(backColor);
		deactivateBgColors();
			
	}
	
	
	private void setForeColor()
	{
		
		foreColor = Color.BLACK;
		
		Color.RGBtoHSB(240,128,128,HSBArray);
		Color ROSA = Color.getHSBColor(HSBArray[0], HSBArray[1], HSBArray[2]);
		
		if (rbVGBlau.isSelected())
		{
			foreColor = Color.BLUE;
			currentFgRB = rbVGBlau;
		}
		else if (rbVGRot.isSelected())
		{
			foreColor = Color.RED;
			currentFgRB = rbVGRot;
		}
		else if (rbVGGelb.isSelected())
		{
			foreColor = Color.YELLOW;
			currentFgRB = rbVGGelb;
		}
		else if (rbVGRosa.isSelected())
		{
			foreColor = ROSA;
			currentFgRB = rbVGRosa;
		}
		else if (rbVGWeiss.isSelected())
		{
			foreColor = Color.WHITE;
			currentFgRB = rbVGWeiss;
		}
		setForeColorPanelBorder(foreColor);
		setForeColorLabelText(foreColor);
		setForeColorRadioText(foreColor);
		deactivateFgColors();
	}
	
	private void setForeColorPanelBorder(Color foreColor)
	{
		foreGroundPanel.setBorder(BorderFactory.createLineBorder(foreColor));
		backGroundPanel.setBorder(BorderFactory.createLineBorder(foreColor));
	}
	
	private void setForeColorLabelText(Color foreColor)
	{
		foreGroundLabel.setForeground(foreColor);
		backGroundLabel.setForeground(foreColor);
	}
	
	private void setForeColorRadioText(Color foreColor)
	{
		rbHGGruen.setForeground(foreColor);
		rbHGBlau.setForeground(foreColor);
		rbHGRot.setForeground(foreColor);
		rbHGGelb.setForeground(foreColor);
		rbHGGrau.setForeground(foreColor);
		rbVGBlau.setForeground(foreColor);
		rbVGRot.setForeground(foreColor);
		rbVGGelb.setForeground(foreColor); 
		rbVGRosa.setForeground(foreColor);
		rbVGWeiss.setForeground(foreColor);
	}

	private void deactivateBgColors()
	{
		if (backColor.equals(Color.GREEN))
		{
			rbVGBlau.setEnabled(true);
			rbVGRot.setEnabled(true);
			rbVGGelb.setEnabled(true);
			rbVGRosa.setEnabled(true);
			rbVGWeiss.setEnabled(true);
		}
		
		if (backColor.equals(Color.BLUE))
		{
			rbVGBlau.setEnabled(false);
			rbVGRot.setEnabled(true);
			rbVGGelb.setEnabled(true);
			rbVGRosa.setEnabled(true);
			rbVGWeiss.setEnabled(true);
		}
		
		if (backColor.equals(Color.RED))
		{
			rbVGBlau.setEnabled(true);
			rbVGRot.setEnabled(false);
			rbVGGelb.setEnabled(true);
			rbVGRosa.setEnabled(true);
			rbVGWeiss.setEnabled(true);
		}
		
		if (backColor.equals(Color.YELLOW))
		{
			rbVGBlau.setEnabled(true);
			rbVGRot.setEnabled(true);
			rbVGGelb.setEnabled(false);
			rbVGRosa.setEnabled(true);
			rbVGWeiss.setEnabled(false);
		}
		
		if (backColor.equals(Color.LIGHT_GRAY))
		{
			rbVGBlau.setEnabled(true);
			rbVGRot.setEnabled(true);
			rbVGGelb.setEnabled(true);
			rbVGRosa.setEnabled(true);
			rbVGWeiss.setEnabled(true);
		}
	}
	
	private void deactivateFgColors()
	{
		Color.RGBtoHSB(240,128,128,HSBArray);
		Color ROSA = Color.getHSBColor(HSBArray[0], HSBArray[1], HSBArray[2]);
		
		if (foreColor.equals(Color.BLUE))
		{
			rbHGGruen.setEnabled(true);
			rbHGBlau.setEnabled(false);
			rbHGRot.setEnabled(true);
			rbHGGelb.setEnabled(true);
			rbHGGrau.setEnabled(true);
		}
		
		if (foreColor.equals(Color.RED))
		{
			rbHGGruen.setEnabled(true);
			rbHGBlau.setEnabled(true);
			rbHGRot.setEnabled(false);
			rbHGGelb.setEnabled(true);
			rbHGGrau.setEnabled(true);
		}
		
		if (foreColor.equals(Color.YELLOW))
		{
			rbHGGruen.setEnabled(true);
			rbHGBlau.setEnabled(true);
			rbHGRot.setEnabled(true);
			rbHGGelb.setEnabled(false);
			rbHGGrau.setEnabled(true);
		}
		
		if (foreColor.equals(ROSA))
		{
			rbHGGruen.setEnabled(true);
			rbHGBlau.setEnabled(true);
			rbHGRot.setEnabled(true);
			rbHGGelb.setEnabled(true);
			rbHGGrau.setEnabled(true);
		}
		
		if (foreColor.equals(Color.WHITE))
		{
			rbHGGruen.setEnabled(true);
			rbHGBlau.setEnabled(true);
			rbHGRot.setEnabled(true);
			rbHGGelb.setEnabled(false);
			rbHGGrau.setEnabled(true);
		}
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
				if (isColorCombinationReadable())
					setBackColor();
				else
					setHGRadioButtonBack();
		}

	}

	private class VGRadioButtonItemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{	
			if (e.getStateChange() == ItemEvent.SELECTED)
				if (isColorCombinationReadable())
					setForeColor();
				else
					setVGRadioButtonBack();
		}

	}
	
	private boolean isColorCombinationReadable()
	{
		Color BgColor = whatBgColorSelected();
		Color FgColor = whatFgColorSelected();
		Color Blue = Color.BLUE;
		Color Red = Color.RED;
		Color Yellow = Color.YELLOW;
		Color White = Color.WHITE;
		
		
		if (BgColor == Blue && FgColor == Blue)
			return false;
		if (BgColor == Red && FgColor == Red)
			return false;
		if (BgColor == Yellow && FgColor == Yellow)
			return false;
		if (BgColor == Yellow && FgColor == White)
			return false;
		return true;
	}
	
	private Color whatBgColorSelected()
	{
		if (rbHGGruen.isSelected())
			return Color.GREEN;
		if (rbHGBlau.isSelected())
			return Color.BLUE;
		if (rbHGRot.isSelected())
			return Color.RED;
		if (rbHGGelb.isSelected())
			return Color.YELLOW;

		return Color.LIGHT_GRAY;
	}
	
	private Color whatFgColorSelected()
	{

		Color.RGBtoHSB(240,128,128,HSBArray);

		Color ROSA = Color.getHSBColor(HSBArray[0], HSBArray[1], HSBArray[2]);
		
		if (rbVGBlau.isSelected())
			return Color.BLUE;
		if (rbVGRot.isSelected())
			return Color.RED;
		if (rbVGGelb.isSelected())
			return Color.YELLOW;
		if (rbVGRosa.isSelected())
			return ROSA;
		return Color.WHITE;
	}
	
	private void setHGRadioButtonBack()
	{
		currentBgRB.doClick();
	}
	
	private void setVGRadioButtonBack()
	{
		currentFgRB.doClick();
	}
	
	
	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		CheckBoxRadioButton f = new CheckBoxRadioButton();
		f.showFrame();
	}

}
