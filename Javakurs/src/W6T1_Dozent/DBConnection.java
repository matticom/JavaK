package W6T1_Dozent;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DBConnection
{

	private static Connection dbConn;
	private static String connectionString;
	
	
	public static boolean connectToDatabase(String classForName, String connectionString, String userID, String passWord)
	{
		
		boolean retValue = false;
		
		try
		{
			// Dynamisches Laden und registrieren einer Klasse beim JDBC-Treibermanager 
			// (statische Initialisierung).
			Class.forName(classForName).newInstance();
			
			dbConn = DriverManager.getConnection(connectionString, userID, passWord);
			DBConnection.connectionString = connectionString;
			
			retValue = true;
			
		}
		catch (Exception ex)
		{
			dbConn = null;
			DBConnection.connectionString = null;
			
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
					                      "Fehler", JOptionPane.ERROR_MESSAGE );
			
		}
		
		return retValue;
	}
	
	
	public static void closeConnection()
	{
		if (dbConn == null)
			return;
		
		try
		{
			dbConn.close();
		}
		catch (Exception ex)
		{}
		
		dbConn = null;
		connectionString = null;
		
	}
	
	public static String getCatalog()
	{
		
		String retValue = "";
		
		if (dbConn == null)
			return retValue;
		
		try
		{
			retValue = dbConn.getCatalog();
		}
		catch (Exception ex)
		{}
		
		return retValue;
	}


	public static Connection getConnection()
	{
		return dbConn;
	}


	public static String getConnectionString()
	{
		return connectionString;
	}
	
	
}
