package W2T4_Dozent;


// In der Klasse 'Tag' soll erzwungen werden, dass der Wert f�r
// eine Varibale 'tag' immer zwischen 1 und 31 liegt.

public class Tag
{

	private int tag;
	
	// Statische Variablen sind im Gegensatz zu anderen Variablen einer Klasse nicht an eine Instanz dieser
	// Klasse gebunden. 
	// Das hei�t, dass statische Variablen immer zur Klassendefinition selbst geh�ren und nicht mit einer neuen Instanz erstellt werden. 
	// Statische Variablen gelten also f�r alle Instanzen einer Klassendefinition. 
	
	// Daher werden sie auch nur ein einziges mal erzeugt. Auch der Zugriff unterscheidet sich. Eine solche Variable kann, sofern sie
	// �ffentlich ist, �ber den Klassennamen erreicht werden. Dazu wird der Variablenname nach der direkten Klasse durch einen Punkt getrennt. 
	
	// Gleiches gilt f�r statische Methoden.
	
	private static int DEFAULT_TAG = 1;
	
	
	
	// Standard-Konstruktor: Initialisiert die Daten, ist �ffentlich, heisst wie
	// die Klasse, und hat keinen Typ und keine Argumente.
	public Tag()
	{
		tag = DEFAULT_TAG;
	}
	
	// �berladenener Konstruktor
	public Tag(int value)
	{
		
		// Aufruf des Standardkonstruktors.
		// Mu� die erste Anweisung in einem Konstruktor sein.
		this();
		
		setTag(value);
		
	}
	
	
	/**
	 * Setzt des aktuellen Tag des Monats.<br>
	 * Das ist eine weitere Zeile der Beschreibung.
	 * @param value
	 * 		Tag. G�ltige Werte <b>1 - 31</b>.
	 */
	public void setTag(int value)
	{
		
		if (checkTag(value))
			tag = value;
		else
			System.out.println("\nUng�ltiger Tag.\nG�ltige Werte 1 - 31.");
		
	}
	
	/**
	 * Gibt den aktuellen Tag zur�ck.
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
			System.out.println("\nUng�ltiger Tag.\nG�ltige Werte 1 - 31.");
		
		
		
	}
	
	
	
	
}
