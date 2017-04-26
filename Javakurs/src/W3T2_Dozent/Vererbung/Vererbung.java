package W3T2_Dozent.Vererbung;

import java.util.LinkedList;
import java.util.List;

/* Die Klassen in Java sind in einer Hierarchie geordnet. Von Object erben automatisch alle Klassen, direkt oder indirekt. 
 * Eine neu definierte Klasse kann durch das Schl�sselwort extends eine Klasse erweitern. 
 * Sie wird dann zur Unter- oder Subklasse beziehungsweise Kindklasse. Die Klasse, von der die Unterklasse erbt, hei�t 
 * Oberklasse (auch Superklasse oder Elternklasse). 
 * Durch den Vererbungsmechanismus werden alle sichtbaren Eigenschaften der Oberklasse auf die Unterklasse �bertragen. 
 * Eine Oberklasse vererbt also Eigenschaften, und die Unterklasse erbt sie.
 * Syntaktisch wird die Vererbung durch das Schl�sselwort extends beschrieben.
 *
 */

/* In Java ist auf direktem Weg nur die Einfachvererbung (engl. single inheritance) erlaubt. 
 * In der Einfachvererbung kann eine Klasse lediglich eine andere erweitern. 
 * In Programmiersprachen wie C++ k�nnen auch mehrere Klassen zu einer neuen verbunden werden. 
 * Dies bringt aber einige Probleme mit sich, die in Java vermieden werden. 
 * 
 * Beispiel:
 * Nehmen wir an, die Klassen K1 und K2 definieren beide eine �ffentliche Funktion f(), und V1 ist eine Klasse, 
 * die von K1 und K2 erbt. Steht in V1 ein Funktionsaufruf f(), ist nicht klar, welche der beiden Funktion gemeint ist. 
 * In C++ l�st der Bereichsoperator (::) das Problem, in dem der Entwickler immer angibt, aus welcher Oberklasse 
 * die Funktion anzusprechen ist.
 * 
 */

/*
 * Eine Unterklasse erbt neben den Variablen auch die Methoden der Superklasse. Oft ist das vorgegebene Verhalten 
 * der geerbten Methode in der Unterklasse nicht mehr ad�quat, sie muss modifiziert werden. In diesem Fall besteht
 * die M�glichkeit, die geerbte Methode zu �berschreiben.
 * Beim �berschreiben oder Overriding von Methoden besitzt eine Methode in einer Unterklasse den Namen und die 
 * Parameterliste einer geerbten Methode. Durch diese neue Methode in der Unterklasse wird die urspr�ngliche Methode
 * der Superklasse ersetzt.
 * Alle �ffentlichen Methoden k�nnen in Java �berschrieben werden. Zus�tzliche Attribute wie 'virtual' in C++ oder C#
 * oder 'overridable' in VB.Net werden nicht ben�tigt.   
 * 
 * Ein �berschreiben von Methoden kann mit 'final' verhindert werden (final public void Methode()).  
 * 
 * Klassen die mit 'final' deklariert wurden erlauben keine Spezialisierung durch eine Unterklasse, d.h. von ihnen
 * kann keine Klasse mit 'extends' abgeleitet werden.   
 */

public class Vererbung
{

	public static void main(String[] args)
	{
		
			
		
		Wolf w = new Wolf();
		w.fressen();
		w.geschlecht();
		System.out.println();
		
		
		Polarwolf pw = new Polarwolf();
		pw.fressen();
		
		System.out.println();
		
		
		// Polymorphie bei Vererbung.
		// Objektvariablen k�nnen Objekte unterschiedlicher Klassen aufnehmen.
		// Das ist allerdings beschr�nkt auf die Oberklasse und die
		// davon abgeleiteten Klassen. Beispiel aus der Vererbung: Tier -> Wolf
		// -> Polarwolf... oder Tier -> Fisch -> Goldfisch...
		// Der Wolf besitzt alle Eigenschaften von Tier. Dass der Wolf noch ein
		// paar zus�tzliche Eigenschaften besitzt st�rt den Kompiler nicht.
		
		// Erstellen einer typisierten Liste vom Typ 'Tier'
		
		List<Tier> tiere = new LinkedList<>();
		
		// Hinzuf�gen von unterschiedlichen Tieren, die alle von der Basisklasse
		// 'Tier' abgeleitet wurden.
		
		tiere.add(new Tier());
		tiere.add(new Wolf());
		tiere.add(new Polarwolf());
		tiere.add(new WeisserSchaeferhund());
		tiere.add(new Fisch());
		tiere.add(new Goldfisch());
		tiere.add(new AnderesTier());
			
		// Ausgabe
		for (Tier aktuellesTier : tiere)
		{
			
			System.out.println(aktuellesTier.getClass());
			aktuellesTier.fressen();
			
			
			// Wenn 'aktuellesTier' eine Instanz von 'Wolf' enth�lt
			if (aktuellesTier instanceof Wolf)
				// Konvertieren in WOlf und Aufruf der Methode geschlecht()
				((Wolf)aktuellesTier).geschlecht();
			
			// Der 'Polarwolf' wurd zwar von 'Wolf' abgeleitet
			// aber nur die Klasse 'Polarwolf' enth�lt die Methode farbe()
			if (aktuellesTier instanceof Polarwolf)
				((Polarwolf)aktuellesTier).farbe();
			
			
			System.out.println();
			
		}
		
		System.out.println();
		
		System.out.println(pw.getClass());
		pw.fressen();
		
		// Das Konvertieren von 'Polarwolf' in die Basisklasse 'Tier'
		// hat keinen Einfluss, auf die zu diesem Zeitpunkt von
		// 'Polarwolf' �berschriebenen Methode fressen().
		// Die originalen Methoden der Klasse 'Tier' sind �berschrieben
		// und stehen nicht mehr zur Verf�gung (late Binding).
		Tier t = (Tier)pw;
		t.fressen();
		
		
		
		
		
	

	}

}
