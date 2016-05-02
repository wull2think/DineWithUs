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
import cmu.andrew.htay.dinewithus.fragment.schedule.ScheduleFragment;
import cmu.andrew.htay.dinewithus.fragment.stores.StoresFragment;
import cmu.andrew.htay.dinewithus.ws.remote.ClientConnector;
import cmu.andrew.htay.dinewithus.ws.remote.ClientRequester;

public class StoreGet extends AsyncTask<Void, Void, Void> {
    private StoreSet storeSet;
    private ArrayList<String> storeNames;
    private ArrayAdapter<String> shopsAdapter;
    private ArrayList<Store> serverStoreList;
    private StoresFragment storefrag;
    private String request;

    public StoreGet(ArrayList<String> storeNames, StoreSet storeSet, StoresFragment storefrag, String request) {
        this.storeSet = storeSet;
        this.storeNames = storeNames;
        this.storefrag = storefrag;
        this.request = request;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        ClientRequester clientIO = new ClientRequester();

        StoreSet serverStoreSet = new StoreSet();
        System.out.println("Connecting to server...");
        if(clientIO.openConnection()) {
            System.out.println("Connected");
            if(clientIO.initReaderWriter()) {
                System.out.println("Sending output");
                //Stores CUISINE PRICE OPENINGTIME CLOSINGTIME LATITUDE LONGITUDE RANGE
                //clientIO.sendOutput("GET Stores * * * * 0 0 -1");
                clientIO.sendOutput(request);

                serverStoreSet = clientIO.handleStoreSet();
            }
            clientIO.closeSession();
        }

        serverStoreList = serverStoreSet.getStoreList();
        System.out.println("GOT STORES: ");

        storeNames.clear();
        storeSet.removeAllStores();

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
        storefrag.updateAllFields();
    }

}
