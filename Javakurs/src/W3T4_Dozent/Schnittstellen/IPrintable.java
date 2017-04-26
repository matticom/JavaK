package W3T4_Dozent.Schnittstellen;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

// Eine Schnittstelle kann auch von einer anderen Schnittstelle erben.
// public interface IPrintable extends IPrintSetup
public interface IPrintable
{
	
	// Variablen k�nnen ebenfalls in Schnittstellen deklariert werden.
	// Als Modifzierer sind lediglich 'public' und 'static' erlaubt.
	// Die Variablen sind implizit 'final' und 'static'.
	
	public int varPrint = 42;
	
	
	// F�r Methoden sind lediglich die Modifizierer 'public' und 'abstract' erlaubt.
	// Der Modifizierer 'abstract' ist implizit, wenn er nicht angegeben wurde.
	public void Print();
	public void ImmediatePrint();
	public void Preview();
	
	// Nachtr�gliches Hinzuf�gen von abstrakten Methoden erfordert das �ndern aller
	// Klassen, die diese Schnittstelle implementieren.
	//public void Test();

	// Ab Java 8 lassen sich in Schnittstellen auch statische Methoden als
	// Hilfsmethoden implementieren. Diese Methoden besitzen, im Gegensatz zu 
	// abstrakten Methoden einen Methodenrumpf.
	// Ein Zugriff auf die statische Schnittstellenmethode ist ausschliessliche �ber den Namen
	// der Schnittstelle m�glich. Sie muss auch nicht implementiert werden.
	// Bei statischen Methoden von Klassen ist im Prinzip auch ein Zugriff �ber eine Referenz
	// erlaubt. Bei statischen Methoden einer Schnittstelle ist das nicht zul�ssig.
	
	// Beispiel: Methode zur Ermittlung des Standarddruckers.
	public static String getDefaultPrintService()
	{
		String retValue = null;
		
		PrintService service = PrintServiceLookup.lookupDefaultPrintService();
		if (service != null)
			retValue = service.getName();
		

		return retValue;
	}
	
	// Default Methoden
	// Ist eine Schnittstelle einmal verbreitet, so sollte es dennoch m�glich sein, Operationen hinzuzuf�gen.
	// Java 8 bringt daf�r eine Sprach�nderung mit die es erlaubt, Operationen einzuf�gen, ohne das die Unterklassen
	// verpflichtet werden diese Methoden zu implementieren.
	// Eine Default-Methode unterscheidet sich syntaktisch in 2 Dingen von herk�mmlichen implizit abstrakten
	// Methodendeklarationen:
	// 1. Die Deklaration einer Default-Methode beginnt mit dem Schl�sselwort 'default'.
	// 2. Statt eines Semikolons markiert bei einer Default-Methode ein Block von geschweiften Klammern das
	//    Ende der Deklaration (sie enth�lt einen Methodenrumpf).
	
	// Die Verwendung von Default-Methoden ist vergleichbar mit normalen nicht-finalen Methoden:
	// Sie k�nnen, m�ssen aber nicht �berschrieben werden.
	
	public default boolean pause()
	{
		return true;
	}
	
	
}
