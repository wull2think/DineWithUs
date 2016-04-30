package cmu.andrew.htay.dinewithus.ws.remote;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.entities.StoreSet;

/**
 * Created by HuiJun on 4/30/16.
 */
public class ClientRequester extends ClientConnector {

    public ArrayList<Appointment> handleAppointment() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        try {
            reader = new ObjectInputStream(sock.getInputStream());
            appointmentList = (ArrayList<Appointment>) reader.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
        }
        return appointmentList;
    }


    public Profile handleProfile() {
        Profile myProfile = new Profile();
        try {
            reader = new ObjectInputStream(sock.getInputStream());
            myProfile = (Profile) reader.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
        }
        return myProfile;
    }

    public ArrayList<ScheduleBlock> handleSchedule() {
        ArrayList<ScheduleBlock> scheduleList = new ArrayList<ScheduleBlock>();
        try {
            reader = new ObjectInputStream(sock.getInputStream());
            scheduleList = (ArrayList<ScheduleBlock>) reader.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
        }
        return scheduleList;
    }

    public StoreSet handleStoreSet() {
        StoreSet storeSet = new StoreSet();
        try {
            reader = new ObjectInputStream(sock.getInputStream());
            storeSet = (StoreSet) reader.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
        }
        return storeSet;
    }

}
