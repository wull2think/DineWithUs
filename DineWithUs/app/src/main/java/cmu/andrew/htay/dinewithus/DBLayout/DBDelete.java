package cmu.andrew.htay.dinewithus.DBLayout;

import android.content.Context;

/**
 * Created by HuiJun on 4/14/16.
 */
public class DBDelete extends DatabaseConnector {

    public DBDelete(Context context) {
        super(context);
    }

    public void deleteAppointment(int id) {

        open(); // open the database
        database.delete("Appointments", "_id=" + id, null);
        close(); // close the database
    }

    public void deleteSchedule(int id) {
        open(); // open the database
        database.delete("Schedule", "_id=" + id, null);
        close(); // close the database

    }
}
