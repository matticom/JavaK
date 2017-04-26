package W5T4_Dozent;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.*;

import Util.MyFocusTraversalPolicy;

public class FrameDialogSuchenUndErsetzen extends JDialog implements ActionListener
{

	// Statische Referenzvariable auf den Dialog.
	// Muss statisch sein, da sie von der statischen Methode getInstance()
	// verwendet wird.
	private static FrameDialogSuchenUndErsetzen myInstance;
	
	
	
	private Component owner;
	private JLabel lbl1, lbl2;
	private JTextField tfSuche, tfErsetze;
	private JCheckBox checkGrossKlein;
	private JButton btnBeenden, btnWeitersuchen, btnErsetzen, btnAlleErsetzen;

	// Wir bekommen vom aufrufenden Fenster eine Referenz auf seine TextArea übergeben
	private JTextArea textArea;

	// Die Position des gefundenen Textmusters
	private int foundPos = -1;

	private int replaceCounter;
	private int searchCounter;

	private boolean replaceAll;
	private boolean newDialog = true;
	
//	public FrameDialogSuchenUndErsetzen(JTextArea textArea)
//	{
//		initializeComponents();
//		this.textArea = textArea;
//
//	}
	
	// Singleton Klasse
	// Private Konstruktoren verwenden, damit die Klasse nicht von aussen
	// erstellt werden kann, sondern nur über die statische Methode
	// getInstance().
	// Die sorgt dafür, dass nur eine Instanz dieser Klasse erstellt wird.
	// Obwohl der Dialog nicht-modal ist, kann er dadurch nur einmal aufgerufen
	// werden.
	
	private FrameDialogSuchenUndErsetzen(JTextArea textArea)
	{
		initializeComponents();
		this.textArea = textArea;

	}

	public static FrameDialogSuchenUndErsetzen getInstance(JTextArea textArea)
	{
		if (FrameDialogSuchenUndErsetzen.myInstance == null)
			FrameDialogSuchenUndErsetzen.myInstance = new FrameDialogSuchenUndErsetzen(textArea);
		
		return FrameDialogSuchenUndErsetzen.myInstance;
	}
	
	
	private void initializeComponents()
	{
		this.setTitle("Suchen und Ersetzen");
		this.setSize(510, 170);

		// Layout ausschalten
		this.setLayout(null);

		// Kein Grössenänderung des Dialogs
		this.setResizable(false);

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		lbl1 = new JLabel("Suchen nach:");
		lbl1.setBounds(5, 5, 90, 25);
		this.add(lbl1);

		tfSuche = new JTextField();
		tfSuche.setBounds(110, 5, 240, 25);
		this.add(tfSuche);

		lbl2 = new JLabel("Ersetzen durch:");
		lbl2.setBounds(5, 35, 90, 25);
		this.add(lbl2);

		tfErsetze = new JTextField();
		tfErsetze.setBounds(110, 35, 240, 25);
		this.add(tfErsetze);

		checkGrossKlein = new JCheckBox("Groß-/Kleinschreibung beachten");
		checkGrossKlein.setBounds(5, 90, 300, 25);
		this.add(checkGrossKlein);

		btnWeitersuchen = new JButton("Weitersuchen");
		btnWeitersuchen.setFont(btnWeitersuchen.getFont().deriveFont(Font.PLAIN, 11.0f));
		btnWeitersuchen.setBounds(360, 5, 120, 25);
		btnWeitersuchen.addActionListener(this);
		this.add(btnWeitersuchen);

		btnErsetzen = new JButton("Ersetzen");
		btnErsetzen.setFont(btnErsetzen.getFont().deriveFont(Font.PLAIN, 11.0f));
		btnErsetzen.setBounds(360, 35, 120, 25);
		btnErsetzen.addActionListener(this);
		this.add(btnErsetzen);

		btnAlleErsetzen = new JButton("Alle Ersetzen");
		btnAlleErsetzen.setFont(btnAlleErsetzen.getFont().deriveFont(Font.PLAIN, 11.0f));
		btnAlleErsetzen.setBounds(360, 65, 120, 25);
		btnAlleErsetzen.addActionListener(this);
		this.add(btnAlleErsetzen);

		btnBeenden = new JButton("Beenden");
		btnBeenden.setFont(btnBeenden.getFont().deriveFont(Font.PLAIN, 11.0f));
		btnBeenden.setBounds(360, 95, 120, 25);
		btnBeenden.addActionListener(this);
		this.add(btnBeenden);

		// -------------------------------------------------------------------

		// Verwenden eines Vectors um die Fokus-Reihenfolge der Komponenten,
		// abweichend von der Standardreihenfolge, zu definieren.
		Vector<Component> components = new Vector<>();

		components.add(tfSuche);
		components.addElement(tfErsetze);
		components.add(checkGrossKlein);
		components.add(btnWeitersuchen);
		components.add(btnErsetzen);
		components.add(btnAlleErsetzen);
		components.add(btnBeenden);

		// Neue FocusTraversalPolicy erstellen
		// und dem Dialog zuweisen.
		MyFocusTraversalPolicy newPolicy = new MyFocusTraversalPolicy(components);
		this.setFocusTraversalPolicy(newPolicy);
		
		// Test, wenn keine Komponente den Eingabefokus erhalten kann. 
		//newPolicy.enableAllComponents(false);
		
	}

