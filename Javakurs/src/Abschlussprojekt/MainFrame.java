package Abschlussprojekt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import Util.WinUtil;



public class MainFrame extends JFrame
{
	private JMenuBar 				menuBar;
	private	JMenu					menuDatei, menuBearbeiten, menuAnsicht, miWaehlSprache, menuExtras;
	private JMenuItem				miBeenden, miNeu, miHistory;
	private JRadioButtonMenuItem	miDeutsch, miSpanisch;
	
	private HeadPanel				HeadPanel; 
	private StartPanel				StartPanel;
	private ProjectStatusBar		ProjectStatusBar;
	private	SearchResults			searchResults;
	private SubjectResults			subjectResults;
	private LetterResults			letterResults;
	private	TermWindow				termWindow;
	private JComponent				currentCenter;
	private	Component				centerContainer = new JPanel(new GridLayout(1,1));
	
	
	private ResourceBundle			Lang;
	private Locale					currentLocale;
	

		
	/**
	 * <li><b><i>MainFrame</i></b> <br>
	 * <br>
	 * public MainFrame() <br>
	 * <br>
	 * Konstruktor für MainFrame<br>
	 * <br>
	 *    
	 */	
	public MainFrame()
	{
		// Konnte Datenbank geöffnet werden? -> wenn nicht: Fehlermeldung und Abbruch
		if(openMySQLDatabase())
			initializeComponents();
		else JOptionPane.showMessageDialog(	this, "Die Datenbank konnte nicht geöffnet werden!", 
											"Fehler beim Öffnen der Datenbank", JOptionPane.ERROR_MESSAGE);
	}
	
