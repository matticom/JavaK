package W1T3_Dozent;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AusgabeFormatieren
{

	public static void main(String[] args)
	{
		
		// Normale Ausgabe eines Wertes mit der Methode println()
		System.out.println("PI: " + Math.PI);
		
		double radius = 0.8;
		
		// Kreisumfang berechnen (2 * Radois * PI)
		System.out.println("Kreisumfang: " + (2 * radius * Math.PI));
		
		System.out.println();
		
		
		// ---------------------------------
		// Formatieren von Ausgaben printf()
		// ---------------------------------
		
		// Syntax:
		// System.out.printf("<Werte formatiert ank�ndigen>", wert1, wert2, ...);
		
		// Formatierungsanweisung :
		// %[Schalter][Breite].[Genauigkeit]Umwandlung
		
		// Die optionalen Schalter ver�ndern das Format der Ausgabe. Ein
		// Minuszeichen (-) z.B. sorgt f�r linksb�ndige Ausgabe.
		// Die optionale Breite ist eine nicht-negative Ganzzahl, die die
		// Mindestanzahl der ausgegebenen Zeichen bestimmt.
		// Mit der optionalen Genauigkeit wird �blicherweise die Anzahl der
		// ausgegebenen Zeichen eingeschr�nkt.
		// Umwandlung ist ein Buchstabe der anzeigt, wie das zugeh�rige Argument
		// formatiert wird.
		
		// Beispiele f�r m�gliche Werte von Umwandlung sind:

		// %s - Der zugeh�rige Parameter wird als String ausgegeben.
		// %S - Der zugeh�rige Parameter wird als String in Gro�buchstaben ausgegeben.
		// %d - Der zugeh�rige Parameter wird als Ganzzahl ausgegeben.
		// %f - Der zugeh�rige Parameter wird als Dezimalzahl ausgegeben.
		// %X - Der zugeh�rige Parameter wird als Hexadezimalwert ausgegeben.
		
		// Es wird keine Mindestbreite und keine Genauigkeit angegeben. Java
		// verwendet dann Standardwerte.
		System.out.printf("PI: %f\n", Math.PI);
		
		// Es werden mindestens 30 Stellen f�r die Ausgabe bereitgestellt. Da nur
		// 5 Stellen ben�tigt werden (1 vor dem Komma, das Komma und 3 Dezimalstellen), 
		// erscheinen vor der Zahl 25 Leerstellen.
		System.out.printf(">>PI:%30.3f<<\n", Math.PI);
		
		// �hnliches gilt f�r diese Anweisung. Die Leerstellen erscheinen jedoch
		// nach der Zahl, da das Minuszeichen f�r eine linksb�ndige Ausgabe sorgt.
		System.out.printf(">>PI:%-30.3f<<\n", Math.PI);
		
		System.out.printf("%S %d %s und %d %s\n", "endpreis", 7, "Euro", 12, "Cent");
		
		// Hexadezimal (X, x, H, h)
		System.out.printf("X'%02X'\n", 9);
		
		// Uhrzeit
		System.out.printf("%02d:%02d:%02d Uhr\n", 9, 34, 7);
		
		System.out.println();
		
		// Format-Spezifizierer f�r Datumswerte
		//
		// Symbol 		Beschreibung
		// --------- 	--------------------------------------------------------------
		// %tA, %ta 	vollst�ndiger, abgek�rzter Name des Wochentags
		// %tB, %tb 	vollst�ndiger, abgek�rzter Name des Monatsnamens
		// %tC 			zweistelliges Jahrhundert (00-99)
		// %te, %td 	Monatstag numerisch ohne bzw. mit f�hrenden Nullen
		// %tk, %tl 	Stundenangabe im 24 bzw. 12 Stunden Format (0-23, 1-12)
		// %tH, %tI 	Zweistellige Stundenangabe im 24 bzw. 12 Stunden Format (00-23, 01-12)
		// %tj 			Tag des Jahres (001-366)
		// %tM 			Zweistellige Minutenangabe (00-59)
		// %tm 			Zweistellige Monatsangabe (01-12)
		// %tS 			Zweistellige Sekundenangabe (00-59)
		// %tY 			Vierstellige Jahresangabe
		// %ty 			Die letzten beiden Ziffern der Jahresangabe (00-99)
		// %tZ 			Abgek�rzte Zeitzone
		// %tz 			Zeitzone mit Verschiebung zu GMT
		// %tR 			Stunden und Minuten in der Form %tH:%tM
		// %tT 			Stunden/Minuten/Sekunden: %tH:%tM:%tS
		// %tD 			Datum in der Form %tm/%td/%ty
		// %tF 			ISO-8601-Format %tY-%tm-%td
		// %tc 			Komplettes Datum mit der Zeit in der Form %ta %tb %td %tT %tZ %tY
		
		
		Date dt = new Date();
		
		System.out.println(dt);
		
		System.out.printf("%tc\n", dt);
		
		System.out.printf("%tA, %td. %tB %tY\n", dt, dt, dt, dt);
		
		System.out.println();
		
	    // --------------
		// Format-Klassen
	    // --------------

		// DateFormat: 		Formatieren von Datums- und Zeitwerten
		// NumberFormat: 	Formatieren von Zahlen
		// MessageFormat: 	Formatieren von allgemeinen Programmmeldungen
		
		System.out.println(DateFormat.getDateInstance().format(dt));
		System.out.println(DateFormat.getTimeInstance().format(dt));
		System.out.println(DateFormat.getDateTimeInstance().format(dt));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		System.out.println(sdf.format(dt));
		
		// Parameter 	Bedeutung 			Beispiel
		// ---------------------------------------------
		// yyyy 		Jahr vierstellig 		2010
		// yy 			Jahr zweistellig 	  	  10
		// MM 			Monat zweistellig 	  	  12
		// w 			Kalenderwoche 		  	  34
		// d 			Tag im Monat 		  	  15
		// HH 			Stunde (24h-Format)   	  13
		// mm 			Minute der Stunde 	  	  05
		// ss 			Sekunde der Minute 	  	  34
		
		sdf = new SimpleDateFormat("dd. MMM yyyy");
		System.out.println(sdf.format(dt));
		
		sdf = new SimpleDateFormat("dd. MMMM yyyy");
		System.out.println(sdf.format(dt));
		
		System.out.println();
		
		System.out.println(NumberFormat.getInstance().format(12345.6789));					     // 12.345,679
		System.out.println(NumberFormat.getCurrencyInstance().format(12345.6789));				 // 12.345,68 �
		System.out.println(NumberFormat.getPercentInstance().format(0.12));						 // 12%
		
		System.out.println();
		
		// Dezimalzahlformatierung mit DecimalFormat
        // -----------------------------------------
		// DecimalFormat ist eine Unterklasse von NumberFormat und erm�glicht
		// individuellere Anpassungen an die Ausgabe.
		// Dem Konstruktor kann ein Formatierungs-String �bergeben werden,
		// sozusagen eine Vorlage, wie die Zahlen zu formatieren sind.
		// Die Formatierung einer Zahl durch DecimalFormat erfolgt mit R�cksicht
		// auf die aktuell eingestellte Sprache.
		
		// Symbol 		Bedeutung
		// ------ 		----------------------------------------------------------------------------------
		// 0 	  		Repr�sentiert eine Ziffer � ist die Stelle nicht belegt, wird eine Null angezeigt.
		// # 	  		Repr�sentiert eine Ziffer � ist die Stelle nicht belegt, bleibt sie
		//   	  		leer, damit f�hrende Nullen und unn�tige Nullen hinter dem Komma nicht
		//   	  		angezeigt werden.
		// . 	  		Dezimaltrenner. Trennt Vor- und Nachkommastellen.
		// , 	  		Gruppiert die Ziffern (eine Gruppe ist so gro� wie der Abstand von
		//   	  		"," zu ".").
		// ; 	  		Trennzeichen. Links davon steht das Muster f�r positive, rechts das
		//   	  		f�r negative Zahlen.
		// - 	  		Das Standardzeichen f�r das Negativpr�fix
		// % 	  		Die Zahl wird mit 100 multipliziert und als Prozentwert ausgewiesen.
		// \u2030 		Die Zahl wird mit 1000 multipliziert und als Promillewert ausgewiesen.
		// \u00A4 		Nationales W�hrungssymbol (� f�r Deutschland) - Unicode Escape
		//        		Sequenz
		// \u00A4\u00A4 Internationales W�hrungssymbol (EUR f�r Deutschland) -
		//        		Unicode Escape Sequenz
		
		// Beispiele
		
		double d = 12.0;
		DecimalFormat df;
		
		// Vierstellig mit f�hrenden Nullen
		df = new DecimalFormat("0000");
		System.out.println(df.format(d));	   												// 0012
		
		// Mit zwei Nachkommastellen
		df = new DecimalFormat(".00");
		System.out.println(df.format(d));													// 12,00
		
		d = 12.345;
		System.out.println(df.format(d));													// 12,35
		
		d = 0.3456;
		System.out.println(df.format(d));													// ,35
		
		// Mit mindestens einer Vorkommastelle und zwei Nachkommastellen
		df = new DecimalFormat("0.00");
		System.out.println(df.format(d));													// 0,35
		
		// Mit nationalem W�hrungssymbol
		df = new DecimalFormat("0.00 \u00A4");
		System.out.println(df.format(d));													// 0,35 �
		
		// Mit internationalem W�hrungssymbol
		df = new DecimalFormat("0.00 \u00A4\u00A4");
		System.out.println(df.format(d));													// 0,35 EUR
		
		
		// Mit Trennzeichen f�r positive und negative Werte
		df = new DecimalFormat("0.00;0.00 CR");
		System.out.println(df.format(d));													// 0,35
		
		// Umkehrung des Vorzeichens
		//d = d * -1;
		// oder
		//d *= -1;
		// oder
		d = -d;
		System.out.println(df.format(d));													// 0,35 CR
		
		
		// Ausgabe als Prozentwert
		df = new DecimalFormat("0.00 %");
		System.out.println(df.format(0.12));												// 12,00 %
		
		// Ausgabe als Promillewert
		df = new DecimalFormat("0.00 \u2030");
		System.out.println(df.format(0.12));												// 120,00 �
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
