package W6T1_Blaszczyk;


import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ProgressDialog extends JDialog
{
	private JFrame owner;
	
	private JLabel lblInfo;
	private JProgressBar progressBar;
	private JLabel lblTimeLeft = new JLabel("geschätzte Restzeit: unbekannt");
	private JButton btnCancel = new JButton("Abbrechen");
	
	private int secsLeft = 0;
	private int maxValue;
	private boolean continueTimeThread = true;
	private boolean requestCancel = false;
	private long initTimeStamp = System.currentTimeMillis();

	private Thread timeThread = new Thread(()->{
		while(continueTimeThread)
		{
			if(secsLeft > 0)
				secsLeft--;
			SwingUtilities.invokeLater(() -> {
				lblTimeLeft.setText(String.format( "geschätzte Restzeit: %3d Sekunde%s",secsLeft, secsLeft == 1 ? "" : "n"));
			});
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				return;
			}
		}
	});
	
	public ProgressDialog(JFrame owner, int maxValue, String info, boolean allowCancel)
	{
		super(owner, "Fortschritt", true);
		this.owner = owner;
		this.maxValue = maxValue;
		
		setLayout(null);
		setSize(606, 170);
		setResizable(false);
		
		lblInfo = new JLabel(info);
		lblInfo.setBounds(10, 10, 580, 30);
		
		progressBar = new JProgressBar(0, maxValue);
		progressBar.setFont(new Font("Arial",Font.PLAIN,18));
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 50, 580, 40);
		progressBar.setBorderPainted(true);
		
		lblTimeLeft.setBounds(10, 100, 380, 30);
		
		btnCancel.setBounds(410, 100, 180, 30);
		btnCancel.setEnabled(allowCancel);
		btnCancel.addActionListener(e -> requestCancel());
		
		add(lblInfo);
		add(progressBar);
		add(lblTimeLeft);
		add(btnCancel);
	}

	
	public void setCurrentValue( int value)
	{
		secsLeft = (int)( (System.currentTimeMillis() - initTimeStamp) * (maxValue - value) / value )/1000;
		progressBar.setValue(value);
	}

	public void setInfo(String info)
	{
		lblInfo.setText(info);
	}
	
	public void setInfoAndValue(String info, int value)
	{
		setCurrentValue(value);
		setInfo(info);
	}

	public void showDialog()
	{
		setLocationRelativeTo(owner);
		timeThread.start();
		setVisible(true);	
	}
	
	public void disposeDialog()
	{
		continueTimeThread = false;
		dispose();
	}
	
	public boolean hasRequestCancel()
	{
		return requestCancel;
	}
	
	
	private void requestCancel()
	{
		if( JOptionPane.showConfirmDialog(this, "Wirklich Abbrechen?", "Abbrechen bestätigen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.CANCEL_OPTION )
			return;
		requestCancel = true;
		btnCancel.setEnabled(false);
		continueTimeThread = false;
	}
}
