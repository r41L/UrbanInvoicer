package Forms;

import javax.swing.*;

public class frmAddress  extends JInternalFrame{
    private JButton neuButton;
    private JButton bearbeitenButton;
    private JButton zurückZumHauptmenüButton;
    private JTable table1;
    private JPanel panelMain;
    static final int xOffset = 30, yOffset = 30;
    int inset = 50;

    public frmAddress(int pWidth, int pHeight){
        super("UrbanInvoicing - Adressbuch",
                false, //resizable
                false, //closable
                false, //maximizable
                false);//iconifiable
        setSize(pWidth, pHeight);
        //frmContainer.openFrameCount = frmContainer.openFrameCount+1;
        setLocation(xOffset * 1, yOffset * 1);
        setContentPane(panelMain);
    }
}
