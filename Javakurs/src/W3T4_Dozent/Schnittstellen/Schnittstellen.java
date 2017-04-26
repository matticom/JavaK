package W3T4_Dozent.Schnittstellen;

import java.util.Arrays;

public class Schnittstellen
{

	// Bei Interfaces, oft auch direkt als Schnittstellen bezeichnet, handelt es sich um eine Abart der abstrakten Klassendeklaration.
	// Sie enthält neben diversen Datenelementen lediglich abstrakte Methoden.
	// Eine Schnittstelle dient in der objektorientierten Programmierung der Vereinbarung gemeinsamer Signaturen von Methoden, 
	// die in unterschiedlichen Klassen implementiert werden um die Kompatibilität der Klassen zu gewährleisten. Die Schnittstelle 
	// gibt dabei an, welche Methoden vorhanden sind oder vorhanden sein müssen.
	// Eine Schnittstelle ist also eine Menge von Anforderungen an Klassen, die mit der Schnittstelle konform gehen wollen.
	// Schnittstellen sind keine Klassen. Eine Schnittstelle kann man nicht mit dem Operator 'new' instanziieren.
	//
	// Schnittstellen werden auch für die Mehrfachvererbung eingesetzt, denn Klassen können beliebig viele dieser Schnittstellen
	// implementieren. Als Ersatz für Mehrfachvererbung eignen sich Schnittstellen allerdings nicht, da sie lediglich Methoden 
	// und deren Parameter definieren und keine Vererbung von Funktionalität ermöglichen.
	//
	// Alle in der Schnittstelle vorhandenen Methoden müssen in der Klasse implementiert werden!
	//
	// Eine Schnittstelle enthält keine Implementierungen, sondern deklariert nur den Kopf einer Methode - also Modifizierer, den Rückgabetyp und
	// die Signatur - ohne Rumpf.
	
