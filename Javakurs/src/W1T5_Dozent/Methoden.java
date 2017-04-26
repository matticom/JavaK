package W1T5_Dozent;

import java.util.Scanner;

// Schreiben Sie eine Methode Maximum der zwei int-Werte �bergeben werden und die
// den gr��ten der beiden Werte zur�ckliefert.

// Schreiben Sie eine Methode Maximum der drei int-Werte �bergeben werden und die
// den gr��ten der drei Werte zur�ckliefert.
// Versuchen Sie zuerst eine L�sung mit if (und else). 
// Anschlie�end versuchen Sie eine L�sung nur mit Hilfe des
// ?:-Operators.

// Versuchen Sie eine weitere Methode Maximum mit drei int-Werten zu programmieren indem
// in der Sie zu L�sung nur die erste Maximum-Funktion (mit 2 Argumenten) verwenden.

public class Methoden
{

	public static void main(String[] args)
	{
		
		int zahl1, zahl2, zahl3;
		
		
		Scanner in = new Scanner(System.in);
		
		
		System.out.println("Bitte Zahl 1 eingeben: ");
		zahl1 = in.nextInt();
		
		System.out.println("Bitte Zahl 2 eingeben: ");
		zahl2 = in.nextInt();
		
		System.out.println("Bitte Zahl 3 eingeben: ");
		zahl3 = in.nextInt();


		System.out.println("Maximum(" + zahl1 + ", " + zahl2 + ") -> " + Maximum(zahl1, zahl2)); 
		System.out.println("Maximum(" + zahl1 + ", " + zahl2 + ", " + zahl3 + ") -> " + Maximum(zahl1, zahl2, zahl3));
		System.out.println("Maximum3(" + zahl1 + ", " + zahl2 + ", " + zahl3 + ") -> " + Maximum3(zahl1, zahl2, zahl3));
		System.out.println("Maximum_3(" + zahl1 + ", " + zahl2 + ", " + zahl3 + ") -> " + Maximum_3(zahl1, zahl2, zahl3));
	}
	
	private static int Maximum(int a, int b)
	{
		
//		int retValue = a;
//		
//		if (b > a)
//			retValue = b;
//		
//		return retValue;
		
		
		// oder
		return (a > b) ? a : b;
	}
	
	private static int Maximum(int a, int b, int c)
	{
		int retValue = c;
		
		if ( a >= b && a > c)
			retValue = a;
		else if (b > a && b > c)
			retValue = b;
		
		return retValue;
	}
	
	private static int Maximum3(int a, int b, int c)
	{
		
		return ((a >= b && a > c) ? a : (b > a && b > c) ? b : c);
		
	}
	
	private static int Maximum_3(int a, int b, int c)
	{
		
		return Maximum(a, Maximum(b, c));
		
	}
	
}
