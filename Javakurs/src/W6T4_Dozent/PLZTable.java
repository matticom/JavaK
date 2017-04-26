package W6T4_Dozent;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import Util.StatusBar;
import Util.WinUtil;

public class PLZTable extends JDialog implements ActionListener, ListSelectionListener, KeyListener, MouseListener
{

	private JMenuBar menuBar;
	private JMenu menuDatei, menuBearbeiten;
	private JMenuItem miNeu, miAendern, miLoeschen, miSchliessen;
	private JTable Tabelle;
	private JScrollPane jspTabelle;

	private StatusBar statusBar;

	private Component owner;

	public PLZTable()
	{
		initializeComponents();
	}

	private void initializeComponents()
	{

		this.setTitle("Postleitzahlen");
		this.setSize(800, 480);

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// Das Standard-Layout ist das BorderLayout

		menuBar = new JMenuBar();

		menuDatei = WinUtil.createMenu(menuBar, "Datei", null, 'D');
		miSchliessen = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Schließen", null, 'S', null);

		menuBearbeiten = WinUtil.createMenu(menuBar, "Bearbeiten", null, 'B');
		miNeu = WinUtil.createMenuItem(menuBearbeiten, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Neu",
				new ImageIcon(this.getClass().getResource("/images/New.png")), 'N', null);
		miAendern = WinUtil.createMenuItem(menuBearbeiten, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Ändern",
				new ImageIcon(this.getClass().getResource("/images/Edit.png")), 'Ä', null);
		miLoeschen = WinUtil.createMenuItem(menuBearbeiten, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Löschen",
				new ImageIcon(this.getClass().getResource("/images/Delete.png")), 'L', null);

		this.setJMenuBar(menuBar);

		// Tabelle zu Anzeige der Datensätze
		Tabelle = new JTable();
		// Keine Mehrfach-Auswahl zulassen
		Tabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Listener nur Anzeige der aktuellen Zeilennummer
		Tabelle.getSelectionModel().addListSelectionListener(this);
		// Zum Abfangen der Tasten POS1 und ENDE
		Tabelle.addKeyListener(this);
		// Für Doppelklick
		Tabelle.addMouseListener(this);
		
				
		// Zum Blättern der Tabelle
		jspTabelle = new JScrollPane(Tabelle);
		this.add(jspTabelle, BorderLayout.CENTER);

		// Statusleiste
		statusBar = new StatusBar();
		statusBar.setStatusLabelFont(statusBar.getFont().deriveFont(Font.PLAIN));
		statusBar.setHorizontalAlignment(SwingConstants.CENTER);
		statusBar.setPreferredSize(new Dimension(0, 25));
		this.add(statusBar, BorderLayout.PAGE_END);

	}

