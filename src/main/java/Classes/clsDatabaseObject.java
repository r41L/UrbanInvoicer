package Classes;

import java.time.LocalDateTime;

public abstract class clsDatabaseObject extends clsSystemstatus
{
    LocalDateTime addedAt;
    LocalDateTime editedAt;

    clsSystemstatus systemstatus;

    abstract void save();
    abstract void load();
    protected void delete()
    {
        this.systemstatus = new clsSystemstatus (clsSystemstatus.enmSystemstatus.Deleted);
        this.editedAt = LocalDateTime.now();
    }

}
