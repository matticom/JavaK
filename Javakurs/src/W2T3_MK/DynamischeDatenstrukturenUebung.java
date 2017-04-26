package W2T3_MK;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class DynamischeDatenstrukturenUebung
{

	private static HashMap <String, String> PlzTable;
	
	
	public static void main(String[] args)
	{
				
		// Übung 1: Datei mit vollständigen PLZ (ohne Überschreiben) in HashMap
		
		readAllPlzInHashmap();

	}
	
	private static void readAllPlzInHashmap()
	{
		Scanner fileNameReader = new Scanner(System.in);
		String filename;
		String row;
		String key, value;
	
		int KEY = 0;
		int VALUE = 1;
		int recordCount = 0;
		
		PlzTable = new HashMap<>();
		
		System.out.println("Gib den Dateipfad an, wie sich die einzulesende Datei befindet:"); //C:\\eclipse\\workspace\\Javakurs\\Orte.txt
		filename = fileNameReader.nextLine();
		
		fileNameReader.close();
		
		Scanner currentLine = null;
		
		try
		{
			currentLine = new Scanner(new FileInputStream(filename));
			
			while(currentLine.hasNextLine())
			{
				row = currentLine.nextLine();
				key = filterLine(row,KEY);
				value = filterLine(row,VALUE);
				
				if (isAlreadyInHash(key))
					PlzTable.merge(key, "\t"+value, String::concat);
				else
					PlzTable.put(key, value);
				recordCount++;				
			}		
			
			hashMapOutConsole();			
			System.out.printf("\nEs wurden %d Datensätze eingelesen.", recordCount);										
		}
		
		catch (Exception ex)
		{
			System.out.println("Fehler beim Einlesen der Datei: " + ex.getMessage());
		}
		
		if (currentLine != null)
			currentLine.close();
		
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	private static boolean isAlreadyInHash(String key)
	{
		if (PlzTable.containsKey(key))
			return true;
		else
			return false;				
	}
	
	private static String filterLine(String row, int part)
	{
		int KEY = 0;
		int VALUE = 1;
		String value;
				
		String[] splitElements;
		splitElements = row.split(";");
		if (part == KEY)
		{
			return splitElements[KEY];
		}
		else
		{
			if (splitElements.length >= 2)
			{
				value = splitElements[VALUE];
				for (int i = 2; i < splitElements.length; i++)
				{
					value += ";" + splitElements[i];
				}
				return value;						
			}
			else
			{
				value = splitElements[VALUE];
				return value;
			}
				
		}
			
	}
	
	private static void hashMapOutConsole()
	{
		System.out.println("\n\nListe aller Einträge der Tabelle Postleitzahlen\n");
				
		Set<String> keys = PlzTable.keySet();
		String[] array = new String[PlzTable.size()];
		array = keys.toArray(array);
		Arrays.sort(array);
		
		for (String s : array)
			System.out.println(s + " - " + PlzTable.get(s));
		
		
		System.out.println("\n\n***   Ende der Liste   ***");	
	}

}
