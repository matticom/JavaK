package W1T1_Dozent;

/*
 *  In Java gibt es zwei Arten von primitiven (eingebauten) Datentypen:
 * 		-  arithmetische Typen (ganze Zahlen, auch integrale Typen genannt, Flie�kommazahlen und Unicode-Zeichen)
 * 		-  Wahrheitswerte f�r die Zust�nde wahr oder falsch.
 * 
 *  Zeichenketten (Strings)  sind invariante (unver�nderliche) Objekte!
 * 
 */

/* Die Speicherbereiche Stack und Heap.
*
* Alle elementaren Datentypen werden auf dem Stack abgelegt, alle Objekte auf dem Heap.
* Ausnahme C++: Objekte k�nnen auch auf dem Stack erstellt werden.
*
* Stack (deutsch: Stapel)
* Der Stapelspeicher ist ein Speicherbereich f�r lokale Variablen eines Moduls (statische Speicherverwaltung). 
* Beim Verlassen eines G�ltigkeitsbereichs werden diese Objekte automatisch zerst�rt.
* F�r Stackspeicher gilt immer, was zuletzt angefordert wurde muss auch als Erstes wieder freigegeben werden (LIFO: Last In � First Out). 
* Wenn Sie innerhalb eines logischen Blocks also Variablen anlegen, werden diese auf dem Stack angelegt. 
* Am Ende des Blocks verliert die Variable ihre G�ltigkeit und der Speicher wird wieder freigegeben.
* Wenn eine Funktion aufgerufen wird, wird die aktuelle Programmadresse (also die Stelle im Programm, an der die Funktion aufgerufen wurde)
* auf dem Stack abgelegt. Innerhalb der Funktionen werden m�glicherweise Variablen angelegt, die wiederum auf dem Stack landen. 
* Am Ende der Funktion werden die Speicherbereiche der Variablen wieder freigegeben und das Programm springt zu der Programmadresse, 
* die oben auf dem Stack liegt. Somit befindet es sich jetzt wieder an der Stelle, an der die Funktion aufgerufen wurde.
* Der Stack ist in seiner Gr�sse begrenzt (1 MegaByte).
*
* Heap (deutsch: Halde)
* Objekte k�nnen dynamisch und permanent bis zum Ende der Laufzeit des Moduls erstellt werden. Dies erfolgt im sog. Heap.
* Zu Erstellung eines Objekts verwendet man den Operator 'new'. Objekte, f�r die es keine Referenz mehr in ein aktives Programm gibt,
* werden vom Garbage Collector automatisch entfernt.
* Der Heap hat den eklatanten Vorteil, dass die Grenzen des zuteilbaren Speichers nur vom Betriebssystem und der physikalischer Speichermenge
* gezogen werden und nicht von Compiler- und Linkereinstellungen. Ein weiterer Vorteil ist, dass alle Elemente einer Klasse dann auch auf dem 
* Heap liegen. 
*/

/*
 *	Datentyp   		Gr��e		Wertebereich 											Beschreibung
 *------------    -------	    -----------------										-----------------
 *	boolean 	     8 Bit	    true / false 											Boolescher Wahrheitswert
 *  char          	16 Bit 		0 ... 65.535											Unicode-Zeichen
 *  byte			 8 Bit		-128 ... 127											Zweierkomplement-Wert
 *  short 		   	16 Bit		-32.768 ... 32.767 										Zweierkomplement-Wert
 *  int 			32 Bit		-2.147.483.648 ... 2.147.483.647						Zweierkomplement-Wert
 *  long 			64 Bit      -9.223.372.036.854.775.808 ...             				Zweierkomplement-Wert
 *                              9.223.372.036.854.775.807
 *  float 			32 Bit 		+/-1,4E-45 ... +/-3,4E+38 								Gleitkommazahl
 *  double 	   		64 Bit 		+/-4,9E-324 ... +/-1,7E+308                   			Gleitkommawert mit doppelter Genauigkeit
 */

/*
 * Zwei wesentliche Punkte zeichnen die primitive Datentypen aus:
 * 
 * 1. Alle Datentypen haben eine festgesetzte L�nge, die sich unter keinen Umst�nden �ndert.
 * 2. Die numerischen Datentypen byte, short, int und long sind vorzeichenbehaftet, Flie�kommazahlen
 * 	  sowieso. Das ist leider nicht immer praktisch, aber man muss stets daran denken.
 */

public class Datentypen
{

	public static void main(String[] args)
	{
		
		
		// Variablendeklaration
		
//		String name;
//		int alter;
//		double gehalt;
//		char geschlecht;
//		boolean istKanzler;
//		
//		
//		name = "Angela Merkel";
//		alter = 61;
//		gehalt = 24575.50;
//		istKanzler = true;
		
		// Variablendeklaration mit Wertinitialisierung
		// Der Zuweisungsoperator ist das Gleichkeitszeichen (=)
		
		String name = "Angela Merkel;";
		int alter = 61;
		double gehalt = 24575.50;
		char geschlecht = 'w';
		boolean istKanzler = true;
		
		// Deklaration von mehreren Variablen des gleichen Datentyps mit einer Anweisung
		int a, b, c, d;
		
		a = b = c = 0;
		
		// Fehler!
		// Ohne vorherige Initialisierung/Zuweisung darf auf eine Variable nicht
		// zugegriffen werden: Die lokale Variable wurde noch nicht initialisiert.
		d = 42;
		System.out.println(d);
		System.out.println();
		
		
		/*
		 * char 
		 * Ein char besteht in Java aus zwei Bytes. Das ist notwendig, da
		 * ein char nicht wie z.B. in C nur einen Wertebereich von 0-255
		 * abdeckt, sondern ein Unicode-Zeichen aufnehmen muss, welches aus zwei
		 * Bytes bestehen kann. Diese Art der Zeichendarstellung wird auch
		 * "UTF-16" genannt, weil 2 Bytes = 16 Bit entsprechen. Mit dieser
		 * Datenbreite ist es m�glich, auch mit asiatischen Zeichens�tzen in
		 * Java zu arbeiten.
		 */
		
		
		char buchstabe1 = 'A';
		char buchstabe2 = 65;
		
		System.out.println(buchstabe1);
		System.out.println(buchstabe2);
		System.out.println();
		
		buchstabe1 = (char)(buchstabe1 + 32);
		buchstabe2 = (char)(buchstabe2 + 2);
		
		System.out.println(buchstabe1);
		System.out.println(buchstabe2);
		System.out.println();
		
		// Explizite Umwandlung von 'char' nach 'int'
		// Ergebnis ist der CodePoint also der Wert, der die Position des
		// Zeichens innerhalb der Ascii-Tabelle definiert.
		System.out.println((int)buchstabe1);
		System.out.println((int)buchstabe2);
		String s;
		s = 3+4+"bla\\";					// vom Compiler gelesen von links nach rechts
		System.out.println(s);
		s = "bla"+3+4+"\r"+34434;
		System.out.println(s);
		System.err.println(s);
		

	}

}
