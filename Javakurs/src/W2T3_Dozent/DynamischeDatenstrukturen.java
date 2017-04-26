package W2T3_Dozent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

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

	private static Scanner console;
	
	public static void main(String[] args)
	{
		
		
//		
//		DemoHashMap();
//		
//		console.close();
		
//		DemoPropertiesErstellen("c:\\temp\\Eigenschaften.prop", false);
//		DemoPropertiesLesen("c:\\temp\\Eigenschaften.prop", false);
//		
//		DemoPropertiesErstellen("c:\\temp\\Eigenschaften.xml", true);
//		DemoPropertiesLesen("c:\\temp\\Eigenschaften.xml", true);
		
		DemoTreeSet();
	}
	
	
	private static void LeseDateiInHashMap(String Dateiname)
	{
		
		Scanner in = null;
		String zeile;
		int recordCount = 0;
		
		String sOrt;
		
		String[] split;
		
		
		System.out.println("\nDie Postleitzahlendatei '" + Dateiname + "' wird eingelesen...");
		
		
		try
		{
			in = new Scanner(new FileInputStream(Dateiname));
			
			while(in.hasNextLine())
			{
				zeile = in.nextLine();
				recordCount++;
				
//				split = zeile.split(";");
//				
//				if (split.length >= 2)
//				{
//					sOrt = split[1];
//					for (int i = 2; i < split.length; i++)
//						sOrt += ";" + split[i];		
//					
//					PLZTable.put(split[0], sOrt);
//					
//				}
				
				// oder
				split = zeile.split(";", 2);
				if (split.length == 2)
					PLZTable.put(split[0], split[1]);
				
		
			}
				
		}
		catch (Exception ex)
		{
			System.out.println("Fehler beim Einlesen der Datei: " + ex.getMessage());
		}
		
		if (in != null)
			in.close();
		
		
		System.out.printf("\nEs wurden %s Datensätze erfolgreich eingelesen\n", NumberFormat.getInstance().format(recordCount));
		System.out.printf("Die Postleitzahlentabelle enthält %s Einträge\n\n", NumberFormat.getInstance().format(PLZTable.size()));		
	}
	
	private static void PostleitzahlSuchenInHashMap()
	{
		
		String plz;
		
		while(true)
		{
			
			System.out.println("\nPostleitzahl eingeben oder 'exit' für Programmende");
			plz = console.nextLine();
			
			if (plz.equalsIgnoreCase("exit"))
				break;
			
			if (!PLZTable.containsKey(plz))
				System.out.println("Die angegebene Postleitzahl wurde nicht gefunden.");
			else
				System.out.println(plz + " - " + PLZTable.get(plz));
			
			
		}
		
	}
	
	private static void AusgabePostleitzahlenAusHashMap()
	{
		System.out.println("Liste aller Einträge der Tabelle Postleitzahlen\n");
		
		
		Set<String> keys = PLZTable.keySet();
		
		
		// Die Ausgabe ist völlig unsortiert und deshalb sind EInträge sehr schwer zu finden.
//		for (String s : keys)
//			System.out.println(s + " - " + PLZTable.get(s));
		
		// Konvertieren des Sets in ein Array und
		// aufsteigende Sortierung.
//		Object[] array = keys.toArray();
//		Arrays.sort(array);
		
		
//		for (Object o : array)
//			System.out.println(o + " - " + PLZTable.get(o));
		
		// oder 
		String[] array = new String[PLZTable.size()];
		array = keys.toArray(array);
		Arrays.sort(array);
		
		for (String s : array)
			System.out.println(s + " - " + PLZTable.get(s));
		
		
		System.out.println("\n\n***   Ende der Liste   ***");
		
	}
	
	
	private static void DemoPropertiesErstellen(String Dateiname, boolean toXML)
	{
		
		/*
		 * Die Klasse Properties ist eine Sonderform der Assoziativspeicher, bei
		 * der Schlüssel-Werte-Paare immer vom Typ String sind. Da sich die
		 * Einträge in einer Datei speichern und wieder auslesen lassen, können
		 * auf diese Weise Konfigurations-Einstellungen für ein Programm extern
		 * abgespeichert und geändert werden, ohne das das Programm geändert
		 * werden muss.
		 */
		
		FileOutputStream fw = null;
				
		Properties prop = new Properties();
		
		prop.setProperty("Benutzer", "alfa");
		prop.setProperty("Kennwort", "geheim");
		prop.setProperty("Sprache", "de");
		prop.setProperty("Anfangswert", "42");
		
		try
		{
		
			fw = new FileOutputStream(Dateiname);
			
			if (toXML)
				prop.storeToXML(fw, "Das ist ein Kommentar");
			else
				prop.store(fw, "Das ist ein Kommentar");
		}
		catch (Exception ex)
		{
			System.out.println("Fehler beim Speichern der Konfigurationseinstellungen: " + ex.getMessage());
		}
		
		try
		{
			fw.close();
		}
		catch (Exception ex)
		{
			
			ex.printStackTrace();
		}
		
	}
	
	private static void DemoPropertiesLesen(String Dateiname, boolean fromXML)
	{
		
		FileInputStream fr = null;
		
		Properties prop = new Properties();
		
		try
		{
			
			fr = new FileInputStream(Dateiname);
			
			if (fromXML)
				prop.loadFromXML(fr);
			else
				prop.load(fr);
			
		}
		catch (Exception ex)
		{
			System.out.println("Fehler beim Lesen der Konfigurationseinstellungen: " + ex.getMessage());
		}
		
		try
		{
			fr.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		// Auflistung aller Properties
		prop.list(System.out);
		System.out.println();
		
		// Gezielter Zugriff auf Properties
		if (prop.containsKey("Benutzer"))
			System.out.println(prop.getProperty("Benutzer"));
		
		if (prop.containsKey("Anfangswert"))
			System.out.println(prop.getProperty("Anfangswert"));
		
	}
	
	
	private static void DemoTreeSet()
	{
		
		/*
		 * Bei TreeSets sind, im Unterschied zu HashSets, die Elemente immer in
		 * aufsteigender Reihenfolge angeordnet. Der TreeSet basiert auf der
		 * TreeMap und implementiert die Schnittstelle Set.
		 */
		
		
		TreeSet<Integer> t = new TreeSet<>();
		
		
		// Hinzufügen von Elementen
		t.add(6);
		t.add(2);
		t.add(9);
		t.add(8);
		t.add(4);
		t.add(3);
		t.add(7);
		t.add(5);
		
		// Doppelte Werte sind nicht erlaubt
		t.add(8);
		
		for (int i : t)
			System.out.print(i + ", ");
		
		System.out.println();
		
		System.out.println("Kleinste Element: " + t.first());
		System.out.println("Größes Element: " + t.last());
		System.out.println();
		
		
		// Bildung von Teilmengen
		
		System.out.println("Alle Elemente kleiner als 5:");
		
		SortedSet<Integer> ss =t.headSet(5);
		for (int i : ss)
			System.out.print(i + ", ");
		
		System.out.println();
		
		System.out.println("Alle Elemente grösser oder gleich 4:");
		
		for (int i : t.tailSet(4))
			System.out.print(i + ", ");
		
		System.out.println();
		
		System.out.println("Alle Elemente grösser gleich 4 und kleiner als 7:");
		
		for (int i : t.subSet(4, 7))
			System.out.print(i + ", ");
		
		System.out.println();
	}
	
	
	
}
