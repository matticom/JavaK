package W6T4_MK;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class PLZForm extends JDialog implements WindowListener, ActionListener, KeyListener, FocusListener
{

	private JLabel	     jlabel1, jlabel2;
	private JTextField	 tfPLZ, tfOrt;
	private JButton	  	 btnOK, btnAbbrechen;
	
	private long mKey =  -1;
	private String PLZ;
	private String Ort;
	private Component owner;
	
	private boolean isInsert;
	
	public PLZForm(long Key)
	{
		initializeComponents();
		mKey = Key;
		btnOK.setText("Neu anlegen");
		isInsert = true;
	}
	
	public PLZForm(long Key, String PLZ, String Ort)
	{
		initializeComponents();
		this.PLZ = PLZ;
		this.Ort = Ort;
		mKey = Key;
		btnOK.setText("Update");
		isInsert = false;
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
		btnOK.setEnabled(false);

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
	
	private void insertData()
	{
		
		if (isValidDate())
		{
			if (!Globals.insertPLZOrt(mKey, PLZ, Ort))
				JOptionPane.showMessageDialog(owner, "insertData: ", "Fehler", JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	private void updateData()
	{
		if (isValidDate())
		{
			if (!Globals.insertPLZOrt(mKey, PLZ, Ort))
				JOptionPane.showMessageDialog(owner, "updateData: ", "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private int abortRequest()
	{
		String msg = "Wollen sie wirklich abbrechen?";
		// Benutzerdefinierte Button Texte
		String[] options =
		{ "OK", "Abbrechen" };

		return JOptionPane.showOptionDialog(this, msg, "Frage", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
	}
	
	private boolean isValidDate()
	{
		boolean retValue = false;
			
		PLZ = tfPLZ.getText();
		Ort = tfOrt.getText();
		
		return retValue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnOK)
		{
			if (isInsert)
				insertData();
			else
				updateData();
		}
		
		if (e.getSource() == btnAbbrechen)
			if (abortRequest()== JOptionPane.OK_OPTION)
				this.dispose();
		
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
		String msg = "Wollen sie wirklich abbrechen?";
		// Benutzerdefinierte Button Texte
		String[] options =
		{ "OK", "Abbrechen" };
				
		if (JOptionPane.showOptionDialog(this, msg, "Frage", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1])
				== JOptionPane.OK_OPTION)
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

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
