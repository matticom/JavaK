package W2T2_Dozent;

import java.util.Arrays;
import java.util.Random;

public class Lottozahlen2
{

	private static final int ANZAHL_LOTTOZAHLEN = 6;
	
	public static void main(String[] args)
	{
		// Instanziierung des Ziehungsfeldes,
		int[] lotto = new int[ANZAHL_LOTTOZAHLEN];

		// Initialisierung des Zufallsalgorithmus
		Random zufall = new Random();

		int anzZiehung = 0;
				
		// Werte zuweisen
		for (int i = 0; i< lotto.length; i++)
		{
			
			lotto[i] = zufall.nextInt(49) + 1;
			
			// Duplikatsprüfung
			for (int j = 0; j < i; j++)
			{
				if (lotto[j] == lotto[i])
				{
					
					i--;
					break;
				}
				
			}
			
			anzZiehung++;
		
			
		}
		
		// Unsortierte Ausgabe ohne Zusatzzahl
		System.out.println("\nAnzahl Ziehungen (" + anzZiehung + ")");
		System.out.println("Lottozahlen:");
		for (int i = 0; i < (lotto.length); i++)
			System.out.printf("%3d", lotto[i]);
		System.out.println();
		
		// Einfach
		Arrays.sort(lotto);
		
		// Sortierte Ausgabe
		System.out.println("Lottozahlen:");
		for (int i = 0; i < (lotto.length); i++)
			System.out.printf("%3d", lotto[i]);
		System.out.println();
		
		// Ziehung und Ausgabe der Superzahl
		System.out.println();
		System.out.println("Superzahl:  \t" + zufall.nextInt(10));
		
		
	}

}
