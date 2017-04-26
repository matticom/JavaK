package W3T4_Dozent;

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
		
	}
	

	
	

}
