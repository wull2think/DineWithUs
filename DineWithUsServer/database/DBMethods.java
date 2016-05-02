package database;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import util.IOUtil;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.entities.StoreSet;



public class DBMethods extends DatabaseConstants {
	
	//get
	public boolean authenticate(String username, String password) {
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
	
		String storedPass = jdbc.getPassword(username);

		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (authenticate) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		if(storedPass.equals(password)) {
			return true;
		}
		
		return false;
	}
	
	//get profile for username
	public Profile getProfile(String username) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
	
		int userID = jdbc.getUserID(username);
		Profile storedProfile = jdbc.getProfile(userID);

		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (getProfile) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return storedProfile;
	}
	

	//get all appointments for username
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
			String error = "Database Error (getAppointments) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return appointmentList;
	}
	
	//get all scheduleblocks for username
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
			String error = "Database Error (getScheduleBlocks) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return sbList;
	}
		
		
    //get store with location
	public StoreSet getStores(String cuisine, String price, 
			String opening, String closing,
			double latitude, double longitude, double myrange) {
		
		
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		ArrayList<Integer> storeIDs = null;
		if (myrange > 0) { //range is not -1, no GPS in use
			storeIDs = 
					jdbc.getStoreIDsWithinRange(cuisine, price, 
							opening, closing, latitude, 
							longitude, myrange);
		} else {
			storeIDs = 
					jdbc.getStoreIDs(cuisine, price, opening, closing);
		}
		StoreSet storeSet = jdbc.getStoresWithIDs(storeIDs);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (getStores) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return storeSet;
		
	}
	
	//add
	//add appointment for user
	public int addAppointment(Appointment appointment) {
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		int userA_ID = appointment.getMemberIDs()[0];
		int userB_ID = appointment.getMemberIDs()[1];
		int appointmentID = jdbc.addAppointment(appointment);
		appointment.setAppointmentID(appointmentID);
		jdbc.addAppointmentMapping(userA_ID, appointmentID);	
		jdbc.addAppointmentMapping(userB_ID, appointmentID);	
		
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (addAppointment) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return appointmentID;
		
	}
	
	//add schedule for user
	public int addScheduleBlock(String username, ScheduleBlock sb) {
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		int userID = jdbc.getUserID(username);
		int sbID = jdbc.addSchedule(sb);
		sb.setID(sbID);
		jdbc.addScheduleMapping(userID, sbID);	
		
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (addScheduleBlock) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return sbID;
	}
	
	//update
	
	//update profile for username
	
	//update name
	public void updateProfileName(String username, String firstname, String lastname) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		int userID = jdbc.getUserID(username);
		jdbc.updateProfile(userID, "FIRSTNAME", "\""+firstname+"\"");
		jdbc.updateProfile(userID, "LASTNAME", "\""+lastname+"\"");
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (updateProfileName) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
	
	//update age
	public void updateProfileAge(String username, int age) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
		
		String ageString = Integer.toString(age);
		int userID = jdbc.getUserID(username);
		jdbc.updateProfile(userID, "AGE", ageString);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (updateProfileAge) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
	//update image
	public void updateProfileGender(String username, String gender) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		int userID = jdbc.getUserID(username);
		jdbc.updateProfile(userID, "GENDER", "\""+gender+"\"");
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (updateProfileGender) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
	//update phone
	public void updateProfilePhoneString(String username, String phoneNumber) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		int userID = jdbc.getUserID(username);
		jdbc.updateProfile(userID, "PHONE", "\""+phoneNumber+"\"");
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (updateProfilePhone) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
	//update email
	public void updateProfileEmail(String username, String email) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		int userID = jdbc.getUserID(username);
		jdbc.updateProfile(userID, "EMAIL", "\""+email+"\"");
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (updateProfileEmail) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
	
	//update appointment status for appointmentID
	public void updateAppointmentStatus(int appointmentID, String name,
			String statusA, String statusB) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		jdbc.updateAppointment(appointmentID, "NAME", "\""+name+"\"");
		jdbc.updateAppointment(appointmentID, "STATUS_A", "\""+statusA+"\"");
		jdbc.updateAppointment(appointmentID, "STATUS_B", "\""+statusB+"\"");
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (updateProfileGender) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
	
	//update schedule for username
	
	//update starttime
	public void updateScheduleStart(int scheduleID, int startTime) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
		
		String startTimeString = Integer.toString(startTime);
		jdbc.updateAppointment(scheduleID, "START", startTimeString);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (updateScheduleStart) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
	//update endttime
	public void updateScheduleEnd(int scheduleID, int endTime) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);
		
		String endTimeString = Integer.toString(endTime);
		jdbc.updateAppointment(scheduleID, "END", endTimeString);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (updateScheduleEnd) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
	
	//update date
	public void updateScheduleDate(int scheduleID, String date) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		jdbc.updateScheduleDate(scheduleID, date);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (updateScheduleDate) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
	
	
	//delete
	
	//delete appointment for appointmentID
	public void deleteAppointment(int idAppointment) {
		
		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		jdbc.deleteAppointment(idAppointment);
		jdbc.deleteMapAppointment(idAppointment);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (deleteAppointment) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
	}
	
	//delete schedule for scheduleID
	public void deleteSchedule(int idSchedule) {

		jdbc = new JDBCAdapter(url, driverName,
                user, passwd);

		jdbc.deleteSchedule(idSchedule);
		jdbc.deleteMapSchedule(idSchedule);
		
		try {
			jdbc.close();
		} catch (SQLException e) {
			String error = "Database Error (deleteSchedule) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	

}
