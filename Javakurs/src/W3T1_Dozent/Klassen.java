package W3T1_Dozent;

public class Klassen
{

	public static void main(String[] args)
	{
		
		// Beispiel für ein Klassen Template (generische Klasse)
		GenClass<Integer> swapInteger1 = new GenClass<>(22);
		GenClass<Integer> swapInteger2 = new GenClass<>(99);
		System.out.println("SwapInteger 1:" + swapInteger1.value + " SwapInteger 2: " + swapInteger2.value);
		swap(swapInteger1, swapInteger2);
		System.out.println("SwapInteger 1:" + swapInteger1.value + " SwapInteger 2: " + swapInteger2.value);
		System.out.println();
		
		GenClass<Double> swapDouble1 = new GenClass<>(22.11);
		GenClass<Double> swapDouble2 = new GenClass<>(99.55);
		System.out.println("SwapDouble 1:" + swapDouble1.value + " SwapDouble 2: " + swapDouble2.value);
		swap(swapDouble1, swapDouble2);
		System.out.println("SwapDouble 1:" + swapDouble1.value + " SwapDouble 2: " + swapDouble2.value);
		System.out.println();
		
		GenClass<String> swapString1 = new GenClass<>("Links");
		GenClass<String> swapString2 = new GenClass<>("Rechts");
		System.out.println("SwapString 1:" + swapString1.value + " SwapString 2: " + swapString2.value);
		swap(swapString1, swapString2);
		System.out.println("SwapString 1:" + swapString1.value + " SwapString 2: " + swapString2.value);
		System.out.println();
		
		// Die Klasse GCFinalize
		
		GCFinalize o;
		
		for (int i = 1; i <= 1000; i++)
			o = new GCFinalize(repeatString('X', i), i);
		

	}
	
	private static void swap(GenClass a, GenClass b)
	{
		
		Object obj = a.value;
		
		a.value = b.value;
		b.value = obj;
		
		
	}


	private static String repeatString(char c, int len)
	{
		String retValue = "";
		
		while (len-- > 0)
			retValue += c;
		
		
		return retValue;
	}
	

}
