package cmu.andrew.htay.dinewithus.intents;

/**
 * Created by HuiJun on 4/25/16.
 */

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.ws.remote.ClientConnector;
import cmu.andrew.htay.dinewithus.ws.remote.ClientUpdater;

public class ScheduleUpdate extends AsyncTask<Void, Void, Void> {
    private ArrayList<ScheduleBlock>  sbList;
    private String username;
    private Context context;

    public ScheduleUpdate(String username, ArrayList<ScheduleBlock> sbList,
                          Context context) {
        this.sbList = sbList;
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
                clientIO.sendOutput("UPDATE Schedules " + username);
                serverReply = clientIO.handleHandshake();
                if(serverReply != null && serverReply.equals("READY FOR SCHEDULES")) {
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
