package W6T1_Blaszczyk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBConnection
{
	private static Connection dbConn;
	private static String connectionString;
	
	private DBConnection()
	{
	}
	
	public static boolean connectToDataBase(String classForName, String connectionString, String userID, String password)
	{
		try
		{
			Class.forName(classForName).newInstance();
			dbConn = DriverManager.getConnection(connectionString,userID,password);
			dbConn.setAutoCommit(false);
			DBConnection.connectionString = connectionString;
			return true;
		}
		catch(Exception e)
		{
			dbConn = null;
			DBConnection.connectionString = null;
			e.printStackTrace();
		}
		return false;
	}

	public static int executeNonQuery(String command)
	{
		if(dbConn == null)
			return -1;
		try(Statement statement = dbConn.createStatement())
		{
			statement.execute(command);
			return 1;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB access error: " + e.getMessage(), "Error",	JOptionPane.ERROR_MESSAGE);
		}
		return 0;
	}
	
	public static long executeScalar(String command)
	{
		if(dbConn == null)
			return 0;
		try(Statement statement = dbConn.createStatement())
		{
			ResultSet rs =  statement.executeQuery(command);
			rs.next();
			Object o = rs.getObject(1);
			if( o instanceof Long )
				return (Long) o;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB access error: " + e.getMessage(), "Error",	JOptionPane.ERROR_MESSAGE);
		}
		return 0;
	}
	

	public static ResultSet executeQuery(String SQL)
	{
		if (dbConn == null)
			return null;
		try
		{
			Statement stmt = dbConn.createStatement();
			return stmt.executeQuery(SQL);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Fehler beim Zugriff auf die Datenbank", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	public static PreparedStatement preparedStatement(String sql)
	{
		if(dbConn == null)
			return null;
		try
		{
			return dbConn.prepareStatement(sql);
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(),  "Fehler beim vorbereiten der SQL Anweisung", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	
	public static void closeConnection()
	{
		if(dbConn == null)
			return;
		try
		{
			dbConn.close();
		}
		catch(Exception e)
		{
		}
		
		dbConn = null;
		connectionString = null;
	}
	
	public static String getCatalog()
	{
		try
		{
			return dbConn.getCatalog();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
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
