package W5T5_Dozent.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Eine Schnittstelle die in einer RMI-Anwendung verwendet werden soll, muss folgende Bedingungen erfüllen:
// 1. Sie muss von der Schnittstelle 'Remote' abgeleitet werden.
// 2. Die Methoden der Schnittstelle müssen, zusätzlich zu den anwendungespezifischen Ausnahmebedingungen, auch 
//    die Ausnahmebedingung 'RemoteException' in der throw-Klausel einbinden.

public interface IRemoteMethods extends Remote
{

	void message(String sender, String message) throws RemoteException;
	void command(String sender, String command) throws RemoteException;
	void data(String sender, String data) throws RemoteException;
	boolean getVitalSigns() throws RemoteException;
	ServerTime getServerTime() throws RemoteException;
	
}
