package W1T4_MK;

import java.util.Scanner;

public class Methoden
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int a, b, c;
		
//		// Zwei Zahlen vergleich
//		System.out.println("Tippen sie eine Ganzzahl a ein, die mit der Ganzzahl b verglichen werden soll\n(die größere der beiden Zahlen wird ermittelt)");
//		a = in.nextInt();
//		System.out.println();
//		System.out.println("Tippen sie die Ganzzahl b ein");
//		b = in.nextInt();
//		System.out.println();
//		System.out.println("Die größte Zahl ist: "+ max(a,b));
		
		
		// Drei Zahlen vergleichen
		System.out.println("Tippen sie eine Ganzzahl a ein, die mit der Ganzzahl b und c verglichen werden soll\n(die größste der Zahlen wird ermittelt)");
		a = in.nextInt();
		System.out.println();
		System.out.println("Tippen sie die Ganzzahl b ein");
		b = in.nextInt();
		System.out.println();
		System.out.println("Tippen sie die Ganzzahl c ein");
		c = in.nextInt();
		System.out.println();
		System.out.println("Die größte Zahl ist: "+ maxK(a,b,c));
	}
	
	private static int max(int a, int b)
	{
		if (a>b)
			return a;
		else if (a<b)
			return b;
		else
			return a;
	}
	
	private static int max(int a, int b, int c)
	{
		int max;
		
		if (a>b)
			max = a;
		else if (a<b)
			max = b;
		else
			max = a;
		
		if (max>c)
			return max;
		else if (max<c)
			return c;
		else
			return c;
	}
	
	private static int maxK(int a, int b, int c)
	{
		int max;
		max = (a > b) ? a : b;
		return (max > c) ? max : c;
		
	}
	
	private static int maxF(int a, int b, int c)
	{
		return max(max(a,b),c);
	}
	
}
