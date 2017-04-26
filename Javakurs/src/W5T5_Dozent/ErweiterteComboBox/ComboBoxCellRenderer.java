package W5T5_Dozent.ErweiterteComboBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import Util.ListItem;

class ComboBoxCellRenderer implements ListCellRenderer<Object>
{
	
	private DefaultListCellRenderer defaultRenderer;
	
	public ComboBoxCellRenderer()
	{
		defaultRenderer = new DefaultListCellRenderer();
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		Font newFont = null;
		Color newForeground = null;
		Icon newIcon = null;
		String newText = null;
		ListItem listItem;
		ExtendedListItem cboItem;
		
		// Hinter jedem Eintrag (Component) in der Combobox verbirgt sich ein Label.
		JLabel itemLabel = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		// Pr�fen, ob es sich bei dem Eintrag um den Datentyp 'ListItem' handelt.
		if (value instanceof ListItem)
		{
			listItem = (ListItem)value;
			// Pr�fen, ob es sich bei dem DisplayMember um den Datentyp 'ExtendedListItem' handelt.
			if (listItem.getDisplayMember() instanceof ExtendedListItem)
			{
				cboItem = (ExtendedListItem)listItem.getDisplayMember();
				// Aufbereiten der der Daten aus dem ComboBoxItem 
			
				newFont = new Font(cboItem.getFontName(), cboItem.getFontStyle(), cboItem.getFontSize());   
				newForeground = cboItem.getFontColor();
				newIcon = cboItem.getIcon();
				newText = cboItem.getItemText();
			}
		}
		else
		{
			newFont = list.getFont();
			newForeground = list.getForeground();
			newText = value.toString();
		}
		
		// Setzen der Werte f�r die Anzeige
		
		if (!isSelected)
		{
			itemLabel.setForeground(newForeground);
		}
		if (newIcon != null)
		{
			itemLabel.setIcon(newIcon);
		}
		
		itemLabel.setText(newText);
		itemLabel.setFont(newFont);
		
		// R�ckgabe des modifizierten Eintrags
		return itemLabel;
	}


}
