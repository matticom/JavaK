package W3T1_MK;

public class DatumExt
{
	private int tag, monat, jahr;
	private static int DEFAULT_Tag = 1;
	private static int DEFAULT_Monat = 1;
	private static int DEFAULT_Jahr = 2000;
	private static final String[] monatsnamen = {"", "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", 
												"August", "September", "Oktober", "November", "Dezember"};
	private static final int[] tageImMonat = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private boolean datumChanged = false;
	private boolean overMinYear = false;
	
			
	
	public DatumExt()
	{
		tag = DEFAULT_Tag;
		monat = DEFAULT_Monat;
		jahr = DEFAULT_Jahr;
	}
	
	public int getTag()
	{
		return tag;
	}

	public int getMonat()
	{
		return monat;
	}

	public int getJahr()
	{
		return jahr;
	}

	/**
	 * Neue Instanz von Datum
	 * @param tag
	 * @param monat
	 * @param jahr
	 */
	public DatumExt(int tag, int monat, int jahr)
	{
		this();
		setDate(tag, monat, jahr);		
	}
	/**
	 * Setze das Datum
	 * @param tag
	 * @param monat
	 * @param jahr
	 */
	public boolean setDate(int tag, int monat, int jahr)
	{
		if (checkDate(tag, monat, jahr))
		{
			this.tag = tag;
			this.monat = monat;
			this.jahr = jahr;
			return true;
		}
		else 
			return false;
	}
	
	private boolean checkDate(int tag, int monat, int jahr)
	{
		if (checkMinimumYear(jahr))
		{
			if (isValidDate(tag, monat, jahr))
			{
				return true;
			}
			else
			{
				System.out.printf("Einer der eingegebenen Werte ist nicht erlaubt (Tag: %s, Monat: %s, Jahr: %s)!\n", tag, monat, jahr);
				return false;
			}
		}
		else 
		{
			System.out.printf("Einer der eingegebenen Werte ist nicht erlaubt (Tag: %s, Monat: %s, Jahr: %s)!\n", tag, monat, jahr);
			return false;
		}
	}
		
	private static boolean checkMinimumYear(int jahr)
	{
		return (jahr > 1582);	
	}
		
	private static boolean isValidDate(int tag, int monat, int jahr)
	{
		if (tag <= 31 && (monat == 1 || monat == 3 || monat == 5 || monat == 7 || monat == 8 || monat == 10 || monat == 12))
			return true;
		else if (tag <= 30 && (monat == 4 || monat == 6 || monat == 9 || monat == 11))
			return true;
		else if (tag <= 28 && monat == 2)
			return true;
		else if (tag == 29 && monat == 2 && checkSchaltjahr(jahr))
			return true;
		else 
			return false;
	}
	
	private static boolean checkSchaltjahr(int jahr)
	{
		return ((jahr%400 == 0) || ((jahr%4 == 0) && (jahr%100 != 0)));
	}
	
	/**
	 * Gibt den Default Tag zurück
	 */
	public static int getDEFAULT_Tag()
	{
		return DEFAULT_Tag;
	}

	/**
	 * Setzt den Default Tag
	 */
	public static void setDEFAULT_Tag(int dEFAULT_Tag)
	{
		if (isValidDate(dEFAULT_Tag, DatumExt.DEFAULT_Monat, DatumExt.DEFAULT_Jahr))
			DEFAULT_Tag = dEFAULT_Tag;
		else 
			System.out.printf("Der eingegebene Tag ist nicht erlaubt (Tag: %s, Monat: %s, Jahr: %s)!\n", dEFAULT_Tag, DatumExt.DEFAULT_Monat, DatumExt.DEFAULT_Jahr);
	}

	/**
	 * Gibt den Default Monat zurück
	 */
	public static int getDEFAULT_Monat()
	{
		return DEFAULT_Monat;
	}

