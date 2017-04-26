package W3T5_Dozent.Threads;

public class SyncMonitor
{
	
	/*  Durch das Schlüsselwort 'synchronized' ist der Zugriff
		auf ein Objekt der Klasse 'SyncMonitor' wechselseitig aus-
		geschlossen. Es darf jeweils höchstens ein Thread in
		einer der mit synchronized markierten Methoden des Objekts aktiv sein.
	*/
	
	public synchronized void Ausgabe(String text)
	//public void Ausgabe(String text)
	{
		
		try
		{

			for (int i = 1; i <= 10; i++)
			{
				System.out.println(text + ": " + i);
				Thread.sleep(200);
			}

		}
		catch (InterruptedException e)
		{
		}
		
	}
	

}
