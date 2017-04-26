package W5T5_Dozent.RMI;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerTime implements Serializable
{
	private Date dt;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	
	public ServerTime(Date dt)
	{
		
		this.dt = dt;
		
	}


	@Override
	public String toString()
	{
		return sdf.format(dt);
	}
	
}
