package cmu.andrew.htay.dinewithus.ws.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by HuiJun on 4/14/16.
 */
public class ClientConnector
            extends Socket implements ClientInterface,
            ClientConstants, Runnable {

        protected BufferedReader reader;
        protected PrintWriter writer;
        protected Socket sock;
        protected String strHost;
        protected int iPort;

        public ClientConnector(String strHost, int iPort) {
            setPort (iPort);
            setHost (strHost);
        }//constructor

        //alternative constructor for using server.accept()
        public ClientConnector(Socket socket) {
            this.sock = socket;
            setPort(socket.getPort());
        }//constructor


        public void run(){
            System.out.println("Running Session...");
            initReaderWriter();
            handleSession();
            closeSession();

        }//run

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

            try {
                reader = new BufferedReader
                        (new InputStreamReader(sock.getInputStream()));
                writer = new PrintWriter
                        (new OutputStreamWriter(sock.getOutputStream()));

            }
            catch (Exception e){
                if (DEBUG) System.err.println
                        ("Unable to obtain stream to/from " + strHost);
                return false;
            }
            return true;
        }

        //default handle session, prints data
        public void handleSession(){
            String strInput = "";
            if (DEBUG) System.out.println ("Handling session with "
                    + strHost + ":" + iPort);
            try {
                while ( (strInput = reader.readLine()) != null) {
                    handleInput(strInput);
                    if(strInput.equals("done")) {
                        break;
                    }
                }
            }
            catch (IOException e){
                if (DEBUG) {
                    System.out.println ("Handling session with "
                            + strHost + ":" + iPort);
                }
            }
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

        public void setHost(String strHost){
            this.strHost = strHost;
        }

        public void setPort(int iPort){
            this.iPort = iPort;
        }




    }
