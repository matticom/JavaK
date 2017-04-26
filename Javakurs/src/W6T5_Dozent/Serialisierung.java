package W6T5_Dozent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * Serialisieren und Deserialisieren
 * Das Serialisieren ist eine sehr komfortable M�glichkeit, ein ganzes Geflecht von Objekten in eine
 * serielle Form einer Folge von Bytes zu bringen, die sich zum Speichern in einer sequentiellen Datei
 * oder zum Transfer �ber die Zwischenablage eignet. 
 * Auf diese Weise l�sst sich auch die Persistenz von Objekten implementieren, das ist das
 * 'Weiterleben' von Objekten �ber einen einzigen Programmlauf hinaus..
 * (Persistenz: das Dauerhafte bzw. das langfristige Fortbestehen einer Sache).
 * 
 * Objekte bilden im Arbeitsspeicher h�ufig ein �ber Referenzen verkn�pftes Geflecht 
 * ( z.B. Listen, B�ume oder Graphen). Serialisieren bedeutet, von einem solchen Objektgeflecht eine lineare,
 * sequentielle Darstellung herzustellen. Die serialisierte Form der Daten erlaubt, beim Wiedereinlesen
 * (Deserialisierung) ein �quivalentes Geflecht von Objekten zu erstellen, mit den gleichen Inhalten und
 * den entsprechenden Verkn�pfungen, aber nat�rlich an anderen Adressen des Arbeitsspeichers.
 *
 * Damit Objekte serialisiert werden k�nnen, m�ssen die Klassen die Schnittstelle 'Serializable' implementieren. 
 * Diese Schnittstelle enth�lt keine Methoden und ist nur eine Markierungsschnittstelle (engl. marker interface). 
 * Implementiert eine Klasse diese Schnittstelle nicht, folgt beim Serialisierungsversuch eine NotSerializableException. 
 * Der Serialisierer akzeptiert also nur die Klassen, die eine Instanz von 'Serializable' sind.
 * 
 * Nicht alle Objekte sind serialisierbar. Zu den nicht serialisierbaren Klassen geh�ren zum Beispiel Thread und Socket 
 * und viele weitere Klassen aus dem java.io-Paket. Das liegt daran, dass nicht klar ist, wie zum Beispiel eine Wiederherstellung
 * aussehen sollte. Wenn ein Thread etwa eine Datei zum Lesen ge�ffnet hat, wie soll der Zustand serialisiert werden, sodass er 
 * beim Deserialisieren auf einem anderen Rechner sofort wieder laufen und dort weitermachen kann, wo er mit dem Lesen aufgeh�rt hat?
 * Ob Objekte als Tr�ger sensibler Daten serialisierbar sein sollen, ist gut zu �berlegen. Bei der Serialisierung der Zust�nde
 * werden auch private Attribute serialisiert. Durch das Kapselungsprinzip kann man normalerweise nicht an diese Daten herankommen.
 * Aus dem Datenstrom lassen sich die internen Belegungen aber ablesen und auch manipulieren.
 */

public class Serialisierung
{

	public static void main(String[] args)
	{
		

		// Eine Kette von Textobjekten erzeugen
		TestObjekt obj = new TestObjekt("Anton", 1);
		obj.next = new TestObjekt("Berta", 2);
		obj.next.next = new TestObjekt("Caesar", 3);
		
		System.out.println("Erzeugte Objekte:");
		System.out.println(obj);
		System.out.println(obj.next);
		System.out.println(obj.next.next);
		System.out.println(obj.next.next.next);
		System.out.println();
		
		String Dateiname = "c:/temp/TestObjekt.ser";
		
		// Ausgabestrom / Ausgabedatei erzeugen
		
		try
		{
			ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(Dateiname));
			objOut.writeObject(obj);
			objOut.close();
			
		}
		// IOException wird von writeObject() ausgel�st.
		catch (IOException ioEx)
		{
			System.out.println("E/A Ausnahmebedingung bei der Serialisierung aufgetreten");
		}
		catch (Exception ex)
		{
			System.out.println("Ausnahmebedingung bei der Serialisierung aufgetreten");
		}
		
		
		// Objekte im Programm l�schen
		System.out.println("Objekte werden gel�scht...");
		obj = null;
		// Garbage Collection
		System.gc();
		
		// Eingabestrom erzeugen
		
		try
		{
			ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(Dateiname));
			obj = (TestObjekt)objIn.readObject();
			objIn.close();
			
		}
		// IOException wird von readObject() ausgel�st.
		catch (IOException ioEx)
		{
			System.out.println("E/A Ausnahmebedingung bei der Deserialisierung aufgetreten");
		}
		// ClassNotFoundException wird von readObject() ausgel�st.
		catch (ClassNotFoundException cnfEx)
		{
			System.out.println("Klasse-nicht-gefunden-Ausnahmebedingung bei der Deserialisierung aufgetreten");
		}
		catch (Exception ex)
		{
			System.out.println("Ausnahmebedingung bei der Deserialisierung aufgetreten");
		}
		
		
		// Man beachte: Nicht nur obj wird eingelesen, sondern auch alle Objekte,
	    // auf die obj seinerzeit direkt oder indirekt verwiesen hatte.
		System.out.println();
		System.out.println("Eingelesene Objekte:");
		System.out.println(obj);
		System.out.println(obj.next);
		System.out.println(obj.next.next);
		System.out.println();
		
		
	}

}
