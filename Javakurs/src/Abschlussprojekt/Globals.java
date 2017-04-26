package Abschlussprojekt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class Globals
{

	// Privater Standardkonstruktor.
	// Alle Methoden dieser Klasse sind statisch. Durch die Deklaration eines
	// eigenen Standardkonstruktors wird verhindert, dass Java einen Standardkonstruktor
	// erstellt.
	// Die Änderung des Zugriffsmodifizierers von 'public' in 'private' verhindert, dass
	// eine Instanz dieser Klasse erstellt werden kann.

	private Globals()
	{
	}
	
	/**
	 * <li><b><i>getFachgebieteDE</i></b> <br>
	 * <br>
	 * public static ResultSet getFachgebieteDE() <br>
	 * <br>
	 * Gibt alle deutschen Fachgebiete aus Tabelle Fachgebiete als Resultset aus<br>
	 * <br>
	 *
	 * @return ResultSet
	 *     		  - alle deutschen Fachgebiete als ResultSet     
	 */
	public static ResultSet getFachgebieteDE()
	{
		String SQL = "SELECT FACHGEBIET FROM FACHGEBIETE";
				
		return DBConnection.executeQuery(SQL);
	}
	
	/**
	 * <li><b><i>getFachgebieteES</i></b> <br>
	 * <br>
	 * public static ResultSet getFachgebieteES() <br>
	 * <br>
	 * Gibt alle spanischen Fachgebiete aus Tabelle Fachgebiete als Resultset aus<br>
	 * <br>
	 *
	 * @return ResultSet
	 *     		  - alle spanischen Fachgebiete als ResultSet     
	 */
	public static ResultSet getFachgebieteES()
	{
		String SQL = "SELECT ESPECIALIDAD FROM FACHGEBIETE";
				
		return DBConnection.executeQuery(SQL);
	}

	/**
	 * <li><b><i>insertFachgebiete</i></b> <br>
	 * <br>
	 * public static boolean insertFachgebiete(String Fachgebiet, String Especialidad) <br>
	 * <br>
	 * Fügt einen neuen Datensatz bestehend aus deutsches und spanisches Fachgebiet<br>
	 * in die Tabelle Fachgebiete<br>
	 *
	 * @param Fachgebiet
	 *            - deutsches Fachgebiet
	 *            
	 * @param Especialidad
	 *            - spanisches Fachgebiet
	 *
	 * @return boolean
	 *     		  - Wurde das Einfügen durchgeführt?     
	 */
	public static boolean insertFachgebiete(String Fachgebiet, String Especialidad)
	{
		
		String SQL = "INSERT INTO FACHGEBIETE ";
		SQL += "(FACHGEBIET, ESPECIALIDAD) ";
		SQL += "VALUES ( ";
		SQL += quote(Fachgebiet) + ", ";
		SQL += quote(Especialidad) + ")";

		return DBConnection.executeNonQuery(SQL) > 0;

	}
	
	/**
	 * <li><b><i>insertLexikon</i></b> <br>
	 * <br>
	 * public static boolean insertLexikon(String Begriff, String Termino, int Fachgebiet, String Inhalt, String Contenido) <br>
	 * <br>
	 * Fügt einen neuen Datensatz bestehend aus deutschen und spanischen Begriff und Textfeldinhalt sowie Schlüssel<br>
	 * für das Fachgebiet in die Tabelle Lexikon<br>
	 *
	 * @param Begriff
	 *            - deutscher Begriff
	 *            
	 * @param Termino
	 *            - spanischer Begriff
	 *            
	 * @param Fachgebiet
	 *            - Schlüssel für das Fachgebiet
	 *            
	 * @param Inhalt
	 *            - spanisches Textfeldinhalt
	 *            
	 * @param Contenido
	 *            - deutsches Textfeldinhalt
	 *            
	 * @return boolean
	 *     		  - Wurde das Einfügen durchgeführt?     
	 */
	public static boolean insertLexikon(String Begriff, String Termino, int Fachgebiet, String Inhalt, String Contenido)
	{
		
		String Begriff_Normal = convertSpecialChar(Begriff);
		String Termino_Normal = convertSpecialChar(Termino);
		
		String SQL = "INSERT INTO LEXIKON ";
		SQL += "(BEGRIFF, TERMINO, FACHGEBIET, INHALT, CONTENIDO, BEGRIFF_NORMAL, TERMINO_NORMAL) ";
		SQL += "VALUES ( ";
		SQL += quote(Begriff) + ", ";
		SQL += quote(Termino) + ", ";
		SQL += Fachgebiet + ", ";
		SQL += quote(Inhalt) + ", ";
		SQL += quote(Contenido) + ", ";
		SQL += quote(Begriff_Normal) + ", ";
		SQL += quote(Termino_Normal) + ")";

		return DBConnection.executeNonQuery(SQL) > 0;

	}

	/**
	 * <li><b><i>insertHistory</i></b> <br>
	 * <br>
	 * public static boolean insertHistory(String Suchworte) <br>
	 * <br>
	 * Fügt ein neues Suchwort in die Tabelle History<br>
	 * <br>
	 *
	 * @param Suchworte
	 *            - Suchbegriff
	 *            
	 * @return boolean
	 *     		  - Wurde das Einfügen durchgeführt?     
	 */
	public static boolean insertHistory(String Suchworte)
	{
		
		String SQL = "INSERT INTO HISTORY ";
		SQL += "(SUCHWORTE) ";
		SQL += "VALUES (";
		SQL += quote(Suchworte) + ")";

		return DBConnection.executeNonQuery(SQL) > 0;

	}

	/**
	 * <li><b><i>updateFachgebiete</i></b> <br>
	 * <br>
	 * public static boolean updateFachgebiete(int PRIMARYKEY, String Fachgebiet, String Especialidad) <br>
	 * <br>
	 * Aktualisiert einen Datensatz bestehend aus Primärschlüssel deutschen und spanischen Fachbegiet<br>
	 * aus Tabelle Fachgebiete <br>
	 *
	 * @param PRIMARYKEY
	 *            - Primärschlüssel
	 *            
	 * @param Fachgebiet
	 *            - deutsches Fachgebiet
	 *            
	 * @param Especialidad
	 *            - spanisches Fachgebiet
	 *
	 * @return boolean
	 *     		  - Wurde das Update durchgeführt?     
	 */
	public static boolean updateFachgebiete(int PRIMARYKEY, String Fachgebiet, String Especialidad)
	{
		String SQL = "UPDATE FACHGEBIETE ";
		SQL += "SET FACHGEBIET = " + quote(Fachgebiet) + ", ";
		SQL += "ESPECIALIDAD = " + quote(Especialidad);
		SQL += " WHERE PRIMARYKEY = " + Integer.toString(PRIMARYKEY);

		return DBConnection.executeNonQuery(SQL) > 0;
	}
	
	/**
	 * <li><b><i>updateTermTextDE</i></b> <br>
	 * <br>
	 * public static boolean updateTermTextDE(String Begriff, String Inhalt, String Contenido) <br>
	 * <br>
	 * Aktualisiert einen Datensatz bestehend aus deutschen und spanischen Textfeldinhalt <br>
	 * in der Tabelle Lexikon wo der deutsche Begriff übereinstimmt <br>
	 *
	 * @param Begriff
	 *            - deutscher Begriff
	 *            
	 * @param Inhalt
	 *            - deutsches Fachgebiet
	 *            
	 * @param Contenido
	 *            - spanisches Fachgebiet
	 *
	 * @return boolean
	 *     		  - Wurde das Update durchgeführt?     
	 */
	public static boolean updateTermTextDE(String Begriff, String Inhalt, String Contenido)
	{
		String SQL = "UPDATE LEXIKON ";
		SQL += "SET INHALT = " + quote(Inhalt) + ", ";
		SQL += "CONTENIDO = " + quote(Contenido);
		SQL += " WHERE BEGRIFF = " + quote(Begriff);

		return DBConnection.executeNonQuery(SQL) > 0;
	}
	
	/**
	 * <li><b><i>updateTermTextES</i></b> <br>
	 * <br>
	 * public static boolean updateTermTextES(String Termino, String Contenido, String Inhalt) <br>
	 * <br>
	 * Aktualisiert einen Datensatz bestehend aus deutschen und spanischen Textfeldinhalt <br>
	 * in der Tabelle Lexikon wo der spanische Begriff übereinstimmt <br>
	 *
	 * @param Termino
	 *            - spanischer Begriff
	 *            
	 * @param Contenido
	 *            - spanisches Fachgebiet
	 *            
	 * @param Inhalt
	 *            - deutsches Fachgebiet
	 *
	 * @return boolean
	 *     		  - Wurde das Update durchgeführt?     
	 */
	public static boolean updateTermTextES(String Termino, String Contenido, String Inhalt)
	{
		String SQL = "UPDATE LEXIKON ";
		SQL += "SET CONTENIDO = " + quote(Contenido) + ", ";
		SQL += "INHALT = " + quote(Inhalt);
		SQL += " WHERE TERMINO = " + quote(Termino);

		return DBConnection.executeNonQuery(SQL) > 0;
	}
	
	/**
	 * <li><b><i>quote</i></b> <br>
	 * <br>
	 * public static String quote(String value) <br>
	 * <br>
	 * Fügt ' am Anfang und Ende der Zeichenkette und ersetzt auftretende '  <br>
	 * durch '' <br>
	 *
	 * @param value
	 *            - zu bearbeitende Zeichenkette
	 *
	 * @return String
	 *     		  - Bearbeitete Zeichenkette     
	 */
	public static String quote(String value)
	{
		return "'" + value.replaceAll("'", "''") + "'";
	}

	/**
	 * <li><b><i>istFachgebietVorhandenDE</i></b> <br>
	 * <br>
	 * public static boolean istFachgebietVorhandenDE(String Fachgebiet) <br>
	 * <br>
	 * Ermittelt ob das deutsche Fachgebiet in der Tabelle Fachgebiete vorhanden ist<br>
	 * <br>
	 *
	 * @param Fachgebiet
	 *            - deutsches Fachgebiet
	 *            
	 * @return boolean
	 *     		  - Ist das Fachgebiet vorhanden?     
	 */
	public static boolean istFachgebietVorhandenDE(String Fachgebiet)
	{

		boolean retValue = false;

		String SQL = "SELECT COUNT(*) FROM FACHGEBIETE";
		SQL += " WHERE FACHGEBIET = " + quote(Fachgebiet);
		
		Object obj = DBConnection.executeScalar(SQL);

		// Number ist eine Unterklasse aller Zahlenwerte und bietet Methoden zum expliziten Konvertieren in byte, double,
		// float, int, long und short.

		if (obj != null)
			retValue = ((Number) obj).longValue() > 0;

		return retValue;

	}
	
	/**
	 * <li><b><i>istFachgebietVorhandenES</i></b> <br>
	 * <br>
	 * public static boolean istFachgebietVorhandenES(String Especialidad) <br>
	 * <br>
	 * Ermittelt ob das spanische Fachgebiet in der Tabelle Fachgebiete vorhanden ist<br>
	 * <br>
	 *
	 * @param Fachgebiet
	 *            - spanisches Fachgebiet
	 *            
	 * @return boolean
	 *     		  - Ist das Fachgebiet vorhanden?     
	 */
	public static boolean istFachgebietVorhandenES(String Especialidad)
	{

		boolean retValue = false;

		String SQL = "SELECT COUNT(*) FROM FACHGEBIETE";
		SQL += " WHERE ESPECIALIDAD = " + quote(Especialidad);
	
		Object obj = DBConnection.executeScalar(SQL);

		// Number ist eine Unterklasse aller Zahlenwerte und bietet Methoden zum expliziten Konvertieren in byte, double,
		// float, int, long und short.

		if (obj != null)
			retValue = ((Number) obj).longValue() > 0;

		return retValue;

	}

	/**
	 * <li><b><i>istLexikonEintragVorhanden</i></b> <br>
	 * <br>
	 * public static boolean istLexikonEintragVorhanden(String Begriff) <br>
	 * <br>
	 * Ermittelt ob der deutsche Begriff in der Tabelle Lexikon vorhanden ist<br>
	 * <br>
	 *
	 * @param Begriff
	 *            - deutscher Begriff
	 *            
	 * @return boolean
	 *     		  - Ist der Begriff vorhanden?     
	 */
	public static boolean istLexikonEintragVorhanden(String Begriff)
	{

		boolean retValue = false;

		String SQL = "SELECT COUNT(*) FROM LEXIKON";
		SQL += " WHERE BEGRIFF = " + quote(Begriff);
		
		Object obj = DBConnection.executeScalar(SQL);

		// Number ist eine Unterklasse aller Zahlenwerte und bietet Methoden zum expliziten Konvertieren in byte, double,
		// float, int, long und short.

		if (obj != null)
			retValue = ((Number) obj).longValue() > 0;

		return retValue;

	}
	
	/**
	 * <li><b><i>istBuchstabeVorhandenDE</i></b> <br>
	 * <br>
	 * public static boolean istBuchstabeVorhandenDE(String Begriff) <br>
	 * <br>
	 * Prüft ob ein deutscher Begriff mit dem Anfangsbuchstaben in <br>
	 * Tabelle Lexikon vorhanden ist<br>
	 *
	 * @param Begriff
	 *            - deutscher Begriff [zb %a]
	 *            
	 * @return boolean
	 *     		  - Ist ein deutscher Begriff mit dem Anfangsbuchstaben vorhanden?     
	 */
	public static boolean istBuchstabeVorhandenDE(String Begriff)
	{

		boolean retValue = false;

		String SQL = "SELECT COUNT(BEGRIFF) FROM LEXIKON";
		SQL += " WHERE BEGRIFF LIKE " + quote(Begriff);
		
		Object obj = DBConnection.executeScalar(SQL);

		// Number ist eine Unterklasse aller Zahlenwerte und bietet Methoden zum expliziten Konvertieren in byte, double,
		// float, int, long und short.

		if (obj != null)
			retValue = ((Number) obj).longValue() > 0;

		return retValue;

	}
	
	/**
	 * <li><b><i>istBuchstabeVorhandenES</i></b> <br>
	 * <br>
	 * public static boolean istBuchstabeVorhandenES(String Begriff) <br>
	 * <br>
	 * Prüft ob ein spanischer Begriff mit dem Anfangsbuchstaben in <br>
	 * Tabelle Lexikon vorhanden ist<br>
	 *
	 * @param Begriff
	 *            - spanischer Begriff [zb %a]
	 *            
	 * @return boolean
	 *     		  - Ist ein spanischer Begriff mit dem Anfangsbuchstaben vorhanden?     
	 */
	public static boolean istBuchstabeVorhandenES(String Begriff)
	{

		boolean retValue = false;

		String SQL = "SELECT COUNT(TERMINO) FROM LEXIKON";
		SQL += " WHERE TERMINO LIKE " + quote(Begriff);
		
		Object obj = DBConnection.executeScalar(SQL);

		// Number ist eine Unterklasse aller Zahlenwerte und bietet Methoden zum expliziten Konvertieren in byte, double,
		// float, int, long und short.

		if (obj != null)
			retValue = ((Number) obj).longValue() > 0;

		return retValue;

	}
	
	
	/**
	 * <li><b><i>getPrimaryKeyFachgebietDE</i></b> <br>
	 * <br>
	 * public static int getPrimaryKeyFachgebietDE(String Fachgebiet) <br>
	 * <br>
	 * Gibt den Primärschlüssel eines deutschen Fachgebiets in der Tabelle Fachgebiete zurück <br>
	 * <br>
	 *
	 * @param Fachgebiet
	 *            - deutsches Fachgebiet
	 *            
	 * @return int
	 *     		  - Primärschlüssel     
	 */
	public static int getPrimaryKeyFachgebietDE(String Fachgebiet)
	{

		int retValue = -1;

		String SQL = "SELECT PRIMARYKEY FROM FACHGEBIETE";
		SQL += " WHERE FACHGEBIET = " + quote(Fachgebiet);
		
		Object obj = DBConnection.executeScalar(SQL);

		// Number ist eine Unterklasse aller Zahlenwerte und bietet Methoden zum expliziten Konvertieren in byte, double,
		// float, int, long und short.

		if (obj != null)
			retValue = ((Number) obj).intValue();

		return retValue;
	}
	
	/**
	 * <li><b><i>getPrimaryKeyFachgebietES</i></b> <br>
	 * <br>
	 * public static int getPrimaryKeyFachgebietES(String Especialidad) <br>
	 * <br>
	 * Gibt den Primärschlüssel eines spanischen Fachgebiets in der Tabelle Fachgebiete zurück <br>
	 * <br>
	 *
	 * @param Especialidad
	 *            - spanisches Fachgebiet
	 *            
	 * @return int
	 *     		  - Primärschlüssel     
	 */
	public static int getPrimaryKeyFachgebietES(String Especialidad)
	{

		int retValue = -1;

		String SQL = "SELECT PRIMARYKEY FROM FACHGEBIETE";
		SQL += " WHERE ESPECIALIDAD = " + quote(Especialidad);
		
		Object obj = DBConnection.executeScalar(SQL);

		// Number ist eine Unterklasse aller Zahlenwerte und bietet Methoden zum expliziten Konvertieren in byte, double,
		// float, int, long und short.

		if (obj != null)
			retValue = ((Number) obj).intValue();

		return retValue;
	}
	
	
	/**
	 * <li><b><i>deleteHistory</i></b> <br>
	 * <br>
	 * public static void deleteHistory() <br>
	 * <br>
	 * Löscht alle Datensätze aus Tabelle History <br>
	 * <br>
	 *  
	 */
	public static void deleteHistory()
	{
		
		String SQL = "TRUNCATE TABLE HISTORY";
		DBConnection.executeNonQuery(SQL);
		
	}
	
	/**
	 * <li><b><i>searchDE</i></b> <br>
	 * <br>
	 * public static ResultSet searchDE(String searchTerm) <br>
	 * <br>
	 * Gibt komplette Datensatz der beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo der Suchbegriff mit dem deutschen oder spanischen Begriff übereinstimmt<br>
	 * (spracheigene Umlaute werden, wenn nötig, ersetzt)<br>
	 *
	 * @param searchTerm
	 *            - Suchbegriff
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden Datensätze     
	 */
	public static ResultSet searchDE(String searchTerm)
	{
		
		String SQL = "SELECT BEGRIFF, fachgebiete.FACHGEBIET, TERMINO, ESPECIALIDAD FROM LEXIKON, FACHGEBIETE WHERE";
		SQL += " lexikon.FACHGEBIET = fachgebiete.PRIMARYKEY ";
		SQL += "AND (BEGRIFF LIKE " + quote(searchTerm);
		SQL += " OR BEGRIFF_NORMAL LIKE " + quote(searchTerm);
		SQL += ") ORDER BY BEGRIFF ASC";
		
		return DBConnection.executeQuery(SQL);

	}
	
	/**
	 * <li><b><i>searchES</i></b> <br>
	 * <br>
	 * public static ResultSet searchES(String searchTerm) <br>
	 * <br>
	 * Gibt komplette Datensatz der beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo der Suchbegriff mit dem deutschen oder spanischen Begriff übereinstimmt<br>
	 * (spracheigene Umlaute werden, wenn nötig, ersetzt)<br>
	 *
	 * @param searchTerm
	 *            - Suchbegriff
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden Datensätze     
	 */
	public static ResultSet searchES(String searchTerm)
	{
				
		String SQL = "SELECT TERMINO, ESPECIALIDAD, BEGRIFF, fachgebiete.FACHGEBIET FROM LEXIKON, FACHGEBIETE WHERE";
		SQL += " lexikon.FACHGEBIET = fachgebiete.PRIMARYKEY ";
		SQL += "AND (TERMINO LIKE " + quote(searchTerm);
		SQL += " OR TERMINO_NORMAL LIKE " + quote(searchTerm);
		SQL += ") ORDER BY BEGRIFF ASC";
		
		return DBConnection.executeQuery(SQL);

	}
	
	/**
	 * <li><b><i>searchDELang2</i></b> <br>
	 * <br>
	 * public static ResultSet searchDELang2(String searchTerm) <br>
	 * <br>
	 * Gibt komplette Datensatz der beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo der Suchbegriff mit dem deutschen oder spanischen Begriff übereinstimmt<br>
	 * (spracheigene Umlaute werden, wenn nötig, ersetzt)<br>
	 *
	 * @param searchTerm
	 *            - Suchbegriff
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden Datensätze     
	 */
	public static ResultSet searchDELang2(String searchTerm)
	{
		
		String SQL = "SELECT BEGRIFF, fachgebiete.FACHGEBIET, TERMINO, ESPECIALIDAD FROM LEXIKON, FACHGEBIETE WHERE";
		SQL += " lexikon.FACHGEBIET = fachgebiete.PRIMARYKEY ";
		SQL += "AND (TERMINO LIKE " + quote(searchTerm);
		SQL += " OR TERMINO_NORMAL LIKE " + quote(searchTerm);
		SQL += ") ORDER BY BEGRIFF ASC";
		
		return DBConnection.executeQuery(SQL);

	}
	
	/**
	 * <li><b><i>searchESLang2</i></b> <br>
	 * <br>
	 * public static ResultSet searchESLang2(String searchTerm) <br>
	 * <br>
	 * Gibt komplette Datensatz der beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo der Suchbegriff mit dem deutschen oder spanischen Begriff übereinstimmt<br>
	 * (spracheigene Umlaute werden, wenn nötig, ersetzt)<br>
	 *
	 * @param searchTerm
	 *            - Suchbegriff
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden Datensätze     
	 */
	public static ResultSet searchESLang2(String searchTerm)
	{
				
		String SQL = "SELECT TERMINO, ESPECIALIDAD, BEGRIFF, fachgebiete.FACHGEBIET FROM LEXIKON, FACHGEBIETE WHERE";
		SQL += " lexikon.FACHGEBIET = fachgebiete.PRIMARYKEY ";
		SQL += "AND (BEGRIFF LIKE " + quote(searchTerm);
		SQL += " OR BEGRIFF_NORMAL LIKE " + quote(searchTerm);
		SQL += ") ORDER BY BEGRIFF ASC";
		
		return DBConnection.executeQuery(SQL);

	}

	/**
	 * <li><b><i>getTermDE</i></b> <br>
	 * <br>
	 * public static ResultSet getTermDE(String term) <br>
	 * <br>
	 * Gibt komplette Datensatz der beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo der Suchbegriff mit dem deutschen Begriff übereinstimmt<br>
	 *
	 * @param term
	 *            - Ausgewählter deutscher Begriff [Begriff ist aus Tabelle]
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden Datensätze     
	 */
	public static ResultSet getTermDE(String term)
	{
					
		String SQL = "SELECT BEGRIFF, TERMINO, fachgebiete.FACHGEBIET, ESPECIALIDAD, INHALT, CONTENIDO FROM LEXIKON, FACHGEBIETE WHERE";
		SQL += " LEXIKON.FACHGEBIET = FACHGEBIETE.PRIMARYKEY ";
		SQL += "AND BEGRIFF = " + quote(term);
				
		return DBConnection.executeQuery(SQL);

	}
	
	/**
	 * <li><b><i>getTermES</i></b> <br>
	 * <br>
	 * public static ResultSet getTermES(String term) <br>
	 * <br>
	 * Gibt komplette Datensatz der beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo der Suchbegriff mit dem spanischen Begriff übereinstimmt<br>
	 *
	 * @param term
	 *            - Ausgewählter spanischer Begriff [Begriff ist aus Tabelle]
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden Datensätze     
	 */
	public static ResultSet getTermES(String term)
	{
			
		String SQL = "SELECT TERMINO, BEGRIFF, ESPECIALIDAD, fachgebiete.FACHGEBIET, CONTENIDO, INHALT FROM LEXIKON, FACHGEBIETE WHERE";
		SQL += " LEXIKON.FACHGEBIET = FACHGEBIETE.PRIMARYKEY ";
		SQL += "AND TERMINO = " + quote(term);
				
		return DBConnection.executeQuery(SQL);

	}
	
	/**
	 * <li><b><i>getSubjectTermsDE</i></b> <br>
	 * <br>
	 * public static ResultSet getSubjectTermsDE(String Subject) <br>
	 * <br>
	 * Gibt alle deutschen Begriffe aufsteigend sortiert aus den beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo das Fachgebiet mit dem deutschen Suchfachbegebiet übereinstimmt<br>
	 *
	 * @param Subject
	 *            - deutsches Fachgebiet
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden deutschen Begriffe     
	 */
	public static ResultSet getSubjectTermsDE(String Subject)
	{
		
		String SQL = "SELECT BEGRIFF FROM LEXIKON, FACHGEBIETE WHERE";
		SQL += " LEXIKON.FACHGEBIET = FACHGEBIETE.PRIMARYKEY ";
		SQL += "AND fachgebiete.FACHGEBIET = " + quote(Subject);
		SQL += " ORDER BY BEGRIFF ASC";
				
		return DBConnection.executeQuery(SQL);

	}
	
	/**
	 * <li><b><i>getSubjectTermsES</i></b> <br>
	 * <br>
	 * public static ResultSet getSubjectTermsES(String Subject) <br>
	 * <br>
	 * Gibt alle spanischen Begriffe aufsteigend sortiert aus den beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo das Fachgebiet mit dem spanischen Suchfachbegebiet übereinstimmt<br>
	 *
	 * @param Subject
	 *            - spanisches Fachgebiet
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden spanischen Begriffe     
	 */
	public static ResultSet getSubjectTermsES(String Subject)
	{
			
		String SQL = "SELECT TERMINO FROM LEXIKON, FACHGEBIETE WHERE";
		SQL += " LEXIKON.FACHGEBIET = FACHGEBIETE.PRIMARYKEY ";
		SQL += "AND ESPECIALIDAD = " + quote(Subject);
		SQL += " ORDER BY TERMINO ASC";
				
		return DBConnection.executeQuery(SQL);

	}
	
	/**
	 * <li><b><i>getLetterTermsDE</i></b> <br>
	 * <br>
	 * public static ResultSet getLetterTermsDE(String Letter) <br>
	 * <br>
	 * Gibt alle deutschen Begriffe und Fachgebiete (aufsteigend sortiert nach Begriffen)<br>
	 * aus den beiden verknüpften Tabellen Lexikon und Fachgebiete<br>
	 * zurück, wo der Buchstabe mit dem Anfangsbuchstaben der deutschen Begriffe übereinstimmt<br>
	 *
	 * @param Letter
	 *            - Anfangsbuchstabe [a%]
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefundenen deutschen Begriffe     
	 */
	public static ResultSet getLetterTermsDE(String Letter)
	{
		
		String SQL = "SELECT BEGRIFF, fachgebiete.FACHGEBIET FROM LEXIKON, FACHGEBIETE WHERE";
		SQL += " LEXIKON.FACHGEBIET = FACHGEBIETE.PRIMARYKEY ";
		SQL += "AND BEGRIFF LIKE " + quote(Letter);
		SQL += " ORDER BY BEGRIFF ASC";
				
		return DBConnection.executeQuery(SQL);

	}
	
	/**
	 * <li><b><i>getLetterTermsES</i></b> <br>
	 * <br>
	 * public static ResultSet getLetterTermsES(String Letter) <br>
	 * <br>
	 * Gibt alle spanischen Begriffe und Fachgebiete (aufsteigend sortiert nach Begriffen)<br>
	 * aus den beiden verknüpften Tabellen Lexikon und Fachgebiete<br>
	 * zurück, wo der Buchstabe mit dem Anfangsbuchstaben der spanischen Begriffe übereinstimmt<br>
	 *
	 * @param Letter
	 *            - Anfangsbuchstabe [a%]
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefundenen spanischen Begriffe     
	 */
	public static ResultSet getLetterTermsES(String Letter)
	{
			
		String SQL = "SELECT TERMINO, ESPECIALIDAD FROM LEXIKON, FACHGEBIETE WHERE";
		SQL += " LEXIKON.FACHGEBIET = FACHGEBIETE.PRIMARYKEY ";
		SQL += "AND TERMINO LIKE " + quote(Letter);
		SQL += " ORDER BY BEGRIFF ASC";
				
		return DBConnection.executeQuery(SQL);

	}
	
	/**
	 * <li><b><i>getSubjectEsToDe</i></b> <br>
	 * <br>
	 * public static String getSubjectEsToDe(String Especialidad) <br>
	 * <br>
	 * Suche aus Tabelle Fachgebiete entsprechend des spanischen Fachgebiets<br>
	 * das zugeordnete deutsche Fachgebiet heraus<br>
	 *
	 * @param Especialidad
	 *            - spanisches Fachgebiet
	 *            
	 * @return String
	 *     		  - übersetztes deutsches Fachgebiet     
	 */	
	public static String getSubjectEsToDe(String Especialidad)
	{

		String retValue = "";

		String SQL = "SELECT FACHGEBIET FROM FACHGEBIETE";
		SQL += " WHERE ESPECIALIDAD = " + quote(Especialidad);
		
		Object obj = DBConnection.executeScalar(SQL);

		if (obj != null)
			retValue = ((String) obj);

		return retValue;
	}
	
	/**
	 * <li><b><i>getSubjectDeToEs</i></b> <br>
	 * <br>
	 * public static String getSubjectDeToEs(String Fachgebiet) <br>
	 * <br>
	 * Suche aus Tabelle Fachgebiete entsprechend des deutschen Fachgebiets<br>
	 * das zugeordnete spanische Fachgebiet heraus<br>
	 *
	 * @param Especialidad
	 *            - deutsches Fachgebiet
	 *            
	 * @return String
	 *     		  - übersetztes spanisches Fachgebiet     
	 */	
	public static String getSubjectDeToEs(String Fachgebiet)
	{

		String retValue = "";

		String SQL = "SELECT ESPECIALIDAD FROM FACHGEBIETE";
		SQL += " WHERE FACHGEBIET = " + quote(Fachgebiet);
		
		Object obj = DBConnection.executeScalar(SQL);

		if (obj != null)
			retValue = ((String) obj);

		return retValue;
	}
	
	/**
	 * <li><b><i>getTermEsToDe</i></b> <br>
	 * <br>
	 * public static String getTermEsToDe(String Termino) <br>
	 * <br>
	 * Suche aus Tabelle Lexikon entsprechend des spanischen Begriffs<br>
	 * den zugeordneten deutschen Begriff heraus<br>
	 *
	 * @param Termino
	 *            - spanischer Begriff
	 *            
	 * @return String
	 *     		  - übersetzter deutscher Begriff     
	 */		
	public static String getTermEsToDe(String Termino)
	{

		String retValue = "";

		String SQL = "SELECT BEGRIFF FROM LEXIKON";
		SQL += " WHERE TERMINO = " + quote(Termino);
		
		Object obj = DBConnection.executeScalar(SQL);

		if (obj != null)
			retValue = ((String) obj);

		return retValue;
	}
	
	/**
	 * <li><b><i>getTermDeToEs</i></b> <br>
	 * <br>
	 * public static String getTermDeToEs(String Begriff) <br>
	 * <br>
	 * Suche aus Tabelle Lexikon entsprechend des deutschen Begriffs<br>
	 * den zugeordneten spanischen Begriff heraus<br>
	 *
	 * @param Begriff
	 *            - deutscher Begriff
	 *            
	 * @return String
	 *     		  - übersetzter spanischer Begriff     
	 */		
	public static String getTermDeToEs(String Begriff)
	{

		String retValue = "";

		String SQL = "SELECT TERMINO FROM LEXIKON";
		SQL += " WHERE BEGRIFF = " + quote(Begriff);
		
		Object obj = DBConnection.executeScalar(SQL);

		if (obj != null)
			retValue = ((String) obj);

		return retValue;
	}

	/**
	 * <li><b><i>convertSpecialChar</i></b> <br>
	 * <br>
	 * public static String convertSpecialChar(String contenido) <br>
	 * <br>
	 * Ersetzt alle spracheigenen (deutsche, spanische) Umlaute in einer Zeichenkette<br>
	 * <br>
	 *
	 * @param contenido
	 *            - die zu konvertierende Zeichenkette
	 *            
	 * @return String
	 *     		  - konvertierte Zeichenkette     
	 */		
	public static String convertSpecialChar(String contenido)
	{
		contenido = contenido.replaceAll("á", "a");
		contenido = contenido.replaceAll("é", "e");
		contenido = contenido.replaceAll("í", "i");
		contenido = contenido.replaceAll("ó", "o");
		contenido = contenido.replaceAll("ú", "u");
		contenido = contenido.replaceAll("ñ", "n");
		contenido = contenido.replaceAll("Á", "A");
		contenido = contenido.replaceAll("É", "E");
		contenido = contenido.replaceAll("Í", "I");
		contenido = contenido.replaceAll("Ó", "O");
		contenido = contenido.replaceAll("Ú", "U");
		contenido = contenido.replaceAll("Ñ", "N");
		contenido = contenido.replaceAll("ä", "ae");
		contenido = contenido.replaceAll("ü", "ue");
		contenido = contenido.replaceAll("ö", "oe");
		contenido = contenido.replaceAll("Ä", "Ae");
		contenido = contenido.replaceAll("Ü", "Ue");
		contenido = contenido.replaceAll("Ö", "Oe");
		contenido = contenido.replaceAll("ß", "ss");
		
		return contenido;
	}
	
	public static String[] getDBColumnAsStringArray(int zeilen, ResultSet fachgebiete)
	{
		String[] subjectsNamesArray = new String[zeilen];
		
		try
		{
			// Namen aus Fachgebiete Tabelle werden in ein Array geschrieben							
			for(int i = 0; i < zeilen; i++)
			{
				fachgebiete.next();
				subjectsNamesArray[i] = (String)fachgebiete.getObject(1);
			}
		
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "getFachgebieteStrings: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			
		}
		return subjectsNamesArray;
	}
	

}