	private void initDialog()
	{

		if (newDialog)
		{
			// Die Dialog ist ein nicht-modaler Dialog
			//this.setModal(true);
			// In der Mitte des Desktops anzeigen
			this.setLocationRelativeTo(null);
			newDialog = false;
		}
		
		// Test der Fokusreihenfolge
		// checkGrossKlein.setEnabled(false);
		// checkGrossKlein.setVisible(false);
		// tfErsetze.setFocusable(false);

	
		if (textArea.getSelectedText() != null)
		{
			// Den markierten Text aus dem TextArea in das Suchfeld übernehmen
			// und dort ebenfalls markieren.
			tfSuche.setText(textArea.getSelectedText());
			tfSuche.selectAll();
		}

		// Wichtig!
		// Durch den Fokusverlust ist der markierte Text im Hauptfenster
		// nicht mehr sichtbar (gilt nur für modalen Dialog).
		// Durch das erneute Setzen des Fokus wird die Markierung wieder
		// angezeigt.
		textArea.requestFocusInWindow();

	}

	private void suche()
	{

		int retValue;

		// Nichts zu suchen
		if (tfSuche.getText().length() == 0)
			return;

		if (checkGrossKlein.isSelected())
			// Groß-Kleinschreibung beachten
			foundPos = textArea.getText().indexOf(tfSuche.getText(), textArea.getCaretPosition());
		else
			// Groß-Kleinschreibung nicht beachten
			foundPos = textArea.getText().toLowerCase().indexOf(tfSuche.getText().toLowerCase(), textArea.getCaretPosition());

		if (foundPos > -1)
		{
			// Text im Suchfeld markieren.
			this.textArea.select(foundPos, foundPos + tfSuche.getText().length());
			searchCounter++;
		}
		else
		{
			if (replaceAll)
				replaceAll = false;
			else if (searchCounter > 0)
			{
				// Die Anzahl der gefundenenen Begriffe verwenden um zu entscheiden
				// ob die Methode showEndOfTextMessage() oder die
				// Methode showTextNotFoundMessage() verwendet werden soll.
				// Die Variable searchFromBegin signalisiert, ob die Suche am Anfang
				// des Textes begonnen hat oder nicht.

				retValue = showEndOfTextMessage();

				if (retValue == JOptionPane.OK_OPTION)
				{
					// Da für die Suche mit der Methode indexOf() die aktuelle
					// Cursorposition verwendet wird, die Markierung entfernen
					// und den Cursor auf den Anfang des Textes setzen.
					// textArea.setSelectionStart(0);
					// textArea.setSelectionEnd(0);
					searchCounter = 0;
					// Den Cursor auf den Anfang des Textes positionieren
					textArea.select(0, 0);
					suche();

				}
				else
					this.dispose();
			}
			else
				showTextNotFoundMessage();

		}

	}

