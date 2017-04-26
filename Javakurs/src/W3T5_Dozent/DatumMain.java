package W3T5_Dozent;

import java.util.Arrays;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class DatumMain
{

	public static void main(String[] args)
	{
		
		Datum d1 = new Datum();
		System.out.println(d1.toString());
		System.out.println(d1.toLongString());
		
		
		d1.setDatum(18, 7, 2016);
		System.out.println(d1.toString());
		System.out.println(d1.toLongString());
		
		d1.setDatum(29, 2, 2016);
		System.out.println(d1.toString());
		System.out.println(d1.toLongString());
		
		d1.setJahr(2017);
		System.out.println(d1.toString());
		System.out.println();
		
		d1.addiereJahre(3);
		System.out.println(d1.toString());
		
		d1.addiereJahre(-3);
		System.out.println(d1.toString());
		System.out.println();
		
		
		d1.addiereMonate(12345);
		System.out.println(d1.toString());
		
		d1.addiereMonate(-12345);
		System.out.println(d1.toString());
				
		d1.addiereTage(1);
		System.out.println(d1.toString());
		System.out.println();
		
		
		d1.addiereTage(123456789);
		System.out.println(d1.toString());
		
		d1.addiereTage(-123456789);
		System.out.println(d1.toString());
		System.out.println();
		
		d1.addiereTage1(123456789);
		System.out.println(d1.toString());
		d1.addiereTage1(-123456789);
		System.out.println(d1.toString());
		System.out.println();
		
		System.out.println(d1.toString());
		System.out.println(d1);
		
		System.out.println("\n");
		
		// Die Klasse 'DatumZeit'
		DatumZeit dz = new DatumZeit();
		System.out.println(dz.toLongString());
		
		dz = new DatumZeit(20, 7, 2016, 10, 56, 32);
		System.out.println(dz.toLongString());
		System.out.println();
		
		dz.addiereStunden(123456789);
		System.out.println(dz.toLongString());
		dz.addiereStunden(-123456789);
		System.out.println(dz.toLongString());
		System.out.println();
		
		dz.addiereMinuten(123456789);
		System.out.println(dz.toLongString());
		dz.addiereMinuten(-123456789);
		System.out.println(dz.toLongString());
		System.out.println();
		
		dz.addiereSekunden(123456789);
		System.out.println(dz.toLongString());
		dz.addiereSekunden(-123456789);
		System.out.println(dz.toLongString());
		System.out.println();
		
		// Verkettete Methodenaufrufe
		String s = "AlfaTraining";
		s = s.toLowerCase().toUpperCase().replace('A', 'X');
		System.out.println(s);
		System.out.println();
		
		System.out.println(d1.toString());
		d1.addiereTage(10).addiereMonate(1).addiereJahre(1).addiereTage(1);
		System.out.println(d1.toString());
		
		
		if (d1.error())
			System.out.println(d1.getLastError());
		
		System.out.println();
		
		System.out.println(Datum.getOstersonntag(2017));
		
		System.out.println(Datum.getBussUndBetTag(2016));
		System.out.println();
		
		
		// Sortierung von Datumswerten
		d1 = new Datum(20, 2, 2014);
		Datum d2 = new Datum(1, 1, 1583);
		Datum d3 = new Datum(31, 12, 2014);
		Datum d4 = new Datum(22, 7, 2016);
		
		Datum[] arrDatum = new Datum[] {d1, d2, d3, d4 };
		System.out.println("Unsortierte Ausgabe der Datumswerte");
		System.out.println(Arrays.toString(arrDatum));
		
		// Aufsteigende Sortierung
		Arrays.sort(arrDatum);
		System.out.println("Datumswerte aufsteigend sortiert");
		System.out.println(Arrays.toString(arrDatum));
		System.out.println();
		
		DatumZeit dz1 = new DatumZeit(20, 2, 2014, 12, 30, 15);
		DatumZeit dz2 = new DatumZeit(20, 2, 2014, 8, 30, 0);
		DatumZeit dz3 = new DatumZeit(1, 1, 2014, 12, 30, 15);
		DatumZeit dz4 = new DatumZeit(1, 1, 2014, 8, 30, 0);
		DatumZeit dz5 = new DatumZeit(1, 1, 1583, 12, 30, 15);
		DatumZeit dz6 = new DatumZeit(1, 1, 1583, 8, 30, 0);
		DatumZeit dz7 = new DatumZeit(31, 12, 2014, 12, 30, 15);
		DatumZeit dz8 = new DatumZeit(31, 12, 2014, 8, 30, 0);
		
		DatumZeit[] arrDatumZeit = new DatumZeit[] { dz3, dz1, dz2, dz4, dz7, dz8, dz5, dz6};
		
		System.out.println("Unsortierte Ausgabe der Datum/Zeit Werte");
		System.out.println(Arrays.toString(arrDatumZeit));
		
		// Aufsteigende Sortierung
		Arrays.sort(arrDatumZeit);
		System.out.println("Datum/Zeit Werte aufsteigend sortiert");
		System.out.println(Arrays.toString(arrDatumZeit));
		System.out.println();
				
		
		// Datum und DatumZeit gemischt
		arrDatum = new Datum[] { dz3, dz1, dz2, dz4, dz7, dz8, dz5, dz6, d1, d2, d3, d4};
				
		System.out.println("Unsortierte Ausgabe der Datum/DatumZeit Werte");
		System.out.println(Arrays.toString(arrDatum));
		
		// Aufsteigende Sortierung
		Arrays.sort(arrDatum);
		System.out.println("Datum/DatumZeit Werte aufsteigend sortiert");
		System.out.println(Arrays.toString(arrDatum));
		System.out.println();
		
		
		
	}
	

	
	

}
