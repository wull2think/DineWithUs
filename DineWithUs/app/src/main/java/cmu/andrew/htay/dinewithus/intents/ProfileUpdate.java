package cmu.andrew.htay.dinewithus.intents;

/**
 * Created by HuiJun on 4/25/16.
 */

import android.os.AsyncTask;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.ws.remote.ClientConnector;
import cmu.andrew.htay.dinewithus.ws.remote.ClientUpdater;

public class ProfileUpdate extends AsyncTask<Void, Void, Void> {
    private Profile profile;
    private String username;

    public ProfileUpdate(String username, Profile profile) {
        this.profile = profile;
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
                clientIO.sendOutput("UPDATE Profile " + username);
                serverReply = clientIO.handleHandshake();
                if(serverReply.equals("READY FOR PROFILE")) {
                    clientIO.sendEntity(profile);
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