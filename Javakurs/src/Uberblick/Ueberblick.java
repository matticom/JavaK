package Uberblick;
//Projektarbeit: 		Methodenbeschreibung, Klasseninitwerte überprüfen, Funktionsparameter überprüfen ob notwendig (setter/getter), 
//						ToString(), equals(), hashCode() überschreiben, methoden der Überklasse überschreiben um this Referenz der geerbten Klasse zurück
//						zu geben (W3T5_Dozent DatumZeit), new DecimalFormat("00").format(), GUI für User Logik, Strg+T = Klassenhierachie, Objekte bilden
//						versuchen (JButton -> Lottotippfeld), Startbedingung abfragen (ist überhaupt was zu tun -> Feld leer), bei Frage ob es passiert
//						ist -> Funktion mit boolean Rückgabewert, Benutzerführung -> deaktivieren von Button, Inlinedokumentation mit Kommentaren,
//						Besonderheiten und Komplexität, Userinterface über Tastatur bedienen, Icons für Felder, Befehle über Menü mussen auch gehen, zb
//						Abbrechen

//						Nebenläufiger Prozess: einmal Objekt instanzieren im Hauptprogramm um darauf Zuzugreifen (existiert immer weiter), nebemläufiger 
//						Prozess startet das Anzeigen des Frames, wo erstmal nichts weiter passiert -> damit der Hauptprozess weiter geht muss es 
//						nebenläufig sein dh HP generiert änderungen an Objekt, dass das Objekt(zb Frame, Dialog) nebenläufig dann anzeigt
//						Implementierung: 1. im HP als innere Klasse, die Runnable ist und in run() showFrame startet 2. die Objekt(klasse) implementiert
//						Runnable und showFrame in run() einfügen -> Beispiel: W6T3_MK Fortschrittsanzeige/ Modaler Dialog(true)-> Dialog immer im Vorderg.

//						Interessante Sachen: Lambda Ausdrücke, Pattern (zb Singleton), Frameworks für Webentwicklung (Spring)

//						erweiterte library: swingx-all-1.6.4 -> für zB JXDatePicker, JXMouth
//						
//						Plausibilitätsprüfung für Textfelder: W4T3 CheckBox.., W4T4 Farbmischer, W6T5 PLZForm

//						weiter Lesen: http://java-tutorial.org/, http://www.java-forum.org/, Games: http://www.java-forum.org/thema/tutorials.6529/


//W1T1_Dozent:
//	Datentypen:  		Datentypen, byte umwandeln in Zahl
//	Hallo: 				Hallo Start

//W1T2_Dozent:
//	Datentypen:			Escape Zeichen (Maskierung)
//	Kontrollstrukturen:	If + Switch Anweisungen
//	Operatoren1:		+ - * / , Prä/Postfix, Double ein Ergebnis mit 2 Integer zuweisen
//	Operatoren2:		Vergleichsoperatoren, Bitweises Verschieben (Komplement, Verschiebeung links/rechts), Hex, Okt

//W1T3_Dozent:
//	AusgabeFormati...	:printf, DateFormat/NumberFormat/DecimalFormat Klasse
//	Schleifen:			for-Schleife, while/do-while-Schleife 

//W1T4_Dozent:
//	Schleife:			Vergleich von Float Werten bei Schleifen
//	Modularisierung:	Globale Variablen, Überladene Methoden, variable Methodenparameterliste, ShortCircuitEvaluation
//	Rekursion:			Rekursion, Schleife zu Rekursion (Fakultät)
	
//W1T5_Dozent:
//	Methoden			? : Operator, Methodenaufruf mit verschiedenen Parameter (überladen)
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
//	DynamischeDaten...	weiter ab "Datei Lesen", Set von Schüssel, Datei schreiben, Properties lesen/schreiben (prop/xml), Treeset

