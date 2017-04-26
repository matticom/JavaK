package W3T4_Dozent.Threads;

import java.util.concurrent.TimeUnit;

public class ThreadJoin implements Runnable
{

	private String threadName;
	private int timeOut;
	
	
	public ThreadJoin(String threadName, int timeOut)
	{
		this.threadName = threadName;
		this.timeOut = timeOut;
	
	}
	
	
	
	@Override
	public void run()
	{
		try
		{

			for (int i = 1; i <= 20; i++)
			{
				System.out.println(threadName + " - " +  i);
				if (timeOut > 0)
					TimeUnit.SECONDS.sleep(timeOut);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		System.out.println("Ende von " + threadName);
		
	}

}