	// Polymorphie (Vielgestaltigkeit) bei Schnittstellen.
	// Die Polymorphie der objektorientierten Programmierung ist eine Eigenschaft, die immer im Zusammenhang mit Vererbung und Schnittstellen
	// auftritt. Eine Methode ist polymorph, wenn sie in verschiedenen Klassen die gleiche Signatur hat, jedoch erneut implementiert ist.

	
	public static void main(String[] args)
	{
		
		Mitarbeiter m1 = new Mitarbeiter("Mitarbeiter 1", 2000);
		m1.Print();
		m1.PrinterSetup();
		m1.Preview();
		System.out.println(m1.varPrint);
		
		
		// Über m1 ist die konkrete Methode getName() der abstrakten Klasse verfügbar,
		// sowie alle Methoden der Schnittstellen 'Printable' und 'IprintSetup', und
		// die Variable 'varPrint'.
		
		
		AbstractMitarbeiter m2 = new Mitarbeiter("Mitarbeiter 2", 1800);
		
		// Über m2 ist die konkrete Methode getName() verfügbar, sowie die abstrakte
		// Methode getGehalt(). Da die Schnittstellen erst über die Klasse 'Mitarbeiter'
		// implementiert wurden, stehen sie hier nicht zur Verfügung.
		
		
		IPrintable m3 = new Mitarbeiter("Mitarbeiter 3", 2000);
		
		// Über m3 sind nur die Methoden der Schnittstelle 'Printable' verfügbar und die
		// Variable 'varPrint'.
		
		IPrintSetup m4 = new Mitarbeiter("Mitarbeiter 4", 1800);
		
		// Über m4 sind nur die Methoden der Schnittstelle 'IPrintSetup' verfügbar.
		
		Object m5 = new Mitarbeiter("Mitarbeiter 5", 1900);

		// Über m5 sind alle Bezüge zu den Klassen 'AbtractMitarbeiter' und 'Mitarbeiter' und
		// den implementierten Schnittstellen verloren.
		
		// Durch das Überschreiben der Methode toString() in der Klasse 'Mitarbeiter' 
		// wird aber trotzdem der Name des Mitarbeiter ausgegeben.
		System.out.println(m5);
		System.out.println(((Mitarbeiter)m5).getGehalt());
		
		System.out.println();
		
		Mitarbeiter ma1 =  new Mitarbeiter("Mitarbeiter 1", 1800);
		Mitarbeiter ma2 =  new Mitarbeiter("Mitarbeiter 2", 2100);
		Mitarbeiter ma3 =  new Mitarbeiter("Mitarbeiter 3", 2000);
		Mitarbeiter ma4 =  new Mitarbeiter("Mitarbeiter 4", 1800);
		Mitarbeiter ma5 =  new Mitarbeiter("Mitarbeiter 5", 1900);
		Mitarbeiter ma6 =  new Mitarbeiter("Mitarbeiter 6", 1200);
		
		
		Mitarbeiter[] arrMitarbeiter = new Mitarbeiter[]
				{ma6, ma2, ma4, ma3, ma5, ma1 };
		
		System.out.println(Arrays.toString(arrMitarbeiter));
		Arrays.sort(arrMitarbeiter);
		
		System.out.println("Aufsteigende Sortierung nach Name");
		System.out.println(Arrays.toString(arrMitarbeiter));
		System.out.println();
		
		System.out.println("Absteigende Sortierung nach Name");
		Mitarbeiter.sortOrder = Mitarbeiter.SortOrder.DESCENDING;
		Arrays.sort(arrMitarbeiter);
		System.out.println(Arrays.toString(arrMitarbeiter));
		System.out.println();
		
		System.out.println("Aufsteigende Sortierung nach Gehalt");
		Mitarbeiter.sortOrder = Mitarbeiter.SortOrder.ASCENDING;
		Mitarbeiter.sortField =Mitarbeiter.SortField.GEHALT;
		Arrays.sort(arrMitarbeiter);
		System.out.println(Arrays.toString(arrMitarbeiter));
		System.out.println();
		
	
		System.out.println("Absteigende Sortierung nach Gehalt");
		Mitarbeiter.sortOrder = Mitarbeiter.SortOrder.DESCENDING;
		Mitarbeiter.sortField =Mitarbeiter.SortField.GEHALT;
		Arrays.sort(arrMitarbeiter);
		System.out.println(Arrays.toString(arrMitarbeiter));
		System.out.println();
		
		System.out.println("Aufsteigende Sortierung nach Name");
		Mitarbeiter.sort(arrMitarbeiter, Mitarbeiter.SortField.NAME, Mitarbeiter.SortOrder.ASCENDING);
		System.out.println(Arrays.toString(arrMitarbeiter));
		System.out.println();
		
		System.out.println("Absteigende Sortierung nach Name");
		Mitarbeiter.sort(arrMitarbeiter, Mitarbeiter.SortField.NAME, Mitarbeiter.SortOrder.DESCENDING);
		System.out.println(Arrays.toString(arrMitarbeiter));
		System.out.println();
		
		System.out.println("Aufsteigende Sortierung nach Gehalt");
		Mitarbeiter.sort(arrMitarbeiter, Mitarbeiter.SortField.GEHALT, Mitarbeiter.SortOrder.ASCENDING);
		System.out.println(Arrays.toString(arrMitarbeiter));
		System.out.println();
		
		
		System.out.println("Absteigende Sortierung nach Gehalt");
		Mitarbeiter.sort(arrMitarbeiter, Mitarbeiter.SortField.GEHALT, Mitarbeiter.SortOrder.DESCENDING);
		System.out.println(Arrays.toString(arrMitarbeiter));
		System.out.println();
		
		
		Mitarbeiter ma7 = new Mitarbeiter("Mitarbeiter 6", 1200);
		
		System.out.println("HashCodes ausgeben:");
		System.out.println(ma1.hashCode());
		System.out.println(ma2.hashCode());
		System.out.println(ma3.hashCode());
		System.out.println(ma4.hashCode());
		System.out.println(ma5.hashCode());
		System.out.println(ma6.hashCode());
		System.out.println(ma7.hashCode());
		
		System.out.println();
		
		System.out.println("Standarddrucker: " + ma1.getDefaultPrinter());
		
		System.out.println("Pause: " + ma1.pause());
		
		
		
		
		

	}

}
