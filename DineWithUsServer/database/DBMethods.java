package database;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Profile;
import model.StoreSet;

public class DBMethods extends DatabaseConstants {
	
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

	  //get store with rating
	public StoreSet getStoresWithRating(int rating) {
		
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
		
		ArrayList<Integer> storeIDs = jdbc.getStoreIDs("RATING", "3");
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
	public StoreSet getStoresWithLocation(int rating) {
		
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
		
		ArrayList<Integer> storeIDs = jdbc.getStoreIDs("RATING", "3");
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
	public StoreSet getStoresWithCuisine(int rating) {
		
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
		
		ArrayList<Integer> storeIDs = jdbc.getStoreIDs("RATING", "3");
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
	public StoreSet getStoresWithPriceRange(int rating) {
		
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
		
		ArrayList<Integer> storeIDs = jdbc.getStoreIDs("RATING", "3");
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
