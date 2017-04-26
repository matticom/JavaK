package W5T5_Dozent.RMI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*
 * RMI erm�glicht es Objekte auf einfache Weise im Netz zu verteilen und ihre Dienste anderen
 * Arbeitspl�tzen zur Verf�gung zu stellen.
 * 
 * 
 * Prinzipielle Arbeitsweise:
 * 1. 	In einem Remote-Interface werden eine oder mehrere Methoden definiert, die als aufrufbare
 * 		Dienste anderen Arbeitspl�tzen zur Verf�gung gestellt werden sollen.
 * 
 * 2.	Eine Serverklasse implementiert das Interface und erzeugt eine oder mehrere Instanzen, die als
 * 		Remote-Objekt bezeichnet werden.
 * 
 * 3.	Die Remote-Objekte werden bei einem Namens-Service registriert, der von den potenziellen 
 * 		Clients abgefragt werden kann. Mit der RMI-Registry ist ein einfacher Namens-Service
 * 		bereits Bestandteil des RMI-Pakets.
 * 
 * 4.	Clients ermitteln mit Hilfe der RMI-Registry Referenzen auf die ben�tigten Objekte und rufen die
 * 		gew�nschten Methoden auf. Die beim Aufruf �bergebenen Parameter werden an das Remote-
 * 		Objekt �bertragen und die passende Methode wird dort ausgef�hrt. Der R�ckgabewert wird an
 * 		den Client zur�ckgegeben.
 * 
 * 5.	Bei der Kommunikation zwischen Client und Server ist folgendes zu beachten:
 * 		a. 	Werden beim Aufruf der Methode primitive Datentypen �bergeben oder zur�ckgegeben
 * 			(int char, boolean, long...) werden sie als Wert �bergeben. In diesem Fall besteht �berhaupt
 * 			kein Unterschied zu lokalen Java-Programmen.
 * 		b. 	Lokale Objekte k�nnen nur dann als Parameter oder als R�ckgabewert verwendet werden,
 * 			wenn sie serialisierbar sind. Sie werden bei der �bertragung kopiert und somit ebenfalls
 * 			als Wert �bergeben. Die meisten System-Klassen des JDK sind serialisierbar und k�nnen
 * 			direkt verwendet werden. Eigene Klassen m�ssen das Interface 'Serializable'
 * 			implementieren.  
 */


// Entfernte Objekte erweitern die Klasse UnicastRemoteObject. 
// Sie selbst ist jedoch eine Unterklasse von java.rmi.server.RemoteServer, eine abstrakte Klasse, 
// die wiederum die abstrakte Klasse java.rmi.server.RemoteObject beerbt.

// RemoteObject ist nichts anderes als ein verteiltes Objekt, welches die Schnittstellen Remote und Serializable implementiert. 
// RemoteObject ersetzt die Klasse Object f�r verteilte Objekte. Ebenso implementiert RemoteObject die Methoden writeObject() 
// und readObject(), damit die verteilten Objekte serialisiert werden k�nnen.

public class RMIServer extends UnicastRemoteObject implements IRemoteMethods, ActionListener
{

	private int      	thisPort;
	private String 		thisAddress;
	private Registry    registry;
	private String 		registryName = "rmiServer";
	private boolean		isRunning;
	
	public RMIServer() throws RemoteException
	{
		
		// Port setzen 
		// Registry.REGISTRYPORT = Standardport 1099
		// Der Port muss f�r beide Seiten freigegeben sein!
		thisPort = 8081;
		
		// Ermittlung der IP Adresse des Rechners
		try
		{
			thisAddress = InetAddress.getLocalHost().toString();
		}
		catch (UnknownHostException ex)
		{
			throw new RemoteException(ex.getMessage());
		}
		
		
		try
		{
			
			registry = LocateRegistry.createRegistry(thisPort);
			registry.rebind(registryName, this);
			
			System.out.println("Server wartet ...");
			System.out.println("IP Adresse: " + thisAddress + ", Port: " + thisPort + ", Name: " + registryName);
			
		}
		catch (RemoteException ex)
		{
			throw ex;
		}
		
	}
	
	private void stopServer()
	{
		isRunning = false;
		System.out.println("   Der Server wurde angehalten...");
	}

	private void startServer()
	{
		isRunning = true;
		System.out.println("   Der Server ist bereit...");
	}
	
	public static void main(String[] args)
	{
		
		try
		{
			
			RMIServer server = new RMIServer();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
		

	}

	private class ShutDown implements Runnable
	{

		private int timeOut;
		
		public ShutDown()
		{
			
		}
		
		public ShutDown(int timeOut)
		{
			this.timeOut = timeOut;
		}
		
		
		@Override
		public void run()
		{
			
			try
			{
				if (timeOut > 0)
					TimeUnit.SECONDS.sleep(timeOut);
			}
			catch (InterruptedException e) 	{}
			
			System.out.println("   \n\nDer RMI Server wurde beendet.");
			System.exit(0);
			
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void message(String sender, String message) throws RemoteException
	{
		System.out.println("\nSender: " + sender);  
		System.out.println("-  Nachricht erhalten: " + message); 
	}




	@Override
	public void command(String sender, String command) throws RemoteException
	{
		System.out.println("\nSender: " + sender);  
		System.out.println("-  Befehl wird ausgef�hrt: " + command);
		
		if (command.equalsIgnoreCase("shutdown"))
		{
			
			System.out.println("   Der Server wird in 5 Sekunden beendet...");
			Thread shutDown = new Thread(new ShutDown(5));
			shutDown.start();
		}
		else if (command.equalsIgnoreCase("stop"))
		{
			stopServer();
		}
		else if (command.equalsIgnoreCase("run"))
		{
			startServer();
		}
		
	}

	@Override
	public void data(String sender, String data) throws RemoteException
	{
		System.out.println("\nSender: " + sender);  
		System.out.println("Daten erhalten: " + data);
		
	}

	@Override
	public boolean getVitalSigns() throws RemoteException
	{
		return isRunning;
	}


	@Override
	public ServerTime getServerTime() throws RemoteException
	{
		
		ServerTime time = new ServerTime(new Date());
		return time;
	}

}