	/**
	 * Setzt den Default Monat
	 */
	public static void setDEFAULT_Monat(int dEFAULT_Monat)
	{
		if (isValidDate(DatumExt.DEFAULT_Tag, dEFAULT_Monat, DatumExt.DEFAULT_Jahr))
			DEFAULT_Monat = dEFAULT_Monat;
		else 
			System.out.printf("Der eingegebene Monat ist nicht erlaubt (Tag: %s, Monat: %s, Jahr: %s)!\n", DatumExt.DEFAULT_Tag, dEFAULT_Monat, DatumExt.DEFAULT_Jahr);
	}

	/**
	 * Gibt das Default Jahr zurück
	 */
	public static int getDEFAULT_Jahr()
	{
		return DEFAULT_Jahr;
	}

	/**
	 * Setzt das Default Jahr
	 */
	public static void setDEFAULT_Jahr(int dEFAULT_Jahr)
	{
		
		if (checkMinimumYear(dEFAULT_Jahr) && isValidDate(DatumExt.DEFAULT_Tag, DatumExt.DEFAULT_Monat, dEFAULT_Jahr))
			DEFAULT_Jahr = dEFAULT_Jahr;
		else 
			System.out.printf("Das eingegebene Jahr ist nicht erlaubt (Tag: %s, Monat: %s, Jahr: %s)!\n", DatumExt.DEFAULT_Tag, DatumExt.DEFAULT_Monat, dEFAULT_Jahr);
	}
	
	/**
	 * Gibt das Datum aus im Format tt.mm.jjjj
	 */
	public String dateStdFormat()
	{
		String date = "";
		
		if (tag < 10)
			date = "0" + tag;
		else
			date = Integer.toString(tag);
		
		if (monat < 10)
			date = date + ".0" + monat + "." + jahr;
		else
			date = date + "." + monat + "." + jahr;
				
		return date;
	}
	
	/**
	 * Gibt das Datum aus im Format tt. Monatsname jjjj
	 */
	public String dateMonthWordFormat()
	{
		String date = "";
		
		if (tag < 10)
			date = "0" + tag;
		else
			date = Integer.toString(tag);
		
		return (date + ". " + DatumExt.monatsnamen[monat] + " " + jahr);		
	}
	
	
	
	
	
	
	public void addiereTage(int tage)
	{
		for (int i = 0; i < tage; i++)
		{
			checkNewYearAdd(this.tag, monat, jahr);
			if (!datumChanged)
			{
				checkNewMonthAdd(this.tag, monat, jahr);
				if (!datumChanged)
					this.tag++;
			}
			datumChanged = false;
			
		}
	
	}

	
	public boolean subtrahiereTage(int tage)
	{
		int tempTag = this.tag;
		int tempMonat = this.monat;
		int tempJahr = this.jahr;
		
		for (int i = 0; i < tage; i++)
		{
			checkNewYearSub(this.tag, monat, jahr);
			if (overMinYear)
			{
				this.tag = tempTag;
				this.monat = tempMonat;
				this.jahr = tempJahr;
				overMinYear = false;
				return false;
			}
			
			if (!datumChanged)
			{
				checkNewMonthSub(this.tag, monat, jahr);
				if (!datumChanged)
					this.tag--;
			}
			
			datumChanged = false;

		}
		return true;
	}
	
	private static int schaltTag(int monat, int jahr)
	{
		if (checkSchaltjahr(jahr) && (monat == 2))
		{
			return 1;
		}
		return 0;
		
	}
	
	private void checkNewYearAdd(int tage, int monat, int jahr)
	{
		if ((tage == 31) && (monat == 12))
		{
			this.jahr++;
			this.monat = 1;
			this.tag = 1;
			this.datumChanged = true;
		}
			
	}
	
	private void checkNewMonthAdd(int tage, int monat, int jahr)
	{
		if ((tageImMonat[monat] + schaltTag(monat, jahr)) == tage)
		{
			if (monat < 12)
			{
				this.tag = 1;
				this.monat++;
				this.datumChanged = true;
			}
			
		}
	}
	
