package W4T3_Dozent;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DrawText extends JFrame implements ActionListener
{

	private JButton btnDraw1, btnDraw2;
	
	
	private String buttonText = "";
	
	public DrawText()
	{
		initializeComponents();
		
	}
	
	private void initializeComponents()
	{
		
		this.setTitle("Textausgabe mit Drawing");
		this.setSize(300,  300);
		
		// Kein Layout 
		this.setLayout(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Der Font, den die Methode drawString() verwendet
		this.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		
		
		btnDraw1 = new JButton("Textausgabe (1)");
		btnDraw1.setBounds(50,  150,  200,  30);
		btnDraw1.addActionListener(this);
		this.add(btnDraw1);
		
		btnDraw2 = new JButton("Textausgabe (2)");
		btnDraw2.setBounds(50,  185,  200,  30);
		btnDraw2.addActionListener(this);
		this.add(btnDraw2);
		
		
		
		
		
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
	
	
	
	private void drawTextOnFrame(String Text)
	{
		
		Graphics g = this.getGraphics();	
		g.drawString(Text, 30, 50);
		
	}
	
	
	public static void main(String[] args)
	{
		
		DrawText f = new DrawText();
		f.showFrame();

	}

	
	
	
	
	@Override
	public void paint(Graphics g)
	{
		int textWidth;
		
		
		super.paint(g);
		
		FontMetrics fm = g.getFontMetrics(); 
		textWidth = fm.charsWidth(buttonText.toCharArray(), 0, buttonText.length());
				
		g.drawString(buttonText, (this.getWidth() - textWidth) / 2, this.getHeight() / 2);
		
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
//		if (e.getSource() instanceof JButton)
//			drawTextOnFrame(((JButton)e.getSource()).getText());
		
		if (e.getSource() instanceof JButton)
		{
			
			buttonText = ((JButton)e.getSource()).getText();
			
			// Erzwingt das Neuzeichnen des gesamten Frames 
			//this.repaint();
			
			// oder (ist schneller als repaint)
			this.update(this.getGraphics());
	
		}
	}

}
