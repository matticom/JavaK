package W1T1_Dozent;

/*
 * Das ist ein Kommentar.
 * N�chste Zeile.
 * 
 */

/*  Im folgenden Programm kommen drei Modifizierer vor:
 *  Der Modifizierer 'public' ist ein Sichtbarkeitsmodifizierer. Er bestimmt, ob die Klasse bzw. die Methode
 *  f�r anderere sichtbar ist oder nicht.
 *  Der Modifizierer 'static' zwingt den Programmierer nicht dazu, vor dem Methodenaufruf ein Objekt
 *  der Klasse zu bilden. Anders gesagt, die Eigenschaft, ob sich eine Methode nur �ber ein konkretes
 *  Objekt aufrufen l�sst oder eine Eigenschaft der Klasse ist, sodass f�r den Aufruf kein Objekt der
 *  Klasse n�tig wird, wird durch diesen Modifizierer bestimmt.
 *  Wir arbeiten in den ersten Tagen nur mit statischen Methoden. Sp�ter werden dann auch nicht-statische
 *  Methoden eingef�hrt. 
 */



public class Hallo
{

	public static void main(String[] args)
	{
		
		// Die Methode main() muss vorhanden sein und ist der Programmeinstiegspunkt.
		// Innerhalb der Methode main() stehen die Programmanweisungen die
		// ausgef�hrt werden, wenn das Programm gestartet wird.
		
		// Vor der Methode wird der Datentyp deklariert, der als R�ckgabewert
		// zur�ckgeliefert wird.
		// 'void' (leer) bedeuted kein R�ckgabewert.
		
		// Der Methodenrumpf oder auch logischer Block ist der Teil der Methode, in
		// dem die Anweisungen stehen.
		// Er wird mit geschweiften Klammern {} gekennzeichnet.
		
		// Jede Anweisung muss mit einem Semikolon (;) abgeschlossen werden.
		
		
		System.out.println("Guten Tag im Java-Kurs.");
		
		System.out.println("Hallo.");
		
		// Leerzeile
		System.out.println();
		
		// Eingabe von syso anschliessend STRG + Leertaste
		// ergibt
		// System.out.println();
		System.out.println("Heute ist der erste Tag des Kurses.");
		
	}

}
