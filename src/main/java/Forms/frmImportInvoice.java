package Forms;

import Classes.clsExtendedTableModel;
import Classes.clsInvoicePosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class frmImportInvoice  extends JInternalFrame{
    private JTextField lieferantTextField;
    private JButton speichernButton;
    private JTable tableInvoicePositions;
    private JLabel bruttoLabel;
    private JLabel mwstLabel;
    private JLabel nettoLabel;
    private JPanel mainPanel;
    private String[] tableColumns;
    private clsInvoicePosition[] tableData;
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
        this.ResetForm();
    }

    private void ResetForm(){
        bruttoLabel.setText("0,00");
        mwstLabel.setText("0,00");
        nettoLabel.setText("0,00");

        clsExtendedTableModel tmpMode = new clsExtendedTableModel();
        tmpMode.columnNames = this.GetTableColumns();
        tmpMode.data = new clsInvoicePosition[100][100];

        tableInvoicePositions = new JTable(tmpMode);
    }

    private String[] GetTableColumns(){
        clsInvoicePosition tmpDummyObject = new clsInvoicePosition();
        Field[] tmpFields = tmpDummyObject.getClass().getFields();
        String[] tmpColumns = new String[tmpFields.length];
        for (int i = 0; i<tmpFields.length; i++)
        {
            tmpColumns[i] = tmpFields[i].getName();
        }
        return tmpColumns;
    }

    public void setData(clsInvoicePosition data) {
    }

    public void getData(clsInvoicePosition data) {
    }

    public boolean isModified(clsInvoicePosition data) {
        return false;
    }
}
