//S16 18-641 Java Smartphone Design
//Hui Jun Tay (htay)
//Project 1

package database;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import util.IOUtil;

import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.entities.Store;
import cmu.andrew.htay.dinewithus.entities.StoreSet;



//helper class based on the provided JDBC code
//provides common methods for building SQL queries
//to retrieve and insert information in a database
public class JDBCAdapter {
    private Connection          connection;
    private  Statement           statement;

    
    //initialize using provided URL, driverName, user, passwd
    //constant values are found in DatabaseConstants.java
    public JDBCAdapter(String url, String driverName,
                       String user, String passwd) {
        try {
            Class.forName(driverName);
            System.out.println("Opening db connection");

            connection = DriverManager.getConnection(url, user, passwd);
            statement = connection.createStatement();
        }
        catch (ClassNotFoundException ex) {
			String error = "Cannot find the database driver classes.";
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");

        }
        catch (SQLException ex) {
			String error = "Cannot connect to this database.";
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
        }
        
     }

    //add
    
    //add to User
    public int addUser(String username, String password) {

		String query =  "INSERT INTO appointments "+ 
		" (`USERNAME`, `PASSWORD`) " +
	    " VALUES (" + "\""+ username + "\", " + password + "\");" ;

    	try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (AddUser) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
			return -1;
		}
    	
    	query = "SELECT LAST_INSERT_ID()";
    	
    	int userID = 0;
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				userID = rs.getInt(1);
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (AddUser) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
    	
