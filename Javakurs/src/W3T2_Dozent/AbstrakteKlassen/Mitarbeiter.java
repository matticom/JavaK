package W3T2_Dozent.AbstrakteKlassen;

import java.text.NumberFormat;

public abstract class Mitarbeiter
{

	private String Name;
	
	
	public Mitarbeiter(String Name)
	{
		
		this.Name = Name;
		
	}
	
	
	public String getName()
	{
		return Name;
	}
	
	
	
	public void Anzeige()
	{
		
		System.out.println("Name: " + getName());
		System.out.println("Vorgesetzter: " + getVorgesetzter());
		System.out.printf("Gehalt: %s\n", NumberFormat.getCurrencyInstance().format(getGehalt()));
		System.out.println("---------------------------------------------------------");
		
	}
	
	
	// Abstrakte Methoden
	public abstract void setVorgesetzter(String Name);
	public abstract String getVorgesetzter();
	public abstract double getGehalt();
	
	
}
