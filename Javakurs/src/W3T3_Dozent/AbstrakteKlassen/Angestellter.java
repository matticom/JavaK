package W3T3_Dozent.AbstrakteKlassen;

public class Angestellter extends Mitarbeiter
{
	
	private String vorgesetzter;
	private double gehalt;
	
	public Angestellter(String name, double gehalt)
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
		return gehalt;
	}

	@Override
	public void Anzeige()
	{
		
		System.out.println("Angestellter");
		super.Anzeige();
	}
	
	

	
	
	
}
