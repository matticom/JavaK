package W3T1_Dozent.Vererbung;

public class V1 extends K1
{

	// Eine Methode mit gleichen Namen und Signatur 
	// überschreibt auch ohne die Annotation '@override'
	// eine evtl. ererbte Methode.
	// Damit kann ein vollständiges Überschreiben oder auch
	// nur ein Erweitern der Methode erreicht werden. 
	
	@Override
	public void f()
	{
		// TODO Auto-generated method stub
		super.f();
		
		// Zusätzliche Anweisungen
		System.out.println("V1.f()");
		
		
	}

	
	
	
	
	
}
