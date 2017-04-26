package Abschlussprojekt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Util.WinUtil;

/**
 * @author Alfa
 *
 */
public class ProjectStatusBar extends JMenuBar
{
	private MainFrame			mainFrame;
	private JButton 			startpageBtn, subjectBtn, termBtn;
	private JLabel				subjectTierLbl, termTierLbl, freeSpace;

	
	private String				subjectDE, termDE;
	private static enum 		Status	{START, SUBJECT, TERM}
	private static Status		status = Status.START;
	
	private ResourceBundle 		Lang;
	
	private final int			VERY_LIGHT_GREY = 150;
		
	/**
	 * <li><b><i>ProjectStatusBar</i></b> <br>
	 * <br>
	 * public ProjectStatusBar(MainFrame mainFrame)<br>
	 * <br>
	 * Konstruktor für Statusbar<br>
	 * <br>
	 *    
	 * @param mainFrame
	 * 				- Referenz auf MainFrame
	 */	
	public ProjectStatusBar(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setBackground(Color.DARK_GRAY);
		initialize();
	}
	
	private void initialize()
	{
		// Sprache einstellen
		Lang = mainFrame.getLang();
		
		// Sehr helles Grau umwandeln von RGB zu HSB
		Color VeryLightGrey = WinUtil.createColor(VERY_LIGHT_GREY, VERY_LIGHT_GREY, VERY_LIGHT_GREY);
				
		// Buttons
		startpageBtn = WinUtil.createButton(Lang.getString("startpageBtn"), 0, 0, 0, 0, new EmptyBorder(0, 5, 0, 5), Color.DARK_GRAY, 
											e -> mainFrame.StartPanel_ActiveStartPanel(), null, null, false, false, Color.WHITE);
		startpageBtn.setHorizontalAlignment(SwingConstants.CENTER);
		subjectBtn = WinUtil.createButton(	subjectDE, 0, 0, 0, 0, new EmptyBorder(0, 5, 0, 5), Color.DARK_GRAY, null, 
											null, null, false, false, Color.WHITE);
		subjectBtn.setHorizontalAlignment(SwingConstants.CENTER);
		subjectBtn.setEnabled(false);
		subjectBtn.setFocusable(false);
		termBtn = WinUtil.createButton(	termDE, 0, 0, 0, 0, new EmptyBorder(0, 5, 0, 5), Color.DARK_GRAY, null,
										null, null, false, false, Color.WHITE);
		termBtn.setHorizontalAlignment(SwingConstants.CENTER);
		termBtn.setEnabled(false);
		termBtn.setFocusable(false);
		
		//Labels
		subjectTierLbl = WinUtil.createLabel(" >> ", 0, 0, 0, 0, new EmptyBorder(0, 15, 0, 15), Color.DARK_GRAY, " >> ", " >> ", VeryLightGrey);
		subjectTierLbl.setHorizontalAlignment(SwingConstants.CENTER);
		termTierLbl = WinUtil.createLabel(" >> ", 0, 0, 0, 0, new EmptyBorder(0, 15, 0, 15), Color.DARK_GRAY, " >> ", " >> ", VeryLightGrey);
		termTierLbl.setHorizontalAlignment(SwingConstants.CENTER);
		freeSpace = WinUtil.createLabel("", 0, 0, 0, 0, new EmptyBorder(0, 600, 0, 5), null, " >> ", " >> ", null);
		
		// Hinzufügen der Buttons und Labels
		this.add(freeSpace);
		this.add(startpageBtn);
		this.add(subjectTierLbl);
		this.add(subjectBtn);
		this.add(termTierLbl);
		this.add(termBtn);
		
		// Initialstatus setzen
		setStatus("start", null, null);
		
	}
	
