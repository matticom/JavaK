package W2T5_Dozent;

import javax.sound.midi.Synthesizer;

/* 
 * Klassen sind Baupläne für Objekte; so wie ein Bauplan für ein Haus kein Haus ist,
 * ist eine Klasse etwas, was nicht direkt verwendet wird, sondern als Vorschrift
 * zur Erstellung von etwas dient, was man verwenden kann. Wenn sie ein Haus bauen,
 * dann bauen Sie ein neues Haus; wenn sie nach der Bauplan-Vorschrift einer Klasse ein
 * Objekt erstellen (im Speicher aufbauen), dann wird immer das Schlüsselwort 'new'
 * verwendet.
 * 
 * Klassen implementieren beliebig abstrakte, benutzerdefinierte Datentypen. 
 *
 * Das Ziel des Klassenkonzepts ist es, dem Programmierer die Möglichkeit zu geben,
 * neue Datentypen zu schaffen und sie so leicht wie Standard-Typen zu benutzen.
 * Zusätzlich stellen abgeleitete Klassen (Vererbung) Mechanismen bereit, die es
 * dem Programmierer erlauben, ihre Zusammenhänge auszunutzen.
 *
 * Die Verwendung von Klassen bietet mehrere Vorteile. Mit Hilfe geeigneter Zugriffsattribute 
 * lassen sich Daten verpacken und schützen. Man spricht von Kapselung. Die ideale Klasse sieht so aus, 
 * dass von anderen Klassen ausschließlich auf Methoden zugegriffen werden darf. 
 * Alle Eigenschaften - also Instanz- und Klassenvariablen - sind privat und können von außen 
 * nicht geändert werden. 
 *
 * Grundgedanke des Kapselungprinzips : Der Gültigkeitsbereich von Variablen muss so weit wie möglich 
 * eingeschränkt werden. Klassen sind das ideale Instrument, um diesen Grundsatz in Programmen umzusetzen: 
 * Wenn eine Klasse nur private Eigenschaften besitzt, können nur Methoden der Klasse selber Eigenschaften 
 * verändern. Treten in einem Programm Fehler auf, weil irgendwelche Eigenschaften einer Klasse plötzlich 
 * falsche Werte enthalten, so müssen im Idealfall nur die Methoden der Klasse nach dem Fehler durchsucht werden
 * - sie sind schließlich die einzigen,  die auf die Eigenschaften zugreifen dürfen. 
 *
 * Neben Kapselung unterstützen Klassen durch die Vererbung ein Konzept, das Wiederverwendung genannt wird. 
 * Wiederverwendung bedeutet, dass in einem Programm auf Code zurückgegriffen werden kann, der bereits 
 * anderweitig geschrieben wurde. Das Konzept der Wiederverwendung spart nicht nur Arbeit, sondern beugt auch 
 * Fehlern vor. Anstatt dass jeder Programmierer den gleichen Code für sich neu entwickelt, greifen alle 
 * auf den gleichen Code zu. Das bedeutet, jeder Programmierer kann sich wirklich auf neue Aufgaben konzentrieren 
 * und verläßt sich darauf, dass der wiederverwendete Code korrekt arbeitet. 
 * 
 * Der Punkt-Operator ist dazu da, ein Memberelement einer Klasse über eine
 * Objektreferenz anzusprechen.
 *
 *     objektName.memberdatum bzw. objektName.membermethode()
 *
 * Der oder die Konstruktor(en) einer Klasse dient bzw. dienen dem ausdrücklichen
 * Zweck, Objekte -- also Instanzen bzw. konkrete Ausprägungen -- einer Klasse zu
 * initialisieren. Ein Konstruktor einer Klasse ist dadurch gekennzeichnet, dass er
 * denselben Namen wie die zugehörige Klasse trägt.
 * 
 * Neben Konstruktoren gibt es in Java auch Destruktoren. 
 * Ein Destruktor wird als parameterlose Methode mit dem Namen finalize definiert. 
 * 
 * Der Destruktor wird aufgerufen, bevor der Garbage Collector das entsprechende
 * Objekt vom Heap entfernt, d.h. beim Aufruf des Destruktors ist das Objekt "noch
 * so gerade lebendig".
 * 
 * Anders als in C++ ist die Verwendung von Destruktoren in Java eher selten. In C++
 * sind Destruktoren hauptsächlich damit beschäftigt, dynamisch allokierten Speicher
 * freizugeben, ein Problem, was in Java vom Garbage Collector gelöst wird.
 * 
 */

public class Klassen
{

