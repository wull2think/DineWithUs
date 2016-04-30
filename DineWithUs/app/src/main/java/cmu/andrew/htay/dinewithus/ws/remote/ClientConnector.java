package cmu.andrew.htay.dinewithus.ws.remote;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.StoreSet;

/**
 * Created by HuiJun on 4/14/16.
 */
public class ClientConnector
            extends Socket implements ClientConstants {

        protected ObjectInputStream reader;
        protected PrintWriter writer;
        protected Socket sock;

        public ClientConnector() {
        }//constructor

        public boolean openConnection(){

            try {
                sock = new Socket(strHost, iPort);
            }
            catch(IOException socketError){
                if (DEBUG) System.err.println
                        ("Unable to connect to " + strHost);
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
                e.printStackTrace();
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
                System.err.println(e);
                System.err.println
                        ("Unable to obtain stream to/from " + strHost);
                return false;
            }
            System.out.println("INITALIZED");
            return true;
        }

        public ArrayList<Appointment> handleAppointment() {
            ArrayList<Appointment> appointmentList = new ArrayList<>();
            try {
                reader = new ObjectInputStream(sock.getInputStream());
                appointmentList = (ArrayList<Appointment>) reader.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println(e);
            }
            return appointmentList;
        }


        public Profile handleProfile() {
            Profile myProfile = new Profile();
            try {
                reader = new ObjectInputStream(sock.getInputStream());
                myProfile = (Profile) reader.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println(e);
            }
            return myProfile;
        }

        public StoreSet handleStoreSet() {
            StoreSet storeSet = new StoreSet();
            try {
                reader = new ObjectInputStream(sock.getInputStream());
                storeSet = (StoreSet) reader.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println(e);
            }
            return storeSet;
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
                if (DEBUG) System.err.println
                        ("Error closing socket to " + strHost);
            }
        }

    }
