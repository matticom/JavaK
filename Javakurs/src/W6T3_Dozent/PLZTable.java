package W6T3_Dozent;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import Util.StatusBar;
import Util.WinUtil;

public class PLZTable extends JDialog implements ActionListener
{

	
	private JMenuBar	  	menuBar;
	private JMenu	     	menuDatei, menuBearbeiten;
	private JMenuItem	  	miNeu, miAendern, miLoeschen, miSchliessen;
	private JTable 			Tabelle;
	private JScrollPane 	jspTabelle;
	
	private StatusBar		statusBar;
	
	private Component		owner;
	
	public PLZTable()
	{
		initializeComponents();
	}
	
	
	private void initializeComponents()
	{
		
		this.setTitle("Postleitzahlen");
		this.setSize(800,  480);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// Das Standard-Layout ist das BorderLayout
		
		menuBar = new JMenuBar();
		
		menuDatei = WinUtil.createMenu(menuBar, "Datei", null, 'D');
		miSchliessen = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Schließen", null, 'S', null);
		
		menuBearbeiten = WinUtil.createMenu(menuBar, "Bearbeiten", null, 'B');
		miNeu = WinUtil.createMenuItem(menuBearbeiten, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Neu", null, 'N', null);
		miAendern = WinUtil.createMenuItem(menuBearbeiten, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Ändern", null, 'Ä', null);
		miLoeschen = WinUtil.createMenuItem(menuBearbeiten, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Löschen", null, 'L', null);

		this.setJMenuBar(menuBar);

		
		// Tabelle zu Anzeige der Datensätze
		Tabelle = new JTable();
		// Keine Mehrfach-Auswahl zulassen
		Tabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Zum Blättern der Tabelle
		jspTabelle = new JScrollPane(Tabelle);
		this.add(jspTabelle, BorderLayout.CENTER);
		
		
		// Statusleiste
		statusBar = new StatusBar();
		statusBar.setHorizontalAlignment(SwingConstants.CENTER);
		statusBar.setPreferredSize(new Dimension(0, 25));
		this.add(statusBar, BorderLayout.PAGE_END);
		
		
	}
	
	
	private void initDialog()
	{
		this.setModal(true);
		this.setLocationRelativeTo(owner);
		
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
	
	
	private class PLZTableModel extends AbstractTableModel
	{

		private int anzahlSpalten, anzahlZeilen;
		
		private String SQL = "SELECT PLZ, ORT FROM POSTLEITZAHLEN ORDER BY PLZ, ORT";
		
		
		public PLZTableModel()
		{
			
			// Ausführen der SQL-Anweisung zum Lesen aller Datensätze
			ResultSet rSet = DBConnection.executeQuery(SQL);
			
			// Lesen der Metadaten rur Ermittlung der Anzahl der Spalten
			ResultSetMetaData rsMetaData = getMetaData(rSet);
			
			// Anzahl der Spalten aus den Metadaten ermitteln
			anzahlSpalten = getColumnCount(rsMetaData);
			
			// Anzahl Zeilen aus dem ResultSet ermitteln
			anzahlZeilen = getRowCount(rSet);
			
		}
		
		private ResultSetMetaData getMetaData(ResultSet rSet)
		{
			
			ResultSetMetaData rsMD = null;
			
			try
			{
				rsMD = rSet.getMetaData();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(owner, "getMetaData: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			}
			
			return rsMD;
		}
		
		
		private int getColumnCount(ResultSetMetaData rsMD)
		{
			int retValue = 0;
			
			try
			{
				retValue = rsMD.getColumnCount();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(owner, "getColumnCount: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			}
			
			return retValue;
		}
		
		
		private int getRowCount(ResultSet rSet)
		{
			
			int retValue = 0;
			
			try
			{
				rSet.last();
				retValue = rSet.getRow();
				rSet.beforeFirst();
				
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(owner, "getRowCount: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			
			
			
			return retValue;
		}
		
		
		
		@Override
		public int getColumnCount()
		{
			return anzahlSpalten;
		}

		@Override
		public int getRowCount()
		{
			return anzahlZeilen;
		}

		@Override
		public Object getValueAt(int rowIndex, int colIndex)
		{
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
