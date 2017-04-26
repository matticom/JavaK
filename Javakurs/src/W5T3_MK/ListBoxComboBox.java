package W5T3_MK;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Util.ListItem;

public class ListBoxComboBox extends JFrame implements ListSelectionListener, ItemListener
{

	private JList<ListItem> listBox, msListBox;
	private DefaultListModel<ListItem> listBoxModel, msListBoxModel;
	private JScrollPane listBoxScroller, msListBoxScroller;

	private JComboBox<ListItem> cboFont, cboFontStyle, cboFontSize;
	private DefaultComboBoxModel<ListItem> cboDefaultFontModel, cboFontModel, cboFontStyleModel, cboFontSizeModel;

	private String[] fontFamilies, fontStyles = {"Standard", "Kursiv", "Fett", "Kursiv + Fett"}; 
	private int[] fontSizes = {8,9,10,11,12,14,16,18,20,22,24,26,28,36,48,72}, fontStylesKey = {Font.PLAIN,Font.ITALIC,Font.BOLD,Font.ITALIC+Font.BOLD};
	
	private Font font;
	

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
		
		this.add(cboFont);

		// FontStyle ComboBox
		cboFontStyleModel = new DefaultComboBoxModel<>();
		cboFontStyle = new JComboBox<>(cboFontStyleModel);
		cboFontStyle.setBounds(300, 40, 180, 25);
		
		this.add(cboFontStyle);

		// FontSize ComboBox
		cboFontSizeModel = new DefaultComboBoxModel<>();
		cboFontSize = new JComboBox<>(cboFontSizeModel);
		cboFontSize.setBounds(300, 70, 180, 25);
		
		this.add(cboFontSize);

	}

	private void initFrame()
	{

		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);

		populateListBox();
		populateMultiSelectListBox();

		//populateDefaultComboBoxModel();
		populateFontComboBox();
		populateFontStyleComboBox();
		populateFontSizeComboBox();
		
		cboFontSize.setSelectedIndex(5);
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
		cboFontSize.addItemListener(this);
		cboFontStyle.addItemListener(this);
		cboFont.addItemListener(this);
	}

	private void setFont()
	{
		ListItem listItemFont, listItemStyle, listItemSize;
		listItemFont = (ListItem) cboFont.getSelectedItem();
		listItemStyle = (ListItem) cboFontStyle.getSelectedItem();
		listItemSize = (ListItem) cboFontSize.getSelectedItem();
		
		font = new Font((String) listItemFont.getDisplayMember(), (int)listItemStyle.getValueMember(), (int)listItemSize.getValueMember());
		 //msListBox.getComponents().
		
		for (int i = 0; i < this.getComponentCount(); i++)
			this.getComponent(i).setFont(font);
			
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

		cboDefaultFontModel.addElement(new ListItem(0, cboFont.getFont().getFamily()));
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


		for (int i = 0; i < fontStyles.length; i++)
			cboFontStyleModel.addElement(new ListItem(fontStylesKey[i], fontStyles[i]));

		cboFontStyle.setModel(cboFontStyleModel);

	}
	
	private void populateFontSizeComboBox()
	{

		for (int i = 0; i < fontSizes.length; i++)
			cboFontSizeModel.addElement(new ListItem(fontSizes[i], String.valueOf(fontSizes[i])));

		cboFontSize.setModel(cboFontSizeModel);

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

		ListItem listItem = null;

		if (e.getSource() instanceof JComboBox && e.getStateChange() == ItemEvent.SELECTED)
		{

			if (e.getSource() == cboFont)
			{

				listItem = (ListItem) cboFont.getSelectedItem();
				showValue(listItem);

				if ((int) listItem.getValueMember() == -1)
				{
					populateFontComboBox();
				}

			}
			
			if (e.getSource() == cboFontStyle)
			{

				listItem = (ListItem) cboFontStyle.getSelectedItem();
				showValue(listItem);
				setFont();

				

			}
			
			if (e.getSource() == cboFontSize)
			{

				listItem = (ListItem) cboFontSize.getSelectedItem();
				showValue(listItem);
				setFont();

				

			}

		}

	}

}
