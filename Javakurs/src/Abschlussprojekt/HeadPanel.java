package Abschlussprojekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import Util.ListItem;
import Util.WinUtil;

public class HeadPanel extends JPanel implements ActionListener, FocusListener, KeyListener
{
	private JComboBox<ListItem> 			cboSearch, cboLang;
	private DefaultComboBoxModel<ListItem> 	cboDefaultSearchModel, cboDefaultLangChooseModel;
	private DefaultListModel<ListItem> 		listBoxModel;
	private MainFrame						mainFrame;
	
	private JButton[]						alphabetBtns = new JButton[26]; 
	private JLabel 							headDescriptionLabel, separatorLbl;
	private JPanel							backgroundPanel;
	private final int						COLOR_BG_PANEL = 80;
	
	private JButton							subjectsBtn, DeBtn, EsBtn, newEntryBtn, searchBtn;
	private Color							ButtonColor;

			
	private ResourceBundle					Lang;

	
	/**
	 * <li><b><i>HeadPanel</i></b> <br>
	 * <br>
	 * public HeadPanel(MainFrame mainFrame)<br>
	 * <br>
	 * Konstruktor für HeadPanel<br>
	 * <br>
	 *    
	 * @param mainFrame
	 * 				- Referenz auf MainFrame
	 */	
	public HeadPanel(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		initialize();
	}

