package W2T4_Dozent;


// In der Klasse 'Tag' soll erzwungen werden, dass der Wert für
// eine Varibale 'tag' immer zwischen 1 und 31 liegt.

public class Tag
{

	private int tag;
	
	// Statische Variablen sind im Gegensatz zu anderen Variablen einer Klasse nicht an eine Instanz dieser
	// Klasse gebunden. 
	// Das heißt, dass statische Variablen immer zur Klassendefinition selbst gehören und nicht mit einer neuen Instanz erstellt werden. 
	// Statische Variablen gelten also für alle Instanzen einer Klassendefinition. 
	
	// Daher werden sie auch nur ein einziges mal erzeugt. Auch der Zugriff unterscheidet sich. Eine solche Variable kann, sofern sie
	// öffentlich ist, über den Klassennamen erreicht werden. Dazu wird der Variablenname nach der direkten Klasse durch einen Punkt getrennt. 
	
	// Gleiches gilt für statische Methoden.
	
	private static int DEFAULT_TAG = 1;
	
	
	
	// Standard-Konstruktor: Initialisiert die Daten, ist öffentlich, heisst wie
	// die Klasse, und hat keinen Typ und keine Argumente.
	public Tag()
	{
		tag = DEFAULT_TAG;
	}
	
	// Überladenener Konstruktor
	public Tag(int value)
	{
		
		// Aufruf des Standardkonstruktors.
		// Muß die erste Anweisung in einem Konstruktor sein.
		this();
		
		setTag(value);
		
	}
	
	
	/**
	 * Setzt des aktuellen Tag des Monats.<br>
	 * Das ist eine weitere Zeile der Beschreibung.
	 * @param value
	 * 		Tag. Gültige Werte <b>1 - 31</b>.
	 */
	public void setTag(int value)
	{
		
		if (checkTag(value))
			tag = value;
		else
			System.out.println("\nUngültiger Tag.\nGültige Werte 1 - 31.");
		
	}
	
	/**
	 * Gibt den aktuellen Tag zurück.
	 */
	public int getTag()
	{
		return tag;
	}
	
	
	private static boolean checkTag(int value)
	{
		return (value > 0 && value <= 31);
	}
	
	
	public static int getDefaultTag()
	{
		
		return DEFAULT_TAG;
		
	}
	
	public static void setDefaultTag(int value)
	{
		if (checkTag(value))
			DEFAULT_TAG = value;
		else
			System.out.println("\nUngültiger Tag.\nGültige Werte 1 - 31.");
		
		
		
	}
	
	
	
	
}
