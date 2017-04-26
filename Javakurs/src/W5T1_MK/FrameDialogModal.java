package W5T1_MK;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FrameDialogModal extends JDialog implements ActionListener
{

	private JButton btnBeenden;
	
	private Component owner;
	
	
	public FrameDialogModal()
	{
		initializeComponents();
		
	}
	
	private void initializeComponents()
	{
		
		this.setTitle("Dialog");
		this.setSize(300, 160);
		
		this.setLayout(null);
		
		// Kein Grössenänderung des Dialogs
		this.setResizable(false);
				
		
		btnBeenden = new JButton("Beenden");
		btnBeenden.setBounds(90, 80, 120, 25);
		btnBeenden.addActionListener(this);
		this.add(btnBeenden);
		
	}


	private void initDialog()
	{
		
		this.setModal(true);
		this.setLocationRelativeTo(owner);
		
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
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource() == btnBeenden)
			// Schließt den Dialog und beauftragt den Garbage Collector
			// diese Objekt vom Heap zu entfernen.
			this.dispose();
	}
	
	
	
	
}
