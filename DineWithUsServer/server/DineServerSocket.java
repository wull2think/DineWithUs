package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.entities.*;

import database.DBMethods;

public class DineServerSocket extends Socket
	implements Runnable{

	Thread T;
	Socket server;
	
	public DineServerSocket(Socket server){
		this.server = server;
	}
	
	public static void dispatch(ServerSocket server){
		DineServerSocket dss = null;
		Socket s;
		try{
			while((s = server.accept()) != null){
				dss = new DineServerSocket(s);
				dss.start();
			}
		}
		catch(Exception E){
			System.err.println("Failed to accept");
			System.exit(1);
		}
	}
	
	public void start(){
		if(T == null){
			T = new Thread(this, "New Thread");
			T.start();
		}
	}
	
	public void run(){
		handleSession();		
	}
	
	public void handleSession(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			String inputLine = in.readLine();
			System.out.println(inputLine);
			String[] args = inputLine.split(" ");
			String op = args[0];
			String targetEntity = args[1];
			if(op.equals("GET")) {
				sendEntity(targetEntity, args);
			}
			else if(op.equals("UPDATE")) {
				
			}
		}
		catch(IOException e){
			System.err.println("Failed to dispatch" + e.toString());
		}
	}
	
	public void sendEntity(String targetEntity, String[] args) throws IOException {
		DBMethods DBWrapper = new DBMethods();
		ObjectOutputStream os;
		String username;

		switch(targetEntity){
		case "Profile":
			username = args[2];
			Profile prof = DBWrapper.getProfile(username);
			os = new ObjectOutputStream(server.getOutputStream());
			os.writeObject(prof);
			System.out.println("Wrote profile object");
			break;
		case "Stores":
            //Stores CUISINE PRICE OPENINGTIME CLOSINGTIME LATITUDE LONGITUDE RANGE
			String cuisine = args[2];
			String price = args[3];
			String opening = args[4];
			String closing = args[5];
			double longitude = Double.parseDouble(args[6]);
			double latitude = Double.parseDouble(args[7]);
			double range = Double.parseDouble(args[8]);
			
			StoreSet stores = DBWrapper.getStores(cuisine, price, opening, closing,
					latitude, longitude, range);
			ArrayList<Store> storeList = stores.getStoreList();
			for (Store s : storeList) {
				System.out.println("Sending Store: " + s.getName());
			}
			os = new ObjectOutputStream(server.getOutputStream());
			os.writeObject(stores);
			break;
		case "Appointments":
			username = args[2];
			ArrayList<Appointment> apptList = DBWrapper.getAppointments(username);
			for(Appointment appt : apptList) {
				System.out.println(appt);
				System.out.println(appt.getName());
			}
			os = new ObjectOutputStream(server.getOutputStream());
			os.writeObject(apptList);
			System.out.println("Wrote AppointmentList object");
			break;
		}
	}

	
}
