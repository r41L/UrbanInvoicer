package Classes;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class clsInvoice extends clsDatabaseObject{

    public int id, customerId;
    public Date date;
    boolean printed;
    public double sumBrutto, sumNetto, sumMwst;

    public ArrayList<clsInvoicePosition> invoicePositionArrayList = new ArrayList<clsInvoicePosition>();

    public clsInvoice()
    { }

    public void load(){}



    public boolean save(){
        boolean result = false;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://SQLSRV01:3307/urbanInvoicing?user=urbanInvoicing&password=urbanInvoicing");
            if (!(connection == null || !connection.isValid(2000) || connection.isClosed())) {
                String tmpDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.date);
                String tmpCommand = "INSERT INTO tbInvoice (customer_id, belegdatum, printed, summeBrutto, summeNetto, summeMwst, systemstatus_id) VALUES ("+this.customerId+", '"+tmpDate+"', false, "+this.sumBrutto+","+this.sumNetto+","+this.sumMwst+",1)";
                PreparedStatement ps = connection.prepareStatement(tmpCommand,Statement.RETURN_GENERATED_KEYS);
                result = ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                while(rs.next())
                {
                    this.id = rs.getInt(1);
                }
                for (clsInvoicePosition pos :invoicePositionArrayList) {
                    pos.SetInvoiceId(this.id);
                    pos.save();
                }
                result = true;
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error in clsInvoice",JOptionPane.OK_CANCEL_OPTION);
            e.printStackTrace();
            result = false;
        }
        finally {
            return result;
        }
    }

    public static int GetId(clsInvoice pInvoice)
    {
return  1;
    }
}
