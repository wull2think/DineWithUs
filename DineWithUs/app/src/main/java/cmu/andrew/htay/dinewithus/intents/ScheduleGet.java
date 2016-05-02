package cmu.andrew.htay.dinewithus.intents;

/**
 * Created by HuiJun on 4/25/16.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.entities.Store;
import cmu.andrew.htay.dinewithus.entities.StoreSet;
import cmu.andrew.htay.dinewithus.fragment.schedule.ScheduleFragment;
import cmu.andrew.htay.dinewithus.ws.remote.ClientConnector;
import cmu.andrew.htay.dinewithus.ws.remote.ClientRequester;

public class ScheduleGet extends AsyncTask<Void, Void, Void> {
    private ArrayList<ScheduleBlock> scheduleList;
    private ScheduleFragment sbFrag;
    private String username;
    private Context context;

    public ScheduleGet(String username, ArrayList<ScheduleBlock> scheduleList,
                       ScheduleFragment sbFrag,
                       Context context) {
        this.scheduleList = scheduleList;
        this.sbFrag = sbFrag;
        this.username = username;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        ClientRequester clientIO = new ClientRequester(context);

        ArrayList<ScheduleBlock> sbList = new ArrayList<>();
        System.out.println("Connecting to server...");
        if(clientIO.openConnection()) {
            System.out.println("Connected");
            if(clientIO.initReaderWriter()) {
                System.out.println("Sending output");
                clientIO.sendOutput("GET Schedules " + username);
                sbList = clientIO.handleSchedule();
            }
            clientIO.closeSession();
        }

        System.out.println("GOT SCHEDULEBLOCKS: ");

        for (ScheduleBlock sb : sbList) {
            System.out.println("ScheduleBlock: " + sb.getID());
            scheduleList.add(sb);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        sbFrag.updateAllFields();

    }

}
