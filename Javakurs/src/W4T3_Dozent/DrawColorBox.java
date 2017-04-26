package W4T3_Dozent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;

public class DrawColorBox extends JFrame implements MouseListener
{

    private final int leftOffset = 5;
    private final int topOffset = 5;
    private final int boxHeight = 20;
    private final int boxWidth = 20;
    private final int boxDistance = 5;    
    
    private Random zufall;
    
    int titleHeight;
	int borderWidth;
    
    public DrawColorBox()
    {
        initializeComponents();
    }
    
    private void initializeComponents()
    {
        
        this.setTitle("ColorBox");
        this.setSize(300,  300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        this.addMouseListener(this);
        
    }
    
    public void showFrame()
    {
        initFrame();
        this.setVisible(true);
        
    }


    private void initFrame()
    {
    	this.setLocationRelativeTo(null);
    	
        // Zufallszahlengenerator initialisieren
        zufall = new Random();
        
      
        
    }
    
    
    public static void main(String[] args)
    {

        DrawColorBox f = new DrawColorBox();
        f.showFrame();

    }

    // Überschreiben der Methode paint() in der Klasse 'Window' 
    @Override
    public void paint(Graphics g)
    {
    	    	
        super.paint(g);
        
        // Die Höhe der Titelzeile ermitteln.
        titleHeight = this.getInsets().top;
        // Die Breite des Rahmens ermitteln.
        borderWidth = this.getInsets().left;
        
        // Zeichnen der farbigen Rechtecke
        // Anzahl Zeilen
        for (int y = topOffset + titleHeight; y < this.getHeight() - boxHeight; y += (boxHeight + boxDistance))
        {
      	  // Anzahl Boxen pro Zeile
            for (int x = leftOffset + borderWidth; x < this.getWidth() - boxWidth - borderWidth; x += (boxWidth + boxDistance))
            {
                // Ermitteln der Farbe mit Hilfe des Zufallszahlengenerators
                g.setColor(Color.BLACK);
                // Ein ausgefülltes Rechteck mit dieser farbe zeichnen
                g.drawRect(x,  y,  boxWidth, boxHeight);
                // Farbe für das Zeichnen des Rahmens auf Schwarz setzen
                
                
            }
        }
        
    }

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getClickCount() == 2)
			this.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
    
    
    
}
