package server;


import java.util.ArrayList;

import model.Profile;
import model.Store;
import model.StoreSet;
import database.DBMethods;
import database.DatabaseConstants;

public class DriverTest extends DatabaseConstants {

	
	public static void main(String[] args) {
		
		DBMethods dbm = new DBMethods();
		
		Profile userProfile = dbm.getProfile("htay");
		
		System.out.println(userProfile.getFirstname());
		System.out.println(userProfile.getLastname());
		System.out.println(userProfile.getPhone());
		System.out.println(userProfile.getEmail());
		System.out.println(userProfile.getAge());
		System.out.println(userProfile.getLikes()[0]);
		System.out.println(userProfile.getLikes()[1]);
		System.out.println(userProfile.getLikes()[2]);
		System.out.println(userProfile.getDislikes()[0]);
		System.out.println(userProfile.getDislikes()[1]);
		System.out.println(userProfile.getDislikes()[2]);
		
		StoreSet storeSet = dbm.getStoresWithRating(3);
		
		ArrayList<Store> storeList = storeSet.getStoreList();
		for(Store store : storeList) {
			System.out.println(store.getName());
			System.out.println(store.getDescription());
		}
		
	}
}
