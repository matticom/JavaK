package Abschlussprojekt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.text.JTextComponent;

import Util.ListItem;
import Util.WinUtil;

public class newEntryDialog extends JDialog implements KeyListener, FocusListener, ActionListener, WindowListener, ItemListener
{
	private MainFrame 				mainFrame;
	private Component 				owner;
	private final int 				BGColor = 30;

	private JLabel 					dlgDescriptionLabel, dlgDeutsch, dlgSpanisch, dlgterm, dlgsubject, dlgText, dlgInfo;
	private JTextField 				tfTermDE, tfTermES, tfSubjectDE, tfSubjectES;
	private JTextArea 				taTextDE, taTextES;
	private JScrollPane 			spDE, spES;
	private JButton 				insertBtn;
	
	private JComboBox						cboSubjectDE, cboSubjectES;
	private DefaultComboBoxModel<ListItem> 	cboModelDE, cboModelES;
	
	private int						zeilenanzahl;

	private ResourceBundle 			Lang;

	/**
	 * <li><b><i>newEntryDialog</i></b> <br>
	 * <br>
	 * public newEntryDialog(MainFrame mainFrame)<br>
	 * <br>
	 * Konstruktor für Neuer Eintrag Dialog<br>
	 * <br>
	 *    
	 * @param mainFrame
	 * 				- Referenz auf MainFrame
	 */	
	public newEntryDialog(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		initialize();
	}

