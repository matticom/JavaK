package W5T5_Dozent.RMI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.*;


public class RMIClient extends JFrame implements ActionListener
{

	private JPanel ServerPanel, RemotePanel;
	private JRadioButton rbMessage, rbData, rbCommand;
	private JLabel lblServer, lblPort, lblRegServer, lblEingabe;
	private JTextField tfServer, tfRegServer, tfPort, tfEingabe;
	private JButton btnVerbinden, btnServerZeit, btnSenden, btnBeenden;
	
	private String server;
	private int port;
	
	private IRemoteMethods rmiMethods;
	private Registry registry;
	private String   thisAddress;
	
	public RMIClient()
	{
		initializeComponents();	
	}
	
	public RMIClient(String server, int port)
	{
		this();
		this.server = server;
		this.port = port;
	}
	
	private void initializeComponents()
	{
		this.setTitle("RMI Client");
		this.setLayout(null);
		this.setBounds(10, 10, 460, 400);
		
		// Keine Grössenänderung des Frames
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ServerPanel = new JPanel(null);
		ServerPanel.setBounds(20, 10, 410, 140);
		ServerPanel.setBorder(BorderFactory.createTitledBorder(" Server "));
		
		tfServer = new JTextField();
		tfServer.setBounds(10, 30, 200, 25);
		ServerPanel.add(tfServer);
		
		lblPort = new JLabel("Port:");
		lblPort.setBounds(240, 30, 50, 25);
		ServerPanel.add(lblPort);
		
		tfPort = new JTextField("8081");
		tfPort.setBounds(280, 30, 50, 25);
		ServerPanel.add(tfPort);
		
		lblRegServer = new JLabel("Registrierter Servername:");
		lblRegServer.setBounds(10, 60, 150, 25);
		ServerPanel.add(lblRegServer);
					
		tfRegServer = new JTextField("rmiServer");
		tfRegServer.setBounds(240, 60, 140, 25);
		ServerPanel.add(tfRegServer);
		
		btnVerbinden = new JButton("Verbinden");
		btnVerbinden.setBounds(160, 100, 100, 25);
		btnVerbinden.addActionListener(this);
		ServerPanel.add(btnVerbinden);
		
		this.add(ServerPanel);
		
		RemotePanel = new JPanel(null);
		RemotePanel.setBounds(20, 160, 410, 200);
		RemotePanel.setBorder(BorderFactory.createTitledBorder(" Methoden "));
		
		ButtonGroup MethodenButtonGroup = new ButtonGroup();
		rbMessage  = new JRadioButton("Nachricht");
		rbMessage.setBounds(10, 30, 100, 25);
		MethodenButtonGroup.add(rbMessage);
		RemotePanel.add(rbMessage);
		
		rbCommand = new JRadioButton("Befehl");
		rbCommand.setBounds(120, 30, 100, 25);
		MethodenButtonGroup.add(rbCommand);
		RemotePanel.add(rbCommand);
		
		rbData = new JRadioButton("Daten");
		rbData.setBounds(230, 30, 100, 25);
		MethodenButtonGroup.add(rbData);
		RemotePanel.add(rbData);
		
		btnServerZeit = new JButton("Serverzeit");
		btnServerZeit.setBounds(220, 70, 100, 25);
		btnServerZeit.addActionListener(this);
		RemotePanel.add(btnServerZeit);
		
		lblEingabe = new JLabel("Eingabe: ");
		lblEingabe.setBounds(10, 80, 100, 25);
		RemotePanel.add(lblEingabe);
		
		tfEingabe = new JTextField();
		tfEingabe.setBounds(10, 110, 390, 25);
		RemotePanel.add(tfEingabe);
		
		btnSenden = new JButton("Senden");
		btnSenden.setBounds(80, 150, 100, 25);
		btnSenden.addActionListener(this);
		RemotePanel.add(btnSenden);
		
		btnBeenden = new JButton("Beenden");
		btnBeenden.setBounds(220, 150, 100, 25);
		btnBeenden.addActionListener(this);
		RemotePanel.add(btnBeenden);
		
		this.add(RemotePanel);
		
	}
	
	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
	}
	
	private void initFrame()
	{
		// Ermittlung der IP Adresse des Rechners
		try
		{
			this.thisAddress = InetAddress.getLocalHost().toString();
		}
		catch (java.net.UnknownHostException e)
		{
			this.thisAddress = "(Unbekannt)";
		}

		rbMessage.setSelected(true);
		enableRemoteMethods(false);
		
		
		// Permanente Überprüfung ob der Server noch verbunden ist 
		 Thread t = new Thread(new ServerVitalSign());
	    t.start();
	     
	}
	
	private void enableRemoteMethods(boolean enable)
	{
		tfEingabe.setEditable(enable);
		btnServerZeit.setEnabled(enable);
		btnSenden.setEnabled(enable);
		tfEingabe.setEditable(enable);
		btnVerbinden.setEnabled(!enable);
	}
	
	private void connectToServer()
	{
		
		try
		{
			// Registry (Namensdienst starten)
			registry = LocateRegistry.getRegistry(tfServer.getText(), new Integer(tfPort.getText()));
			// Nach dem eingetragenen Remote Objekt suchen
			rmiMethods = (IRemoteMethods)registry.lookup(tfRegServer.getText());
			enableRemoteMethods(true);
			
			rbCommand.setSelected(true);
			tfEingabe.setText("run");
			send();
			
			rbMessage.setSelected(true);
		}
		catch (RemoteException ex)
		{
			JOptionPane.showMessageDialog(this, ex.getMessage(), "RemoteException", JOptionPane.ERROR_MESSAGE);
		}
		catch (NotBoundException ex)
		{
			JOptionPane.showMessageDialog(this, ex.getMessage(), "NotBoundException", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void send()
	{

		try
		{
			if (rbMessage.isSelected())
				rmiMethods.message(thisAddress, tfEingabe.getText());
			else if (rbCommand.isSelected())
				rmiMethods.command(thisAddress, tfEingabe.getText());
			else if (rbData.isSelected())
				rmiMethods.data(thisAddress, tfEingabe.getText());

			tfEingabe.setText(null);
			tfEingabe.requestFocus();

		}
		catch (RemoteException ex)
		{
			JOptionPane.showMessageDialog(this, ex.getMessage(), "RemoteException", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private class ServerVitalSign implements Runnable
	{
		
		@Override
		public void run()
		{
			while (true)
			{
				try
				{
					enableRemoteMethods( rmiMethods.getVitalSigns());
					Thread.sleep(500);
				} 
				catch (InterruptedException iex)
				{
					enableRemoteMethods(false);
				} 
				catch (RemoteException rex)
				{
					enableRemoteMethods(false);
				} 
				catch (Exception ex)
				{
					enableRemoteMethods(false);
				}
			}
		}
	}
	
	private ServerTime getServerTime()
	{	
		ServerTime svrDateTime = null;
		
		try
      {
			svrDateTime = rmiMethods.getServerTime();
      } 
		catch (RemoteException e)    { }
		
		return svrDateTime;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RMIClient f = new RMIClient();
		f.showFrame();

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnBeenden)
			System.exit(0);
		else if (e.getSource() == btnVerbinden)
			connectToServer();
		else if (e.getSource() == btnSenden)
			send();
		else if (e.getSource() == btnServerZeit)
			tfEingabe.setText(getServerTime().toString());
	}

}
