package Forms;

import javax.swing.*;

public class frmImportInvoice  extends JInternalFrame{
    private JTextField lieferantTextField;
    private JButton speichernButton;
    private JTable table1;
    private JLabel bruttoLabel;
    private JLabel mwstLabel;
    private JLabel nettoLabel;
    private JPanel mainPanel;
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
    }
}
