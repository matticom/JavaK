package W3T5_Dozent.Threads;

/* Moderne Betriebssysteme geben dem Benutzer die Illusion, dass verschiedene Programme gleichzeitig ausgef�hrt werden � 
 * die Betriebssysteme nennen sich multitaskingf�hig. Was wir dann wahrnehmen, ist eine Quasiparallelit�t, die im Deutschen 
 * auch �Nebenl�ufigkeit� genannt wird. 
 * Diese Nebenl�ufigkeit der Programme wird durch das Betriebssystem gew�hrleistet, welches auf Einprozessormaschinen die 
 * Prozesse alle paar Millisekunden umschaltet. Daher ist das Programm nicht wirklich parallel, sondern das Betriebssystem 
 * gaukelt uns dies durch verzahnte Bearbeitung der Prozesse vor. 
 * Erst wenn mehrere Prozessoren oder mehrere Prozessor-Kerne am Werke sind werden die Programmteile tats�chlich parallel abgearbeitet.
 * 
 * Der Teil des Betriebssystems, der die Umschaltung �bernimmt, hei�t Scheduler. Die dem Betriebssystem bekannten aktiven Programme bestehen 
 * aus Prozessen. Ein Prozess setzt sich aus dem Programmcode und den Daten zusammen und besitzt einen eigenen Adressraum. 
 * Des Weiteren geh�ren Ressourcen wie ge�ffnete Dateien oder belegte Schnittstellen dazu. Die virtuelle Speicherverwaltung des 
 * Betriebssystems trennt die Addressr�ume der einzelnen Prozesse. Dadurch ist es nicht m�glich, dass ein Prozess den Speicherraum eines 
 * anderen Prozesses korrumpiert; er sieht den anderen Speicherbereich nicht. Damit Prozesse untereinander Daten austauschen k�nnen, 
 * wird ein besonderer Speicherbereich als Shared-Memory markiert.
 * 
 * Die Programmierung von Threads ist in Java einfach m�glich, und die quasi parallel ablaufenden Aktivit�ten ergeben f�r den Benutzer den 
 * Eindruck von Gleichzeitigkeit. In Java ist auch multi-threaded Software m�glich, wenn das Betriebssystem des Rechners keine Threads direkt 
 * verwendet. In diesem Fall simuliert die virtuelle Maschine die Parallelit�t indem sie die Synchronisation und die verzahnte Ausf�hrung regelt. 
 * Unterst�tzt das Betriebssystem Threads direkt, bildet die JVM die Thread-Verwaltung in der Regel auf das Betriebssystem ab.
 */

/* Die Zust�nde eines Threads.
 * 
 * 1. Nicht erzeugt: Der Lebenslauf eines Threads beginnt mit 'new', doch damit befindet er sich
 * 	  noch nicht im Zustand 'ausf�hrend'.
 * 2. Laufend  (vom Scheduler ber�cksichtigt) und nicht laufend (vom Scheduler nicht
 * 	  ber�cksichtigt):  Durch start() gelangt der Thread in den Zustand 'ausf�hrbar' bzw.
 * 	  'laufend'. Der Zustand kann sich �ndern, wenn ein anderer Thread ausgef�hrt wird
 * 	  und dann dem aktuellen Thread dem Prozessor entzieht. Der vormals laufende
 * 	  Thread kommt in den Zustand 'nicht laufend' bis der Scheduler ihm wieder
 * 	  Rechenzeit zuordnet.
 * 3. Wartend: Dieser Zustand wird mittels spezieller Synchronisationstechniken oder
 * 	  Ein-/und Ausgabefunktionen erreicht.
 * 4. Beendet: Nachdem die Aktivit�t des Threads beendet wurde, kann er nicht mehr
 * 	  aktiviert werden und ist tot.
 * 
 * Zudem l�sst sich die Methode isAlive() verwenden um abzufragen, ob der Thread
 * gestartet wurde, aber noch nicht tot ist.
 */

public class ThreadMain
{

	public static void main(String[] args)
	{
		
		//ThreadStart();
		//ThreadRun();
		//ThreadSleep();
		//ThreadJoin();
		ThreadSync();
		
		
		
		System.out.println("Ende von ThreadMain");

	}

	
	private static void ThreadStart()
	{
		/* Dem Konstruktor der Klasse Thread wird eine Referenz auf das
		 * auszuf�hrende Objekt �bergeben.
		 * Danach wird die Methode start() aufgerufen.
		 * Nachdem start() f�r den Thread eine Ablaufumgebung erstellt hat,
		 * ruft sie intern selbstst�ndig die Methode run() genau einmal auf.
		 * L�uft der Thread bereits, so l�st ein zweiter Aufruf der start()-
		 * Methode  eine Ausnahmebedingung aus (IllegalThreadStateException).  
		 */
		

		Thread t1 = new Thread(new ThreadDatum());
		t1.start();
		
		Thread t2 = new Thread(new ThreadZaehler());
		t2.start();
		
	}
	
