package Classes;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class clsDatabaseObject extends clsSystemstatus
{
    LocalDateTime addedAt;
    LocalDateTime editedAt;

    clsSystemstatus systemstatus;

    abstract boolean save();
    abstract void load();
    protected void delete()
    {
        this.systemstatus = new clsSystemstatus (clsSystemstatus.enmSystemstatus.Deleted);
        this.editedAt = LocalDateTime.now();
    }

}
