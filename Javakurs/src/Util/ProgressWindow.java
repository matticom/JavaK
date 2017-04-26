package Util;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class ProgressWindow extends JDialog implements Runnable, ComponentListener, MouseListener, MouseMotionListener, WindowListener
{
	private int thisWidth = 400;
	private int thisHeight = 60;
	private Color progressColor = SystemColor.activeCaption;
	private boolean showPercentage = true;
	private boolean enableCancel = true;
	private boolean showTitleBar = false;
	private boolean showAlwaysOnTop = false;
	private boolean showModal = true;
	private int progressOrientation = JProgressBar.HORIZONTAL;
	private float thisOpacity = 1.0f;
	
	private Component owner;

	private JLabel lblStatustext;
	private JProgressBar progressBar;
	private JButton btnCancel;
	
	private Point p1, p2;
	
	private boolean cancelProcess = false;
	
	/**
	 * 
	 * @param owner
	 * Eigent�mer des Fensters oder 'null' f�r Desktop.
	 */
	public ProgressWindow(Component owner)
	{
		this.owner = owner;
		initializeComponents();
	}
	
	/**
	 * 
	 * @param owner
	 *  Eigent�mer des Fensters oder 'null' f�r Desktop.
	 * @param width
	 * Breite des Fensters.
	 * @param height
	 * H�he des Fensters.
	 */
	public ProgressWindow(Component owner, int width, int height)
	{
		this(owner);
		thisWidth = width;
		thisHeight = height;
	}
	
	private void initializeComponents()
	{
		this.setLayout(new BorderLayout());
		this.setAlwaysOnTop(showAlwaysOnTop);
		
		this.setModal(showModal);
		
		this.addComponentListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		// Keine Gr�ssen�nderung
		this.setResizable(false);
		// Anzeige mit/ohne Titelleiste
		this.setUndecorated(!showTitleBar);
		
		// Das Schlie�en des Dialogs wird vom WindowListener �berwacht.
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		// Zus�tzliches Verwenden der ESCAPE-Taste um den Prozess zu beenden.
		// Lesen der ActionMap und der InputMap des Dialogs
		ActionMap actionMap = this.getRootPane().getActionMap();
		InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		String commandKey = "VK_ESCAPE";
		// Setzen der Input- und Action-Map auf die Escape-Taste �ber den angegebenen Schl�sselbegriff.
		// �bergabe des Schl�sselbegriffs an die Klasse 'KeyPreviewListener' zur Abfrage im ActionEvent.
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), commandKey);
		actionMap.put(commandKey, new KeyPreviewListener(commandKey));
		
		// Fortschrittsanzeige erstellen 
		progressBar = new JProgressBar(progressOrientation);
		progressBar.setBorderPainted(showPercentage);
		
		// Farbe f�r die Fortschrittanzeige
		progressBar.setForeground(progressColor);
		// Prozentsatz anzeigen
		progressBar.setStringPainted(true);
			
		this.add(progressBar, BorderLayout.CENTER);
		
		btnCancel = new JButton("X");
		btnCancel.setBounds(thisWidth - 16, 2, 20, 14);
		btnCancel.setFont(btnCancel.getFont().deriveFont(10.0f));
		btnCancel.setMargin(new Insets(0,0,0,0));
		btnCancel.setToolTipText("Abbrechen");
		btnCancel.setBackground(Color.RED);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFocusPainted(false);
		btnCancel.setRolloverEnabled(false);
		//btnCancel.setBorderPainted(false);
		// F�r den Button einen MouseListener und keinen ActionListener verwenden damit nicht
		// durch bet�tigen der Eingabetaste irrt�mlich das Programm beendet wird.
		btnCancel.addMouseListener(this);
		btnCancel.setVisible(false);
		
		progressBar.add(btnCancel);
		
		lblStatustext = new JLabel();
		lblStatustext.setSize(new Dimension(this.getWidth(), thisHeight / 3));
		lblStatustext.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
		lblStatustext.setHorizontalAlignment(JLabel.CENTER);
		lblStatustext.setVisible(false);
		this.add(lblStatustext, BorderLayout.PAGE_END);
		
		// setSize() l�st das das componentResized-Ereignis aus.
		// Dort wird der Cancel-Button positioniert. 
		// Deswegen setSize() erst ganz zum Schluss aufrufen.
		this.setSize(new Dimension(thisWidth, thisHeight));
		
	}

	/**
	 * Zeigt den angegebenen Statustext an. 
	 * @param msg
	 * Statustext
	 */
	public void setMessage(String msg)
	{
		if (msg.length() > 0)
		{
			lblStatustext.setText(msg);
			lblStatustext.setVisible(true);
		}
		else
			lblStatustext.setVisible(false);
	}
	
	/**
	 * Gibt den aktuellen Statustext zur�ck.
	 * @return
	 */
	public String getMessage()
	{
		return lblStatustext.getText();
	}
	
	/**
	 * Setzt den aktuellen in der Fortschrittsanzeige.
	 * @param value
	 */
	public void setValue(int value)
	{
		progressBar.setValue(value);
	}
	
	/**
	 * Gibt den aktuellen Wert der Fortschrittsanzeige zur�ck.
	 * @return
	 */
	public int getValue()
	{
		return progressBar.getValue();
	}
	
	/**
	 * Setzt den Maximalwert der Fortschrittsanzeige.
	 * Entspricht 100 %. 
	 * @param value
	 */
	public void setMaxValue(int value)
	{
		progressBar.setMaximum(value);
	}
	
	/**
	 * Gibt den Maximalwert der Fortschrittsanzeige zur�ck.
	 * @return
	 */
	public int getMaxValue()
	{
		return progressBar.getMaximum();
	}
	
	/**
	 * Setzt die Farbe der Fortschrittsanzeige.
	 * @param color
	 */
	public void setProgressbarColor(Color color)
	{
		progressColor = color;
		progressBar.setForeground(color);
	}
	
	/**
	 * Gibt an, ob f�r den Dialog eine Titelzeile angezeigt werden soll oder nicht.
	 * @param value
	 */
	public void showTitleBar(boolean value)
	{
	    this.showTitleBar = value;
	    this.setUndecorated(!value);
	    if (value) enableCancel = true;
	    if (!value)
	   	 btnCancel.setVisible(enableCancel);
	}
	
	public void showAlwaysOnTop(boolean value)
	{
	    this.showAlwaysOnTop = value;
	    this.setAlwaysOnTop(value);
	}
	
	
	public void showModal(boolean value)
	{
	    this.showModal = value;
	    this.setModal(showModal);
	}
	
	/**
	 * Gibt an, ob der prozentuale Wert innerhalb der Fortschrittsanzeige
	 * angezeigt werden soll.
	 * @param value
	 */
	public void showPercentage(boolean value)
	{
		showPercentage = value;
		progressBar.setStringPainted(value);
	}
	
	/**
	 * Gibt an, ob ein Abbruch durch den Benutzer ausgew�hlt werden kann.
	 * @param value
	 */
	public void showCancelButton(boolean value)
	{
	    enableCancel = value;
	    
	    // Die Titelzeile enth�lt bereits einen Abbruch-Button
	    if (showTitleBar) 
	        btnCancel.setVisible(false);
	    else
	        btnCancel.setVisible(value);
	}
	
	/**
	 * Zeigt eine unbestimmte Fortschrittsnazeige an.
	 * @param value
	 */
	public void setIndeterminate(boolean value)
	{
		progressBar.setIndeterminate(value);
	}
	
	/**
	 * Benutzerdefinerter Text in der Fortschrittsanzeige erstellen
	 * @param s
	 */
	public void setString(String s)
	{
		 progressBar.setString(s);
		 showPercentage(true);
	}
	
	public JProgressBar getProgressbar()
	{
		return progressBar;
	}
	
	/**
	 * Gibt den Statustext der Fortschritssanzeige zur�ck.
	 * @return
	 */
	public JLabel getStatusLabel()
	{
		return lblStatustext;
	}
	
	private void centerOnParent(Component owner)
	{
		this.setLocationRelativeTo(owner);
	}
	
	/**
	 * Setzt die Transparenz des Fensters.
	 * @param value
	 * G�ltige Werte: 0 - 100.
	 */
	public void setTransparency(int  value)
	{
		thisOpacity = (1 - (value / 100.0F));
		this.setOpacity(thisOpacity);
	}
	
	/**
	 * Bestimmt die Breite der Fortschrittsanzeige in Pixeln.
	 * @param value
	 */
	public void setWidth(int  value)
	{
		thisWidth = value;
		this.setSize(thisWidth, thisHeight);
	}
	
	/**
	 * Bestimmt die H�he der Fortschrittsanzeige in Pixeln.
	 * @param value
	 */
	public void setHeight(int  value)
	{
		thisHeight = value;
		this.setSize(thisWidth, thisHeight);
	}
	
	/**
	 * Gibt den Abbruch-Status zur�ck.
	 * @return
	 */
	public boolean cancelRequest()
	{
		return this.cancelProcess;
	}
	
	/**
	 * Setzt die Abbruchbedingung.
	 * @param value
	 */
	public void setCancelRequest(boolean value)
	{
		this.cancelProcess = value;
	}
	
	/**
	 * Zeigt die Fortschrittsanzeige an.
	 */
	private void showWindow()
	{
		centerOnParent(owner);
		this.setVisible(true);

	}

	
	/**
	 * Schlie�t die Fortschrittsanzeige.
	 */
	public void close()
	{
		this.dispose();
	}
	
	@Override
	public void run()
	{
		showWindow();
	}

	private class KeyPreviewListener extends AbstractAction
	{

		public KeyPreviewListener(String commandKey)
		{
			super(commandKey);
			putValue(ACTION_COMMAND_KEY, commandKey);
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{

			if (e.getActionCommand().equalsIgnoreCase("VK_ESCAPE"))
			{
				if (enableCancel)
					setCancelRequest(true);
			}
				
		}

	}
	
	@Override
	public void componentHidden(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
	    // Geht nur, wenn der Dialog keine Titelzeile hat.
		if (e.getSource() ==  this && !showTitleBar)
		{
			this.setShape(new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 5, 5));
			btnCancel.setLocation(thisWidth - btnCancel.getWidth()- 2, 2);
		}
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// x und y Position des Mauszeigers ermitteln
		p2 = e.getPoint();
		// Obere Linke Ecke (x,y) des Fensters ermitteln
		Point pLocation = this.getLocation();
		// �bersetzen der x und y Koordinaten entlang der
		// X- und Y-Achse
		pLocation.translate(p2.x - p1.x, p2.y - p1.y);
		// Neue Position des Fensters etzen
		this.setLocation(pLocation);
	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getSource().equals(btnCancel))
		    this.setCancelRequest(true);
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// x und y Position des Mauszeigers ermitteln
		p1 = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		
		// TODO Auto-generated method stub
		
	}

    @Override
    public void windowActivated(WindowEvent e)
    {
        // TODO Automatisch generierter Methodenstub
        
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        // TODO Automatisch generierter Methodenstub
        
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
   	  // Wirkt wie ein Abbruch
        if (enableCancel)
            this.setCancelRequest(true);
        
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
        // TODO Automatisch generierter Methodenstub
        
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
        // TODO Automatisch generierter Methodenstub
        
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
        // TODO Automatisch generierter Methodenstub
        
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
        // TODO Automatisch generierter Methodenstub
        
    }
	
}
