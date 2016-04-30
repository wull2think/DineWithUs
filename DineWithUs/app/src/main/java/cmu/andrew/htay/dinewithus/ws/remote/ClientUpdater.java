package cmu.andrew.htay.dinewithus.ws.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.entities.StoreSet;

/**
 * Created by HuiJun on 4/30/16.
 */
public class ClientUpdater  extends ClientConnector  {
    public String handleHandshake() {
        String reply = "";
        try {
            BufferedReader lineReader =  new BufferedReader
                    (new InputStreamReader(sock.getInputStream()));
            reply = lineReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
        }

        return reply;
    }


    public void sendEntity(Object entity) {
        ObjectOutputStream out = null;
        try {
            out = new
                    ObjectOutputStream(sock.getOutputStream());
            out.writeObject(entity);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
