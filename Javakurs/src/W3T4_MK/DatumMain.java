package W3T4_MK;

import java.util.Arrays;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class DatumMain
{

	public static void main(String[] args)
	{
		
		DatumZeit d1 = new DatumZeit(1, 1, 1583, 0, 0, 5);
//		System.out.println(d1.toString());
//		System.out.println(d1.toLongString());
//		
//		
//		d1.setDatum(18, 7, 2016);
//		System.out.println(d1.toString());
//		System.out.println(d1.toLongString());
//		
//		d1.setDatum(29, 2, 2016);
//		System.out.println(d1.toString());
//		System.out.println(d1.toLongString());
//		
//		d1.setJahr(2017);
//		System.out.println(d1.toString());
//		System.out.println();
//		
//		d1.addiereJahre(3);
//		System.out.println(d1.toString());
//		
//		d1.addiereJahre(-3);
//		System.out.println(d1.toString());
//		System.out.println();
//		
//		
//		d1.addiereMonate(12345);
//		System.out.println(d1.toString());
//		
//		d1.addiereMonate(-12345);
//		System.out.println(d1.toString());
//				
//		d1.addiereTage(1);
//		System.out.println(d1.toString());
//		System.out.println();
//		
//		
//		d1.addiereTage(123456789);
//		System.out.println(d1.toString());
//		
//		d1.addiereTage(-123456789);
//		System.out.println(d1.toString());
//		System.out.println();
//		
//		d1.addiereTage1(123456789);
//		System.out.println(d1.toString());
//		d1.addiereTage1(-123456789);
//		System.out.println(d1.toString());
//		System.out.println();
//		
//		System.out.println(d1.toString());
//		System.out.println(d1);
//		
//		System.out.println("\n");
//		
		// Die Klasse 'DatumZeit'
		DatumZeit dz = new DatumZeit();
//		System.out.println(dz.toLongString());
//		
//		
//		
//		
//		dz = new DatumZeit(20, 7, 2016, 10, 56, 32);
//		System.out.println(dz.toLongString());
//		System.out.println();
//		
////		dz.addiereStunden(123456789);
////		System.out.println(dz.toLongString());
////		dz.addiereStunden(-123456789);
////		System.out.println(dz.toLongString());
////		System.out.println();
//		
////		dz.addiereMinuten(123456789);
////		System.out.println(dz.toLongString());
////		dz.addiereMinuten(-123456789);
////		System.out.println(dz.toLongString());
////		System.out.println();
////		
////		dz.addiereSekunden(123456789);
////		System.out.println(dz.toLongString());
////		dz.addiereSekunden(-123456789);
////		System.out.println(dz.toLongString());
////		System.out.println();
//		
//		// Verkettete Methodenaufrufe
//		String s = "AlfaTraining";
//		s = s.toLowerCase().toUpperCase().replace('A', 'X');
//		System.out.println(s);
//		System.out.println();
//		
//		System.out.println(d1.toString());
		d1.addiereSekunden(-1000000000);
		System.out.println(d1.getLastError());
		System.out.println(d1.toString());
//		
//		if (d1.error())
//			System.out.println(d1.getLastError());
//		
//		System.out.println();
//		
//		System.out.println(Datum.getOstersonntag(2017));
//		
//		System.out.println(Datum.getBussUndBetTag(2016));
		
		
		dz = new DatumZeit(1, 1, 1583, 0, 0, 0);
		DatumZeit da = new DatumZeit(1, 1, 1583, 0, 0, 2);
		DatumZeit db = new DatumZeit(1, 1, 1583, 0, 0, 2);
//		System.out.println(dz.toLongString());
//		System.out.println();
//		System.out.println(dz.getLastError());
//		dz.addiereSekunden(-2);
//		System.out.println(dz.toString());
//		System.out.println(dz.getLastError());
		
		DatumZeit[] dates = new DatumZeit[] {new DatumZeit(1, 1, 1583, 0, 0, 5), new DatumZeit(1, 1, 1583, 0, 0, 1), 
				new DatumZeit(1, 1, 1583, 0, 0, 7), new DatumZeit(1, 1, 1583, 0, 0, 5)};
//
		Arrays.sort(dates);
		System.out.println(Arrays.toString(dates));
		System.out.println(da.equals(db));
		System.out.println(dz.hashCode());
		for (DatumZeit date : dates)
			System.out.println(date.hashCode());
		Datum date = new Datum(1, 1, 1583);
		System.out.println(dz.compareTo(date));
	}
	

	
	

	
	

}
