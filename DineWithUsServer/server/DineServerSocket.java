package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.*;

import database.DBMethods;
import model.*;

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
		DBMethods DBWrapper = new DBMethods();
		ObjectOutputStream os;
		String username;
		StoreSet stores;
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			String[] args = in.readLine().split(" ");
			switch(args[0]){
			case "Profile":
				username = args[1];
				Profile prof = DBWrapper.getProfile(username);
				os = new ObjectOutputStream(server.getOutputStream());
				os.writeObject(prof);
				System.out.println("Wrote profile object");
				break;
			case "StoreRating":
				int rating = Integer.parseInt(args[1]);
				stores = DBWrapper.getStoresWithRating(rating);
				os = new ObjectOutputStream(server.getOutputStream());
				os.writeObject(stores);
				break;
			case "Appointments":
				username = args[1];
				break;
			//TODO: implement StoreLocation, StoreCuisine, StorePriceRange, Appointments
			}
		}
		catch(IOException e){
			System.err.println("Failed to dispatch" + e.toString());
		}
	}
	
}