//W2T4_Dozent:
//	DynamischeDaten...	Übungsaufgaben: TreeMaps, ArrayList, PLZs ausgeben und bearbeiten
//	Klassen				MeineKlasse aufrufen
//	MeineKlasse			Aufbau der Klasse --> static für sachen, die sich nicht verändern bei der Instanzierung
//	Tag					static Variablen, DEFAULT Konstanten, Konstruktoren, Klassenbeschreibung JAVADOC, DEFAULT Werte mit statischen F verändern

//W2T5_Dozent:
//	Klassen				Aufrufen der Klassen, elementare Datentypen als Referenz übergeben -> eigene WrapperKlasse, 
//						eigene Funktionen mit eignen Klassen (parsetry(s,MyInteger)
//	Monat				Klassen def. , static Funktion/Variablen, final, Konstruktoren, this Referenz
//	Punkt				Punkt Klasse definiert  / public Variablen
//	Rechteck			Rechteck Klasse aus Punkt Objekten / public Variablen
//	Farbe				Farbe mit public  R G B
//	Gebilde				Klasse beinhaltet Klassen Punkt, Rechteck, Farbe / private Variablen --> getter/setter
//	Haus				Innere Klassen: zugriff auf äußere Variablen innere Klassen dürfen auf äußere Klassen zugreifen, 
//						auf private Klasse von nur ERSTEN übergeordnete Klasse zugreifen
//	MyInteger			siehe Klassen



//W3T1_Dozent:
//	Datum				Klasse Datum erstellen, setter/ getter, private/public, static, 
//	DatumMain			Aufruf Klasse Datum
//	Klassen				Aufruf der Klassen (swap Bsp an verschiedenen Datentypen)
//	GenClass			Generischer Datentyp (Klasse) erstellen
//	GCFinalize			Garbage Collector, Finalize überschreiben
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
//	Datum				Basisklasse für DatumZeit (Übung)
//	DatumMain			Aufruf Klasse Datum
//	DatumZeit			Übungsaufgabe BspLösung --> Vererbung von Datum, Konstruktoren, Zeit Logik
//W3T3_Dozent.AbstrakteKlassen
//	AbstrakteKlassen	Aufruf von Klassen, Objekt deklariert als abstrakte Klasse mit abgeleiteten Klassen instanziert, 
//						Arraylist mit Typ Mitarbeiter, abstrakte Klasse kann nicht instanziert werden --> abgeleitet Klassen werden instanziert
//	Mitarbeiter			Abstrakte Klassen, Abstrakte Methoden
//	Arbeiter			Abgeleite von Mitarbeiter, Konstruktor der Oberklasse
//	Angesteller			Abgeleite von Mitarbeiter, Konstruktor der Oberklasse + gehalt + vorgesetzter (Überschrieben/neu)
//	Manager				Abgeleite von Mitarbeiter, Konstruktor der Oberklasse + gehalt + vorgesetzter (Überschrieben/neu)
//W3T3_Dozent.Schnittstellen
//	Schnittstellen		Schnittstellen allg, Objekt mit Abstraktklasse, Interface, reduzierende Konvertierung auf Methode Interface/Abstrakte Klasse
//						Interface Comparable<Mitarbeiter> (Mitarbeiter= Klasse wo interface implementiert wurde), hashCode
//	Iprintable			Interface (nur final, static, public Konstanten, nur public abstract methoden)
//	IPrintSetup			Interface mit Methoden (Interfaces können Interfaces "extends"en)
//	AbstractMitarbeiter	Abstrakte Klasse (wenn Interfaces in abstrakten Klassen implementiert sind -> wie zus. abstrakte Methoden) 
//	Mitarbeiter			abgeleitet von AbstractMitarbeiter mit Interfaces IPrintable, IPrintSetup,Comparable implementiert, equals() überschreiben

