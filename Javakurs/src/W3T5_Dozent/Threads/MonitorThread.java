package W3T5_Dozent.Threads;

public class MonitorThread implements Runnable
{

	private SyncMonitor monitor;
	private String threadName;
	
	public MonitorThread(String threadName, SyncMonitor monitor)
	{
		this.threadName = threadName;
		this.monitor = monitor;
	}

	@Override
	public void run()
	{
		monitor.Ausgabe(threadName);
		
	}
	
	
	
}
