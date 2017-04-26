package Uberblick;
//Projektarbeit: 		Methodenbeschreibung, Klasseninitwerte �berpr�fen, Funktionsparameter �berpr�fen ob notwendig (setter/getter), 
//						ToString(), equals(), hashCode() �berschreiben, methoden der �berklasse �berschreiben um this Referenz der geerbten Klasse zur�ck
//						zu geben (W3T5_Dozent DatumZeit), new DecimalFormat("00").format(), GUI f�r User Logik, Strg+T = Klassenhierachie, Objekte bilden
//						versuchen (JButton -> Lottotippfeld), Startbedingung abfragen (ist �berhaupt was zu tun -> Feld leer), bei Frage ob es passiert
//						ist -> Funktion mit boolean R�ckgabewert, Benutzerf�hrung -> deaktivieren von Button, Inlinedokumentation mit Kommentaren,
//						Besonderheiten und Komplexit�t, Userinterface �ber Tastatur bedienen, Icons f�r Felder, Befehle �ber Men� mussen auch gehen, zb
//						Abbrechen

//						Nebenl�ufiger Prozess: einmal Objekt instanzieren im Hauptprogramm um darauf Zuzugreifen (existiert immer weiter), nebeml�ufiger 
//						Prozess startet das Anzeigen des Frames, wo erstmal nichts weiter passiert -> damit der Hauptprozess weiter geht muss es 
//						nebenl�ufig sein dh HP generiert �nderungen an Objekt, dass das Objekt(zb Frame, Dialog) nebenl�ufig dann anzeigt
//						Implementierung: 1. im HP als innere Klasse, die Runnable ist und in run() showFrame startet 2. die Objekt(klasse) implementiert
//						Runnable und showFrame in run() einf�gen -> Beispiel: W6T3_MK Fortschrittsanzeige/ Modaler Dialog(true)-> Dialog immer im Vorderg.

//						Interessante Sachen: Lambda Ausdr�cke, Pattern (zb Singleton), Frameworks f�r Webentwicklung (Spring)

//						erweiterte library: swingx-all-1.6.4 -> f�r zB JXDatePicker, JXMouth
//						
//						Plausibilit�tspr�fung f�r Textfelder: W4T3 CheckBox.., W4T4 Farbmischer, W6T5 PLZForm

//						weiter Lesen: http://java-tutorial.org/, http://www.java-forum.org/, Games: http://www.java-forum.org/thema/tutorials.6529/


//W1T1_Dozent:
//	Datentypen:  		Datentypen, byte umwandeln in Zahl
//	Hallo: 				Hallo Start

//W1T2_Dozent:
//	Datentypen:			Escape Zeichen (Maskierung)
//	Kontrollstrukturen:	If + Switch Anweisungen
//	Operatoren1:		+ - * / , Pr�/Postfix, Double ein Ergebnis mit 2 Integer zuweisen
//	Operatoren2:		Vergleichsoperatoren, Bitweises Verschieben (Komplement, Verschiebeung links/rechts), Hex, Okt

//W1T3_Dozent:
//	AusgabeFormati...	:printf, DateFormat/NumberFormat/DecimalFormat Klasse
//	Schleifen:			for-Schleife, while/do-while-Schleife 

//W1T4_Dozent:
//	Schleife:			Vergleich von Float Werten bei Schleifen
//	Modularisierung:	Globale Variablen, �berladene Methoden, variable Methodenparameterliste, ShortCircuitEvaluation
//	Rekursion:			Rekursion, Schleife zu Rekursion (Fakult�t)
	
//W1T5_Dozent:
//	Methoden			? : Operator, Methodenaufruf mit verschiedenen Parameter (�berladen)
//	Primzahlen			Try/Catch, Globale Variablen, NumberFormat Methode (int->String), Str eingelesen --> zu int parsen
//	Zeichenketten		Index von Char in String, Strings vergleichen, Wrapper int->str->int



//W2T1_Dozent:
//	StringMethoden		Aufgaben--> whitespace entfernen, substring, indexof
//	StringUndStringB...	Zeitmessung Str/strBuilder
// 	Felder				Felder def,ini ; arrays.methoden (fill), Arrays.clone / copyOf /copyOfRange , system.arraycopy

//W2T2_Dozent:
//	Felder(erweitert)	arrays.(deep)toString, 2D/3D Arrays, erweiterte for each Schleife
// 	DynamischeDaten...	LinkedList, generics, ele. Datentypen als Objekte, ArrayList, <Object>, getClass(), addAll
//						Queue (poll(),offer()), Stack, Assoziativspeicher HashMap, Datei Lesen
//	KommandozeilenA...	Main Funktion mit Parameter starten

