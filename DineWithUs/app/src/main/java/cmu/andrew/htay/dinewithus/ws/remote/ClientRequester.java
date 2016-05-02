package cmu.andrew.htay.dinewithus.ws.remote;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.entities.StoreSet;
import cmu.andrew.htay.dinewithus.ws.local.IOUtil;

/**
 * Created by HuiJun on 4/30/16.
 */
public class ClientRequester extends ClientConnector {

    public ClientRequester(Context context) {
        super(context);
    }

    public ArrayList<Appointment> handleAppointment() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        try {
            reader = new ObjectInputStream(sock.getInputStream());
            appointmentList = (ArrayList<Appointment>) reader.readObject();
        } catch (ClassNotFoundException e) {
            String error = "Class from server not found " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        } catch (IOException e) {
            String error = "IO exception (handleAppt) " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        } catch (Exception e) {
            String error = "Error getting entity " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        }
        return appointmentList;
    }


    public Profile handleProfile() {
        Profile myProfile = new Profile();
        try {
            reader = new ObjectInputStream(sock.getInputStream());
            myProfile = (Profile) reader.readObject();
        } catch (ClassNotFoundException e) {
            String error = "Class from server not found " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        } catch (IOException e) {
            String error = "IO exception (handleProfile) " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        } catch (Exception e) {
            String error = "Error getting entity " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        }
        return myProfile;
    }

    public ArrayList<ScheduleBlock> handleSchedule() {
        ArrayList<ScheduleBlock> scheduleList = new ArrayList<ScheduleBlock>();
        try {
            reader = new ObjectInputStream(sock.getInputStream());
            scheduleList = (ArrayList<ScheduleBlock>) reader.readObject();
        } catch (ClassNotFoundException e) {
            String error = "Class from server not found " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        } catch (IOException e) {
            String error = "IO exception (handleSchedule) " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        } catch (Exception e) {
            String error = "Error getting entity " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        }
        return scheduleList;
    }

    public StoreSet handleStoreSet() {
        StoreSet storeSet = new StoreSet();
        try {
            reader = new ObjectInputStream(sock.getInputStream());
            storeSet = (StoreSet) reader.readObject();
        } catch (ClassNotFoundException e) {
            String error = "Class from server not found " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        } catch (IOException e) {
            String error = "IO exception (handleStoreSet) " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        } catch (Exception e) {
            String error = "Error getting entity " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        }
        return storeSet;
    }

}
