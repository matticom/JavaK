package W2T5_Dozent;

public class Punkt
{
	
	public int x, y;
	
	
	public Punkt(int x, int y)
	{
		
		// Die lokalen Variablen 'x' und 'y' verdecken die Objektvariablen 'x' und 'y'.
		// Das hei�t aber nicht, dass auf die �usseren Variablen nicht mehr
		// zugegriffen werden kann. Mit der this-Referenz kann auf das aktuelle
		// Objekt zugegriffen werden und mit dem Punkt-Operator auf Variablen
		// des Objekts.
		
		this.x = x;
		this.y = y;
		
	}
	
	
	

}