//W2T3_Dozent:
//	DynamischeDaten...	weiter ab "Datei Lesen", Set von Sch�ssel, Datei schreiben, Properties lesen/schreiben (prop/xml), Treeset

//W2T4_Dozent:
//	DynamischeDaten...	�bungsaufgaben: TreeMaps, ArrayList, PLZs ausgeben und bearbeiten
//	Klassen				MeineKlasse aufrufen
//	MeineKlasse			Aufbau der Klasse --> static f�r sachen, die sich nicht ver�ndern bei der Instanzierung
//	Tag					static Variablen, DEFAULT Konstanten, Konstruktoren, Klassenbeschreibung JAVADOC, DEFAULT Werte mit statischen F ver�ndern

//W2T5_Dozent:
//	Klassen				Aufrufen der Klassen, elementare Datentypen als Referenz �bergeben -> eigene WrapperKlasse, 
//						eigene Funktionen mit eignen Klassen (parsetry(s,MyInteger)
//	Monat				Klassen def. , static Funktion/Variablen, final, Konstruktoren, this Referenz
//	Punkt				Punkt Klasse definiert  / public Variablen
//	Rechteck			Rechteck Klasse aus Punkt Objekten / public Variablen
//	Farbe				Farbe mit public  R G B
//	Gebilde				Klasse beinhaltet Klassen Punkt, Rechteck, Farbe / private Variablen --> getter/setter
//	Haus				Innere Klassen: zugriff auf �u�ere Variablen innere Klassen d�rfen auf �u�ere Klassen zugreifen, 
//						auf private Klasse von nur ERSTEN �bergeordnete Klasse zugreifen
//	MyInteger			siehe Klassen



//W3T1_Dozent:
//	Datum				Klasse Datum erstellen, setter/ getter, private/public, static, 
//	DatumMain			Aufruf Klasse Datum
//	Klassen				Aufruf der Klassen (swap Bsp an verschiedenen Datentypen)
//	GenClass			Generischer Datentyp (Klasse) erstellen
//	GCFinalize			Garbage Collector, Finalize �berschreiben
//W3T1_Dozent.Vererbung (Package in Package)
//	Vererbung			Aufruf der Klassen
// 	K1, K2				Elternklassen
//	V1					@Override Notation -> Methoden erweitern oder neu implimentieren
//	V2_1, V2_2			Vererbung (mit Strg T Hierachie ansehen)

//W3T2_Dozent:
//	Vererbung			Aufruf der Klassen, Liste mit generischer Typ: Tier (Interface: List, Typklasse: LinkedList), instanceof
//	Tier				
//	Wolf				Abgeleitet von Tier	(neu fressen, Geschlecht)
//	Polarwolf			Abgeleitet von Wolf (neu final Farbe)
//	WeisserSchaeferh...	Abgeleitet von Polarwolf (neu fressen)
// 	Fisch				Abgeleitet von Tier (neu fressen)
//	Goldfisch			Abgeleitet von Fisch (neu fressen)
//	AnderesTier			Abgeleitet von Tier
//W3T2_Dozent.AbstrakteKlassen
//	AbstrakteKlassen	Aufruf von Klassen
//	Mitarbeiter			Abstrakte Klassen, Abstrakte Methoden
//	Arbeiter			Abgeleite von Mitarbeiter, Konstruktor der Oberklasse