//W3T4_Dozent:
//	Datum				NEU: this Objekte zurückliefern statt elementare Datentypen, -> ErrorFlag einbauen -> Ziel: verkettete Methodenaufrufe
//						Ostersonntagberechnung -> Feiertagsberechnung
//	DatumMain			NEU: verketteter Methodenaufruf
//	DatumZeit			nichts Neues
//W3T4_Dozent.Schnittstellen
//	Schnittstellen		NEU: Auf-/Absteigende sortierung mit enums
//	Mitarbeiter			NEU: enums integriert für Auf-/Absteigende sortierung / Methoden zum Sortieren mit enums / default Methode
//	Iprintable			NEU: ab Java 8 public STATIC Methoden -> Logik Implementiert / ohne Implementierungszwang, default Methode
//W3T4_Dozent.Threads
//	ThreadMain			Threads / Status von Threads / Aufruf von anderen Threads -> start(), run(), join() / in ThreadKlasse: sleep()
//	ThreadDatum			Multitasking/Nebenläufiger Prozess -> Interface Runnable / schreibt aktuelles Datum
//	ThreadZaehler		gleiche wie ThreadDatum -> zählt Durchläufe
//	ThreadJoin			Thread mit verschiedenen Problemen (Verzögerung)

//W3T5_Dozent:
//	Datum				Übung: implementierung compareTo(), equal(), hashmap()
//	DatumMain			Übungsaufgaben testen
//	DatumZeit			Übung: implementierung compareTo(), equal(), hashmap() // überschreiben von addieren Methoden um DatumZeit Objekt zurück gibt
//	Grafik1				JFrame: kompletter Aufbau mit Labels, Textfeldern..., ActionListener (Klasse in Klasse)
//	Grafik1Main			Aufruf von Grafik1
//W3T5_Dozent.Threads
//	ThreadMain			Neu: Aufruf SyncMonitor, T1
//	SyncMonitor			Synchronized 
//	MonitorThread		Klasse für synchronized threads
//	T1					Klasse direkt von Klasse "Thread" abgeleitet -> kein Interface runnable nötig -> run() musst überschrieben werden (nicht vergessen!)

//W4T1_Dozent:
//	CheckBoxRadioButton	Direkt von JFrame abgeleitet, main direkt in Klasse eingebettet, Fensterpositionen, Buttongroup, JPanel, BackgroundColor

//W4T2_Dozent:
//	CheckBoxRadioButton	Erweiterung: best. Farbkombis verbieten, Checkboxen, Schriftgröße ändern, Component iterieren, Font bitweises ODER, KeyListener
//						Fehlernachricht ausgeben, FocusListener (Gain: markiert Zahl im Textfeld), Focus auf nächstes Element, Tooltip

//W4T3_Dozent:
//	CheckBoxRadioButton	Erweiterung: nur 2 Char eingeben, nur Zahlen, Steuerzeichen ignorieren (getKeyCode in KeyPressed / getKeyChar in KeyTyped)
//	Farbmischer			Slider, Skala, Anzeige der Zahlen in der Skala -> HashMap, ChangeListener (ohne eigene Klasse)
//	DrawText			Graphics Objekt, Methode paint() aus Window überschreiben um alles neu zu zeichnen, Text größer und in der Mitte
//	DrawRectangle		Rechteck zeichnen, Farbe angeben (Rahmen, Füllfarbe), abgerundete Ecken, Graphics2D, Stroke(Strichstärke), Striche, Kreis
//	DrawColorBox		MouseListener (doppelklick), viele Quadrate mit zufälligen Farben, Titelzeilengrößen, Rahmenbreite vom Frame

//W4T4_Dozent:
//	Farbmischer			Erweiterung: Slider und Eingabewerte mit einander verbunden, Labelfarbe umschalten entsprechend Hintergrund (Componenten 
//						durch interieren), Zahlenwert schon bei KeyTyped überprüfen, Wert in Zwischenablage kopieren (Toolkit verwendet), Transparenz 
//						setzen (setUndecorated, getRootPane -> der JFrame wird verändert)
//	FrameDialog			JTextArea, JScrollPane

