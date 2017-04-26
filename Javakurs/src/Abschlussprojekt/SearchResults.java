package Abschlussprojekt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalScrollBarUI;

import Util.WinUtil;

public class SearchResults extends JScrollPane implements ActionListener
{
	private MainFrame 				mainFrame;
	private JPanel 					searchPanel;
	private JLabel 					resultsLbl, resultsTerm, resultsSubject, resultsTermLang2, resultsSubjectLang2, lang1, lang2;
	
	private JScrollBar 				scrollbar;
	private JViewport				scrollPaneVP;
	
	private final int				BAR_POSITION = 125;
	private int						barYPosition = BAR_POSITION;
	
	private int 					scrollBarSize = ((Integer) UIManager.get("ScrollBar.width")).intValue();
	private String					searchWord;
	private int 					lengthSite;
	private int 					zeilenanzahl, zeilenanzahlLang2, counterLang2;
	private boolean 				noData;

	private final int 				BGColor = 30;

	private Locale 					currentLocale;
	private ResourceBundle 			Lang;
	
	private ArrayList<JButton>		termButtons;
	private ArrayList<JLabel>		subjectLabels, termLang2Labels, subjectLang2Labels;
	

	/**
	 * <li><b><i>SearchResults</i></b> <br>
	 * <br>
	 * SearchResults(MainFrame mainFrame, String searchWord)<br>
	 * <br>
	 * Konstruktor für Suchergebnis Panel<br>
	 * <br>
	 *    
	 * @param mainFrame
	 * 				- Referenz auf MainFrame
	 * 
	 * @param searchWord
	 * 				- Suchwort
	 */	
	public SearchResults(MainFrame mainFrame, String searchWord)
	{
		this.mainFrame = mainFrame;
		this.searchWord = searchWord;
		
		initialize();
	}

	private void initialize()
	{
		
		// Linie repainten
		scrollPaneVP = this.getViewport();
		scrollPaneVP.addChangeListener(e -> repaintBar());
		
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
		resultsLbl = WinUtil.createLabel(Lang.getString("resultsLbl") + "  ' " + searchWord + " '", 1300 / 2 - 625 - scrollBarSize, 30, 1250, 100,
				new EmptyBorder(0, 0, 0, 0), Color.DARK_GRAY, null, null, Color.GRAY);
		resultsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resultsLbl.setFont(resultsLbl.getFont().deriveFont(Font.BOLD, 30));
		searchPanel.add(resultsLbl);

		// Sprache1 Label
		lang1 = WinUtil.createLabel(Lang.getString("TermWinLang"), (1300 / 10)*3 - 100 - scrollBarSize, 150, 200,
				25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null, null,
				WinUtil.createColor(0, 178, 238));
		lang1.setFont(lang1.getFont().deriveFont(Font.BOLD, 18));
		lang1.setHorizontalAlignment(SwingConstants.CENTER);
		searchPanel.add(lang1);
		
		// Sprache2 Label
		lang2 = WinUtil.createLabel(Lang.getString("TermWinLang2"), (1300 / 10)*7 - 100 - scrollBarSize, 150, 200,
				25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null, null,
				WinUtil.createColor(0, 178, 238));
		lang2.setFont(lang2.getFont().deriveFont(Font.BOLD, 18));
		lang2.setHorizontalAlignment(SwingConstants.CENTER);
		searchPanel.add(lang2);
				
		// Begriffs Label Sprache1
		resultsTerm = WinUtil.createLabel(Lang.getString("resultsTerm"), (1300 / 5) - 100 - scrollBarSize, 200, 200,
				25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null, null,
				Color.WHITE);
		resultsTerm.setFont(resultsTerm.getFont().deriveFont(Font.BOLD, 18));
		resultsTerm.setHorizontalAlignment(SwingConstants.CENTER);
		searchPanel.add(resultsTerm);
		
		// Fachgebiets Label Sprache1
		resultsSubject = WinUtil.createLabel(Lang.getString("resultsSubject"), (1300 / 5 )*2 -100 - scrollBarSize, 200, 200, 25,
				new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null, null,
				Color.WHITE);
		resultsSubject.setFont(resultsSubject.getFont().deriveFont(Font.BOLD, 18));
		resultsSubject.setHorizontalAlignment(SwingConstants.CENTER);
		searchPanel.add(resultsSubject);
		
		// Begriffs Label Sprache2
		resultsTermLang2 = WinUtil.createLabel(Lang.getString("resultsTerm"), (1300 / 5)*3 - 100 - scrollBarSize, 200, 200,
				25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null, null,
				Color.WHITE);
		resultsTermLang2.setFont(resultsTermLang2.getFont().deriveFont(Font.BOLD, 18));
		resultsTermLang2.setHorizontalAlignment(SwingConstants.CENTER);
		searchPanel.add(resultsTermLang2);
		
		// Fachgebiets Label Sprache1
		resultsSubjectLang2 = WinUtil.createLabel(Lang.getString("resultsSubject"), (1300 / 5 )*4 -100 - scrollBarSize, 200, 200, 25,
				new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null, null,
				Color.WHITE);
		resultsSubjectLang2.setFont(resultsSubjectLang2.getFont().deriveFont(Font.BOLD, 18));
		resultsSubjectLang2.setHorizontalAlignment(SwingConstants.CENTER);
		searchPanel.add(resultsSubjectLang2);

		// noData: gab es ein Suchergebnis?
		noData = false;
		
		// Button und Labels anhand des Ergebnis der Suche erstellen
		createSearchResult();
		
		// Größe des AnzeigePanels entsprechend der Anzahl der Begriffseinträge einstellen (ScrollPane richtet sich nach PreferredSize) 
		searchPanel.setPreferredSize(new Dimension(1100, lengthSite));
		
		// AnzeigePanels der ScrollPane hinzufügen
		this.getViewport().add(searchPanel);
		
	}
	
