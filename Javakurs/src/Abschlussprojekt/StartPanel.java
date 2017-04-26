package Abschlussprojekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalScrollBarUI;

import Util.WinUtil;
import Abschlussprojekt.DBConnection;
import Abschlussprojekt.Globals;

public class StartPanel extends JScrollPane implements ActionListener
{
	private MainFrame						mainFrame;
	private JPanel 							startPanel;
	private JLabel 							welcomeLbl, introductionLbl, subjectLbl;
	
	private ArrayList<JButton>				subjectsNames;
	private int								zeilenanzahl;
	private int 							lengthSite;
	private JScrollBar 						scrollbar;
	private int 							scrollBarSize = ((Integer) UIManager.get("ScrollBar.width")).intValue();
	 
	private ResourceBundle 					Lang;
	
	private final int 						BGColor = 30, introductionLblBGColor = 70, introductionLblFGColor = 210;

	/**
	 * <li><b><i>StartPanel</i></b> <br>
	 * <br>
	 * public StartPanel(MainFrame mainFrame)<br>
	 * <br>
	 * Konstruktor für StartPanel<br>
	 * <br>
	 *    
	 * @param mainFrame
	 * 				- Referenz auf MainFrame
	 * 
	 */	
	public StartPanel(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		
		initialize();
	}

	private void initialize()
	{
		// ScrollBar zugreifen und Farbe ändern
		System.out.println("ScrollBarV: "+ScrollBar.VERTICAL);
		scrollbar = new JScrollBar(ScrollBar.VERTICAL);
		scrollbar.setUI(new MyScrollBarUI());
		this.setVerticalScrollBar(scrollbar);
		
		// Sprache einstellen
		Lang = mainFrame.getLang();
		
		// AnzeigePanel für Scrollpane initialisieren
		startPanel = new JPanel();		
		startPanel.setLayout(null);
		startPanel.setBackground(WinUtil.createColor(BGColor, BGColor, BGColor));
		
		// Willkommenslabel
		welcomeLbl = WinUtil.createLabel(Lang.getString("welcomeLbl"), 150, 30, 1000, 100, new EmptyBorder(0, 0, 0, 0), Color.DARK_GRAY, null, null, Color.WHITE);
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLbl.setFont(welcomeLbl.getFont().deriveFont(Font.BOLD, 30));
		startPanel.add(welcomeLbl);
		
		// Kurze Einführung (per Html)	
		introductionLbl = WinUtil.createLabel(	Lang.getString("introductionLbl"), 100, 150, 1100, 100, new EmptyBorder(0, 18, 0, 5), 
												WinUtil.createColor(introductionLblBGColor, introductionLblBGColor, introductionLblBGColor), 
												null, null, WinUtil.createColor(introductionLblFGColor, introductionLblFGColor, introductionLblFGColor));
		introductionLbl.setOpaque(true);
		introductionLbl.setFont(introductionLbl.getFont().deriveFont(Font.PLAIN,18));
		startPanel.add(introductionLbl);
		
		// Fachgebiete Label
		subjectLbl = WinUtil.createLabel(Lang.getString("subjectLbl"), 1300/2-115, 300, 200, 25, new EmptyBorder(0, 0, 0, 0), Color.DARK_GRAY, null, null, Color.WHITE);
		subjectLbl.setFont(introductionLbl.getFont().deriveFont(Font.BOLD,18));
		subjectLbl.setHorizontalAlignment(SwingConstants.CENTER);
		startPanel.add(subjectLbl);
		
		// Auflistung der Fachbegebiete
		createSubjects();
		
		// Größe des AnzeigePanels entsprechend der Anzahl der Begriffseinträge einstellen (ScrollPane richtet sich nach PreferredSize) 			
		startPanel.setPreferredSize(new Dimension(1100, lengthSite));
		
		// AnzeigePanels der ScrollPane hinzufügen
		this.getViewport().add(startPanel);

	}

	private void createSubjects()
	{
		// Resultset von aktueller Fachgebiete Tabelle in DB
		ResultSet fachgebiete = mainFrame.getDataFachgebiete();
		zeilenanzahl = mainFrame.getRowCount(fachgebiete);
		
		// Speichert Deutsche/Spanische Fachgebiete in einem 2D Array
		String[] subjectsNameArray = Globals.getDBColumnAsStringArray(zeilenanzahl, fachgebiete);
		
		subjectsNames = new ArrayList<JButton>();
		
		//Anzahl der darzustellenden Zeilen
		int numberDisplayedLines = zeilenanzahl/3;
		if (zeilenanzahl%3 != 0)
			numberDisplayedLines++;
		
		int b = 0;
		for (int i = 0; i < numberDisplayedLines; i++)
		{
			
			for (int j = 0; j < 4; j++)
			{
				// Anzahl der geschrieben Fachgebiete ist genauso groß wie in Array enthaltene Fachgebiete
				if (i*4+j == subjectsNameArray.length)
				{
					b = 1;
					break;
				}
				int index = i*4+j;
				
				// Button für Fachgebiete werden aus NamensArray erzeugt und in eine ArrayList gespeichert
				subjectsNames.add(WinUtil.createButton(	subjectsNameArray[index], 120+j*270 - scrollBarSize, 360+i*50, 250, 25, BorderFactory.createLineBorder(WinUtil.createColor(0, 178, 238)), 
														WinUtil.createColor(BGColor, BGColor, BGColor), 
														this, null, null, false, false, 
														WinUtil.createColor(0, 178, 238)));
				subjectsNames.get(index).setHorizontalAlignment(SwingConstants.HORIZONTAL);
				subjectsNames.get(index).setFont(subjectsNames.get(index).getFont().deriveFont(Font.BOLD,18));
				startPanel.add(subjectsNames.get(index));
				
				// benötigte Länge des JPanels -> Scrollbar notwendig oder nicht
				lengthSite = 360+i*50;
								
			}
			if (b == 1)
			break;
		}										
		
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		// Vertikale Linien Zeichen ins Panel
		super.paint(g);
		g.setColor(Color.MAGENTA);
		g.drawLine(150, 125, 1150, 125);
		// g.drawLine(150, 275, 1100, 275);
	}
			
	/**
	 * <li><b><i>switchLang</i></b> <br>
	 * <br>
	 * public void switchLang()<br>
	 * <br>
	 * Ändert alle nötigen Komponenten von StartPanel bei Sprachwechsel oder neue Eintrag<br>
	 * <br>
	 *  
	 */
	public void switchLang()
	{
		// Alle Beschriftungen werden geändert
		Lang = mainFrame.getLang();
		welcomeLbl.setText(Lang.getString("welcomeLbl"));
		introductionLbl.setText(Lang.getString("introductionLbl"));
		subjectLbl.setText(Lang.getString("subjectLbl"));
		
		// Buttons entfernen
		for (int i = 0; i < zeilenanzahl; i++)
		{
			startPanel.remove(subjectsNames.get(i));
		}
		subjectsNames = null;
		
		// Buttons und Labels neu erstellen
		createSubjects();
		startPanel.setPreferredSize(new Dimension(1100, lengthSite));
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Begriffsbutton wurde geklickt und der Eintrag wird in Dialog dargestellt
		mainFrame.subjectResults_openSubjectsWindow(e.getActionCommand());
	}

}