//W4T5_Dozent:
//	JackpotKnacker		Neue Klasse Lottofeld aus JButton geerbt, Button Formatierung: FocusPainted, setMargin, Rahmen selber zeichnen, 2D Feld mit einer
//						Schleife (% /), Titelhöhe, Rahmenbreite, pack() = gibt Ausdehnung aller Objekte auf der ContentPane, dynamisch JFrame anpassen, 
//						Methode paint: repaint löst es aus, repaint auf eine Komponente (hier Button), Icons setzen von Bilder aus Projekt, 
//						Verzeichnis "\" bei Java mit "/" schreiben (Übung von W4T4)
//	FrameDialog			Erweiterung: Instanz von FrameDialogModal + anzeigen, Anzeige in der Mitte bezogen auf Componente FrameDialog, WindowListener, 
//						Schließen (closing) Ereignis, JA/Nein Dialog beim Schließen, Dateien lesen (Dateifilter fest), Focus auf Fenster, Default Verzeichnis
//						
//	FrameDialogModal	Frame Dialog erstellen, this.dispose, Modal machen

//W5T1_Dozent:
//	FrameDialog			Erweiterung: mehrere Methode zum buffered Lesen von Dateien
//	FrameDialogModal	nichts Neues
//	FrameDialogSuc...	Fokusreihenfolge definieren in einen Vector, neue Klasse die von FocusTraversalPolicy (dort steckt Logik zum OS drin) ableiten und
//						entsprechend anpassen, das Elemente im Vector durchgehen (abh von visible,focusable,enable)-> isFocusable / wenn Element nicht 
//						enthalten dann components.indexOf(c) = -1 +1 = 0 -> start des Vectors
//	Util/MyFocusTr...	steuert den Focus, vorwärts, rückwärts, isFocusable

//W5T2_Dozent:
//	FrameDialog			Neu: Drucken Btn (mit ImageIcon) -> Bilder Hintergrund entfernen (pixlr: http://apps.pixlr.com/editor/), Drucken wie auf Bildschirm
//						Utilities.getRowEnd/Start -> ermittelt Zeilenstart/End von TextArea mit simulation einer TextArea Logik, eigene print Methode 
//	FrameDialogModal	nichts Neues
//	FrameDialogSuc...	Suchen von markierten Text, Ersetzen von Text, Alles Ersetzen reg.Ausdruck (?i) für unabhängig Groß und Kleinschreibung
//	Util/MyFocusTr...	Neu: isEditable von JTextComponent, Problem: keine Componente erfüllt isFocusable Kriterien -> Container zurückgeben

//W5T3_Dozent:
//	FrameDialog			Neu: dispose ruft windowClosed auf, bei windowClosed wird Owner abgefragt, 1. Variante: neue Instanz in FrameDialog steuern 
//						2. Variante: der Button wird deaktiviert, Änderungen detektieren: DocumentListener -> textArea.getDocument().addDocumentListener,
//						hashCode berechnen lassen (von String der textArea) und vergleichen
//						Datei speichern (FileWriter) / speichern unter... (Datei Erweiterung hinzufügen, file.exists file.isFile)
//						Drucken: PrinterJob, printDialog, PageFormat, FontMetrics -> berechnen ob max Zeilenlänge in Druckbereich passt
//	FrameDialogModal	nichts Neues
//	FrameDialogSuc...	nichts Neues
//	ListBoxComboBox		(JList, DefaultListModel, JScrollPane) -> ListBox, ListSelectionListener, setSelectionMode, e.getValueIsAdjusting, 
//						(JComboBox, DefaultComboBoxModel) -> ComboBox, zu neuen Datenmodell wechseln, ItemListener, GraphicEnviroment -> alle Schriftarten
//						ListBox: Element selektieren im Programm, ensureIndexIsVisible, Multiple_Interval_Selection
//						ActionListener ist einfachste Listener (viele Objekte haben das) -> bietet bestimmt einfache Ereignisse
//	Util/ListItem		Datentyp für Einträge in ListBox (besteht aus Schlüssel für DB und AnzeigeString)

