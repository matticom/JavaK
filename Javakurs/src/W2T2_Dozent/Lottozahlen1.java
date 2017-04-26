package W2T2_Dozent;

import java.util.Arrays;
import java.util.Random;

public class Lottozahlen1
{

	private static final int ANZAHL_ZIEHUNGEN = 6;
	
	public static void main(String[] args)
	{
		
		// Instanziierung des Ziehungsfeldes,
		int[] lotto = new int[ANZAHL_ZIEHUNGEN];

		// Initialisierung des Zufallsalgorithmus
		Random zufall = new Random();
		
		// Ziehung mit Duplikatsvermeidung
		int anzZiehung = 0;
		boolean mehrfach;
			
		do
		{
			
			// 6 Werte zuweisen
			for (int i = 0; i < lotto.length; i++)
				lotto[i] = zufall.nextInt(49) + 1;
			
			
			mehrfach = false;
			
			for (int i = 0; i < lotto.length - 1; i++)
			{
				for (int j = i + 1; j < lotto.length; j++)
				{
					if (lotto[i] == lotto[j])
					{
						mehrfach = true;
						break;
					}
				}

				if (mehrfach)
					break;
			}
			
			anzZiehung++;
		}
		while (mehrfach);
		
		// Unsortierte Ausgabe ohne Zusatzzahl
		System.out.println("\nAnzahl Ziehungen (" + anzZiehung + ")");
		System.out.println("Lottozahlen:");
		for (int i = 0; i < (lotto.length); i++)
			System.out.printf("%3d", lotto[i]);
		System.out.println();
		
		// Einfach
		//Arrays.sort(lotto);
		
		// Algorithmisches Sortieren
		sort(lotto, 0, 6);
		
		
		// Sortierte Ausgabe
		System.out.println("Lottozahlen:");
		for (int i = 0; i < (lotto.length); i++)
			System.out.printf("%3d", lotto[i]);
		System.out.println();
		
		// Ziehung und Ausgabe der Superzahl
		System.out.println();
		System.out.println("Superzahl:  \t" + zufall.nextInt(10));

	}
	
	private static void sort(int[] lotto, int startIndex, int anzahlElemente)
	{
		for (int i = startIndex; i <= anzahlElemente; i++)
		{
			for (int j = i + 1; j < anzahlElemente; j++)
			{
				if (lotto[i] > lotto[j]) // Linker Wert ist grösser als der Rechte
				{
					// falls der jeweils linke Wert größer ist
					// als der rechte Wert, dann wird vertauscht
					int tmp = lotto[i]; // linker Wert temporär sichern
					lotto[i] = lotto[j]; // linker Wert mit rechtem Wert
											// überschreiben
					lotto[j] = tmp; // rechter Wert mit linkem Wert
									// überschreiben
				}
			}
		}

	}
	
	
	

}
