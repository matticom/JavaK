package W2T5_MK;

public class Datum
{
	private int tag, monat, jahr;
	private static int DEFAULT_Tag = 1;
	private static int DEFAULT_Monat = 1;
	private static int DEFAULT_Jahr = 2000;
	private static final String[] monatsnamen = {"", "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", 
												"August", "September", "Oktober", "November", "Dezember"};
	public Datum()
	{
		tag = DEFAULT_Tag;
		monat = DEFAULT_Monat;
		jahr = DEFAULT_Jahr;
	}
	
	/**
	 * Neue Instanz von Datum
	 * @param tag
	 * @param monat
	 * @param jahr
	 */
	public Datum(int tag, int monat, int jahr)
	{
		this();
		checkDate(tag, monat, jahr);		
	}
	/**
	 * Setze das Datum
	 * @param tag
	 * @param monat
	 * @param jahr
	 */
	public void setDate(int tag, int monat, int jahr)
	{
		checkDate(tag, monat, jahr);
	}
	
	private void checkDate(int tag, int monat, int jahr)
	{
		if (checkMinimumYear(jahr))
		{
			if (isValidDate(tag, monat, jahr))
			{
				this.tag = tag;
				this.monat = monat;
				this.jahr = jahr;
			}
			else
				System.out.printf("Einer der eingegebenen Werte ist nicht erlaubt (Tag: %s, Monat: %s, Jahr: %s)!\n", tag, monat, jahr);
		}
		else 
			System.out.printf("Einer der eingegebenen Werte ist nicht erlaubt (Tag: %s, Monat: %s, Jahr: %s)!\n", tag, monat, jahr);
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
		if (isValidDate(dEFAULT_Tag, Datum.DEFAULT_Monat, Datum.DEFAULT_Jahr))
			DEFAULT_Tag = dEFAULT_Tag;
		else 
			System.out.printf("Der eingegebene Tag ist nicht erlaubt (Tag: %s, Monat: %s, Jahr: %s)!\n", dEFAULT_Tag, Datum.DEFAULT_Monat, Datum.DEFAULT_Jahr);
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
		if (isValidDate(Datum.DEFAULT_Tag, dEFAULT_Monat, Datum.DEFAULT_Jahr))
			DEFAULT_Monat = dEFAULT_Monat;
		else 
			System.out.printf("Der eingegebene Monat ist nicht erlaubt (Tag: %s, Monat: %s, Jahr: %s)!\n", Datum.DEFAULT_Tag, dEFAULT_Monat, Datum.DEFAULT_Jahr);
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
		
		if (checkMinimumYear(dEFAULT_Jahr) && isValidDate(Datum.DEFAULT_Tag, Datum.DEFAULT_Monat, dEFAULT_Jahr))
			DEFAULT_Jahr = dEFAULT_Jahr;
		else 
			System.out.printf("Das eingegebene Jahr ist nicht erlaubt (Tag: %s, Monat: %s, Jahr: %s)!\n", Datum.DEFAULT_Tag, Datum.DEFAULT_Monat, dEFAULT_Jahr);
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
		
		return (date + ". " + Datum.monatsnamen[monat] + " " + jahr);		
	}
		
}
