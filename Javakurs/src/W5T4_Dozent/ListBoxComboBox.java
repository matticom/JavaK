package W5T4_Dozent;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormatSymbols;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;

import Util.ListItem;

public class ListBoxComboBox extends JFrame implements ListSelectionListener, ItemListener, KeyListener, FocusListener
{

	private JList<ListItem> listBox, msListBox;
	private DefaultListModel<ListItem> listBoxModel, msListBoxModel;
	private JScrollPane listBoxScroller, msListBoxScroller;

	private JComboBox<ListItem> cboFont, cboFontStyle, cboFontSize;
	private DefaultComboBoxModel<ListItem> cboDefaultFontModel, cboFontModel, cboFontStyleModel, cboFontSizeModel;

	private JTextArea taFontDemo;
	private JScrollPane taScrollPane;

	private String fontDemoText;

	private String[] fontFamilies;

	private char decimalSeparator;
	
	public ListBoxComboBox()
	{
		initializeComponents();
	}

	private void initializeComponents()
	{

		this.setTitle("Listbox und ComboBox Demo");
		this.setSize(510, 460);

		// Layout Manager ausschalten
		this.setLayout(null);

		// Keine Grössenänderung des Frames.
		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 1. Das Datenmodell für die ListBox erstellen
		listBoxModel = new DefaultListModel<>();

		// 2. Die ListBox erstellen und das Datenmodell als referenz übergeben
		listBox = new JList<>(listBoxModel);
		listBox.addListSelectionListener(this);
		// Nur ein Eintrag soll ausgewählt werden können
		listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 3. Um die Listbox blättern zu können, eine ScrollPane erstellen, und die
		// Referenz auf die ListBox an die ScrollPane übergeben.
		listBoxScroller = new JScrollPane(listBox);
		listBoxScroller.setBounds(10, 10, 280, 110);
		this.add(listBoxScroller);

		// Multi-Select ListBox
		msListBoxModel = new DefaultListModel<>();
		msListBox = new JList<>(msListBoxModel);
		msListBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		msListBox.addListSelectionListener(this);
		msListBoxScroller = new JScrollPane(msListBox);
		msListBoxScroller.setBounds(10, 130, 280, 110);
		this.add(msListBoxScroller);

		// Font ComboBox
		cboDefaultFontModel = new DefaultComboBoxModel<>();
		cboFont = new JComboBox<>(cboDefaultFontModel);
		cboFont.setBounds(300, 10, 180, 25);
		cboFont.addItemListener(this);
		this.add(cboFont);

		// Font Style
		cboFontStyleModel = new DefaultComboBoxModel<>();
		cboFontStyle = new JComboBox<>(cboFontStyleModel);
		cboFontStyle.setBounds(10, 260, 220, 25);
		cboFontStyle.addItemListener(this);
		this.add(cboFontStyle);

		// Font Size
		cboFontSizeModel = new DefaultComboBoxModel<ListItem>();
		cboFontSize = new JComboBox<>(cboFontSizeModel);
		cboFontSize.setBounds(235, 260, 60, 25);

		// ComboBox editierbar machen und einen KeyListener zur
		// Überprüfung der Eingabe auf die editierbare Komponente zuweisen.
		cboFontSize.setEditable(true);
		cboFontSize.getEditor().getEditorComponent().addKeyListener(this);
		cboFontSize.getEditor().getEditorComponent().addFocusListener(this);

		cboFontSize.addItemListener(this);
		this.add(cboFontSize);

		// Font Demo
		taFontDemo = new JTextArea();
		taFontDemo.setLineWrap(true);
		taFontDemo.setWrapStyleWord(true);
		taFontDemo.setMargin(new Insets(3, 3, 3, 3));
		taFontDemo.setFocusable(false);

		taScrollPane = new JScrollPane(taFontDemo);
		taScrollPane.setBounds(10, 290, 490, 90);
		taScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(taScrollPane);

	}

	private void initFrame()
	{

		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);

		// Dezimaltrennzeichen des Systems ermitteln
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		decimalSeparator = dfs.getDecimalSeparator();
		
		populateListBox();
		populateMultiSelectListBox();

		populateDefaultComboBoxModel();

		// Programmatisches Auswählen eines Listbox-Eintrags
		// und im sichtbaren Bereich der Listbox anzeigen.
		listBox.setSelectedIndex(8);
		listBox.ensureIndexIsVisible(listBox.getSelectedIndex());

		// Programmatische Mehrfachauswahl

		// msListBox.removeListSelectionListener(this);
		msListBox.setSelectedIndices(new int[]
		{ 2, 3, 5, 9 });
		// msListBox.addListSelectionListener(this);

		// cboFont.setSelectedIndex(1);

		populateFontStyleComboBox();
		populateFontSizeComboBox();