//W3T3_Dozent:
//	Datum				Basisklasse f�r DatumZeit (�bung)
//	DatumMain			Aufruf Klasse Datum
//	DatumZeit			�bungsaufgabe BspL�sung --> Vererbung von Datum, Konstruktoren, Zeit Logik
//W3T3_Dozent.AbstrakteKlassen
//	AbstrakteKlassen	Aufruf von Klassen, Objekt deklariert als abstrakte Klasse mit abgeleiteten Klassen instanziert, 
//						Arraylist mit Typ Mitarbeiter, abstrakte Klasse kann nicht instanziert werden --> abgeleitet Klassen werden instanziert
//	Mitarbeiter			Abstrakte Klassen, Abstrakte Methoden
//	Arbeiter			Abgeleite von Mitarbeiter, Konstruktor der Oberklasse
//	Angesteller			Abgeleite von Mitarbeiter, Konstruktor der Oberklasse + gehalt + vorgesetzter (�berschrieben/neu)
//	Manager				Abgeleite von Mitarbeiter, Konstruktor der Oberklasse + gehalt + vorgesetzter (�berschrieben/neu)
//W3T3_Dozent.Schnittstellen
//	Schnittstellen		Schnittstellen allg, Objekt mit Abstraktklasse, Interface, reduzierende Konvertierung auf Methode Interface/Abstrakte Klasse
//						Interface Comparable<Mitarbeiter> (Mitarbeiter= Klasse wo interface implementiert wurde), hashCode
//	Iprintable			Interface (nur final, static, public Konstanten, nur public abstract methoden)
//	IPrintSetup			Interface mit Methoden (Interfaces k�nnen Interfaces "extends"en)
//	AbstractMitarbeiter	Abstrakte Klasse (wenn Interfaces in abstrakten Klassen implementiert sind -> wie zus. abstrakte Methoden) 
//	Mitarbeiter			abgeleitet von AbstractMitarbeiter mit Interfaces IPrintable, IPrintSetup,Comparable implementiert, equals() �berschreiben

//W3T4_Dozent:
//	Datum				NEU: this Objekte zur�ckliefern statt elementare Datentypen, -> ErrorFlag einbauen -> Ziel: verkettete Methodenaufrufe
//						Ostersonntagberechnung -> Feiertagsberechnung
//	DatumMain			NEU: verketteter Methodenaufruf
//	DatumZeit			nichts Neues
//W3T4_Dozent.Schnittstellen
//	Schnittstellen		NEU: Auf-/Absteigende sortierung mit enums
//	Mitarbeiter			NEU: enums integriert f�r Auf-/Absteigende sortierung / Methoden zum Sortieren mit enums / default Methode
//	Iprintable			NEU: ab Java 8 public STATIC Methoden -> Logik Implementiert / ohne Implementierungszwang, default Methode
//W3T4_Dozent.Threads
//	ThreadMain			Threads / Status von Threads / Aufruf von anderen Threads -> start(), run(), join() / in ThreadKlasse: sleep()
//	ThreadDatum			Multitasking/Nebenl�ufiger Prozess -> Interface Runnable / schreibt aktuelles Datum
//	ThreadZaehler		gleiche wie ThreadDatum -> z�hlt Durchl�ufe
//	ThreadJoin			Thread mit verschiedenen Problemen (Verz�gerung)

//W3T5_Dozent:
//	Datum				�bung: implementierung compareTo(), equal(), hashmap()
//	DatumMain			�bungsaufgaben testen
//	DatumZeit			�bung: implementierung compareTo(), equal(), hashmap() // �berschreiben von addieren Methoden um DatumZeit Objekt zur�ck gibt
//	Grafik1				JFrame: kompletter Aufbau mit Labels, Textfeldern..., ActionListener (Klasse in Klasse)
//	Grafik1Main			Aufruf von Grafik1
//W3T5_Dozent.Threads
//	ThreadMain			Neu: Aufruf SyncMonitor, T1
//	SyncMonitor			Synchronized 
//	MonitorThread		Klasse f�r synchronized threads
//	T1					Klasse direkt von Klasse "Thread" abgeleitet -> kein Interface runnable n�tig -> run() musst �berschrieben werden (nicht vergessen!)

//W4T1_Dozent:
//	CheckBoxRadioButton	Direkt von JFrame abgeleitet, main direkt in Klasse eingebettet, Fensterpositionen, Buttongroup, JPanel, BackgroundColor

//W4T2_Dozent:
//	CheckBoxRadioButton	Erweiterung: best. Farbkombis verbieten, Checkboxen, Schriftgr��e �ndern, Component iterieren, Font bitweises ODER, KeyListener
//						Fehlernachricht ausgeben, FocusListener (Gain: markiert Zahl im Textfeld), Focus auf n�chstes Element, Tooltip

//W4T3_Dozent:
//	CheckBoxRadioButton	Erweiterung: nur 2 Char eingeben, nur Zahlen, Steuerzeichen ignorieren (getKeyCode in KeyPressed / getKeyChar in KeyTyped)
//	Farbmischer			Slider, Skala, Anzeige der Zahlen in der Skala -> HashMap, ChangeListener (ohne eigene Klasse)
//	DrawText			Graphics Objekt, Methode paint() aus Window �berschreiben um alles neu zu zeichnen, Text gr��er und in der Mitte
//	DrawRectangle		Rechteck zeichnen, Farbe angeben (Rahmen, F�llfarbe), abgerundete Ecken, Graphics2D, Stroke(Strichst�rke), Striche, Kreis
//	DrawColorBox		MouseListener (doppelklick), viele Quadrate mit zuf�lligen Farben, Titelzeilengr��en, Rahmenbreite vom Frame

