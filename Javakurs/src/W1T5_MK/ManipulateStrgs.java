package W1T5_MK;

import java.util.Scanner;

public class ManipulateStrgs
{

	private static char zeichen;
	private static String substr;
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Geben sie eine Zeichenkette ein:");
		String str = "viel vor viel dahinter, noch viel viel mehr vor, aber viel viel weniger dahinter"; //in.nextLine();  
//		System.out.println("Geben sie das Zeichen ein, was sie aussortiert haben wollen:");
//		String zeichenstr = in.nextLine();
//		ManipulateStrgs.zeichen = zeichenstr.charAt(0);
		System.out.println("Geben sie die Zeichenkette an, welche in der angegebenen Zeichenkette gesucht werden soll:");
		substr = in.nextLine();
				
//		System.out.printf("\nDie Zeichenkette enthält %d Digits", digitCount(str));
//		System.out.printf("\nUmgedreht heißt die Zeichenkette: %s", stringReverse(str));
//		System.out.printf("\nDie Zeichenkette ohne das gewünschte Zeichen am Anfang: %s", lTrim(str));
//		System.out.printf("\nDie Zeichenkette ohne das gewünschte Zeichen am End: %s", rTrim(str));
//		System.out.printf("\nDie Zeichenkette ohne das gewünschte Zeichen am Anfang und End: %s", Trim(str));
		System.out.printf("\nDie gesuchte Zeichenkette befindet sich %d mal in der eingegebenen Zeichenkette.", subStringCount(str));

	}
	
	private static int digitCount(String str)
	{
		int anzahl = 0;
		for(int i=0;i< str.length();i++)
		{
			
			if ((str.charAt(i) >= 48) && (str.charAt(i) <= 57))
				anzahl++;
		}
		return anzahl; 
	}
	
	private static String stringReverse(String str)
	{
		String strReverse = "";
		for(int i=str.length()-1 ; i>=0 ; i--)
		{
			strReverse = strReverse + str.charAt(i);			
		}
		return strReverse;
	}
	
//	private static String lTrim(String str)
//	{
//		String strlTrim = "";
//		for(int i=0;i< str.length();i++)
//		{
//			
//			if (str.charAt(i) == 32)
//				;
//			else
//				strlTrim = strlTrim + str.charAt(i);			
//						
//		}
//		return strlTrim;
//	}
	
	private static String lTrim(String str)
	{
		int z = 0;
		for(int i=0;i< str.length();i++)
		{			
			if (str.charAt(i) == ManipulateStrgs.zeichen)
				continue;
			else
				z = i;
				break;						
		}
		return str.substring(z);
	}
	
	private static String rTrim(String str)
	{
		int z = 0;
		for(int i=str.length()-1 ;i>= 0;i--)
		{			
			if (str.charAt(i) == ManipulateStrgs.zeichen)
				continue;
			else
				z = i;
				break;						
		}
		return str.substring(0, z+1);
	}
	
	private static String Trim(String str)
	{
		int start = 0;
		int ende = str.length()-1;
		
		// return rTrim(lTrim(str));
		
		for(int i=0; i< str.length();i++)
		{			
			if (str.charAt(i) == ManipulateStrgs.zeichen)
				continue;
			else
				start = i;
				break;						
		}
	
		for(int i=str.length()-1 ;i>= 0;i--)
		{			
			if (str.charAt(i) == ManipulateStrgs.zeichen)
				continue;
			else
				ende = i;
				break;						
		}
		
		return str.substring(start, ende+1);
	}
	
	private static int subStringCount(String str)
	{
	
		int anzahl = 0;
		for(int i=0; i< str.length();)
		{			
			if ((i+ ManipulateStrgs.substr.length()<= str.length()) && 
				(ManipulateStrgs.substr.compareTo(str.substring(i, i+ ManipulateStrgs.substr.length())) == 0))
			{
				anzahl++;
				i+=ManipulateStrgs.substr.length();
			}
			else
				i++;
		}
		return anzahl;
	
	}
}



















