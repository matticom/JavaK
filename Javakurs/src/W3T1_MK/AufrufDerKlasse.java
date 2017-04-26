package W3T1_MK;

public class AufrufDerKlasse
{

	public static void main(String[] args)
	{
		DatumExt d1 = new DatumExt(1,1,1583);
		System.out.println("Setze das Datum auf den 01.01.1583: "+d1.dateStdFormat());
		d1.addiereTage(3);
		System.out.println("3 Tage auf das aktuelle Datum addiert: "+d1.dateStdFormat());
		d1.subtrahiereTage(3);
		System.out.println("3 Tage von dem aktuellen Datum subtrahiert: "+d1.dateStdFormat());
		d1.subtrahiereTage(1);
		System.out.println("Probiert: 1 Tag von dem aktuellen Datum subtrahiert: "+d1.dateStdFormat());
		d1.addiereMonate(1);
		System.out.println("1 Tag auf das aktuelle Datum addiert: "+d1.dateStdFormat());
		d1.subtrahiereMonate(1);
		System.out.println("1 Monat von dem aktuellen Datum subtrahiert: "+d1.dateStdFormat());
		d1.subtrahiereMonate(1);
		System.out.println("1 Monat von dem aktuellen Datum subtrahiert: "+d1.dateStdFormat());
		System.out.println();
		d1.subtrahiereJahre(1);
		System.out.println("1 Jahr von dem aktuellen Datum subtrahiert: "+d1.dateStdFormat());
		System.out.println();
		d1.addiereJahre(1);
		System.out.println("1 Jahr auf das aktuelle Datum addiert: "+d1.dateStdFormat());
		d1.setDate(29, 2, 2016);
		System.out.println("Setze das Datum auf den 29.02.2016: "+d1.dateStdFormat());
		d1.subtrahiereMonate(1);
		System.out.println("1 Monat von dem aktuellen Datum subtrahiert: "+d1.dateStdFormat());
		d1.addiereMonate(2);
		System.out.println("2 Jahre auf das aktuelle Datum addiert: "+d1.dateStdFormat());
		
		DatumExt d2 = new DatumExt(29,2,2016);
		System.out.println("Setze das Datum auf den 29.02.2016: "+d2.dateStdFormat());
		d2.addiereTage(123456789);
		System.out.println("1 Jahr von dem aktuellen Datum subtrahiert: "+d2.dateStdFormat());
		d2.subtrahiereTage(123456789);
		System.out.println("1 Jahr von dem aktuellen Datum subtrahiert: "+d2.dateStdFormat());
	}

}