//W5T4_Dozent:
//	FrameDialog			Neu: Singleton aufrufen
//	FrameDialogModal	nichts Neues
//	FrameDialogSuc...	Neu: Singleton implementiert
//	ListBoxComboBox		Neu: 3 Comboboxen (Schriftart, Schriftstil, Schriftgröße), setVerticalScrollBarPolicy (TextArea), deriveFont -> Element des Font 
//						verändern, ComboBox editierbar (KeyListener für Überprüfung) -> ItemListener: getSelectedIndex = -1 (wenn selbst geschrieben wurde)
//						KeyListener für Combobox: cbo.setEditor().getEditorComponent().addKeyListener, .addFocusListener (wie Farbmischer), um an Text zu
//						kommen: cbo.setEditor().getItem, Dezimalzeichen des Systems ermitteln, eingegebene Zeichen autom. umwandeln (.->,)
//	TabbedPane			BorderLayout, JMenubar, JMenu,JMenuItem -> ActionListener, StatusBar, setSize oder preferredSize (feste Größe), JTabbedPane, Mehrere 
//						Dateien auswählen beim öffnen, JMenuItem: RadioButton und Checkbox sind abgeleitet von JMenuItem)
//	Util/StatusBar		Ziel: Universelle StatusBar für viele Projekte, anderes Layout: BorderLayout,EtchedBorder, EmptyBorder (Text im Label ausrichten)
//	Util/WinUtil		Erstellt JMenu mit Referenz zurück (Methodenbeschreibung JavaDoc), Enums statt Konstanten,  Untermenüs 

//W5T5_Dozent:
//	TabbedPane			Neu: AcceleratorKeys, addTab, Try-Catch mit Ressourcen -> new File, ChangeListener für tabAuswahl, ScrollTabLayout, getComponentAt()
//						= JScrollPane (TextArea) bzw Tab welcher ausgewählt wurde, Tableiste oben/u/l/r, Referenz auf Komponenten(zb TextArea) von 
//						Scrollpane bekommen = jsp.getViewport() -> getView(), Zeilenumbruch: 2 Ereignisse: Haken setzen/tab wechseln
//W5T5_Dozent.RMI		
//	RMIServer			Allgemein RMI, Constructor mit throws, ShutDown als nebenläufiger Prozess -> Countdown: TimeUnit.Sleep,SystemTray, PopupMenu
//	IRemoteMethods		Interface mit nötigen Methoden und Datentypen
//	ServerTime			user def. Datentyp: gibt Servertime zurück -> serializable (simpleDateFormat ist auch serializable)
// 	RMIClient			Client für Server mit Abfrage von Lebenszeichen von Server
//W5T5_Dozent.Erwei...		
//	ComboBoxCellRe...	CellRenderer
//	ExtendedListItem	Datentyp ListItem erweitert für CellRender
//	ListBoxComboBox		Schriftarten in ComboBox werden direkt angezeigt

//W6T1_Dozent:
//	Datenbankzugriffe	Fenster mit Menu, Statusleiste als Anzeige für DB Status, Postleitzahlen importieren, addChoosableFileFilter, Warten auf neu-
//						zeichen paintImmediately(statusBar.getVisibleRect())
//	DBConnection		Da nur eine DB -> static, Datenbank öffnen, schließen, welche Datenbank geöffnet (getCatalog)

//W6T2_Dozent:
//	Datenbankzugriffe	Neu: readfile ist nebenläufiger Prozess (Abarbeitung warte nicht auf Vollständigkeit) -> readfile für Statusbar im Nebenläufigen 
//						Prozess und readfile für wirklichen Datenbankschreibaktionen, Datenbank löschen, ' maskieren durch ''SQL Aggregatfunktionen 
//						(MAX, MIN, COUNT,...) (SELECT count(Spalte) from plz where prykey >10), UNIQUE Index erstellen -> keine Duplikate (funktioniert
//						automatisch) -> Integrität macht DB, PrimaryKey automatisch erhöhen beim neuen Datenschreiben
//	DBConnection		Neu: executeNonQuery(Rückgabewert = Anzahl Änderungen), executeScalar -> SELECT/ein Wert Resultset, 
//	Globals				globale Hilfsmethoden: INSERT Methode, Last Primarykey, Duplikatprüfung mit Count, unbestimmter Zahlenwert (int,long) -> (Number)

