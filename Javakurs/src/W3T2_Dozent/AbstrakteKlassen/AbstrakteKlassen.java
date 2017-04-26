package W3T2_Dozent.AbstrakteKlassen;


// Abstrakte Klassen k�nnen nicht instanziiert werden.
// Eine abstrakte Klasse kann nur Basisklasse (Superklasse) von anderen Klassen sein.

// Abstrakte Klassen k�nnen (m�ssen aber nicht) neben konkreten Methoden auch abstrakte Methoden enthalten.
// Eine abstrakte Methode hat keinen K�rper (sie enth�lt keine Anweisungen).
// Eine abstrakte Methode definiert lediglich die Signatur, und eine Unterklasse implementiert dann irgendwann diese Methode. 
// Die Klasse ist dann nur f�r den Kopf der Methode zust�ndig, w�hrend die Implementierung an anderer Stelle erfolgt. 
// Durch abstrakte Methoden wird ausgedr�ckt, dass die Oberklasse keine Ahnung von der Implementierung hat und dass sich die 
// Unterklassen darum k�mmern m�ssen.
// Dieses Konzept erlaubt es Klassen als Vorlagen zu implementieren und gleichzeitig zu erzwingen, dass die Entwickler der 
// Unterklasse fehlende oder eigene Methoden und Attribute zur Verf�gung stellen. 
// Eine nicht-abstrakte Subklasse erbt die abstrakten Methoden und muss alle abstrakten Methoden (sinnvoll) implementieren. 


public class AbstrakteKlassen
{

	public static void main(String[] args)
	{
		
		// Geht nicht, da die Klasse 'Mitarbeiter' abstrakt ist.
		//Mitarbeiter m = new Mitarbeiter("Meier");
		
		
	}

}
