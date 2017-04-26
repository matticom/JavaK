package W6T4_Dozent;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class PLZForm extends JDialog implements WindowListener, ActionListener
{

	private JLabel	     jlabel1, jlabel2;
	private JTextField	 tfPLZ, tfOrt;
	private JButton	  	 btnOK, btnAbbrechen;
	
	private long mKey =  -1;
	private Component owner;
	
	public PLZForm(long Key)
	{
		initializeComponents();
		mKey = Key;
	}
	
	private void initializeComponents()
	{
		this.setTitle("Eintrag");
		this.setSize(480, 160);
		
		// Das Schließen des Dialogs wird vom WindowListener überwacht
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		this.setResizable(false);
		this.setLayout(null);
		
		jlabel1 = new JLabel("Postleitzahl");
		jlabel1.setBounds(10, 10, 100, 25);
		this.add(jlabel1);
		
		tfPLZ = new JTextField();
		tfPLZ.setBounds(10, 35, 80, 25);
		this.add(tfPLZ);
		
		jlabel2 = new JLabel("Ort");
		jlabel2.setBounds(100, 10, 100, 25);
		this.add(jlabel2);
		
		tfOrt = new JTextField();
		tfOrt.setBounds(100, 35, 350, 25);
		this.add(tfOrt);
		
		btnOK = new JButton("OK");
		btnOK.setBounds(100, 80, 120, 25);
		btnOK.addActionListener(this);
		this.add(btnOK);

		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(240, 80, 120, 25);
		btnAbbrechen.addActionListener(this);
		this.add(btnAbbrechen);
		
	}
	
	
	private void initDialog()
	{
		
		this.setModal(true);
		this.setLocationRelativeTo(owner);
		
		tfPLZ.setText(Long.toString(mKey));
		

		
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
	
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent arg0)
	{
		this.dispose();
		
	}


	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