	private void initialize()
	{
		// Sprache einstellen
		Lang = mainFrame.getLang();

		this.setLayout(null);
		this.setTitle(Lang.getString("newEntry"));
		this.setSize(1200, 700);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		this.setResizable(false);
		this.getContentPane().setBackground(WinUtil.createColor(BGColor, BGColor, BGColor));

		// Beschreibungslabel
		dlgDescriptionLabel = WinUtil.createLabel(	Lang.getString("dlgDescriptionLabel"), 350, 30, 500, 25,
													new EmptyBorder(0, 0, 0, 0), Color.DARK_GRAY, null, null, Color.GRAY);
		dlgDescriptionLabel.setFont(dlgDescriptionLabel.getFont().deriveFont(Font.BOLD, 20));
		dlgDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(dlgDescriptionLabel);

		// Deutschlabel
		dlgDeutsch = WinUtil.createLabel(	Lang.getString("dlgDeutsch"), 125, 100, 200, 25, new EmptyBorder(0, 0, 0, 0),
											WinUtil.createColor(0, 178, 238), null, null, WinUtil.createColor(0, 178, 238));
		dlgDeutsch.setFont(dlgDeutsch.getFont().deriveFont(Font.BOLD, 18));
		dlgDeutsch.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(dlgDeutsch);

		// SpanischLabel
		dlgSpanisch = WinUtil.createLabel(Lang.getString("dlgSpanisch"), 875, 100, 200, 25, new EmptyBorder(0, 0, 0, 0),
				WinUtil.createColor(0, 178, 238), null, null, WinUtil.createColor(0, 178, 238));
		dlgSpanisch.setFont(dlgDeutsch.getFont().deriveFont(Font.BOLD, 18));
		dlgSpanisch.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(dlgSpanisch);

		// deutsches Begriffstextfeld
		tfTermDE = new JTextField();
		tfTermDE.setBounds(50, 180, 350, 25);
		tfTermDE.addKeyListener(this);
		tfTermDE.addFocusListener(this);
		this.getContentPane().add(tfTermDE);

		// Begriffs Label
		dlgterm = WinUtil.createLabel(	Lang.getString("dlgterm"), 450, 180, 300, 25, new EmptyBorder(0, 0, 0, 0),
										WinUtil.createColor(0, 178, 238), null, null, Color.WHITE);
		dlgterm.setFont(dlgterm.getFont().deriveFont(Font.BOLD, 18));
		dlgterm.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(dlgterm);

		// spanisches Begriffstextfeld
		tfTermES = new JTextField();
		tfTermES.setBounds(800, 180, 350, 25);
		tfTermES.addKeyListener(this);
		tfTermES.addFocusListener(this);
		this.getContentPane().add(tfTermES);

		
//		ComboBox
		
		cboModelDE = new DefaultComboBoxModel<ListItem>();
		cboSubjectDE = new JComboBox<>(cboModelDE);
		cboSubjectDE.setBounds(50, 230, 350, 25);
		cboSubjectDE.addKeyListener(this);
		cboSubjectDE.addFocusListener(this);
		this.getContentPane().add(cboSubjectDE);
		
//		// deutsche Fachgebietstextfeld
//		tfSubjectDE = new JTextField();
//		tfSubjectDE.setBounds(50, 230, 350, 25);
//		tfSubjectDE.addKeyListener(this);
//		tfSubjectDE.addFocusListener(this);
//		this.getContentPane().add(tfSubjectDE);

		// Fachgebiet Label
		dlgsubject = WinUtil.createLabel(	Lang.getString("dlgsubject"), 450, 230, 300, 25, new EmptyBorder(0, 0, 0, 0),
											WinUtil.createColor(0, 178, 238), null, null, Color.WHITE);
		dlgsubject.setFont(dlgterm.getFont().deriveFont(Font.BOLD, 18));
		dlgsubject.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(dlgsubject);

		
		cboModelES = new DefaultComboBoxModel<ListItem>();
		cboSubjectES = new JComboBox<>(cboModelES);
		cboSubjectES.setBounds(800, 230, 350, 25);
		cboSubjectES.addKeyListener(this);
		cboSubjectES.addFocusListener(this);
		this.getContentPane().add(cboSubjectES);
		
		
//		// spanische Fachgebietstextfeld
//		tfSubjectES = new JTextField();
//		tfSubjectES.setBounds(800, 230, 350, 25);
//		tfSubjectES.addKeyListener(this);
//		tfSubjectES.addFocusListener(this);
//		this.getContentPane().add(tfSubjectES);

		// deutsche Inhaltstextarea
		taTextDE = new JTextArea();
		taTextDE.setMargin(new Insets(3, 3, 3, 3));
		taTextDE.setLineWrap(true);
		taTextDE.setWrapStyleWord(true);

		// deutsche ScrollPane für deutsche Inhaltstextarea
		spDE = new JScrollPane(taTextDE);
		spDE.setBounds(50, 280, 350, 360);
		this.getContentPane().add(spDE);

		// Beschreibung Label
		dlgText = WinUtil.createLabel(Lang.getString("dlgText"), 450, 280, 300, 25, new EmptyBorder(0, 0, 0, 0),
				WinUtil.createColor(0, 178, 238), null, null, Color.WHITE);
		dlgText.setFont(dlgText.getFont().deriveFont(Font.BOLD, 18));
		dlgText.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(dlgText);

		// spanische Inhaltstextarea
		taTextES = new JTextArea();
		taTextES.setMargin(new Insets(3, 3, 3, 3));
		taTextES.setLineWrap(true);
		taTextES.setWrapStyleWord(true);

		// spanische ScrollPane für spanische Inhaltstextarea
		spES = new JScrollPane(taTextES);
		spES.setBounds(800, 280, 350, 360);
		this.getContentPane().add(spES);

		// Info Label unter Beschreibungslabel

		dlgInfo = WinUtil.createLabel(Lang.getString("Info"), 425, 450, 350, 100, new EmptyBorder(0, 0, 0, 0),
				WinUtil.createColor(0, 178, 238), null, null, Color.GRAY);
		dlgInfo.setFont(dlgInfo.getFont().deriveFont(Font.PLAIN, 14));
		dlgInfo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(dlgInfo);

		
		// Insertbutton
		insertBtn = WinUtil.createButton(Lang.getString("insertBtn"), 450, 400, 300, 30,
				BorderFactory.createLineBorder(WinUtil.createColor(0, 178, 238)), Color.DARK_GRAY, this,
				null, null, false, false, WinUtil.createColor(0, 178, 238));
		this.getContentPane().add(insertBtn);
		
		// Comboboxen füllen
		fillCombos();
	}

