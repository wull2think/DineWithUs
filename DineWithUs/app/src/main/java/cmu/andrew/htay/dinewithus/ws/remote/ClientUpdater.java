package cmu.andrew.htay.dinewithus.ws.remote;

import android.content.Context;

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
import cmu.andrew.htay.dinewithus.ws.local.IOUtil;

/**
 * Created by HuiJun on 4/30/16.
 */
public class ClientUpdater  extends ClientConnector  {

    public ClientUpdater(Context context) {
        super(context);
    }

    public String handleHandshake() {
        String reply = "";
        try {
            BufferedReader lineReader =  new BufferedReader
                    (new InputStreamReader(sock.getInputStream()));
            reply = lineReader.readLine();
        } catch (IOException e) {
            String error = "Server handshake failed: " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
            return error;

        } catch (Exception e) {
            String error = "Server handshake failed: " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
            return error;
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
            String error = "Sending entity failed (IOException)";
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        } catch (Exception e) {
            String error = "Sending entity failed: " + e.toString();
            System.err.println(error);
            IOUtil.logFile(context, "log.txt", error);
        }

    }
}
