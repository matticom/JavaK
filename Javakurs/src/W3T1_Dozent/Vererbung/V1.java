package W3T1_Dozent.Vererbung;

public class V1 extends K1
{

	// Eine Methode mit gleichen Namen und Signatur 
	// �berschreibt auch ohne die Annotation '@override'
	// eine evtl. ererbte Methode.
	// Damit kann ein vollst�ndiges �berschreiben oder auch
	// nur ein Erweitern der Methode erreicht werden. 
	
	@Override
	public void f()
	{
		// TODO Auto-generated method stub
		super.f();
		
		// Zus�tzliche Anweisungen
		System.out.println("V1.f()");
		
		
	}

	
	
	
	
	
}