	/**
	 * <li><b><i>getCurrentSubject</i></b> <br>
	 * <br>
	 * public String getCurrentSubject()<br>
	 * <br>
	 * Gibt den Text des Fachgebietebuttons in der Statusleiste zurück<br>
	 * <br>
	 *    
	 * @return String
	 * 				- Text des Fachgebietebuttons in der Statusleiste
	 */
	public String getCurrentSubject()
	{
		String retValue = subjectBtn.getText();
		return retValue;
	}
	
	
	/**
	 * <li><b><i>setStatus</i></b> <br>
	 * <br>
	 * public void setStatus(String tier, String subject, String term)<br>
	 * <br>
	 * Die entsprechenden Panels setzten Status in StatusBar<br>
	 * <br>
	 *    
	 * @param tier
	 * 				- Bis zu welcher Ebene aktivierten
	 * 
	 * @param subject
	 * 				- Fachgebietsname der gesetzt werden soll
	 * 
	 * @param term
	 * 				- Begriffsname der gesetzt werden soll
	 */
	public void setStatus(String tier, String subject, String term)
	{
		// Nur Startseiten Ebene aktivieren
		if (tier.equals("start"))
		{
			status= status.START;
			if (mainFrame.getCurrentLocale().toString().equals("de"))
				freeSpace.setBorder(new EmptyBorder(0, 600, 0, 5));
			else
				freeSpace.setBorder(new EmptyBorder(0, 575, 0, 5));
			subjectTierLbl.setVisible(false);
			subjectBtn.setVisible(false);
			termTierLbl.setVisible(false);
			termBtn.setVisible(false);
		}
		
		// Startseiten Ebene und Fachgebiete Ebene aktivieren
		if (tier.equals("subject"))
		{
			status= status.SUBJECT;
			this.subjectBtn.setText(subject);
			this.subjectTierLbl.setVisible(true);
			this.subjectBtn.setVisible(true);
			if (mainFrame.getCurrentLocale().toString().equals("de"))
				freeSpace.setBorder(new EmptyBorder(0, 550, 0, 5));
			else
				freeSpace.setBorder(new EmptyBorder(0, 525, 0, 5));
			termTierLbl.setVisible(false);
			termBtn.setVisible(false);
		}
		
		// Startseiten Ebene, Fachgebiete und Begriffs Ebene aktivieren
		if (tier.equals("term"))
		{
			status= status.TERM;
			this.subjectBtn.setText(subject);
			this.termBtn.setText(term);
			this.subjectTierLbl.setVisible(true);
			this.subjectBtn.setVisible(true);
			this.termTierLbl.setVisible(true);
			this.termBtn.setVisible(true);
			if (mainFrame.getCurrentLocale().toString().equals("de"))
				freeSpace.setBorder(new EmptyBorder(0, 500, 0, 5));
			else
				freeSpace.setBorder(new EmptyBorder(0, 475, 0, 5));
		}
	}
	
	/**
	 * <li><b><i>switchLang</i></b> <br>
	 * <br>
	 * public void switchLang()<br>
	 * <br>
	 * Ändert alle nötigen Komponenten von der Statusbar bei Sprachwechsel oder neue Eintrag<br>
	 * <br>
	 *    
	 */	
	public void switchLang()
	{
		// Alle Beschriftungen werden geändert
		Lang = mainFrame.getLang();
		startpageBtn.setText(Lang.getString("startpageBtn"));
		
		if (mainFrame.getCurrentLocale().toString().equals("de"))
			freeSpace.setBorder(new EmptyBorder(0, 600, 0, 5));
		else
			freeSpace.setBorder(new EmptyBorder(0, 575, 0, 5));
					
		if (status == status.SUBJECT)
		{
			subjectBtn.setText(mainFrame.getSubjectChangeDeEs(subjectBtn.getText()));
			if (mainFrame.getCurrentLocale().toString().equals("de"))
				freeSpace.setBorder(new EmptyBorder(0, 550, 0, 5));
			else
				freeSpace.setBorder(new EmptyBorder(0, 525, 0, 5));
		}
		if (status == status.TERM)
		{
			subjectBtn.setText(mainFrame.getSubjectChangeDeEs(subjectBtn.getText()));
			termBtn.setText(mainFrame.getTermChangeDeEs(termBtn.getText()));
			if (mainFrame.getCurrentLocale().toString().equals("de"))
				freeSpace.setBorder(new EmptyBorder(0, 500, 0, 5));
			else
				freeSpace.setBorder(new EmptyBorder(0, 475, 0, 5));
		}
				
	}	
		
}
