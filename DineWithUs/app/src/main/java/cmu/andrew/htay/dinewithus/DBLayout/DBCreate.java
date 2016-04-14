package cmu.andrew.htay.dinewithus.DBLayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;

/**
 * Created by HuiJun on 4/14/16.
 */
public class DBCreate extends DatabaseConnector {

    public DBCreate(Context context) {
        super(context);
    }

    public int addToAppointmentList(Appointment appointment) {
        int id = -1;

        int appID = appointment.getAppointmentID();

        System.out.println("Writing to DB");
        ContentValues newAppt = new ContentValues();
        newAppt.put("id", appID);
        open(); // open the database
        database.insert("Appointments", null, newAppt);
        close(); // close the database
        return id;

    }

    public int addToScheduleList(ScheduleBlock schedule) {
        int id = -1;
        return id;

    }
}
