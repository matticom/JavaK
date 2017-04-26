package W3T2_Dozent;

public class DatumMain
{

	public static void main(String[] args)
	{
		
		Datum d1 = new Datum();
		System.out.println(d1.toDateString());
		System.out.println(d1.toLongDateString());
		
		
		d1.setDatum(18, 7, 2016);
		System.out.println(d1.toDateString());
		System.out.println(d1.toLongDateString());
		
		d1.setDatum(29, 2, 2016);
		System.out.println(d1.toDateString());
		System.out.println(d1.toLongDateString());
		
		d1.setJahr(2017);
		System.out.println(d1.toDateString());
		System.out.println();
		
		d1.addiereJahre(3);
		System.out.println(d1.toDateString());
		
		d1.addiereJahre(-3);
		System.out.println(d1.toDateString());
		System.out.println();
		
		
		d1.addiereMonate(12345);
		System.out.println(d1.toDateString());
		
		d1.addiereMonate(-12345);
		System.out.println(d1.toDateString());
				
		d1.addiereTage(1);
		System.out.println(d1.toDateString());
		System.out.println();
		
		
		d1.addiereTage(123456789);
		System.out.println(d1.toDateString());
		
		d1.addiereTage(-123456789);
		System.out.println(d1.toDateString());
		System.out.println();
		
		d1.addiereTage1(123456789);
		System.out.println(d1.toDateString());
		d1.addiereTage1(-123456789);
		System.out.println(d1.toDateString());
		System.out.println();
		
		System.out.println(d1.toString());
		System.out.println(d1);
		
		
	}

}