	private void repaintBar()
	{
		int actualYPositon = (int)scrollPaneVP.getViewPosition().getY();
		if (actualYPositon < BAR_POSITION)
		{
			barYPosition = BAR_POSITION - actualYPositon;
			repaint();
		}
	}
	
	private void createSearchResult()
	{
		// Resultsets von aktueller Fachgebiete Tabelle in DB (Suche erst deutsche Begriffe dann spanische Begriffe)
		ResultSet searchResultSet = mainFrame.getDataSearchResult(searchWord);
		ResultSet searchResultSetLang2 = mainFrame.getDataSearchResultLang2(searchWord);
					
		zeilenanzahl = mainFrame.getRowCount(searchResultSet);
		zeilenanzahlLang2 = mainFrame.getRowCount(searchResultSetLang2);
		
		// Suchbegriff stimmt mit keinen Daten überein
		if (zeilenanzahl == 0 && zeilenanzahlLang2 == 0)
		{
			// Elemente ausblenden welche nicht gebraucht werden
			resultsLbl.setText(Lang.getString("noData"));
			noData = true;
			lang1.setVisible(false);
			lang2.setVisible(false);
			resultsTerm.setVisible(false);
			resultsSubject.setVisible(false);
			resultsTermLang2.setVisible(false);
			resultsSubjectLang2.setVisible(false);
			return;
		}
		
		// alle Elemente einblenden
		lang1.setVisible(true);
		lang2.setVisible(true);
		resultsTerm.setVisible(true);
		resultsSubject.setVisible(true);
		resultsTermLang2.setVisible(true);
		resultsSubjectLang2.setVisible(true);
		
		// Speichert Suchergebnis (besteht aus Begriff, Fachgebiet) 
		String[][] resultListArray = getResultList(zeilenanzahl, searchResultSet);
		String[][] resultListArrayLang2 = getResultList(zeilenanzahlLang2, searchResultSetLang2);
			
		//ArrayLists nehmen Referenzen von Buttons und Labels auf
		termButtons = new ArrayList<JButton>();
		subjectLabels = new ArrayList<JLabel>();
		termLang2Labels = new ArrayList<JLabel>();
		subjectLang2Labels = new ArrayList<JLabel>();
						
		// Position der Elemente per Scheife erzeugen (Suchergebnisse der ersten Sprache)	
		for (int j = 0; j < zeilenanzahl; j++)
		{
							
			// Button für Begriffe werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			termButtons.add(WinUtil.createButton(	resultListArray[0][j], 1300 / 5 - 150 - scrollBarSize, 250+j*50, 300, 25, BorderFactory.createLineBorder(WinUtil.createColor(100, 100, 100)), 
													WinUtil.createColor(BGColor, BGColor, BGColor), 
													this, null, null, false, false, WinUtil.createColor(100, 100, 100)));
			termButtons.get(j).setHorizontalAlignment(SwingConstants.CENTER);
			termButtons.get(j).setFont(termButtons.get(j).getFont().deriveFont(Font.BOLD,18));
			searchPanel.add(termButtons.get(j));
			