//W4T4_Dozent:
//	Farbmischer			Erweiterung: Slider und Eingabewerte mit einander verbunden, Labelfarbe umschalten entsprechend Hintergrund (Componenten 
//						durch interieren), Zahlenwert schon bei KeyTyped �berpr�fen, Wert in Zwischenablage kopieren (Toolkit verwendet), Transparenz 
//						setzen (setUndecorated, getRootPane -> der JFrame wird ver�ndert)
//	FrameDialog			JTextArea, JScrollPane

//W4T5_Dozent:
//	JackpotKnacker		Neue Klasse Lottofeld aus JButton geerbt, Button Formatierung: FocusPainted, setMargin, Rahmen selber zeichnen, 2D Feld mit einer
//						Schleife (% /), Titelh�he, Rahmenbreite, pack() = gibt Ausdehnung aller Objekte auf der ContentPane, dynamisch JFrame anpassen, 
//						Methode paint: repaint l�st es aus, repaint auf eine Komponente (hier Button), Icons setzen von Bilder aus Projekt, 
//						Verzeichnis "\" bei Java mit "/" schreiben (�bung von W4T4)
//	FrameDialog			Erweiterung: Instanz von FrameDialogModal + anzeigen, Anzeige in der Mitte bezogen auf Componente FrameDialog, WindowListener, 
//						Schlie�en (closing) Ereignis, JA/Nein Dialog beim Schlie�en, Dateien lesen (Dateifilter fest), Focus auf Fenster, Default Verzeichnis
//						
//	FrameDialogModal	Frame Dialog erstellen, this.dispose, Modal machen

//W5T1_Dozent:
//	FrameDialog			Erweiterung: mehrere Methode zum buffered Lesen von Dateien
//	FrameDialogModal	nichts Neues
//	FrameDialogSuc...	Fokusreihenfolge definieren in einen Vector, neue Klasse die von FocusTraversalPolicy (dort steckt Logik zum OS drin) ableiten und
//						entsprechend anpassen, das Elemente im Vector durchgehen (abh von visible,focusable,enable)-> isFocusable / wenn Element nicht 
//						enthalten dann components.indexOf(c) = -1 +1 = 0 -> start des Vectors
//	Util/MyFocusTr...	steuert den Focus, vorw�rts, r�ckw�rts, isFocusable

//W5T2_Dozent:
//	FrameDialog			Neu: Drucken Btn (mit ImageIcon) -> Bilder Hintergrund entfernen (pixlr: http://apps.pixlr.com/editor/), Drucken wie auf Bildschirm
//						Utilities.getRowEnd/Start -> ermittelt Zeilenstart/End von TextArea mit simulation einer TextArea Logik, eigene print Methode 
//	FrameDialogModal	nichts Neues
//	FrameDialogSuc...	Suchen von markierten Text, Ersetzen von Text, Alles Ersetzen reg.Ausdruck (?i) f�r unabh�ngig Gro� und Kleinschreibung
//	Util/MyFocusTr...	Neu: isEditable von JTextComponent, Problem: keine Componente erf�llt isFocusable Kriterien -> Container zur�ckgeben

//W5T3_Dozent:
//	FrameDialog			Neu: dispose ruft windowClosed auf, bei windowClosed wird Owner abgefragt, 1. Variante: neue Instanz in FrameDialog steuern 
//						2. Variante: der Button wird deaktiviert, �nderungen detektieren: DocumentListener -> textArea.getDocument().addDocumentListener,
//						hashCode berechnen lassen (von String der textArea) und vergleichen
//						Datei speichern (FileWriter) / speichern unter... (Datei Erweiterung hinzuf�gen, file.exists file.isFile)
//						Drucken: PrinterJob, printDialog, PageFormat, FontMetrics -> berechnen ob max Zeilenl�nge in Druckbereich passt
//	FrameDialogModal	nichts Neues
//	FrameDialogSuc...	nichts Neues
//	ListBoxComboBox		(JList, DefaultListModel, JScrollPane) -> ListBox, ListSelectionListener, setSelectionMode, e.getValueIsAdjusting, 
//						(JComboBox, DefaultComboBoxModel) -> ComboBox, zu neuen Datenmodell wechseln, ItemListener, GraphicEnviroment -> alle Schriftarten
//						ListBox: Element selektieren im Programm, ensureIndexIsVisible, Multiple_Interval_Selection
//						ActionListener ist einfachste Listener (viele Objekte haben das) -> bietet bestimmt einfache Ereignisse
//	Util/ListItem		Datentyp f�r Eintr�ge in ListBox (besteht aus Schl�ssel f�r DB und AnzeigeString)

