package Abschlussprojekt;

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
	
	/**
	 * <li><b><i>connectToDatabase</i></b> <br>
	 * <br>
	 * public static boolean connectToDatabase(String classForName, String connectionString, String userID, String passWord) <br>
	 * <br>
	 * Baut eine Verbindung zur Datenbank auf <br>
	 * <br>
	 * 
	 * @param classForName
	 *            - Klassenname des DB Treibers
	 *            
	 * @param connectionString
	 *            - Zeichenkette mit Verbindungsdaten
	 *            
	 * @param userID
	 *            - UserID
	 *            
	 * @param passWord
	 *            - Passwort
	 *            
	 * @return boolean
	 * 			  - Konnt Verbindung erstellt werden
	 */
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
	
	/**
	 * <li><b><i>closeConnection</i></b> <br>
	 * <br>
	 * public static void closeConnection() <br>
	 * <br>
	 * Schließt aufgebaute Verbindung zur Datenbank <br>
	 * <br>
	 *   
	 * @return boolean
	 *     		  - Konnt Verbindung geschlossen werden
	 */
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
	
	/**
	 * <li><b><i>getCatalog</i></b> <br>
	 * <br>
	 * public static String getCatalog() <br>
	 * <br>
	 * Initialisiert catalog für Datenbank<br>
	 * <br>
	 * 
	 * @return String
	 *     		  - Zeichenkette für den Catalog   
	 */
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

	/**
	 * <li><b><i>getConnection</i></b> <br>
	 * <br>
	 * public static Connection getConnection() <br>
	 * <br>
	 * Gibt die Verbindung zur Datenbank zurück<br>
	 * <br>
	 * 
	 * @return Connection
	 *     		  - Verbindung zur Datenbank     
	 */
	public static Connection getConnection()
	{
		return dbConn;
	}

	
	/**
	 * <li><b><i>getConnectionString</i></b> <br>
	 * <br>
	 * public static String getConnectionString() <br>
	 * <br>
	 * Gibt die Verbindungzeichenkette zurück<br>
	 * <br>
	 * 
	 * @return Connection
	 *     		  - Verbindungzeichenkette zur Datenbank     
	 */
	public static String getConnectionString()
	{
		return connectionString;
	}
	
	/**
	 * <li><b><i>executeNonQuery</i></b> <br>
	 * <br>
	 * public static int executeNonQuery(String SQL) <br>
	 * <br>
	 * Führt einen Non Excute Query Abfrage aus<br>
	 * <br>
	 *
	 * @param SQL
	 *            - SQL Befehl
	 * 
	 * @return int
	 *     		  - Anzahl der Änderungen an der Datenbank     
	 */
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
	
	/**
	 * <li><b><i>executeScalar</i></b> <br>
	 * <br>
	 * public static Object executeScalar(String SQL) <br>
	 * <br>
	 * Führt eine scalare Execute Query Abfrage aus<br>
	 * <br>
	 *
	 * @param SQL
	 *            - SQL Befehl
	 * 
	 * @return Object
	 *     		  - Skalares Ergebnis der Anfrage als Object     
	 */
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
			
			// Die erste Saplte der ersten Zeile zum Rückgabewert zuweisen.
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
	
	
	/**
	 * <li><b><i>executeQuery</i></b> <br>
	 * <br>
	 * public static ResultSet executeQuery(String SQL) <br>
	 * <br>
	 * Führt eine Execute Query Abfrage aus<br>
	 * <br>
	 *
	 * @param SQL
	 *            - SQL Befehl
	 * 
	 * @return ResultSet
	 *     		  - Ergebnis der Anfrage als ResultSet     
	 */
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
	
	/**
	 * <li><b><i>prepareStatement</i></b> <br>
	 * <br>
	 * public static PreparedStatement prepareStatement(String SQL) <br>
	 * <br>
	 * Erstellt ein preparedstatement aus SQL Befehl<br>
	 * <br>
	 *
	 * @param SQL
	 *            - SQL Befehl
	 * 
	 * @return PreparedStatement
	 *     		  - Prepared SQL Statement     
	 */
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
	
	/**
	 * <li><b><i>beginTransaction</i></b> <br>
	 * <br>
	 * public static void beginTransaction() <br>
	 * <br>
	 * Beginnt eine Transaktionsschleife<br>
	 * <br>
     *
     */
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
	
	
	/**
	 * <li><b><i>commitTransaction</i></b> <br>
	 * <br>
	 * public static void commitTransaction() <br>
	 * <br>
	 * Beendet Transaktionsschleife und übergibt Anfragen endgültig an die Datenbank<br>
	 * <br>
     *
     */
	public static void commitTransaction()
	{
		
		if (dbConn == null)
			return;
		
		try
		{
			// Keine Transaktionsschleife geöffnet
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
	
	/**
	 * <li><b><i>rollbackTransaction</i></b> <br>
	 * <br>
	 * public static void rollbackTransaction() <br>
	 * <br>
	 * Beendet Transaktionsschleife und verwürft alle Anfragen an die Datenbank<br>
	 * <br>
     *
     */
	public static void rollbackTransaction()
	{
		
		if (dbConn == null)
			return;
		
		try
		{
			// Keine Transaktionsschleife geöffnet
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
