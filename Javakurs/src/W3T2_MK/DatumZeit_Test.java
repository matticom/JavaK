package W3T2_MK;

public class DatumZeit_Test
{

	public static void main(String[] args)
	{
		DatumZeit d1 = new DatumZeit(20,3,2016,0,0,14);
		System.out.println(d1.dateFormat());
		d1.addiereStunden(11);
		System.out.println(d1.dateFormat());
		d1.addiereStunden(2);
		System.out.println(d1.dateFormat());
		d1.addiereStunden(20);
		System.out.println(d1.dateFormat());
		d1.addiereStunden(1);
		System.out.println(d1.dateFormat());
		System.out.println();
		d1.addiereStunden(-1);
		System.out.println(d1.dateFormat());
		d1.addiereMinuten(-122);
		System.out.println(d1.dateFormat());
		d1.addiereSekunden(-1222);
		System.out.println(d1.dateFormat());
		System.out.println();
		System.out.println("Neues Objekt:D2-----");
		DatumZeit d2 = new DatumZeit(1,1,1583,0,59,1);
		System.out.println(d2.dateFormat());
		d2.addiereStunden(-1);
		System.out.println(d2.dateFormat());
		d2.addiereStunden(-1);
		System.out.println(d2.dateFormat());
		System.out.println();
		System.out.println("Neues Objekt:D3-------");
		DatumZeit d3 = new DatumZeit(1,1,2083,0,59,1);
		System.out.println(d3.dateFormat());
		d3.addiereSekunden(1234567891);
		System.out.println(d3.dateFormat());
		d3.addiereSekunden(-1234567891);
		System.out.println(d3.dateFormat());

	}

}
