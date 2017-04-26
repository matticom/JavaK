package W6T5_Dozent;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

import javax.swing.*;

import org.jdesktop.swingx.JXDatePicker;

public class PLZForm extends JDialog implements WindowListener, ActionListener, KeyListener, FocusListener
{

	private JLabel	     jlabel1, jlabel2;
	private JTextField	 tfPLZ, tfOrt;
	private JButton	  	 btnOK, btnAbbrechen;
	
	private long mKey =  -1;
	private Component owner;
	private boolean hasChanged;
	
	private KeyboardFocusManager kbFocusManager;
	
	
	// TIMESTAMP zur Überprüfung, ob der Datensatz von einem anderen Benutzer geändert wurde.
	private String timeStamp;
	
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
		tfPLZ.addKeyListener(this);
		tfPLZ.addFocusListener(this);
		this.add(tfPLZ);
		
		jlabel2 = new JLabel("Ort");
		jlabel2.setBounds(100, 10, 100, 25);
		this.add(jlabel2);
		
		tfOrt = new JTextField();
		tfOrt.setBounds(100, 35, 350, 25);
		tfOrt.addKeyListener(this);
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
		
		kbFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		
		if (mKey > 0)
			readEntry(mKey);
		
		
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
	
	
	private void readEntry(long key)
	{
		
		String SQL = "SELECT PLZ, ORT, TIMESTAMP FROM POSTLEITZAHLEN ";
		SQL += " WHERE PRIMARYKEY = " + Long.toString(key);
		
		ResultSet rSet = DBConnection.executeQuery(SQL);
		if (rSet == null)
			return;
		
		
		try
		{
			if (rSet.next())
			{
				
				tfPLZ.setText(rSet.getString("PLZ"));
				tfOrt.setText(rSet.getString("ORT"));
				
				// TIMESTAMP des Datensatzes sichern
				timeStamp = rSet.getString("TIMESTAMP");
			}
			else
			{
				JOptionPane.showMessageDialog(this,  "Der Datensatz konnte nicht gefunden werden", "Datensatz lesen", JOptionPane.ERROR_MESSAGE);
				mKey = -1;
			}
			
			rSet.close();
			
		}
		catch (Exception ex)
		{}
		
	}
	
	
	private boolean eingabeOK()
	{
		
		boolean retValue = false;
		
		if (tfPLZ.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(this, "Eingabe fehlt", "Fehler", JOptionPane.ERROR_MESSAGE);
			tfPLZ.requestFocusInWindow();
		}
		else if (tfPLZ.getText().length() != 5)
		{
			JOptionPane.showMessageDialog(this, "Eingabe ungültig", "Fehler", JOptionPane.ERROR_MESSAGE);
			tfPLZ.requestFocusInWindow();
		}
		else if (tfOrt.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(this, "Eingabe fehlt", "Fehler", JOptionPane.ERROR_MESSAGE);
			tfOrt.requestFocusInWindow();
		}
		else
			retValue = true;
		
		
		return retValue;
	}
	
	
	private boolean saveEntry()
	{
		
		boolean retValue = false;
		
		
		if (!eingabeOK())
			return false;
		
		
		if (Globals.istPLZOrtVorhanden(tfPLZ.getText(), tfOrt.getText()))
		{
			JOptionPane.showMessageDialog(this, "Ein Eintrag mit dieser Postleitzahl und diesem Ort ist bereits vorhanden", "Fehler", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (mKey > -1)
			retValue = updateEntry(mKey);
		else
			retValue = insertEntry();
		

		hasChanged = !retValue;
		
		return retValue;
		
	}
	
	public long getPrimaryKey()
	{
		return mKey;
	}
	
	
	private boolean updateEntry(long key)
	{
		// Vor dem Update prüfen, ob der TIMESTAMP des Datensatzes
		// in der Zwischenzeit durch einen anderen Benutzer geändert wurde. 
		
		String SQL = "SELECT TIMESTAMP FROM POSTLEITZAHLEN ";
		SQL += " WHERE PRIMARYKEY = " + Long.toString(key);
		
		Object obj = DBConnection.executeScalar(SQL);
		if (obj != null)
		{
			
			if (!timeStamp.equals(obj.toString()))
			{
				JOptionPane.showMessageDialog(this, "Dieser Eintrag wurde zwischenzeitlich von einem anderen Benutzer geändert.\nIhre Änderung wird verworfen." , "Fehler",					
		                JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
		}
		
		SQL = "UPDATE POSTLEITZAHLEN ";
		SQL += "SET PLZ = " + Globals.quote(tfPLZ.getText()) + ", ";
		SQL += "ORT = " + Globals.quote(tfOrt.getText());
		SQL += " WHERE PRIMARYKEY = " + Long.toString(key);
		
		return DBConnection.executeNonQuery(SQL) > 0;
		
	}
	
	private boolean insertEntry()
	{
		
		mKey = Globals.getNextKey();
		return Globals.insertPLZOrt(mKey, tfPLZ.getText(), tfOrt.getText());
	}
	

	private boolean queryExit()
	{
		
		// Benutzerdefinierten Button Text
		String[] options = { "Ja", "Nein", "Abbrechen" };
				
		if (!hasChanged)
			return true;
		
		int retValue = JOptionPane.showOptionDialog(this, "Daten wurden geändert.\nÄnderungen speichern", "Frage", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
		
		// Nein - nicht speichern, aber Dialog schließen
		if (retValue == JOptionPane.NO_OPTION)
			return true;
		
		// Abbruch - Dialog witrd nicht geschlossen
		if (retValue != JOptionPane.YES_OPTION)
			return false;
		
		
		// Ja - Speichern
		return saveEntry();
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource() == btnAbbrechen)
			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		else if (e.getSource() == btnOK)
		{
			if (hasChanged && saveEntry())
				windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			
		}
		
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
		
		// Überprüfen, ob die Änderungen noch gespeichert werden müssen.
		if (queryExit())
			this.dispose();
		else
			tfPLZ.requestFocusInWindow();

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
	public void focusGained(FocusEvent e)
	{
		if (e.getSource() == tfPLZ)
			tfPLZ.selectAll();
		
	}

	@Override
	public void focusLost(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			
			if (e.getSource() == tfPLZ && tfPLZ.getText().length() == 5)
				kbFocusManager.focusNextComponent();
			else if (e.getSource() == tfOrt && tfOrt.getText().length() > 0) 
				kbFocusManager.focusNextComponent();
			
				
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
		if (e.getSource() == tfPLZ)
		{
			
			if (Character.isISOControl(e.getKeyChar())) 
				return;
			
			if (!Character.isDigit(e.getKeyChar()))
			{
				Toolkit.getDefaultToolkit().beep();
				e.consume();
				return;
			}
			
			// Zuerst die markierten Zeichen löschen.
			((JTextField) e.getSource()).replaceSelection(null);
			
			if (((JTextField) e.getSource()).getText().length() >= 5)
			{
				Toolkit.getDefaultToolkit().beep();
				e.consume();
				return;
			}
			
			
			hasChanged = true;
			
		}
		
		hasChanged = true;
		
	}

}
