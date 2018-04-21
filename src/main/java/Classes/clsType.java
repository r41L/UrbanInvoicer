package Classes;

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
        String[] tmpResult = new String[2];
        tmpResult[0] = "Haushaltsware";
        tmpResult[1] = "Verpflegung";
        return tmpResult;
    }

    public static int GetId(String pName)
    {
        return 1;
    }
}
