package Abschlussprojekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalScrollBarUI;

import Util.WinUtil;

public class SubjectResults extends JScrollPane implements ActionListener
{

	private MainFrame 				mainFrame;
	private JPanel 					searchPanel;
	private JLabel 					resultsLbl, resultsTerm;
	
	private JScrollBar 				scrollbar;
	private int 					scrollBarSize = ((Integer) UIManager.get("ScrollBar.width")).intValue();
	private String					subjectWord;
	private int 					lengthSite;
	private int 					zeilenanzahl;

	private final int 				BGColor = 30;

	private ResourceBundle 			Lang;
	
	private ArrayList<JButton>		termButtons;
	
	/**
	 * <li><b><i>SubjectResults</i></b> <br>
	 * <br>
	 * public SubjectResults(MainFrame mainFrame, String subjectWord)<br>
	 * <br>
	 * Konstruktor für Fachgebiet Panel Panel<br>
	 * <br>
	 *    
	 * @param mainFrame
	 * 				- Referenz auf MainFrame
	 * 
	 * @param subjectWord
	 * 				- Fachgebiet
	 */	
	public SubjectResults(MainFrame mainFrame, String subjectWord)
	{
		this.mainFrame = mainFrame;
		this.subjectWord = subjectWord;
		
		initialize();
	}

	private void initialize()
	{
		// ScrollBar zugreifen und Farbe ändern
		scrollbar = new JScrollBar(ScrollBar.VERTICAL);
		scrollbar.setUI(new MyScrollBarUI());
		this.setVerticalScrollBar(scrollbar);
		
		// Sprache einstellen
		Lang = mainFrame.getLang();

		// AnzeigePanel für Scrollpane initialisieren
		searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBackground(WinUtil.createColor(BGColor, BGColor, BGColor));

		// Ergebnislabel
		resultsLbl = WinUtil.createLabel(Lang.getString("resultsLblSubject") + "  ' " + subjectWord + " '", 50 - scrollBarSize, 30, 1250, 100,
				new EmptyBorder(0, 0, 0, 0), Color.DARK_GRAY, null, null, Color.GRAY);
		resultsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resultsLbl.setFont(resultsLbl.getFont().deriveFont(Font.BOLD, 30));
		searchPanel.add(resultsLbl);
		
		// BegriffsLabel
		resultsTerm = WinUtil.createLabel(Lang.getString("resultsTerm"), 1300 / 2 - 100 - scrollBarSize, 150, 200,
				25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null, null,
				WinUtil.createColor(0, 178, 238));
		resultsTerm.setFont(resultsTerm.getFont().deriveFont(Font.BOLD, 18));
		resultsTerm.setHorizontalAlignment(SwingConstants.CENTER);
		searchPanel.add(resultsTerm);

		// Buttons anhand des Einträge im Fachgebiet erstellen
		createSearchResult();
		
		// Größe des AnzeigePanels entsprechend der Anzahl der Begriffseinträge einstellen (ScrollPane richtet sich nach PreferredSize) 
		searchPanel.setPreferredSize(new Dimension(1100, lengthSite));
		
		// AnzeigePanels der ScrollPane hinzufügen
		this.getViewport().add(searchPanel);

	}
	
	private void createSearchResult()
	{
		// Resultset von aktueller Fachgebiete Tabelle in DB
		ResultSet subjectResultSet = mainFrame.getDataSubjectResult(subjectWord);
		zeilenanzahl = mainFrame.getRowCount(subjectResultSet);
		
		
		// Speichert Suchergebnis (besteht aus Begriff, Fachgebiet) 
		String[] resultListArray = getResultList(zeilenanzahl, subjectResultSet);
			
		//ArrayList nimmt Referenzen von Buttons auf
		termButtons = new ArrayList<JButton>();
							
		// Position der Elemente per Scheife erzeugen	
		for (int j = 0; j < zeilenanzahl; j++)
		{
							
			// Button für Begriffe werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			termButtons.add(WinUtil.createButton(	resultListArray[j], 1300 / 2 - 200 - scrollBarSize, 200+j*50, 400, 25, BorderFactory.createLineBorder(WinUtil.createColor(100, 100, 100)), 
													WinUtil.createColor(BGColor, BGColor, BGColor), 
													this, null, null, false, false, WinUtil.createColor(100, 100, 100)));
			termButtons.get(j).setHorizontalAlignment(SwingConstants.CENTER);
			termButtons.get(j).setFont(termButtons.get(j).getFont().deriveFont(Font.BOLD,18));
			
			searchPanel.add(termButtons.get(j));
							
			// benötigte Länge des JPanels -> Scrollbar notwendig oder nicht
			lengthSite = 250+j*50;
							
		}
										
		
	}
	
		
	private String[] getResultList(int Lines, ResultSet termCacheResultSet)
	{
		
		
		String[] termArrayCache = new String[Lines];
		
		try
		{
			for(int i = 0; i< Lines; i++ )
			{
				// eine Datenzeile wird in Array geschrieben					
				termCacheResultSet.next();
				termArrayCache[i] = (String)termCacheResultSet.getObject(1);
			}
					
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "getResultList" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		return termArrayCache;
		
	}

	/**
	 * <li><b><i>switchLang</i></b> <br>
	 * <br>
	 * public void switchLang()<br>
	 * <br>
	 * Ändert alle nötigen Komponenten von SubjectResult bei Sprachwechsel oder neue Eintrag<br>
	 * <br>
	 *  
	 */	
	public void switchLang()
	{
		// Alle Beschriftungen werden geändert
		Lang = mainFrame.getLang();
				
		// Wie ist das Fachgebiet in der anderen Sprache
		subjectWord = mainFrame.getSubjectChangeDeEs(subjectWord);
		resultsLbl.setText(Lang.getString("resultsLblSubject") + "  ' " + subjectWord + " '");
		resultsTerm.setText(Lang.getString("resultsTerm"));
								
		// Buttons entfernen
		for (int i = 0; i < zeilenanzahl; i++)
		{
			searchPanel.remove(termButtons.get(i));
		}
		termButtons = null;
		
		// Buttons neu erstellen
		createSearchResult();
		searchPanel.setPreferredSize(new Dimension(1100, lengthSite));
	}
	
	@Override
	public void paint(Graphics g)
	{
		// Vertikale Linien Zeichen ins Panel
		super.paint(g);
		g.setColor(Color.MAGENTA);
		g.drawLine(50, 125, 1250, 125);
	}

	
// Listener
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Begriffsbutton wurde geklickt und der Eintrag wird in Dialog dargestellt
		mainFrame.termWindow_openTermWindow(e.getActionCommand(), subjectWord);
		
	}

}
