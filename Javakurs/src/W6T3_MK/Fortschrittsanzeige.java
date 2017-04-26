package W6T3_MK;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Fortschrittsanzeige extends JDialog implements WindowListener, ActionListener
{
	private JButton btnAbort;
	private JLabel currentOp;
	private JProgressBar progressBar;
	
	private Component owner;
	
	private int max;
	private String title, labelText;
	
	public boolean abort;

	

	public Fortschrittsanzeige(String title, int max)
	{
		this.title = title;
		this.max = max;
		initializeComponents();
	}

	private void initializeComponents()
	{
		this.setTitle(title);
		this.setSize(400, 230);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(this);
		this.setLayout(null);
		this.setResizable(false);
		this.addWindowListener(this);

		// Fortschrittsanzeige innerhalb der Statusleiste erzeugen
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL);
		progressBar.setBorderPainted(true);
		progressBar.setBounds(50, 70, 300, 60);

		// Farbe für den Fortschrittsbalken
		progressBar.setForeground(Color.GREEN);

		// Prozentsatz anzeigen
		progressBar.setStringPainted(true);
		progressBar.setMinimum(0);
		progressBar.setMaximum(max);
		
		// Initial unsichtbar
		progressBar.setVisible(true);
		

		
		this.add(progressBar);
		
		btnAbort = new JButton();
		btnAbort.setBounds(160, 150, 80, 20);
		btnAbort.setText("Abbrechen");
		this.add(btnAbort);
		btnAbort.addActionListener(this);
		
		currentOp = new JLabel();
		currentOp.setBounds(50, 20, 300, 30);
		currentOp.setText("Label");
		this.add(currentOp);

	}

	public void setLabelText(String labelText)
	{
		currentOp.setText(labelText);
	}
	
	public void setProgressValue(int readCounter)
	{
		progressBar.setValue(readCounter);
	}
	
	public int getProgressValue()
	{
		return progressBar.getValue();
	}
	
	private void initDialog()
	{
		this.setModal(true);
		this.setLocationRelativeTo(owner);
		abort = false;
		
	}
	
	public void showDialog()
	{
		initDialog();
		this.setVisible(true);
		
	}
	
	
	public void showDialog(Component owner)
	{
		this.owner = owner;
		showDialog();
	}
	
	public boolean isAbort()
	{
		return abort;
	}
	
	public void setAbort()
	{
		abort = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnAbort)
			abort = true;
		

	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		abort = true;

	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

}
