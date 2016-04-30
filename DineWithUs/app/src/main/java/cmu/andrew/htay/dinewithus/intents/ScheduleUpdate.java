package cmu.andrew.htay.dinewithus.intents;

/**
 * Created by HuiJun on 4/25/16.
 */

import android.os.AsyncTask;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.ws.remote.ClientConnector;
import cmu.andrew.htay.dinewithus.ws.remote.ClientUpdater;

public class ScheduleUpdate extends AsyncTask<Void, Void, Void> {
    private ArrayList<ScheduleBlock>  sbList;
    private String username;

    public ScheduleUpdate(String username, ArrayList<ScheduleBlock> sbList) {
        this.sbList = sbList;
        this.username = username;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        ClientUpdater clientIO = new ClientUpdater();

        String serverReply = "";
        System.out.println("Connecting to server...");
        if(clientIO.openConnection()) {
            System.out.println("Connected");
            if(clientIO.initReaderWriter()) {
                System.out.println("Sending output");
                clientIO.sendOutput("UPDATE Schedules " + username);
                serverReply = clientIO.handleHandshake();
                if(serverReply.equals("READY FOR SCHEDULES")) {
                    clientIO.sendEntity(sbList);
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
