package W2T4_MK;

public class Monat
{
	private static int DEFAULT_Monat = 4;
	
	private int monat;
	
	
	public static int getDEFAULT_Monat()
	{
		return DEFAULT_Monat;
	}

	public static void setDEFAULT_Monat(int dEFAULT_Monat)
	{
		DEFAULT_Monat = dEFAULT_Monat;
	}

	/**
	 * Konstruktor mit Defaultwert
	 */
	public Monat()
	{
		monat = DEFAULT_Monat;
	}
	
	/**
	 * Konstruktor mit Monatinput
	 * @param monat
	 */
	public Monat(int monat)
	{
		this();
		setMonat(monat);
	}
	
	/**
	 * Gibt den Monat zurück
	 */
	public int getMonat()
	{
		return this.monat;
	}
	/**
	 * Setz den Monat
	 * @param value Monat (1-12)
	 */
	public void setMonat(int value)
	{
		if (checkMonat(value))
			this.monat = value;
		else
			System.out.println("Fehler: Der Monat muss zwischen 1 und 12 liegen!");
			
	}
	
	private static boolean checkMonat(int monat)
	{
		if ((monat>=1) && (monat<=12))
			return true;
		return false;
	}
	
	/**
	 * Weist den Defaultwert der Klasse für den Monat einen anderen Wert zu.
	 * @param value Monat von 1 bis 12
	 */
	public static void changeDefaultMonat(int value)
	{
		if (checkMonat(value))
			DEFAULT_Monat = value;
		else
			System.out.println("Fehler: Der Monat muss zwischen 1 und 12 liegen!");
	}
	
	public static int getDefaultMonat()
	{
		return DEFAULT_Monat;
	}
	
	/**
	 * Gibt den Namen des Monats zurück
	 */
	public String nameOfMouth()
	{
		return toName();
	}
	
	private String toName()
	{
		switch (this.monat)
		{
		case 1: return "Januar";
		case 2: return "Februar";
		case 3: return "März";
		case 4: return "April";
		case 5: return "Mai";
		case 6: return "Juni";
		case 7: return "Juli";
		case 8: return "August";
		case 9: return "September";
		case 10: return "Oktober";
		case 11: return "November";
		case 12: return "Dezember";
		default: return "Fehler!";
		}
				
	}
		
}
