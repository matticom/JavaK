package W3T3_Dozent.Schnittstellen;

public abstract class AbstractMitarbeiter //implements IPrintSetup
{

	private String name;
	
	
	public AbstractMitarbeiter(String name)
	{
		
		this.name = name;
		
	}
	
	public String getName()
	{
		return name;
	}
	
	
	public abstract double getGehalt();
	

}