	public static void main(String[] args)
	{
		
		MeineKlasse mk = new MeineKlasse();
		mk.a = 42;
		System.out.println("MeineKlasse.a = " + mk.a);
		System.out.println();
		
		// Die Klasse 'Tag'
		Tag  t = new Tag();
		t.setTag(14);
		System.out.println("Tag: " + t.getTag());
		System.out.println();
		
		Tag t1 = new Tag(32);
		System.out.println("Tag: " + t1.getTag());
		System.out.println();
		
		
		// Die Klasse 'Monat'
		
		Monat m = new Monat();
		System.out.println(m.getMonat() + " - " + m.getMonatsname());
		
		m.setMonat(7);
		System.out.println(m.getMonat() + " - " + m.getMonatsname());
		
		m.setMonat(13);
		System.out.println(m.getMonat() + " - " + m.getMonatsname());
		
		Monat m1 = new Monat(11);
		System.out.println(m1.getMonat() + " - " + m1.getMonatsname());

		System.out.println();
		
		// Verwendung von statischen Variablen und Methoden
        // Statische Inhalte (Variablen/Methoden) sind im gleichen
        // Ausführungsobjekt
        // 		- in allen Instanzen dieser Klasse gleich,
        // 		- auch verfügbar, wenn keine Instanz der Klasse existiert.
		
		System.out.println("Default Monat: " + Monat.getDefaultMonat());
		
		Monat.setDefaultMonat(7);
		System.out.println("Default Monat: " + Monat.getDefaultMonat());
		
		Monat m2 = new Monat();
		System.out.println(m2.getMonat() + " - " + m2.getMonatsname());
		
		// Öffentliche statische Inhalte können sowohl über den Namen der Klasse
        // als auch über die Instanz einer Klasse angesprochen werden.
		m.setDefaultMonat(1);
		Monat m3 = new Monat();
		System.out.println(m3.getMonat() + " - " + m3.getMonatsname());
		System.out.println();
		

		// Die Klasse 'Punkt'
		Punkt p1 = new Punkt(2, 4);
		System.out.printf("P1 = (%d, %d)\n", p1.x, p1.y);
		System.out.println();
	
		
		// Die Klasse 'Rechteck'
		Rechteck r = new Rechteck(2, 4, 100, 200);
		System.out.printf("Rechteck Punkt P0 = (%d, %d)\n", r.p0.x, r.p0.y);
		System.out.printf("Rechteck Punkt P1 = (%d, %d)\n", r.p1.x, r.p1.y);
		System.out.printf("Rechteck Breite   = %d\n", r.getBreite());
		System.out.printf("Rechteck Höhe     = %d\n", r.getHoehe());
		System.out.printf("Rechteck Fläche   = %d\n", r.getFlaeche());
		System.out.println();
		
		
		// Die Klasse 'Farbe'
		Farbe f1 = new Farbe(255, 0, 0);
		
		// Die Klasse 'Gebilde'
		//Gebilde g1 = new Gebilde(2, 4, 100, 200, f1);
		
		Gebilde g1 = new Gebilde(2, 4, 100, 200, new Farbe(0, 255, 0));
		
		Farbe f2 = g1.getFarbe();
		System.out.printf("Farbe(%d, %d, %d)\n", f2.rot, f2.gruen, f2.blau);
		
		f2.rot = 255;
		
		System.out.printf("Gebilde-Rechteck-P0(%d, %d)\n", g1.getRechteck().p0.x, g1.getRechteck().p0.y);
		System.out.printf("Gebilde-Rechteck-P1(%d, %d)\n", g1.getRechteck().p1.x, g1.getRechteck().p1.y);
		System.out.printf("Gebilde-Rechteck-Breite = %d\n", g1.getRechteck().getBreite());
		System.out.printf("Gebilde-Rechteck-Höhe   = %d\n", g1.getRechteck().getHoehe());
		System.out.printf("Gebilde-Rechteck-Fläche = %d\n", g1.getRechteck().getFlaeche());
		System.out.printf("Gebilde-Farbe(%d, %d, %d)\n", g1.getFarbe().rot, g1.getFarbe().gruen, g1.getFarbe().blau);
		System.out.println();
		
		// Innere Klassen (geschachtelte Klassen)
		
		Haus h = new Haus();
		Haus.Zimmer z = h.new Zimmer();
		Haus.Zimmer.Schrank s = z.new Schrank();
		s.Ausgabe();
		
		System.out.println();
		
		// Übergabe von Elementaren Datentypen als Referenz mit Hilfe von Klassen
        //
		
		
	    // Wrapper-Objekte sind »immutable« (unveränderbar).
        // Ist ein Wrapper-Objekt erst einmal erzeugt, lässt sich der im Wrapper-Objekt gespeicherte Wert nachträglich
        // nicht mehr verändern. 
        // Um dies auch wirklich sicherzustellen, sind die konkreten Wrapper-Klassen allesamt final. 
        // Die Wrapper-Klassen sind nur als Ummantelung und nicht als vollständiger Datentyp gedacht. 
        // Da sich der Wert nicht mehr ändern lässt, heißen Objekte mit dieser Eigenschaft auch Werte-Objekte.
		
		Integer i1 = new Integer(42);
		// oder
		Integer i2 = 10;
		
		System.out.println("i1 = " + i1 + ", i2 = " + i2);
		swap(i1, i2);
		System.out.println("i1 = " + i1 + ", i2 = " + i2);
		
		
		// Tauschen der Werte a und b
		MyInteger a = new MyInteger(42);
		MyInteger b = new MyInteger(10);
	
		System.out.println("a = " + a.value + ", b = " + b.value);
		swap(a, b);
		System.out.println("a = " + a.value + ", b = " + b.value);
		
		
		if (MyInteger.tryParse("12345", b))
			System.out.println("Wert: " + b.value);
		else
			System.out.println("Umwandlung war nicht erfolgreich");
			
			
		if (MyInteger.tryParse("alfa", b))
			System.out.println("Wert: " + b.value);
		else
			System.out.println("Umwandlung war nicht erfolgreich");
		
		
		
		
		
		
		
		
	}
	
	
	private static void swap(Integer a, Integer b)
	{
		
		Integer i = a;
		
		a = b;
		b = i;
		
		
		System.out.printf("swap: a = %d, b = %d\n", a.intValue(), b.intValue());
		
	}
	

	private static void swap(MyInteger a, MyInteger b)
	{
		
		int i = a.value;
		
		a.value = b.value;
		b.value  = i;
		
	}
	
}
