package W6T1_Blaszczyk;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Globals
{
	private Globals(){}
	
	public static boolean insertPLZOrt(long primaryKey, String plz, String ort )
	{
		String sqlCommand = "INSERT INTO POSTLEITZAHLEN";
		sqlCommand += "(PRIMARYKEY, PLZ, ORT)";
		sqlCommand += String.format( "VALUES (%d, %s, %s)", primaryKey, quote(plz), quote(ort) );	
		return DBConnection.executeNonQuery(sqlCommand) > 0;
	}
	
	public static String quote(String value)
	{
		return "'" + value.replaceAll("'", "''") + "'";
	}

	public static boolean deletePLZEntries()
	{
		String sqlCommand = "DELETE FROM POSTLEITZAHLEN";
		return DBConnection.executeNonQuery(sqlCommand) > 0;
	}
	

	public static long getMaxPrimaryKey()
	{
		String command = "SELECT MAX(PRIMARYKEY) FROM POSTLEITZAHLEN";
		return DBConnection.executeScalar(command);
	}

	public static boolean istPLZOrtVorhanden(String PLZ, String Ort)
	{
		String SQL = "SELECT COUNT(*) FROM POSTLEITZAHLEN";
		SQL += " WHERE PLZ = " + quote(PLZ);
		SQL += " AND ORT = " + quote(Ort);
		Object obj = DBConnection.executeScalar(SQL);
		if (obj instanceof Number)
			return ((Number)obj).longValue() > 0;
		return false;
	}

	public static PreparedStatement prepareInsertPLZOrt()
	{
		String sqlCommand = "INSERT INTO POSTLEITZAHLEN ";
		sqlCommand += "(PRIMARYKEY, PLZ, ORT) ";
		sqlCommand += "VALUES (? , ?, ?) ";	
		return DBConnection.preparedStatement(sqlCommand);
	}
	
	public static PreparedStatement preparePLZOrtExists()
	{
		String sqlCommand = "SELECT COUNT(*) FROM POSTLEITZAHLEN ";
		sqlCommand += "WHERE PLZ = ? AND ORT = ? ";
		return DBConnection.preparedStatement(sqlCommand);
	}
	
	public static boolean insertPLZOrtPrepared(PreparedStatement prepStatement, long primaryKey, String PLZ, String Ort)
	{
		try
		{
			prepStatement.setLong(1, primaryKey);
			prepStatement.setString(2, PLZ);
			prepStatement.setString(3, Ort);
			prepStatement.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Datenbank Zugriff Fehler", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	public static boolean istPLZOrtVorhandenPrepared(PreparedStatement prepStatement, String PLZ, String Ort)
	{
		try
		{
			prepStatement.setString(1, PLZ);
			prepStatement.setString(2, Ort);
			ResultSet resultSet = prepStatement.executeQuery();
			resultSet.next();
			Object o = resultSet.getObject(1);
			if( o instanceof Number )
				return ((Number) o).longValue() > 0;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Datenbank Zugriff Fehler", JOptionPane.ERROR_MESSAGE);
		}
		return false;
		
	}
}
