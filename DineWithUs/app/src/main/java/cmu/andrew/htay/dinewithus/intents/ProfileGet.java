package cmu.andrew.htay.dinewithus.intents;

/**
 * Created by HuiJun on 4/25/16.
 */

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.ws.remote.ClientConnector;
import cmu.andrew.htay.dinewithus.ws.remote.ClientRequester;

public class ProfileGet extends AsyncTask<Void, Void, Void> {
    Profile profile;

    public ProfileGet(Profile profile) {
        this.profile = profile;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        ClientRequester clientIO = new ClientRequester();

        Profile serverProfile = new Profile();
        System.out.println("Connecting to server...");
        if(clientIO.openConnection()) {
            System.out.println("Connected");
            if(clientIO.initReaderWriter()) {
                System.out.println("Sending output");
                clientIO.sendOutput("GET Profile htay");
                serverProfile = clientIO.handleProfile();
            }
            clientIO.closeSession();
        }

        profile.setAge(serverProfile.getAge());
        profile.setEmail(serverProfile.getEmail());
        profile.setFirstname(serverProfile.getFirstname());
        profile.setLastname(serverProfile.getLastname());
        profile.setPhone(serverProfile.getPhone());

        ArrayList<String> likes = serverProfile.getLikes();
        ArrayList<String> dislikes = serverProfile.getDislikes();

        for (String like : likes) {
            profile.addLike(like);
        }

        for (String dislike : dislikes) {
            profile.addDislike(dislike);
        }

        return null;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

}
