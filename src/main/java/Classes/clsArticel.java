package Classes;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class clsArticel extends clsDatabaseObject  {
    int id;
    public String name;
    public double vatRate;
    public double price;
    public double squareMeter;

    public void clsArticel()
    {   }

    public static ArrayList<String> GetArticlesFromDB()
    {
        ArrayList<String> tmpResult = new ArrayList<String>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://SQLSRV01:3307/urbanInvoicing?user=urbanInvoicing&password=urbanInvoicing");

            String tmpCommand = "SELECT name FROM tbArtikel WHERE systemstatus_id = 1";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(tmpCommand);
            while (rs.next())
            {
                tmpResult.add(rs.getString("name"));
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.OK_CANCEL_OPTION);
            e.printStackTrace();
        } finally {
            return tmpResult;
        }
    }

    public static int GetId(String pArticleName){
        int tmpResult = 1;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://SQLSRV01:3307/urbanInvoicing?user=urbanInvoicing&password=urbanInvoicing");

            String tmpCommand = "Select id FROM tbArtikel WHERE systemstatus_id = 1 AND name Like '"+ pArticleName + "'";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(tmpCommand);
            while (rs.next())
            {
                tmpResult = rs.getInt("id");
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.OK_CANCEL_OPTION);
            e.printStackTrace();
        } finally {
            return tmpResult;
        }
    }

    public static Double GetMwst(String pArticleName){
        Double tmpResult = 0.;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://SQLSRV01:3307/urbanInvoicing?user=urbanInvoicing&password=urbanInvoicing");

            String tmpCommand = "Select mwstSatz FROM tbArtikel WHERE systemstatus_id = 1 AND name Like '"+ pArticleName + "'";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(tmpCommand);
            while (rs.next())
            {
                tmpResult = rs.getDouble("mwstSatz");
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.OK_CANCEL_OPTION);
            e.printStackTrace();
        } finally {
            return tmpResult;
        }
    }

    public boolean save() {
        boolean result = false;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://SQLSRV01:3307/urbanInvoicing?user=urbanInvoicing&password=urbanInvoicing");
                String tmpCommand = "INSERT INTO tbArtikel (name, mwstSatz, systemstatus_id) VALUES ('"
                        + this.name + "', " + this.vatRate + ",1)";
                PreparedStatement ps = connection.prepareStatement(tmpCommand);
                result = ps.execute();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error in clsArticle",JOptionPane.OK_CANCEL_OPTION);
            e.printStackTrace();
            result = false;
        }
        finally {
            return result;
        }
    }

    void load() {

    }
}
