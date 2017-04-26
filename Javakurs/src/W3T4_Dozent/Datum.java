package W3T4_Dozent;

public class Datum
{

	private int				tag, monat, jahr;
	
	private static final  	int[]	 maxMonatstage	= new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	protected static final   	String[] arrMonatsname	= new String[] { "", "Januar", "Februar", "M�rz", "April", "Mai", "Juni", 
			                                                         "Juli", "August", "September", "Oktober", "November", "Dezember" };
	
	private static final String[] arrWochentag = new String[]
			{ "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag" };
	
	private static int 	DEFAULT_TAG = 1;
	private static int 	DEFAULT_MONAT= 1;
	private static int 	DEFAULT_JAHR = 2000;
	
	
	private boolean errFlag;
	private String errorMessage = "";
	
	
	
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
	
	
	public boolean error()
	{
		return errFlag;
	}
	
	public String getLastError()
	{
		
		String retValue = "";
		
		if (errFlag)
			retValue = errorMessage;
		
		
		return retValue;
		
	}
	
	
	private void clearError()
	{
		errFlag = false;
		errorMessage = "";
		
	}
	
	
	private void setError(String errorMessage)
	{
		errFlag = true;
		this.errorMessage = errorMessage;
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
		return String.format("%s, %d. %s %04d", getWochentag(tag, monat, jahr), tag, arrMonatsname[monat], jahr);
	}
	
	
	public Datum addiereJahre(int anzahl)
	{
		
		
		if (checkJahr(jahr + anzahl))
		{
			
			jahr += anzahl;
			
			// Wenn der Tag des aktuellen Datums ung�ltig ist,
			// auf den letzten Tag des aktuellen Monats setzen
			// (speziell f�r Februar und Schaltjahr).
			if (!checkDatum(tag, monat, jahr))
				setDatum(maxMonatstage(monat, jahr), monat, jahr);
		
		}
		else
			setError("Das ermittelte Datum liegt au�erhalb des Gregorianischen Kalenders.");
			
		
		return this;
	}
	
	
	public Datum addiereMonate(int anzahl)
	{
		
		// Aktuelle Werte des Datums sichern
		int aktTag = tag;
		int aktMonat = monat;
		int aktJahr = jahr;
		
		clearError();
		
		// Pr�fen, ob die Anzahl der Monate >= 1 Jahr
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
		// liegt pr�fen, ob der Tag ein g�ltiger Tag f�r diesen Monat und dieses jahr ist.
		if (checkJahr(jahr))
		{
			if (!checkDatum(tag, monat, jahr))
				setDatum(maxMonatstage(monat, jahr), monat, jahr);
		}
		else
		{
			setError("Das ermittelte Datum liegt au�erhalb des Gregorianischen Kalenders.");
			setDatum(aktTag, aktMonat, aktJahr);
			
		}
			
		return this;
	}
	
