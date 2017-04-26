package W1T4_Dozent;

/*
 * Methoden
 * 
 * Bestandteil einer Methode
 * Eine Methode setzt sich aus mehreren Bestandteilen zusammen. Dazu gehören der
 * Methodenkopf und der Methodenrumpf. Der Kopf besteht aus einem Rückgabetyp,
 * auch Ergebnistyp genannt, dem Methodennnamen und einer optionalen
 * Parameterliste.
 * 
 * Die Signatur einer Methode
 * Der Methodennname und die Parameterliste bestimmen die Signatur einer Methode;
 * der Rückgabetyp gehört nicht dazu. Die Parameterliste ist durch die Anzahl,
 * die Reihenfolge und die Typen der Parameter beschrieben. Pro Klasse darf es
 * nur eine Methode mir derselben Signatur geben.
 * 
 * Aufruf einer Methode
 * Da eine Methode immer einer Klasse oder einem Objekt zugeordnet ist, muss
 * der Eigentümer beim Aufruf angegeben werden. Nur wenn die Methode innerhalb
 * der eigenen Klasse verwendet wird kann der Eigentümer weggelassen werden.
 * Die aufgerufene Methode wird mit ihrem Namen genannt. Die Parameterliste
 * wird durch ein Klammerpaar umschlossen. Diese Klammern müssen auch dann
 * gesetzt werden, wenn die Methode keine Parameter enthält.
 * 
 * Statische Methoden (Klassenmethoden)
 * Statische Methoden müssen explizit mit dem Schlüsselwort 'static' kenntlich
 * gemacht werden. Fehlt der Modifizierer 'static', so wird damit eine Objektmethode
 * deklariert die nur aufgerufen werden kann, wenn mann vorher ein Objekt an-
 * gelegt (instanziiert) hat. 
 * Das Besondere an statischen Methoden ist, dass sie nicht an einem Objekt 
 * hängen und daher immer ohne explizit erzeugtes Objekt aufgerufen werden 
 * können.
 * 
 * 
 */

public class Modularisierung
{
	
	// Globale Variable
	private static int p1 = 42;
	

	public static void main(String[] args)
	{
		
		System.out.println("Aufruf der Methode NeueZeile()");
		NeueZeile();
		System.out.println("Ende der Methode NeueZeile()");
		
		System.out.println("Aufruf der Methode NeueZeile(int)");
		NeueZeile(3);
		System.out.println("Ende der Methode NeueZeile(int)");
		
		int p1 = 12;
		M1(p1);
		// Die Änderung der Variable 'p1' in der Methode M1(int)
		// hat keine Auswirkungen auf die Variable 'p1'
		// der Methode main(),
		System.out.println(p1);
		System.out.println();
		
		// Aufruf der Methode M1(), welche die globale Variable p1 verwendet.
		M1();
		System.out.println(Modularisierung.p1);
		System.out.println();
		
		int ergebnis = Mult(12, 64);
		System.out.println(ergebnis);
		
		System.out.println(Mult(12, 64));
		System.out.println(Mult(Mult(3, 4), Mult(8, 8)));
		
		System.out.println(Mult(122.35, 2.89));
		System.out.println();
		
		OptionaleParameter(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		System.out.println();
		OptionaleParameter(1, 2, 3);
		System.out.println();
		OptionaleParameter(99);
		System.out.println();
		
		// Beispiel Short Circuit Evaluation
		// Ohne Shortcut Evaluation müssten zuerst beide Ausdrücke
		// ausgewertet werden.
		
		if (Methode1() == 1 && Methode2() == 2)
			System.out.println("Die Bedingung ist wahr.");
		else
			System.out.println("Die Bedingung ist nicht wahr.");
		
		System.out.println();
		
		if (Methode1() == 1 || Methode2() == 2)
			System.out.println("Die Bedingung ist wahr.");
		else
			System.out.println("Die Bedingung ist nicht wahr.");
		
		System.out.println();
		
		
		if (Methode1() == 2 && Methode2() == 2)
			System.out.println("Die Bedingung ist wahr.");
		else
			System.out.println("Die Bedingung ist nicht wahr.");
	}

	private static void NeueZeile()
	{
		System.out.println();
	}
	
	
	private static void NeueZeile(int count)
	{
		
//		for (int i = 1; i <= count; i++)
//			System.out.println();
		
		// oder:
		while (count-- > 0)
			System.out.println();
		
		
		
	}
	
	
	private static void M1(int p1)
	{
		
		System.out.println("Dies ist die Methode M1(int)");
		System.out.println("Übergebener Parameter: " + p1);
		
		p1 = 100;
		
		
		
	}
	
	private static void M1()
	{
		
		System.out.println("Dies ist die Methode M1()");
		System.out.println("Globale Variable p1: "  + p1);
		
		p1 = 100;
		
	}
	
	
	private static int Mult(int p1, int p2)
	{
		int retValue = 0;
		
		retValue = p1 * p2;
			
		
		return retValue;
	}
	
	
	private static double Mult(double p1, double p2)
	{		
		return (p1 * p2);
	}
	
	// Methode mit variabler Parameterliste.
	// Technisch entspricht die Deklaration der eines Arrays-Parameters, und so
	// wird auch auf die Elemente zugegriffen.
	// Dabei ist zu beachten, dass lediglich der letzte Parameter variabel sein
	// darf. Andernfalls könnte der Compiler
	// nicht mehr unterscheiden, welches aktuelle Argument zu welchem formalen
	// Parameter gehört.
	// Praktischen Nutzen haben die variablen Parameterlisten bei Anwendungen, in
	// denen nicht von vorneherein klar ist,
	// wieviele Argumente benötigt werden. Tatsächlich wurde ihre Entwicklung
	// durch den Wunsch motiviert, flexible Ausgabemethoden
	// definieren zu können, wie in anderen Programmierspachen auch (z.B.
	// System.out.printf()).
	
	private static void OptionaleParameter(int ... parm)
	{
		
		for (int i = 0; i < parm.length; i++)
			System.out.println(parm[i]);
		
		
	}
	
	
	private static int Methode1()
	{
		
		System.out.println("Die Methode 1 wird ausgeführt.");
		return 1;
		
	}
	
	private static int Methode2()
	{
		
		System.out.println("Die Methode 2 wird ausgeführt.");
		return 2;
		
	}
	
}
