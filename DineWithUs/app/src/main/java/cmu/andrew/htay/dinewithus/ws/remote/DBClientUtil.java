package cmu.andrew.htay.dinewithus.ws.remote;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.entities.StoreSet;

/**
 * Created by HuiJun on 4/14/16.
 */
public class DBClientUtil implements DBRemoteInterface {

    public ArrayList<Appointment> getAppointmentsForUser(int id) {
        return null;
    }

    public ArrayList<ScheduleBlock> getScheduleForUser(int id) {
        return null;

    }

    public StoreSet getStoresForLocation(long latitude, long longitude) {
        return null;

    }

    public StoreSet getAllStores() {
        return null;

    }

    public void getProfile(int id) {

    }


    public void updateAppointments(ArrayList<Appointment> appointmentList) {

    }

    public void updateSchedule(ArrayList<ScheduleBlock> schedule) {

    }

    public void updateProfile() {

    }
}
