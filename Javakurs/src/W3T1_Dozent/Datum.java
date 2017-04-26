package W3T1_Dozent;

public class Datum
{

	private int				tag, monat, jahr;
	
	private static final  	int[]	 maxMonatstage	= new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final   	String[] arrMonatsname	= new String[] { "", "Januar", "Februar", "März", "April", "Mai", "Juni", 
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


	public String toDateString()
	{
		return String.format("%02d.%02d.%04d", tag, monat, jahr);
	}
	
	public String toLongDateString()
	{
		return String.format("%d. %s %04d", tag, arrMonatsname[monat], jahr);
	}
	
	
}
