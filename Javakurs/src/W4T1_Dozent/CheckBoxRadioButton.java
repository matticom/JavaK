package W4T1_Dozent;

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
	private JRadioButton rbHGGruen, rbHGBlau, rbHGRot, rbHGGelb, rbHGGrau;
	private JRadioButton rbVGBlau, rbVGRot, rbVGGelb, rbVGRosa, rbVGWeiss;

	private ItemListener hgRadioButtonListener, vgRadioButtonListener;

	public CheckBoxRadioButton()
	{
		initializeComponents();
	}

	private void initializeComponents()
	{

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
		
		
		
	}

	
	private void setBackColor()
	{
		
		Color backColor = Color.LIGHT_GRAY;
		
		if (rbHGGruen.isSelected())
			backColor = Color.GREEN;
		else if (rbHGBlau.isSelected())
			backColor = Color.BLUE;
		else if (rbHGRot.isSelected())
			backColor = Color.RED;
		else if (rbHGGelb.isSelected())
			backColor = Color.YELLOW;
		else if (rbHGGrau.isSelected())
			backColor = Color.LIGHT_GRAY;
		
		this.getContentPane().setBackground(backColor);
		
	}
	
	
	private void setForeColor()
	{
		
		foreGroundLabel.setForeground(Color.RED);
		
		
		
		
		
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
	
	public static void main(String[] args)
	{

		CheckBoxRadioButton f = new CheckBoxRadioButton();
		f.showFrame();

	}

}
