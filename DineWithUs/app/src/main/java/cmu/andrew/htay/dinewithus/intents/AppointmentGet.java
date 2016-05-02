package cmu.andrew.htay.dinewithus.intents;

/**
 * Created by HuiJun on 4/25/16.
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentFragment;
import cmu.andrew.htay.dinewithus.ws.remote.ClientConnector;
import cmu.andrew.htay.dinewithus.ws.remote.ClientRequester;

public class AppointmentGet extends AsyncTask<Void, Void, Void> {

    private ArrayList<String> appointmentList;
    private LinkedHashMap<String, Appointment> appLHM;
    private AppointmentFragment apptFrag;
    private String username;
    private Context context;

    public AppointmentGet(String username, ArrayList<String> appointmentList,
                          LinkedHashMap<String, Appointment> appLHM,
                          AppointmentFragment apptFrag,
                          Context context) {
        this.appointmentList = appointmentList;
        this.appLHM = appLHM;
        this.apptFrag = apptFrag;
        this.username = username;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        ClientRequester clientIO = new ClientRequester(context);

        ArrayList<Appointment> serverApptList = new ArrayList<Appointment>();
        System.out.println("Connecting to server...");
        if(clientIO.openConnection()) {
            System.out.println("Connected");
            if(clientIO.initReaderWriter()) {
                System.out.println("Sending output");
                clientIO.sendOutput("GET Appointments " + username);
                serverApptList = clientIO.handleAppointment();
            }
            clientIO.closeSession();
        }

        appointmentList.clear();
        appLHM.clear();
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
        apptFrag.updateAllFields();
    }

}
