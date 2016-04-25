package server;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.Appointment;
import model.Profile;
import model.ScheduleBlock;
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
		System.out.println(userProfile.getLikes());
		System.out.println(userProfile.getLikes());
		System.out.println(userProfile.getLikes());
		System.out.println(userProfile.getDislikes());
		System.out.println(userProfile.getDislikes());
		System.out.println(userProfile.getDislikes());
		
		StoreSet storeSet = dbm.getStoresWithRating(3);
		
		ArrayList<Store> storeList = storeSet.getStoreList();
		for(Store store : storeList) {
			System.out.println(store.getName());
			System.out.println(store.getDescription());
		}
		
	    storeSet = dbm.getStoresWithLocation(100, 100, 1000);
		
		storeList = storeSet.getStoreList();
		for(Store store : storeList) {
			System.out.println(store.getName());
			System.out.println(store.getDescription());
		}
		
	    storeSet = dbm.getStoresWithCuisine("CHINESE");
		
		storeList = storeSet.getStoreList();
		for(Store store : storeList) {
			System.out.println(store.getName());
			System.out.println(store.getDescription());
		}
		
		storeSet = dbm.getStoresWithPriceRange(2);
		
		storeList = storeSet.getStoreList();
		for(Store store : storeList) {
			System.out.println(store.getName());
			System.out.println(store.getDescription());
		}
		
		System.out.println("Authenticating...");
		System.out.println(dbm.authenticate("htay", "test"));
		
		
		Appointment appt1 = new Appointment();		
		appt1.setMemberIDs(1, 2);
		appt1.setRestaurantID(1);
		appt1.setStartTime(1000);
		appt1.setEndTime(1100);
		appt1.setStatus("PENDING");
		appt1.setDate("5/25/16");
		appt1.setAppointmentID(-1);
		
		
		Appointment appt2 = new Appointment();
		appt2.setMemberIDs(2, 3);
		appt2.setRestaurantID(1);
		appt2.setStartTime(1500);
		appt2.setEndTime(1700);
		appt2.setStatus("PENDING");
		appt2.setDate("5/22/16");
		appt2.setAppointmentID(-1);
		
		System.out.println("adding appt: " + dbm.addAppointment(appt1));
		System.out.println("adding appt: " + dbm.addAppointment(appt2));
		
		
		ArrayList<Appointment> appointmentList = dbm.getAppointments("htay");
		System.out.println("=========== PRINTING APPOINTMENT ===========");
		for(Appointment appt : appointmentList) {
			System.out.println("----------------------------------------------");
			System.out.println("   AppointmentID: " + appt.getAppointmentID());
			System.out.println("   Store: " + appt.getRestaurantName());
			System.out.println("   Date: " + appt.getDate());
			System.out.println("   Start: " + appt.getStartTime());
			System.out.println("   End: " + appt.getEndTime());
			System.out.println("   MemberA: " + appt.getMemberIDs()[0]);
			System.out.println("   MemberB: " + appt.getMemberIDs()[1]);
			System.out.println("   Status: " + appt.getStatus());
		}

		ScheduleBlock sb1 = new ScheduleBlock();
        sb1.setDate("3/10/16");
        sb1.setStartTime(900);
        sb1.setEndTime(1100);
        

		ScheduleBlock sb2 = new ScheduleBlock();
		sb2.setDate("3/12/16");
		sb2.setStartTime(2000);
		sb2.setEndTime(2100);
        

		ScheduleBlock sb3 = new ScheduleBlock();
		sb3.setDate("3/15/16");
		sb3.setStartTime(1600);
		sb3.setEndTime(1800);
		

		System.out.println("adding sb: " + dbm.addScheduleBlock("htay", sb1));
		System.out.println("adding sb: " + dbm.addScheduleBlock("htay", sb2));
		System.out.println("adding sb: " + dbm.addScheduleBlock("htay", sb3));
		
		ArrayList<ScheduleBlock> sbList = dbm.getScheduleBlocks("htay");
		System.out.println("=========== PRINTING SCHEDULE ===========");
		for(ScheduleBlock sb : sbList) {
			System.out.println("----------------------------------------------");
			System.out.println("   ScheduleID: " + sb.getID());
			System.out.println("   Date: " + sb.getDate());
			System.out.println("   Start: " + sb.getStartTime());
			System.out.println("   End: " + sb.getEndTime());
		}
		
		
		
	}
}
