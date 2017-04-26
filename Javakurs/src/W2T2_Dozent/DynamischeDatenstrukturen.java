package W2T2_Dozent;

import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/*
 * Dynamische Datenstrukturen (Kollektionen) passen ihre Grösse der Anzahl
 * der Daten an, die sie aufnehmen.
 * 
 * Die wichtigsten Datenstrukturen sind - Listen, - Mengen, - Kellerspeicher
 * (Stapel) und - Assoziativspeicher.
 * 
 * Eine der größten Neuerungen, die die Java-Plattform eingeführt hat, ist
 * die Collection-API. Eine Collection oder auch Container ist ein Objekt, 
 * welches wiederum Objekte aufnimmt und die Verantwortung für die Elemente
 * übernimmt. Wir werden die Begriffe »Container« und »Collection« synonym verwenden.
 * 
 * Collection ist die Basis der Hierarchie, die bis auf die
 * Assoziativspeicher alle Datenstrukturen implementieren. Durch die
 * gemeinsame Schnittstelle Collection erhalten alle implementierenden
 * Klassen einen gemeinsamen, äußeren Rahmen. Mit den dort definierten
 * Operationen lassen sich Elemente hinzufügen, löschen, selektieren und
 * finden.
 */

public class DynamischeDatenstrukturen
{

	
	private static HashMap<String, String> PLZTable;
	
	
	public static void main(String[] args)
	{
		
		// 1. LinkedList
		// LinkedList ist eine doppelt verkettete Liste, also eine Liste von Einträgen
		// mit einer Referenz auf den jeweiligen Nachfolger und Vorgänger. 
		// Das ist nützlich beim Einfügen und Löschen von Elementen an beliebigen
		// Stellen innerhalb der Liste.
		
		// Ein großes Problem mit Datenstrukturen bis Java 5 ist, dass sie prinzipiell offen
		// für jeden Typ sind, da sie Objekte vom allgemeinsten Typ Object beim Speichern
		// entgegennehmen und diesen auch als Rückgabe liefern.
		
		// Seit Java 5 sind alle Datenstrukturen generisch deklariert.
		// Erst dadurch wird bessere Typsicherheit gewährleistet, da nur ganz spezielle Objekte
		// in die Datenstruktur kommen. Mit den Generics lässt sich bei der Konstruktion einer
		// Collection-Datenstruktur angeben, welche Objekte in die Liste aufgenommen werden dürfen. 
		
		LinkedList<String> l1 = new LinkedList<String>();
	
//		
//		
//		for (int i = 1; i <= 50; i++)
//			l1.add(String.valueOf(i));
//		
//		// Ausgabe mit erweiterter for-Schleife
//		for (String s : l1)
//			System.out.println(s);
//		System.out.println();
//		
//		// Ausgabe mit for-Schleife
//		for (int i = 0; i < l1.size(); i++)
//			System.out.println(l1.get(i));
//		System.out.println();
//		
//		// Die Methode toString() der Klasse Array gibt ein Array mit allen Elementen des Containers
//		// mit Hilfe der Methode toArray() in der Form [e, e, e, ...] zurück.
//		System.out.println(Arrays.toString(l1.toArray()));
//		System.out.println();
//
//		LinkedList<Integer> l2 = new LinkedList<>();
//		
//		for (int i = 101; i <= 150; i++)
//			//l2.add(new Integer(i));
//			// oder
//			l2.add(i);
//		
//		for (Integer i : l2)
//			System.out.println(i);
//		
//		System.out.println();
//		
//		for (int i : l2)
//			System.out.println(i);
//		
//		System.out.println();
		
		
		//DemoArrayList();
		
		//DemoQueue();

		// DemoStack();
		
		DemoHashMap();
		
	}
	
	private static void DemoArrayList()
	{
		
		// ArrayList speichert Elemente in einem internen Array.
		// Dadurch wird der Zugriff auf ein einzelnes Element über die
		// Position in der Liste sehr schnell.
		// Es bedeuted jedoch viel Arbeit für ein Array, wenn Elemente
		// eingefügt oder gelöscht werden.
		// Ausserdem kann die Grösse des internen Feldes zu klein werden.
		// Dann bleibt der Laufzeitumgebung nichts anderes übrig, als ein
		// neues grösseres Objekt anzulegen und alle Elemente zu kopieren.
		
		
		// Arraylist als Liste von Objekten verwenden
		ArrayList<Object> al = new ArrayList<>();
		
		al.add(13);
		al.add('A');
		al.add(true);
		al.add(3.14);
		al.add("Java ist toll.");
		al.add(new Date());
		
		// Problematisch beim Auslesen, da jedes mal geprüft werden muss,
		// um welchen Typ es sich bei dem jeweiligen Objekt handelt.	
		
		for (int i = 0; i < al.size(); i++)
			System.out.printf("%s -> %s\n", al.get(i), al.get(i).getClass());
		
		System.out.println();
		
		
		ArrayList<String> arrList = new ArrayList<>();
		
		arrList.add("Franz");
		arrList.add("jagt");
		arrList.add("im");
		
		ArrayList<String> strings = new ArrayList<>();
		strings.add("verwahrlosten");
		strings.add("Taxi");
		
		arrList.addAll(strings);
		
		arrList.add("quer");
		arrList.add("durch");
		arrList.add("Bayern");
			
		Ausgabe(arrList);
		
		// Einfügen eines neuen Eintrags
		arrList.add(3, "komplett");
		Ausgabe(arrList);
		
		// Entfernen eines Eintrags
		arrList.remove("quer");
		Ausgabe(arrList);
		
		// Einen Bereich entfernen:
		// ab Indexposition 2, 4 Einträge
		
//		for (int i = 0; i < 4; i++)
//			arrList.remove(2);
		
		// oder
		arrList.subList(2, 6).clear();
		
		
		Ausgabe(arrList);

	}
	
