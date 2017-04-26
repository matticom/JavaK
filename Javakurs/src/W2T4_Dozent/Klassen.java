package W2T4_Dozent;

/* 
 * Klassen sind Baupl�ne f�r Objekte; so wie ein Bauplan f�r ein Haus kein Haus ist,
 * ist eine Klasse etwas, was nicht direkt verwendet wird, sondern als Vorschrift
 * zur Erstellung von etwas dient, was man verwenden kann. Wenn sie ein Haus bauen,
 * dann bauen Sie ein neues Haus; wenn sie nach der Bauplan-Vorschrift einer Klasse ein
 * Objekt erstellen (im Speicher aufbauen), dann wird immer das Schl�sselwort 'new'
 * verwendet.
 * 
 * Klassen implementieren beliebig abstrakte, benutzerdefinierte Datentypen. 
 *
 * Das Ziel des Klassenkonzepts ist es, dem Programmierer die M�glichkeit zu geben,
 * neue Datentypen zu schaffen und sie so leicht wie Standard-Typen zu benutzen.
 * Zus�tzlich stellen abgeleitete Klassen (Vererbung) Mechanismen bereit, die es
 * dem Programmierer erlauben, ihre Zusammenh�nge auszunutzen.
 *
 * Die Verwendung von Klassen bietet mehrere Vorteile. Mit Hilfe geeigneter Zugriffsattribute 
 * lassen sich Daten verpacken und sch�tzen. Man spricht von Kapselung. Die ideale Klasse sieht so aus, 
 * dass von anderen Klassen ausschlie�lich auf Methoden zugegriffen werden darf. 
 * Alle Eigenschaften - also Instanz- und Klassenvariablen - sind privat und k�nnen von au�en 
 * nicht ge�ndert werden. 
 *
 * Grundgedanke des Kapselungprinzips : Der G�ltigkeitsbereich von Variablen muss so weit wie m�glich 
 * eingeschr�nkt werden. Klassen sind das ideale Instrument, um diesen Grundsatz in Programmen umzusetzen: 
 * Wenn eine Klasse nur private Eigenschaften besitzt, k�nnen nur Methoden der Klasse selber Eigenschaften 
 * ver�ndern. Treten in einem Programm Fehler auf, weil irgendwelche Eigenschaften einer Klasse pl�tzlich 
 * falsche Werte enthalten, so m�ssen im Idealfall nur die Methoden der Klasse nach dem Fehler durchsucht werden
 * - sie sind schlie�lich die einzigen,  die auf die Eigenschaften zugreifen d�rfen. 
 *
 * Neben Kapselung unterst�tzen Klassen durch die Vererbung ein Konzept, das Wiederverwendung genannt wird. 
 * Wiederverwendung bedeutet, dass in einem Programm auf Code zur�ckgegriffen werden kann, der bereits 
 * anderweitig geschrieben wurde. Das Konzept der Wiederverwendung spart nicht nur Arbeit, sondern beugt auch 
 * Fehlern vor. Anstatt dass jeder Programmierer den gleichen Code f�r sich neu entwickelt, greifen alle 
 * auf den gleichen Code zu. Das bedeutet, jeder Programmierer kann sich wirklich auf neue Aufgaben konzentrieren 
 * und verl��t sich darauf, dass der wiederverwendete Code korrekt arbeitet. 
 * 
 * Der Punkt-Operator ist dazu da, ein Memberelement einer Klasse �ber eine
 * Objektreferenz anzusprechen.
 *
 *     objektName.memberdatum bzw. objektName.membermethode()
 *
 * Der oder die Konstruktor(en) einer Klasse dient bzw. dienen dem ausdr�cklichen
 * Zweck, Objekte -- also Instanzen bzw. konkrete Auspr�gungen -- einer Klasse zu
 * initialisieren. Ein Konstruktor einer Klasse ist dadurch gekennzeichnet, dass er
 * denselben Namen wie die zugeh�rige Klasse tr�gt.
 * 
 * Neben Konstruktoren gibt es in Java auch Destruktoren. 
 * Ein Destruktor wird als parameterlose Methode mit dem Namen finalize definiert. 
 * 
 * Der Destruktor wird aufgerufen, bevor der Garbage Collector das entsprechende
 * Objekt vom Heap entfernt, d.h. beim Aufruf des Destruktors ist das Objekt "noch
 * so gerade lebendig".
 * 
 * Anders als in C++ ist die Verwendung von Destruktoren in Java eher selten. In C++
 * sind Destruktoren haupts�chlich damit besch�ftigt, dynamisch allokierten Speicher
 * freizugeben, ein Problem, was in Java vom Garbage Collector gel�st wird.
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
		
		
		
		
		
		
		

	}

}
