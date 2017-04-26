package W2T2_Dozent;

import java.util.Random;

public class Lottozahlen3
{

	private static final int ANZAHL_LOTTOZAHLEN = 6;
	
	public static void main(String[] args)
	{
		
		boolean[] lotto = new boolean[50];
		Random zufall = new Random();
		
		int index = 0;
		int anzahl = 0;
		
		while (anzahl < ANZAHL_LOTTOZAHLEN) 		
		{
			index = zufall.nextInt(49) + 1;
			
			if (!lotto[index]) // wenn der Index noch nicht gesetzt ist
			{
				lotto[index] = true;
				anzahl++;
			}

		}
		
		// Ausgabe der Ziehung
		// Die Sortierung bekommen wir implizit über die Feld-Indizierung
		// "geschenkt"
		System.out.println("Lottozahlen:");
		for (int i = 1; i < lotto.length; i++)
			if (lotto[i]) // nur Index ausgeben, falls 'true'
			   System.out.printf("%3d", i);
		
		  // Ziehung und Ausgabe der Superzahl
	      System.out.println();
	      System.out.println("Superzahl:  \t" + zufall.nextInt(10));
	      
	}

}
