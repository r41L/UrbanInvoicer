package Classes;

import java.util.Date;

public class clsInvoice {

    private int id, custumerId;
    private Date date;
    boolean printed;
    public double sumBrutto, sumNetto, sumMwst;

    public clsInvoice()
    { }

    public boolean SaveToDb(){
        boolean tmpResult = false;

        return tmpResult;
    }

    public static int GetId(clsInvoice pInvoice)
    {
return  1;
    }
}
