package W7T1_Dozent;

import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Globals
{

	static private HashMap<String, String> hmLAF = new HashMap<>();
	

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
	
	// Prepared Statements
	// Die SQL-Anweisungen, die mittels execute(), executeQuery() oder
	// executeUpdate() an die Datenbank gesendet werden,
	// haben bis zur Ausführung im Datenbanksystem einige Umwandlungen vor sich.
	// Zuerst müssen sie auf syntaktische Korrektheit getestet werden. Dann
	// werden sie in einen internen Ausführungsplan der Datenbank übersetzt und mit anderen Transaktionen optimal verzahnt.
	// Der Aufwand für jede Anweisung ist messbar. 
	// Deutlich besser wäre es jedoch, eine Art Vorübersetzung für SQL-Anweisungen zu nutzen.
	// Diese Vorübersetzung ist eine Eigenschaft, die JDBC unterstützt und die
	// sich Prepared Statements nennt.
	// Vorbereitet (engl. prepared) deshalb, weil die Anweisungen in einem
	// ersten Schritt zur Datenbank geschickt und dort in ein internes
	// Format umgesetzt werden. Später verweist ein Programm auf diese
	// vorübersetzten Anweisungen, und die Datenbank kann sie schnell ausführen,
	// da sie in einem optimalen Format vorliegen. Ein Geschwindigkeitsvorteil
	// macht sich immer dann besonders bemerkbar, wenn Schleifen Änderungen
	// an Tabellenspalten vornehmen. Dies kann durch die vorbereiteten
	// Anweisungen schneller geschehen.
	
	public static PreparedStatement prepareInsertPLZOrt()
	{
		
		String SQL = "INSERT INTO POSTLEITZAHLEN ";
		SQL += "(PRIMARYKEY, PLZ, ORT) ";
		SQL += "VALUES (?, ?, ?)";
		
		return DBConnection.prepareStatement(SQL);
		
	}
	
	public static PreparedStatement preparePLZOrtExists()
	{
		
		String SQL = "SELECT COUNT(*) FROM POSTLEITZAHLEN ";
		SQL += "WHERE PLZ = ? AND ORT = ?";
		
		return DBConnection.prepareStatement(SQL);
		
	}
	
	
	public static boolean insertPLZOrtPrepared(PreparedStatement prepStatement, long PrimaryKey, String PLZ, String Ort)
	{
		boolean retValue = false;
		
		try
		{
			
			prepStatement.setLong(1, PrimaryKey);
			prepStatement.setString(2, PLZ);
			prepStatement.setString(3, Ort);
			
			prepStatement.executeUpdate();
			retValue = true;
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
		return retValue;
	}
	
	public static boolean istPLZOrtVorhandenPrepared(PreparedStatement prepStatement, String PLZ, String Ort)
	{
		
		boolean retValue = false;
		
		try
		{
			prepStatement.setString(1, PLZ);
			prepStatement.setString(2, Ort);
			
			ResultSet rSet = prepStatement.executeQuery();
			rSet.next();
			Object obj = rSet.getObject(1);
			rSet.close();
			
			// Number ist eine Unterklasse aller Zahlenwerte und bietet Methoden zum expliziten Konvertieren in byte, double, 
			// float, int, long und short.
			
			if (obj != null)
				retValue = ((Number)obj).longValue() > 0;
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		return retValue;
		
	}
	
	
	public static int getLookAndFeels()
	{
		
		LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
		
		for (LookAndFeelInfo laf : lafInfo)
			hmLAF.put(laf.getName(), laf.getClassName());
		
		
		return hmLAF.size();
		
	}
	
	public static HashMap<String, String> getLAFTable()
	{
		return hmLAF;
	}
	
	public static String getSystemLookAndFeelName()
	{
		return UIManager.getLookAndFeel().getName();
	}
	
	public static void setLookAndFeel(String lafName, Component component)
	{
		
		if (!hmLAF.containsKey(lafName))
			return;
		
		
		String lafClassName = hmLAF.get(lafName);
		
		try
		{
			UIManager.setLookAndFeel(lafClassName);
			SwingUtilities.updateComponentTreeUI(component);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}
	
	
}