	private void fillCombos()
	{
		ResultSet fachgebiete = Globals.getFachgebieteDE();
		ResultSet especialidades = Globals.getFachgebieteES();
		zeilenanzahl = mainFrame.getRowCount(fachgebiete);
		
		// Speichert Deutsche/Spanische Fachgebiete in einem 2D Array
		String[] subjectsNameArrayDE = Globals.getDBColumnAsStringArray(zeilenanzahl, fachgebiete);
		String[] subjectsNameArrayES = Globals.getDBColumnAsStringArray(zeilenanzahl, especialidades);
		
		int i=0;
		for(String subject:subjectsNameArrayDE)
			cboModelDE.addElement(new ListItem(i++,subject));
		
		i=0;
		for(String subject:subjectsNameArrayES)
			cboModelES.addElement(new ListItem(i++,subject));
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

	
// Logik

	private boolean queryExit()
	{
		// Benutzerdefinierten Button Text
		String[] options =
		{ Lang.getString("close"), Lang.getString("abort") };

		int retValue = JOptionPane.showOptionDialog(this, Lang.getString("queryExit"), Lang.getString("queryExitTitle"),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

		// YES = Schließen - Dialog schließen
		if (retValue == JOptionPane.YES_OPTION)
			return true;
		// NO = Abbrechen
		return false;
	}

	private boolean isRightState()
	{
		// Wurden alle Felder ausgefüllt mit der Einschränkung, dass ein Fachgebietsfeld nicht ausgefüllt werden muss, wenn das Fachgebiet schon vorhanden isz
		int tfTermDElength = tfTermDE.getText().length();
		int tfTermESlength = tfTermES.getText().length();
		int tfSubjectDElength = tfSubjectDE.getText().length();
		int tfSubjectESlength = tfSubjectES.getText().length();
		int taTextDElength = taTextDE.getText().length();
		int taTextESlength = taTextES.getText().length();

		// Die Felder von Begriff und Beschreibung müssen in beiden Sprachen
		// ausgefüllt sein
		if (!(tfTermDElength != 0 && tfTermESlength != 0 && taTextDElength != 0 && taTextESlength != 0))
		{
			
			JOptionPane.showMessageDialog(this, Lang.getString("eingabeUnvoll"), Lang.getString("errorTitle"), JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Das Fachgebiet einer Sprache muss nicht ausgefüllt werden, wenn es
		// bereits vorhanden ist (andere Wert ist bereits in der Datenbank)
		boolean kombi1 = (tfSubjectDElength != 0 && tfSubjectESlength == 0);
		boolean kombi2 = (tfSubjectDElength == 0 && tfSubjectESlength != 0);

		if (kombi1 || kombi2)
		{
			if (isNewSubject())
			{
				JOptionPane.showMessageDialog(this, Lang.getString("needNewFachgebiet"), Lang.getString("fehlerFachgebiet"), JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		// Prüfen, ob beide Fachgebietsfeld leer sind
		if (tfSubjectDElength == 0 && tfSubjectESlength == 0)
		{
			JOptionPane.showMessageDialog(this, Lang.getString("needNurEinFachgebiet"), Lang.getString("errorTitle"), JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private boolean isNewEntry()
	{
		if (isRightState())
		{
			// Ist der Begriff bereits im Lexikon?
			if (mainFrame.isDoubleLexikon(tfTermDE.getText()))
			{
				JOptionPane.showMessageDialog(this, Lang.getString("begriffSchonVorhanden"), Lang.getString("errorTitle"), JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		}
		return false;
	}

	private boolean isInsertDone()
	{
		int PKeySubject = -1;

		// Ein neuer Eintrag?
		if (isNewEntry())
		{
			// Muss ein neues Fachgebiet erstellt werden?
			if (isNewSubject())
			{
				// neues Fachgebiet anlegen
				if (!insertNewSubject())
					return false;
				// PrimaryKey vom neuen Fachgebiete Eintrag erhalten
				PKeySubject = mainFrame.getPrimaryKeyFachgebietDE(tfSubjectDE.getText());

			}
			else
			{
				// PrimaryKey vom vorhandenen Fachgebiete Eintrag erhalten
				// (entweder DE oder ES Feld)
				if (tfSubjectDE.getText().length() != 0)
					PKeySubject = mainFrame.getPrimaryKeyFachgebietDE(tfSubjectDE.getText());
				else
					PKeySubject = mainFrame.getPrimaryKeyFachgebietES(tfSubjectES.getText());
			}

			if (!insertNewEntry(PKeySubject))
				return false;

		}
		// kein neuer Eintrag: Fehler wird in isNewEntry generiert
		return true;
	}

	private boolean isNewSubject()
	{
		if (tfSubjectDE.getText().length() != 0)
		{
			// deutsche Fachgebietsfeld: ist Name schon vorhanden?
			if (mainFrame.isDoubleFachgebietDE(tfSubjectDE.getText()))
				return false;
			return true;
		}
		else
		{
			// spanische Fachgebietsfeld: ist Name schon vorhanden?
			if (mainFrame.isDoubleFachgebietES(tfSubjectES.getText()))
				return false;
			return true;
		}

	}

	private boolean insertNewEntry(int PKey)
	{
		if (mainFrame.insertDbLexikon(tfTermDE.getText(), tfTermES.getText(), PKey, taTextDE.getText(),
				taTextES.getText()))
			return true;
		return false;
	}

	private boolean insertNewSubject()
	{
		if (mainFrame.insertDbFachbegebiete(tfSubjectDE.getText(), tfSubjectES.getText()))
			return true;
		return false;
	}
	
	
	// Listener

	@Override
	public void focusGained(FocusEvent e)
	{
		if (e.getSource() instanceof JTextComponent)
			((JTextComponent) e.getSource()).selectAll();
	}

	@Override
	public void focusLost(FocusEvent arg0)
	{
	
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
		//deutsche Texteingaben
		if (e.getSource() == tfTermDE || e.getSource() ==tfSubjectDE)
		{
			if (Character.isISOControl(e.getKeyChar())) // Ignoriert
														// Steuerkommandos
				return;
			
			if (!Character.isAlphabetic(e.getKeyChar()))
			{
				Toolkit.getDefaultToolkit().beep();
				e.consume();
				return;
			}
		}
		
		if (e.getSource() == tfTermES || e.getSource() ==tfSubjectES)
		{
			if (Character.isISOControl(e.getKeyChar())) // Ignoriert Steuerkommandos
				return;

			if (!Character.isAlphabetic(e.getKeyChar()))
			{
				Toolkit.getDefaultToolkit().beep();
				e.consume();
				return;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == insertBtn)
		{
			if (isInsertDone())
			{
				JOptionPane.showMessageDialog(this, Lang.getString("addToDb"), Lang.getString("addSuccess"), JOptionPane.INFORMATION_MESSAGE);
				mainFrame.all_ActivteLanguageBtn(mainFrame.getCurrentLocale().toString());
				this.dispose();
			}
			else
			{
				// Fehlermeldung kommt aus insertDone()
			}

		}

	}

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
			this.dispose();
		else
			tfTermDE.requestFocusInWindow();
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
	public void itemStateChanged(ItemEvent e)
	{
		
		ListItem listItem = null;
		
		if (e.getSource() instanceof JComboBox &&  e.getStateChange() == ItemEvent.SELECTED)
		{
			
			if (e.getSource() == cboSubjectDE)
			{
				
				listItem = (ListItem)cboSubjectDE.getSelectedItem();
			}
			else
			{
				listItem = (ListItem)cboSubjectES.getSelectedItem();
			}
									
			int ChoosenItem = (int)listItem.getValueMember();
			
			
		}
	}
}
	
