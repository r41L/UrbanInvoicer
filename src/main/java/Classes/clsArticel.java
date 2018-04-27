package Classes;

public class clsArticel extends clsDatabaseObject  {
    int id;
    String name;
    double vatRate;
    double price;
    double squareMeter;

    public void clsArticel()
    {   }

    public static String[] GetArticlesFromDB()
    {
        String[] tmpResult = new String[2];
        tmpResult[0] = "Klopapier";
        tmpResult[1] = "Seife";
        return tmpResult;
    }

    public static int GetId(String pArticleName){
        return 1;
    }

    void save() {

    }

    void load() {

    }
}
