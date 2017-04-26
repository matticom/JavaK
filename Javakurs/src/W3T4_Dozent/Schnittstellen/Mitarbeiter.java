package W3T4_Dozent.Schnittstellen;

import java.util.Arrays;

// Schnittstelle Comparable: Damit können sich die Objekte selbst mit anderen Objekten vergleichen. 
// Sollen Objekte verglichen werden, muss es ein Vergleichskriterium für diese Objekte geben: Name, Grösse, Menge, Gehalt...

// Die Schnittstelle Comparable kommt aus dem java.lang-Paket und deklariert eine Methode:compareTo().
// Die Methode 'sort' der Klasse 'Array' verspricht, ein Array von Objekten zu sortieren. Allerdings
// unter der Bedingung dass die Objekte zu Klassen gehören, die die Schnittstelle 'Comparable' implementieren.

public class Mitarbeiter extends AbstractMitarbeiter implements IPrintable,  IPrintSetup, Comparable<Mitarbeiter>
{
	
	/*
	 * Mit Enumerations (Enums) wurde in Java 1.5 ein lang vermisstes Element
	 * der Programmiersprache nachgereicht, welches in anderen Sprachen schon
	 * lange vorhanden ist. Durch Enums ist es möglich auf einfachste und
	 * sichere Art und Weise Aufzählungen zu realisieren, die früher über
	 * statische Konstante implementiert werden mussten.
	 */
	
	public static enum SortOrder
	{
		ASCENDING, DESCENDING
	}
	
	
	public static enum SortField
	{
		NAME, GEHALT
	}
	
	// protected, wegen evtl. Ableitung (Vererbung)
	protected static SortOrder sortOrder = SortOrder.ASCENDING;
	protected static SortField sortField = SortField.NAME;
	
	
	private double gehalt;
	
	public Mitarbeiter (String name, double gehalt)
	{
		super(name);
		this.gehalt = gehalt;
		
	}

	
	// Optionale Verwendung der statischen Schnittstellen-Methode getDefaultPrintService()
	public String getDefaultPrinter()
	{
		return IPrintable.getDefaultPrintService();
	}
	
	
	
	
	
	@Override
	public boolean pause()
	{
		return IPrintable.super.pause();
	}


	@Override
	public double getGehalt()
	{
		return gehalt;
	}

	@Override
	public void Print()
	{
		System.out.println("Druckerauswahl-Dialog anzeigen.");
		
	}

	@Override
	public void ImmediatePrint()
	{
		System.out.println("Sofortige Druckausgabe über den Standarddrucker.");
		
	}

	@Override
	public void Preview()
	{
		System.out.println("Seitenansicht anzeigen.");
		
	}


	@Override
	public void PrinterSetup()
	{
		System.out.println("Dialog für die Druckereinstellungen anzeigen.");
		
	}

	@Override
	public void PageSetup()
	{
		System.out.println("Dialog für die Seiteneinstellungen anzeigen.");
	}

	@Override
	public String toString()
	{
		
		return getName();
		
	}
	
	// compareTo() gibt einen int-Wert zurück der signalisiert, ob das
	// Argument kleiner oder größer als das Mitarbeiter-Objekt
	// ist bzw. mit diesem übereinstimmt.
	
    // Rückgabewerte: 1 = Der eigene Wert ist größer als der Argumentwert, 
    //              < 0 = der eigene Wert ist kleiner als der Argumentwert, 
    //				  0 = die Werte stimmen überein.
	
	@Override
	public int compareTo(Mitarbeiter that)
	{
		
		// Einfache Sortierung nach Name
		//return this.getName().compareTo(that.getName());
		
		// Einfache absteigende Sortierung nach Name
		//return that.getName().compareTo(this.getName());
		// oder
		//return -this.getName().compareTo(that.getName());
		// oder
		//return this.getName().compareTo(that.getName()) * -1;
		
		int retValue = 0;
		int mult = 1;
		
		if (sortOrder == SortOrder.DESCENDING)
			mult = -1;
		
		
		switch(sortField)
		{
		
		case NAME:
			retValue = this.getName().compareTo(that.getName());
			break;
			
		case GEHALT:
//			if (this.getGehalt() < that.getGehalt())
//				retValue = -1;
//			else if (this.getGehalt() > that.getGehalt())
//				retValue = 1;
//			else 
//				retValue = 0;
			
			retValue = Double.compare(this.getGehalt(), that.getGehalt());
			
			break;
		}
		
	
		// Auf-/absteigende Sortierung
		return retValue * mult;
		
	}

	// Wenn equals() nicht sinnvoll überschrieben wird verhält es sich wie ==
	// also werden Referenzen verglichen und keine Werte.
	
	@Override
	public boolean equals(Object that)
	{
		
		boolean retValue = false;
		
		// Wenn auf das gleiche Objekt verwiesen wird
		if (this == that)
			return true;
		
		// Wenn der Argumenttyp ungültig ist (kein Vergleich von Äpfeln mit Birnen).
		if (!(that instanceof Mitarbeiter))
			return false;
		
		
		//retValue = this.getName().equals(((Mitarbeiter)that).getName());
		// oder
		//retValue = this.compareTo((Mitarbeiter)that) == 0;
		
		switch(sortField)
		{
		
		case NAME:
			retValue = this.getName().equals(((Mitarbeiter)that).getName());
			break;
			
		case GEHALT:
			retValue = this.getGehalt() == ((Mitarbeiter)that).getGehalt();
			break;
		}
		
		
		return retValue;
	}

	
	// Eine Klasse die 'equals() überschreibt muss auch die Methode
	// hashCode() überschreiben.
	// Das Überschreiben der Methode 'equals()' bedeuted, dass ein bestimmtes
	// Kriterium für den Vergleich des Objekts verwendet wird.
	// Diese Kriterium sollte auch für Methode 'hashCode()' verwendet werden.

	// Die Methode hashCode() soll zu jedem Objekt eine möglichst eindeutige
	// Integerzahl (sowohl positiv als auch negativ)
	// liefern, die das Objekt identifiziert. Die Ganzzahl heißt Hashcode
	// beziehungsweise Hash-Wert, und hashCode() ist die
	// Implementierung einer Hash-Funktion. Nötig sind Hashcodes, wenn die
	// Objekte in speziellen Datenstrukturen untergebracht werden,
	// die nach dem Hashing-Verfahren arbeiten (Assoziativspeicher HashMap,
	// TreeMap).
	// Datenstrukturen mit Hashing-Algorithmen bieten effizienten Zugriff auf
	// ihre Elemente.	
	
	@Override
	public int hashCode()
	{
		int retValue = 0;
		
		
		switch(sortField)
		{
			case NAME:
				if (this.getName() != null)
					retValue = this.getName().hashCode();
				else 
					retValue = 0;
				break;
			
			case GEHALT:
				retValue = Double.hashCode(this.getGehalt());
				break;
				
		}
		
		return retValue;
	}
	
	
	public static void sort(Mitarbeiter[] a, Mitarbeiter.SortField sortField, Mitarbeiter.SortOrder sortOrder)
	{
		Mitarbeiter.sortField = sortField;
		Mitarbeiter.sortOrder = sortOrder;
		Arrays.sort(a);
	}
	
	
	
	
	
	
	
	
}
