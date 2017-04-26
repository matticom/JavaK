package testProgramme;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
 
import javax.swing.*;
 
public class InvokeLaterTest extends JFrame implements ActionListener {
 
    private static int max = 555;
 
    private int i;
   
    private String fac;
 
    private JButton btn = new JButton("Calculate all factorials till ");
 
    private JTextField tf = new JTextField(4);
 
    private JPreviewDialog previewDialog = new JPreviewDialog(this);
 
    public InvokeLaterTest() {
        super("Progress Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.setAlwaysOnTop(true);
 
        btn.addActionListener(this);
        tf.setText("" + max);
 
        this.setLayout(new FlowLayout());
        this.add(btn);
        this.add(tf);
 
        this.pack();
        this.setVisible(true);
    }
 
    public static void main(String[] args) {
        new InvokeLaterTest();
    }
 
    public void actionPerformed(ActionEvent e) {
 
        try {
            int temp = Integer.valueOf(tf.getText());
            max = temp;
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        }
 
        previewDialog.clearTextArea();
 
        new Thread() {
            public void run() {
                previewDialog.showDialog();
 
                for (i = 0; i <= max; i++) {
                    fac = InvokeLaterTest.this.getFactorial(i).toString();
 
// Variante 1                  
//                  {
//                  previewDialog.updateTextArea("factorial of " + i + ": "
//                          + fac
//                          + "\n");
//                  }
 
// Variante 2                  
//                  {
//                  SwingUtilities.invokeLater(new Runnable() {
//                      public void run() {
//                          previewDialog.updateTextArea("factorial of " + i + ": "
//                                  + fac
//                                  + "\n");
//                      }
//                  });
//                  }
 
// Variante 3                  
//                    try {
//                        SwingUtilities.invokeAndWait(new Runnable() {
//                            public void run() {
//                                previewDialog.updateTextArea("factorial of " + i + ": "
//                                        + fac
//                                        + "\n");
//                            }
//                        });
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//               
                }
            }
        }.start();
    }
 
    public BigInteger getFactorial(long n) {
        BigInteger bi = new BigInteger("1");
        if (n == 0)
            return bi;
        for (long i = 1; i <= n; i++) {
            bi = bi.multiply(new BigInteger("" + i));
        }
        return bi;
    }
 
    class JPreviewDialog extends JDialog {
 
        private JTextArea tArea = new JTextArea();
 
        public JPreviewDialog(JFrame owner) {
            super(owner, "MyDialog", false);
            this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.setLocationByPlatform(true);
            this.setSize(640, 480);
 
            tArea.setEditable(false);
 
            this.add(new JScrollPane(tArea), BorderLayout.CENTER);
            this.add(btn, BorderLayout.SOUTH);
        }
 
        public void showDialog() {
            this.setVisible(true);
        }
 
        public void updateTextArea(String s) {
            tArea.append(s);
            tArea.setCaretPosition(tArea.getText().length());
        }
 
        public void clearTextArea() {
            tArea.setText("");
        }
    }
}