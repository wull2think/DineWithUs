package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.entities.Store;
import cmu.andrew.htay.dinewithus.entities.StoreSet;

import database.DBMethods;

public class Matcher {

	private DBMethods DBWrapper;
	public void runMatch() {
		DBWrapper = new DBMethods();
		ArrayList<String> users = DBWrapper.getUsers();
		
		HashMap<String, ArrayList<ScheduleBlock>> userSchedMap =
				new HashMap<String, ArrayList<ScheduleBlock>>();
		
		for(String username : users) {

			ArrayList<ScheduleBlock> sbPairedList = new ArrayList<>();
			userSchedMap.put(username, sbPairedList);
		}
		
		
		//iterate over all users
		for (String username : users) { //for each user
			for(String otheruser : users) { //for each other user
				if(!otheruser.equals(username)) {
					ArrayList<ScheduleBlock> sbListA = 
							DBWrapper.getScheduleBlocks(username);
					ArrayList<ScheduleBlock> sbListB = 
							DBWrapper.getScheduleBlocks(otheruser);
					ArrayList<ScheduleBlock> sbPairedListA 
										= userSchedMap.get(username);
					ArrayList<ScheduleBlock> sbPairedListB 
										= userSchedMap.get(otheruser);
					
					//for each schedule block in userA
					for (ScheduleBlock sbA : sbListA) {
						if(!sbPairedListA.contains(sbA)) { //if not already paired
							//iterate over each schedule block in userB
							for (ScheduleBlock sbB : sbListB) {
								if(!sbPairedListB.contains(sbB)) { //not already paired
									if(checkMatch(sbA, sbB)) { //check if time overlaps
										sbPairedListA.add(sbA);
										sbPairedListB.add(sbB);
										Appointment appt = createAppointment(username, otheruser, sbA, sbB);
										DBWrapper.addAppointment(appt);
									}
								}
							}
						}
					}
				}
			}
		}
		
		//
	}
	
	public boolean checkMatch(ScheduleBlock sbA, ScheduleBlock sbB) {
		int startTimeA = sbA.getStartTime();
		int startTimeB = sbB.getStartTime();
		int endTimeA = sbA.getEndTime();
		int endTimeB = sbB.getEndTime();
		String dateA = sbA.getDate();
		String dateB = sbB.getDate();
		
		if(dateA.equals(dateB)) {
			//A block is subset of B block
			if(startTimeA >= startTimeB && endTimeA <= endTimeB) {
				return true;
			}
	
			//B block is subset of A block
			if(startTimeB >= startTimeA && endTimeB <= endTimeA) {
				return true;
			}
		}
		
		return false;
	}
	
	public Appointment createAppointment(String userA, String userB,
			ScheduleBlock sbA, ScheduleBlock sbB) { 
		Appointment appointment = new Appointment();
		
		StoreSet stores = DBWrapper.getStores("*", "*", "*", "*", 0, 0, -1);
		ArrayList<Store> storeList = stores.getStoreList();
		
		Random rand = new Random();
		Store store = storeList.get(rand.nextInt(storeList.size()));

		int startTimeA = sbA.getStartTime();
		int startTimeB = sbB.getStartTime();
		int endTimeA = sbA.getEndTime();
		int endTimeB = sbB.getEndTime();
		int startTime = 0;
		int endTime = 24;
		
		//A block is subset of B block
		if(startTimeA >= startTimeB && endTimeA <= endTimeB) {
			startTime = startTimeA;
			endTime = endTimeA;
		}

		//B block is subset of A block
		if(startTimeB >= startTimeA && endTimeB <= endTimeA) {
			startTime = startTimeB;
			endTime = endTimeB;
		}
		
		String apptName = sbA.getDate() + " - " + startTime + " - " + "PENDING";
		appointment.setAppointmentName(apptName);
		appointment.setAppointmentID(-1);
		appointment.setMemberIDs(DBWrapper.getUserID(userA), 
				DBWrapper.getUserID(userB));
		appointment.setRestaurantID(store.getStoreID());
		appointment.setDate(sbA.getDate());
		appointment.setStartTime(startTime);
		appointment.setEndTime(endTime);
		appointment.setStatus("PENDING", "PENDING");
		
		return appointment;
	}

}
