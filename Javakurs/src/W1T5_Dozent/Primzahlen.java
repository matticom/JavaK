package W1T5_Dozent;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Eine Primzahl ist eine nat�rliche Zahl mit genau zwei nat�rlichen Zahlen als Teiler, 
// n�mlich der Zahl 1 und sich selbst. 
// Die kleinsten Primzahlen sind 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, ...


public class Primzahlen
{

	private static final int COLUMNS = 10;
	private static int COLUMN_WIDTH = 8;
	
	public static void main(String[] args)
	{
		
		
		int maxValue = -1;
		int count = 0;
		String eingabe;
		
		
		Scanner in = new Scanner(System.in);
		
		while(maxValue < 2)
		{
			
			// Die try-catch-Anweisung besteht aus einem try-Block, auf den eine
			// oder mehrere catch-Klauseln folgen, welche die die Behandlung verschiedener
			// Ausnahmen erm�glichen.
			// Der try-Block enth�lt den �berwachten Code, der m�glicherweise
			// die Ausnahme verursacht.
			// Die catch-Klausel f�ngt den Ausnahmetyp ab.
			// Die finally-Klausel ist optional. Sie wird immer ausgef�hrt,
			// unabh�ngig davon, ob eine Ausnahme verursacht wurde oder nicht.
			
			/*
			 * Syntax der try-catch-Anweisung
			 * 
			 * try 
			 * { 
			 * 		// Anweisungen 
			 * } 
			 * catch(Typ Objekt) 
			 * { 
			 * 		// Anweisungen 
			 * } 
			 * [finally]
			 * { 
			 * 		// Anweisungen 
			 * }
			 * 
			 */

			/*
			 * 1. Wenn eine Ausnahme von einer Anweisung im try{}-Block ausgel�st wird, 
			 * 	  werden die catch{}-Bl�cke einer nach dem anderen untersucht, beginnend
			 * 	  mit dem ersten. 
			 * 2. Nur ein catch{}-Block wird ausgew�hlt. 
			 * 3. Wenn kein catch{}-Block mit der Ausnahme �bereinstimmt, wird keiner ausgew�hlt und
			 * 	  die Ausf�hrung verl��t die Methode (gerade so als wenn es keinen try{}-Block gegeben h�tte.)
			 * 4. Der erste catch{}-Block, der mit dem Typ der Ausnahme �bereinstimmt, bekommt die Kontrolle. 
			 * 5. Die speziellsten Ausnahmentypen sollten in der Struktur zuerst kommen, gefolgt von
			 * 	  allgemeineren Ausnahmetypen. 
			 * 6. Die Anweisungen in dem gew�hlten catch{}-Block werden sequentiell ausgef�hrt. 
			 * 	  Nachdem die letzte Anweisung ausgef�hrt ist, wird die Kontrolle an die erste Anweisung weitergegeben, 
			 *    die der try/catch-Struktur folgt. 
			 * 7. Die Kontrolle kehrt nicht zum try-Block zur�ck.
			 */
			
			System.out.println("Bitte einen Wert gr��er 1 eingeben, bis zu dem die Primzahlen berechnet werden sollen und die Eingabetaste bet�tigen: ");
			
			try
			{
				
				
				
				//maxValue = in.nextInt();
				// F�hrt aber bei falscher Eingabe zu einer permanenten Ausgabe
				// der Fehlermeldung, ohne dass auf eine erneute Eingabe gewartet wird.
				
				// Vollst�ndige Zeile aus der Konsole entgegennehmen und anschlie�end
				// versuchen diesen in einen Integer-Wert umzuwandeln.
				eingabe = in.nextLine();
				maxValue = Integer.parseInt(eingabe);
				
				
			}
			catch (InputMismatchException ex)
			{
				System.out.println(ex.getMessage());
			}
			catch (NoSuchElementException ex)
			{
				System.out.println(ex.getMessage());
			}
			catch (IllegalStateException ex)
			{
				System.out.println(ex.getMessage());
			}
			catch (Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			
		}
		
		in.close();
		
		System.out.println("Primzahlenberechnung bis " + NumberFormat.getInstance().format(maxValue));
		
		// Dynamische Berechnung der Spaltenbreite �ber den eingegebenen Maximalwert.
		COLUMN_WIDTH = NumberFormat.getInstance().format(maxValue).length() + 2;
	
		
		for (int i = 2; i <= maxValue; i++)
		{
			
			if (istPrimzahl(i))
			{
				count++;
				System.out.printf("%" + COLUMN_WIDTH + "s", NumberFormat.getInstance().format(i));
				
				if (count % COLUMNS == 0)
					System.out.println();
				

			}
			
		}
		

		System.out.printf("\n\nAnzahl der gefundenen Primzahlen: " + NumberFormat.getInstance().format(count));
		
		
	}
	
	private static boolean istPrimzahl(int zahl)
	{
		
		boolean retValue = true;
		
		
		for (int i = 2; i < zahl; i++)
		{
			if (zahl % i == 0)
			{
				retValue = false;
				break;
			}
			
			
		}
		
		return retValue;
		
	}
	
	

}