	public Datum addiereTage(int anzahl)
	{
		
		// Aktuelle Werte des Datums sichern
		int aktTag = tag;
		int aktMonat = monat;
		int aktJahr = jahr;
		
		clearError();
		
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
					break;
			}
		}
		
		// Fehler bei der Datumsermittlung
		// Zur�cksetzen auf das originale Datum
		if (error())
			setDatum(aktTag, aktMonat, aktJahr);
		
		return this;
		
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
				{
					setError("Das ermittelte Datum liegt au�erhalb des Gregorianischen Kalenders.");
					retValue = false;
				}
				
			}
		}
		
		return retValue;
	}
	
	public Datum addiereTage1(int anzahl)
	{

		
		// Aktuelle Werte des Datums sichern
		int aktTag = tag;
		int aktMonat = monat;
		int aktJahr = jahr;

		clearError();
		
		if (anzahl > 0)
		{

			while (anzahl > 0)
			{
				// Wenn die Anzahl der Tage nicht mehr in den aktuellen Monat
				// addiert werden k�nnen, auf den n�chsten Monat weiterschalten.
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
				// Wenn die Anzahl der Tage gr��er ist als die Anzahl der Tage
				// im  aktuellen Monat, auf den vorherigen Monat zur�ck gehen.
				if (tag + anzahl < 1)
				{
					anzahl += tag;
					addiereMonate(-1);
					if (error())
						break;
					
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
		// Zur�cksetzen auf das originale Datum
		if (error())
			setDatum(aktTag, aktMonat, aktJahr);
			
		
		return this;
	}

	@Override
	public String toString()
	{
		return String.format("%02d.%02d.%04d", tag, monat, jahr);
	}
	
	// Berechnung des Ostersonntags nach Gau�
	public static Datum getOstersonntag(int jahr)
	{
		Datum Ostersonntag;

		int K, M, S, D, R, A, OG, SZ, OE, OSI;

		// Berechnung der S�kul�rzahl
		K = jahr / 100;
		// S�kul�re Mondschaltung
		M = 15 + ((3 * K + 3) / 4) - ((8 * K + 13) / 25);
		// S�kul�re Sonnenschaltung
		S = 2 - ((3 * K + 3) / 4);
		// Mondparameter
		A = jahr % 19;
		// Keim f�r den ersten Vollmond im Fr�hling
		D = (19 * A + M) % 30;
		// Kalendarische Korrekturgr��e
		R = (D / 29) + ((D / 28) - (D / 29)) * (A / 11);
		// Ostergrenze
		OG = 21 + D - R;
		// Erster Sonntag im M�rz
		SZ = 7 - (jahr + (jahr / 4) + S) % 7;
		// Entfernung des Ostersonntags von der Ostergrenze (Osterentfernung in
		// Tagen)
		OE = 7 - (OG - SZ) % 7;
		// Datum des Ostersonntags als M�rzdatum (32. M�rz = 1. April usw.)
		OSI = ((OG + OE) - 1);

		Ostersonntag = new Datum(1, 3, jahr);
		Ostersonntag.addiereTage(OSI);

		return Ostersonntag;

	}
	
	// F�r die Berechnung des Bu�- und Bettag gilt folgendes:
	// Dieser Tag ist immer der vorletzte Mittwoch vor dem 1. Advent und kann
	// demnach nur
	// in der Zeit vom 16. - 22.11. liegen. Zur Bestimmung des 1. Advent muss
	// der 4. Advent ermittelt werden.
	// Der 4. Advent ist nun der letzte Sonntag vor dem 1. Weihnachtstag
	// (25.12.) und kann demnach nur in der Zeit vom 18. - 24.12 liegen.
	// Um diesen Sonntag zu bestimmen, muss die Tageszahl des 24.12. ermittelt
	// werden.
	// F�r die Ermittlung der Tageszahl eines bestimmten Datum gibt es eine
	// Formel, die
	// jedoch nur f�r die Tage vom 1.1.1901 bis zum 31.12.2099 korrekt ist. Da
	// aber in der Zeit zwischen Ostern und Weihnachten keine Schalttage liegen k�nnen,
	// kann man den 4. Advent trotzdem korrekt berechnen.
	// Da der 30.4. auf den gleichen Wochentag f�llt wie der 24.12., kann
	// folgende Formel zur Berechnung der Tageszahl herangezogen werden:
	//
	// Liegt der Ostersonntag im M�rz:
	// Tageszahl = (33 - Tag des Ostersonntags) mod 7
	// Liegt der Ostersonntag im April:
	// Tageszahl = (30 - Tag des Ostersonntags) mod 7

	// Jetzt gilt f�r den Bu�- und Bettag:
	// Zwischen Buss und Bettag und dem 4. Advent liegen 32 Tage.
	// Zwischen dem 4. Advent und dem 24.12. liegt die Anzahl Tage, die durch
	// die Tageszahl
	// ausgedr�ckt wird.
	// Deshalb: Der 24.12. - 32 Tage (ergibt den 22.11.) - Tageszahl = Bu�- und
	// Bettag

	public static Datum getBussUndBetTag(int jahr)
	{

		Datum Ostern = getOstersonntag(jahr);
		int Tageszahl = 0;
		Datum BussUndBetTag;

		if (Ostern.getMonat() == 3)
			Tageszahl = (33 - Ostern.getTag()) % 7;
		else if (Ostern.getMonat() == 4)
			Tageszahl = (30 - Ostern.getTag()) % 7;

		BussUndBetTag = new Datum(24, 12, jahr);
		BussUndBetTag.addiereTage(-32 - Tageszahl);

		return BussUndBetTag;

	}

	/*
	 * Gau� hat eine Formel entwickelt, mit der man zu einem Datum bestehend aus
	 * Tag, Monat und Jahr den zugeh�rigen Wochentag ermitteln kann. Damit die
	 * untenstehende Formel richtige Ergebnisse liefert, mu� das Datum zwischen
	 * dem 01.01.1582 und dem 31.12.3000 liegen. Die Berechnung erfolgt mit der
	 * folgenden von Gau� stammenden sogenannten Kalenderformel : Das zu
	 * behandelnde Datum sei in den Variablen tag, monat, jahr abgelegt.
	 *
	 * Damit werden zwei Hilfsgr��en h und k wie folgt berechnet :
	 * 
	 * Ist der Wert von monat <= 2, so ist h = monat + 12 und k = jahr - 1. Ist
	 * der Wert von monat > 2, so ist h = monat und k = jahr. Der Wochentag
	 * ergibt sich nun durch : wochentag = [ tag + 2*h + (3*h + 3) div 5 + k + k
	 * div 4 - k div 100 + k div 400 +1] mod 7
	 * 
	 * Dadurch erh�lt die Variable wochentag einen Wert zwischen 0 und 6 . Dabei
	 * bedeutet 0 = Sonntag , 1 = Montag , usw.
	 */

	public static int getTagDerWoche(int tag, int monat, int jahr)
	{

		int h = monat;
		int k = jahr;

		if (monat <= 2)
		{
			h = monat + 12;
			k = jahr - 1;
		}

		return (tag + 2 * h + (3 * h + 3) / 5 + k + k / 4 - k / 100 + k / 400 + 1) % 7;

	}

	public static String getWochentag(int tag, int monat, int jahr)
	{

		return arrWochentag[getTagDerWoche(tag, monat, jahr)];

	}
}