	private void initDialog()
	{
		this.setModal(true);
		this.setLocationRelativeTo(owner);
		showDataThread();
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

	
	private void showDataThread()
	{
		
		statusBar.setText("Datensätze werden gelesen...");
		Thread t = new Thread(new ShowData());
		t.start();
	}
	
	
	private class ShowData implements Runnable
	{

		@Override
		public void run()
		{
			
			Tabelle.setModel(new PLZTableModel());
			
			// Überschriften der Tabelle größer und fett
			Font font = Tabelle.getTableHeader().getFont().deriveFont(Font.BOLD, 14F);
			Tabelle.getTableHeader().setFont(font);		
			
			
			// Erste Spalte PRIMARYKEY unsichtbar machen
			setTableColumnInvisible(Tabelle, 0);
			
			
			// Für die zweite Spalte PLZ (Postleitzahlen) die Breite setzen
			setTableColumnWidth(Tabelle, 1, 110);
			
			// Alle Spaltenüberschriften linksbündig ausrichten
			DefaultTableCellRenderer tableHeaderRenderer = (DefaultTableCellRenderer)Tabelle.getTableHeader().getDefaultRenderer();
			tableHeaderRenderer.setHorizontalAlignment(SwingConstants.LEFT);
			Tabelle.getTableHeader().setDefaultRenderer(tableHeaderRenderer);
			
			
			// Weitere Zellenformatierungen
			Tabelle.setRowHeight(21);
			Tabelle.setIntercellSpacing(new Dimension(5, 2));
			
			
			Tabelle.setEnabled(Tabelle.getRowCount() > 0);
			
			miAendern.setEnabled(Tabelle.isEnabled());
			miLoeschen.setEnabled(Tabelle.isEnabled());
			
			if (Tabelle.getRowCount() > 0)
				setSelectedRow(0);
			else
				statusBar.setText("");
			
			
			
		}
		
	}
	
	
	private void setSelectedRow(int rowIndex)
	{
		
		// changeSelection(rowIndex, columnIndex, toggle (select/deselect), extend (bis zur letzten Spalte))
		Tabelle.changeSelection(rowIndex, 0, false, true);
		
	}
	
	
	private void setTableColumnWidth(JTable jTable, int colIndex, int width)
	{
		
		jTable.getColumnModel().getColumn(colIndex).setPreferredWidth(width);
		// setMaxWidth() ist erforderlich um die maximale Spaltenbreite zu
		// setzen.
		// Durch den Multiplikator wird aber die Möglichkeit gegeben
		// die Spalte nachträglich um das n-fache zu vergrössern.
		jTable.getColumnModel().getColumn(colIndex).setMaxWidth(width * 3);
		
	}
	
	private void setTableColumnInvisible(JTable jTable, int colIndex)
	{
		
		// Wichtig ist die Reihenfolge der Aufrufe
		jTable.getColumnModel().getColumn(colIndex).setWidth(0);
		jTable.getColumnModel().getColumn(colIndex).setMaxWidth(0);
		jTable.getColumnModel().getColumn(colIndex).setMinWidth(0);
		jTable.getColumnModel().getColumn(colIndex).setPreferredWidth(0);
		jTable.getColumnModel().getColumn(colIndex).setResizable(false);
		
	}
	
	
	private void detailFormat(long PrimaryKey)
	{
		
		PLZForm dlg = new PLZForm(PrimaryKey);
		dlg.showDialog(this);
		
	}
	
	
	
	private class PLZTableModel extends AbstractTableModel
	{

		private int anzahlSpalten, anzahlZeilen;

		private String SQL = "SELECT PRIMARYKEY, PLZ as Postleitzahlen, ORT as Wohnort FROM POSTLEITZAHLEN ORDER BY PLZ, ORT";

		private ArrayList<String> ColumnNames;

		private Object[][] data;

		public PLZTableModel()
		{

			// Ausführen der SQL-Anweisung zum Lesen aller Datensätze
			ResultSet rSet = DBConnection.executeQuery(SQL);

			// Lesen der Metadaten zur Ermittlung der Anzahl der Spalten
			ResultSetMetaData rsMetaData = getMetaData(rSet);

			// Anzahl der Spalten aus den Metadaten ermitteln
			anzahlSpalten = getColumnCount(rsMetaData);

			// Anzahl Zeilen aus dem ResultSet ermitteln
			anzahlZeilen = getRowCount(rSet);

			// Überschriften für die Spalten aus den Metadaten ermitteln
			setHeader(rsMetaData);

			// Liest alle datensätze aus dem ResultSet in das zweidimensionale
			// Objekt-Array 'data'
			getData(rSet);

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

		private void setHeader(ResultSetMetaData rsMD)
		{

			ColumnNames = new ArrayList<>();

			for (int i = 1; i <= anzahlSpalten; i++)
				ColumnNames.add(getColumnLabel(rsMD, i));

		}

		private String getColumnLabel(ResultSetMetaData rsMD, int colIndex)
		{
			String colName = "";

			try
			{
				colName = rsMD.getColumnLabel(colIndex);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(owner, "getColumnLabel: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			}

			return colName;

		}

		private void getData(ResultSet rSet)
		{

			data = new Object[anzahlZeilen][anzahlSpalten];

			try
			{
				for (int zeile = 1; zeile <= anzahlZeilen; zeile++)
				{

					rSet.next();
					for (int spalte = 1; spalte <= anzahlSpalten; spalte++)
						data[zeile - 1][spalte - 1] = rSet.getObject(spalte);

				}
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(owner, "getData: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			}

		}

		
		
		
		@Override
		public String getColumnName(int colIndex)
		{
			return ColumnNames.get(colIndex);
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
			return data[rowIndex][colIndex];
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource() == miSchliessen)
			this.dispose();
		else if (e.getSource() == miAendern)
			detailFormat((long)Tabelle.getValueAt(Tabelle.getSelectedRow(), 0));
		else if (e.getSource() == miNeu)
			detailFormat(-1);

	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		
		if (!e.getValueIsAdjusting())
			statusBar.setText(String.format("Datensatz %s von %s", NumberFormat.getInstance().format(Tabelle.getSelectedRow() + 1),
											NumberFormat.getInstance().format(Tabelle.getRowCount())));
		
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		
		if (e.getKeyCode() == KeyEvent.VK_HOME)
			setSelectedRow(0);
		else if (e.getKeyCode() == KeyEvent.VK_END)
			setSelectedRow(Tabelle.getRowCount() - 1);
		else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			e.consume();
			detailFormat((long)Tabelle.getValueAt(Tabelle.getSelectedRow(), 0));
		}
		
			
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// Doppelklick erkennen
		if (e.getClickCount() == 2)
			detailFormat((long)Tabelle.getValueAt(Tabelle.getSelectedRow(), 0));
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
