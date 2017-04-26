package W3T2_MK;

import W3T1_MK.DatumExt;

public class DatumZeit extends DatumExt
{
	private int tempsek;
	private int tempmin;
	private int temph;
	private int temptag;
	private int tempmonat;
	private int tempjahr;
	
	private int sekunden;
	private int minuten;
	private int stunden;
	
	private static int Default_sek = 0;
	private static int Default_min = 0;
	private static int Default_h = 0;
		
	public DatumZeit()
	{
		// Standardkonstruktor wird automatisch aufgerufen --> this() kann weggelassen werden
		sekunden = Default_sek;
		minuten = Default_min;
		stunden = Default_h;		
	}
	
	public DatumZeit(int tag, int monat, int jahr, int sekunden, int minuten, int stunden)
	{
		if (checkTime(stunden, minuten, sekunden) && setDate(tag, monat, jahr))
		{
			this.sekunden = sekunden;
			this.minuten = minuten;
			this.stunden = stunden;
		}
		else 
		{
			sekunden = Default_sek;
			minuten = Default_min;
			stunden = Default_h;
			setDate(getDEFAULT_Tag(), getDEFAULT_Monat(), getDEFAULT_Jahr()); //Default_Tag war in super Klasse private und wurde nicht von setDate verändert --> per getter zugriff
		}
		
		
	}
	
	private static boolean checkTime(int stunden, int minuten, int sekunden)
	{
		return ((stunden >= 0) && (stunden <= 24) && (minuten >=0) && (minuten <= 59) && (sekunden >= 0) && (sekunden <= 59));
		
	}
	
	public boolean setTime(int stunden, int minuten, int sekunden)
	{
		if (checkTime(stunden, minuten, sekunden))
		{
			this.sekunden = sekunden;
			this.minuten = minuten;
			this.stunden = stunden;
			return true;
		}
		else 
			return false;
	}
	
	public boolean addiereStunden(int stunden)
	{
	
		int uebertragTage;
		int restStunden;
		
		data1safe2recover(1);
		
		if (stunden >= 0)
		{
			if (stunden <= (24-this.stunden))
				this.stunden += stunden;
			else
			{
				uebertragTage = (((stunden - (24-this.stunden)))/24)+1;
				restStunden = ((stunden - (24-this.stunden)))%24;
				addiereTage(uebertragTage);
				this.stunden = restStunden;
				return true;
			}
		}
		else 
		{
			if (Math.abs(stunden) <= this.stunden)
				this.stunden += stunden;
			else
			{
				uebertragTage = ((Math.abs(this.stunden + stunden))/24)+1;
				restStunden = (Math.abs(this.stunden + stunden))%24;
				
				if (restStunden == 0)
				{
					restStunden = 24;
					uebertragTage--;
				}
				
				if (!subtrahiereTage(uebertragTage))
				{
					data1safe2recover(2);
					return false;
				}
				this.stunden = (24 - restStunden);
			}
		}
		
		return true;
	}
	
	public boolean addiereMinuten(int minuten)
	{
		
		int uebertragStunden;
		int restMinuten;
		
		data1safe2recover(1);
		
		if (minuten >= 0)
		{
			if (minuten <= (60-this.minuten))
				this.minuten += minuten;
			else
			{
				uebertragStunden = (((minuten - (60-this.minuten)))/60)+1;
				restMinuten = ((minuten - (60-this.minuten)))%60;
				
				addiereStunden(uebertragStunden);
				this.minuten = restMinuten;
				return true;
			}
		}
		else 
		{
			if (Math.abs(minuten) <= this.minuten)
				this.minuten += minuten;
			else
			{
				uebertragStunden = ((this.minuten + minuten)/60)-1;
				restMinuten = ((this.minuten + minuten)%60);
				
				if (restMinuten == 0)
				{
					restMinuten = -60;
					uebertragStunden++;
				}
				
				if (!addiereStunden(uebertragStunden))
				{
					data1safe2recover(2);
					return false;
				}
				this.minuten = (60 + restMinuten);
			}
		}
		
		return true;
	}
	
	public boolean addiereSekunden(int sekunden)
	{
		
		int uebertragMinuten;
		int restSekunden;
		
		data1safe2recover(1);
		
		if (sekunden >= 0)
		{
			if (sekunden <= (60-this.sekunden))
				this.sekunden += sekunden;
			else
			{
				uebertragMinuten = (((sekunden - (60-this.sekunden)))/60)+1;
				restSekunden = ((sekunden - (60-this.sekunden)))%60;
				addiereMinuten(uebertragMinuten);
				this.sekunden = restSekunden;
				return true;
			}
		}
		else 
		{
			if (Math.abs(sekunden) <= this.sekunden)
				this.sekunden -= sekunden;
			else
			{
				uebertragMinuten = ((this.sekunden + sekunden)/60)-1;
				restSekunden = ((this.sekunden + sekunden)%60);
				
				if (restSekunden == 0)
				{
					restSekunden = -60;
					uebertragMinuten++;
				}
				
					
				if (!addiereMinuten(uebertragMinuten))
				{
					data1safe2recover(2);
					return false;
				}
				this.sekunden = (60 + restSekunden);
			}
		}
		
		return true;
	}
	
	private void data1safe2recover(int option)
	{
		if (option == 1)
		{
			tempsek = sekunden;
			tempmin = minuten;
			temph = stunden;
			temptag = getTag();
			tempmonat = getMonat();
			tempjahr =getJahr();
		}
		
		if (option == 2)
		{
			sekunden = tempsek;
			minuten = tempmin;
			stunden = temph;
			setDate(temptag, tempmonat, tempjahr);
		}
	}
	
	public String dateFormat()
	{
		return String.format("%02d.%02d.%04d %02d:%02d:%02d", getTag(), getMonat(), getJahr(), stunden, minuten, sekunden);
	}
	
	public String dateFormatLong()
	{
		return (dateMonthWordFormat() + String.format("%02d:%02d:%02d", stunden, minuten, sekunden));
	}
	
}