//W5T4_Dozent:
//	FrameDialog			Neu: Singleton aufrufen
//	FrameDialogModal	nichts Neues
//	FrameDialogSuc...	Neu: Singleton implementiert
//	ListBoxComboBox		Neu: 3 Comboboxen (Schriftart, Schriftstil, Schriftgr��e), setVerticalScrollBarPolicy (TextArea), deriveFont -> Element des Font 
//						ver�ndern, ComboBox editierbar (KeyListener f�r �berpr�fung) -> ItemListener: getSelectedIndex = -1 (wenn selbst geschrieben wurde)
//						KeyListener f�r Combobox: cbo.setEditor().getEditorComponent().addKeyListener, .addFocusListener (wie Farbmischer), um an Text zu
//						kommen: cbo.setEditor().getItem, Dezimalzeichen des Systems ermitteln, eingegebene Zeichen autom. umwandeln (.->,)
//	TabbedPane			BorderLayout, JMenubar, JMenu,JMenuItem -> ActionListener, StatusBar, setSize oder preferredSize (feste Gr��e), JTabbedPane, Mehrere 
//						Dateien ausw�hlen beim �ffnen, JMenuItem: RadioButton und Checkbox sind abgeleitet von JMenuItem)
//	Util/StatusBar		Ziel: Universelle StatusBar f�r viele Projekte, anderes Layout: BorderLayout,EtchedBorder, EmptyBorder (Text im Label ausrichten)
//	Util/WinUtil		Erstellt JMenu mit Referenz zur�ck (Methodenbeschreibung JavaDoc), Enums statt Konstanten,  Untermen�s 

//W5T5_Dozent:
//	TabbedPane			Neu: AcceleratorKeys, addTab, Try-Catch mit Ressourcen -> new File, ChangeListener f�r tabAuswahl, ScrollTabLayout, getComponentAt()
//						= JScrollPane (TextArea) bzw Tab welcher ausgew�hlt wurde, Tableiste oben/u/l/r, Referenz auf Komponenten(zb TextArea) von 
//						Scrollpane bekommen = jsp.getViewport() -> getView(), Zeilenumbruch: 2 Ereignisse: Haken setzen/tab wechseln
//W5T5_Dozent.RMI		
//	RMIServer			Allgemein RMI, Constructor mit throws, ShutDown als nebenl�ufiger Prozess -> Countdown: TimeUnit.Sleep,SystemTray, PopupMenu
//	IRemoteMethods		Interface mit n�tigen Methoden und Datentypen
//	ServerTime			user def. Datentyp: gibt Servertime zur�ck -> serializable (simpleDateFormat ist auch serializable)
// 	RMIClient			Client f�r Server mit Abfrage von Lebenszeichen von Server
//W5T5_Dozent.Erwei...		
//	ComboBoxCellRe...	CellRenderer
//	ExtendedListItem	Datentyp ListItem erweitert f�r CellRender
//	ListBoxComboBox		Schriftarten in ComboBox werden direkt angezeigt

//W6T1_Dozent:
//	Datenbankzugriffe	Fenster mit Menu, Statusleiste als Anzeige f�r DB Status, Postleitzahlen importieren, addChoosableFileFilter, Warten auf neu-
//						zeichen paintImmediately(statusBar.getVisibleRect())
//	DBConnection		Da nur eine DB -> static, Datenbank �ffnen, schlie�en, welche Datenbank ge�ffnet (getCatalog)

//W6T2_Dozent:
//	Datenbankzugriffe	Neu: readfile ist nebenl�ufiger Prozess (Abarbeitung warte nicht auf Vollst�ndigkeit) -> readfile f�r Statusbar im Nebenl�ufigen 
//						Prozess und readfile f�r wirklichen Datenbankschreibaktionen, Datenbank l�schen, ' maskieren durch ''SQL Aggregatfunktionen 
//						(MAX, MIN, COUNT,...) (SELECT count(Spalte) from plz where prykey >10), UNIQUE Index erstellen -> keine Duplikate (funktioniert
//						automatisch) -> Integrit�t macht DB, PrimaryKey automatisch erh�hen beim neuen Datenschreiben
//	DBConnection		Neu: executeNonQuery(R�ckgabewert = Anzahl �nderungen), executeScalar -> SELECT/ein Wert Resultset, 
//	Globals				globale Hilfsmethoden: INSERT Methode, Last Primarykey, Duplikatpr�fung mit Count, unbestimmter Zahlenwert (int,long) -> (Number)

