package W3T3_Dozent.Schnittstellen;

// Eine Schnittstelle kann auch von einer anderen Schnittstelle erben.
// public interface IPrintable extends IPrintSetup
public interface IPrintable
{
	
	// Variablen können ebenfalls in Schnittstellen deklariert werden.
	// Als Modifzierer sind lediglich 'public' und 'static' erlaubt.
	// Die Variablen sind implizit 'final' und 'static'.
	
	public int varPrint = 42;
	
	
	// Für Methoden sind lediglich die Modifizierer 'public' und 'abstract' erlaubt.
	// Der Modifizierer 'abstract' ist implizit, wenn er nicht angegeben wurde.
	public void Print();
	public void ImmediatePrint();
	public void Preview();
	
	// Nachträgliches Hinzufügen von abstrakten Methoden erfordert das Ändern aller
	// Klassen, die diese Schnittstelle implementieren.
	//public void Test();

}
