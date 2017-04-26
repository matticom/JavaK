package W4T4_MK;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class JackpotKnacker extends JFrame
{
	private JButton[] buttonArray = new JButton[49], superArray = new JButton[10];
	private boolean[] buttonClickedArray = new boolean[49], superClickedArray = new boolean[10];
	private int[] getippteZahlen = new int[6];
	private int getippteSuperZahl = 20;

	private JButton startZiehung, exitBtn, tippLoeschen;
	private JLabel lottoFeld, superFeld, anzeigeGezZahlen, anzeigeSuperZahl, wertSuperZahl, ergebnis, erg6Zahlen, ergSuperZahl;
	private JLabel[] gezogenZahlenArray = new JLabel[6];

	private final int leftOffset = 20;
	private final int topOffset = 50;
	private final int boxHeight = 20;
	private final int boxWidth = 20;
	private final int boxDistance = 2;

	int buttonNumber;
	int titleHeight;
	int borderWidth;

	boolean istBereit6Zahlen, istBereitSuperZahl;

	int[] lotto = new int[7];

	public JackpotKnacker()
	{

		initializeComponents();

	}

	private void initializeComponents()
	{

		this.setTitle("Jackpot Knacker");
		this.setSize(650, 400);
		startZiehung = new JButton("Starte die Ziehung der Lottozahlen");
		startZiehung.setBounds(20, 290, 230, 30);
		startZiehung.setHorizontalAlignment(JLabel.CENTER);
		startZiehung.addActionListener(new ziehungListener());
		this.add(startZiehung);

		lottoFeld = new JLabel("Wähle dein Lotto Tipp:");
		lottoFeld.setBounds(20, 15, 150, 30);
		lottoFeld.setHorizontalAlignment(JLabel.LEFT);
		this.add(lottoFeld);

		superFeld = new JLabel("Wähle dein Superzahl:");
		superFeld.setBounds(20, 216, 150, 30);
		superFeld.setHorizontalAlignment(JLabel.LEFT);
		this.add(superFeld);

		anzeigeGezZahlen = new JLabel("Die gezogenen Zahlen lauten:");
		anzeigeGezZahlen.setBounds(300, 15, 200, 30);
		anzeigeGezZahlen.setHorizontalAlignment(JLabel.LEFT);
		this.add(anzeigeGezZahlen);
		anzeigeGezZahlen.setVisible(false);

		anzeigeSuperZahl = new JLabel("Die gezogene Superzahlen lautet:");
		anzeigeSuperZahl.setBounds(300, 80, 200, 30);
		anzeigeSuperZahl.setHorizontalAlignment(JLabel.LEFT);
		this.add(anzeigeSuperZahl);
		anzeigeSuperZahl.setVisible(false);

		ergebnis = new JLabel("Ergebnis:");
		ergebnis.setBounds(300, 150, 200, 30);
		ergebnis.setHorizontalAlignment(JLabel.LEFT);
		this.add(ergebnis);
		ergebnis.setVisible(false);

		erg6Zahlen = new JLabel("erg6Zahlen:");
		erg6Zahlen.setBounds(300, 180, 330, 30);
		erg6Zahlen.setHorizontalAlignment(JLabel.LEFT);
		this.add(erg6Zahlen);
		erg6Zahlen.setVisible(false);

		ergSuperZahl = new JLabel("ergSuperZ");
		ergSuperZahl.setBounds(300, 200, 300, 30);
		ergSuperZahl.setHorizontalAlignment(JLabel.LEFT);
		this.add(ergSuperZahl);
		ergSuperZahl.setVisible(false);

		exitBtn = new JButton("Beenden");
		exitBtn.setBounds(350, 280, 140, 30);
		exitBtn.addActionListener(new ButtonBeendenActionListener());
		this.add(exitBtn);

		tippLoeschen = new JButton("Tipp zurücksetzen");
		tippLoeschen.setBounds(20, 330, 230, 30);
		tippLoeschen.addActionListener(new ButtonTippDelActionListener());
		this.add(tippLoeschen);

		// Keine Grössenänderung des Frames
		this.setResizable(false);

		// Layout Manager ausschalten
		this.setLayout(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Die Höhe der Titelzeile ermitteln.
		titleHeight = this.getInsets().top;
		// Die Breite des Rahmens ermitteln.
		borderWidth = this.getInsets().left;

		int i = 0;
		int zahl = 1;

		// Zeichnen der farbigen Rechtecke
		// Anzahl Zeilen
		for (int y = topOffset + titleHeight; i < 7; y += (boxHeight + boxDistance))
		{
			i++;
			int j = 0;
			// Anzahl Boxen pro Zeile
			for (int x = leftOffset + borderWidth; j < 7; x += (boxWidth + boxDistance))
			{
				j++;
				// g.drawRect(x, y, boxWidth, boxHeight);
				buttonArray[zahl - 1] = new JButton(Integer.toString(zahl));
				buttonArray[zahl - 1].setBounds(x, y, boxWidth, boxHeight);
				buttonArray[zahl - 1].setHorizontalAlignment(JLabel.CENTER);
				buttonArray[zahl - 1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				buttonArray[zahl - 1].setBackground(Color.WHITE);
				buttonArray[zahl - 1].setFocusPainted(false);
				buttonArray[zahl - 1].setFont(new Font(this.getContentPane().getFont().getFamily(), Font.PLAIN, 13));
				buttonArray[zahl - 1].addActionListener(new btnClickListener());

				this.add(buttonArray[zahl - 1]);
				// labelzeichnen();
				zahl++;

			}
		}

		int j = 0;
		for (int x = leftOffset + borderWidth; j < 10; x += (boxWidth + boxDistance))
		{

			// g.drawRect(x, y, boxWidth, boxHeight);
			superArray[j] = new JButton(Integer.toString(j));
			superArray[j].setBounds(x, 250, boxWidth, boxHeight);
			superArray[j].setHorizontalAlignment(JLabel.CENTER);
			superArray[j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			superArray[j].setBackground(Color.WHITE);
			superArray[j].setFocusPainted(false);
			superArray[j].setFont(new Font(this.getContentPane().getFont().getFamily(), Font.PLAIN, 13));
			superArray[j].addActionListener(new superClickListener());

			this.add(superArray[j]);
			// labelzeichnen();
			j++;

		}

		j = 0;
		for (int x = leftOffset + borderWidth + 280; j < 6; x += (boxWidth + boxDistance))
		{

			// g.drawRect(x, y, boxWidth, boxHeight);
			gezogenZahlenArray[j] = new JLabel();
			gezogenZahlenArray[j].setBounds(x, topOffset + titleHeight, boxWidth, boxHeight);
			gezogenZahlenArray[j].setHorizontalAlignment(JLabel.CENTER);
			gezogenZahlenArray[j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			gezogenZahlenArray[j].setBackground(Color.WHITE);
			gezogenZahlenArray[j].setFont(new Font(this.getContentPane().getFont().getFamily(), Font.PLAIN, 13));
			gezogenZahlenArray[j].setVisible(false);

			this.add(gezogenZahlenArray[j]);
			// labelzeichnen();
			j++;

		}

		wertSuperZahl = new JLabel();
		wertSuperZahl.setBounds(leftOffset + borderWidth + 280, topOffset + titleHeight + 65, boxWidth, boxHeight);
		wertSuperZahl.setHorizontalAlignment(JLabel.CENTER);
		wertSuperZahl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		wertSuperZahl.setBackground(Color.WHITE);
		wertSuperZahl.setFont(new Font(this.getContentPane().getFont().getFamily(), Font.PLAIN, 13));
		wertSuperZahl.setVisible(false);
		this.add(wertSuperZahl);
	}

	// private void darstellenGezZahlen

	private void ziehung()
	{
		Random zahl = new Random();

		for (int j = 0; j < 7; j++)
		{
			lotto[j] = 0;

		}

		int gezogeneZahl;
		int i = 0;

		while (i < 7)
		{
			if (i < 6)
			{
				gezogeneZahl = zahl.nextInt(49) + 1;
				// Suche, ob gezogene Zahl bereits existiert
				if (Arrays.binarySearch(lotto, gezogeneZahl) >= 0)
					continue;
				else
				{
					// wenn nicht vorhanden, ins Array schreiben und sortieren
					// für nächste binäre Suche
					lotto[1] = gezogeneZahl;
					Arrays.sort(lotto);
					i++;
				}
			}
			else
			{
				// Superzahl zum Schluss an Start des Arrays
				lotto[0] = zahl.nextInt(10);
				i++;
			}
		}

		Ausgabe(lotto);
		System.out.println("\nDie SuperZahl lautet: " + lotto[0]);
	}

	private static void Ausgabe(int[] arr)
	{
		System.out.println("Die Lottozahlen sind: ");

		for (int i = 1; i < arr.length; i++)
			System.out.printf("%4d", arr[i]);

		System.out.println();

	}

	private void initFrame()
	{
		// Inder Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);

	}

	public void showFrame()
	{
		initFrame();
		this.setVisible(true);

	}

	public static void main(String[] args)
	{
		JackpotKnacker f = new JackpotKnacker();
		f.showFrame();
	}

	private class btnClickListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof JButton)
			{
				//Welcher Btn wurde geklickt
				JButton currentBtn = (JButton) e.getSource();
				for (int i = 0; i < 49; i++)
				{
					if (buttonArray[i] == currentBtn)
						buttonNumber = i;

				}
				
				//Wieviel Felder wurden schon geklickt (i = Zähler)
				int i = 0;
				for (boolean a : buttonClickedArray)
				{
					if (a == true)
						i++;
				}
				
				// Wenn noch nicht 6 Felder ausgewählten wurden
				if (buttonClickedArray[buttonNumber] == false && i < 6)
				{
					buttonArray[buttonNumber].setBackground(Color.ORANGE);
					buttonArray[buttonNumber].setFont(new Font(JackpotKnacker.this.getContentPane().getFont().getFamily(), Font.BOLD, 14));
					buttonClickedArray[buttonNumber] = true;
				}
				else	// Wenn 6 Felder ausgewählten wurden
				{
					buttonArray[buttonNumber].setBackground(Color.WHITE);
					buttonArray[buttonNumber].setFont(new Font(JackpotKnacker.this.getContentPane().getFont().getFamily(), Font.PLAIN, 13));
					buttonClickedArray[buttonNumber] = false;
				}

				// getippten Zahlen werden in das gezogene Zahlen Array geschrieben
				int z = 0;
				for (int j = 0; j < 49; j++)
				{
					if (buttonClickedArray[j] == true)
					{
						getippteZahlen[z] = j + 1;
						z++;
						if (getippteZahlen[5] != 0)
							istBereit6Zahlen = true;
						else
							istBereit6Zahlen = false;
					}

				}

				// System.out.println(Arrays.toString(getippteZahlen));

			}

		}

	}

	private class superClickListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof JButton)
			{
				getippteSuperZahl = 20;
				
				// Welcher SuperZahl Btn wurde geklickt
				JButton currentBtn = (JButton) e.getSource();
				for (int i = 0; i < 10; i++)
				{
					if (superArray[i] == currentBtn)
						buttonNumber = i;

				}
				
				//Wieviel Superzahl Felder wurden schon geklickt (i = Zähler)
				int i = 0;
				for (boolean a : superClickedArray)
				{
					if (a == true)
						i++;
				}
				
				// Wenn noch kein Superzahl Feld ausgewählten wurden
				if (superClickedArray[buttonNumber] == false && i < 1)
				{
					superArray[buttonNumber].setBackground(Color.ORANGE);
					superArray[buttonNumber].setFont(new Font(JackpotKnacker.this.getContentPane().getFont().getFamily(), Font.BOLD, 14));
					superClickedArray[buttonNumber] = true;
				}
				else   // Wenn Superzahl Feld ausgewählten wurden
				{
					superArray[buttonNumber].setBackground(Color.WHITE);
					superArray[buttonNumber].setFont(new Font(JackpotKnacker.this.getContentPane().getFont().getFamily(), Font.PLAIN, 13));
					superClickedArray[buttonNumber] = false;
				}

				// getippte Superzahl wird in das gezogene Superzahl
				for (int j = 0; j < 10; j++)
				{
					if (superClickedArray[j] == true)
					{
						getippteSuperZahl = j;
						if (getippteSuperZahl < 10)
							istBereitSuperZahl = true;
						else
							istBereitSuperZahl = false;
					}

				}

				System.out.println(getippteSuperZahl);// Arrays.toString(getippteZahlen));

			}

		}

	}

	private class ziehungListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			for (int j = 0; j < 49; j++)
			{
				if (buttonClickedArray[j] == true)
					buttonArray[j].setBackground(Color.ORANGE);
			}

			for (int j = 0; j < 10; j++)
			{
				if (superClickedArray[j] == true)
					superArray[j].setBackground(Color.ORANGE);
			}

			if (istBereit6Zahlen == true && istBereitSuperZahl == true)
			{

				ziehung();

				for (int j = 0; j < 6; j++)
				{
					gezogenZahlenArray[j].setText(Integer.toString(lotto[j + 1]));
					gezogenZahlenArray[j].setVisible(true);
				}

				wertSuperZahl.setText(Integer.toString(lotto[0]));
				wertSuperZahl.setVisible(true);

				anzeigeGezZahlen.setVisible(true);
				anzeigeSuperZahl.setVisible(true);

				ergebnis.setVisible(true);

				int z = 0;
				for (int j = 0; j < 5; j++)
				{
					int gezognZahl = Integer.valueOf(gezogenZahlenArray[j].getText());
					if (buttonClickedArray[gezognZahl - 1] == true)
					{
						z++;
						buttonArray[gezognZahl - 1].setBackground(Color.GREEN);
					}

				}

				if (superClickedArray[Integer.valueOf(wertSuperZahl.getText())] == true)
				{
					ergSuperZahl.setText("Die Superzahl war richtig!");
					superArray[Integer.valueOf(wertSuperZahl.getText())].setBackground(Color.GREEN);
				}
				else
					ergSuperZahl.setText("Die Superzahl war leider nicht richtig!");

				if (z == 0)
					erg6Zahlen.setText("Es war leider keine deiner Zahlen richtig bei 6 aus 49!");
				else if (z == 1)
					erg6Zahlen.setText("Es war 1 deiner Zahlen richtig bei 6 aus 49!");
				else
					erg6Zahlen.setText("Es waren " + z + " deiner Zahlen richtig bei 6 aus 49!");

				erg6Zahlen.setForeground(Color.BLUE);
				erg6Zahlen.setVisible(true);
				ergSuperZahl.setForeground(Color.BLUE);
				ergSuperZahl.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(startZiehung, "Du musst erst alle Zahlen tippen!", "FehlerHex", JOptionPane.ERROR_MESSAGE);

		}

	}

	private class ButtonBeendenActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);

		}

	}

	private class ButtonTippDelActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			for (JButton btn : buttonArray)
			{
				btn.setBackground(Color.WHITE);
				btn.setFont(new Font(JackpotKnacker.this.getContentPane().getFont().getFamily(), Font.PLAIN, 13));
			}

			for (int j = 0; j < 49; j++)
			{
				buttonClickedArray[j] = false;

			}
			
			// for (boolean button: buttonClickedArray)
			// 		button = false;

			for (JButton btn : superArray)
			{
				btn.setBackground(Color.WHITE);
				btn.setFont(new Font(JackpotKnacker.this.getContentPane().getFont().getFamily(), Font.PLAIN, 13));
			}

			for (int j = 0; j < 9; j++)
			{
				superClickedArray[j] = false;

			}
			
			istBereit6Zahlen = false;
			istBereitSuperZahl = false;
			for (JLabel btn : gezogenZahlenArray)
				btn.setVisible(false);

			wertSuperZahl.setVisible(false);
			anzeigeGezZahlen.setVisible(false);
			anzeigeSuperZahl.setVisible(false);
			ergebnis.setVisible(false);
			erg6Zahlen.setVisible(false);
			ergSuperZahl.setVisible(false);

		}

	}

}
