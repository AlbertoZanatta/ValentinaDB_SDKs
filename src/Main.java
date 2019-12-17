import javax.swing.*;

import com.paradigmasoft.valentina.vjdk.*;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world");
        try
        {
            //initialize valentina kernel
            VJDK.init( 3 * 1024 * 1024, "", "" );
            //Set the debugging level to maximum verbosity
            VJDK.setDebugLevel(2);
            String path = "resources\\1998.vdb";

            VDataBase db = new VDataBase();
            //Schema version with 3 files: ".vdb"[description,data] + ".blb"[BLOB] + ".ind"[indexes]
            db.setSchemaVersion((short)1);
            db.open(path);

            //name and type of the dump file - 2 is USED for an xml dump (commented)
            //db.dump("dump.xml", (short)2);

            //Additional info about the db
            System.out.println(db.getFullPath());
            System.out.println(db.getName());
            System.out.println(db.getSchemaVersion());
            System.out.println(db.isEncrypted());

            //Querying the db
            VCursor cursor = db.sqlSelect("SELECT Titolo FROM Gennaio");

            long recCount = cursor.getRecordCount();
            cursor.firstRecord();
            for (long i = 1; i <= recCount; ++i)
            {

                VField field = cursor.getField("Titolo");
                System.out.println(field.getString());
                System.out.println("\n");
                cursor.nextRecord();
            }
        }
        catch (VException vx)
        {
            System.out.println(vx.getMessage());
        }
    }
}
