package cmu.andrew.htay.dinewithus.intents;

/**
 * Created by HuiJun on 4/25/16.
 */

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.ws.remote.ClientUpdater;

public class AppointmentUpdate extends AsyncTask<Void, Void, Void> {
    private ArrayList<Appointment> apptList;
    private String username;
    private Context context;

    public AppointmentUpdate(String username, ArrayList<Appointment> apptList,
                             Context context) {
        this.apptList = apptList;
        this.username = username;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        ClientUpdater clientIO = new ClientUpdater(context);

        String serverReply = "";
        System.out.println("Connecting to server...");
        if(clientIO.openConnection()) {
            System.out.println("Connected");
            if(clientIO.initReaderWriter()) {
                System.out.println("Sending output");
                clientIO.sendOutput("UPDATE Appointments " + username);
                serverReply = clientIO.handleHandshake();
                if(serverReply.equals("READY FOR APPOINTMENTS")) {
                    clientIO.sendEntity(apptList);
                }
            }
        }
        clientIO.closeSession();

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

}