	private void initialize()
	{
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1300, 100));
		this.setBackground(Color.DARK_GRAY);
		
		//Sprache setzen
		Lang = mainFrame.getLang();
				
		// Farbe der Button
		ButtonColor = WinUtil.createColor(200, 200, 200);
						
		// ComboSuchBox mit History
		cboDefaultSearchModel = new DefaultComboBoxModel<ListItem>();
		cboSearch = new JComboBox<>(cboDefaultSearchModel);
		cboSearch.setBounds(40, 22, 260, 25);
		cboSearch.setEditable(true);
		cboSearch.getEditor().getEditorComponent().addKeyListener(this);
		cboSearch.getEditor().getEditorComponent().addFocusListener(this);
		cboSearch.getEditor().getEditorComponent().setBackground(Color.LIGHT_GRAY);
		cboSearch.getEditor().getEditorComponent().setForeground(WinUtil.createColor(50, 50, 50));
		this.add(cboSearch);
		
		// ComboBox wird mit Suchhistory gefüllt
		writeSearchWordsFromDbToHistory();
		((JTextComponent)cboSearch.getEditor().getEditorComponent()).setText("");
				
		// Such-Button
		searchBtn = WinUtil.createButton(	Lang.getString("searchBtn"), 40, 57, 260, 26, BorderFactory.createLineBorder(Color.BLACK),
											Color.DARK_GRAY, this, null, null, false, false, Color.WHITE);
		this.add(searchBtn);
		
		// Beschreibungslabel
		headDescriptionLabel = WinUtil.createLabel(	Lang.getString("headDescriptionLabel"), 401, 10, 500, 25, new EmptyBorder(0, 0, 0, 0), Color.DARK_GRAY, 
													null, null, Color.WHITE);
		headDescriptionLabel.setFont(headDescriptionLabel.getFont().deriveFont(Font.BOLD,13));
		headDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(headDescriptionLabel);
				
		// Alphabet darstellen
		drawAlphabet();
		
		// Verfügbare Buchstaben verlinken
		checkAvailabilityLetters();
		
		// Fachgebiete darstellen
		subjectsBtn = WinUtil.createButton(	Lang.getString("subjectsBtn"), 1300/2 - 100, 68, 200, 20, BorderFactory.createLineBorder(Color.GRAY), Color.DARK_GRAY, 
											this, null, null, false, false, ButtonColor);
		this.add(subjectsBtn);
		
		// HintergrundPanel
		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(390, 8, 520, 85);
		backgroundPanel.setBackground(WinUtil.createColor(COLOR_BG_PANEL, COLOR_BG_PANEL, COLOR_BG_PANEL));
		this.add(backgroundPanel);
		
		// Sprachauswahlbuttons
		DeBtn = WinUtil.createButton("DE", 1200, 10, 25, 25, new EmptyBorder(0, 0, 0, 0), Color.BLACK, this, "DE", "DE", false, false, Color.WHITE);
		this.add(DeBtn);
		separatorLbl = WinUtil.createLabel("/", 1230, 10, 5, 25, new EmptyBorder(0, 0, 0, 0), Color.DARK_GRAY, "/", "/", Color.WHITE);
		this.add(separatorLbl);
		EsBtn = WinUtil.createButton("ES", 1238, 10, 25, 25, new EmptyBorder(0, 0, 0, 0), Color.DARK_GRAY, this, "ES", "ES", false, false, Color.WHITE);
		this.add(EsBtn);
				
		// Neuer Eintrag Button
		newEntryBtn = WinUtil.createButton(	Lang.getString("newEntryBtn"), 1000, 50, 260, 30, BorderFactory.createLineBorder(Color.BLACK),
											Color.DARK_GRAY, this, null, null, false, false, Color.WHITE);
		this.add(newEntryBtn);
		
		// den jenigen Sprachbutton umrahmen der übereinstimmt mit aktueller Sprache
		if (mainFrame.getCurrentLocale().toString().equals("de"))
			DeBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		else
			EsBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}

	/**
	 * <li><b><i>writeSearchWordsFromDbToHistory</i></b> <br>
	 * <br>
	 * public void writeSearchWordsFromDbToHistory()<br>
	 * <br>
	 * Schreibt Suchwörter von der Datenbank in die Combobox<br>
	 * <br>
	 *    
	 */	
	public void writeSearchWordsFromDbToHistory()
	{
		// bei Programmstart wird Suchhistory aus der DB ausgelesen und in Combobox eingefügt
		ResultSet history = mainFrame.getDataHistory();
		int zeilenanzahl = mainFrame.getRowCount(history);
		cboDefaultSearchModel.removeAllElements();
		
		try
		{
			for(int i = 0; i < zeilenanzahl; i++)
			{
				history.next();
				cboDefaultSearchModel.addElement(new ListItem(cboDefaultSearchModel.getSize(), (String)history.getObject(1)));
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "getHistoryStrings: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
		
	private void drawAlphabet()
	{
		// Schleife die A...Z Button in alphabet schreibt und als Button im HeadPanel darstellt
		for (int i = 0; i <= 25; i++)
		{
			char letter = (char)(65 + i);
			int j = i*18;
		
			alphabetBtns[i] = WinUtil.createButton(	String.valueOf(letter), 420+j , 40, 14, 20, BorderFactory.createLineBorder(Color.GRAY), 
													Color.DARK_GRAY, this, String.valueOf(letter), String.valueOf(letter), false, false, 
													ButtonColor);
			alphabetBtns[i].setActionCommand(String.valueOf(letter)+ "%");
			this.add(alphabetBtns[i]);
		}
	}

	private void checkAvailabilityLetters()
	{
		// Checkt ob es Begriffseinträge mit entsprechenden Anfangsbuchstaben gibt -> wenn ja: Buchstaben umrahmen
		for (int i = 0; i <= 25; i++)
		{
			String searchLetter = alphabetBtns[i].getActionCommand();
			
			boolean isLetterAvailable = mainFrame.isLetterAvailable(searchLetter);
									
			if (isLetterAvailable)
			{
				alphabetBtns[i].setEnabled(true);
				alphabetBtns[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
				alphabetBtns[i].setFocusable(true);
			}
			else
			{
				alphabetBtns[i].setEnabled(false);
				alphabetBtns[i].setBorder(new EmptyBorder(0, 0, 0, 0));
				alphabetBtns[i].setFocusable(false);
			}
					
		}
		
	}

	/**
	 * <li><b><i>switchLang</i></b> <br>
	 * <br>
	 * public void switchLang()<br>
	 * <br>
	 * Ändert alle nötigen Komponenten von Headpanel bei Sprachwechsel oder neue Eintrag<br>
	 * <br>
	 *    
	 */	
	public void switchLang()
	{
		// Alle Beschriftungen werden geändert
		Lang = mainFrame.getLang();
		searchBtn.setText(Lang.getString("searchBtn"));
		headDescriptionLabel.setText(Lang.getString("headDescriptionLabel"));
		subjectsBtn.setText(Lang.getString("subjectsBtn"));
		newEntryBtn.setText(Lang.getString("newEntryBtn"));
		
		// Welche Buchstaben sind in der entsprechenden Sprache verfügbar
		checkAvailabilityLetters();
		
	}
	
	/**
	 * <li><b><i>changeLangBtn</i></b> <br>
	 * <br>
	 * public void changeLangBtn(String locale)<br>
	 * <br>
	 * Ändert die Umrahmung der Sprachbutton entsprechend aktueller Sprache<br>
	 * <br>
	 *    
	 */
	public void changeLangBtn(String locale)
	{
		if (mainFrame.getCurrentLocale().toString().equals("de"))
		{
			DeBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			EsBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		}
		else
		{
			EsBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			DeBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		}
	}
	
	
	private boolean isCharacterAllowed(char c, JTextComponent tf)
	{
		// Prüfung der Eingabe in der Suchleiste (wildcard dürfen immer nur am Anfang oder Ende eines Wortes stehen)
		// wenn versucht wird ein Buchstabe davor/dahinter einzufügen wird es abgelehnt
		// nur Buchstaben dürfen eingefügt werden
		
		boolean retValue = false;
		int textLength = tf.getText().length();
		
		if (textLength > 1)
		{
			String textStart = tf.getText().substring(0, 1);
			String textEnd = tf.getText().substring(textLength-1, textLength);
			int caretPosition = tf.getCaretPosition();
			String wildcard = "%";
			
			if (textStart.equals("%") && caretPosition == 0)
				return false;
			if (textEnd.equals("%") && caretPosition == textLength)
				return false;
		}
		
		// nur Buchstaben und * ist erlaubt
		
		if (Character.isAlphabetic(c))
			return true;
		else if ( c == '%')
			retValue = true;
		
		
		return retValue;
		
	}
	
	private boolean isPositionAllowed(JTextComponent tf)
	{
		// Prüfung, ob Position für das Einfügen der Wildcard (%) okay ist (wildcard dürfen immer nur am Anfang oder Ende eines Wortes stehen)
		
		int textLength = tf.getText().length();
				
		// Leere Feld
		if(textLength == 0)
			return true;
		
		String textStart = tf.getText().substring(0, 1);
		String textEnd = tf.getText().substring(textLength-1, textLength);
		int caretPosition = tf.getCaretPosition();
		String wildcard = "%";
		
		// Nur eine Wildcard als einziges Zeichen
		if(textLength == 1 && tf.getText().contains("%"))
			return false;
		
		// ist Wildcard schon vorhanden?
		if (textStart.equals(wildcard) && textEnd.equals(wildcard)) 
			return false;
		if (textStart.equals(wildcard) && caretPosition == 0)
			return false;
		if (textEnd.equals(wildcard) && caretPosition == textLength)
			return false;
		
		// Wildcard darf nur am Anfang oder Ende hinzugefügt werden
		if (caretPosition == 0 || caretPosition == textLength)
			return true;
		
		return false;
	}
	
	private void searchStart()
	{
		// Startet die Suche
		String searchWord = ((JTextComponent)cboSearch.getEditor().getEditorComponent()).getText();
		
		// Suchfenster wird im CENTER geöffnet
		mainFrame.all_ActivateSearchWindow(searchWord);
		
		// Suchbegriff in DB geschrieben
		mainFrame.insertDbHistory(searchWord);
		
		// Combobox aktualisieren			
		writeSearchWordsFromDbToHistory();
		((JTextComponent)cboSearch.getEditor().getEditorComponent()).setText("");
		
		// HistoryButton aktivieren
		mainFrame.getMiHistory().setEnabled(true);
	}
	
	
// Listener

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Fachgebiete Button gedrückt
		if (e.getSource() == subjectsBtn)
		{
			mainFrame.StartPanel_ActiveStartPanel();
		}
		
		// Neuer Eintrag Button gedrückt
		if (e.getSource() == newEntryBtn)
		{
			// Modaler Dialog
			newEntryDialog dlg = new newEntryDialog(mainFrame);
			dlg.showDialog(mainFrame);
			// Dialogreferenz wird gelöscht, wenn geschlossen wurde
			dlg = null;
		}
		
		// Such Button gedrückt
		if (e.getSource() == searchBtn && !((JTextComponent)cboSearch.getEditor().getEditorComponent()).getText().equals(""))
		{
			searchStart();
		}
		
		// Deutsche Sprache Button gedrückt
		if (e.getSource() == DeBtn && mainFrame.getCurrentLocale().toString().equals("es"))
		{
			mainFrame.all_ActivteLanguageBtn("de");
			DeBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			EsBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		}
		
		// Deutsche Sprache Button gedrückt
		if (e.getSource() == EsBtn && mainFrame.getCurrentLocale().toString().equals("de"))
		{
			mainFrame.all_ActivteLanguageBtn("es");
			EsBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			DeBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		}
		
		// ABC Button (zweites Zeichen des ActionCommand = '%')
		if (e.getSource() instanceof JButton && ((JButton)e.getSource()).getActionCommand().substring(1, 2).equals("%"))
		{
			// Buchstabenfenster wird im CENTER geöffnet
			mainFrame.letterResults_OpenLetterWindow(((JButton)e.getSource()).getActionCommand());
		}
		
	}

	
	@Override
	public void keyPressed(KeyEvent e)
	{
		// 
		if (e.getKeyCode() == KeyEvent.VK_ENTER && !((JTextComponent)cboSearch.getEditor().getEditorComponent()).getText().equals(""))
				KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
		// %s% delete vor s verbieten
		if (e.getKeyCode() == KeyEvent.VK_DELETE && 
				((JTextComponent)e.getSource()).getText().length() == 3 &&
				((JTextComponent)e.getSource()).getText().substring(0, 1).equals("%") &&
				((JTextComponent)e.getSource()).getText().substring(2, 3).equals("%") &&
				((JTextComponent)e.getSource()).getCaretPosition() == 1) 
			e.consume();
		// %s% backspace nach s verbieten
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && 
				((JTextComponent)e.getSource()).getText().length() == 3 &&
				((JTextComponent)e.getSource()).getText().substring(0, 1).equals("%") &&
				((JTextComponent)e.getSource()).getText().substring(2, 3).equals("%") &&
				((JTextComponent)e.getSource()).getCaretPosition() == 2) 
			e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if (e.getSource().equals(cboSearch.getEditor().getEditorComponent()))
		{
			if (Character.isISOControl(e.getKeyChar())) //Ignoriert Steuerkommandos
				return;
			
			
			 if (!isCharacterAllowed(e.getKeyChar(), (JTextComponent)e.getSource()))
			 {
				 Toolkit.getDefaultToolkit().beep();
				 e.consume();
				 return;
			 }
			 
			 if (e.getKeyChar() == '%')
				 if(!isPositionAllowed((JTextComponent)e.getSource()))
					e.consume();	 
		}

	}

	@Override
	public void focusGained(FocusEvent e)
	{
		if (e.getSource() instanceof JTextComponent)
			((JTextComponent)e.getSource()).selectAll();
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		// searchStart();
	}
}
