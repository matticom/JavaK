package W5T5_Dozent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Util.StatusBar;
import Util.WinUtil;

public class TabbedPane extends JFrame implements WindowListener, ActionListener, ChangeListener
{

	private JMenuBar 				menuBar;
	private JMenu					menuDatei, menuAnsicht, menuRegisterkarten;
	private JMenuItem 				miBeenden, miOeffnen, miSchliessen;
	private JRadioButtonMenuItem	miRegisterOben, miRegisterLinks, miRegisterRechts, miRegisterUnten;
	private JCheckBoxMenuItem	  	miZeilenumbruch;
	
	private JTabbedPane				tabPane;
	private StatusBar 				statusBar;
	
	private File	              	fcFile;
	
	public TabbedPane()
	{
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		
		this.setTitle("Registerkarten");
		this.setSize(800,  600);
		
		
		// Das BorderLayout ist das Standard Layout des Frames.
		
		// Der BorderLayout Manager unterteilt Container in fünf Bereiche, in
		// denen Grafikkomponenten untergebracht werden können.
		// Einem Bereich kann dabei immer nur eine Komponente zugerodnet werden.

		// 					PAGE_START
		// LINE_START 		CENTER 					LINE_END
		// 					PAGE_END
		
		
		// Das Schließen des Frames wird vom WindowListener überwacht
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		
		menuBar = new JMenuBar();
		
		menuDatei = WinUtil.createMenu(menuBar, "Datei", null, 'D');
		
		miOeffnen = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Öffnen...", 
                new ImageIcon(this.getClass().getResource("/images/open-icon.png")), 'Ö', null);
		miOeffnen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		
		miSchliessen = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Schließen", 
				new ImageIcon(this.getClass().getResource("/images/Delete.png")), 'S', null);
		miSchliessen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		
		menuDatei.addSeparator();
		miBeenden = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Beenden", 
				                           null, 'e', "Programm beenden");
		
		menuAnsicht = WinUtil.createMenu(menuBar, "Ansicht", null, 'A');
		
		menuRegisterkarten = WinUtil.createSubMenu(menuAnsicht, "Registerkarten", null, 'R');
		
		ButtonGroup bg = new ButtonGroup();
		
		miRegisterOben = (JRadioButtonMenuItem)WinUtil.createMenuItem(menuRegisterkarten, null, WinUtil.MenuItemType.ITEM_RADIO, this, "Oben", 							
				null, 'o', null);
		bg.add(miRegisterOben);
		miRegisterLinks = (JRadioButtonMenuItem)WinUtil.createMenuItem(menuRegisterkarten, null, WinUtil.MenuItemType.ITEM_RADIO, this, "Links", 
				null, 'l', null);
		bg.add(miRegisterLinks);
		miRegisterRechts = (JRadioButtonMenuItem)WinUtil.createMenuItem(menuRegisterkarten, null, WinUtil.MenuItemType.ITEM_RADIO, this, "Rechts", 
				null, 'r', null);
		bg.add(miRegisterRechts);
		miRegisterUnten = (JRadioButtonMenuItem)WinUtil.createMenuItem(menuRegisterkarten, null, WinUtil.MenuItemType.ITEM_RADIO, this, "Unten", 
				null, 'u', null);
		bg.add(miRegisterUnten);
		
		menuAnsicht.addSeparator();
		
		miZeilenumbruch = (JCheckBoxMenuItem)WinUtil.createMenuItem(menuAnsicht, null, WinUtil.MenuItemType.ITEM_CHECK, this, "Zeilenumbruch", 
				null, 'z', null);
		
		
		this.setJMenuBar(menuBar);
		
		tabPane = new JTabbedPane();
		tabPane.addChangeListener(this);
		
		// Die Registerkarten werden alle horizontal in einer Zeile angezeigt
		tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		
		this.add(tabPane, BorderLayout.CENTER);
		
		
		// Statusleiste am unteren Rand erzeugen
		statusBar = new StatusBar();
		statusBar.setPreferredSize(new Dimension(300, 25));
		statusBar.setStatusLabelFont(statusBar.getStatusLabelFont().deriveFont(Font.PLAIN));
		this.add(statusBar, BorderLayout.PAGE_END);
		
		
	}
	
	private void initFrame()
	{
		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);
		
		miSchliessen.setEnabled(false);
		miRegisterOben.setSelected(true);
		
		statusBar.setText("Bereit");
		
	}
	
	private void openFile()
	{
		
		int tabIndex;
		
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(fcFile);
		fc.setMultiSelectionEnabled(true);
		
		if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
		
		// Alle ausgewählten Dateien als File-Array zurückliefern.
		File[] files = fc.getSelectedFiles();
		
		for (File f : files)
		{
			
			// Prüfen, ob eine Registerkarte mit dem Dateinamen bereits existiert
			tabIndex = queryTab(f.toString());
			if (tabIndex != -1)
			{
				tabPane.setSelectedIndex(tabIndex);
				JOptionPane.showMessageDialog(this, "Eine  Registerkarte für diese Datei ist bereits vorhanden", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				addTab(f.toString());
				fcFile = f;
			}
		}
		
	}
	
	// Prüft, ob es bereits eine Registerkarte mit dieser Datei gibt.
	private int queryTab(String Dateiname)
	{
		int retValue = -1;
		
		for (int i = 0; i < tabPane.getTabCount(); i++)
		{
			JScrollPane jsp = (JScrollPane)tabPane.getComponentAt(i);
			if (jsp.getName().equals(Dateiname))
			{
				retValue = i;
				break;
			}
		}
		
		return retValue;
	}
	
	
	private void addTab(String Dateiname)
	{
		
		JTextArea textArea = new JTextArea();
		
		// Automatischer Zeilenumbruch auf Wortgrenze
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		// Freien Bereich zwischen Rahmen und Text definieren
		textArea.setMargin(new Insets(3, 3, 3, 3));
		
		// Stellt horizontale und vertikale Laufleisten zum Blättern zur
		// Verfügung wenn der Inhalt der Komponente größer ist
		// als die definierte Sicht.
		JScrollPane textAreaScrollPane = new JScrollPane(textArea);
		
		// Vollständigen Namen der Datei als Name in der ScrollPane hinterlegen
		textAreaScrollPane.setName(Dateiname);
		
		// Nur den Dateinamen (ohne Pfad) in der Registerkarte anzeigen
		// 1. Argument ist der Text in der Registerkarte,
		// 2. Argument ist die Komponente, die in der Registerkarte angezeigt wird.
		tabPane.addTab(new File(Dateiname).getName(), textAreaScrollPane);
		
		// Einlesen der Datei in die TextArea der Registerkarte
		leseDatei(Dateiname, textArea);
		
		
		
		// Menu-Eintrag zum Schließen der Registerkarte aktivieren
		miSchliessen.setEnabled(true);
	
		// Die zuletzt erstellte Registerkarte selektieren
		tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
	
		// Vollständigen Dateinamen in der Statusleiste anzeigen
		//statusBar.setText(Dateiname);

	}
	
	private boolean leseDatei(String Dateiname, JTextArea textArea)
	{
		
		boolean retValue = false;
		
		File file = new File(Dateiname);
		
		// Try-catch-Anweisungen mit Resourcen.
		// Mit Resource ist ein Objekt gemeint, welches geschlossen werden muss, wenn die try-catch-Anweisung beendet wird.
		// Die try-catch-Anweisung mit Resourcen stellt dies sicher.
		// Jedes Objekt, welches die Schnittstelles java.lang.AutoClosetryable implementiert, können in dieser Anweisung verwendet werden.
		
		try (FileReader in = new FileReader(Dateiname))
		{
			char[] chars = new char[(int)file.length()];
			in.read(chars);
			textArea.setText(new String(chars));
			retValue = true;
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Fehler beim Lesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		

		return retValue;
		
	}
	
	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
	}
	
	
	private void updateTabInfo(int tabIndex)
	{
		
		// Die Komponente, die über getComponentAt() zurückgeliefert wird ist die ScrollPane.
		JScrollPane jsp = (JScrollPane)tabPane.getComponentAt(tabIndex);
		
		// Anzeige des vollständigen Dateinamens, der im Namen der ScrollBar abgespeichert wurde.
		statusBar.setText(jsp.getName());
		
		// Über die Referenz von viewPort mit getView() wird die Komponente (JTextArea) zurückgeliefert.
		// Eine ScrollPane kann für unterschiedliche Komponenten verwendet werden (TextField, TextArea, JList, JTree, JTable...)
		JViewport viewPort = jsp.getViewport();
		
		// Konvertieren der Komponente in eine TextArea und individuellen Zeilenumbruch im Menü setzen. 
		miZeilenumbruch.setSelected(((JTextArea)viewPort.getView()).getLineWrap());

		
	}
	
	private void setLineWrap(JScrollPane scrollPane)
	{
		
		JViewport viewPort = scrollPane.getViewport();
		((JTextArea)viewPort.getView()).setLineWrap(miZeilenumbruch.isSelected());
		
	}
	
	
	private boolean queryExit()
	{
		// Benutzerdefinierte Button Texte
		String[] options = {"Ja", "Nein"};
		
		int retValue = JOptionPane.showOptionDialog(this, "Wollen Sie das Programm wirklich beenden", "Programm beenden", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
		return (retValue == JOptionPane.YES_OPTION);
		
	}
	
	
	public static void main(String[] args)
	{
		
		TabbedPane f = new TabbedPane();
		f.showFrame();

	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		
		if (queryExit())
			this.dispose();
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource() == miBeenden)
			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		else if (e.getSource() == miOeffnen)
			openFile();
		else if (e.getSource() == miSchliessen)
		{
			
			tabPane.remove(tabPane.getSelectedIndex());
			miSchliessen.setEnabled(tabPane.getTabCount() > 0);
		}
		else if (e.getSource() == miRegisterOben)
			tabPane.setTabPlacement(JTabbedPane.TOP);
		else if (e.getSource() == miRegisterLinks)
			tabPane.setTabPlacement(JTabbedPane.LEFT);
		else if (e.getSource() == miRegisterRechts)
			tabPane.setTabPlacement(JTabbedPane.RIGHT);
		else if (e.getSource() == miRegisterUnten)
			tabPane.setTabPlacement(JTabbedPane.BOTTOM);
		else if (e.getSource() == miZeilenumbruch)
		{
			setLineWrap((JScrollPane)tabPane.getComponentAt(tabPane.getSelectedIndex()));	
		}
		
		
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		
		// Einge Registerkarte wurde ausgewählt
		if (e.getSource() == tabPane)
		{
			// Dieses Ereignis tritt auch auf, wenn das letzte Register aus der
			// Tabbed Pane entfernt wurde! Deshalb sicherheitshalber den Index abfragen.
			
			if (tabPane.getSelectedIndex() != -1)
				updateTabInfo(tabPane.getSelectedIndex());
			else
				// All Registerkarten sind geschlossen
				statusBar.setText("Bereit");
			
		}
		
		
		
		
	}

}
