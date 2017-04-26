package Abschlussprojekt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.metal.MetalScrollBarUI;

import Util.WinUtil;

public class TermWindow extends JDialog implements WindowListener, ActionListener, DocumentListener
{
	private MainFrame mainFrame;
	
	private JLabel 				TermWinDescriptionLabel, TermWinLang, TermWinLang2, TermWinTerm, TermWinSubject, TermWinText;
	private JTextArea 			tfTermWinTermLang, tfTermWinTermLang2, tfTermWinSubjectLang, tfTermWinSubjectLang2;
	private JTextArea 			taTermWinTextDE, taTermWinTextES;
	private JScrollPane 		spTermWinDE, spTermWinES, spTerm1, spTerm2, spSubject1, spSubject2;
	private JButton 			termEditBtn, saveBtn, abortBtn;
	private String				tempTA1, tempTA2;
	
	private boolean 			hasChangedTerm = false;
	
	private Color 				veryDarkGray = WinUtil.createColor(30, 30, 30);
	private String 				term;

	private Component 			owner;

	private ResourceBundle 		Lang;

	
	/**
	 * <li><b><i>TermWindow</i></b> <br>
	 * <br>
	 * public TermWindow(MainFrame mainFrame, String term)<br>
	 * <br>
	 * Konstruktor für TermWindow<br>
	 * <br>
	 *    
	 * @param mainFrame
	 * 				- Referenz auf MainFrame
	 * 
	 * @param term
	 * 				- Begriff
	 */	
	public TermWindow(MainFrame mainFrame, String term)
	{
		this.mainFrame = mainFrame;
		this.term = term;
		initialize();
	}

	private void initialize()
	{
		// Sprache einstellen
		Lang = mainFrame.getLang();

		// Dialogfenster anpassen
		this.addWindowListener(this);
		this.setLayout(null);
		this.setSize(1200, 700);
		this.setTitle(term);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setBackground(veryDarkGray);

		// Beschreibungslabel
		TermWinDescriptionLabel = WinUtil.createLabel(Lang.getString("TermWinDescriptionLabel") + " ' " + term + " '", 350, 30, 500, 25,
				new EmptyBorder(0, 0, 0, 0), Color.DARK_GRAY, null, null, Color.GRAY);
		TermWinDescriptionLabel.setFont(TermWinDescriptionLabel.getFont().deriveFont(Font.BOLD, 20));
		TermWinDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(TermWinDescriptionLabel);

		// Deutschlabel
		TermWinLang = WinUtil.createLabel(Lang.getString("TermWinLang"), 175, 100, 200, 25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null,
				null, WinUtil.createColor(0, 178, 238));
		TermWinLang.setFont(TermWinLang.getFont().deriveFont(Font.BOLD, 18));
		TermWinLang.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(TermWinLang);

		// SpanischLabel
		TermWinLang2 = WinUtil.createLabel(Lang.getString("TermWinLang2"), 825, 100, 200, 25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238),
				null, null, WinUtil.createColor(0, 178, 238));
		TermWinLang2.setFont(TermWinLang2.getFont().deriveFont(Font.BOLD, 18));
		TermWinLang2.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(TermWinLang2);

		// deutsche Begriffstextarea
		tfTermWinTermLang = new JTextArea();
		tfTermWinTermLang.setMargin(new Insets(3, 3, 0, 3));
		tfTermWinTermLang.setEditable(false);
		tfTermWinTermLang.setBackground(veryDarkGray);
		tfTermWinTermLang.setForeground(Color.WHITE);

		// deutsche Begriffstextarea ScrollPane
		spTerm1 = new JScrollPane(tfTermWinTermLang);
		spTerm1.setBounds(50, 145, 450, 25);
		spTerm1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		spTerm1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.add(spTerm1);

