package cmu.andrew.htay.dinewithus.ws.remote;

/**
 * Created by HuiJun on 4/14/16.
 */
public interface ClientInterface {
    boolean openConnection();
    void handleSession();
    void closeSession();
}
