package W1T2_Dozent;

public class Operatoren2
{

	public static void main(String[] args)
	{
		
		/*
		 * Vergleichsoperatoren -> liefern beim Vergleich von Zahlen oder Zeichen
		 * einen logischen Wert ('true' oder 'false')
		 * 
		 * (a) >  (größer als) 
		 * (b) >= (größer oder gleich) 
		 * (c) == (gleich, äquivalent) 
		 * (d) <  (kleiner als) 
		 * (e) <= (kleiner oder gleich) 
		 * (f) != (ungleich, nicht identisch)
		 */
		
		
		double a = 13.4, b = 22.6, c = 19.5;
		
		System.out.println("a: " + a + " b: " + b + " c: " + c);
		System.out.println("a > b: " + (a > b) + ", b >= c: " + (b >=c) + ", c < a: " + (c < a) + ", a == b: " + (a == b) + ", a != b: " + (a !=b));
		
		System.out.println();
		
		/*
		 * Logische Operatoren -> verknüpfen logische Operanden zu einem logischen
		 * Ergebnis ('true' oder 'false')
		 * 
		 * (a) && (logisches AND) 
		 * (b) || (logischer OR) 
		 * (c) !  (logisches NOT) -> unär
		 * 
		 * Operator-Priorität: erst !, dann &&, zuletzt ||, 
		 * d.h. ohne explizite Klammerung wird zuerst das logische NOT ausgewertet, danach das
		 * logische AND und erst zuletzt das logische OR
		 */ 
		
		
		double i = 13.4, j = 22.6, k = 19.5;
		boolean bo;
		
		
		// (a) Beide Bedingungen müssen wahr sein, damit die Gesamtbedingung wahr ist.
		bo = (i < j) && (j < k);
		System.out.println(bo);
		
		// (b) Nur eine Bedingung muss wahr sein, damit die Gesamtbedingung wahr ist.
		bo = (i < j) || (j < k);
		System.out.println(bo);
		
		// (c) Logische Verneinung. Aus 'true' wird 'false', aus 'false' wird 'true'.
		System.out.println(!bo);
		
		System.out.println();
		
		//
		// Bitweise Operatoren
		//
		
		// Ein byte ist ein Datentyp mit einem Wertebereich von -128 bis 127.
		//
		
		byte b1 = (byte)204;
		
		// Der Java-Compiler nimmt dazu einfach die Bitbelegung von 204 und
		// interpretiert das oberste dann gesetzte Bit als Vorzeichenbit.
	
		// Die Bitbelegung von dezimal 204: 1100 1100
		
		//
		// 2 ^ 7 = 128		-	1
		// 2 ^ 6 =  64		-	1
		// 2 ^ 5 =   0		-	0
		// 2 ^ 4 =   0		-	0
		// 2 ^ 3 =   8		-   1
		// 2 ^ 2 =   4		-   1
		// 2 ^ 1 =	 0		-   0
		// 2 ^ 0 =   0		-   0
		// ------------
		// 		   204
		
		/*
		 * Umrechungsvorschrift, um aus einem Binärmuster die 
		 * entsprechenden negative Ganzzahl zu ermitteln.
		 * 
		 * (1) Das Binärmuster bitweise invertieren. 
		 * (2) Das daraus resultierende Bitmuster mit 1 addieren.	
		 * 
		 * (1) 1100 1100 -> 0011 0011
		 * (2) 			  + 0000 0001
		 * --------------------------
		 * 				    0011 0100   (32+16+4) = -52)  
		 * 
		 */ 
		
		System.out.println(b1);
		
		byte b2, b3;
		
		// Der ~ Operator (Tilde) führt eine bitweise Komplementoperation aus.
		
		b2 = (byte)~b1;
		
		// ~1100 1100 
		//  0011 0011  (32+16+2+1 = 51)
		
		System.out.println("~ Operator. Bitweises Komplement: " + b2);
		
		// Der << Operator ist ein binärer Operator, der eine Linksverschiebung
		// ausführt.
		
		// Die am weitestens links stehenden Bits werden verworfen und von rechts
		// werden Nullen eingeschoben.
		
		b2 = (byte)(b1 << 2);
		
		//       -52 
		// 1100 1100 << 2 = 0011 0000 -> (32 + 16) = 48
		System.out.println("<< Operator. Linksverschiebung: " + b2);
		
		// Der >> Operator ist ein binärer Operator, der eine Rechtsverschiebung
		// ausführt.
		// Von links wird mit dem aktuellen Wert des Vorzeichenbits aufgefüllt.
		
		b2 = (byte)(b1 >> 2);
		
		//       -52              -13
		// 1100 1100 >> 2 = 1111 0011
		System.out.println(">> Operator. Rechtsverschiebung: " + b2);

		
		// Der binäre Operator | führt eine bitweise OR-Operation aus.
		// Der Ergebniswert enthält eine 1 an jeder Position, an der einer der
		// beiden Operanden eine 1 enthält.
		
		b2 = 24;
		b3 = (byte)(b1 | b2);
		
		//       -52          24         -36 
		// 1100 1100 | 0001 1000 = 1101 1100 
		System.out.println("| Operator. Bitweises Oder: " + b3);
		
		// Der &-Operator verknüpft zwei Operanden mit einer bitweisen
		// AND-Operation.
		// Der Ergebniswert enthält nur an den Positionen eine 1, an denen beide
		// Operanden eine 1 enthalten.		
		
		b3 = (byte)(b1 & b2);
		
		//       -52          24           8
		// 1100 1100 & 0001 1000 = 0000 1000
		System.out.println("& Operator. Bitweises Und: " + b3);
		
		
		// Der ^-Operator führt eine bitweise XOR-Operation (exklusives Oder) aus
		// und gibt eine 1 für jede Bitposition zurück, an der entweder in dem
		// einen oder in dem anderen Operanden (jedoch nicht in beiden) eine 1 steht.
		// Zwei Einsen liefern eine Null – das ist der „exklusive“ Teil des
		// Operators.
		
		
		b3 = (byte)(b1 ^ b2);
		
		//       -52          24         -44    
		// 1100 1100 ^ 0001 1000 = 1101 0100
		System.out.println("^ Operator. Bitweises exklusives Oder, Antivalenz): " + b3);
		System.out.println();
		

		//
		// Zuweisungsoperatoren
		//

		// =	Zuweisung	
		// +=	Addition	
		// -=	Subtraktion		
		// *=	Multiplikation		
		// /=	Division		
		// %=	Modulo		
		// <<=	Zuweisung nach bitweisem Linksschieben(shift left)	
		// >>=	Zuweisung nach bitweisem Rechtsschieben	(shift right)
		// &=	Bitweises AND (Und)
		// |=	bitweises OR (Oder)
		// ^=	bitweises XOR (exklusives OR)
		
		i = j = k = 42;
		System.out.println("i: " + i + " j: " + j + " k: " + k);
		
		i += 10;			// 52
		j -= 2;				// 40
		k *= 2;             // 84 
				
		System.out.println("i: " + i + " j: " + j + " k: " + k);
		
		System.out.println();
		
		// Zuweisung von hexadezimalen Werten
		int x = 0xFF;
		System.out.println(x);                         // 255
		
		// Zuweisung von Binärwerten
		x = 0b11001100;								  // 204
		System.out.println(x);   
		
		// Zuweisung von Oktalwerten
		x = 036;									  // 30
		System.out.println(x);  
		
		
		
		
		
		
	}

}
