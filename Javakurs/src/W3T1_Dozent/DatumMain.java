package W3T1_Dozent;

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
		
		
		
		
	}

}
