package W1T3_MK;

import java.util.Scanner;

public class BMI
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Bitte geben Sie ihre Körpergröße in m an (zb 1,88):");
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
		case 1: System.out.println("Überprüfen sie ihre Eingabe, ihr BMI ist kleiner als 10");break;
		case 2: System.out.printf("Ihr BMI beträgt %2.2f d.h. sie sind magersüchtig.\n", bmidb);break;
		case 3: System.out.printf("Ihr BMI beträgt %2.2f d.h. sie sind untergewichtig.\n", bmidb);break;
		case 4: System.out.printf("Ihr BMI beträgt %2.2f d.h. sie sind normalgewichtig.\n", bmidb);break;
		case 5: System.out.printf("Ihr BMI beträgt %2.2f d.h. sie sind übergewichtigt.\n", bmidb);break;
		case 6: System.out.printf("Ihr BMI beträgt %2.2f d.h. sie sind fettsüchtig.\n", bmidb);break;
		case 7: System.out.printf("Ihr BMI beträgt %2.2f d.h. sie sind morbide fettsüchtig.\n", bmidb);break;
		default: System.out.printf("Ihr Wert %5.2f kann nicht stimmen, bitte überprüfen sie ihre Eingabe.\n", bmidb);break;
		}
	}

}
