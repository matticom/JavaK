package W1T5_Dozent;

/*
 * Ein String ist eine Sammlung von Zeichen (Datentyp char), die die Laufzeit-
 * umgebung geordnet im Speicher ablegt.
 * F�r einen Zugriff auf einzelne Zeichen innerhalb eines Strings ist deswegen ist zu beachten, 
 * dass auch hier, ebenso wie bei Arrays, das erste Zeichen den Index 0 hat. Daher entspricht 
 * der Index des letzten Elements nicht der L�nge der Zeichenkette, sondern der L�nge-1. 
 * Ansonsten kann man auch hier eine java.lang.ArrayIndexOutOfBoundsException bekommen.
 * 
 * 
 * String ist einer der am meisten verwendeten Datentypen in Java. In einer Variablen 
 * mit dem Datentyp String werden Zeichenketten gespeichert. Zeichenketten werden in Java
 * immer in doppelte Anf�hrungszeichen gesetzt. Die L�nge einer Zeichenkette ist variabel. 
 * Ein String-Objekt kann mit dem new-Operator erzeugt werden. Der new-Operator ist aber 
 * nicht zwingend erforderlich, da er bei der ersten Zuweisung ansonsten implizit aufgerufen wird.
 * 
 */

/* Java sieht drei Klassen vor, die Zeichenfolgen verwalten: String, StringBuilder und StringBuffer.
 * Sie unterscheiden sich in folgenden Punkten:
 * 		-  Sind die Zeichenfolgen unver�nderbar (invariant, immutable) oder
 *         nicht.
 *      -  Sind die Operationen auf den Zeichenketten gegen nebenl�ufige Zugriffe
 *         aus mehreren Threads abgesichert.
 */

/* Die Klasse String repr�sentiert eine nicht �nderbare Zeichenkette. Daher ist ein 
 * String immer threadsicher.
 * Mit Methoden der Klasse String kann man nach Zeichen oder Teilzeichenketten suchen
 * und ein String l�sst sich mit einem anderen String vergleichen. Aber Zeichen im
 * String k�nnen nicht ver�ndert werden.
 * Es gibt einige Methoden, die scheinbar Ver�nderungen an Strings vornehmen, aber
 * sie erzeugen in Wahrheit neue String-Objekte, die die ver�nderten Zeichenketten
 * repr�sentieren.      
 *        
 */

public class Zeichenketten
{

