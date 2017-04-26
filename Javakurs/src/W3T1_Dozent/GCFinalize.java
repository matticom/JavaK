package W3T1_Dozent;

public class GCFinalize
{

	private String data;
	private int counter;
	
	
	public GCFinalize(String s, int i)
	{
		
		data = s;
		counter = i;
		
		System.out.printf("Objekt erstellt %d\n", counter);
		
		
	}


	@Override
	protected void finalize() throws Throwable
	{
		// TODO Auto-generated method stub
		super.finalize();
		
		
		// Zusatzliche Ausgabe hinzufügen
		System.out.printf(">> Object wurde entfernt %d\n", counter);
		
	}
	
	
	
	
}
