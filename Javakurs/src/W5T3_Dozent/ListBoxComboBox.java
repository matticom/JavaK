package W5T3_Dozent;

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
	
	
	private JComboBox<ListItem> cboFont;
	private DefaultComboBoxModel<ListItem> cboDefaultFontModel, cboFontModel;
	
	private String[] fontFamilies;
	
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
		// 	  Referenz auf die ListBox an die ScrollPane übergeben.
		listBoxScroller = new JScrollPane(listBox);
		listBoxScroller.setBounds(10,  10,  280, 110);
		this.add(listBoxScroller);
		
		
		// Multi-Select ListBox
		msListBoxModel = new DefaultListModel<>();
		msListBox = new JList<>(msListBoxModel);
		msListBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		msListBox.addListSelectionListener(this);
		msListBoxScroller = new JScrollPane(msListBox);		
		msListBoxScroller.setBounds(10,  130, 280, 110);
		this.add(msListBoxScroller);
		
		// Font ComboBox
		cboDefaultFontModel = new DefaultComboBoxModel<>();
		cboFont = new JComboBox<>(cboDefaultFontModel);		
		cboFont.setBounds(300, 10, 180, 25);
		cboFont.addItemListener(this);
		this.add(cboFont);
		
	}
	
	
	private void initFrame()
	{
		
		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);
		
		populateListBox();
		populateMultiSelectListBox();
		
		populateDefaultComboBoxModel();
		
		
		// Programmatisches Auswählen eines Listbox-Eintrags
		// und im sichtbaren Bereich der Listbox anzeigen.
		listBox.setSelectedIndex(8);
		listBox.ensureIndexIsVisible(listBox.getSelectedIndex());
		
		// Programmatische Mehrfachauswahl
		
		//msListBox.removeListSelectionListener(this);
		msListBox.setSelectedIndices(new int[] {2, 3, 5, 9});
		//msListBox.addListSelectionListener(this);
		
		
		//cboFont.setSelectedIndex(1);
		
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
			cboFontModel.addElement(new ListItem(++i, ff ));
		
		cboFont.setModel(cboFontModel);
		
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
		
		if (e.getSource() instanceof JComboBox &&  e.getStateChange() == ItemEvent.SELECTED)
		{
			
			if (e.getSource() == cboFont)
			{
				
				listItem = (ListItem)cboFont.getSelectedItem();
				showValue(listItem);
				
				if ((int)listItem.getValueMember() == -1)
				{
					populateFontComboBox();
				}

				
			}
			
		}
		
		
		
	}

}
