package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

import util.IOUtil;

import cmu.andrew.htay.dinewithus.entities.*;

import database.DBMethods;

public class DineServerSocket extends Socket
	implements Runnable{

	protected Thread T;
	protected Socket server;
	protected IOUtil io = new IOUtil();
	
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
				getEntity(targetEntity, args);
				
			}
		}
		catch(IOException e){
			String error = "Failed to dispatch" + e.toString();
			System.err.println(error);
			io.logFile(error, "log.txt");
		}
	}
	
	public void sendEntity(String targetEntity, String[] args) throws IOException {
		DBMethods DBWrapper = new DBMethods();
		ObjectOutputStream os;
		os = new ObjectOutputStream(server.getOutputStream());
		String username;

		switch(targetEntity){
		case "Profile":
			username = args[2];
			Profile prof = DBWrapper.getProfile(username);
			os.writeObject(prof);
			System.out.println("Wrote profile object");
			break;
		case "Stores":
            //Stores CUISINE PRICE OPENINGTIME CLOSINGTIME LATITUDE LONGITUDE RANGE
			String cuisine = args[2].toUpperCase();
			String price = args[3].toUpperCase();
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
			os.writeObject(stores);
			break;
		case "Appointments":
			username = args[2];
			ArrayList<Appointment> apptList = DBWrapper.getAppointments(username);
			for(Appointment appt : apptList) {
				System.out.println(appt);
				System.out.println(appt.getName());
			}
			os.writeObject(apptList);
			System.out.println("Wrote AppointmentList object");
			break;
		case "Schedules":
			username = args[2];
			ArrayList<ScheduleBlock> sbList = DBWrapper.getScheduleBlocks(username);

			os.writeObject(sbList);
			System.out.println("Wrote ScheduleBlockList object");
			break;
		}
	}
	
	public void getEntity(String targetEntity, String[] args) throws IOException {

		String username = args[2];;
		PrintWriter writer = new PrintWriter
 	      (new OutputStreamWriter (server.getOutputStream()));
		ObjectInputStream reader = null;
		
		String reply = "";
		
		switch(targetEntity){
			case "Profile":
				Profile clientProfile = new Profile();
				reply = "READY FOR PROFILE";
		    	System.out.println("Got Profile for " + 
		    			username + ", replying: " +reply);
		    	writer.println(reply);
				writer.flush();
				try {
		            reader = new ObjectInputStream(server.getInputStream());
		            clientProfile = (Profile) reader.readObject();
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } catch (Exception e) {
		            System.err.println(e);
		        }
				updateProfile(username, clientProfile);
				
				break;
			case "Appointments":
				ArrayList<Appointment> clientAppts = new ArrayList<Appointment>();
				reply = "READY FOR APPOINTMENTS";
		    	System.out.println("Got Appointments for " + 
		    			username + ", replying: " +reply);
		    	writer.println(reply);
				writer.flush();
				try {
		            reader = new ObjectInputStream(server.getInputStream());
		            clientAppts = (ArrayList<Appointment>) reader.readObject();
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } catch (Exception e) {
		            System.err.println(e);
		        }
				updateAppointments(username, clientAppts);
				break;
			case "Schedules":
				ArrayList<ScheduleBlock> clientSchedules = new ArrayList<>();
				reply = "READY FOR SCHEDULES";
		    	System.out.println("Got Schedules for " + 
		    			username + ", replying: " +reply);
		    	writer.println(reply);
				writer.flush();
				try {
		            reader = new ObjectInputStream(server.getInputStream());
		            clientSchedules = (ArrayList<ScheduleBlock>) reader.readObject();
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } catch (Exception e) {
		            System.err.println(e);
		        }
				updateSchedule(username, clientSchedules);
				break;
		}
	}
	
	private void updateProfile(String username, Profile profile) {
		System.out.println("GOT PROFILE: " + profile.getFirstname() + 
				" " + profile.getLastname());
		DBMethods DBWrapper = new DBMethods();

		DBWrapper.updateProfileName(username, 
				profile.getFirstname(), profile.getLastname());
		DBWrapper.updateProfileAge(username, profile.getAge());
		DBWrapper.updateProfileAge(username, profile.getAge());
		DBWrapper.updateProfilePhoneString(username, profile.getPhone());
		DBWrapper.updateProfileGender(username, profile.getGender());
		DBWrapper.updateProfileEmail(username, profile.getEmail());
		
	}
	
	private void updateAppointments(String username, 
			ArrayList<Appointment> apptList) {
		DBMethods DBWrapper = new DBMethods();
		for (Appointment appt : apptList) {
			System.out.println("GOT APPT: " + appt.getAppointmentID());
			System.out.println(appt.getStatus()[0]+ " " + appt.getStatus()[1]);
			if(appt.getAppointmentID() >= 0) { //apt exists in DB
				DBWrapper.updateAppointmentStatus(appt.getAppointmentID(), 
						appt.getStatus()[0], appt.getStatus()[1]);
			}
			else { //appt does not exist, create new one
				DBWrapper.addAppointment(appt);
				
			}
		}
	}
	
	private void updateSchedule(String username, 
			ArrayList<ScheduleBlock> sbList) {
		DBMethods DBWrapper = new DBMethods();
		for (ScheduleBlock sb : sbList) {
			System.out.println("GOT SCHEDULE: " + sb.getID());
			System.out.println(sb.getStartTime() + " - " + sb.getEndTime());
			if(sb.getID() >= 0) { //apt exists in DB
				DBWrapper.updateScheduleStart(sb.getID(), sb.getStartTime());
				DBWrapper.updateScheduleEnd(sb.getID(), sb.getEndTime());
				DBWrapper.updateScheduleDate(sb.getID(), sb.getDate());
			}
			else { //appt does not exist, create new one
				DBWrapper.addScheduleBlock(username, sb);
				
			}
		}
	}

	
}
