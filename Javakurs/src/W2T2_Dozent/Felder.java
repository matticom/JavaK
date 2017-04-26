package W2T2_Dozent;

import java.util.Arrays;

/*
 * Ein Array (deutsch: Feld) ist eine Zusammenfassung mehrerer Variablen desselben
 * Typs zu einer Einheit. Innerhalb einer solchen Einheit werden die einzelnen
 * Elemente �ber Indizes (ganze Zahlen von 0 an aufw�rts) angesprochen.
 * 
 * Arrays sind in der Gr��e festgelegt, d.h. sie sind nicht dynamisch erweiterbar.
 * Arrays k�nnen (auch deshalb) intern besonders schnell verarbeitet werden.
 * Es gibt ein- und mehrdimensionale Arrays (d.h. ein Array von Arrays).
 * 
 * Jedes Array beinhaltet nur Werte eines bestimmten Datentyps.
 * Dies k�nnen sein:
 * 			- elementare Datentypen (int, byte, long, double...)
 * 			- Referenztypen
 * 			- Referenztypen anderer Arrays, um mehrdimensionale Arrays zu realisieren.
 */


public class Felder
{

	public static void main(String[] args)
	{
		
		/*
		 * Die Verwendung von Arrays bietet sich speziell dann an, wenn man mit
		 * gro�en Datenmengen vom gleichen Datentyp arbeitet, oder/und die
		 * Reihenfolge der Daten eine wichtige Rolle spielt - wenn man �ber den
		 * Index ein Element ansprechen k�nnen soll.
		 * 
		 * Arrays sind Objekte und m�ssen instanziert werden. Die Arraygr��e wird
		 * in eckige Klammern [] geschrieben. G�ltige Werte f�r die Gr��e eines
		 * Arrays sind positive, ganzzahlige Zahlen. Der Arrayindex beginnt mit 0.
		 * F�r ein Array der Gr��e n ist somit der Endindex (n-1).
		 */
		
		/*
		 * Bei der Deklaration eines Arrays wird der eigentliche Container
		 * erzeugt. Das bedeutet, dass das Array zun�chst exakt in seiner Gr��e
		 * und Typ bestimmt wird. M�glich ist die Verwendung aller elementaren und
		 * benutzerdefinierten Datentypen. Die Deklaration wird mit dem Datentyp
		 * eingeleitet, gefolgt vom Namen des Arrays. Wichtig ist die
		 * Kennzeichnung des Arrays mit den eckigen Klammeroperatoren, welche eine
		 * Array - Variable ausweisen. Dabei spielt es keine Rolle, ob diese nach
		 * dem Typ oder dem Namen erscheinen. Zum Abschlu� wird das Array noch mit
		 * dem new - Operator auf dem Speicher instanziiert, da es sich um eine
		 * Objektinstanz handelt.
		 */
		
		
		
		// (a) Deklaration und Instanziierung (d.h. Einrichten eines leeren 'int'-Arrays)
		//     der Gr�sse 6.
		
		//int[] lotto  = new int[6];
		int lotto[]  = new int[6];
		
		// Jetzt haben wir ein Feld 'lotto' vom Typ 'int' mit 6 Werten,
		// die alle mit 0 (vor-)belegt sind, und auf die wir so zugreifen k�nnen.
		
		System.out.printf("%d %d %d...\n", lotto[0], lotto[1], lotto[3]);
		System.out.println();
		
		// Zuweisung (explizite Initialisierung)
		lotto[0] = 2;
		lotto[1] = 5;
		lotto[2] = 18;
		lotto[3] = 25;
		lotto[4] = 32;
		lotto[5] = 42;
		
		Ausgabe(lotto);
		System.out.println();
		
		// Arrays schnell l�schen
		Arrays.fill(lotto, 0);
		Ausgabe(lotto);
		System.out.println();

		// Dies erzeugt eine 'ArrayIndexOutOfBoundsException',
		// jedoch keinen Compiler-Fehler !!!
		//lotto[6] = 49;
		//Ausgabe(lotto);
		//System.out.println();
		
		// (b) Deklaration und Initialisierung ohne L�ngenangabe
		int[] lotto2 = new int[] {3, 9, 19, 31, 33, 45};
		Ausgabe(lotto2);
		System.out.println();

		// oder in Kurzschreibweise
		int[] lotto3 = {7, 17, 27, 37, 47, 48};
		Ausgabe(lotto3);
		System.out.println();
		
		
		// Ein Array von Zeichenketten erstellen
		String[] arrText = {"Apfelkuchen", "Pustekuchen", "Sahne"};
		
		// Ausgabe
		for (int i = 0; i < arrText.length; i++)
			System.out.println(arrText[i]);
		System.out.println();
		
		
		Arrays.fill(arrText, "Leer");
		for (int i = 0; i < arrText.length; i++)
			System.out.println(arrText[i]);
		System.out.println();
		
		System.out.println();
		
		// Beide Instanzvariablen verweisen auf das gleiche Array.
		int[] lotto4 = lotto3;
		lotto4[0] = 5;
		
		Ausgabe(lotto4);
		Ausgabe(lotto3);
		System.out.println();
		
		// Um eine Kopie eines Arrays zu erhalten( mit gleicher Gr�sse und
		// gleichem Elementtyp so gibt es hierf�r die Objekt-Methode clone().
		
		int[] intClone = lotto4.clone();
		intClone[0] = 42;
		
		Ausgabe(lotto4);
		Ausgabe(intClone);
		System.out.println();
		
		// Um einzelne Feldinhalte in ein anderes Array zu kopieren gibt es die
		// statische Methode System.arraycopy().
		// Dabei kann auch die Position und die Anzahl der zu kopierenden Elemente
		// angegeben werden.
		
		int[] intCopy = new int[10];
		System.arraycopy(intClone, 0, intCopy, 3, 3);
		Ausgabe(intClone);
		Ausgabe(intCopy);
		System.out.println();
		
		// Kopieren eines Arrays geht ebenfalls �ber die Klasse Arrays.
		// Der R�ckgabewert ist ein Array mit der im Argument 2 angegeben Anzahl von
		// Elementen. 
		// Ist die Anzahl der Elemente kleiner als die des Ziel-Arrays, 
		// werden die �berz�hligen Elemente des Ziel-Arrays entfernt.
		// Ist die Anzahl der Elemente gr��er als die des Ziel-Arrays, 
		// wird das Ziel-Array erweitert.
		
		// 1. copyOf()
		int[] intCopy2 = new int[10];
		Ausgabe(intCopy2);
		intCopy2 = Arrays.copyOf(intClone, intClone.length);
		Ausgabe(intCopy2);
		System.out.println();
		
		int[] intCopy3 = new int[2];
		Ausgabe(intCopy3);
		intCopy3 = Arrays.copyOf(intClone, intClone.length);
		Ausgabe(intCopy3);
		System.out.println();
		
		// 2. copyOfRange()
		// Teilweises Kopieren eines Arrays zwischen einer angegebenen Position und dem EndIndex (exklusive)
		int[] intCopy4 = Arrays.copyOfRange(intClone, 3, intClone.length);
		Ausgabe(intCopy4);
		System.out.println();
		
		System.out.println(intCopy2.toString());
		System.out.println(intCopy2);
		
		// Ausgabe eines Arrays �ber die Klasse Arrays
		// Ausgabe im Format [e, e, e, e, e, ..]
		System.out.println(Arrays.toString(intCopy2));
		System.out.println();
		
		// Der Index vom Typ 'char' ist auch ein vorzeichenloser ganzzahliger Datentyp.
		// Der Index eines Feldes muss von einem Typ sein, der sich ohne
		// Verlust in 'int' konvertieren l�sst.
		// Dazu geh�ren byte, short, und char.
		// G�nstig ist ein Index vom Typ char z.B. als Laufvariable, wenn
		// Felder von Zeichenketten generiert werden (z.B. Alphabet in Kleinbuchstaben).
		
		char[] alphabet = new char['z' - 'a' + 1];
		
		for (char c = 'a'; c <= 'z'; c++)
		{
			alphabet[c - 'a'] = c;
		}
			
		// Alphabet ausgeben
		for (int i = 0; i < alphabet.length; i++)
			System.out.print(alphabet[i] + " ");
		
		System.out.println("\n");
		
		
		// Zweidimensionale Arrays
		
		int[][] Feld2D = new int[2][4];
		
		// 1. Dimension = Zeilen
		// 2. Dimension = Spalten
		
		
		// F�llen
		for (int zeile = 0; zeile < 2; zeile++)
		{
			
			for (int spalte = 0; spalte < 4; spalte++)
				Feld2D[zeile][spalte] = (zeile + 2) * (spalte + 3);
				
		}
		
		// Ausgabe
		for (int zeile = 0; zeile < 2; zeile++)
		{
			
			for (int spalte = 0; spalte < 4; spalte++)
				System.out.printf("%3d", Feld2D[zeile][spalte]);
			
			System.out.println();
		}
		
		System.out.println();
		
		
		System.out.println(Feld2D);
		System.out.println(Arrays.toString(Feld2D));
		
		// F�r die Ausgabe von mehrdimensionalen Arrays im Standardformat gibt es die Methode
		// Arrays.deepToString().
		System.out.println(Arrays.deepToString(Feld2D));
		
		
		// 3D-Arrays
		
		int Feld3D[][][] = new int[2][3][4];
		
		// F�llen
		int n = 1;
		
		for(int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				for (int k = 0; k < 4; k++)
				{
					Feld3D[i][j][k] = n++;
				}
			}
		}
		
		
		for(int i = 0; i < 2; i++)
		{
			System.out.println();
			for (int j = 0; j < 3; j++)
			{
				System.out.println();
				for (int k = 0; k < 4; k++)
				{
					System.out.printf("%3d", Feld3D[i][j][k]);
				}
			}
		}
		
