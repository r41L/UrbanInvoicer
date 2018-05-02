package Classes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class clsInvoicePosition extends clsDatabaseObject {
        private int Id;
        private int InvoiceId;
        public String Bemerkung;
        public double Brutto;
        public double Netto;
        public double MwSt;
        public double Rabat;
        public int ArtikelId;
        public int TypeId;

        private clsType Type;
        private clsArticel Articel;
        private clsInvoice Invoice;

        public clsInvoicePosition() {
        }

        public clsInvoicePosition(int pId, int pInvoiceId, int pArtikelId, String pBemerkung, double pBrutto, double pNetto, double pMwst, double pRabat, clsType pType) {
                this.Id = pId;
                this.InvoiceId = pInvoiceId;
                this.Type = pType;
                this.TypeId = this.Type.id;
                this.ArtikelId = pArtikelId;
                this.Bemerkung = pBemerkung;
                this.Brutto = pBrutto;
                this.Netto = pNetto;
                this.MwSt = pMwst;
                this.Rabat = pRabat;
        }

        public void SetInvoiceId(int pInvoiceId)
        {
                this.InvoiceId = pInvoiceId;
        }

        public boolean save() {
                boolean result = false;

                try {
                        Connection connection = DriverManager.getConnection("jdbc:mariadb://SQLSRV01:3307/urbanInvoicing?user=urbanInvoicing&password=urbanInvoicing");
                        if (!(connection == null || !connection.isValid(2000) || connection.isClosed())) {
                                String tmpCommand = "INSERT INTO tbInvoicePosition (invoice_id, bemerkung, brutto, netto, mwSt, rabatt, artikel_id, type_id, systemstatus_id) VALUES ("
                                        + this.InvoiceId + ", '" + this.Bemerkung + "'," + this.Brutto + "," + this.Netto + "," + this.MwSt + "," + this.Rabat + "," + this.ArtikelId + "," + this.TypeId + ",1)";
                                PreparedStatement ps = connection.prepareStatement(tmpCommand);
                                result = ps.execute();
                        }
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Error in clsInvoicePosition",JOptionPane.OK_CANCEL_OPTION);
                        e.printStackTrace();
                        result = false;
                } finally {
                        return result;
                }
        }

        public void load() {
        }
}