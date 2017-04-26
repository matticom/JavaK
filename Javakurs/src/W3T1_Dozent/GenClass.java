package W3T1_Dozent;

// Bei einer generischen Klasse ist der Datentyp nicht fest vorgegeben. 
// Vielmehr kann durch Angabe von Typparametern der Datentyp an die Aufgabe angepasst werden. 
// Man spricht daher von parametrischer Polymorphie.
// Generische Klassen, (besonders) generische Schnittstellen und generische Delegaten dienen dazu gleiches 
// Verhalten f�r verschiedene Datentypen zu definieren.

// Template Klasse f�r einen beliebigen Datentypen.
// Hinter dem Klassennamen werden formale Typparameter angegeben. Es sind in der Regel einzelne Gro�buchstaben wie T (steht f�r Typ), 
// E (Element), K (Key/Schl�ssel), V (Value/Wert).  Sie sind nur Platzhalter und keine wirklichen Typen.
// F�r einen Template Datentyp  sind nicht alle Operatoren erlaubt.
// Ausgeschlossen sind z.B. alle mathematischen Operatoren und alle Vergleichsoperatoren.

public class GenClass<T>
{
	
	public T value;
		
	public GenClass(T value)
	{
		this.value = value;	
	}
	
}