	private static void DemoQueue()
	{
		
		// Queues sind Datenstrukturen, die nach dem FIFO-Prinzip
		// (First-In, First-Out) arbeiten.
		
		Queue<String> schlange = new LinkedList<>();
		
		// Hinzufügen zu einer Queue mit entweder mit
		// add(), was durch die Collection zur Verfügung stünde,
		// oder mit offer(). 
		// Unterschied: im Fehlerfall löst add()
		// eine Exception aus, während offer() durch die Rückgabe
		// false anzeigt, das das Element nicht hinzugefügt wurde.
		
		schlange.offer("Fiat");
		schlange.offer("LKW");
		schlange.offer("Opel");
		schlange.offer("Audi");
		schlange.offer("Bus");
		schlange.offer("Motorrad");
		schlange.offer("Toyota");
		
		// Jetzt wird die Ampel grün:
		// Nur die ersten 4 Fahrzeuge könne die Kreuzung überqueren bevor die
		// Ampel wieder rot wird. 
		
		for (int i = 1; i <= 4; i++)
			System.out.println(schlange.poll());
		
		System.out.println();
		
		// Während der Rot-Phase kommen weitere Fahrzeuge:
		schlange.offer("Ford");
		schlange.offer("Honda");
		
		// Alle Fahrzeuge können während der nächsten Grün-Phase die Kreuzung
		// überqueren.
		
		while(!schlange.isEmpty())
		{
			System.out.println(schlange.poll());
		}
		
		
		System.out.println();
		
		
	}
	
	
	private static void DemoStack()
	{
		
		// Die Klasse Stack repräsentiert einen Stapelspeicher, der
		// nach dem LIFO-Prinzip (Last-In, First-Out) arbeitet.
		
		Stack<String> stapel = new Stack<>();
		
		stapel.push("BMW");
		stapel.push("VW");
		stapel.push("Audi");
		stapel.push("Mercedes");
		stapel.push("Honda");
		stapel.push("Toyota");
		stapel.push("Porsche");
		
		while(!stapel.isEmpty())
		{
			System.out.println(stapel.pop());
		}
			
			
		System.out.println();
		
	}
	
	
	private static void DemoHashMap()
	{
		
		// Ein assoziativer Speicher verbindet einen Schlüssel mit einem
		// Wert.
		// Die Hash-Tabelle arbeitet mit Schlüssel-Werte-Paaren. Aus dem
		// Schlüssel wird nach einer mathematischen Funktion, der sogenannten
		// Hash-Funktion, ein Hashcode berechnet. Dieser dient dann als
		// Index für ein internes Array.
		
		
		PLZTable = new HashMap<>();
		
//		PLZTable.put("20097", "Hamburg");
//		PLZTable.put("63069", "Offenbach/Main");
//		PLZTable.put("76133", "Karlsruhe");
//		PLZTable.put("10178", "Berlin");
//		
//		
//		System.out.println(PLZTable.get("76133"));
//		
//		// Der Schlüsselbegriff für eine HashMap muss eindeutig sein.
//		// Eine erneute Verwendung eines bereits vorhandenen Schlüssel
//		// führt zu keiner Fehlermeldung sondern der dazugehörige Wert wird
//		// kommentarlos überschrieben.
//		
//		PLZTable.put("76133", "Karlsruhe-Durlach");
//		System.out.println(PLZTable.get("76133"));
//		System.out.println();
//		
//		// Aufruf eines Schlüsselwerts, der nicht vorhanden ist.
//		System.out.println(PLZTable.get("12345"));
//		System.out.println();
//		
//		String s = PLZTable.get("12345");
//		if (s != null)
//			s = s.toUpperCase();
//		else
//			System.out.println("Der Schlüsselbegriff wurde nicht gefunden.");
//		
//		
//		if (PLZTable.containsKey("20097"))
//			System.out.println(PLZTable.get("20097"));
//		else
//			System.out.println("Der Schlüsselbegriff wurde nicht gefunden.");
		
		
		LeseDatei();
		
		
		
		
		
	}
	
	
	private static void Ausgabe(ArrayList<String> al)
	{
		
		for (String s : al)
			System.out.print(s + " ");
		
		
		System.out.println();
		
	}
	

	private static void LeseDatei()
	{
		
		Scanner in;
		String eingabe;
		
		in = new Scanner(System.in);
		System.out.println("\nName der Postleitzahlendatei eingeben:");
		
		eingabe = in.nextLine();
		
		LeseDateiInHashMap(eingabe);
		

	}
	
	
	private static void LeseDateiInHashMap(String Dateiname)
	{
		
		Scanner in = null;
		String zeile;
		int recordCount = 0;
		
		
		System.out.println("\nDie Postleitzahlendatei '" + Dateiname + "' wird eingelesen...");
		
		
		try
		{
			in = new Scanner(new FileInputStream(Dateiname));
			
			while(in.hasNextLine())
			{
				zeile = in.nextLine();
				recordCount++;
				
				System.out.println(zeile);
				
			}
				
		}
		catch (Exception ex)
		{
			System.out.println("Fehler beim Einlesen der Datei: " + ex.getMessage());
		}
		
		if (in != null)
			in.close();
		
		
		System.out.printf("\nEs wurden %s Datensätze erfolgreich eingelesen\n", NumberFormat.getInstance().format(recordCount));
				
	}
	
	
	
}
