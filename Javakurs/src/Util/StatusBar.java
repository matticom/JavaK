package Util;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class StatusBar extends JMenuBar
{

	private JLabel statusText;
	
	
	public StatusBar()
	{
		
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		
		statusText = new JLabel();
		
		// Damit der Text innerhalb des Labels etwas nach rechts gerückt wird
		// einen leeren Rahmen mit einer Breite von 5 Pixeln auf der linken und rechten Seite definieren.
		// Die Methode setInsets(), mit der man die Distanz zwischen Umrandung und 
		// Text bestimmen könnte, gibt es für den Label nicht.
		statusText.setBorder(new EmptyBorder(0, 5, 0, 5));
		
		
		this.add(statusText, BorderLayout.CENTER);
		
	}
	
	public void setText(String text)
	{
		statusText.setText(text);
	}
	
	
	public String getText()
	{
		return statusText.getText();
	}
	
	public void setHorizontalAlignment(int alignment)
	{
		statusText.setHorizontalAlignment(alignment);
	}
		
	public void setStatusLabelFont(Font font)
	{
		statusText.setFont(font);
	}
	
	public Font getStatusLabelFont()
	{
		return statusText.getFont();
	}
	
	public void setIcon(Icon icon)
	{
		statusText.setIcon(icon);	
	}
}
