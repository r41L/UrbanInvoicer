package Forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmImportInvoice  extends JInternalFrame{
    private JTextField lieferantTextField;
    private JButton speichernButton;
    private JTable table1;
    private JLabel bruttoLabel;
    private JLabel mwstLabel;
    private JLabel nettoLabel;
    private JPanel mainPanel;
    private JButton zurückZumHauptmenüButton;
    static final int xOffset = 30, yOffset = 30;
    int inset = 50;

    public frmImportInvoice(int pWidth, int pHeight){
        super("UrbanInvoicing - Rechnung Eingehend",
                false, //resizable
                false, //closable
                false, //maximizable
                false);//iconifiable
        setSize(pWidth, pHeight);
        //frmContainer.openFrameCount = frmContainer.openFrameCount+1;
        setLocation(xOffset * 1, yOffset * 1);
        setContentPane(mainPanel);
        zurückZumHauptmenüButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
