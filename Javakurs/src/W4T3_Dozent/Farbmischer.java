package W4T3_Dozent;

import java.awt.Color;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Farbmischer extends JFrame implements ChangeListener
{
	
	private static final int COLOR_MIN = 0;
	private static final int COLOR_MAX = 255;
	private static final int COLOR_INIT = 238;
	
	
	private JLabel labelRed, labelGreen, labelBlue;
	private JSlider sliderRed, sliderGreen, sliderBlue;
	
	private JTextField tfRed, tfHexRed;
	
	
	
	private Hashtable<Integer, JLabel> sliderLabelTabelle;
	
	
	public Farbmischer()
	{
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		
		this.setTitle("Farbmischer");
		this.setSize(480,  280);
		
		// Keine Grössenänderung des Frames
		this.setResizable(false);
		
		// Layout Manager ausschalten
		this.setLayout(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		labelRed = new JLabel("Rot");
		labelRed.setBounds(30,  10,  30,  25);
		this.add(labelRed);
		
				
		sliderRed = new JSlider(JSlider.HORIZONTAL, COLOR_MIN, COLOR_MAX, COLOR_INIT);
		sliderRed.setBounds(80,  10,  300,  50);
		
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
		tfHexRed.setBounds(400,  10,  40, 25);
		tfHexRed.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(tfHexRed);
		
		tfRed = new JTextField();
		tfRed.setBounds(400,  35,  40, 25);
		tfRed.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(tfRed);
		
		
		
		
		
		
		
	}

	
	private void initFrame()
	{
		// In der Mitte des Desktops positionieren
		this.setLocationRelativeTo(null);
		
		scrollSlider();
		
	}
	

	private void scrollSlider()
	{
		
		// Jeder einzelne Schieberegler bekommt seine  aktuellen spezifischen
		// Farbanteil als Hintergrundfarbe zugewiesen.
		
		sliderRed.setBackground(new Color(sliderRed.getValue(), 0, 0));
		
		
		
		tfHexRed.setText(String.format("X'%02X'", sliderRed.getValue()));
		tfRed.setText(String.valueOf(sliderRed.getValue()));
		
		
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
