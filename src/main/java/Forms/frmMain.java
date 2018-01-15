package Forms;

        import javax.swing.*;
        import javax.swing.event.InternalFrameEvent;
        import javax.swing.event.InternalFrameListener;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class frmMain extends JFrame {
    private JPanel panelMain;
    private JButton buttonExportInvoice;
    private JButton buttonImportInvoice;
    private JButton buttonOverview;
    private JButton buttonAddresses;
    private JButton buttonInvoiceExport;
    static final int xOffset = 30, yOffset = 30;
    static JDesktopPane desktop;
    static int openFrameCount = 0;
    static frmExportInvoice frameExportInvoice;
    static frmImportInvoice frameImportInvoice;
    static frmAddress frameAddress;
    int inset = 50;

    private JPanel panel1;

    public frmMain() {
        super("UrbanInvoicing v1.0 (Alpha - Development Build)");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        setContentPane(panelMain);
        desktop = new JDesktopPane();

        frameExportInvoice = new frmExportInvoice(getSize().width - inset * 2, getSize().height - inset * 2);
        frameImportInvoice = new frmImportInvoice(getSize().width - inset * 2, getSize().height - inset * 2);
        frameAddress = new frmAddress(getSize().width - inset * 2, getSize().height - inset * 2);
        setLocation(xOffset * 1, yOffset * 1);
        setContentPane(panelMain);

        frameExportInvoice.addInternalFrameListener(new InternalFrameListener() {
            public void internalFrameOpened(InternalFrameEvent e) {

            }

            public void internalFrameClosing(InternalFrameEvent e) {

            }

            public void internalFrameClosed(InternalFrameEvent e) {
                setContentPane(panelMain);
            }

            public void internalFrameIconified(InternalFrameEvent e) {

            }

            public void internalFrameDeiconified(InternalFrameEvent e) {

            }

            public void internalFrameActivated(InternalFrameEvent e) {

            }

            public void internalFrameDeactivated(InternalFrameEvent e) {

            }
        });
        frameImportInvoice.addInternalFrameListener(new InternalFrameListener() {
            public void internalFrameOpened(InternalFrameEvent e) {

            }

            public void internalFrameClosing(InternalFrameEvent e) {

            }

            public void internalFrameClosed(InternalFrameEvent e) {
                setContentPane(panelMain);
            }

            public void internalFrameIconified(InternalFrameEvent e) {

            }

            public void internalFrameDeiconified(InternalFrameEvent e) {

            }

            public void internalFrameActivated(InternalFrameEvent e) {

            }

            public void internalFrameDeactivated(InternalFrameEvent e) {

            }
        });
        frameAddress.addInternalFrameListener(new InternalFrameListener() {
            public void internalFrameOpened(InternalFrameEvent e) {

            }

            public void internalFrameClosing(InternalFrameEvent e) {

            }

            public void internalFrameClosed(InternalFrameEvent e) {
                setContentPane(panelMain);
            }

            public void internalFrameIconified(InternalFrameEvent e) {

            }

            public void internalFrameDeiconified(InternalFrameEvent e) {

            }

            public void internalFrameActivated(InternalFrameEvent e) {

            }

            public void internalFrameDeactivated(InternalFrameEvent e) {

            }
        });

        buttonExportInvoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameExportInvoice.setVisible(true);
                desktop.add(frameExportInvoice);
                try {
                    frameExportInvoice.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                }
                setContentPane(desktop);
            }
        });
        buttonImportInvoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameImportInvoice.setVisible(true);
                desktop.add(frameImportInvoice);
                try {
                    frameImportInvoice.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                }
                setContentPane(desktop);
            }
        });
        buttonAddresses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameAddress.setVisible(true);
                desktop.add(frameAddress);
                try {
                    frameAddress.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                }
                setContentPane(desktop);
            }
        });
    }
}