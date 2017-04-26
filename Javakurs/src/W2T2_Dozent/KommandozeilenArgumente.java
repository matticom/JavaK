package W2T2_Dozent;

public class KommandozeilenArgumente
{

	public static void main(String[] args)
	{
		
		
		if (args.length == 0)
			System.out.println("Es wurden keine Kommandozeilenargumente übergeben.");
		else
			Argumente(args);

	}
	
	
	private static void Argumente(String[] args)
	{
		
		for (String s : args)
			System.out.println(s);
		
		System.out.println();
		
	}
	

}