		System.out.println();
		
		
		System.out.println(Arrays.toString(Feld3D));
		
		// F�r die Ausgabe von mehrdimensionalen Arrays im Standardformat gibt es die Methode
		// Arrays.deepToString().
		System.out.println(Arrays.deepToString(Feld3D));
		
		
	}
	
	
	private static void Ausgabe(int[] arr)
	{
		System.out.println("Ausgabe(int[])");
		
//		for (int i = 0; i < arr.length; i++)
//			System.out.printf("%3d", arr[i]);
		
		
		// Oder mit Hilfe einer erweiterten for-Schleife
		
		// Die erweiterte for-Schleife 
		// Weil das komplette Durchlaufen von Feldern h�ufig vorkommt,
		// wurde in Java 5 eine Abk�rzung f�r solche Iterationen in die
		// Sprache eingef�hrt:

		// Syntax:
		// for (Typ Bezeichner : Feld)
		// ...

		// Die erweiterte for-Schleife l�st sich vom Index und erfragt jedes
		// Element des Feldes.

		/*
		 * Umsetzung und Einschr�nkung.
		 * Intern setzt der Compiler die erweiterte for-Schleife ganz klassisch um, 
		 * sodass der Bytecode unter beiden Varianten gleich ist.
		 * 
		 * Nachteile der Variante sind:
		 * 		- Es wird immer das gesamte Feld durchlaufen. Anfang- und Ende-Index k�nnen nicht ausdr�cklich gesetzt
		 * 		  werden.
		 * 		- Die Ordnung ist immer von vorne nach hinten.
		 * 		- Der Index ist nicht sichtbar.
		 * 		- Die Schleife liefert ein Element, kann aber nicht in das Feld schreiben.
		 */
		
		for (int i : arr)
			System.out.printf("%3d", i);
		
		System.out.println();
		
	}
	

}
