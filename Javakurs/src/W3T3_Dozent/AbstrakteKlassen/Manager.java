package W3T3_Dozent.AbstrakteKlassen;

public class Manager extends Mitarbeiter
{

	private String vorgesetzter;
	private double gehalt;
	
	public Manager(String name, double gehalt)
	{
		super(name);
		
		this.gehalt = gehalt;
	
	}

	@Override
	public void setVorgesetzter(String Name)
	{
		vorgesetzter = Name;
		
	}

	@Override
	public String getVorgesetzter()
	{
		return vorgesetzter;
	}

	@Override
	public double getGehalt()
	{
		
		return gehalt / 12.0; 
	}

	@Override
	public void Anzeige()
	{
		System.out.println("Manager");
		super.Anzeige();
	}
	
	
	
}
