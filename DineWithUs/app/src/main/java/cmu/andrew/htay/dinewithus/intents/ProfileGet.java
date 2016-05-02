package cmu.andrew.htay.dinewithus.intents;

/**
 * Created by HuiJun on 4/25/16.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.fragment.profile.ProfileFragment;
import cmu.andrew.htay.dinewithus.ws.remote.ClientConnector;
import cmu.andrew.htay.dinewithus.ws.remote.ClientRequester;

public class ProfileGet extends AsyncTask<Void, Void, Void> {
    private Profile profile;
    private ProfileFragment profileFrag;
    private String username = "htay";
    private Context context;

    public ProfileGet(String username, Profile profile, ProfileFragment profileFrag, Context context) {
        this.profile = profile;
        this.profileFrag = profileFrag;
        this.username = username;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        ClientRequester clientIO = new ClientRequester(context);

        Profile serverProfile = new Profile();
        System.out.println("Connecting to server...");
        if(clientIO.openConnection()) {
            System.out.println("Connected");
            if(clientIO.initReaderWriter()) {
                System.out.println("Sending output");
                clientIO.sendOutput("GET Profile " + username);
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

        profile.getLikes().clear();
        profile.getDislikes().clear();
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
        /*
        super.onPostExecute(result);
        profileFrag.updateAllFields();
        */
    }

}
