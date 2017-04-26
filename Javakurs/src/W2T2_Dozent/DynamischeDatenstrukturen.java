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
 * Dynamische Datenstrukturen (Kollektionen) passen ihre Gr�sse der Anzahl
 * der Daten an, die sie aufnehmen.
 * 
 * Die wichtigsten Datenstrukturen sind - Listen, - Mengen, - Kellerspeicher
 * (Stapel) und - Assoziativspeicher.
 * 
 * Eine der gr��ten Neuerungen, die die Java-Plattform eingef�hrt hat, ist
 * die Collection-API. Eine Collection oder auch Container ist ein Objekt, 
 * welches wiederum Objekte aufnimmt und die Verantwortung f�r die Elemente
 * �bernimmt. Wir werden die Begriffe �Container� und �Collection� synonym verwenden.
 * 
 * Collection ist die Basis der Hierarchie, die bis auf die
 * Assoziativspeicher alle Datenstrukturen implementieren. Durch die
 * gemeinsame Schnittstelle Collection erhalten alle implementierenden
 * Klassen einen gemeinsamen, �u�eren Rahmen. Mit den dort definierten
 * Operationen lassen sich Elemente hinzuf�gen, l�schen, selektieren und
 * finden.
 */

public class DynamischeDatenstrukturen
{

	
	private static HashMap<String, String> PLZTable;
	
	
	public static void main(String[] args)
	{
		
		// 1. LinkedList
		// LinkedList ist eine doppelt verkettete Liste, also eine Liste von Eintr�gen
		// mit einer Referenz auf den jeweiligen Nachfolger und Vorg�nger. 
		// Das ist n�tzlich beim Einf�gen und L�schen von Elementen an beliebigen
		// Stellen innerhalb der Liste.
		
		// Ein gro�es Problem mit Datenstrukturen bis Java 5 ist, dass sie prinzipiell offen
		// f�r jeden Typ sind, da sie Objekte vom allgemeinsten Typ Object beim Speichern
		// entgegennehmen und diesen auch als R�ckgabe liefern.
		
		// Seit Java 5 sind alle Datenstrukturen generisch deklariert.
		// Erst dadurch wird bessere Typsicherheit gew�hrleistet, da nur ganz spezielle Objekte
		// in die Datenstruktur kommen. Mit den Generics l�sst sich bei der Konstruktion einer
		// Collection-Datenstruktur angeben, welche Objekte in die Liste aufgenommen werden d�rfen. 
		
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
//		// mit Hilfe der Methode toArray() in der Form [e, e, e, ...] zur�ck.
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
		// Dadurch wird der Zugriff auf ein einzelnes Element �ber die
		// Position in der Liste sehr schnell.
		// Es bedeuted jedoch viel Arbeit f�r ein Array, wenn Elemente
		// eingef�gt oder gel�scht werden.
		// Ausserdem kann die Gr�sse des internen Feldes zu klein werden.
		// Dann bleibt der Laufzeitumgebung nichts anderes �brig, als ein
		// neues gr�sseres Objekt anzulegen und alle Elemente zu kopieren.
		
		
		// Arraylist als Liste von Objekten verwenden
		ArrayList<Object> al = new ArrayList<>();
		
		al.add(13);
		al.add('A');
		al.add(true);
		al.add(3.14);
		al.add("Java ist toll.");
		al.add(new Date());
		
		// Problematisch beim Auslesen, da jedes mal gepr�ft werden muss,
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
		
		// Einf�gen eines neuen Eintrags
		arrList.add(3, "komplett");
		Ausgabe(arrList);
		
		// Entfernen eines Eintrags
		arrList.remove("quer");
		Ausgabe(arrList);
		
		// Einen Bereich entfernen:
		// ab Indexposition 2, 4 Eintr�ge
		
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
		
		// Hinzuf�gen zu einer Queue mit entweder mit
		// add(), was durch die Collection zur Verf�gung st�nde,
		// oder mit offer(). 
		// Unterschied: im Fehlerfall l�st add()
		// eine Exception aus, w�hrend offer() durch die R�ckgabe
		// false anzeigt, das das Element nicht hinzugef�gt wurde.
		
		schlange.offer("Fiat");
		schlange.offer("LKW");
		schlange.offer("Opel");
		schlange.offer("Audi");
		schlange.offer("Bus");
		schlange.offer("Motorrad");
		schlange.offer("Toyota");
		
		// Jetzt wird die Ampel gr�n:
		// Nur die ersten 4 Fahrzeuge k�nne die Kreuzung �berqueren bevor die
		// Ampel wieder rot wird. 
		
		for (int i = 1; i <= 4; i++)
			System.out.println(schlange.poll());
		
		System.out.println();
		
		// W�hrend der Rot-Phase kommen weitere Fahrzeuge:
		schlange.offer("Ford");
		schlange.offer("Honda");
		
		// Alle Fahrzeuge k�nnen w�hrend der n�chsten Gr�n-Phase die Kreuzung
		// �berqueren.
		
		while(!schlange.isEmpty())
		{
			System.out.println(schlange.poll());
		}
		
		
		System.out.println();
		
		
	}
	
	
	private static void DemoStack()
	{
		
		// Die Klasse Stack repr�sentiert einen Stapelspeicher, der
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
		
		// Ein assoziativer Speicher verbindet einen Schl�ssel mit einem
		// Wert.
		// Die Hash-Tabelle arbeitet mit Schl�ssel-Werte-Paaren. Aus dem
		// Schl�ssel wird nach einer mathematischen Funktion, der sogenannten
		// Hash-Funktion, ein Hashcode berechnet. Dieser dient dann als
		// Index f�r ein internes Array.
		
		
		PLZTable = new HashMap<>();
		
//		PLZTable.put("20097", "Hamburg");
//		PLZTable.put("63069", "Offenbach/Main");
//		PLZTable.put("76133", "Karlsruhe");
//		PLZTable.put("10178", "Berlin");
//		
//		
//		System.out.println(PLZTable.get("76133"));
//		
//		// Der Schl�sselbegriff f�r eine HashMap muss eindeutig sein.
//		// Eine erneute Verwendung eines bereits vorhandenen Schl�ssel
//		// f�hrt zu keiner Fehlermeldung sondern der dazugeh�rige Wert wird
//		// kommentarlos �berschrieben.
//		
//		PLZTable.put("76133", "Karlsruhe-Durlach");
//		System.out.println(PLZTable.get("76133"));
//		System.out.println();
//		
//		// Aufruf eines Schl�sselwerts, der nicht vorhanden ist.
//		System.out.println(PLZTable.get("12345"));
//		System.out.println();
//		
//		String s = PLZTable.get("12345");
//		if (s != null)
//			s = s.toUpperCase();
//		else
//			System.out.println("Der Schl�sselbegriff wurde nicht gefunden.");
//		
//		
//		if (PLZTable.containsKey("20097"))
//			System.out.println(PLZTable.get("20097"));
//		else
//			System.out.println("Der Schl�sselbegriff wurde nicht gefunden.");
		
		
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
		
		
		System.out.printf("\nEs wurden %s Datens�tze erfolgreich eingelesen\n", NumberFormat.getInstance().format(recordCount));
				
	}
	
	
	
}
