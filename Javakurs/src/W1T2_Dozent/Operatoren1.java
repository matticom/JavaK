package W1T2_Dozent;

/*
 *   Operatoren -> dienen zur Verknüpfung von Daten
 *
 *   Mathematische Operatoren:     <Zahl> operator <Zahl> => Zahl
 *   Vergleichsoperatoren:         <Zahl> operator <Zahl> => logischer Wert
 *   Logische Operatoren:          <log. Wert> operator <log. Wert> => logischer Wert
 *   Bitweise logische Operatoren: <Bitmuster> operator <Bitmuster> => Bitmuster
 */

public class Operatoren1
{

	public static void main(String[] args)
	{
		/*
	      * Mathematische Operatoren:
	      *
	      *     (a) + (Addition)
	      *     (b) - (Substraktion) -> sowohl binär (a = b - c) als auch unär (-a)
	      *     (c) * (Multiplikation)
	      *     (d) / (Division)
	      *     (e) % (Modulo, Rest der Ganzzahldivision)
	      *
	      */
		
		// Beispiel 1: Grundlagen
		
		int ganzzahlig = 17;
		double gleitkommawert = 0.16;
		
		System.out.println("Ganzzahlwert: " + ganzzahlig);
		System.out.println("Gleitkommawert: " + gleitkommawert);
		
		System.out.println("Multiplikation: " + (ganzzahlig * gleitkommawert));
		System.out.println();
		
		int wert = 27, teiler = 5;
		int restwert, quotient;
		
		quotient = wert / teiler;
		System.out.println("Quotient: " + quotient);
		
		restwert = wert % teiler;
		System.out.println("Restwert: " + restwert);
		System.out.println();
		
		
		double rechnung = (3 * 6.60) + (2 * 1.90) + 3.10;
		System.out.println("Die Rechnung beträgt: " + rechnung);
		
		// Benzinverbrauch auf 345 Kilometer bei einm Verbrauch von 7,5 Lietern/100 km
		
		double benzinverbrauch = (345 / 100) * 7.5;
		System.out.println("Benzinverbrauch: " + benzinverbrauch);      // 22,5 Liter falsch!
		
		benzinverbrauch = (345 / 100.0) * 7.5;
		System.out.println("Benzinverbrauch: " + benzinverbrauch);      // 25.875 Liter, richtig. 
		
		System.out.println();
		
	     /*
	       * Inkrement- und Dekrement-Operatoren (unäre Operatoren)
	       * sowohl in Postfix- als auch in Präfix-Notation.
	       *
	       *		(a) Inkrement: ++ (erhöht den Wert einer Variablen um 1)  .
	       *		(b) Dekrement: -- (verringert den Wert einer Variablen um 1).
	       *
	       * Wenn die Präfix-Notation gewählt wird, so erfolgt das Inkrementieren
	       * bzw. Dekrementieren vor allen anderen Operationen innerhalb einer
	       * geschachtelten Anweisung, ansonsten danach.
	       *
	       */
		
		
		int a = 0, b = 0;
		System.out.println("a: " + a + " b: " + b);					// a = 0, b = 0;
		
		
		// Postfix
		a++;
		
		// Präfix
		--b;
		System.out.println("a: " + a + " b: " + b);					// a = 1, b = -1;
		
		// a wird erst nach der Zuweisung inkrementiert
		b = a++;
		System.out.println("a: " + a + " b: " + b);		            // a = 2, b = 1;
		
		// a wird vor der Zuweisung dekrementiert
		b = --a;
		System.out.println("a: " + a + " b: " + b);	                // a = 1, b = 1

		// a wird nach der Ausgabe inkrementiert,
		// b wird vor der Ausgabe dekrementiert.
		System.out.println("a: " + a++ + " b: " + --b);            //  a = 1, b = 0 
		
		// Überprüfung
		System.out.println("a: " + a + " b: " + b);
		
		
	}

}
