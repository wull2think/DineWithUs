package cmu.andrew.htay.dinewithus.ws.remote;

import java.io.IOException;
import java.io.ObjectInputStream;

import cmu.andrew.htay.dinewithus.entities.StoreSet;

/**
 * Created by HuiJun on 4/30/16.
 */
public class ClientUpdater  extends ClientConnector  {
    public String handleProfileHandshake() {
        String reply = "";
        try {
            reader = new ObjectInputStream(sock.getInputStream());
            reply = (String) reader.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
        }
        return reply;
    }
}
