package W1T4_Dozent;

import java.util.Scanner;

import javax.sound.midi.Synthesizer;

// Schleifen dienen dazu, Anweisungsfolgen wiederholt auszuführen.

public class Schleifen
{

	public static void main(String[] args)
	{
	
		
		
		
		
		
		// Die for-Schleife
		// Man setzt eine for-Schleife zumeist dann ein, wenn bekannt ist, wie
		// oft bestimmte Anweisungen ausgeführt werden müssen.
		
		// Ausdruck1: Initialisierung
		// Ausdruck2: Bedingung
		// Ausdruck3: Modifizierung
		
		// Syntax:
		// for (Ausdruck1; Ausdruck2; Ausdruck3)
		// {
		// 		Anweisungen
		// 		...
		// }
		
		
		// Gibt die Zahlen 1 bis 10 auf der Konsole aus
		for (int i = 1; i <= 10; i++)
		{
			System.out.println(i);
		}
		
		// Die folgende Anweisung verursacht einen Kompilierfehler
		// Die Zählervariable, die im Schleifenkopf deklariert wird, gilt als
		// lokale Variable der Schleife und ist deshalb auch nur innerhalb des
		// Anweisungsblocks der for-Schleife gültig.
		//System.out.println(i);
		
		System.out.println();
		
		
		// Vorzeitiges Beenden einer Schleife mit 'break'
		
		System.out.println("Vorzeitiges Beenden einer Schleife");
		
		// Ausgabe aller Zahlen von 1 bis 4
		for (int i = 1; i <= 10; i++)
		{
			
			if (i > 4)
				break;
			
			System.out.println(i);
		}
		
		System.out.println();
		
		
		// for-Schleife mit Komma-Operator
		// Im ersten und im letzten Teil einer for-Schleife lässt sich ein Komma
		// setzen.
		// Damit lassen sich entweder mehrere Variablen gleichen Typs
		// deklarieren, oder mehrere Ausdrücke nebeneinander schreiben.
		
		// Ausgabe: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
		for (int i = 0, j = 0; i + j < 10; i--, j+= 2)
		{
			System.out.print((i + j) + ", ");
		}
		
		System.out.println("\n");
		
		// Variationen der for-Schleife
		
		// 1. for-Schleife ohne Initialisierungsausdruck
		
		int a = 1;
		
		for (; a <= 10; a++)
			System.out.print(a + ", ");
		
		
		System.out.println("\n");
		
		// 2. for-Schleife ohne Bedingungsausdruck
		//    Der Schleifenabbruch muss innerhalb des Schleifenrumpfs angegeben werden.
		
		for (int i = 1;;i++)
		{
			if (i > 10)
				break;
			System.out.print(i + ", ");
			
		}
		
		System.out.println("\n");
		
		// 3. for-Schleife ohne Modifizierungsausdruck
		//    Inkrementierung erfolgt im Schleifenrumpf.
		for (int i = 1; i <= 10;)
		{
			
			System.out.print(i + ", ");
			i++;
		}
			
		System.out.println("\n");
		
		// 4. Leere for-Schleife (ohne Initialisierung, Bedingung und Modifizierung):
		//    Endlosschleife.
		
//		for (;;)
//			System.out.println("Immer wieder und wieder...");
		
		
		for (int zeile = 1; zeile <= 10; zeile++)
		{
			
			for (int spalte = 1; spalte <= 10; spalte++)
			{
				System.out.printf("%4d", zeile * spalte);
			}
			
			System.out.println();
			
			
		}
		
		System.out.println("\n");
		
		// Bedingtes Auslassen von Anweisungen im Schleifenrumpf.
		// So ähnlich wie 'break' verhält sich auch die Anweisung 'continue'.
		// Die Bearbeitung des Codes in der Schleife wird war abgebrochen, aber
		// die Steuerung wieder an den Schleifenkopf übergeben.
		// Das bedeutet, alle Anweisungen, die zwischen continue und dem Ende
		// des Anweisungsblockes stehen, werden übersprungen.
		
		
		System.out.println("Bedingtes Auslassen von Anweisungen im Schleifenrumpf");
		
		
		// Ausgabe aller geraden Zahlen zwischen 0 und 10
		for (int i = 0; i <= 10; i++)
		{
			
			if (i % 2 == 1)
				continue;
			
			System.out.print(i + ", ");
			
		}
		
		System.out.println("\n");
		
		// Die 'while'-Schleife.
		// In eine while-Schleife wird dann eingetreten, wenn bestimmte
		// Bedingungen erfüllt sind. Bei der for-Schleife wird diese Bedingung
		// durch den Schleifenzähler festgelegt, bei einer while-Schleife wird die
		// Bedingung hinter dem Schlüsselwort while in runden Klammern angegeben. 
		// Da sich die Anweisungen der Bedingungsprüfung anschließen, spricht man auch von
		// einer kopfgesteuerten Schleife.
		
		// Syntax:
		// while (Bedingung)
		// {
		// 		Anweisungen
		// }
		
		// In der Bedingung handelt es sich um einen booleschen Ausdruck, der
		// aus Vergleichsoperatoren gebildet wird und entweder true oder false
		// liefert.

		// Eine while-Schleife wird ausgeführt, solange die Bedingung wahr, also
		// true ist. Die Schleife wird beendet, wenn die Bedingung false ist.

		// Ist die Bedingung schon bei der ersten Überprüfung falsch, werden die
		// Anweisungen im Schleifenkörper überhaupt nicht ausgeführt.

		// Da im Gegensatz zur for-Schleife die Bedingung zum Austritt der
		// Schleife nicht automatisch verändert wird, muss innerhalb des
		// Schleifenkörpers eine Anweisung stehen, die es ermöglicht, die Schleife 
		// zu einem vordefinierten Zustand zu verlassen. Wird eine solche Anweisung
		// vergessen, liegt der klassische Fall einer Endlosschleife vor.
		
		int i = 1;
		int zahl = 10;
		
		while(i <= zahl)
		{
			//System.out.println("Schleifendurchlauf: " + i);
			//i++;
			// oder
			System.out.println("Schleifendurchlauf: " + (i++));
		}
		
		System.out.println();
		
		System.out.println("Nach der while-Schleife: " + i);
		
		System.out.println();
			
		// Endlosschleife
//		while (true)
//		{
//			
//		}
		
		// Die 'do'-Schleife.
		// Die do-Schleife unterscheidet sich dahingehend von der
		// while-Schleife, dass die Schleifenbedingung am Ende der Schleife ausgewertet wird.
		// Die do-Schleife ist eine fußgesteuerte Schleife. Die Folge ist,
		// dass die Anweisungen innerhalb des Anweisungsblocks zumindest einmal
		// durchlaufen werden.
		
		
		// Syntax:
		// do
		// {
			// 	Anweisungen
		// } 
		// while (Bedingung);
		
		i = 1;
		do
		{
			System.out.println("Schleifendurchlauf: " + (i++));
		}
		while (i <= 10);
		
		System.out.println();
		
		// Eingabe eines Wertes über die Konsole, der halbiert wird
		// so lange der Wert >= 11 ist.
		
		// Erstellen einer Klasse Scanner mit Übergabe des Standardeingabestroms
		// (Konsole).
		Scanner in = new Scanner(System.in);
		
		double wert; 
		double d;
		
		System.out.println("Bitte einen Wert eingeben:");
		wert = in.nextDouble();
		d = wert;
	
		do
		{
			wert /= 2;
			System.out.println(wert);
		}
		while (wert >= 11);
		
		System.out.println();
		
		
		wert = d;
		
		do
		{
			System.out.println(wert);
		}
		while ((wert /= 2) >= 11);
		
		System.out.println();
		
		
		// Schleifenbedingungen und absolute Vergleiche mit == oder !=

		// Eine Schleifenabbruchbedingung kann ganz unterschiedlich aussehen.
		// Beim Zählen ist es häufig der Vergleich auf einen Endwert.
		// Oft steckt an dieser Stelle ein absoluter Vergleich mit == oer !=,
		// der aus zwei Gründen problematisch werden kann.
		
		
		// 1. Beispiel
		
		for (int x = 1; x != 11; x++)
			System.out.println(x);

		System.out.println();
		
		// Ist der Wert der Variablen x kleiner als 11, so haben wir beim Zählen
		// kein Problem, denn dann ist anschließend spätestens bei 11 Schluss und die 
		// Schleife bricht ab.
		
		// Kommt der Wert aber aus einer unbekannten Quelle und ist er größer
		// als 11, so ist die Bedingung ebenso wahr, und der Schleifenrumpf wird ziemlich
		// lange durchlaufen – genau genommen so weit, bis wir durch einen Überlauf
		// wieder bei 0 beginnen und dann auch bei 11 und dem Abbruch landen.
		// Die Absicht ist sicherlich eine andere gewesen. Die Schleife sollte
		// nur so lange zählen, wie x kleiner 11 ist, und nicht einfach nur ungleich 11.
		
		int x = 12;
		
//		for (; x != 11; x++)
//			System.out.println(x);
		
		System.out.println();
		
		
		// Demonstration mit dem Datentyp Byte
		byte b = 12;
		for (; b != 11; b++)
			System.out.println(b);

		// Beispiel Byte:
		
		//       127            -128            -127             0            11
		// 0111 1111 + 1 = 1000 0000 + 1 = 1000 0001 ... 0000 0000 ... 0000 1011
		
		System.out.println();
		
		
		// 2. Beispiel
		// Das zweite Problem ergibt sich bei Fließkommazahlen. Es ist sehr
		// problematisch, echte Vergleiche zu fordern:
		
		d = 0;
		
//		while(d != 1.0)
//		{
//			d += 0.1;
//			System.out.println(d);
//		}
		
		// Bei Fließkommawerten bietet es sich daher immer an, mit den
		// relationalen Operatoren <, >, <= oder >= zu arbeiten.
		
		while(d <= 1.0)
		{
			d += 0.1;
			System.out.println(d);
		}
		
		System.out.println();
		
		
	}

}