			// Labels für Fachgebiete werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			subjectLabels.add(WinUtil.createLabel(	resultListArray[1][j], (1300 / 5 )*2 - 150 - scrollBarSize, 250+j*50, 300, 25, new EmptyBorder(0, 0, 0, 0), 
													Color.DARK_GRAY, null, null, WinUtil.createColor(100, 100, 100)));
			subjectLabels.get(j).setHorizontalAlignment(SwingConstants.CENTER);
			subjectLabels.get(j).setFont(subjectLabels.get(j).getFont().deriveFont(Font.BOLD,18));
			searchPanel.add(subjectLabels.get(j));
			
			// Labels für Begriffe 2. Sprache werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			termLang2Labels.add(WinUtil.createLabel(	resultListArray[2][j], (1300 / 5 )*3 - 150 - scrollBarSize, 250+j*50, 300, 25, new EmptyBorder(0, 0, 0, 0), 
													Color.DARK_GRAY, null, null, WinUtil.createColor(100, 100, 100)));
			termLang2Labels.get(j).setHorizontalAlignment(SwingConstants.CENTER);
			termLang2Labels.get(j).setFont(termLang2Labels.get(j).getFont().deriveFont(Font.BOLD,18));
			searchPanel.add(termLang2Labels.get(j));
			
			// Labels für Fachgebiete 2. Sprache werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			subjectLang2Labels.add(WinUtil.createLabel(	resultListArray[3][j], (1300 / 5 )*4 - 150 - scrollBarSize, 250+j*50, 300, 25, new EmptyBorder(0, 0, 0, 0), 
													Color.DARK_GRAY, null, null, WinUtil.createColor(100, 100, 100)));
			subjectLang2Labels.get(j).setHorizontalAlignment(SwingConstants.CENTER);
			subjectLang2Labels.get(j).setFont(subjectLang2Labels.get(j).getFont().deriveFont(Font.BOLD,18));
			searchPanel.add(subjectLang2Labels.get(j));
				
			lengthSite = 250+(j)*50;
		}
		
		// counterLang2 = wieviele Begriffe von Sprache 2 hinzufügt werden
		counterLang2 = 0;
		
		// Position der Elemente per Scheife erzeugen (Suchergebnisse der zweite Sprache, Problem: 2. Sprache soll Buttons bekommen)	
		for (int j = 0; j < (zeilenanzahlLang2); j++)
		{
			
			// Testet ob Begriff in der ersten Sprache schon aufgezählt wurde
			boolean breakVar = false;
			for (int i = 0; i < zeilenanzahl; i++)
			{
				String BegriffLang1 = resultListArray[0][i];
				String BegriffLang2 = resultListArrayLang2[0][j];
				if 	(BegriffLang2.equals(BegriffLang1))
				{
					breakVar = true;
					break;
				}
			}
			
			if (breakVar == true)
				continue;
			
			
			// Button für Begriffe werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			termButtons.add(WinUtil.createButton(	resultListArrayLang2[2][j], (1300 / 5) * 3 - 150 - scrollBarSize, 250+(counterLang2+zeilenanzahl)*50, 300, 25, BorderFactory.createLineBorder(WinUtil.createColor(100, 100, 100)), 
													WinUtil.createColor(BGColor, BGColor, BGColor), 
													this, null, null, false, false, WinUtil.createColor(100, 100, 100)));
			termButtons.get(counterLang2+zeilenanzahl).setHorizontalAlignment(SwingConstants.CENTER);
			termButtons.get(counterLang2+zeilenanzahl).setFont(termButtons.get(counterLang2+zeilenanzahl).getFont().deriveFont(Font.BOLD,18));
			termButtons.get(counterLang2+zeilenanzahl).setActionCommand(resultListArrayLang2[0][j]);
			searchPanel.add(termButtons.get(counterLang2+zeilenanzahl));
			
			// Labels für Fachgebiete werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			subjectLabels.add(WinUtil.createLabel(	resultListArrayLang2[1][j], (1300 / 5 )*2 - 150 - scrollBarSize, 250+(counterLang2+zeilenanzahl)*50, 300, 25, new EmptyBorder(0, 0, 0, 0), 
													Color.DARK_GRAY, null, null, WinUtil.createColor(100, 100, 100)));
			subjectLabels.get(counterLang2+zeilenanzahl).setHorizontalAlignment(SwingConstants.CENTER);
			subjectLabels.get(counterLang2+zeilenanzahl).setFont(subjectLabels.get(counterLang2+zeilenanzahl).getFont().deriveFont(Font.BOLD,18));
			searchPanel.add(subjectLabels.get(counterLang2+zeilenanzahl));
			
			// Labels für Begriffe 2. Sprache werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			termLang2Labels.add(WinUtil.createLabel(	resultListArrayLang2[0][j], (1300 / 5 ) - 150 - scrollBarSize, 250+(counterLang2+zeilenanzahl)*50, 300, 25, new EmptyBorder(0, 0, 0, 0), 
													Color.DARK_GRAY, null, null, WinUtil.createColor(100, 100, 100)));
			termLang2Labels.get(counterLang2+zeilenanzahl).setHorizontalAlignment(SwingConstants.CENTER);
			termLang2Labels.get(counterLang2+zeilenanzahl).setFont(termLang2Labels.get(counterLang2+zeilenanzahl).getFont().deriveFont(Font.BOLD,18));
			searchPanel.add(termLang2Labels.get(counterLang2+zeilenanzahl));
			
			// Labels für Fachgebiete 2. Sprache werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			subjectLang2Labels.add(WinUtil.createLabel(	resultListArrayLang2[3][j], (1300 / 5 )*4 - 150 - scrollBarSize, 250+(counterLang2+zeilenanzahl)*50, 300, 25, new EmptyBorder(0, 0, 0, 0), 
													Color.DARK_GRAY, null, null, WinUtil.createColor(100, 100, 100)));
			subjectLang2Labels.get(counterLang2+zeilenanzahl).setHorizontalAlignment(SwingConstants.CENTER);
			subjectLang2Labels.get(counterLang2+zeilenanzahl).setFont(subjectLang2Labels.get(counterLang2+zeilenanzahl).getFont().deriveFont(Font.BOLD,18));
			searchPanel.add(subjectLang2Labels.get(counterLang2+zeilenanzahl));
						
			// benötigte Länge des JPanels -> Scrollbar notwendig oder nicht
			lengthSite = 250+(counterLang2+zeilenanzahl)*50;
			
			counterLang2++;
							
		}
		
										
		
	}
	
		
	private String[][] getResultList(int zeilen, ResultSet searchResultSet)
	{
		
		
		String[][] searchResultArray = new String[4][zeilen];
		
		try
		{
			// Begriffe und Fachgebiete (eine Sprache) werden in ein 2D Array geschrieben							
			for(int i = 0; i < zeilen; i++)
			{
				searchResultSet.next();
				searchResultArray[0][i] = (String)searchResultSet.getObject(1);
				searchResultArray[1][i] = (String)searchResultSet.getObject(2);
				searchResultArray[2][i] = (String)searchResultSet.getObject(3);
				searchResultArray[3][i] = (String)searchResultSet.getObject(4);
			}
		
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "getResultList: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			
		}
		return searchResultArray;
		
	}

	
	@Override
	public void paint(Graphics g)
	{
		// Vertikale Linien Zeichen ins Panel
		super.paint(g);
		g.setColor(Color.MAGENTA);
		g.drawLine(100, barYPosition, 1200, barYPosition);
		
	}
	
	/**
	 * <li><b><i>switchLang</i></b> <br>
	 * <br>
	 * public void switchLang()<br>
	 * <br>
	 * Ändert alle nötigen Komponenten von SearchResult bei Sprachwechsel oder neue Eintrag<br>
	 * <br>
	 *  
	 */	
	public void switchLang()
	{
		// Alle Beschriftungen werden geändert
		Lang = mainFrame.getLang();
		resultsLbl.setText(Lang.getString("resultsLbl") + "  ' " + searchWord + " '");
		lang1.setText(Lang.getString("TermWinLang"));
		lang2.setText(Lang.getString("TermWinLang2"));
		resultsTerm.setText(Lang.getString("resultsTerm"));
		resultsSubject.setText(Lang.getString("resultsSubject"));
		resultsTermLang2.setText(Lang.getString("resultsTerm"));
		resultsSubjectLang2.setText(Lang.getString("resultsSubject"));
		
		
		if (noData)
			resultsLbl.setText(Lang.getString("noData"));
		
		// Buttons entfernen (Sprache1)
		for (int i = 0; i < zeilenanzahl; i++)
		{
			searchPanel.remove(termButtons.get(i));
			searchPanel.remove(subjectLabels.get(i));
			searchPanel.remove(termLang2Labels.get(i));
			searchPanel.remove(subjectLang2Labels.get(i));
		
		}
		
		// Buttons entfernen (Sprache2)
		for (int i = zeilenanzahl; i < zeilenanzahl+counterLang2; i++)
		{
			searchPanel.remove(termButtons.get(i));
			searchPanel.remove(subjectLabels.get(i));
			searchPanel.remove(termLang2Labels.get(i));
			searchPanel.remove(subjectLang2Labels.get(i));
		
		}
		termButtons = null;
		subjectLabels = null;
		
		// Buttons und Labels neu erstellen
		createSearchResult();
		searchPanel.setPreferredSize(new Dimension(1100, lengthSite));
		
	}
	
	private String getSubject(String actionCommand)
	{
		// Ermittelt Name Fachgebiet des ausgewählten Begriffsbutton für TermWindow bzw zum Anzeigen in der Statusbar
		String retValue = "";
		for(int i = 0; i < zeilenanzahl; i++)
		{
			if (termButtons.get(i).getText() == actionCommand)
			{
				retValue = subjectLabels.get(i).getText();
				break;
			}
		}
		// Teil für Sprache2
		for(int i = zeilenanzahl; i < zeilenanzahl+counterLang2; i++)
		{
			if (termButtons.get(i).getActionCommand() == actionCommand)
			{
				retValue = subjectLabels.get(i).getText();
				break;
			}
		}
		
		return retValue;
	}
	
// Listener
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Begriffsbutton wurde geklickt und der Eintrag wird in Dialog dargestellt
		mainFrame.termWindow_openTermWindow(e.getActionCommand(),getSubject(e.getActionCommand()));	
	}

}
