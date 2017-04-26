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
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalScrollBarUI;

import Util.WinUtil;

public class LetterResults extends JScrollPane implements ActionListener
{
	private MainFrame 				mainFrame;
	private JPanel 					searchPanel;
	private JLabel 					resultsLbl, resultsTerm, resultsSubject;
	
	private JScrollBar 				scrollbar;
	private JViewport				scrollPaneVP;
	private int 					scrollBarSize = ((Integer) UIManager.get("ScrollBar.width")).intValue();
	private String					letterWord;
	private int 					lengthSite;
	private int 					zeilenanzahl;

	private final int 				BGColor = 30;
	
	private ResourceBundle 			Lang;
	
	private ArrayList<JButton>		termButtons;
	private ArrayList<JLabel>	 	subjectLabels;
	
	/**
	 * <li><b><i>LetterResults</i></b> <br>
	 * <br>
	 * public LetterResults(MainFrame mainFrame, String letterWord)<br>
	 * <br>
	 * Konstruktor für LetterResults<br>
	 * <br>
	 *  
	 * @param mainFrame
	 * 				- Referenz auf MainFrame
	 *
	 * @param letterWord
	 * 				- Buchstabe
	 */	
	public LetterResults(MainFrame mainFrame, String letterWord)
	{
		this.mainFrame = mainFrame;
		this.letterWord = letterWord;
		initialize();
	}

	private void initialize()
	{
		
		scrollPaneVP = this.getViewport();
		
		scrollPaneVP.addChangeListener(e -> repaint());
		
		// auf ScrollBar zugreifen und Farbe ändern
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
		resultsLbl = WinUtil.createLabel(Lang.getString("resultsLblLetter") + "  ' " + letterWord.substring(0, 1) + " '", 1300 / 2 - 625 - scrollBarSize, 30, 1250, 100,
				new EmptyBorder(0, 0, 0, 0), Color.DARK_GRAY, null, null, Color.GRAY);
		resultsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		resultsLbl.setFont(resultsLbl.getFont().deriveFont(Font.BOLD, 30));
		searchPanel.add(resultsLbl);
		
		// Begriffs Label
		resultsTerm = WinUtil.createLabel(Lang.getString("resultsTerm"), 1300 / 3 - 100 - scrollBarSize, 150, 200,
				25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null, null,
				WinUtil.createColor(0, 178, 238));
		resultsTerm.setFont(resultsTerm.getFont().deriveFont(Font.BOLD, 18));
		resultsTerm.setHorizontalAlignment(SwingConstants.CENTER);
		searchPanel.add(resultsTerm);
		
		// Fachgebiet Label
		resultsSubject = WinUtil.createLabel(Lang.getString("resultsSubject"), (1300 / 3)*2 - 100 - scrollBarSize, 150, 200, 25,
				new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null, null,
				WinUtil.createColor(0, 178, 238));
		resultsSubject.setFont(resultsSubject.getFont().deriveFont(Font.BOLD, 18));
		resultsSubject.setHorizontalAlignment(SwingConstants.CENTER);
		searchPanel.add(resultsSubject);

		// Button und Labels anhand des Anfangsbuchstaben erstellen
		createSearchResult();
		
		// Größe des AnzeigePanels entsprechend der Anzahl der Begriffseinträge einstellen (ScrollPane richtet sich nach PreferredSize) 
		searchPanel.setPreferredSize(new Dimension(1100, lengthSite));
		
		// AnzeigePanels der ScrollPane hinzufügen
		this.getViewport().add(searchPanel);

	}
	
	
		
	private void createSearchResult()
	{
		// Resultset von aktueller Fachgebiete Tabelle in DB
		ResultSet letterResultSet = mainFrame.getDataLetterResult(letterWord);
					
		zeilenanzahl = mainFrame.getRowCount(letterResultSet);
		
		// Speichert Suchergebnis (besteht aus Begriff, Fachgebiet) 
		String[][] resultListArray = getResultList(zeilenanzahl, letterResultSet);
		
		//ArrayLists nehmen Referenzen von Buttons und Labels auf
		termButtons = new ArrayList<JButton>();
		subjectLabels = new ArrayList<JLabel>();
						
		// Position der Elemente per Scheife erzeugen	
		for (int j = 0; j < zeilenanzahl; j++)
		{
							
			// Button für Begriffe werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			termButtons.add(WinUtil.createButton(	resultListArray[0][j], 1300 / 3 - 200 - scrollBarSize, 200+j*50, 400, 25, BorderFactory.createLineBorder(WinUtil.createColor(100, 100, 100)), 
													WinUtil.createColor(BGColor, BGColor, BGColor), 
													this, null, null, false, false, WinUtil.createColor(100, 100, 100)));
			termButtons.get(j).setHorizontalAlignment(SwingConstants.CENTER);
			termButtons.get(j).setFont(termButtons.get(j).getFont().deriveFont(Font.BOLD,18));
			searchPanel.add(termButtons.get(j));
			
			// Labels für Fachbereiche werden aus resultListArray erzeugt und in eine ArrayList gespeichert
			subjectLabels.add(WinUtil.createLabel(	resultListArray[1][j], (1300 / 3)*2 - 200 - scrollBarSize, 200+j*50, 400, 25, new EmptyBorder(0, 0, 0, 0), 
													Color.DARK_GRAY, null, null, WinUtil.createColor(100, 100, 100)));
			subjectLabels.get(j).setHorizontalAlignment(SwingConstants.CENTER);
			subjectLabels.get(j).setFont(subjectLabels.get(j).getFont().deriveFont(Font.BOLD,18));
			searchPanel.add(subjectLabels.get(j));
			
			// benötigte Länge des JPanels -> Scrollbar notwendig oder nicht
			lengthSite = 250+j*50;
							
		}
										
		
	}
			
	private String[][] getResultList(int zeilen, ResultSet letterResultSet)
	{
		
		
		String[][] searchResultArray = new String[2][zeilen];
		
		try
		{
			// Begriffe und Fachgebiete (eine Sprache) werden in ein 2D Array geschrieben							
			for(int i = 0; i < zeilen; i++)
			{
				letterResultSet.next();
				searchResultArray[0][i] = (String)letterResultSet.getObject(1);
				searchResultArray[1][i] = (String)letterResultSet.getObject(2);
			}
		
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "getResultList: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			
		}
		return searchResultArray;
		
	}
		
	/**
	 * <li><b><i>switchLang</i></b> <br>
	 * <br>
	 * public void switchLang()<br>
	 * <br>
	 * Ändert alle nötigen Komponenten von LetterResult bei Sprachwechsel oder neue Eintrag<br>
	 * <br>
	 *  
	 */	
	public void switchLang()
	{
		// Alle Beschriftungen werden geändert
		Lang = mainFrame.getLang();
		resultsLbl.setText(Lang.getString("resultsLblLetter") + "  ' " + letterWord.substring(0, 1) + " '");
		resultsTerm.setText(Lang.getString("resultsTerm"));
		resultsSubject.setText(Lang.getString("resultsSubject"));
						
		// Buttons entfernen
		for (int i = 0; i < zeilenanzahl; i++)
		{
			searchPanel.remove(termButtons.get(i));
			searchPanel.remove(subjectLabels.get(i));
		
		}
		termButtons = null;
		subjectLabels = null;
		
		// Buttons und Labels neu erstellen
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
		return retValue;
	}
	
// Listener
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Begriffsbutton wurde geklickt und der Eintrag wird in Dialog dargestellt
		mainFrame.termWindow_openTermWindow(e.getActionCommand(), getSubject(e.getActionCommand()));
	}

}
