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
    Profile profile;

    public ProfileUpdate(Profile profile) {
        this.profile = profile;
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
                clientIO.sendOutput("UPDATE Profile htay");
                serverReply = clientIO.handleProfileHandshake();
                System.out.println("SERVER REPLY: " + serverReply);
                clientIO.sendProfile(profile);
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