//W6T3_Dozent:
//	Datenbankzugriffe	Neu: Fortschrittsbalken, in StatusBar Borderlayout LINE_END, gr�n, setStringPainted(true)-> Prozentsatzanzeige, Fortschritt anhand 
//						Dateigr��e und Bytes von eingelesenen Zeichen, prepareStatement statt StdSQL Statements, MenuBar deaktivieren (mit Schleife durch
//						Elemente), INSERT INTO erweitert: nach VALUES mehrere Datens�tze durch "," trennen
//	DBConnection		Neu: prepareStatement, Transaktionsscheifen (nur mit InnoDB) -> AutoComit = Transaktionsschleife ge�ffnet, wenn stmt.close -> 
//						resultset weg!
//	Globals				Neu: vorbereite SQL Statements -> schneller beim Schreiben in DB
//	PLZTable			JDialog: mit StatusBar, Menu und JTable, in eigener Klasse abgeleitet von AbstractTableModel: Datenmodell f�r JTable, Metadaten
//						aus rSet, ResultSet + Metadaten haben 1 basierende Indices!!!

//W6T4_Dozent:
//	Datenbankzugriffe	Neu: ProgressWindow, Icon f�r Fenster, 
//	DBConnection		nichts neues			
//	Globals				nichts neues
//	PLZTable			Icons integriert, nebenl�ufiger Prozess zum Einlesen der Datens�tze, SQL Aliasnamen, Tabellenformatierung, ListSelectionListener:
//						e.getValueIsAdjusting filtert Ereignis beim Zeilen�nderung in Tabelle -> welcher Datensatz in Tabelle in Statusbar anzeigen
//						Tasten POS1 und ENDE �berschreiben, Spalten der Tabelle unsichtbar machen, MouseListener, Doppelklick mit Mouse = e.getClickCount()
//						== 2 
//	ProgressWindowDemo	Demo f�r Fortschrittsanzeige (Konfigfenster mit GridLayout), mit vielen Listener Verschiebt Fenster mit Mouse
//	Util/ProgressWindow	Konfigurierbare Fortschrittsanzeige (runnable), bei Abbrechen Kopieren stoppen bis zur Entscheidung
//	Util/StatusBar		Neu: Icon kann hinzuf�gt werden

//W6T5_Dozent:
//	Datenbankzugriffe	nichts neues
//	DBConnection		nichts neues 	
//	Globals				nichts neues 
//	PLZTable			Neu: Aktualisieren rSet/DB -> showData als nicht nebenl�ufer Prozess, um an die Tabelle (welche Datenmodell bekommen hat) zu 
//						kommen (und auch indirekt ans Datenmodell) -> Methode setValueAt() von Datenmodell �berschreiben (fireTableCellUpdated
//						aktualisiert Zelle sofort in Tabelle), bei nicht �berschriebenen Methoden von PLZTableModel muss 
//						((PLZTableModel)Tabelle.getModel). ... -> auf eingef�gten Eintrag autom. bl�ttern, l�schen mit delete
//	PLZForm				Abfrage aus Datenbank direkt -> in rSet (rSet.getString("PLZ [oder alias]"), Plausibilit�tspr�fung, Focusmanager, �nderungspr�fungen
//						windowClosing, JOptionPane Abfrage (auch X Schlie�en Button), vieleeee �berpr�fung der werte, TIMESTAMP �berpr�fen
//	Serialisierung		Serialisieren = das ganze Objektgeflecht wird gespeichert (nicht nur das "first level" Objekt) -> preparedStatements zur DB
//	TestObjekt			Verkettetes Objekt


//W7T1_Dozent:
//	Datenbankzugriffe	Neu: Menueintrag f�r Look and Feel, �ber ActionCommand
//	DBConnection		nichts neues 	
//	Globals				Neu: Look and Feel speichern/dynamische Auswahl/Einstellung speichern in prop Datei, Command
//	PLZTable			 
//	PLZForm				



public class Ueberblick{public static void main(String[] args){}}
