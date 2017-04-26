package W4T3_Dozent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.*;

public class DrawRectangle extends JFrame
{

	public DrawRectangle()
	{
		initializeComponents();
	}
	
	
	private void initializeComponents()
	{
		this.setTitle("Rechtecke zeichnen");
		this.setSize(500,  200);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	
	private void initFrame()
	{
		this.setLocationRelativeTo(null);
	}
	
	
	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
	}

	
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		// Einfaches Rechteck mit schwarzem Rahmen
		g.setColor(Color.BLACK);
		g.drawRect(50, 50, 50, 50);
		
		// Rot ausgefülltes Rechteck
		g.setColor(Color.RED);
		g.fillRect(110, 50, 50, 50);
		
		// Einfaches Rechteck mit blauem Rahmen und abgerundeteten Ecken
		g.setColor(Color.BLUE);
		g.drawRoundRect(170, 50, 50, 50, 5, 5);
		
		// Graues Rechteck mit 3d Effekt und orangefarbenem Rahmen
		g.setColor(Color.ORANGE);
		g.draw3DRect(290, 50, 50, 50, false);
		
		// Grün ausgefülltes Rechteck mit 3D Effekt
		g.setColor(Color.GREEN);
		g.fill3DRect(350, 50, 50, 50, true);
		
		
		// Graphics in Graphics2D umwandeln, um zusätzliche Grafik-Methoden zu erhalten
		Graphics2D g2 = (Graphics2D)g;
		
		// Strichstärke für alle nachfolgenden Draw-Methoden setzen
		Stroke stroke = new BasicStroke(4);
		g2.setStroke(stroke);
		
		
		g2.setColor(Color.MAGENTA);
		g2.drawLine(50, 120, 400, 120);
		
		
		// Kreis zeichnen
		g2.drawOval(230, 50, 50, 50);
		
		
	}


	public static void main(String[] args)
	{
		DrawRectangle f = new DrawRectangle();
		f.showFrame();
	}

}