		fontDemoText = "Franz jagt im komplett verwahrlosten Taxi quer durch Bayern. 1234567890";
		taFontDemo.setText(fontDemoText);

	}

	private void populateListBox()
	{

		for (int i = 1; i <= 10; i++)
			listBoxModel.addElement(new ListItem(i, String.format("Listbox Eintrag %02d", i)));

	}

	private void populateMultiSelectListBox()
	{

		for (int i = 1001; i <= 1010; i++)
			msListBoxModel.addElement(new ListItem(i, String.format("Multi-Select Listbox Eintrag %02d", i)));

	}

	private void populateDefaultComboBoxModel()
	{

		cboDefaultFontModel.addElement(new ListItem(0, taFontDemo.getFont().getFamily()));
		cboDefaultFontModel.addElement(new ListItem(-1, "Alle Schriftarten anzeigen"));

	}

	private void populateFontComboBox()
	{

		int i = 0;

		cboFontModel = new DefaultComboBoxModel<>();

		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();

		fontFamilies = e.getAvailableFontFamilyNames();

		for (String ff : fontFamilies)
			cboFontModel.addElement(new ListItem(++i, ff));

		cboFont.setModel(cboFontModel);

	}

	private void populateFontStyleComboBox()
	{

		cboFontStyleModel.addElement(new ListItem(Font.PLAIN, "Standard"));
		cboFontStyleModel.addElement(new ListItem(Font.BOLD, "Fett"));
		cboFontStyleModel.addElement(new ListItem(Font.ITALIC, "Kursiv"));
		cboFontStyleModel.addElement(new ListItem(Font.BOLD + Font.ITALIC, "Fett und Kursiv"));

	}

	private void populateFontSizeComboBox()
	{
		int[] arrSize = new int[]
		{ 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72 };

		for (int i = 0; i < arrSize.length; i++)
			cboFontSizeModel.addElement(new ListItem(arrSize[i], String.format("%d", arrSize[i])));

	}

	private void showValue(ListItem listItem)
	{

		System.out.println(listItem.getValueMember() + " - " + listItem.getDisplayMember());

	}

	private void showMultiSelectItems()
	{

		System.out.println();

		for (ListItem li : msListBox.getSelectedValuesList())
			showValue(li);

		System.out.println();
	}

	private void setFont(String fontFamily)
	{

		Font font = new Font(fontFamily, taFontDemo.getFont().getStyle(), taFontDemo.getFont().getSize());
		taFontDemo.setFont(font);

	}

	private void setFontStyle(int fontStyle)
	{

		taFontDemo.setFont(taFontDemo.getFont().deriveFont(fontStyle));

	}

	private void setFontSize(float fontSize)
	{

		taFontDemo.setFont(taFontDemo.getFont().deriveFont(fontSize));

	}

	
	private boolean isCharacterAllowed(char c, JTextComponent tc)
	{
		
		boolean retValue = false;
		
		// Nur Ziffern 0 - 9 und ein Dezimaltrennzeichen (Komma) sind erlaubt.
		if (Character.isDigit(c))
			retValue = true;
		else if (c == decimalSeparator && !tc.getText().contains(Character.toString(decimalSeparator)))
			retValue = true;
		
		return retValue;
		
	}
	
	
	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		ListBoxComboBox f = new ListBoxComboBox();
		f.showFrame();

	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{

		// Das Auswählen eines Listbox-Eintrages löst eine Serie
		// von Ereignissen aus. Erst wenn alle Ereignisse abgeschlossen
		// sind (getValueIsAdjusting()), wird der ausgewählte Eintrag
		// übernommen.

		if (e.getSource() instanceof JList && !e.getValueIsAdjusting())
		{

			if (e.getSource() == listBox)
				showValue(listBox.getSelectedValue());
			else if (e.getSource() == msListBox)
				showMultiSelectItems();

		}

	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{

		JComboBox<ListItem> cbo = null;
		ListItem listItem = null;

		if (e.getSource() instanceof JComboBox)
		{

			cbo = (JComboBox<ListItem>) e.getSource();
		
			// Keine Eintrag ausgewählt: selectedIndex = - 1.
			// Betrifft editierbare ComboBox 'cboFontSize', wenn
			// über die Tastatur eine Grösse eingegeben wurde.

			if (e.getStateChange() == ItemEvent.SELECTED && cbo.getSelectedIndex() > -1)
			{

				listItem = (ListItem) cbo.getSelectedItem();
				showValue(listItem);
				
				if (e.getSource() == cboFont)
				{

					if ((int) listItem.getValueMember() == -1)
					{
						populateFontComboBox();
						listItem = (ListItem) cboFont.getSelectedItem();
						setFont(listItem.getDisplayMember().toString());
					}
					else
						setFont(listItem.getDisplayMember().toString());
				}
				else if (e.getSource() == cboFontStyle)
				{
					setFontStyle((int) listItem.getValueMember());
				}
				else if (e.getSource() == cboFontSize)
				{
					setFontSize((Float.valueOf(listItem.getValueMember().toString())));
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getSource() == cboFontSize.getEditor().getEditorComponent() && e.getKeyCode() == KeyEvent.VK_ENTER)
			KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if (e.getSource().equals(cboFontSize.getEditor().getEditorComponent()))
		{
			if (Character.isISOControl(e.getKeyChar()))
				return;

			
			// Einen evtl. eingegeben Punkt durch ein Komma ersetzen.
			if (e.getKeyChar() == '.')
				e.setKeyChar(decimalSeparator);
						
			
			 if (!isCharacterAllowed(e.getKeyChar(), (JTextComponent)e.getSource()))
			 {
				 Toolkit.getDefaultToolkit().beep();
				 e.consume();
				 return;
			 }

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
		
		
		
	}

}
