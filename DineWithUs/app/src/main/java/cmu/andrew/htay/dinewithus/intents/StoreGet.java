package cmu.andrew.htay.dinewithus.intents;

/**
 * Created by HuiJun on 4/25/16.
 */

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.Store;
import cmu.andrew.htay.dinewithus.entities.StoreSet;
import cmu.andrew.htay.dinewithus.ws.remote.ClientConnector;

public class StoreGet extends AsyncTask<Void, Void, Void> {
    StoreSet storeSet;
    ArrayList<String> storeNames;
    ArrayAdapter<String> shopsAdapter;
    ArrayList<Store> serverStoreList;

    public StoreGet(ArrayList<String> storeNames, StoreSet storeSet, ArrayAdapter<String> shopsAdapter) {
        this.storeSet = storeSet;
        this.storeNames = storeNames;
        this.shopsAdapter = shopsAdapter;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        ClientConnector clientIO = new ClientConnector();

        StoreSet serverStoreSet = new StoreSet();
        System.out.println("Connecting to server...");
        if(clientIO.openConnection()) {
            System.out.println("Connected");
            if(clientIO.initReaderWriter()) {
                System.out.println("Sending output");
                //Stores CUISINE PRICE OPENINGTIME CLOSINGTIME LATITUDE LONGITUDE RANGE
                clientIO.sendOutput("GET Stores * * * * 0 0 -1");
                serverStoreSet = clientIO.handleStoreSet();
            }
            clientIO.closeSession();
        }

        serverStoreList = serverStoreSet.getStoreList();
        System.out.println("GOT STORES: ");

        for (Store s : serverStoreList) {
            System.out.println("STORE: " + s.getName());
            storeNames.add(s.getName());
            storeSet.addStore(s);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        shopsAdapter.notifyDataSetChanged();
    }

}
