package W1T3_MK;

import java.util.Scanner;

public class BMI
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Bitte geben Sie ihre K�rpergr��e in m an (zb 1,88):");
		double bodysize;
		bodysize = in.nextDouble();
		System.out.println();
		System.out.println("Bitte geben Sie ihr Gewicht in Kilogramm an (zb 74,7):");
		
		double weight;
		weight = in.nextDouble();
		
		double bmidb;
		bmidb=weight/(bodysize*bodysize);
		
		int bmi;
		bmi = (int)(bmidb/5);
		
		switch(bmi)
		{
		case 0:
		case 1: System.out.println("�berpr�fen sie ihre Eingabe, ihr BMI ist kleiner als 10");break;
		case 2: System.out.printf("Ihr BMI betr�gt %2.2f d.h. sie sind magers�chtig.\n", bmidb);break;
		case 3: System.out.printf("Ihr BMI betr�gt %2.2f d.h. sie sind untergewichtig.\n", bmidb);break;
		case 4: System.out.printf("Ihr BMI betr�gt %2.2f d.h. sie sind normalgewichtig.\n", bmidb);break;
		case 5: System.out.printf("Ihr BMI betr�gt %2.2f d.h. sie sind �bergewichtigt.\n", bmidb);break;
		case 6: System.out.printf("Ihr BMI betr�gt %2.2f d.h. sie sind fetts�chtig.\n", bmidb);break;
		case 7: System.out.printf("Ihr BMI betr�gt %2.2f d.h. sie sind morbide fetts�chtig.\n", bmidb);break;
		default: System.out.printf("Ihr Wert %5.2f kann nicht stimmen, bitte �berpr�fen sie ihre Eingabe.\n", bmidb);break;
		}
	}

}
