package W2T1_Dozent;

import java.text.NumberFormat;
import java.util.Scanner;

public class StringUndStringBuilder
{

	public static void main(String[] args)
	{
		
		Scanner eingabe = new Scanner(System.in);
		
		long StartTime;
		long EndTime;
		long maxLoop = 50000;
			
		String str;
		
		
		System.out.println("Eingabetaste betätigen zum Starten der String-Methode ...");
		eingabe.nextLine();
		
		StartTime = System.currentTimeMillis();
		str = StringMethode(maxLoop);
		EndTime = System.currentTimeMillis();
				
		System.out.println("\nFertig mit String: Dauer " + NumberFormat.getInstance().format(EndTime - StartTime) + " Millisekunden");
		System.out.println(str.substring(0, 200) + "\n");
		
		System.out.println("Eingabetaste betätigen zum Starten der StringBuilder-Methode ...");
		eingabe.nextLine();
		
		str = "";
		
		StartTime = System.currentTimeMillis();
		str = StringBuilderMethode(maxLoop);
		EndTime = System.currentTimeMillis();
		
		System.out.println("\nFertig mit StringBuilder: Dauer " + NumberFormat.getInstance().format(EndTime - StartTime) + " Millisekunden");
		System.out.println(str.substring(0, 200) + "\n");
		
		eingabe.close();

	}
	
	
	private static String StringMethode(long maxLoop)
	{
		
		String str = "";
		
		for (int i = 1; i <= maxLoop; i++)
			str += "Ausgabe: " + i  + " "; 
				
		return str;
		
	}
	
	private static String StringBuilderMethode(long maxLoop)
	{
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= maxLoop; i++)
			sb.append("Ausgabe: ").append(i).append(" ");
		
		
		return sb.toString();
		
		
	}
	
	
	

}
