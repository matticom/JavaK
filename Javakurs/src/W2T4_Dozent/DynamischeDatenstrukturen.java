package W2T4_Dozent;

import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class DynamischeDatenstrukturen
{

	private static TreeMap<String, ArrayList<String>> PLZTree;
	
	private static Scanner console;
	
	public static void main(String[] args)
	{
		console = new Scanner(System.in);
		
		LeseDatei();
		
		console.close();

	}

	private static void LeseDatei()
	{
		
		String eingabe;
		
		System.out.println("\nName der Postleitzahlendatei eingeben:");
		
		eingabe = console.nextLine();
		
		LeseDateiInTreeMap(eingabe);
		
		System.out.println("Soll nach einer Postleitzahl gesucht werden ? (j/n)");
		if (console.nextLine().equalsIgnoreCase("j"))
			PostleitzahlSuchenInTreeMap();
		
		System.out.println("Soll eine vollst�ndige Liste aller Eintr�ge der Postleitzahlen �ber die Konsole ausgegeben werden ? (j/n)");
		if (console.nextLine().equalsIgnoreCase("j"))
			AusgabePostleitzahlenAusTreeMap();
		
		System.out.println("Soll nach einem Ort gesucht werden ? (j/n)");
		if (console.nextLine().equalsIgnoreCase("j"))
			OrteSuchenInTreeMap();
		
		System.out.println("Soll eine vollst�ndige Liste aller Orte ohne Postleitzahl aufsteigend sortiert nach Ort ausgegeben werden ? (j/n)");
		if (console.nextLine().equalsIgnoreCase("j"))
			AusgabeAllerOrteAusTreeMapNachOrt();
		
		System.out.println("Soll eine vollst�ndige Liste aller Orte mit Postleitzahl aufsteigend sortiert nach Ort ausgegeben werden ? (j/n)");
		if (console.nextLine().equalsIgnoreCase("j"))
			AusgabeAllerOrteMitPostleitzahlAusTreeMap();
		
		System.out.println("Demo TreeMap wurde beendet.");

	}
	
	private static void LeseDateiInTreeMap(String Dateiname)
	{
		
		Scanner in = null;
		String zeile;
		int recordCount = 0, addCounter = 0;
		
		String[] split;
		ArrayList<String> lstOrte;
		
		PLZTree = new TreeMap<>();
		
		System.out.println("\nDie Postleitzahlendatei '" + Dateiname + "' wird eingelesen...");
		
		
		try
		{
			in = new Scanner(new FileInputStream(Dateiname));
			
			while(in.hasNextLine())
			{
				zeile = in.nextLine();
				recordCount++;
				
				split = zeile.split(";", 2);
				
				if (split.length == 2)
				{
					
					// Ist die Postleitzahl bereits vorhanden?
					if (PLZTree.containsKey(split[0]))
						lstOrte = PLZTree.get(split[0]);
					else
						lstOrte = new ArrayList<>();
					
					
					// Ist der Ort bereits vorhanden?
					if (!lstOrte.contains(split[1]))
					{
						lstOrte.add(split[1]);
						addCounter++;
						
						// Das Zur�ckschreiben der ArrayList ist eigentlich
						// nur bei einer Neuanlage n�tig.
						PLZTree.put(split[0], lstOrte);
					}

				}
						
			}
				
		}
		catch (Exception ex)
		{
			System.out.println("Fehler beim Einlesen der Datei: " + ex.getMessage());
		}
		
		if (in != null)
			in.close();
		
		
		System.out.printf("\nEs wurden %s Datens�tze eingelesen\n", NumberFormat.getInstance().format(recordCount));
		System.out.printf("Es wurden %s Eintr�ge zur Postleitzahlentabelle hinzugef�gt\n", NumberFormat.getInstance().format(addCounter));
		System.out.printf("Die Postleitzahlentabelle enth�lt %s Eintr�ge\n\n", NumberFormat.getInstance().format(PLZTree.size()));		
	}
	
	
	private static void PostleitzahlSuchenInTreeMap()
	{
		String plz;
		ArrayList<String> lstOrte;
		
		
		do
		{
			
			System.out.println("\nPostleitzahl eingeben oder 'exit' f�r Programmende");
			plz = console.nextLine();
			
			if (plz.equalsIgnoreCase("exit"))
				break;
			
			if (!PLZTree.containsKey(plz))
			{
				System.out.println("Die angegebene Postleitzahl wurde nicht gefunden.");
				continue;
			}
			
			lstOrte = PLZTree.get(plz);
			
			// Aufsteigende Sortierung der Orte
			Collections.sort(lstOrte);
			
			
			for (int i = 0; i < lstOrte.size(); i++)
				System.out.println(plz + " - " + lstOrte.get(i));
			
			
			System.out.println("\n\n***   Ende der Liste   ***");
			System.out.println("Es wurden " + NumberFormat.getInstance().format(lstOrte.size()) + " Eintr�ge gefunden.");
			
		}
		while (true);
		
	}
	
	private static void AusgabePostleitzahlenAusTreeMap()
	{
		
		ArrayList<String> lstOrte;
		int entryCount = 0;
		
		
		System.out.println("Liste aller Eintr�ge der Tabelle Postleitzahlen\n");
		
		
		for (String s : PLZTree.keySet())
		{
			
			lstOrte = PLZTree.get(s);
			Collections.sort(lstOrte);
			
			for (int i = 0; i < lstOrte.size(); i++)
			{
				System.out.println(s + " - " + lstOrte.get(i));
				entryCount++;
			}
		
		}
		
		
		System.out.println("\n\n***   Ende der Liste   ***");
		System.out.println("Es wurden " + NumberFormat.getInstance().format(entryCount) + " Eintr�ge gefunden.");
		
	}
	
	private static void OrteSuchenInTreeMap()
	{
		
		String suchBegriff;
		ArrayList<String> lstOrte;
		String suchOrt, ort;
		int entryCount = 0;
		boolean entryFound = false;
		
		
		do
		{
			entryCount = 0;
			System.out.println("\nVollst�ndigen Ort, *Suchbegriff, Suchbegriff*, *Suchbegriff* oder 'exit' eingeben:");
			suchBegriff = console.nextLine();
			
			if (suchBegriff.equalsIgnoreCase("exit"))
				break;
			
			// Umwandlung des Suchbegriffs in Kleinbuchstaben
			// Gro�-/Kleinschreibung ist nicht relevant
			suchOrt = suchBegriff.replace("*", "").toLowerCase();
						
			for (String key : PLZTree.keySet())
			{
				
				lstOrte = PLZTree.get(key);
				
				// Orte aufsteigend sortieren
				Collections.sort(lstOrte);
				
				for (String value : lstOrte)
				{
					entryFound = false;
					
					// Umwandlung der einzelnen Werte in Kleinbuchstaben
					ort = value.toLowerCase();
					
					if (suchBegriff.startsWith("*") && suchBegriff.endsWith("*"))
					{
						if (ort.contains(suchOrt))
							entryFound = true;
						
					}
					else if (suchBegriff.startsWith("*") && ort.endsWith(suchOrt))
						entryFound = true;
					else if (suchBegriff.endsWith("*") && ort.startsWith(suchOrt))
						entryFound = true;
					else if (ort.equals(suchOrt))
						entryFound = true;
					
					if (entryFound)
					{
						System.out.println(key + " - " + value);
						entryCount++;
					}

				}
				
			}
			
			
			System.out.println("\n\n***   Ende der Liste   ***");
			System.out.println("Es wurden " + NumberFormat.getInstance().format(entryCount) + " Eintr�ge gefunden.");
			
		}
		while(true);
		
	}
	
	// Ausgabe aller Orte (ohne Postleitzahl), aufsteigend sortiert.
	private static void AusgabeAllerOrteAusTreeMapNachOrt()
	{

		ArrayList<String> lstOrte = null;
		ArrayList<String> lstAlleOrt;


		System.out.println("Liste aller Orte aus der Tabelle Postleitzahlen\n");
		
		// Erstellen einer ArrayList mit einer Mindestanzahl von Elementen.
		lstAlleOrt = new ArrayList<String>(PLZTree.size());

		// Jeden einzelnen Eintrag �ber das Objekt (PLZ) aus der TreeMap lesen
		// und und den dazugeh�rigen Wert (ArrayList von Orte) der ArrayList f�r
		// alle Orte hinzuf�gen.
		for (String key : PLZTree.keySet())
		{
			lstOrte = PLZTree.get(key);
			lstAlleOrt.addAll(lstOrte);
		}

		// ArrayList aller Orte sortieren.
		Collections.sort(lstAlleOrt);

		// Ausgabe
		for (String s : lstAlleOrt)
			System.out.println(s);

		System.out.println("\n\n*** Ende der Liste ***");
		System.out.printf("Es wurden %s Eintr�ge gefunden.\n", NumberFormat.getInstance().format(lstAlleOrt.size()));

	}
	
	// Ausgabe Eintr�ge (mit Postleitzahl) aus der TreeMap aufsteigend sortiert nach Ort.
	private static void AusgabeAllerOrteMitPostleitzahlAusTreeMap()
	{

		ArrayList<String> lstOrte = null;
		ArrayList<String> lstAlleOrt;
		String[] split;


		System.out.println("Liste aller Orte aus der Tabelle Postleitzahlen\n");

		// Erstellen einer ArrayList mit einer Mindestanzahl von Elementen.
		// Jeden einzelnen Eintrag �ber das Objekt (PLZ) aus der TreeMap lesen
		// und
		// und den dazugeh�rigen Wert (ArrayList von Orte) mit der Postleitzahl
		// getrennt durch das @-Zeichen hinzuf�gen.
		lstAlleOrt = new ArrayList<String>(PLZTree.size());

		for (String key : PLZTree.keySet())
		{
			lstOrte = PLZTree.get(key);

			// Jeden Ort mit der Postleitzahl getrennt durch 10 Leerstellen und das @-Zeichen
			// hinzuf�gen.
			// Leerstellen werden f�r ordnungsgem��e Sortierung von Orten ben�tigt die mehrfach
			// vorkommen aber mit einem zus�tzlichen Ortsteil versehen sind.
			for (String s : lstOrte)
				lstAlleOrt.add(s + "          @" + key);

		}

		// ArrayList aller Orte sortieren.
		Collections.sort(lstAlleOrt);

		// Ausgabe
		for (String s : lstAlleOrt)
		{
			// Aufsplitten der Eintr�ge nach dem @-Zeichen
			// (Trennt den Ort von der Postleitzahl).
			split = s.split("@");
			if (split.length > 1)
				System.out.println(split[1] + " - " + split[0].trim());
		}

		System.out.println("\n\n*** Ende der Liste ***");
		System.out.printf("Es wurden %s Eintr�ge gefunden.\n", NumberFormat.getInstance().format(lstAlleOrt.size()));

	}

	
	
}
