package W3T3_Dozent.Schnittstellen;

// Schnittstelle Comparable: Damit können sich die Objekte selbst mit anderen Objekten vergleichen. 
// Sollen Objekte verglichen werden, muss es ein Vergleichskriterium für diese Objekte geben: Name, Grösse, Menge, Gehalt...

public class Mitarbeiter extends AbstractMitarbeiter implements IPrintable, IPrintSetup, Comparable<Mitarbeiter>
{

	private double gehalt;

	public Mitarbeiter(String name, double gehalt)
	{
		super(name);
		this.gehalt = gehalt;

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

	@Override
	public int compareTo(Mitarbeiter that)
	{

		// Einfache Sortierung nach Name
		// return this.getName().compareTo(that.getName());

		// Einfache Sortierung nach Gehalt
		if (this.getGehalt() < that.getGehalt())
			return -1;
		else
		{
			if (this.getGehalt() == that.getGehalt())
				return 0;
			else
				return 1;
		}

		// Einfache absteigende Sortierung nach Name
		// return that.getName().compareTo(this.getName());
		// oder
		// return -this.getName().compareTo(that.getName());
		// oder
		// return this.getName().compareTo(that.getName()) * -1;

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

		// retValue = this.getName().equals(((Mitarbeiter)that).getName());
		// oder
		retValue = this.compareTo((Mitarbeiter) that) == 0;

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

		// hashCode aus Name des Objekts
		// if (this.getName() != null)
		// retValue = this.getName().hashCode();

		// hashCode aus Gehalt des Objekts
		// retValue = Double.toString(this.getGehalt()).hashCode();
		// oder
		retValue = Double.hashCode(this.getGehalt());

		return retValue;
	}

}
