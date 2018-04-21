package Classes;

public class clsCustomer {

    public int id;
    public String name;
    public String lastName;
    public String plz;
    public String street;
    public String city;
    public String land;
    public String telefone;
    public String telefax;
    public String email;
    public String note;
    private boolean isCompany;

    public clsCustomer()
    {   }

    public static clsCustomer[] GetCustomerFromDB(){
        clsCustomer[] tmpResult = new clsCustomer[2];
        clsCustomer tmp = new clsCustomer();
        tmp.name  = "Brummel GmbH";
        clsCustomer tmp2 = new clsCustomer();
        tmp2.name  = "Ludwig AG";
        tmpResult[0] = tmp;
        tmpResult[1] = tmp2;
        return tmpResult;
    }
}
