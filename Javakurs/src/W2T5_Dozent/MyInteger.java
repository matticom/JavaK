package W2T5_Dozent;

public class MyInteger
{

	public int value;
	
	public MyInteger(int value)
	{
		this.value = value;
	}

	
	public static boolean tryParse(String s, MyInteger i)
	{
		
		boolean retValue = false;
		
		try
		{
			i.value = Integer.parseInt(s);
			retValue = true;
		}
		catch (Exception ex)
		{}
		
		
		
		return retValue;
	}
	
	
	
}
