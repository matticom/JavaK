package W6T5_Dozent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	
	public static int executeNonQuery(String SQL)
	{
		
		Statement stmt;
		int retValue = 0;
		
		if (dbConn == null)
			return retValue;
		
		try
		{
			stmt = dbConn.createStatement();
			retValue = stmt.executeUpdate(SQL);
			stmt.close();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
					                      "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		return retValue;
	}
	
	
	public static Object executeScalar(String SQL)
	{
		Statement stmt;
		Object retValue = null;
		
		if (dbConn == null)
			return retValue;
		
		
		try
		{
			stmt = dbConn.createStatement();
			ResultSet rSet = stmt.executeQuery(SQL);
			// Liest den ersten Datensatz aus dem ResultSet
			rSet.next();
			
			// Die erste Saplte der ersten Zeile zum R�ckgabewert zuweisen.
			retValue = rSet.getObject(1);
			stmt.close();
			
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
					                      "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		return retValue;
		
	}
	
	public static ResultSet executeQuery(String SQL)
	{
		Statement stmt;
		ResultSet rSet = null;
		
		if (dbConn == null)
			return rSet;
		
		
		try
		{
			stmt = dbConn.createStatement();
			rSet = stmt.executeQuery(SQL);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
					                      "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		return rSet;
		
	}
	
	
	public static PreparedStatement prepareStatement(String SQL)
	{
		PreparedStatement prepStatement = null;
		
		if (dbConn == null)
			return prepStatement;
		
		try
		{
			prepStatement = dbConn.prepareStatement(SQL);	
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Vorbereiten der SQL-Anweisung: " + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		return prepStatement;
	}
	
	
	public static void beginTransaction()
	{
		
		if (dbConn == null)
			return;
		
		
		try
		{	
			dbConn.setAutoCommit(false);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	
	public static void commitTransaction()
	{
		
		if (dbConn == null)
			return;
		
		try
		{
			// Keine Transaktionsschleife ge�ffnet
			if (dbConn.getAutoCommit())
				return;
			
			dbConn.commit();
			dbConn.setAutoCommit(true);
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	public static void rollbackTransaction()
	{
		
		if (dbConn == null)
			return;
		
		try
		{
			// Keine Transaktionsschleife ge�ffnet
			if (dbConn.getAutoCommit())
				return;
			
			dbConn.rollback();
			dbConn.setAutoCommit(true);
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	
}
