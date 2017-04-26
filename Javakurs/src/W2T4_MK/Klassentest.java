package W2T4_MK;

public class Klassentest
{

	public static void main(String[] args)
	{
		Monat j2016 = new Monat(7);
		System.out.println(j2016.nameOfMouth());
		System.out.println(j2016.getDefaultMonat());
		j2016.changeDefaultMonat(2);
		System.out.println(j2016.getDefaultMonat());
		Monat.changeDefaultMonat(12);
		System.out.println(Monat.getDefaultMonat());
		
	}

}
