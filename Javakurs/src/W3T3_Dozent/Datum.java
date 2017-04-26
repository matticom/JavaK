package W3T3_Dozent;

public class Datum
{

	private int				tag, monat, jahr;
	
	private static final  	int[]	 maxMonatstage	= new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	protected static final   	String[] arrMonatsname	= new String[] { "", "Januar", "Februar", "März", "April", "Mai", "Juni", 
			                                                         "Juli", "August", "September", "Oktober", "November", "Dezember" };
	
	private static int 	DEFAULT_TAG = 1;
	private static int 	DEFAULT_MONAT= 1;
	private static int 	DEFAULT_JAHR = 2000;
	
	public Datum()
	{
		tag = DEFAULT_TAG ;
		monat = DEFAULT_MONAT ;
		jahr = DEFAULT_JAHR;
	}
	
	public Datum(int tag, int monat, int jahr)
	{
		
		this();
		setDatum(tag, monat, jahr);
		
	}
	
	
	
	public boolean setDatum(int tag, int monat, int jahr)
	{
		
		boolean retValue = false;
		
		if (checkDatum(tag, monat, jahr))
		{
			this.tag = tag;
			this.monat = monat;
			this.jahr = jahr;
			retValue = true;
		}
		
		return retValue;
	}
	
	
	private boolean checkDatum(int tag, int monat, int jahr)
	{
		
		return (checkJahr(jahr) && checkMonat(monat) && checkTag(tag, monat, jahr));
		
	}
	
	private static boolean checkJahr(int jahr)
	{
		
		return (jahr > 1582);
	}
	
	private boolean checkMonat(int monat)
	{
		
		return (monat >= 1 && monat <= 12);
	}
	
	
	private boolean checkTag(int tag, int monat, int jahr)
	{
		
		return (tag >= 1 && tag <= maxMonatstage(monat, jahr));
		
	}
	
	
	private int maxMonatstage(int monat, int jahr)
	{
		
		int retValue = maxMonatstage[monat];
		if (monat == 2)
			retValue += (istSchaltjahr(jahr) ? 1 : 0);
		
		return retValue;
		
	}
	
	public static boolean istSchaltjahr(int jahr)
	{
		
		boolean retValue = false;
		
		if (checkJahr(jahr))
		{
			
			if ((jahr % 4 == 0 && jahr % 100 != 0) || jahr % 400 == 0)
				retValue = true;
			
		}
		
		return retValue;
		
	}

	public int getTag()
	{
		return tag;
	}

	public boolean setTag(int value)
	{	
		return setDatum(value, monat, jahr);
	}

	public int getMonat()
	{
		return monat;
	}

	public boolean setMonat(int value)
	{
		return setDatum(tag, value, jahr);
	}

	public int getJahr()
	{
		return jahr;
	}

	public boolean setJahr(int value)
	{
		return setDatum(tag, monat, value);
	}

	
	public String toLongString()
	{
		return String.format("%d. %s %04d", tag, arrMonatsname[monat], jahr);
	}
	
	
	public boolean addiereJahre(int anzahl)
	{
		
		boolean retValue = false;
		
		if (checkJahr(jahr + anzahl))
		{
			
			jahr += anzahl;
			
			// Wenn der Tag des aktuellen Datums ungültig ist,
			// auf den letzten Tag des aktuellen Monats setzen
			// (speziell für Februar und Schaltjahr).
			if (!checkDatum(tag, monat, jahr))
				setDatum(maxMonatstage(monat, jahr), monat, jahr);
		
			retValue = true;
		}
		
		return retValue;
	}
	
	
	public boolean addiereMonate(int anzahl)
	{

		boolean retValue = true;
		
		// Aktuelle Werte des Datums sichern
		int aktTag = tag;
		int aktMonat = monat;
		int aktJahr = jahr;
		
		// Prüfen, ob die Anzahl der Monate >= 1 Jahr
		int jahre = anzahl / 12;
		int monate = anzahl % 12;
		
		jahr += jahre;
		monat += monate;
		
		// Wenn die restlichen Monate nicht mehr auf das aktuelle Jahr addiert
		// werden konnten, ein Jahr weiter schalten.
		if (monat > 12)
		{
			jahr++;
			monat = Math.abs(12 - monat);
		}
		else if (monat < 1)
		{
			jahr--;
			monat = 12 + monat;
		}
		
		// Wenn das neu ermittelte Datum innerhalb des Gregorianischen Kalenders
		// liegt prüfen, ob der Tag ein gültiger Tag für diesen Monat und dieses jahr ist.
		if (checkJahr(jahr))
		{
			if (!checkDatum(tag, monat, jahr))
				setDatum(maxMonatstage(monat, jahr), monat, jahr);
		}
		else
		{
			setDatum(aktTag, aktMonat, aktJahr);
			retValue = false;
		}
		
		return retValue;
	}
	
