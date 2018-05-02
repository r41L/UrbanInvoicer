package Forms;

import Classes.*;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class frmImportInvoice  extends JInternalFrame{
    private JButton buttonSave;
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
    private JButton calcButton;
    private JTextField textFieldDate;
    static final int xOffset = 30, yOffset = 30;
    int inset = 50;
    private ArrayList<String> ArticlesFromDB =  clsArticel.GetArticlesFromDB();

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
                ResetForm();
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
        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SaveToDb();
                ResetForm();
            }
        });
        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FillArtikel();
                CalcSum();
            }
        });
        CellEditorListener ChangeNotification = new CellEditorListener() {

            public void editingCanceled(ChangeEvent e) {
                FillArtikel();
                CalcRows();
                CalcSum();
            }
            public void editingStopped(ChangeEvent e) {
                FillArtikel();
                CalcRows();
                CalcSum();
            }
        };
        tablePositions.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);
    }

    private void FillArtikel() {
        try {
            for (int i = 0; i < tablePositions.getRowCount(); i++) {
                if (tablePositions.getValueAt(i, 5) != null
                        && tablePositions.getValueAt(i, 2) != null) {
                    clsArticel tmpArticle = new clsArticel();
                    tmpArticle.name = tablePositions.getValueAt(i, 5).toString();
                    boolean tmpExists = false;
                    RefreshArticlesFromDB();
                    for (String s : this.ArticlesFromDB) {
                        if (!tmpExists && tmpArticle.name.equals(s)) {
                            tmpExists = true;
                        }
                    }

                    if (!tmpExists) {
                        this.ArticlesFromDB.add(tmpArticle.name);
                        try {
                            tmpArticle.vatRate = Double.parseDouble(tablePositions.getValueAt(i, 2).toString().replace(',','.'));
                            tmpArticle.save();
                            RefreshArticlesFromDB();
                        } catch (NumberFormatException e1) {
                            JOptionPane.showMessageDialog(null, "In der Spalte Mwst ist ein Fehler", "Error", JOptionPane.OK_OPTION);
                        }
                    }
                }
            }
        } catch (HeadlessException e1) {

            JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.OK_OPTION);
        }
    }

    private void RefreshArticlesFromDB() {
        this.ArticlesFromDB = clsArticel.GetArticlesFromDB();
        String[] tmp = this.ArticlesFromDB.toArray(new String[0]);
        comboBoxArtikel.removeAllItems();
        for (int i = 0; i < tmp.length; i++)
        {
            comboBoxArtikel.addItem(tmp[i]);
        }
    }

    private void CalcRows() {
        for (int i = 0; i < tablePositions.getRowCount(); i++)
        {
            if (tablePositions.getValueAt(i,5) != null && (tablePositions.getValueAt(i,2) ==null || tablePositions.getValueAt(i,2).toString().isEmpty())) {
                tablePositions.setValueAt(clsArticel.GetMwst(tablePositions.getValueAt(i, 5).toString().replace(',','.')), i, 2);
            }
            if (tablePositions.getValueAt(i, 2)!=null) {
                if (tablePositions.getValueAt(i, 1) != null && (tablePositions.getValueAt(i, 3) == null || tablePositions.getValueAt(i,3).toString().isEmpty())) {
                    {
                        double tmpNetto = Double.parseDouble(tablePositions.getValueAt(i, 1).toString()) / (1 + (Double.parseDouble(tablePositions.getValueAt(i, 2).toString()) / 100.));
                        String tmpNettoString = new DecimalFormat("0.00").format(tmpNetto);
                        tmpNettoString.replace(',','.');
                        tablePositions.setValueAt(tmpNettoString, i, 3);
                    }
                }

                if (tablePositions.getValueAt(i, 3) != null && (tablePositions.getValueAt(i, 1) == null || tablePositions.getValueAt(i,1).toString().isEmpty())) {
                    double tmpBrutto = Double.parseDouble(tablePositions.getValueAt(i, 3).toString()) * (1 + (Double.parseDouble(tablePositions.getValueAt(i, 2).toString()) / 100.));
                    String tmpBruttoString = new DecimalFormat("0.00").format(tmpBrutto);
                    tmpBruttoString.replace(',','.');
                    tablePositions.setValueAt(tmpBruttoString, i, 1);
                }
            }
            if(tablePositions.getValueAt(i,4)!=null)
            {
                tablePositions.setValueAt(0.00,i,4);
            }
        }
    }

    private void CalcSum() {
        Double tmpBrutto = 0.00;
        Double tmpNetto = 0.00;
        Double tmpMwst = 0.00;
        CalcRows();

        for (int i = 0; i < tablePositions.getRowCount(); i++) {
            try {
                if  (!tablePositions.getValueAt(i,1).toString().isEmpty())
                    tmpBrutto += Double.parseDouble(tablePositions.getValueAt(i, 1).toString().replace(',','.'));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "In der Spalte Brutto ist ein Fehler.", "Error", JOptionPane.OK_OPTION);
                return;
            }

            try {
                if  (!tablePositions.getValueAt(i,3).toString().isEmpty())
                    tmpNetto += Double.parseDouble(tablePositions.getValueAt(i, 3).toString().replace(',','.'));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "In der Spalte Netto ist ein Fehler.", "Error", JOptionPane.OK_OPTION);
                return;
            }
        }

        this.bruttoLabel.setText(tmpBrutto.toString().replace(',','.'));
        this.nettoLabel.setText(tmpNetto.toString().replace(',','.'));
        Double tmpBruttoTmp = Math.round(tmpBrutto*100.)/100.;
        Double tmpNettoTmp = Math.round(tmpNetto*100.)/100.;
        tmpMwst = tmpBruttoTmp - tmpNettoTmp;
        this.mwstLabel.setText(new DecimalFormat("0.00").format(tmpMwst));
    }

    private void ResetForm(){
        bruttoLabel.setText("0.00");
        mwstLabel.setText("0.00");
        nettoLabel.setText("0.00");
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
        String[] tmp = ArticlesFromDB.toArray(new String[0]);
        comboBoxArtikel.removeAllItems();
        comboBoxCostumer.removeAllItems();
        comboBoxTyp.removeAllItems();
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
        comboBoxArtikel.setEditable(true);
        tablePositions.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(comboBoxArtikel));
        tablePositions.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(comboBoxTyp));

        GregorianCalendar gc = new GregorianCalendar();
        int tmpMonth = (gc.get(Calendar.MONTH)+1);
        String tmpMonthString;
        if (tmpMonth > 10)
            tmpMonthString = String.valueOf(tmpMonth);
        else
            tmpMonthString = "0"+String.valueOf(tmpMonth);
        this.textFieldDate.setText(gc.get(Calendar.DAY_OF_MONTH) +"." +tmpMonthString+"." +gc.get(Calendar.YEAR));
    }

    private String[] GetTableColumns(){
        clsInvoicePosition tmpDummyObject = new clsInvoicePosition();
        String[] tmpColumns = new String[7];
        tmpColumns[0] = "Bemerkung";
        tmpColumns[1] = "Brutto";
        tmpColumns[2] = "MwSt";
        tmpColumns[3] = "Netto";
        tmpColumns[4] = "Rabat";
        tmpColumns[5] = "Artikel";
        tmpColumns[6] = "Typ";
        return tmpColumns;
    }

    private void SaveToDb() {
        CalcSum();

        clsInvoice tmpNewInvoice = new clsInvoice();
        DefaultTableModel model = (DefaultTableModel) tablePositions.getModel();
        //int tmpInvoiceId = clsInvoice.GetId(tmpNewInvoice);
        for (int i = 0; i < tablePositions.getRowCount(); i++) {
            clsInvoicePosition tmpPosition = new clsInvoicePosition();
            if (null != tablePositions.getValueAt(i, 0).toString()) {
                tmpPosition.Bemerkung = tablePositions.getValueAt(i, 0).toString();
            } else {
                tmpPosition.Bemerkung = "";
            }

            try {
                tmpPosition.Brutto = Double.parseDouble(tablePositions.getValueAt(i, 1).toString().replace(',','.'));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "In der Spalte Brutto ist ein Fehler.", "Error", JOptionPane.OK_OPTION);
                return;
            }

            try {
                tmpPosition.Netto = Double.parseDouble(tablePositions.getValueAt(i, 3).toString().replace(',','.'));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "In der Spalte Netto ist ein Fehler.", "Error", JOptionPane.OK_OPTION);
                return;
            }

            try {
                if (null!=tablePositions.getValueAt(i, 2))
                    tmpPosition.MwSt = Double.parseDouble(tablePositions.getValueAt(i, 2).toString().replace(',','.'));
                else
                    tmpPosition.MwSt = 0.;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "In der Spalte Mwst ist ein Fehler.", "Error", JOptionPane.OK_OPTION);
                return;
            }

            try {
                if (null!=tablePositions.getValueAt(i, 4))
                    tmpPosition.Rabat = Double.parseDouble(tablePositions.getValueAt(i, 4).toString().replace(',','.'));
                else
                    tmpPosition.Rabat=0.;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "In der Spalte Rabatt ist ein Fehler.", "Error", JOptionPane.OK_OPTION);
                return;
            }


            int tmpArticleId = clsArticel.GetId(tablePositions.getValueAt(i, 5).toString());
            tmpPosition.ArtikelId = tmpArticleId;
            int tmpTypeId = clsType.GetId(tablePositions.getValueAt(i, 6).toString());
            tmpPosition.TypeId = tmpTypeId;
            tmpNewInvoice.invoicePositionArrayList.add(tmpPosition);
        }

        if (!this.textFieldDate.getText().isEmpty() && this.textFieldDate.getText() != null)
        {
            JOptionPane.showMessageDialog(null, "if Datum", "Bla", JOptionPane.OK_OPTION);
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            try {
                tmpNewInvoice.date = df.parse(this.textFieldDate.getText());

                JOptionPane.showMessageDialog(null, tmpNewInvoice.date.toString(), "Bla", JOptionPane.OK_OPTION);
            } catch (ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Fehler beim Datum. Eventuell stimmt das Format nicht (DD.MM.YYYY)","Error",JOptionPane.OK_OPTION);
            }
        }
        int tmpSelectedCustomer = clsCustomer.getId((String)comboBoxCostumer.getSelectedItem());
        tmpNewInvoice.sumBrutto = Double.parseDouble(this.bruttoLabel.getText().replace(',','.'));
        tmpNewInvoice.sumNetto = Double.parseDouble(this.nettoLabel.getText().replace(',','.'));
        tmpNewInvoice.sumMwst = Double.parseDouble(this.mwstLabel.getText().replace(',','.'));
        tmpNewInvoice.customerId = tmpSelectedCustomer;
        if (tmpNewInvoice.save()) {
            JOptionPane.showConfirmDialog(null, "Speichern Erfolgreich", "Erfolgreich", JOptionPane.OK_OPTION);
            dispose();
        } else
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern.", "Error", JOptionPane.OK_OPTION);
    }
}