		return userID;
    	
   }
    
    //add to Profile
    public void addProfile(Profile profile) {		
		
    	
    	
   }
    
    //add to Appointments
	public int addAppointment(Appointment appointment) {		
		String date = appointment.getDate();
		int idStore = appointment.getRestaurantID();
		int startTime = appointment.getStartTime();
		int endTime = appointment.getEndTime();
		String statusA = appointment.getStatus()[0];
		String statusB = appointment.getStatus()[1];
		String name = appointment.getName();
		
		
		int idUserA = appointment.getMemberIDs()[0];
		int idUserB = appointment.getMemberIDs()[1];

		String query =  "INSERT INTO appointments "+ 
		" (`NAME`, `DATE`, `START`, `END`, " +
		"`idStore`, `idUSER_A`, `idUSER_B`, `STATUS_A`, `STATUS_B`) " +
	    " VALUES (" + "\""+ name + "\", " + 
	    "\"" + date + "\"," + startTime + ", " + endTime +
	    ", " + idStore + ", " + idUserA + ", " + idUserB + 
	    ", \""+ statusA + "\"" + 
	    ", \""+ statusB + "\");" ;

    	try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (addAppointment) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
			return -1;
		}
    	
    	query = "SELECT LAST_INSERT_ID()";
    	
    	int appointmentID = 0;
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				appointmentID = rs.getInt(1);
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (addAppointment) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
			deleteAppointment(appointmentID); //rollback
		}
    	
		return appointmentID;
    	
   }
    
    //add to Schedule
	public int addSchedule(ScheduleBlock schedule) {		
		String date = schedule.getDate();
		int startTime = schedule.getStartTime();
		int endTime = schedule.getEndTime();
		
		String query =  "INSERT INTO schedules "+ 
		" (`DATE`, `START`, `END`) " +
	    " VALUES (\"" + date + "\"," + startTime + ", " + endTime+");" ;

    	try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (addSchedule) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
			return -1;
		}
    	
    	query = "SELECT LAST_INSERT_ID()";
    	
    	int scheduleID = 0;
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				scheduleID = rs.getInt(1);
			}
		}
		catch (SQLException e) {
			String error = "SQL query error (AddUser) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
			deleteSchedule(scheduleID);
		}
    	
		return scheduleID;
    	
   }
	
    //add to Stores
    //add to Likes
    //add to Dislikes
    
    //add MapAppointment
	public void addAppointmentMapping(int idUser, int idAppointment) {		
		String query =  "INSERT INTO mapappointments "+ 
		" (`idUser`, `idAppointment`) " +
	    " VALUES (" + idUser + "," + idAppointment + ");" ;

    	try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (addApptMapping) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
    	
   }
	
    //add MapSchedule
	public void addScheduleMapping(int idUser, int idSchedule) {		
		String query =  "INSERT INTO mapschedule "+ 
		" (`idUser`, `idSchedule`) " +
	    " VALUES (" + idUser + "," + idSchedule + ");" ;

    	try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (addSchedMapping) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
    	
   }
    
    
    
    //update
    
    //update User
	public void updatePassword(int idUser, String newPassword) {
		String query =  "UPDATE users " + " SET " + 
				"PASSWORD" + " = " + newPassword +
				" WHERE idUser = " + idUser + ";" ;
	
	try {
		statement.executeUpdate(query);
	} catch (SQLException e) {
		String error = "SQL update error (updatePassword) " + e.toString();
		System.err.println(error);
		IOUtil.logFile(error, "log.txt");
	}
	
	}
	
    //update Profile
	public void updateProfile(int idUser, String field, String value) {
		String query =  "UPDATE profiles " + " SET " + field + " = " + value +
					" WHERE idUser = " + idUser + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (updateProfile) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
	}
	
    //update Appointment
	public void updateAppointment(int idAppointment,String field, String value) {
		String query =  "UPDATE appointments " + " SET " + field + " = " + value +
				" WHERE idAppointment = " + idAppointment + ";" ;
	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (updateAppointment) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
    //update Schedule
	public void updateSchedule(int idSchedule, String field, String value) {
		String query =  "UPDATE schedules " + " SET " + field + " = " + value +
				" WHERE idSchedule = " + idSchedule + ";" ;
	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (updateSchedule) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	

	public void updateScheduleDate(int idSchedule, String date) {
		String query =  "UPDATE schedules " + " SET " + "DATE" + " = \"" + date +
				"\" WHERE idSchedule = " + idSchedule + ";" ;
	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (addScheduleDate) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
	
	
    //update Store
	public void updateStore(int idStore, String field, String value) {
		String query =  "UPDATE stores " + " SET " + field + " = " + value +
				" WHERE idStore = " + idStore + ";" ;
	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (updateStore) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
	}
    
    
    //delete
    
    //delete User
    public void deleteUser(int ID) {

		String query =  "DELETE FROM " + "users" + 
		" WHERE idUser = " + ID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (deleteUser) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
    }
    
    //delete Appointment
    public void deleteAppointment(int appointmentID) {

		String query =  "DELETE FROM " + "appointments" + 
		" WHERE idAppointment = " + appointmentID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (deleteAppt) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
    }

    //delete Schedule
    public void deleteSchedule(int scheduleID) {

		String query =  "DELETE FROM " + "users" + 
		" WHERE idSchedule = " + scheduleID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (deleteSched) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
    }
    
    //delete MapAppointment
    public void deleteMapAppointment(int appointmentID) {

		String query =  "DELETE FROM " + "mapappointment" + 
		" WHERE idAppointment = " + appointmentID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (deleteMapAppt) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
    }
    
    //delete MapProfile
    public void deleteMapProfile(int profileID) {

		String query =  "DELETE FROM " + "mapprofile" + 
		" WHERE idProfile = " + profileID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (deleteMapProfile) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
    }
    
    //delete MapSchedule
    public void deleteMapSchedule(int mapID) {

		String query =  "DELETE FROM " + "appointmentmap" + 
		" WHERE idSchedule = " + mapID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			String error = "SQL update error (deleteMapSchedule) " + e.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
    }
    

    //delete Profile
    //delete Store
    //delete Like
    //delete Dislike
    
    
    
    //get
    
    //get password for USERNAME
	public String getPassword(String name) {

		String query =  "SELECT PASSWORD FROM " + "users"  + 
				" WHERE USERNAME = \"" + name + "\";";
		String password = "";

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				password = rs.getString("PASSWORD");
			}
		}
		catch (SQLException ex) {
			String error = "Username not found" + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}

		return password;
	}

    
    //get userID for USERNAME
	public int getUserID(String name) {

		String query =  "SELECT idUser FROM " + "users"  + 
				" WHERE USERNAME = \"" + name + "\";";
		int id = -1;

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("idUser");
			}
		}
		catch (SQLException ex) {
			String error = "Username not found" + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}

		return id;
	}
	
	public String getUsername(int id) {

		String query =  "SELECT USERNAME FROM " + "users"  + 
				" WHERE idUser = " + id + ";";
		
		String username = "";

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				username = rs.getString("USERNAME");
			}
		}
		catch (SQLException ex) {
			String error = "UserID not found" + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}

		return username;
	}

    //get ProfileID for UserID
	public Profile getProfile(int id) {
		Profile storedProfile = new Profile(); //default to blank profile
		String query =  "SELECT * FROM " + "profiles" + 
						" WHERE idUser = " + id;

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				// = rs.getInt("idProfile");
				storedProfile.setFirstname(rs.getString("FIRSTNAME"));
				storedProfile.setLastname(rs.getString("LASTNAME"));
				storedProfile.setPhone(rs.getString("PHONE"));
				storedProfile.setEmail(rs.getString("EMAIL"));
				storedProfile.setAge(rs.getInt("AGE"));
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (getProfile) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		//get likes
		query =  "SELECT * FROM " + "Likes" + 
				" WHERE idUser = " + id;

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				storedProfile.addLike(rs.getString("Like"));
				
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (getLikes) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		//get dislikes
		query =  "SELECT * FROM " + "Dislikes" + 
				" WHERE idUser = " + id;

		try {
			ResultSet rs = statement.executeQuery(query);
			int i = 0;
			while (rs.next()) {
				storedProfile.addDislike(rs.getString("Dislike"));
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (getDislikes) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return storedProfile;
	}

	
	//get appointments for userID
	public ArrayList<Integer> getAppointmentIDs(int userID) {

		ArrayList<Integer> appointmentIDs = new ArrayList<Integer>();
		
		String query =  "SELECT * FROM " + "mapappointments" + 
				" WHERE idUser = " + userID;

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				appointmentIDs.add(rs.getInt("idAppointment"));
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (getApptIDs) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return appointmentIDs;
		
	}
	
	
    //get appointment for appointmentID
	public Appointment getAppointment(int appointmentID) {

		Appointment appointment = new Appointment();
		String query =  "SELECT * FROM " + "appointments"  + 
				" WHERE idAppointment = \"" + appointmentID + "\";";

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				appointment.setAppointmentName(rs.getString("NAME"));
				appointment.setAppointmentID(rs.getInt("idAppointment"));
				appointment.setMemberIDs(rs.getInt("idUSER_A"), rs.getInt("idUSER_B"));
				appointment.setRestaurantID(rs.getInt("idStore"));
				appointment.setDate(rs.getString("DATE"));
				appointment.setStartTime(rs.getInt("START"));
				appointment.setEndTime(rs.getInt("END"));
				appointment.setStatus(rs.getString("STATUS_A"), rs.getString("STATUS_B"));
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (getAppt) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		String userA = getUsername(appointment.getMemberIDs()[0]);
		String userB = getUsername(appointment.getMemberIDs()[1]);
		appointment.setMemberNames(userA, userB);

		return appointment;
	}
	
    //get schedules for UserID
	public ArrayList<Integer> getScheduleIDs(int userID) {

		ArrayList<Integer> scheduleIDs = new ArrayList<Integer>();
		
		String query =  "SELECT * FROM " + "mapschedule" + 
				" WHERE idUser = " + userID;

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				scheduleIDs.add(rs.getInt("idSchedule"));
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (getSchedIDs) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return scheduleIDs;
		
	}
	
	
    //get schedule for scheduleID
	public ScheduleBlock getSchedule(int scheduleID) {

		ScheduleBlock schedule = new ScheduleBlock();
		String query =  "SELECT * FROM " + "schedules"  + 
				" WHERE idSchedule = \"" + scheduleID + "\";";

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				schedule.setID(rs.getInt("idSchedule"));
				schedule.setDate(rs.getString("DATE"));
				schedule.setStartTime(rs.getInt("START"));
				schedule.setEndTime(rs.getInt("END"));
				
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (getSched) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}

		return schedule;
	}
	
    
	//get stores for ids
	public StoreSet getStoresWithIDs(ArrayList<Integer> idList) {
		
		StoreSet storeSet = new StoreSet();
		String set = "";
		for(int id : idList) {
			set = set+id+",";
		}
		
		if(idList.isEmpty()) {
			System.err.println("No Stores Found for Search");
			return storeSet; //default to empty stores
		}
		//cut the , off the last bit
		set = set.substring(0,set.length()-1);

		String query =  "SELECT * FROM " + "stores" + 
				" WHERE " + "idStore" + " in (" + set + ");";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Store store = new Store();
				store.setStoreID(rs.getInt("idStore"));
				store.setName(rs.getString("NAME"));
				store.setAddress(rs.getString("LOCATION"));
				store.setLatitude(rs.getLong("LATITUDE"));
				store.setLongitude(rs.getLong("LONGITUDE"));
				store.setRating(rs.getInt("RATING"));
				store.setOpeningTime(rs.getInt("START"));
				store.setClosingTime(rs.getInt("END"));
				store.setPriceRange(rs.getString("PRICERANGE"));
				store.setCuisine(rs.getString("CUISINE"));
				store.setDescription(rs.getString("DESCRIPTION"));
				store.setMenuURL(rs.getString("MENUURL"));
				store.setShopPictureURL(rs.getString("IMAGEURL"));
				store.setWebsiteURL(rs.getString("WEBSITE"));
				store.setPhone(rs.getString("PHONE"));
				storeSet.addStore(store);
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (getStoresForIDs) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}

		
		
		return storeSet;
	}
	
	//get all store ids where 
	//cuisine, price and/or rating equals value
	public ArrayList<Integer> getStoreIDs(String cuisine, 
			String price, String opening, String closing) {

		ArrayList<Integer> storeIDs = new ArrayList<Integer>();
		
		String template =  "SELECT idStore FROM " + "stores" + 
				" WHERE idStore IS NOT NULL";
		

        StringBuilder sb = new StringBuilder();
        sb.append(template);
		
		 if(!cuisine.equals("*")) {
			sb.append(" AND CUISINE = ");
			sb.append("\"");
			sb.append(cuisine);
			sb.append("\"");
			
		 }
		 if(!price.equals("*")) {
				sb.append(" AND PRICERANGE = ");
				sb.append("\"");
				sb.append(price);
				sb.append("\"");
		 }

		 if(!opening.equals("*")) {
				sb.append(" AND START >= ");
				sb.append(opening);
			 
		 }
		 
		 if(!closing.equals("*")) {
				sb.append(" AND END <= ");
				sb.append(closing);
		 }
		 

        String query = sb.toString();
		System.out.println(query);
		
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				storeIDs.add(rs.getInt("idStore"));
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (getStoreIDs) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return storeIDs;
		
	}
	
	//get all store ids with latitude, longitude in range and
	//cuisine, price and/or rating equals value
	public ArrayList<Integer> getStoreIDsWithinRange(String cuisine, 
			String price, String opening, String closing,
			double latitude, 
			double longitude, double range) {
		

		double latMax = latitude + range;
		double latMin = latitude - range;
		double longMax = longitude + range;
		double longMin = longitude - range;

		ArrayList<Integer> storeIDs = new ArrayList<Integer>();
		
		String template =  "SELECT idStore FROM " + "stores" + 
				" WHERE idStore IS NOT NULL" +
				" AND LATITUDE > " + latMin + " AND LATITUDE < " + latMax +
				" AND LONGITUDE > " + longMin + " AND LONGITUDE < " +longMax;;
		

        StringBuilder sb = new StringBuilder();
        sb.append(template);
		
        if(!cuisine.equals("*")) {
			sb.append(" AND CUISINE = ");
			sb.append("\"");
			sb.append(cuisine);
			sb.append("\"");
			
		 }
		 if(!price.equals("*")) {
				sb.append(" AND PRICERANGE = ");
				sb.append("\"");
				sb.append(price);
				sb.append("\"");
		 }

		 if(!opening.equals("*")) {
				sb.append(" AND START >= ");
				sb.append(opening);
			 
		 }
		 
		 if(!closing.equals("*")) {
				sb.append(" AND END <= ");
				sb.append(closing);
		 }
		 

        String query = sb.toString();
		System.out.println(query);
		
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				storeIDs.add(rs.getInt("idStore"));
			}
		}
		catch (SQLException ex) {
			String error = "SQL query error (getStoreIDswRange) " + ex.toString();
			System.err.println(error);
			IOUtil.logFile(error, "log.txt");
		}
		
		return storeIDs;
		
	}
  
	
	 /**
     * closes the JDBC connection
     */

    public void close() throws SQLException {
        System.out.println("Closing db connection");
        statement.close();
        connection.close();
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
    
    
}
