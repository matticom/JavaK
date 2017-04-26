package W3T2_Dozent.AbstrakteKlassen;

public class Arbeiter extends Mitarbeiter
{

	private String vorgesetzter;
	private double stunden, stundenlohn;
	
	
	public Arbeiter(String name, double stunden, double stundenlohn)
	{
		
		// Eine abgeleitete Klasse muss die Basisklasse instanziieren.
		// Standardmässig wird dafür der Standardkonstrukror der Basisklasse verwendet.
		// Die abstrakte Klasse 'Mitarbeiter' hat jedoch nur einen überladenenen Konstruktor.
		// In diesem Fall muss der zu verwendende Konstruktor explizit angegeben werden. 
		super(name);
		
		this.stunden = stunden;
		this.stundenlohn = stundenlohn;
		
	}

	public double getStunden()
	{
		return stunden;
	}

	public double getStundenlohn()
	{
		return stundenlohn;
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
		return getStunden() * getStundenlohn();
	}
	
	
	
}