	public boolean addiereTage(int anzahl)
	{
		
		boolean retValue = false;
		boolean error = false;
		
		// Aktuelle Werte des Datums sichern
		int aktTag = tag;
		int aktMonat = monat;
		int aktJahr = jahr;
		
		if (anzahl > 0)
		{
			
			while(anzahl-- > 0)
				naechsterTag();

		}
		else if (anzahl < 0)
		{
			while(anzahl++ < 0)
			{
				if (!vorherigerTag())
				{
					error = true;
					break;
				}
			}
		}
		
		// Fehler bei der Datumsermittlung
		// Zurücksetzen auf das originale Datum
		if (error)
			setDatum(aktTag, aktMonat, aktJahr);
	
		
		retValue = !error;
		
		return retValue;
		
	}
	
	private void naechsterTag()
	{
		
		if (!setDatum(tag + 1, monat, jahr))
		{
			if (!setDatum(1, monat + 1, jahr))
				setDatum(1, 1, ++jahr);
			
		}
	}
	
	
	private boolean vorherigerTag()
	{
		
		boolean retValue = true;
		
		if (!setDatum(tag - 1, monat, jahr))
		{
			if (!setDatum(maxMonatstage(monat - 1, jahr), monat - 1, jahr))
			{
				if (!setDatum(31, 12, --jahr))
					retValue = false;
				
			}
		}
		
		return retValue;
	}
	
	public boolean addiereTage1(int anzahl)
	{

		boolean retValue = false;
		boolean error = false;
		
		// Aktuelle Werte des Datums sichern
		int aktTag = tag;
		int aktMonat = monat;
		int aktJahr = jahr;

		if (anzahl > 0)
		{

			while (anzahl > 0)
			{
				// Wenn die Anzahl der Tage nicht mehr in den aktuellen Monat
				// addiert werden können, auf den nächsten Monat weiterschalten.
				if (tag + anzahl > maxMonatstage(monat, jahr))
				{
					anzahl -= (maxMonatstage(monat, jahr) - tag) + 1;
					tag = 1;
					addiereMonate(1);
				}
				else
				{
					tag += anzahl;
					anzahl = 0;
				}
			}
		}
		else if (anzahl < 0)
		{

			while (anzahl < 0)
			{
				// Wenn die Anzahl der Tage größer ist als die Anzahl der Tage
				// im  aktuellen Monat, auf den vorherigen Monat zurück gehen.
				if (tag + anzahl < 1)
				{
					anzahl += tag;
					if (!addiereMonate(-1))
					{
						error = true;
						break;
					}
					tag = maxMonatstage(monat, jahr);
				}
				else
				{
					tag += anzahl;
					anzahl = 0;
				}
			}
		}

		// Fehler bei der Datumsermittlung
		// Zurücksetzen auf das originale Datum
		if (error)
			setDatum(aktTag, aktMonat, aktJahr);
				
		retValue = !error;
		
		
		return retValue;
	}

	@Override
	public String toString()
	{
		return String.format("%02d.%02d.%04d", tag, monat, jahr);
	}
	
	
	
	
}
