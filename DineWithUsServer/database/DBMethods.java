package database;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Appointment;
import model.Profile;
import model.ScheduleBlock;
import model.StoreSet;

public class DBMethods extends DatabaseConstants {
	
	public boolean authenticate(String username, String password) {
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
	
		String storedPass = jdbc.getPassword(username);

		try {
			jdbc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(storedPass.equals(password)) {
			return true;
		}
		
		return false;
	}
	
	public Profile getProfile(String username) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
	
		int userID = jdbc.getUserID(username);
		Profile storedProfile = jdbc.getProfile(userID);

		try {
			jdbc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return storedProfile;
	}
	
	
	
	public ArrayList<Appointment> getAppointments(String username) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
	
		int userID = jdbc.getUserID(username);
		ArrayList<Integer> appointmentIDList = 
				jdbc.getAppointmentIDs(userID);
		
		ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
		for(int apptID : appointmentIDList) {
			Appointment appt = jdbc.getAppointment(apptID);
			appointmentList.add(appt);
		}
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return appointmentList;
	}
	
	public ArrayList<ScheduleBlock> getScheduleBlocks(String username) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
	
		int userID = jdbc.getUserID(username);
		ArrayList<Integer> sbBlockList = 
				jdbc.getScheduleIDs(userID);
		
		ArrayList<ScheduleBlock> sbList = new ArrayList<ScheduleBlock>();
		for(int sbID : sbBlockList) {
			ScheduleBlock sb = jdbc.getSchedule(sbID);
			sbList.add(sb);
		}
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sbList;
	}
	
	  //get store with rating
	public StoreSet getStoresWithRating(int rating) {
		
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		String ratingString = Integer.toString(rating);
		ArrayList<Integer> storeIDs = jdbc.getStoreIDs("RATING", ratingString);
		StoreSet storeSet = jdbc.getStoresWithIDs(storeIDs);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return storeSet;
		
	}
		
		
    //get store with location
	public StoreSet getStoresWithLocation(long latitude, long longitude, long range) {
		
		
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
		
		ArrayList<Integer> storeIDs = 
				jdbc.getStoreIDsWithinRange(latitude, longitude, range);
		StoreSet storeSet = jdbc.getStoresWithIDs(storeIDs);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return storeSet;
		
	}
	
    //get store with cuisine
	public StoreSet getStoresWithCuisine(String cuisine) {
		
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
		
		ArrayList<Integer> storeIDs = 
				jdbc.getStoreIDs("CUISINE", "\""+ cuisine +"\"");
		StoreSet storeSet = jdbc.getStoresWithIDs(storeIDs);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return storeSet;
		
		}	
			
    //get store with pricerange
	public StoreSet getStoresWithPriceRange(int priceRange) {
		
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
		String priceRangeString = Integer.toString(priceRange);
		ArrayList<Integer> storeIDs = 
				jdbc.getStoreIDs("PRICERANGE", priceRangeString);
		StoreSet storeSet = jdbc.getStoresWithIDs(storeIDs);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return storeSet;
		
	}
	

}
