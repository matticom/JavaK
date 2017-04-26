package W6T2_Dozent;

public class Globals
{

	// Privater Standardkonstruktor.
	// Alle Methoden dieser Klasse sind statisch. Durch die Deklaration eines
	// eigenen Standardkonstruktors wird verhindert, dass Java einen Standardkonstruktor
	// erstellt.
	// Die Änderung des Zugriffsmodifizierers von 'public' in 'private' verhindert, dass
	// eine Instanz dieser Klasse erstellt werden kann.
	
	private Globals() {}
	
	
	public static boolean insertPLZOrt(long PrimaryKey, String PLZ, String Ort)
	{

		String SQL = "INSERT INTO POSTLEITZAHLEN ";
		SQL += "(PRIMARYKEY, PLZ, ORT) ";
		SQL += "VALUES (";
		SQL += Long.toString(PrimaryKey) + ", ";
		SQL += quote(PLZ) + ", ";
		SQL += quote(Ort) + ")";
		
		return DBConnection.executeNonQuery(SQL) > 0;
		
	}

	
	public static String quote(String value)
	{
		return "'" + value.replaceAll("'", "''") + "'";
	}
	
	
	public static long getNextKey()
	{
		long retValue = 0;
		
		String SQL = "SELECT MAX(PRIMARYKEY) FROM POSTLEITZAHLEN";
		Object obj = DBConnection.executeScalar(SQL);
		
		if (obj != null)
			retValue = (long)obj;
		
		return ++retValue;
		
	}
	
	public static boolean istPLZOrtVorhanden(String PLZ, String Ort)
	{
		
		boolean retValue = false;
		
		String SQL = "SELECT COUNT(*) FROM POSTLEITZAHLEN";
		SQL += " WHERE PLZ = " + quote(PLZ);
		SQL += " AND ORT = " + quote(Ort);
		
		Object obj = DBConnection.executeScalar(SQL);
		
		// Number ist eine Unterklasse aller Zahlenwerte und bietet Methoden zum expliziten Konvertieren in byte, double, 
		// float, int, long und short.
		
		if (obj != null)
			retValue = ((Number)obj).longValue() > 0;
			
		return retValue;
		
	}
	
	
	
	
}
