package W2T5_MK;

public class AufrufDerKlasse
{

	public static void main(String[] args)
	{
		Datum d1 = new Datum(30,8,2016);
		System.out.println(d1.dateMonthWordFormat());
		d1.setDate(29, 4, 1985);
		System.out.println(d1.dateStdFormat());
		d1.setDate(29, 2, 2016);
		System.out.println(d1.dateMonthWordFormat());
		d1.setDate(29, 2, 2015);
		d1.setDate(29, 2, 2012);
		System.out.println(d1.dateStdFormat());
		System.out.println();
		System.out.println(Datum.getDEFAULT_Tag());
		System.out.println(Datum.getDEFAULT_Monat());
		System.out.println(Datum.getDEFAULT_Jahr());
		System.out.println();
		Datum.setDEFAULT_Tag(27);
		Datum.setDEFAULT_Monat(1);
		Datum.setDEFAULT_Jahr(2016);
		System.out.println(Datum.getDEFAULT_Tag());
		System.out.println(Datum.getDEFAULT_Monat());
		System.out.println(Datum.getDEFAULT_Jahr());
		System.out.println();
		Datum d2 = new Datum(29,2,2013);
		System.out.println(d2.dateMonthWordFormat());
		Datum.setDEFAULT_Tag(29);
		Datum.setDEFAULT_Monat(2);
		Datum.setDEFAULT_Jahr(2015);
		System.out.println(Datum.getDEFAULT_Tag());
		System.out.println(Datum.getDEFAULT_Monat());
		System.out.println(Datum.getDEFAULT_Jahr());
	}

}
