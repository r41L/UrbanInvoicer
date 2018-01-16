package Forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmNewAddress  extends JInternalFrame{
    private JTextField vornameTextField;
    private JTextField nachnameTextField;
    private JTextField strasseTextField;
    private JTextField plzTextField;
    private JTextField ortTextField;
    private JTextField landTextField;
    private JTextField telefoneTextField;
    private JTextField telefaxTextField;
    private JTextField emailTextField;
    private JTextArea bemerkungTextArea;
    private JButton speichernButton;
    private JTable table1;
    private JButton zurückZumHauptmenüButton;
    static final int xOffset = 30, yOffset = 30;
    int inset = 50;

    public frmNewAddress(int pWidth, int pHeight){

        zurückZumHauptmenüButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