	private void initializeComponents()
	{
		// Sprache einstellen
		currentLocale = new Locale("de");
		// Sprachdatei laden entsprechend der Sprache
		Lang = ResourceBundle.getBundle("resources.lexikon",currentLocale);
		
				
		this.setTitle(Lang.getString("title"));
		this.setSize(1300,  800);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) 
			{
				closeWindow();
            }
		});
		this.setResizable(false);
		
		// Menu erzeugen
		menuBar = new JMenuBar();
				
		menuDatei = WinUtil.createMenuExt(menuBar, Lang.getString("menuDatei"), null, null, 0);
		menuBearbeiten = WinUtil.createMenuExt(menuBar, Lang.getString("menuBearbeiten"), null, null, 0);
		menuAnsicht = WinUtil.createMenuExt(menuBar, Lang.getString("menuAnsicht"), null, null, 0);
		menuExtras = WinUtil.createMenuExt(menuBar, Lang.getString("menuExtras"), null, null, 0);
		
		miBeenden = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, e -> closeWindow(), Lang.getString("miBeenden"), 
											new ImageIcon(this.getClass().getResource("/images/Delete.png")), 0, null);
		
		miNeu = WinUtil.createMenuItem(menuBearbeiten, null, WinUtil.MenuItemType.ITEM_PLAIN, e -> newEntryMenu(), Lang.getString("miNeu"),
											new ImageIcon(this.getClass().getResource("/images/New.png")), 0,null);
		miWaehlSprache = WinUtil.createSubMenuExt(menuAnsicht, Lang.getString("miWaehlSprache"), null, null, 0);
		miHistory = WinUtil.createMenuItem(menuExtras, null, WinUtil.MenuItemType.ITEM_PLAIN, 
											e -> {	deleteHistory();
													HeadPanel.writeSearchWordsFromDbToHistory();
												 }
											,Lang.getString("miHistory"),
											new ImageIcon(this.getClass().getResource("/images/Delete.png")), 0,null);
		
		// Buttongroup für Sprachauswahl
		
		ButtonGroup sprachButtonGroup = new ButtonGroup();
		
		miDeutsch = (JRadioButtonMenuItem)WinUtil.createMenuItem(miWaehlSprache, null, WinUtil.MenuItemType.ITEM_RADIO , e -> switchDE(),
																Lang.getString("miDeutsch"), null, 0, null);
		
		miSpanisch = (JRadioButtonMenuItem)WinUtil.createMenuItem(miWaehlSprache, null, WinUtil.MenuItemType.ITEM_RADIO , e -> switchES(),
																Lang.getString("miSpanisch"), null, 0, null);
		
		
		sprachButtonGroup.add(miDeutsch);
		sprachButtonGroup.add(miSpanisch);
		
		// Sprachbutton richtig setzen
		if (this.getCurrentLocale().toString().equals("de"))
			miDeutsch.setSelected(true);
		else
			miSpanisch.setSelected(true);
						
		this.setJMenuBar(menuBar);
		
		
		// Statusleiste
		ProjectStatusBar = new ProjectStatusBar(this);
		this.add(ProjectStatusBar, BorderLayout.PAGE_END);
		
		// Kopfzeile
		HeadPanel = new HeadPanel(this);
		this.add(HeadPanel, BorderLayout.PAGE_START);
				
		
		// Container CENTER zuordnen (in Container werden die verschiedenen "Hauptfenster" eingefügt
		this.add(centerContainer, BorderLayout.CENTER);
		
		// StartPanel initialisieren und in Center Container
		StartPanel = new StartPanel(this);
		((JPanel)centerContainer).add(StartPanel);
		this.validate();
		
		// Aktuelles Panel im CENTER in Variablen speichern
		currentCenter = StartPanel;
		
		// Status der Statusbar setzen
		ProjectStatusBar.setStatus("start", null, null);
							
	}
	
	private void initFrame()
	{
		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * <li><b><i>showFrame</i></b> <br>
	 * <br>
	 * public void showFrame() <br>
	 * <br>
	 * Frame initialisieren und sichtbar machen<br>
	 * <br>
	 *    
	 */
	public void showFrame()
	{
		// Frame initialisieren und sichtbar machen
		initFrame();
		this.setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		
		MainFrame fenster = new MainFrame();
		fenster.showFrame();
	}
	
	// Datenbank öffnen
	private boolean openMySQLDatabase()
	{
		
		String connectionString, classForName;
		String server = "localhost";
		String dataBase = "alfatraining";
		classForName = "com.mysql.jdbc.Driver";
		
		connectionString = "jdbc:mysql://" + server + ":3306/";
		connectionString += dataBase;
		
		if(DBConnection.connectToDatabase(classForName, connectionString, "root", null))
			return true;
		return false;
				
	}

	
// Getter und Setter
	
	/**
	 * <li><b><i>getCurrentCenter</i></b> <br>
	 * <br>
	 * public JComponent getCurrentCenter() <br>
	 * <br>
	 * Gibt Referenz des aktuell im Center Container befindlichen JComponent zurück<br>
	 * <br>
	 * 
	 *       
	 * @return JComponent
	 *     		  - Referenz des aktuell im Center Container befindlichen Panels  
	 */
	public JComponent getCurrentCenter()
	{
		return currentCenter;
	}
	
	/**
	 * <li><b><i>ResourceBundle</i></b> <br>
	 * <br>
	 * public ResourceBundle getLang()<br>
	 * <br>
	 * Gibt Referenz der aktuellen Sprachdatei zurück<br>
	 * <br>
	 * 
	 *       
	 * @return Lang
	 *     		  - Referenz auf aktuelle Sprachdatei  
	 */	
	public ResourceBundle getLang()
	{
		return Lang;
	}
	
	/**
	 * <li><b><i>getCurrentLocale</i></b> <br>
	 * <br>
	 * public Locale getCurrentLocale()<br>
	 * <br>
	 * Gibt Referenz der aktuellen Sprache zurück<br>
	 * <br>
	 * 
	 *       
	 * @return currentLocale
	 *     		  - Referenz der aktuellen Sprache  
	 */
	public Locale getCurrentLocale()
	{
		return currentLocale;
	}

	/**
	 * <li><b><i>setCurrentLocale</i></b> <br>
	 * <br>
	 * public void setCurrentLocale(String locale)<br>
	 * <br>
	 * Setzt neue Sprache<br>
	 * <br>
	 * 
	 *       
	 * @param locale
	 *     		  - Zeichenkette der neuen Sprache ["de" oder "es"]  
	 */
	public void setCurrentLocale(String locale)
	{
		currentLocale = new Locale(locale);
		Lang = ResourceBundle.getBundle("resources.lexikon",currentLocale);
	}
	
	/**
	 * <li><b><i>getMiHistory</i></b> <br>
	 * <br>
	 * public JMenuItem getMiHistory()<br>
	 * <br>
	 * Gibt Referenz des MenuItems History zurück<br>
	 * <br>
	 * 
	 * @return miHistory
	 *     		  - Referenz des MenuItems History  
	 */
	public JMenuItem getMiHistory()
	{
		return miHistory;
	}
	
	/**
	 * <li><b><i>getProjectStatusBar</i></b> <br>
	 * <br>
	 * public JMenuBar getProjectStatusBar()<br>
	 * <br>
	 * Gibt Referenz auf die StatusBar zurück<br>
	 * <br>
	 * 
	 * @return JMenuBar
	 *     		  - Referenz auf die StatusBar  
	 */
	public JMenuBar getProjectStatusBar()
	{
		return ProjectStatusBar;
	}

	/**
	 * <li><b><i>getStartPanel</i></b> <br>
	 * <br>
	 * public StartPanel getStartPanel()<br>
	 * <br>
	 * Gibt Referenz auf das StartPanel zurück<br>
	 * <br>
	 * 
	 * @return StartPanel
	 *     		  - Referenz auf das StartPanel  
	 */
	public StartPanel getStartPanel()
	{
		return StartPanel;
	}

	/**
	 * <li><b><i>getHeadPanel</i></b> <br>
	 * <br>
	 * public HeadPanel getHeadPanel()<br>
	 * <br>
	 * Gibt Referenz auf das HeadPanel zurück<br>
	 * <br>
	 * 
	 * @return HeadPanel
	 *     		  - Referenz auf das HeadPanel  
	 */
	public HeadPanel getHeadPanel()
	{
		
		return HeadPanel;
	}
		
		
	
// Sprachumstellungen
	
	// Sprache umstellen MainFrame	
	private void switchLangMainFrame()
	{
		this.setTitle(Lang.getString("title"));
		menuDatei.setText(Lang.getString("menuDatei"));
		miBeenden.setText(Lang.getString("miBeenden"));
		menuBearbeiten.setText(Lang.getString("menuBearbeiten"));
		miNeu.setText(Lang.getString("miNeu"));
		menuAnsicht.setText(Lang.getString("menuAnsicht"));
		miWaehlSprache.setText(Lang.getString("miWaehlSprache"));
		miDeutsch.setText(Lang.getString("miDeutsch"));
		miSpanisch.setText(Lang.getString("miSpanisch"));
		menuExtras.setText(Lang.getString("menuExtras"));
		miHistory.setText(Lang.getString("miHistory"));
		
		// Radiobutton in Menu umstellen
		if (getCurrentLocale().toString().equals("de"))
			((JRadioButtonMenuItem)miDeutsch).setSelected(true);
		else
			((JRadioButtonMenuItem)miSpanisch).setSelected(true);
	}
	
	// Sprache umstellen HeadPanel	
	private void switchLangHeadPanel()
	{
		HeadPanel.switchLang();
	}
	
	// Sprache umstellen ProjectStatusBar	
	private void switchLangStatusBar()
	{
		ProjectStatusBar.switchLang();
	}
	
	// Sprache umstellen StartPanel	
	private void switchLangStartPanel()
	{
		StartPanel.switchLang();
		this.validate();
		this.repaint();
		
	}
	
	// Sprache umstellen searchResults	
	private void switchLangSearchResults()
	{
		if (searchResults != null)
		{
			searchResults.switchLang();
			this.validate();
			this.repaint();
		}
	}
	
	// Sprache umstellen subjectResults	
	private void switchLangSubjectResults()
	{
		if (subjectResults != null)
		{
			subjectResults.switchLang();
			this.validate();
			this.repaint();
		}
	}
	
	// Sprache umstellen letterResults	
	private void switchLangLetterResults()
	{
		if (letterResults != null)
		{
			letterResults.switchLang();
			this.validate();
			this.repaint();
		}
	}
	
	
	
	
// Zustandsänderungen Funktionen (ausgelöst durch Actions in Frames) -> Namens Struktur: Ziel_Zustand
	
	/**
	 * <li><b><i>all_ActivteLanguageBtn</i></b> <br>
	 * <br>
	 * public void all_ActivteLanguageBtn(String locale)<br>
	 * <br>
	 * Stellt alle Panels um, wenn sich die Sprache ändert oder <br>
	 * neue Einträge erstellt werden oder Einträge geändert werden<br>
	 * 
	 * @param locale
	 * 			  - aktuelle Sprache
	 */
	public void all_ActivteLanguageBtn(String locale)
	{
		//neue Sprache setzen
		setCurrentLocale(locale);
		
		// Sprache der Panels wechseln 
		switchLangMainFrame();
		switchLangHeadPanel();
		switchLangStatusBar();
		switchLangStartPanel();
		switchLangSearchResults();
		switchLangSubjectResults();
		switchLangLetterResults();
		
	}
	
	/**
	 * <li><b><i>StatusBar_ChangeVisibleLabels</i></b> <br>
	 * <br>
	 * public void StatusBar_ChangeVisibleLabels()<br>
	 * <br>
	 * Stellt Anzeige in Statusbar um wenn Fachgebiete ausgewählt wurden<br>
	 * (Ebene "Begriffe" wird in TermWindow gemacht)<br>
	 * 
	 */
	public void StatusBar_ChangeVisibleLabels()
	{
		if (currentCenter == subjectResults)
			ProjectStatusBar.setStatus("subject", ProjectStatusBar.getCurrentSubject(), null);
		else
			ProjectStatusBar.setStatus("start", null, null);
		
	}
	
	/**
	 * <li><b><i>StartPanel_ActiveStartPanel</i></b> <br>
	 * <br>
	 * public void StartPanel_ActiveStartPanel()<br>
	 * <br>
	 * Das Startpanel wird aktiviert (über Headpanel oder Statusbar)<br>
	 * Entsprechend wird der CENTER Container befüllt und die<br>
	 * Statusbar eingestellt<br>
	 */
	public void StartPanel_ActiveStartPanel()
	{
		// Panel nicht weg schmeißen nur die ArrayLists neu füllen
		ProjectStatusBar.setStatus("start", null, null);
		((JPanel)centerContainer).remove(getCurrentCenter());
		((JPanel)centerContainer).add(StartPanel);
		// wegen add muss validate passieren
		this.validate();
		// wegen remove muss repaint passieren
		this.repaint();
		
		currentCenter = StartPanel;
	}
	
	/**
	 * <li><b><i>all_ActivateSearchWindow</i></b> <br>
	 * <br>
	 * public void all_ActivateSearchWindow(String searchWord)<br>
	 * <br>
	 * Das Suchpanel wird aktiviert (über Headpanel)<br>
	 * Entsprechend wird der CENTER Container befüllt und die<br>
	 * Statusbar eingestellt<br>
	 * 
	 * @param searchWord
	 *     		  - Suchwort 
	 */
	public void all_ActivateSearchWindow(String searchWord)
	{
		ProjectStatusBar.setStatus("start", null, null);
		
		searchResults = new SearchResults(this, searchWord);
		
		((JPanel)centerContainer).remove(getCurrentCenter());
		((JPanel)centerContainer).add(searchResults);
		this.validate();
		this.repaint();
		currentCenter = searchResults;		
	}
	
	
	/**
	 * <li><b><i>termWindow_openTermWindow</i></b> <br>
	 * <br>
	 * public void termWindow_openTermWindow(String term, String subject)<br>
	 * <br>
	 * Das Panel (als Dialog) mit der genauen Begriffserklärung wird geöffnet<br>
	 * Entsprechend wird die Statusbar eingestellt<br>
	 * 
	 * @param term
	 *     		  - Begriff 
	 *     
	 * @param subject
	 *     		  - Fachgebiet 
	 */
	public void termWindow_openTermWindow(String term, String subject)
	{
		// Begriff wird in der Statusbar angezeigt
		ProjectStatusBar.setStatus("term", subject, term);
		
		termWindow = new TermWindow(this, term);
		termWindow.showDialog(this);
	}
	
	/**
	 * <li><b><i>subjectResults_openSubjectsWindow</i></b> <br>
	 * <br>
	 * public void subjectResults_openSubjectsWindow(String subjectWord)<br>
	 * <br>
	 * Das FachgebietePanel wird aktiviert (über StartPanel)<br>
	 * Entsprechend wird der CENTER Container befüllt und die<br>
	 * Statusbar eingestellt<br>
	 * 
	 * @param subjectWord
	 *     		  - Fachgebiet
	 */
	public void subjectResults_openSubjectsWindow(String subjectWord)
	{
		ProjectStatusBar.setStatus("subject", subjectWord, null);
		
		subjectResults = new SubjectResults(this, subjectWord);
		
		((JPanel)centerContainer).remove(getCurrentCenter());
		((JPanel)centerContainer).add(subjectResults);
		this.validate();
		this.repaint();
		
		currentCenter = subjectResults;
		
				
	}
	
	/**
	 * <li><b><i>letterResults_OpenLetterWindow</i></b> <br>
	 * <br>
	 * public void letterResults_OpenLetterWindow(String letterWord)<br>
	 * <br>
	 * Das BuchstabenPanel wird aktiviert (über HeadPanel)<br>
	 * Entsprechend wird der CENTER Container befüllt und die<br>
	 * Statusbar eingestellt<br>
	 * 
	 * @param letterWord
	 *     		  - Anfangsbuchstabe
	 */
	public void letterResults_OpenLetterWindow(String letterWord)
	{
		ProjectStatusBar.setStatus("start", null, null);
		
		letterResults = new LetterResults(this, letterWord);
		
		((JPanel)centerContainer).remove(getCurrentCenter());
		((JPanel)centerContainer).add(letterResults);
		this.validate();
		this.repaint();
		currentCenter = letterResults;
	}
	
	
	
	
// Datenbankzugriffe (SQL Befehle)
	
	
	/**
	 * <li><b><i>insertDbFachbegebiete</i></b> <br>
	 * <br>
	 * public boolean insertDbFachbegebiete(String Fachgebiet, String Especialidad) <br>
	 * <br>
	 * Fügt einen neuen Datensatz bestehend aus deutsches und spanisches Fachgebiet<br>
	 * in die Tabelle Fachgebiete<br>
	 *
	 * @param Fachgebiet
	 *            - deutsches Fachgebiet
	 *            
	 * @param Especialidad
	 *            - spanisches Fachgebiet
	 *
	 * @return boolean
	 *     		  - Wurde das Einfügen durchgeführt?     
	 */	
	public boolean insertDbFachbegebiete(String Fachgebiet, String Especialidad)
	{
		if (Globals.insertFachgebiete(Fachgebiet, Especialidad))
			return true;
		return false;
	}
	
	/**
	 * <li><b><i>insertDbLexikon</i></b> <br>
	 * <br>
	 * public boolean insertDbLexikon(String Begriff, String Termino, int Fachgebiet, String Inhalt, String Contenido) <br>
	 * <br>
	 * Fügt einen neuen Datensatz bestehend aus deutschen und spanischen Begriff und Textfeldinhalt sowie Schlüssel<br>
	 * für das Fachgebiet in die Tabelle Lexikon<br>
	 *
	 * @param Begriff
	 *            - deutscher Begriff
	 *            
	 * @param Termino
	 *            - spanischer Begriff
	 *            
	 * @param Fachgebiet
	 *            - Schlüssel für das Fachgebiet
	 *            
	 * @param Inhalt
	 *            - spanisches Textfeldinhalt
	 *            
	 * @param Contenido
	 *            - deutsches Textfeldinhalt
	 *            
	 * @return boolean
	 *     		  - Wurde das Einfügen durchgeführt?     
	 */
	public boolean insertDbLexikon(String Begriff, String Termino, int Fachgebiet, String Inhalt, String Contenido)
	{
		if (Globals.insertLexikon(Begriff, Termino, Fachgebiet, Inhalt, Contenido))
			return true;
		return true;
	}
	
	/**
	 * <li><b><i>insertDbHistory</i></b> <br>
	 * <br>
	 * public void insertDbHistory(String searchWord) <br>
	 * <br>
	 * Fügt ein neues Suchwort in die Tabelle History<br>
	 * <br>
	 *
	 * @param searchWord
	 *            - Suchbegriff
	 *            
	 * @return boolean
	 *     		  - Wurde das Einfügen durchgeführt?     
	 */
	public void insertDbHistory(String searchWord)
	{
		if (!Globals.insertHistory(searchWord))
			JOptionPane.showMessageDialog(null, Lang.getString("insertHistory"), Lang.getString("errorTitle"), JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * <li><b><i>isDoubleFachgebietDE</i></b> <br>
	 * <br>
	 * public boolean isDoubleFachgebietDE(String subjectDe) <br>
	 * <br>
	 * Ermittelt ob das deutsche Fachgebiet in der Tabelle Fachgebiete vorhanden ist<br>
	 * <br>
	 *
	 * @param subjectDe
	 *            - deutsches Fachgebiet
	 *            
	 * @return boolean
	 *     		  - Ist das Fachgebiet vorhanden?     
	 */
	public boolean isDoubleFachgebietDE(String subjectDe)
	{
		if (Globals.istFachgebietVorhandenDE(subjectDe))
			return true;
		return false;
	}
	
	/**
	 * <li><b><i>isDoubleFachgebietES</i></b> <br>
	 * <br>
	 * public boolean isDoubleFachgebietES(String subjectEs) <br>
	 * <br>
	 * Ermittelt ob das spanische Fachgebiet in der Tabelle Fachgebiete vorhanden ist<br>
	 * <br>
	 *
	 * @param subjectEs
	 *            - spanisches Fachgebiet
	 *            
	 * @return boolean
	 *     		  - Ist das Fachgebiet vorhanden?     
	 */
	public boolean isDoubleFachgebietES(String subjectEs)
	{
		if (Globals.istFachgebietVorhandenES(subjectEs))
			return true;
		return false;
	}
	
	/**
	 * <li><b><i>isDoubleLexikon</i></b> <br>
	 * <br>
	 * public boolean isDoubleLexikon(String termDe) <br>
	 * <br>
	 * Ermittelt ob der deutsche Begriff in der Tabelle Lexikon vorhanden ist<br>
	 * <br>
	 *
	 * @param termDe
	 *            - deutscher Begriff
	 *            
	 * @return boolean
	 *     		  - Ist der Begriff vorhanden?     
	 */
	public boolean isDoubleLexikon(String termDe)
	{
		if (Globals.istLexikonEintragVorhanden(termDe))
			return true;
		return false;
	}
	
	/**
	 * <li><b><i>getPrimaryKeyFachgebietDE</i></b> <br>
	 * <br>
	 * public int getPrimaryKeyFachgebietDE(String subjectDe) <br>
	 * <br>
	 * Gibt den Primärschlüssel eines deutschen Fachgebiets in der Tabelle Fachgebiete zurück <br>
	 * <br>
	 *
	 * @param subjectDe
	 *            - deutsches Fachgebiet
	 *            
	 * @return int
	 *     		  - Primärschlüssel     
	 */
	public int getPrimaryKeyFachgebietDE(String subjectDe)
	{
			return Globals.getPrimaryKeyFachgebietDE(subjectDe);
	}
	
	/**
	 * <li><b><i>getPrimaryKeyFachgebietES</i></b> <br>
	 * <br>
	 * public int getPrimaryKeyFachgebietES(String subjectEs) <br>
	 * <br>
	 * Gibt den Primärschlüssel eines spanischen Fachgebiets in der Tabelle Fachgebiete zurück <br>
	 * <br>
	 *
	 * @param subjectEs
	 *            - spanisches Fachgebiet
	 *            
	 * @return int
	 *     		  - Primärschlüssel     
	 */
	public int getPrimaryKeyFachgebietES(String subjectEs)
	{
			return Globals.getPrimaryKeyFachgebietES(subjectEs);
	}
	
	/**
	 * <li><b><i>updateTermText</i></b> <br>
	 * <br>
	 * public static boolean updateTermTextDE(String Begriff, String Inhalt, String Contenido) <br>
	 * <br>
	 * Aktualisiert einen Datensatz bestehend aus deutschen und spanischen Textfeldinhalt <br>
	 * in der Tabelle Lexikon wo der deutsche/spanische Begriff übereinstimmt<br>
	 * (entsprechend aktueller Sprache wird eine Funktion ausgewählt)<br>
	 *
	 * @param Begriff
	 *            - deutscher/spanischer Begriff
	 *            
	 * @param Inhalt
	 *            - deutsches/spanisches Fachgebiet
	 *            
	 * @param Contenido
	 *            - spanisches/deutsches Fachgebiet
	 *
	 * @return boolean
	 *     		  - Wurde das Update durchgeführt?     
	 */	
	public boolean updateTermText(String Begriff_Lang1, String Inhalt_Lang1, String Inhalt_Lang2)
	{
		if (this.getCurrentLocale().toString().equals("de"))
			return Globals.updateTermTextDE(Begriff_Lang1, Inhalt_Lang1, Inhalt_Lang2);
		else
			return Globals.updateTermTextES(Begriff_Lang1, Inhalt_Lang1, Inhalt_Lang2);
	}
	
	/**
	 * <li><b><i>isLetterAvailable</i></b> <br>
	 * <br>
	 * public boolean isLetterAvailable(String searchLetter)<br>
	 * <br>
	 * Prüft ob ein deutscher/spanischer Begriff mit dem Anfangsbuchstaben in <br>
	 * Tabelle Lexikon vorhanden ist<br>
	 * (entsprechend aktueller Sprache wird eine Funktion ausgewählt)<br>
	 *
	 * @param Begriff
	 *            - deutscher/spanischer Begriff [zb %a]
	 *            
	 * @return boolean
	 *     		  - Ist ein deutscher/spanischer Begriff mit dem Anfangsbuchstaben vorhanden?     
	 */
	public boolean isLetterAvailable(String searchLetter)
	{
		if (this.getCurrentLocale().toString().equals("de"))
			return Globals.istBuchstabeVorhandenDE(searchLetter);
		else
			return Globals.istBuchstabeVorhandenES(searchLetter);
	}
	
	/**
	 * <li><b><i>getDataFachgebiete</i></b> <br>
	 * <br>
	 * public ResultSet getDataFachgebiete()<br>
	 * <br>
	 * Gibt alle deutschen/spanischen Fachgebiete aus Tabelle Fachgebiete als Resultset aus<br>
	 * (entsprechend aktueller Sprache wird eine Funktion ausgewählt)<br>
	 *
	 *
	 * @return ResultSet
	 *     		  - alle deutschen/spanischen Fachgebiete als ResultSet     
	 */
	public ResultSet getDataFachgebiete()
	{
		if (this.getCurrentLocale().toString().equals("de"))
			return Globals.getFachgebieteDE();
		else
			return Globals.getFachgebieteES();
	}
	
	/**
	 * <li><b><i>getDataHistory</i></b> <br>
	 * <br>
	 * public ResultSet getDataHistory()<br>
	 * <br>
	 * Gibt alle Suchgebegriffe aus Tabelle History als Resultset aus<br>
	 * <br>
	 *
	 * @return ResultSet
	 *     		  - alle Suchgebegriffe aus Tabelle History als ResultSet     
	 */
	public ResultSet getDataHistory()
	{
		String SQL = "SELECT SUCHWORTE FROM HISTORY";
				
		return DBConnection.executeQuery(SQL);
	}
	
	/**
	 * <li><b><i>deleteHistory</i></b> <br>
	 * <br>
	 * public static void deleteHistory() <br>
	 * <br>
	 * Löscht alle Datensätze aus Tabelle History <br>
	 * <br>
	 *  
	 */
	public void deleteHistory()
	{
		Globals.deleteHistory();
		miHistory.setEnabled(false);
	}
	
	/**
	 * <li><b><i>getDataSearchResult</i></b> <br>
	 * <br>
	 * public ResultSet getDataSearchResult(String searchTerm) <br>
	 * <br>
	 * Gibt komplette Datensatz der beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo der Suchbegriff mit dem deutschen oder spanischen Begriff übereinstimmt<br>
	 * (spracheigene Umlaute werden, wenn nötig, ersetzt)<br>
	 *
	 * @param searchTerm
	 *            - Suchbegriff
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden Datensätze     
	 */
	public ResultSet getDataSearchResult(String searchTerm)
	{
		if (this.getCurrentLocale().toString().equals("de"))
			return Globals.searchDE(searchTerm);
		else
			return Globals.searchES(searchTerm);
	}
	
	/**
	 * <li><b><i>getDataSearchResultLang2</i></b> <br>
	 * <br>
	 * public ResultSet getDataSearchResultLang2(String searchTerm) <br>
	 * <br>
	 * Gibt komplette Datensatz der beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo der Suchbegriff mit dem deutschen oder spanischen Begriff übereinstimmt (2. Sprache)<br>
	 * (spracheigene Umlaute werden, wenn nötig, ersetzt)<br>
	 *
	 * @param searchTerm
	 *            - Suchbegriff
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden Datensätze     
	 */
	public ResultSet getDataSearchResultLang2(String searchTerm)
	{
		if (this.getCurrentLocale().toString().equals("de"))
			return Globals.searchDELang2(searchTerm);
		else
			return Globals.searchESLang2(searchTerm);
	}
	
	/**
	 * <li><b><i>getDataTerm</i></b> <br>
	 * <br>
	 * public ResultSet getDataTerm(String Term)<br>
	 * <br>
	 * Gibt komplette Datensatz der beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo der Suchbegriff mit dem deutschen/spanischen Begriff übereinstimmt<br>
	 *
	 * @param term
	 *            - Ausgewählter deutscher Begriff [Begriff ist aus Tabelle]
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden Datensätze     
	 */
	public ResultSet getDataTerm(String Term)
	{
		if (this.getCurrentLocale().toString().equals("de"))
			return Globals.getTermDE(Term);
		else
			return Globals.getTermES(Term);
	}
	
	/**
	 * <li><b><i>getDataSubjectResult</i></b> <br>
	 * <br>
	 * public ResultSet getDataSubjectResult(String Subject)<br>
	 * <br>
	 * Gibt alle deutschen/spanischen Begriffe aufsteigend sortiert aus den beiden verknüpften Tabellen Lexikon und Fachgebiete <br>
	 * zurück wo das Fachgebiet mit dem deutschen Suchfachbegebiet übereinstimmt<br>
	 * (entsprechend aktueller Sprache wird eine Funktion ausgewählt)<br>
	 *
	 * @param Subject
	 *            - deutsches/spanisches Fachgebiet
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefunden deutschen/spanischen Begriffe     
	 */
	public ResultSet getDataSubjectResult(String Subject)
	{
		if (this.getCurrentLocale().toString().equals("de"))
			return Globals.getSubjectTermsDE(Subject);
		else
			return Globals.getSubjectTermsES(Subject);
	}
	
	/**
	 * <li><b><i>getDataLetterResult</i></b> <br>
	 * <br>
	 * public ResultSet getDataLetterResult(String Letter)<br>
	 * <br>
	 * Gibt alle deutschen/spanischen Begriffe und Fachgebiete (aufsteigend sortiert nach Begriffen)<br>
	 * aus den beiden verknüpften Tabellen Lexikon und Fachgebiete<br>
	 * zurück, wo der Buchstabe mit dem Anfangsbuchstaben der deutschen/spanischen Begriffe übereinstimmt<br>
	 * (entsprechend aktueller Sprache wird eine Funktion ausgewählt)<br>
	 *
	 * @param Letter
	 *            - Anfangsbuchstabe [a%]
	 *            
	 * @return ResultSet
	 *     		  - Resultset der gefundenen deutschen/spanischen Begriffe     
	 */
	public ResultSet getDataLetterResult(String Letter)
	{
		if (this.getCurrentLocale().toString().equals("de"))
			return Globals.getLetterTermsDE(Letter);
		else
			return Globals.getLetterTermsES(Letter);
	}
	
	/**
	 * <li><b><i>getSubjectChangeDeEs</i></b> <br>
	 * <br>
	 * public String getSubjectChangeDeEs(String subject)<br>
	 * <br>
	 * Suche aus Tabelle Fachgebiete entsprechend des spanischen/deutschen Fachgebiets<br>
	 * das zugeordnete deutsche/spanische Fachgebiet heraus<br>
	 * (entsprechend aktueller Sprache wird eine Funktion ausgewählt)<br>
	 *
	 * @param Especialidad
	 *            - spanisches/deutsches Fachgebiet
	 *            
	 * @return String
	 *     		  - übersetztes deutsches/spanisches Fachgebiet     
	 */
	public String getSubjectChangeDeEs(String subject)
	{
		if (this.getCurrentLocale().toString().equals("de"))
			return Globals.getSubjectEsToDe(subject);
		else
			return Globals.getSubjectDeToEs(subject);
	}
	
	/**
	 * <li><b><i>getTermChangeDeEs</i></b> <br>
	 * <br>
	 * public String getTermChangeDeEs(String subject)<br>
	 * <br>
	 * Suche aus Tabelle Lexikon entsprechend des spanischen/deutschen Begriffs<br>
	 * den zugeordneten deutschen/spanischen Begriff heraus<br>
	 * (entsprechend aktueller Sprache wird eine Funktion ausgewählt)<br>
	 *
	 * @param Termino
	 *            - spanischer/deutscher Begriff
	 *            
	 * @return String
	 *     		  - übersetzter deutscher/spanischer Begriff     
	 */	
	public String getTermChangeDeEs(String subject)
	{
		if (this.getCurrentLocale().toString().equals("de"))
			return Globals.getTermEsToDe(subject);
		else
			return Globals.getTermDeToEs(subject);
	}
	

	
// andere Logik
	
	/**
	 * <li><b><i>getRowCount</i></b> <br>
	 * <br>
	 * public int getRowCount(ResultSet fachgebiete)<br>
	 * <br>
	 * Gibt die Zeilenanzahl eines Resultsets zurück<br>
	 * 
	 * @param rSet
	 *     		  - Resultset
	 *     
	 * @return int
	 *     		  - Anzahl der Zeilen
	 */
	public int getRowCount(ResultSet rSet)
	{
		int zeilenanzahl = 0;
		try
		{
			rSet.last();
			zeilenanzahl = rSet.getRow();
			rSet.beforeFirst();
		}
		
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "getRowCount: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			
		}
		return zeilenanzahl;
	}
	
	private boolean queryExit()
	{
		// Benutzerdefinierten Button Text
		String[] options =
		{ Lang.getString("close"), Lang.getString("abort") };

		int retValue = JOptionPane.showOptionDialog(this, Lang.getString("queryExitMF"), Lang.getString("queryExitTitleMF"),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

		// YES = Schließen - Dialog schließen
		if (retValue == JOptionPane.YES_OPTION)
			return true;
		// NO = Abbrechen
		return false;
	}
	
// Listener Funktionen
	
	
	// Beenden Button gedrückt
	private void closeWindow()
	{
		if (queryExit())
			MainFrame.this.dispose();
	}
	
	// Neu Button gedrückt		
	private void newEntryMenu()
	{
		// Modaler Dialog
		newEntryDialog dlg = new newEntryDialog(this);
		dlg.showDialog(this);
		// Dialogreferenz wird gelöscht, wenn geschlossen wurde
		dlg = null;
	}
	
	// Deutsch RadioBox Button gedrückt
	private void switchDE()
	{
		if (this.getCurrentLocale().toString().equals("es"))
		{
			all_ActivteLanguageBtn("de");
			((JRadioButtonMenuItem)miDeutsch).setSelected(true);
			((JRadioButtonMenuItem)miSpanisch).setSelected(false);
			HeadPanel.changeLangBtn("de");
		}
	}
	
	// Spanische RadioBox Button gedrückt
		private void switchES()
		{
			if (this.getCurrentLocale().toString().equals("de"))
			{
				all_ActivteLanguageBtn("es");
				((JRadioButtonMenuItem)miDeutsch).setSelected(false);
				((JRadioButtonMenuItem)miSpanisch).setSelected(true);
				HeadPanel.changeLangBtn("es");
			}
		}

	
}
