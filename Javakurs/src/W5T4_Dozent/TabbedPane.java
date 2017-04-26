package W5T4_Dozent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.*;

import Util.StatusBar;
import Util.WinUtil;

public class TabbedPane extends JFrame implements WindowListener, ActionListener
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
		
		miSchliessen = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Schließen", 
				new ImageIcon(this.getClass().getResource("/images/Delete.png")), 'S', null);
		
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
		
		statusBar.setText("Bereit");
		
		
	}
	
	private void openFile()
	{
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(fcFile);
		fc.setMultiSelectionEnabled(true);
		
		if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
		
		fcFile = fc.getSelectedFile();
		
	}
	
	
	
	public void showFrame()
	{
		initFrame();
		this.setVisible(true);
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
		
		
		
	}

}