//W6T3_Dozent:
//	Datenbankzugriffe	Neu: Fortschrittsbalken, in StatusBar Borderlayout LINE_END, grün, setStringPainted(true)-> Prozentsatzanzeige, Fortschritt anhand 
//						Dateigröße und Bytes von eingelesenen Zeichen, prepareStatement statt StdSQL Statements, MenuBar deaktivieren (mit Schleife durch
//						Elemente), INSERT INTO erweitert: nach VALUES mehrere Datensätze durch "," trennen
//	DBConnection		Neu: prepareStatement, Transaktionsscheifen (nur mit InnoDB) -> AutoComit = Transaktionsschleife geöffnet, wenn stmt.close -> 
//						resultset weg!
//	Globals				Neu: vorbereite SQL Statements -> schneller beim Schreiben in DB
//	PLZTable			JDialog: mit StatusBar, Menu und JTable, in eigener Klasse abgeleitet von AbstractTableModel: Datenmodell für JTable, Metadaten
//						aus rSet, ResultSet + Metadaten haben 1 basierende Indices!!!

//W6T4_Dozent:
//	Datenbankzugriffe	Neu: ProgressWindow, Icon für Fenster, 
//	DBConnection		nichts neues			
//	Globals				nichts neues
//	PLZTable			Icons integriert, nebenläufiger Prozess zum Einlesen der Datensätze, SQL Aliasnamen, Tabellenformatierung, ListSelectionListener:
//						e.getValueIsAdjusting filtert Ereignis beim Zeilenänderung in Tabelle -> welcher Datensatz in Tabelle in Statusbar anzeigen
//						Tasten POS1 und ENDE überschreiben, Spalten der Tabelle unsichtbar machen, MouseListener, Doppelklick mit Mouse = e.getClickCount()
//						== 2 
//	ProgressWindowDemo	Demo für Fortschrittsanzeige (Konfigfenster mit GridLayout), mit vielen Listener Verschiebt Fenster mit Mouse
//	Util/ProgressWindow	Konfigurierbare Fortschrittsanzeige (runnable), bei Abbrechen Kopieren stoppen bis zur Entscheidung
//	Util/StatusBar		Neu: Icon kann hinzufügt werden

//W6T5_Dozent:
//	Datenbankzugriffe	nichts neues
//	DBConnection		nichts neues 	
//	Globals				nichts neues 
//	PLZTable			Neu: Aktualisieren rSet/DB -> showData als nicht nebenläufer Prozess, um an die Tabelle (welche Datenmodell bekommen hat) zu 
//						kommen (und auch indirekt ans Datenmodell) -> Methode setValueAt() von Datenmodell überschreiben (fireTableCellUpdated
//						aktualisiert Zelle sofort in Tabelle), bei nicht überschriebenen Methoden von PLZTableModel muss 
//						((PLZTableModel)Tabelle.getModel). ... -> auf eingefügten Eintrag autom. blättern, löschen mit delete
//	PLZForm				Abfrage aus Datenbank direkt -> in rSet (rSet.getString("PLZ [oder alias]"), Plausibilitätsprüfung, Focusmanager, Änderungsprüfungen
//						windowClosing, JOptionPane Abfrage (auch X Schließen Button), vieleeee Überprüfung der werte, TIMESTAMP überprüfen
//	Serialisierung		Serialisieren = das ganze Objektgeflecht wird gespeichert (nicht nur das "first level" Objekt) -> preparedStatements zur DB
//	TestObjekt			Verkettetes Objekt


//W7T1_Dozent:
//	Datenbankzugriffe	Neu: Menueintrag für Look and Feel, über ActionCommand
//	DBConnection		nichts neues 	
//	Globals				Neu: Look and Feel speichern/dynamische Auswahl/Einstellung speichern in prop Datei, Command
//	PLZTable			 
//	PLZForm				



public class Ueberblick{public static void main(String[] args){}}
