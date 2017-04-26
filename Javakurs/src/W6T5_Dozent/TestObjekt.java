package W6T5_Dozent;

import java.io.Serializable;

public class TestObjekt implements Serializable
{

	private String Name;
	private int Ganzzahl;
	public TestObjekt next;
		
	public TestObjekt(String Name, int Ganzzahl)
	{
		
		this.Name = Name;
		this.Ganzzahl = Ganzzahl;
		this.next = null;
		
	}

	@Override
	public String toString()
	{
		return "TextObjekt: " + Name + " " + String.valueOf(Ganzzahl);
	}
	
	
}
