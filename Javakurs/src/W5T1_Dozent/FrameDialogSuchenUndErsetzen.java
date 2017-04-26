package W5T1_Dozent;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import Util.MyFocusTraversalPolicy;

public class FrameDialogSuchenUndErsetzen extends JDialog implements ActionListener
{

	private Component owner;
	private JLabel lbl1, lbl2;
	private JTextField tfSuche, tfErsetze;
	private JCheckBox checkGrossKlein;
	private JButton btnBeenden, btnWeitersuchen, btnErsetzen, btnAlleErsetzen;

	// Wir bekommen vom aufrufenden Fenster eine Referenz auf seine TextArea übergeben
	private JTextArea textArea;
	
	public FrameDialogSuchenUndErsetzen(JTextArea textArea)
	{
		initializeComponents();
		this.textArea = textArea;
		
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
		
	}
	
	private void initDialog()
	{
	
		this.setModal(true);
		this.setLocationRelativeTo(owner);
		
		// Test der Fokusreihenfolge
		//checkGrossKlein.setEnabled(false);
		//checkGrossKlein.setVisible(false);
		//tfErsetze.setFocusable(false);
		
		if (textArea.getSelectedText() != null)
		{
			// Den markierten Text aus dem TextArea in das Suchfeld übernehmen
			// und dort ebenfalls markieren.
			tfSuche.setText(textArea.getSelectedText());
			tfSuche.selectAll();
		}
		else
		{
//			textArea.setSelectionStart(5);
//			textArea.setSelectionEnd(13);
			
			// oder
			textArea.select(5, 13);
		}
		
		
		// Wichtig!
		// Durch den Fokusverlust ist der markierte Text im Hauptfenster
		// nicht mehr sichtbar (gilt nur für modalen Dialog).
		// Durch das erneute Setzen des Fokus wird die Markierung wieder
		// angezeigt.	
		textArea.requestFocusInWindow();
		
		
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

		
		
		
	}
	
}
