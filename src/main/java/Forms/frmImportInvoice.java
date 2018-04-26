package Forms;

import Classes.clsExtendedTableModel;
import Classes.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class frmImportInvoice  extends JInternalFrame{
    private JButton speichernButton;
    private JLabel bruttoLabel;
    private JLabel mwstLabel;
    private JLabel nettoLabel;
    private JPanel mainPanel;
    private String[] tableColumns;
    private clsInvoicePosition[][] tableData;
    private JComboBox<String> comboBoxArtikel;
    private JComboBox<String> comboBoxTyp;
    private JTable tablePositions;
    private JButton zurückZumHauptmenüButton;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonDelete;
    private JScrollPane scrollPane1;
    private JComboBox comboBoxCostumer;
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
        this.ResetForm();
        zurückZumHauptmenüButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)tablePositions.getModel();
                model.addRow(new clsInvoicePosition[1]);
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)tablePositions.getModel();
                model.removeRow(tablePositions.getSelectedRow());
            }
        });
    }

    private void ResetForm(){
        bruttoLabel.setText("0,00");
        mwstLabel.setText("0,00");
        nettoLabel.setText("0,00");
        tableData = new clsInvoicePosition[0][0];
        tableColumns = GetTableColumns();
        DefaultTableModel tmpTableModel = new DefaultTableModel(tableData, tableColumns);
        JTable tmpTable = new JTable( tmpTableModel);
        tablePositions = tmpTable;
        JViewport tmpViewPort = new JViewport();
        tablePositions.setFillsViewportHeight(true);
        tmpViewPort.add(tablePositions );
        scrollPane1.setViewport(tmpViewPort);

        comboBoxArtikel = new JComboBox<String>();
        comboBoxTyp = new JComboBox<String>();
        String[] tmp = clsArticel.GetArticlesFromDB();
        for (int i = 0; i < tmp.length; i++)
        {
            comboBoxArtikel.addItem(tmp[i]);
        }
        tmp = clsType.GetTypesFromDB();
        for(int i = 0; i < tmp.length; i++)
        {
            comboBoxTyp.addItem(tmp[i]);
        }
        clsCustomer[] tmp2 = clsCustomer.GetCustomerFromDB();
        for(int i = 0; i < tmp2.length; i++)
        {
            comboBoxCostumer.addItem(tmp2[i].name);
        }
        tablePositions.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(comboBoxArtikel));
        tablePositions.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(comboBoxTyp));
    }

    private String[] GetTableColumns(){
        clsInvoicePosition tmpDummyObject = new clsInvoicePosition();
        String[] tmpColumns = new String[7];
        tmpColumns[0] = "Bemerkung";
        tmpColumns[1] = "Brutto";
        tmpColumns[2] = "Netto";
        tmpColumns[3] = "MwSt";
        tmpColumns[4] = "Rabat";
        tmpColumns[5] = "Artikel";
        tmpColumns[6] = "Typ";
        return tmpColumns;
    }

    private void SaveToDb(){
        clsInvoice tmpNewInvoice = new clsInvoice();
        tmpNewInvoice.SaveToDb();
        DefaultTableModel model = (DefaultTableModel)tablePositions.getModel();
        int tmpInvoiceId = clsInvoice.GetId(tmpNewInvoice);
        for (int i = 0; i < tablePositions.getRowCount(); i++)
        {
            clsInvoicePosition tmpPosition = new clsInvoicePosition();
            tmpPosition.Bemerkung = tablePositions.getCellEditor(i, 0).toString();
            tmpPosition.Brutto =  Double.parseDouble(tablePositions.getCellEditor(i, 1).toString());
            tmpPosition.Netto = Double.parseDouble(tablePositions.getCellEditor(i, 2).toString());
            tmpPosition.MwSt = Double.parseDouble(tablePositions.getCellEditor(i, 3).toString());
            tmpPosition.Rabat = Double.parseDouble(tablePositions.getCellEditor(i, 4).toString());
            int tmpArticleId = clsArticel.GetId(tablePositions.getCellEditor(i, 5).toString());
            tmpPosition.ArtikelId = tmpArticleId;
            int tmpTypeId = clsType.GetId(tablePositions.getCellEditor(i, 6).toString());
            tmpPosition.TypeId = tmpTypeId;
            tmpPosition.SetInvoiceId(tmpInvoiceId);
            tmpPosition.save();
        }
        clsCustomer tmpSelectedCustomer = clsCustomer.class.cast(comboBoxCostumer.getSelectedItem());
        tmpNewInvoice.customerId = tmpSelectedCustomer.id;
        //tmpNewInvoice.save();
        if(true) {
            dispose();
        }
    }
}