	private static void ThreadRun()
	{
	
		/* Ein Fehler, der schnell passiert ist: Anstelle von start() die
		 * Methode run() aufzurufen.
		 * Die Ausf�hrung wurde dadurch ebenfalls gestartet, Der Unterschied
		 * ist jedoch, dass die Objekt-Methode nicht parallel zum �brigen
		 * Programm abgearbeitet wird sondern sequenziell.
		 * Der Fehler f�llt nicht sofort auf, denn die Ausf�hrung findet
		 * ja statt - nur nicht nebenl�ufig.
		 */
		

		Thread t1 = new Thread(new ThreadDatum());
		t1.run();
		
		Thread t2 = new Thread(new ThreadZaehler());
		t2.run();
		
		
	}
	
	private static void ThreadSleep()
	{
		
		/* Manchmal ist es notwendig, einen Thread f�r eine bestimmte Zeit anzuhalten.
		 * Dazu lassen sich die Methoden zweier Klassen nutzen:
		 * 
		 * 1. Die �berladene statische Methode Thread.sleep(),
		 * 2. Die Objektmethode sleep() auf einem TimeUnit-Objekt.
		 *    Der Vorteil dieser Methode ist, dass hier die Zeiteinheiten
		 *    besser sichtbar sind.
		 */
		
		/* Neben der Methode sleep() gibt es eine weitere Methode, um kooperative Threads zu
		 * programmieren: die Methode yield().
		 * Sie funktioniert etwas anders als sleep(), da hier nicht nach Ablauf einer Zeit zum
		 * Thread zur�ckgekehrt wird, sondern yield() den Thread wieder, entsprechend seiner
		 * Priorit�t, in die Thread-Warteschlange des Systems einordnet. Der Thread hat einfach
		 * eine Runde ausgesetzt und wartet bis er das n�chste Mal dran ist.
		 * 
		 */
		
		
		Thread t1 = new Thread(new ThreadDatum(1));
		t1.start();
		
		Thread t2 = new Thread(new ThreadZaehler(2));
		t2.start();
		
	}
	
	private static void ThreadJoin()
	{
		
		/* Warten auf den Langsamsten
		 * Gro�e Probleme lassen sich in mehrere Teile zerlegen und jedes Teilproblem kann
		 * dann von einem Thread gel�st werden. Das ist insbesondere bei Mehrprozessorsystemen
		 * eine lohnenswerte Investition.
		 * Zum Schluss muss nur noch darauf gewartet werden, bis alle Threads zu Ende gekommen
		 * sind, und das Ergebnis einzusammeln. Dazu eignet sich join() gut.
		 * 
		 */
		
		Thread a = new Thread(new ThreadJoin("Thread A", 1));
		Thread b = new Thread(new ThreadJoin("Thread B", 2));
		Thread c = new Thread(new ThreadJoin("Thread C", 3));
		Thread d = new Thread(new ThreadJoin("Thread D", 1));
		
		a.start();
		b.start();
		c.start();
		d.start();
		
		System.out.println("Thread A ist alive: " + a.isAlive());
		System.out.println("Thread B ist alive: " + b.isAlive());
		System.out.println("Thread C ist alive: " + c.isAlive());
		System.out.println("Thread D ist alive: " + d.isAlive());
		
		
		try
		{
			a.join();
			b.join();
			c.join();
			d.join();
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		System.out.println("Thread A ist alive: " + a.isAlive());
		System.out.println("Thread B ist alive: " + b.isAlive());
		System.out.println("Thread C ist alive: " + c.isAlive());
		System.out.println("Thread D ist alive: " + d.isAlive());
		
		
	}
	
	
	
	private static void ThreadSync()
	{
		
		/* Da Threads ihre eigenen Daten verwalten � sie haben alle eigene lokale Variablen und einen Stack �, 
		 * kommen sie sich gegenseitig nicht in die Quere. Auch wenn mehrere Threads gemeinsame Daten nur lesen, 
		 * ist das unbedenklich; Schreiboperationen sind jedoch kritisch. Wenn sich zehn Nutzer einen Drucker teilen, 
		 * der die Ausdrucke nicht als unteilbare Einheit b�ndelt, l�sst sich leicht ausmalen, wie das Ergebnis 
		 * aussieht. Seiten, Zeilen oder gar einzelne Zeichen aus verschiedenen Druckauftr�gen werden bunt 
		 * gemischt ausgedruckt.
		 *   
		 */
		
		
		// Um Klassen threadsicher zu machen, kann man Methoden der Klassen als 'synchronized' deklarieren.
		// Die Qualifizierung mit 'synchronized' bewirkt, dass zuerst eine begonnene synchronized Methode
		// zu Ende ausgef�hrt wird, bevor eine neue synchronized Methode in einem anderen Thread begonnen
		// werden kann.
		// Das bedeuted, synchronized Methoden k�nnen nicht konkurrierend zueinander ablaufen, sondern werden
		// immer sequentiell nacheinander ausgef�hrt.  
		
		// Enth�lt synchronisierte Methoden (z.B. Ausgabe(String))
		SyncMonitor Monitor = new SyncMonitor();
				 
		 // Erstellen der Threads 'MonitorThread' mit �bergabe des Monitors als Referenz
		 Thread t1 = new Thread(new MonitorThread("Thread 1", Monitor));
		 Thread t2 = new Thread(new MonitorThread("Thread 2", Monitor));
		 
		
		 t1.start();
		 t2.start();
		 
		
	}
	
	
}
