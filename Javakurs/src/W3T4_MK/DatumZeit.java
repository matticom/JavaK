package W3T4_MK;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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
					break;
				}
					
			}
		}
		if (error())
			this.setDatumZeit(aktTag, aktMonat, aktJahr, aktStunde, aktMinute, aktSekunde);
		
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
				
		if (!setZeit(stunde - 1, minute, sekunde))
		{
			setZeit(23, minute, sekunde);
			addiereTage(-1);
			if (error())
				return false;
		}
		
		return true;
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
				if (!vorherigeMinute())
				{
					break;
				}
					
			}
		}
		if (error())
			this.setDatumZeit(aktTag, aktMonat, aktJahr, aktStunde, aktMinute, aktSekunde);
		
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
				
		if (!setZeit(stunde, minute - 1, sekunde))
		{
			setZeit(stunde,  59, sekunde);
			addiereStunden(-1);
			if (error())
				return false;
		}
		
		return true;
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
					break;
					
			}
		}

		if (error())
			this.setDatumZeit(aktTag, aktMonat, aktJahr, aktStunde, aktMinute, aktSekunde);
		
		return this;
		
	}
	
	@Override
	public Datum addiereJahre(int anzahl)
	{
		// TODO Auto-generated method stub
		super.addiereJahre(anzahl);
		return this;
	}


	@Override
	public Datum addiereMonate(int anzahl)
	{
		// TODO Auto-generated method stub
		super.addiereMonate(anzahl);
		return this;
	}


	@Override
	public Datum addiereTage(int anzahl)
	{
		// TODO Auto-generated method stub
		super.addiereTage(anzahl);
		return this;
	}


	@Override
	public Datum addiereTage1(int anzahl)
	{
		// TODO Auto-generated method stub
		super.addiereTage1(anzahl);
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
	
		if (!setZeit(stunde, minute, sekunde - 1))
		{
			setZeit(stunde,  minute, 59);
			addiereMinuten(-1);
			if (error())
				return false;
		}
		
		return true;
	}
	
	@Override
	public int compareTo(Datum that)	// geerbt von Datum -> muss in DatumZeit comparable Interface implementiert werden damit Typ genau verglichen werden kann?
	{
		if (!(that instanceof DatumZeit))
		{
			Datum inDatumUmgewandelteThisRef = new Datum(this.getTag(), this.getMonat(), this.getJahr());
			if (inDatumUmgewandelteThisRef.compareTo(that) == 0)
				return 1;
			else
				return inDatumUmgewandelteThisRef.compareTo(that);
					
		}
		
		if (this.getJahr() < that.getJahr())
			return -1;
		if (this.getJahr() > that.getJahr())
			return 1;
		if (this.getMonat() < that.getMonat())
			return -1;
		if (this.getMonat() > that.getMonat())
			return 1;
		if (this.getTag() < that.getTag())
			return -1;
		if (this.getTag() > that.getTag())
			return 1;
		if (stunde < ((DatumZeit)that).stunde)
			return -1;
		if (stunde > ((DatumZeit)that).stunde)
			return 1;
		if (minute < ((DatumZeit)that).minute)
			return -1;
		if (minute > ((DatumZeit)that).minute)
			return 1;
		if (sekunde < ((DatumZeit)that).sekunde)
			return -1;
		if (sekunde > ((DatumZeit)that).sekunde)
			return 1;
		
		
		return 0;
	}

	@Override
	public boolean equals(Object that)
	{
		if (this == that)
			return true;
		
		if (!(that instanceof DatumZeit))
			return false;
		
		if ((((DatumZeit)that).getSekunde() == this.getSekunde()) && (((DatumZeit)that).getMinute() == this.getMinute()) && 
				(((DatumZeit)that).getStunde() == this.getStunde()) && (((DatumZeit)that).getTag() == this.getTag()) && 
				(((DatumZeit)that).getMonat() == this.getMonat()) && (((DatumZeit)that).getJahr() == this.getJahr()))
			return true;
		else
			return false;

	}

	@Override
	public int hashCode()
	{
		
		String datumZeit = new DecimalFormat("00").format(getTag())+ new DecimalFormat("00").format(getMonat()) +
				new DecimalFormat("0000").format(getJahr()) + new DecimalFormat("00").format(getSekunde()) +
				new DecimalFormat("00").format(getMinute()) + new DecimalFormat("00").format(getStunde());
				
		return datumZeit.hashCode();
		
	}
	
}
