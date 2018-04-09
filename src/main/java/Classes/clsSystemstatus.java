package Classes;

public class clsSystemstatus {
    int Id;
    String name;

    public clsSystemstatus(enmSystemstatus pEnm) {
        this.Id = pEnm.id;
        this.name = pEnm.name();
    }
}

public enum enmSystemstatus {
    Created (1),
    Deleted (11);

    int id;

    enmSystemstatus(int a)
    {
        this.id = a;
    }
}

