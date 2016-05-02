package cmu.andrew.htay.dinewithus.ws.remote;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.entities.StoreSet;
import cmu.andrew.htay.dinewithus.ws.local.IOUtil;

/**
 * Created by HuiJun on 4/14/16.
 */
public class ClientConnector
            extends Socket implements ClientConstants {

        protected ObjectInputStream reader;
        protected PrintWriter writer;
        protected Socket sock;
        protected Context context;

        public ClientConnector(Context context) {
            this.context = context;
        }//constructor

        public boolean openConnection(){

            try {
                sock = new Socket(strHost, iPort);
            }
            catch(IOException socketError){
                String error = "Unable to connect to " + strHost;
                System.err.println(error);
                IOUtil.logFile(context, "log.txt", error);
                return false;
            }
            return true;
        }


        //helper function for closing reader/writers after a session
        //is complete
        public void closeReaderWriter() {
            try {
                reader.close();
            } catch (IOException e) {
                String error = "unable to close reader/writer " + e.toString();
                System.err.println(error);
                IOUtil.logFile(context, "log.txt", error);
            }
            writer.close();
        }

        //inits reader writer seperate of opening a
        //connection to make it easier to partition code function
        public boolean initReaderWriter() {
            System.out.println("Initializing READERWRITER");

            try {
                writer = new PrintWriter
                        (new OutputStreamWriter(sock.getOutputStream()));

            }
            catch (Exception e){
                String error = "Unable to obtain stream to/from " + strHost;
                System.err.println(error);
                IOUtil.logFile(context, "log.txt", error);
                return false;
            }
            return true;
        }

    //default send output, sends Strings
        public void sendOutput(String strOutput){
            System.out.println("Writing: " +strOutput);
            writer.println(strOutput);
            writer.flush();
        }

        //default handle input, prints received data
        public void handleInput(String strInput){
            System.out.println(strInput);
        }

        public void closeSession(){
            try {
                writer = null;
                reader = null;
                sock.close();
            }
            catch (IOException e){
                String error = "Error closing socket to " + strHost;
                System.err.println(error);
                IOUtil.logFile(context, "log.txt", error);
            }
        }

    }
