package W3T4_Dozent;

public class DatumZeit extends Datum
{

	private int stunde, minute, sekunde;
	
	
	public DatumZeit()
	{
		// Automatischer Aufruf des Standardkonstruktors der Superklasse 'Datum'
	}

	
	public DatumZeit(int tag, int monat, int jahr, int stunde, int minute, int sekunde)
	{
		
		setDatumZeit(tag, monat, jahr, stunde, minute, sekunde);
		
		
	}
	
	public boolean setZeit(int stunde, int minute, int sekunde)
	{
		boolean retValue = false;
		
		if (checkZeit(stunde, minute, sekunde))
		{
			this.stunde = stunde;
			this.minute = minute;
			this.sekunde = sekunde;
			retValue = true;
		}
		
		return retValue;
	}
	
	
	public boolean setDatumZeit(int tag, int monat, int jahr, int stunde, int minute, int sekunde)
	{
		
		boolean retValue = false;
		
		
		if (checkZeit(stunde, minute, sekunde))
		{
			if (setDatum(tag, monat, jahr))
				retValue = setZeit(stunde, minute, sekunde);
			
		}
		
		return retValue;
	}
	
	
	
	
	private String toTimeString()
	{
		return String.format("%02d:%02d:%02d Uhr", stunde, minute, sekunde);
	}
	

	@Override
	public String toString()
	{
		return super.toString() + " " + toTimeString();
	}


	@Override
	public String toLongString()
	{
		return super.toLongString() + " " + toTimeString();
		
	}
	
	
	private boolean checkZeit(int stunde, int minute, int sekunde)
	{
		boolean retValue = true;
		
		if (stunde < 0 || stunde > 23)
			retValue = false;
		else if (minute < 0 || minute > 59)
			retValue = false;
		else if (sekunde < 0 || sekunde > 59)
			retValue = false;
		
		return retValue;
		
	}


	public int getStunde()
	{
		return stunde;
	}


	public boolean setStunde(int value)
	{
		
		boolean retValue = false;
		
		if (checkZeit(value, minute, sekunde))
		{
			stunde = value;
			retValue = true;
		}
		
		return retValue;
	}


	public int getMinute()
	{
		return minute;
	}


	public boolean setMinute(int value)
	{
		boolean retValue = false;
		
		if (checkZeit(stunde, value, sekunde))
		{
			minute = value;
			retValue = true;
		}
		
		return retValue;
	}


	public int getSekunde()
	{
		return sekunde;
	}


	public boolean setSekunde(int value)
	{
		boolean retValue = false;
		
		if (checkZeit(stunde, minute, value))
		{
			sekunde = value;
			retValue = true;
		}
		
		return retValue;
	}
	
	
	public boolean addiereStunden(int anzahl)
	{
		
		boolean retValue = false;
		boolean error = false;
		

		// Aktuelle Werte von DatumZeit sichern
		int aktTag = getTag();
		int aktMonat = getMonat();
		int aktJahr = getJahr();
		int aktStunde = stunde;
		int aktMinute = minute;
		int aktSekunde = sekunde;
		
		
		// Prüfen, ob die Anzahl der Stunden >= 1 Tag ist
		int tage = anzahl / 24;
		int stunden = anzahl % 24;
		
//		if (!addiereTage(tage))
//		{
//			setDatumZeit(aktTag, aktMonat, aktJahr, aktStunde, aktMinute, aktSekunde);
//			return retValue;
//		}
			
			
		// Die verbleibenden Stunden addieren/subtrahieren
		if (stunden > 0)
		{
			while(stunden-- > 0)
				naechsteStunde();

		}
		else if (stunden < 0)
		{
			
			while (stunden++ < 0)
			{
				if (!vorherigeStunde())
				{
					error = true;
					break;
				}
					
			}
		}
		
		retValue = !error;
		
		return retValue;
		
	}
	
	private void naechsteStunde()
	{
		
		if (!setZeit(stunde + 1, minute, sekunde))
		{
			setZeit(0, minute, sekunde);
			addiereTage(1);
		}
		
	}
	
	private boolean vorherigeStunde()
	{
		boolean retValue = true;
		
//		if (!setZeit(stunde - 1, minute, sekunde))
//		{
//			setZeit(23, minute, sekunde);
//			retValue = addiereTage(-1);				
//		}
		
		return retValue;
	}
	
	public boolean addiereMinuten(int anzahl)
	{
		
		boolean retValue = false;
		boolean error = false;
		

		// Aktuelle Werte von DatumZeit sichern
		int aktTag = getTag();
		int aktMonat = getMonat();
		int aktJahr = getJahr();
		int aktStunde = stunde;
		int aktMinute = minute;
		int aktSekunde = sekunde;
		
		
		// Prüfen, ob die Anzahl der Minuten >= 1 Stunde ist
		int stunden = anzahl / 60;
		int minuten = anzahl % 60;
		
		if (!addiereStunden(stunden))
		{
			setDatumZeit(aktTag, aktMonat, aktJahr, aktStunde, aktMinute, aktSekunde);
			return retValue;
		}
			
			
		// Die verbleibenden Minuten addieren/subtrahieren
		if (minuten > 0)
		{
			while(minuten-- > 0)
				naechsteMinute();

		}
		else if (minuten < 0)
		{
			
			while (minuten++ < 0)
			{
				if (!vorherigeMinute())
				{
					error = true;
					break;
				}
					
			}
		}
		
		retValue = !error;
		
		return retValue;
		
	}
	
	private void naechsteMinute()
	{
		
		if (!setZeit(stunde, minute + 1, sekunde))
		{
			setZeit(stunde, 0, sekunde);
			addiereStunden(1);
		}
		
	}
	
	private boolean vorherigeMinute()
	{
		boolean retValue = true;
		
		if (!setZeit(stunde, minute - 1, sekunde))
		{
			setZeit(stunde,  59, sekunde);
			retValue = addiereStunden(-1);				
		}
		
		return retValue;
	}
	
	public boolean addiereSekunden(int anzahl)
	{
		
		boolean retValue = false;
		boolean error = false;
		

		// Aktuelle Werte von DatumZeit sichern
		int aktTag = getTag();
		int aktMonat = getMonat();
		int aktJahr = getJahr();
		int aktStunde = stunde;
		int aktMinute = minute;
		int aktSekunde = sekunde;
		
		
		// Prüfen, ob die Anzahl der Sekunden >= 1 Minute ist
		int minuten =  anzahl / 60;
		int sekunden = anzahl % 60;
		
		if (!addiereMinuten(minuten))
		{
			setDatumZeit(aktTag, aktMonat, aktJahr, aktStunde, aktMinute, aktSekunde);
			return retValue;
		}
			
			
		// Die verbleibenden Sekunden addieren/subtrahieren
		if (sekunden > 0)
		{
			while(sekunden-- > 0)
				naechsteSekunde();

		}
		else if (sekunden < 0)
		{
			
			while (sekunden++ < 0)
			{
				if (!vorherigeSekunde())
				{
					error = true;
					break;
				}
					
			}
		}
		
		retValue = !error;
		
		return retValue;
		
	}
	
	private void naechsteSekunde()
	{
		
		if (!setZeit(stunde, minute, sekunde + 1))
		{
			setZeit(stunde, minute, 0);
			addiereMinuten(1);
		}
		
	}
	
	private boolean vorherigeSekunde()
	{
		boolean retValue = true;
		
		if (!setZeit(stunde, minute, sekunde - 1))
		{
			setZeit(stunde,  minute, 59);
			retValue = addiereMinuten(-1);				
		}
		
		return retValue;
	}
	
}