		// Begriffslabel
		TermWinTerm = WinUtil.createLabel(Lang.getString("TermWinTerm"), 450, 145, 300, 25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238), null,
				null, Color.WHITE);
		TermWinTerm.setFont(TermWinTerm.getFont().deriveFont(Font.BOLD, 18));
		TermWinTerm.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(TermWinTerm);

		// spanische Begriffstextarea
		tfTermWinTermLang2 = new JTextArea();
		tfTermWinTermLang2.setMargin(new Insets(3, 3, 0, 3));
		tfTermWinTermLang2.setEditable(false);
		tfTermWinTermLang2.setBackground(veryDarkGray);
		tfTermWinTermLang2.setForeground(Color.WHITE);

		// spanische Begriffstextarea ScrollPane
		spTerm2 = new JScrollPane(tfTermWinTermLang2);
		spTerm2.setBounds(700, 145, 450, 25);
		spTerm2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		spTerm2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.add(spTerm2);

		// deutsche Fachgebietstextarea
		tfTermWinSubjectLang = new JTextArea();
		tfTermWinSubjectLang.setMargin(new Insets(3, 3, 0, 3));
		tfTermWinSubjectLang.setEditable(false);
		tfTermWinSubjectLang.setBackground(veryDarkGray);
		tfTermWinSubjectLang.setForeground(Color.WHITE);

		// deutsche Fachgebietstextarea ScrollPane
		spSubject1 = new JScrollPane(tfTermWinSubjectLang);
		spSubject1.setBounds(50, 195, 450, 25);
		spSubject1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		spSubject1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.add(spSubject1);

		// Fachgebietslabel
		TermWinSubject = WinUtil.createLabel(Lang.getString("TermWinSubject"), 450, 195, 300, 25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238),
				null, null, Color.WHITE);
		TermWinSubject.setFont(TermWinSubject.getFont().deriveFont(Font.BOLD, 18));
		TermWinSubject.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(TermWinSubject);

		// spanische Fachgebietstextarea
		tfTermWinSubjectLang2 = new JTextArea();
		tfTermWinSubjectLang2.setEditable(false);
		tfTermWinSubjectLang2.setBackground(veryDarkGray);
		tfTermWinSubjectLang2.setForeground(Color.WHITE);
		tfTermWinSubjectLang2.setMargin(new Insets(3, 3, 0, 3));

		// spanische Fachgebietstextarea ScrollPane
		spSubject2 = new JScrollPane(tfTermWinSubjectLang2);
		spSubject2.setBounds(700, 195, 450, 25);
		spSubject2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		spSubject2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.add(spSubject2);

		// deutsche Inhaltstextarea
		taTermWinTextDE = new JTextArea();
		taTermWinTextDE.setMargin(new Insets(3, 3, 3, 3));
		taTermWinTextDE.setLineWrap(true);
		taTermWinTextDE.setWrapStyleWord(true);
		taTermWinTextDE.setEditable(false);
		taTermWinTextDE.setBackground(veryDarkGray);
		taTermWinTextDE.setForeground(Color.WHITE);
	
		
		// deutsche Inhaltstextarea ScrollPane
		spTermWinDE = new JScrollPane(taTermWinTextDE);
		spTermWinDE.setBounds(50, 245, 450, 395);
		spTermWinDE.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(spTermWinDE);

		// Beschreibungslabel
		TermWinText = WinUtil.createLabel(Lang.getString("TermWinText"), 450, 245, 300, 25, new EmptyBorder(0, 0, 0, 0), WinUtil.createColor(0, 178, 238),
				"Beschreibung:", "Spa:", Color.WHITE);
		TermWinText.setFont(TermWinText.getFont().deriveFont(Font.BOLD, 18));
		TermWinText.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(TermWinText);

		// spanische Inhaltstextarea
		taTermWinTextES = new JTextArea();
		taTermWinTextES.setMargin(new Insets(3, 3, 3, 3));
		taTermWinTextES.setLineWrap(true);
		taTermWinTextES.setWrapStyleWord(true);
		taTermWinTextES.setEditable(false);
		taTermWinTextES.setBackground(veryDarkGray);
		taTermWinTextES.setForeground(Color.WHITE);
		
		// spanische Inhaltstextarea ScrollPane
		spTermWinES = new JScrollPane(taTermWinTextES);
		spTermWinES.setBounds(700, 245, 450, 395);
		spTermWinES.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(spTermWinES);

		// Editieren Button
		termEditBtn = WinUtil.createButton(	Lang.getString("termEditBtn"), 525, 525, 150, 100, BorderFactory.createLineBorder(WinUtil.createColor(0, 178, 238)), 
											veryDarkGray, this, null, null, false, false, WinUtil.createColor(0, 178, 238));
		this.add(termEditBtn);
		
		// Änderungen speichern Button
		saveBtn = WinUtil.createButton(	Lang.getString("saveChanges"), 525, 525, 150, 40, BorderFactory.createLineBorder(WinUtil.createColor(0, 178, 238)), 
				veryDarkGray, this, null, null, false, false, WinUtil.createColor(0, 178, 238));
		saveBtn.setEnabled(false);
		saveBtn.setVisible(false);
		this.add(saveBtn);
		
		// Editieren abbrechen Button
		abortBtn = WinUtil.createButton(	Lang.getString("abortEditMode"), 525, 585, 150, 40, BorderFactory.createLineBorder(WinUtil.createColor(0, 178, 238)), 
				veryDarkGray, this, null, null, false, false, WinUtil.createColor(0, 178, 238));
		abortBtn.setEnabled(false);
		abortBtn.setVisible(false);
		this.add(abortBtn);
		
		// Daten des ausgewählten Begriffs in den Dialog schreiben 
		insertData();
		
		// Inhalttextarea einen Documentlistener hinzufügen
		taTermWinTextDE.getDocument().addDocumentListener(this);
		taTermWinTextES.getDocument().addDocumentListener(this);
	}

	private void initDialog()
	{
		this.setModal(true);
		this.setLocationRelativeTo(owner);

	}

	/**
	 * <li><b><i>showFrame</i></b> <br>
	 * <br>
	 * public void showFrame() <br>
	 * <br>
	 * Frame initialisieren und sichtbar machen<br>
	 * <br>
	 *  
	 * @param owner
	 *         - MainFrame
	 */
	public void showDialog(Component owner)
	{
		this.owner = owner;
		initDialog();
		this.setVisible(true);
	}

	private void insertData()
	{
		// Kompletter Datensatz von ausgewählten Begriff aus DB holen
		ResultSet termResultSet = mainFrame.getDataTerm(term);
		String[] termArray = getResultList(termResultSet);

		tfTermWinTermLang.setText(termArray[0]);
		tfTermWinTermLang2.setText(termArray[1]);
		tfTermWinSubjectLang.setText(termArray[2]);
		tfTermWinSubjectLang2.setText(termArray[3]);
		taTermWinTextDE.setText(termArray[4]);
		taTermWinTextES.setText(termArray[5]);

	}

	private String[] getResultList(ResultSet termCacheResultSet)
	{
		String[] termArrayCache = new String[6];

		try
		{
			// eine Datenzeile wird in Array geschrieben
			termCacheResultSet.next();
			termArrayCache[0] = (String) termCacheResultSet.getObject(1);
			termArrayCache[1] = (String) termCacheResultSet.getObject(2);
			termArrayCache[2] = (String) termCacheResultSet.getObject(3);
			termArrayCache[3] = (String) termCacheResultSet.getObject(4);
			termArrayCache[4] = (String) termCacheResultSet.getObject(5);
			termArrayCache[5] = (String) termCacheResultSet.getObject(6);

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "getResultList" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return termArrayCache;

	}

	@Override
	public void paint(Graphics g)
	{
		// Vertikale Linien Zeichen ins Panel
		super.paint(g);
		g.setColor(Color.MAGENTA);
		g.drawLine(25, 115, 1175, 115);

	}
	
	private boolean updateTerms()
	{
		// Inhalte des ausgewählten Begriffs in der Datenbank aktualisieren
		boolean retValue = false;
			retValue = mainFrame.updateTermText(tfTermWinTermLang.getText(), taTermWinTextDE.getText(), taTermWinTextES.getText());
		return retValue;
	}

	private boolean queryExit()
	{
		
		// Benutzerdefinierten Button Text
		String[] options = { Lang.getString("speichern"), Lang.getString("verwerfen"), Lang.getString("abort") };
				
		if (!hasChangedTerm)
			return true;
		
		int retValue = JOptionPane.showOptionDialog(this, Lang.getString("frageSpeichern"), Lang.getString("queryExitTitle"), 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
		
		// Nein - nicht speichern, aber Dialog schließen
		if (retValue == JOptionPane.NO_OPTION)
			return true;
		
		// Abbruch - Dialog witrd nicht geschlossen
		if (retValue != JOptionPane.YES_OPTION)
			return false;
		
		
		// Ja - Speichern
		return updateTerms();
		
	}
	
	private boolean queryEdit()
	{
		// Benutzerdefinierten Button Text
		String[] options =
		{ Lang.getString("close"), Lang.getString("abort") };

		if (!hasChangedTerm)
			return true;
		
		int retValue = JOptionPane.showOptionDialog(this, Lang.getString("queryEdit"), Lang.getString("queryExitTitle"),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

		// YES = Schließen - Edit schließen
		if (retValue == JOptionPane.YES_OPTION)
			return true;
		// NO = Abbrechen
		return false;
	}
	
	private boolean querySave()
	{
		// Benutzerdefinierten Button Text
		String[] options =
		{ Lang.getString("speichern"), Lang.getString("abort") };

		if (!hasChangedTerm)
			return true;
		
		int retValue = JOptionPane.showOptionDialog(this, Lang.getString("speichernDaten"), Lang.getString("speichernDatenTitle"),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

		// YES = Schließen - Edit schließen
		if (retValue == JOptionPane.YES_OPTION)
			return true;
		// NO = Abbrechen
		return false;
	}
	
	private void activateEdit()
	{
		// Elemente anpassen wenn der editor Modus aktiviert wird
		taTermWinTextDE.setEditable(true);
		taTermWinTextDE.setBackground(Color.WHITE);
		taTermWinTextDE.setForeground(Color.BLACK);
		tempTA1 = taTermWinTextDE.getText();
		taTermWinTextES.setEditable(true);
		taTermWinTextES.setBackground(Color.WHITE);
		taTermWinTextES.setForeground(Color.BLACK);
		tempTA2 = taTermWinTextES.getText();
		saveBtn.setEnabled(true);
		saveBtn.setVisible(true);
		abortBtn.setEnabled(true);
		abortBtn.setVisible(true);
		termEditBtn.setEnabled(false);
		termEditBtn.setVisible(false);
	}
	
	private void abortEdit()
	{
		// Elemente anpassen wenn der editor Modus deaktiviert wird
		taTermWinTextDE.setEditable(false);
		taTermWinTextDE.setBackground(veryDarkGray);
		taTermWinTextDE.setForeground(Color.WHITE);
		taTermWinTextDE.setText(tempTA1);
		taTermWinTextES.setEditable(false);
		taTermWinTextES.setBackground(veryDarkGray);
		taTermWinTextES.setForeground(Color.WHITE);
		taTermWinTextES.setText(tempTA2);
		saveBtn.setEnabled(false);
		saveBtn.setVisible(false);
		abortBtn.setEnabled(false);
		abortBtn.setVisible(false);
		termEditBtn.setEnabled(true);
		termEditBtn.setVisible(true);
	}
	
	private void saveEdit()
	{
		if (!updateTerms())
		{
			JOptionPane.showMessageDialog(this, Lang.getString("cannotSave"), Lang.getString("errorTitle"), JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(this, Lang.getString("updateToDb"), Lang.getString("updateSuccessTitle"), JOptionPane.INFORMATION_MESSAGE);
			taTermWinTextDE.setEditable(false);
			taTermWinTextDE.setBackground(veryDarkGray);
			taTermWinTextDE.setForeground(Color.WHITE);
			taTermWinTextES.setEditable(false);
			taTermWinTextES.setBackground(veryDarkGray);
			taTermWinTextES.setForeground(Color.WHITE);
			saveBtn.setEnabled(false);
			saveBtn.setVisible(false);
			abortBtn.setEnabled(false);
			abortBtn.setVisible(false);
			termEditBtn.setEnabled(true);
			termEditBtn.setVisible(true);
			hasChangedTerm = false;
		}
	}
		
	
// Listener

	@Override
	public void windowActivated(WindowEvent arg0)
	{
	
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
	
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if (queryExit())
		{
			this.dispose();
			mainFrame.StatusBar_ChangeVisibleLabels();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
	
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
	
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{

	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
	
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Editier Button gedrückt
		if (e.getSource() == termEditBtn)
		{
			activateEdit();			
		}
		
		// Editier Modus abbrechen Button gedrückt
		if (e.getSource() == abortBtn)
		{
			if (queryEdit())
				abortEdit();
		}
		
		// Änderungen speichern Button gedrückt
		if (e.getSource() == saveBtn)
		{
			if (querySave())
				saveEdit();
		}
		
	}

	@Override
	public void changedUpdate(DocumentEvent arg0)
	{
		hasChangedTerm = true;
	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		hasChangedTerm = true;
	}

	@Override
	public void removeUpdate(DocumentEvent arg0)
	{
		hasChangedTerm = true;
	}

}
