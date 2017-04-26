package W3T4_Dozent.Threads;

// Wenn eine Klasse bereits von einer anderen Basisklasse abgeleitet wurde, aber
// trotzdem als Thread ausgeführt werden soll, kann stattdessen die Schnittstelle
// 'Runnable' implementiert werden.

public class T1 extends Thread
{

	@Override
	public void run()
	{
		
		try
		{
			for (int i = 1; i <= 20; i++)
			{
				System.out.println(i);
				Thread.sleep(200);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	
	
	
}
