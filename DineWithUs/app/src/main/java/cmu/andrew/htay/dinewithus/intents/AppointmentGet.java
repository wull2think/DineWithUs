package cmu.andrew.htay.dinewithus.intents;

/**
 * Created by HuiJun on 4/25/16.
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.ws.remote.ClientConnector;

public class AppointmentGet extends AsyncTask<Void, Void, Void> {

    ArrayList<String> appointmentList;
    LinkedHashMap<String, Appointment> appLHM;
    ArrayAdapter<String> appointmentAdapter;

    public AppointmentGet(ArrayList<String> appointmentList,
                          LinkedHashMap<String, Appointment> appLHM, ArrayAdapter<String> appointmentAdapter) {
        this.appointmentList = appointmentList;
        this.appLHM = appLHM;
        this.appointmentAdapter = appointmentAdapter;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        ClientConnector clientIO = new ClientConnector();

        ArrayList<Appointment> serverApptList = new ArrayList<Appointment>();
        System.out.println("Connecting to server...");
        if(clientIO.openConnection()) {
            System.out.println("Connected");
            if(clientIO.initReaderWriter()) {
                System.out.println("Sending output");
                clientIO.sendOutput("GET Appointments htay");
                serverApptList = clientIO.handleAppointment();
            }
            clientIO.closeSession();
        }

        //appointmentList.clear();
        for(Appointment appt : serverApptList) {
            String apptName = appt.getName();
            System.out.println("ADDING APPOINTMENT: "+apptName);
            appointmentList.add(apptName);
            appLHM.put(apptName, appt);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        appointmentAdapter.notifyDataSetChanged();
    }

}
