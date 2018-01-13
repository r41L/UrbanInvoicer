package Forms;

import javax.swing.*;
import java.awt.*;

public class frmContainer extends JFrame {

    static final int xOffset = 30, yOffset = 30;
    static int openFrameCount = 0;
    static JDesktopPane desktop;
    private JPanel panel1;

    public frmContainer(){
        /*super("UrbanInvoicing v1.0 (Alpha)");
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width  - inset*2,
                screenSize.height - inset*2);
        desktop = new JDesktopPane();

        frmMain frame = new frmMain( getSize().width  - inset*2, getSize().height  - inset*2);
        frame.setVisible(true);
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}

        setContentPane(desktop);
        */
    }
}
