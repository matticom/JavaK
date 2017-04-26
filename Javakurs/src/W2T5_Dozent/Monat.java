package W2T5_Dozent;

// In der Klasse 'Monat' soll erzwungen werden,
// dass der Wert der Variablen 'monat' immer zwischen 1 und 12 liegt.



public class Monat
{

	private int monat;
	private static int DEFAULT_MONAT = 1;
	
	// Das Schlüsselwort 'final' bedeuted hier, dass die Referenz-Variable
	// 'monatsname' nachträglich nicht mehr geändert werden kann.
	// Auf die Elemente dieses Arrays kann jedoch immer noch schreibend
	// zugegriffen werden.
	private static final String[] monatsname = new String[] 
			{"", "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};      
	
	
	public Monat()
	{
		monat = DEFAULT_MONAT;
	}
	
	public Monat(int monat)
	{
		this();
		setMonat(monat);
	}

	public int getMonat()
	{
		return monat;
	}

	public void setMonat(int monat)
	{
		if (checkMonat(monat))
			this.monat = monat;
		else
			System.out.println("Ungültiger Monat.\nGültige Werte 1 - 12.");
		
	}

	public String getMonatsname()
	{
		return monatsname[monat];
	}
	
	
	public static int getDefaultMonat()
	{
		return DEFAULT_MONAT;
	}

	public static void setDefaultMonat(int monat)
	{
		if (checkMonat(monat))
			DEFAULT_MONAT = monat;
		else
			System.out.println("Ungültiger Monat.\nGültige Werte 1 - 12.");
	}

	private static boolean checkMonat(int monat)
	{
		return (monat >= 1 && monat <=12);
	}
	
	
}
