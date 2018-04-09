package Classes;

import java.util.Date;

public abstract class clsDatabaseObject
{
    int systemstatusId;
    Date addedAt;
    Date editedAt;

    clsSystemstatus systemstatus;

    abstract void save();
    abstract void load();
    protected void delete()
    {
        //this.systemstatus = new clsSystemstatus (enmSystemstatus.Deleted);
        //ToDo: DateTime.Now in Java finden und this.editedAt setzen
    }
}
