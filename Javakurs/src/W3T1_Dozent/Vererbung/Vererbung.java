package W3T1_Dozent.Vererbung;

/* Die Klassen in Java sind in einer Hierarchie geordnet. Von Object erben automatisch alle Klassen, direkt oder indirekt. 
 * Eine neu definierte Klasse kann durch das Schlüsselwort extends eine Klasse erweitern. 
 * Sie wird dann zur Unter- oder Subklasse beziehungsweise Kindklasse. Die Klasse, von der die Unterklasse erbt, heißt 
 * Oberklasse (auch Superklasse oder Elternklasse). 
 * Durch den Vererbungsmechanismus werden alle sichtbaren Eigenschaften der Oberklasse auf die Unterklasse übertragen. 
 * Eine Oberklasse vererbt also Eigenschaften, und die Unterklasse erbt sie.
 * Syntaktisch wird die Vererbung durch das Schlüsselwort extends beschrieben.
 *
 */

/* In Java ist auf direktem Weg nur die Einfachvererbung (engl. single inheritance) erlaubt. 
 * In der Einfachvererbung kann eine Klasse lediglich eine andere erweitern. 
 * In Programmiersprachen wie C++ können auch mehrere Klassen zu einer neuen verbunden werden. 
 * Dies bringt aber einige Probleme mit sich, die in Java vermieden werden. 
 * 
 * Beispiel:
 * Nehmen wir an, die Klassen K1 und K2 definieren beide eine öffentliche Funktion f(), und V1 ist eine Klasse, 
 * die von K1 und K2 erbt. Steht in V1 ein Funktionsaufruf f(), ist nicht klar, welche der beiden Funktion gemeint ist. 
 * In C++ löst der Bereichsoperator (::) das Problem, in dem der Entwickler immer angibt, aus welcher Oberklasse 
 * die Funktion anzusprechen ist.
 * 
 */

/*
 * Eine Unterklasse erbt neben den Variablen auch die Methoden der Superklasse. Oft ist das vorgegebene Verhalten 
 * der geerbten Methode in der Unterklasse nicht mehr adäquat, sie muss modifiziert werden. In diesem Fall besteht
 * die Möglichkeit, die geerbte Methode zu überschreiben.
 * Beim Überschreiben oder Overriding von Methoden besitzt eine Methode in einer Unterklasse den Namen und die 
 * Parameterliste einer geerbten Methode. Durch diese neue Methode in der Unterklasse wird die ursprüngliche Methode
 * der Superklasse ersetzt.
 * Alle öffentlichen Methoden können in Java überschrieben werden. Zusätzliche Attribute wie 'virtual' in C++ oder C#
 * oder 'overridable' in VB.Net werden nicht benötigt.   
 * 
 * Ein Überschreiben von Methoden kann mit 'final' verhindert werden (final public void Methode()).  
 * 
 * Klassen die mit 'final' deklariert wurden erlauben keine Spezialisierung durch eine Unterklasse, d.h. von ihnen
 * kann keine Klasse mit 'extends' abgeleitet werden.   
 */

public class Vererbung
{

	public static void main(String[] args)
	{
		
		V1 v1 = new V1();
		v1.f();
		
		
		V2 v2 = new V2();
		v2.f();
		v2.m();
		
		V2_1 v2_1 = new V2_1();
		
		
		

	}

}