	private int showEndOfTextMessage()
	{
		String msg = "Die Suche hat das Ende des Textes erreicht.\nVom Anfang des Textes an weitersuchen";
		// Benutzerdefinierte Button Texte
		String[] options =
		{ "OK", "Abbrechen" };

		return JOptionPane.showOptionDialog(this, msg, "Frage", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

	}

	private void showTextNotFoundMessage()
	{
		String msg = "Die angegebene Zeichenfolge wurde nicht gefunden";
		JOptionPane.showMessageDialog(this, msg, "Hinweis", JOptionPane.INFORMATION_MESSAGE);

	}

	private void ersetze()
	{
		// Nichts zu ersetzen
		if (tfErsetze.getText().length() == 0)
			return;

		if (textArea.getSelectedText() != null)
		{

			if (checkGrossKlein.isSelected() && textArea.getSelectedText().equals(tfSuche.getText()))
			{
				textArea.replaceSelection(tfErsetze.getText());
				replaceCounter++;
			}
			else if (!checkGrossKlein.isSelected() && textArea.getSelectedText().equalsIgnoreCase(tfSuche.getText()))
			{
				textArea.replaceSelection(tfErsetze.getText());
				replaceCounter++;
			}
		}

		/*
		 * Falls initial schon der Ersetzungstext eingegeben wurde und gleich der 'Ersetzen'-Button geklickt wurde, wird zuerst auf den Suchtext positioniert.
		 * 
		 * Falls das zu ersetzende Suchmuster mehrfach im Text vorkommt, wird nach dem Ersetzen jeweils zur nächsten Stelle des Suchetextes positioniert.
		 */

		suche();

	}

	private void ersetzeAlle()
	{

		// Nichts zu ersetzen
		if (tfErsetze.getText().length() == 0)
			return;

		replaceCounter = 0;
		searchCounter = 0;

		foundPos = -1;
		textArea.setCaretPosition(0);

		replaceAll = true;

//		while (true)
//		{
//			ersetze();
//			if (foundPos == -1)
//				break;
//		}
		
		
		// oder mit regulärem Ausdruck
		String regex = "";
		
		if (!checkGrossKlein.isSelected())
			regex = "(?i)";
		
		
		replaceCounter = countMatches(textArea.getText(), tfSuche.getText());
		
		
		if (replaceCounter > 0)
		{
			textArea.setText(textArea.getText().replaceAll(regex + tfSuche.getText(), tfErsetze.getText()));
			
			JOptionPane.showMessageDialog(this, String.format("Es wurden %s Textstellen ersetzt", NumberFormat.getInstance().format(replaceCounter)), "Hinweis",
					JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		replaceCounter = 0;
		searchCounter = 0;
		
	}

	private int countMatches(String s, String searchPattern)
	{
		// Gross-/Kleinschreibung nicht berücksichtigen
		if (!checkGrossKlein.isSelected())
		{
			s = s.toLowerCase();
			searchPattern = searchPattern.toLowerCase();
		}
		
		return (s.length() - s.replace(searchPattern, "").length()) / searchPattern.length();

	}
	
	
	
	public void showDialog()
	{
		initDialog();
		this.setVisible(true);
	}

	public void showDialog(Component owner)
	{
		this.owner = owner;
		showDialog();

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() == btnBeenden)
		{
			this.dispose();
			myInstance = null;
		}
		else if (e.getSource() == btnWeitersuchen)
			suche();
		else if (e.getSource() == btnErsetzen)
			ersetze();
		else if (e.getSource() == btnAlleErsetzen)
			ersetzeAlle();

	}

}