	private void checkNewYearSub(int tage, int monat, int jahr)
	{
		if ((tage == 1) && (monat == 1))
		{
			if(checkMinimumYear(jahr-1))
			{
				this.jahr--;
				this.monat = 12;
				this.tag = 31;
				this.datumChanged = true;
			}
			else
			{
				System.out.println("Fehler: Das erste Jahr des gregorianischen Kalenders (1582) wird unterschritten!");
				overMinYear = true;
			}
		}
					
	}
	
	private void checkNewMonthSub(int tage, int monat, int jahr)
	{
		if ( 1 == tage )
		{
			if (monat > 1)
			{
				this.tag = (tageImMonat[monat-1] + schaltTag(monat-1, jahr));
				this.monat--;
				this.datumChanged = true;
			}
			
		}
	}
	
	public void addiereMonate(int monate)
	{
		for (int i = 0; i < monate; i++)
		{
			addMonth();
			
		}
	
	}
	
	public void subtrahiereMonate(int monate)
	{
		int tempTag = this.tag;
		int tempMonat = this.monat;
		int tempJahr = this.jahr;
		
		for (int i = 0; i < monate; i++)
		{
			subMonth();	
			if (overMinYear)
			{
				this.tag = tempTag;
				this.monat = tempMonat;
				this.jahr = tempJahr;
				overMinYear = false;
				break;
			}
		}
	
	}
	
	private void addMonth()
	{

		if ((tag == (tageImMonat[monat]+schaltTag(monat, jahr))) && (monat < 12))
		{
			this.tag = tageImMonat[monat+1]+schaltTag(monat+1, jahr);
		}
		
		if (monat == 12)
		{
			this.monat = 1;
			this.jahr++;
				
		}
		else 
			this.monat++;
		
	}
	
	private void subMonth()
	{

		if (monat == 1 && jahr == 1583)
		{
			System.out.println("Fehler: Das erste Jahr des gregorianischen Kalenders (1582) wird unterschritten!");
			overMinYear = true;
		}
		else
		{	if ((tag == tageImMonat[monat]+schaltTag(monat, jahr)) && (monat > 1))
			{
				this.tag = tageImMonat[monat-1]+schaltTag(monat-1, jahr);
			}
			
			if (monat == 1)
			{
				this.monat = 12;
				this.jahr--;
					
			}
			else 
				this.monat--;
		}
		
	}
	
	public void addiereJahre(int jahre)
	{
		for (int i = 0; i < jahre; i++)
		{
			addYear();
			
		}
	
	}
	
	private void addYear()
	{
		if ( (monat == 2) && (tag == tageImMonat[monat]+schaltTag(monat, jahr)))
		{
			this.tag = tageImMonat[monat]+schaltTag(monat, jahr+1);
			jahr++;
		}
		else
			jahr++;
	}
	
	public void subtrahiereJahre(int jahre)
	{
		int tempTag = this.tag;
		int tempMonat = this.monat;
		int tempJahr = this.jahr;
		
		for (int i = 0; i < jahre; i++)
		{
			subYear();	
			if (overMinYear)
			{
				this.tag = tempTag;
				this.monat = tempMonat;
				this.jahr = tempJahr;
				overMinYear = false;
				break;
			}
		}
	
	}
	
	private void subYear()
	{
		if (jahr == 1583)
		{
			System.out.println("Fehler: Das erste Jahr des gregorianischen Kalenders (1583) wird unterschritten!");
			overMinYear = true;
		}
		else
		{	
			if ( (monat == 2) && (tag == tageImMonat[monat]+schaltTag(monat, jahr)))
			{
				this.tag = tageImMonat[monat]+schaltTag(monat, jahr-1);
				jahr--;
			}
			else
				jahr--;
		}
			
		
	}
	
	
	
	
}
