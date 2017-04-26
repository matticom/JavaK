package W1T3_MK;

import java.util.Random;
import java.util.Scanner;

public class Zahlenraten
{

	public static void main(String[] args)
	{
		int zufallszahl;
		Random zufall = new Random();
		zufallszahl = zufall.nextInt(100)+1;
//		System.err.println(zufallszahl);

		System.out.println("Es wurde eine ganzzahlige Zufallszahl zwischen 1 und  generiert, du hast 7 Versuche sie zu erraten!");
		
		Scanner in = new Scanner(System.in);
		int versuch;
	
		for(int i=1;i<8;i++ )
		{
			System.out.println();
			System.out.printf("Versuch %d:\n",i);
			versuch = in.nextInt();
			if (versuch < zufallszahl)
				System.out.println("Falsch! Die Zahl ist größer!");
			if (versuch > zufallszahl)
				System.out.println("Falsch! Die Zahl ist kleiner!");
			if (versuch == zufallszahl)
			{
				System.out.println("Nice, du hast es erraten!!!");
				break;
			}
			if (i == 7)
			{	
				System.out.println();
				System.out.printf("Schade, du hast es nicht erraten. Die Zahl war %d ! \n",zufallszahl);
			}
		}

			
	}

}
