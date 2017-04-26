package W3T5_Dozent;

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
	
	
	public DatumZeit addiereStunden(int anzahl)
	{	

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
		
		addiereTage(tage);
		if (error())	
		{
			setDatumZeit(aktTag, aktMonat, aktJahr, aktStunde, aktMinute, aktSekunde);
			return this;
		}
			
			
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
					if (error())
						break;
				}
					
			}
		}
		
		return this;
		
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
		
		if (!setZeit(stunde - 1, minute, sekunde))
		{
			setZeit(23, minute, sekunde);
			addiereTage(-1);
			retValue = !error(); 
		}
		
		return retValue;
	}
	
	public DatumZeit addiereMinuten(int anzahl)
	{
		
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
		
		addiereStunden(stunden);
		
		if (error())
		{
			setDatumZeit(aktTag, aktMonat, aktJahr, aktStunde, aktMinute, aktSekunde);
			return this;
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
				vorherigeMinute();
				if (error())
					break;
					
			}
		}
		
		return this;
		
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
			addiereStunden(-1);
			retValue = !error();
		}
		
		return retValue;
	}
	
	public DatumZeit addiereSekunden(int anzahl)
	{
		
	
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
		
		addiereMinuten(minuten);
		if (error())
		{
			setDatumZeit(aktTag, aktMonat, aktJahr, aktStunde, aktMinute, aktSekunde);
			return this;
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
					if (error())
					break;
				}
					
			}
		}
				
		return this;
		
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
			addiereMinuten(-1);
			retValue = !error();
		}
		
		return retValue;
	}


	protected int convertTimeToNumber()
	{
		// Erstellt eine Ganzzahl von der Zeit im Format HHMMSS
		return (stunde * 10000) + (minute * 100) + sekunde;
		
	}
	
	
	
	@Override
	public DatumZeit addiereJahre(int anzahl)
	{
		super.addiereJahre(anzahl);
		return this;
	}


	@Override
	public DatumZeit addiereMonate(int anzahl)
	{
		super.addiereMonate(anzahl);
		return this;
	}


	@Override
	public DatumZeit addiereTage(int anzahl)
	{
		super.addiereTage(anzahl);
		return this;
	}


	@Override
	public DatumZeit addiereTage1(int anzahl)
	{
		super.addiereTage1(anzahl);
		return this;
	}


	@Override
	public int compareTo(Datum that)
	{
		
		int retValue = 0;
		
		retValue = super.compareTo(that);
		if (retValue != 0)
			return retValue;
		
		// Wenn das Vergleichsdatum identisch ist, die Zeit vergleichen.
		if (that instanceof DatumZeit)
		{
			if (this.convertTimeToNumber() < ((DatumZeit)that).convertTimeToNumber())
				retValue = -1;
			else if (this.convertTimeToNumber() > ((DatumZeit)that).convertTimeToNumber())
				retValue = 1;
			else
				retValue = 0;
		}
		else
		// Das Argument ist vom Typ 'Datum'
		// Bei einem Vergleich mit DatumZeit wird der Zeitanteil als 00:00:00 angenommen
			retValue = this.convertTimeToNumber() == 0 ? 0 : 1; 
		
		return retValue;
	}


	@Override
	public boolean equals(Object that)
	{
		
		boolean retValue = false;
		
		int thisDateValue = this.convertDateToNumber();
		int thisTimeValue = this.convertTimeToNumber();
		
		if (this == that)
			retValue = true;
		else if (that instanceof DatumZeit)
			retValue = this.toString().equals(((DatumZeit)that).toString());
		else if (that instanceof Datum)
			retValue = (thisDateValue == ((Datum)that).convertDateToNumber()) && (thisTimeValue == 0);
		
		return retValue;
	}


	@Override
	public int hashCode()
	{
		
		String s = String.format("%04d-%02d-%02d %02d:%02d:%02d", getJahr(), getMonat(), getTag(), stunde, minute, sekunde);
		return s.hashCode();
		
	}
	
	
	
	
	
	
	
}
