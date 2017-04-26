package W2T1_MK;

import java.util.Arrays;
import java.util.Random;


public class Lottoziehung
{
	
	
	public static void main(String[] args)
	{
		Random zahl = new Random();
		
		int[] lotto = new int[7];
		int gezogeneZahl;
		int i = 0;
		
		while (i<7)
		{
			if (i<6)
			{
				gezogeneZahl = zahl.nextInt(49)+1;
				
				// Suche, ob gezogene Zahl bereits existiert 
				if (Arrays.binarySearch(lotto, gezogeneZahl) >= 0)
					continue;
				else
				{
					// wenn nicht vorhanden, ins Array schreiben und sortieren für nächste binäre Suche
					lotto[1] = gezogeneZahl;
					Arrays.sort(lotto);
					i++;
				}
			}
			else
			{
				//Superzahl zum Schluss an Start des Arrays
				lotto[0] = zahl.nextInt(10);
				i++;
			}							
		}
		
		Ausgabe(lotto);
		System.out.println("\nDie SuperZahl lautet: " + lotto[0]);

	}
	
	private static void Ausgabe(int[] arr)
	{
		System.out.println("Die Lottozahlen sind: ");
		
		for (int i = 1; i < arr.length; i++)
			System.out.printf("%4d", arr[i]);
		
		System.out.println();
		
	}

}
