package W1T4_MK;

import java.util.Scanner;

public class Primzahlen
{

	private static final int ABSATZ = 10;
	
	public static void main(String[] args)
	{
		System.out.println("Bis zu welchem Wert sollen die Primzahlen berechnet werden?:");
		Scanner in = new Scanner(System.in);
		int ende;
		ende = in.nextInt();
		System.out.println();
		primErkennung(ende);
		in.close();
				 

	}
	
	private static void primErkennung(int primzahl)
	{
		int absatz = 1;
		int anzahl = 1;
		
		//	Eingabe kleiner als 2	
		if (primzahl<2)
		{
			System.out.println("Die Primzahlen beginnen bei 2!");
			return;
		}
		
		// Eingabe genau 2
		if (primzahl==2)
		{
			System.out.printf("%15d",2);
			return;
		}
		// Eingabe größer 2
		else
		{
			System.out.printf("%15d",2);
		}
			
		// Schleife1 durch alle Zahlen von 3 bis zur eingegebenen Primzahl
		for (int b = 3; b <= primzahl; b+=2 )
		{
//												
			// Falls istPrimzahl komplett durch gelaufen ist ohne in if Zweig zu gelangen --> Primzahl
			
			if (istPrimzahl(b) == true)
			{
				System.out.printf("%15d",b);
				anzahl++;
				absatz++;
				if (absatz == Primzahlen.ABSATZ)
				{
					System.out.println();
					absatz = 0;
				}
			}
			
		}
		System.out.printf("\n\nAnzahl der Primzahlen: %4d\n",anzahl);

	}
	
	private static boolean istPrimzahl(int b)
	{
		if ((b % 2) == 0)
		{
			return false;
		}
		else
		{
			for (int i = 3; i < (b/2+1); i+=2)
			{				
				if ((b % i) == 0)
				{
					return false;											
				}														
			}
		}
		return true;
	}

}
