package cmu.andrew.htay.dinewithus.DBLayout;

import android.content.Context;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;

/**
 * Created by HuiJun on 4/14/16.
 */
public class DBRead extends DatabaseConnector {
    public DBRead(Context context) {
        super(context);
    }

    public Profile getProfile(int id) {

       /* database.query(
                "Profile", null, "_id=" + id, null, null, null, null);*/
        return null;
    }

    public Appointment getAppointmentByID(int id) {
        return null;
    }

    public ArrayList<Appointment> getAppointmentList() {
        return null;

    }

    public ScheduleBlock getScheduleByID(int id) {
        return null;
    }

    public ArrayList<ScheduleBlock> getScheduleList() {
        return null;

    }


    public String getUserName() {
        return "";
    }

    public int getUserID() {
        return -1;
    }

    public String getPassword() {

        return "";
    }
}
