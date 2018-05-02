package Classes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class clsType
{
    int id;
    int multiplier;
    String name;
    boolean isSquarmeterRelevant;
    boolean isRoomRelevant;

    public clsType()
    { }

    public static String[] GetTypesFromDB(){
        ArrayList<String> tmpResult = new ArrayList<String>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://SQLSRV01:3307/urbanInvoicing?user=urbanInvoicing&password=urbanInvoicing");

            String tmpCommand = "Select name FROM tbType WHERE systemstatus_id = 1";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(tmpCommand);
            while (rs.next())
            {
                tmpResult.add(rs.getString(1));
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            return tmpResult.toArray(new String[0]);
        }
    }

    public static int GetId(String pName)
    {
        int tmpResult = 1;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://SQLSRV01:3307/urbanInvoicing?user=urbanInvoicing&password=urbanInvoicing");

            String tmpCommand = "Select id FROM tbType WHERE name like '"+pName+"'";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(tmpCommand);
            while (rs.next())
            {
                tmpResult=rs.getInt("id");
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.OK_CANCEL_OPTION);
            e.printStackTrace();
        } finally {
            return tmpResult;
        }
    }
}