	public static void main(String[] args)
	{

		String s = "Java";
		s += " ist";
		s += " toll.";
		
		System.out.printf("Die L�nge der Zeichenkette '%s' betr�gt %d\n", s, s.length());
		
		
		// Umwandlung der Zeichenkette in Gro�buchstaben
		s = s.toUpperCase();
		System.out.println("Umwandlung der Zeichenkette in Gro�buchstaben: " + s);
		
		// Umwandlung der Zeichenkette in Kleinbuchstaben
		s = s.toLowerCase();
		System.out.println("Umwandlung der Zeichenkette in Kleinbuchstaben: " + s);
				
		System.out.println();
		
		 // Nach enthaltenen Zeichen und Zeichenfolgen suchen.
		
		char c = 'a';
		
		// Fundstelle von Zeichen oder Zeichenketten mit Methode indexOf() zur�ckgeben.
        // Wenn das Zeichen nicht gefunden wurde liefert sie -1 .
		// Gross- und Kleinschreibung ist relevant.
		
		int i =  s.indexOf(c);
		if (i >= 0)
			System.out.printf("Die Zeichenkette enth�lt das Zeichen '" + c + "' an der Indexposition %d\n", i);
		else
			System.out.println("Das Zeichen '" + c + "' kommt in der Zeichenkette nicht vor");
		
		
		String suchString = "toll";
		
		i = s.indexOf(suchString);
		if (i >= 0)
			System.out.printf("Die Zeichenkette enth�lt die Zeichenfolge '" + suchString + "' an der Indexposition %d\n", i);
		else
			System.out.println("Die Zeichenfolge '" + suchString + "' kommt in der Zeichenkette nicht vor");
		
		if (s.contains(suchString))
			System.out.println("Die Zeichenkette enth�lt die Zeichenfolge '" + suchString + "'");
		else
			System.out.println("Die Zeichenfolge '" + suchString + "' kommt in der Zeichenkette nicht vor");
		
		
		 // Vom Ende an suchen
		i = s.lastIndexOf(c);
		if (i >= 0)
			System.out.printf("Die Zeichenkette enth�lt das Zeichen '" + c + "' an der Indexposition %d\n", i);
		else
			System.out.println("Das Zeichen '" + c + "' kommt in der Zeichenkette nicht vor");
		
		System.out.println();
		
		
		// Alle Zeichen einer Zeichenkette ausgeben
		
		
		for (i = 0; i < s.length(); i++)
			System.out.print(s.charAt(i));
		System.out.println();
		
		
		// Der Vergleich von Zeichenketten
		
//		String s1 = "Java ist toll.";
//		String s2 = "Java ist toll.";
		
		// Die Zeichenkette "Java ist toll." wird nur ein einziges Mal angelegt
        // und verweist deshalb auf das gleiche Objekt (Speicheradresse).
		
		// Der Vergleichsoperator (==) vergleicht lediglich Speicheradressen: 
        // bei Objekten unbrauchbar!
        // Hier werden die beiden Objekte s1 und s2 verglichen und nicht die
        // Zeichenkette selbst.
		
		
		System.out.println("Der Vergleich von Zeichenketten");
		
		 // Erzwingen, dass 2 String-Objekte erstellt werden:
		String s1 = new String("Java ist toll.");
		String s2 = new String("Java ist toll.");
		
		if (s1 == s2)
			System.out.println("Die Zeichenketten stimmen �berein");
		else
			System.out.println("Die Zeichenketten stimmen nicht �berein");
		
		
	    // equals() achtet auf absolute �bereinstimung.
        // Die Methode gibt true zur�ck, falls die Zeichenketten gleich lang
        // sind und Zeichen f�r Zeichen �bereinstimmen.
		
		if (s1.equals(s2))
			System.out.println("Die Zeichenketten stimmen �berein");
		else
			System.out.println("Die Zeichenketten stimmen nicht �berein");
		
		
		s1 = s1.toUpperCase();
		
		// Absoluter Vergleich ohne Ber�cksichtigung von Gro�- und Kleinschreibung.
		
		if (s1.equalsIgnoreCase(s2))
			System.out.println("Die Zeichenketten stimmen �berein");
		else
			System.out.println("Die Zeichenketten stimmen nicht �berein");
		
		System.out.println();
		
		
	    // Lexikografische Vergleiche

        // compareTo() gibt einen int-Wert zur�ck der signalisiert, ob das
        // Argument lexikografisch kleiner oder gr��er das als String-Objekt ist
        // bzw. mit diesem �bereinstimmt.
        // R�ckgabewerte: 1 = Basiszeichenkette ist gr��er als die Vergleichszeichenkette, 
        //              < 0 = Basiszeichenkette ist kleiner als die Vergleichszeichenkette, 
        //				  0 = Zeichenketten stimmen �berein.
		
		
		s1 = "112";
		s2 = "12";
		
		System.out.printf("Lexikografischer Vergleich der Zeichenketten '%s' und '%s'\n", s1, s2);
		
		if (s1.compareTo(s2) < 0)
			System.out.println(s1 + " < " + s2);
		
		if (s1.compareTo(s2) >= 0)
			System.out.println(s1 + " >= " + s2);
		
		System.out.println();
		
		// Mit welchen Zeichen endet (Suffix) oder beginnt (Pr�fix) die
        // Zeichenkette.
		boolean istTextDatei = "Aufgaben.txt".endsWith(".txt");
		System.out.printf("Ist Textdatei: %s\n", istTextDatei);
		
		// Zeichenketten extrahieren
		
		s1 = "Java ist toll.";
		
		String ss = s1.substring(0, 8);
		System.out.println(ss);
		
		System.out.println();
		
		// Umwandlung eines numerischen Wertes in eine Zeichenkette
		
		i = 42;
		
		String text = "" + i;
		System.out.println(text);
		
		
		text = Integer.toString(1234);
		System.out.println(text);
		
		
		text = String.valueOf(2468);
		System.out.println(text);
		
		
		
		
		
		
	}

}
