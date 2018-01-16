        package Forms;

        import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class frmExportInvoice extends JInternalFrame {
    private JPanel panelMain;
    private JTextField kundeTextField;
    private JButton druckenButton;
    private JTable table1;
    private JButton zurückZumHauptmenüButton;
    static final int xOffset = 30, yOffset = 30;
    int inset = 50;

    public frmExportInvoice(int pWidth, int pHeight) {
        super("UrbanInvoicing - Rechnung Ausgehend",
                false, //resizable
                false, //closable
                false, //maximizable
                false);//iconifiable
        setSize(pWidth, pHeight);
        //frmContainer.openFrameCount = frmContainer.openFrameCount+1;
        setLocation(xOffset * 1, yOffset * 1);
        setContentPane(panelMain);

        zurückZumHauptmenüButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}