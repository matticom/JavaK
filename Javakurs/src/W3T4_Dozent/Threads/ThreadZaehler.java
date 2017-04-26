package W3T4_Dozent.Threads;

public class ThreadZaehler implements Runnable
{

	int timeOut;

	public ThreadZaehler()
	{
	}

	public ThreadZaehler(int timeOut)
	{
		this.timeOut = timeOut;
	}

	@Override
	public void run()
	{

		try
		{

			for (int i = 1; i <= 20; i++)
			{
				System.out.println(i);
				if (timeOut > 0)
					Thread.sleep(timeOut * 1000);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		System.out.println("Ende von " + this.getClass());

	}

}
