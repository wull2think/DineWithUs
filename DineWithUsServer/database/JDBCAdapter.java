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

import model.Appointment;
import model.Profile;
import model.ScheduleBlock;
import model.Store;
import model.StoreSet;

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
            System.err.println("Cannot find the database driver classes.");
            System.err.println(ex);
        }
        catch (SQLException ex) {
            System.err.println("Cannot connect to this database.");
            System.err.println(ex);
        }
        
     }

    //add
    
    //add to User
    //add to Profile
    
    
    //add to Appointments
	public int addAppointment(Appointment appointment) {		
		Date date = new Date(appointment.getDate().getTime());
		int idStore = appointment.getRestaurantID();
		int startTime = appointment.getStartTime();
		int endTime = appointment.getEndTime();
		String status = appointment.getStatus();
		String restaurantName = appointment.getRestaurantName();
		
		
		int idUserA = appointment.getMemberIDs()[0];
		int idUserB = appointment.getMemberIDs()[1];

		String query =  "INSERT INTO appointments "+ 
		" (`NAME`, `DATE`, `START`, `END`, " +
		"`idStore`, `idUSER_A`, `idUSER_B`, `STATUS`) " +
	    " VALUES (" + "\""+ restaurantName + "\", " + 
		date + "," + startTime + ", " + endTime +
	    ", " + idStore + ", " + idUserA + ", " + idUserB + 
	    ", \""+ status + "\");" ;

    	try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	query = "SELECT LAST_INSERT_ID()";
    	
    	int appointmentID = 0;
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				appointmentID = rs.getInt("last_id");
			}
		}
		catch (SQLException ex) {
			System.err.println(ex);
		}
    	
		return appointmentID;
    	
   }
    
    //add to Schedule
	public int addSchedule(ScheduleBlock schedule) {		
		Date date = new Date(schedule.getDate().getTime());
		int startTime = schedule.getStartTime();
		int endTime = schedule.getEndTime();
		
		String query =  "INSERT INTO appointments "+ 
		" (`DATE`, `START`, `END`) " +
	    " VALUES (" + date + "," + startTime + ", " + endTime+"\");" ;

    	try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	query = "SELECT LAST_INSERT_ID()";
    	
    	int scheduleID = 0;
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				scheduleID = rs.getInt("last_id");
			}
		}
		catch (SQLException ex) {
			System.err.println(ex);
		}
    	
		return scheduleID;
    	
   }
	
    //add to Stores
    //add to Likes
    //add to Dislikes
    
    //add MapAppointment
    //add MapProfiles
    //add MapSchedule
    
    
    
    //update
    
    //update User
    //update Profile
    //update Appointment
    //update Schedule
    //update Store
    
    
    //delete
    
    //delete User
    public void deleteUser(int ID) {

		String query =  "DELETE FROM " + "users" + 
		" WHERE idUser = " + ID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //delete Appointment
    public void deleteAppointment(int appointmentID) {

		String query =  "DELETE FROM " + "appointments" + 
		" WHERE idAppointment = " + appointmentID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //delete Schedule
    public void deleteSchedule(int scheduleID) {

		String query =  "DELETE FROM " + "users" + 
		" WHERE idSchedule = " + scheduleID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //delete MapAppointment
    public void deleteMapAppointment(int appointmentID) {

		String query =  "DELETE FROM " + "mapappointment" + 
		" WHERE idAppointment = " + appointmentID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //delete MapProfile
    public void deleteMapProfile(int profileID) {

		String query =  "DELETE FROM " + "mapprofile" + 
		" WHERE idProfile = " + profileID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //delete MapSchedule
    public void deleteMapSchedule(int mapID) {

		String query =  "DELETE FROM " + "appointmentmap" + 
		" WHERE idSchedule = " + mapID + ";" ;
    	
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			System.err.println(ex);
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
			System.err.println(ex);
		}

		return id;
	}

    //get ProfileID for UserID
	public Profile getProfile(int id) {
		Profile storedProfile = new Profile();
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
			System.err.println(ex);
		}
		
		//get likes
		query =  "SELECT * FROM " + "Likes" + 
				" WHERE idUser = " + id;

		try {
			ResultSet rs = statement.executeQuery(query);
			int i = 0;
			while (rs.next()) {
				if(i < 3) {
					storedProfile.setLikes(rs.getString("Like"), i);
					i++;
				}
			}
		}
		catch (SQLException ex) {
			System.err.println(ex);
		}
		
		//get dislikes
		query =  "SELECT * FROM " + "Dislikes" + 
				" WHERE idUser = " + id;

		try {
			ResultSet rs = statement.executeQuery(query);
			int i = 0;
			while (rs.next()) {
				if(i < 3) {
					storedProfile.setDislikes(rs.getString("Dislike"), i);
					i++;
				}
			}
		}
		catch (SQLException ex) {
			System.err.println(ex);
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
				appointmentIDs.add(rs.getInt("idAppointments"));
			}
		}
		catch (SQLException ex) {
			System.err.println(ex);
		}
		
		return appointmentIDs;
		
	}
	
	
    //get appointment for appointmentID
	public Appointment getAppointment(int appointmentID) {

		Appointment appointment = new Appointment();
		String query =  "SELECT * FROM " + "appointments"  + 
				" WHERE idAppointments = \"" + appointmentID + "\";";

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				appointment.setMemberIDs(rs.getInt("idUSER_A"), rs.getInt("idUSER_B"));
				appointment.setRestaurantID(rs.getInt("idStore"));
				appointment.setDate(rs.getDate("DATE"));
				appointment.setStartTime(rs.getInt("START"));
				appointment.setEndTime(rs.getInt("END"));
				appointment.setStatus(rs.getString("STATUS"));
			}
		}
		catch (SQLException ex) {
			System.err.println(ex);
		}

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
			System.err.println(ex);
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
				schedule.setDate(rs.getDate("DATE"));
				schedule.setStartTime(rs.getInt("START"));
				schedule.setEndTime(rs.getInt("END"));
				
			}
		}
		catch (SQLException ex) {
			System.err.println(ex);
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
		
		//cut the , off the last bit
		set = set.substring(0,set.length()-1);

		String query =  "SELECT * FROM " + "stores" + 
				" WHERE " + "idStore" + " in (" + set + ");";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Store store = new Store();
				store.setName(rs.getString("NAME"));
				store.setAddress(rs.getString("LOCATION"));
				store.setLatitude(rs.getLong("LATITUDE"));
				store.setLongitude(rs.getLong("LONGITUDE"));
				store.setRating(rs.getInt("RATING"));
				store.setOpeningTime(rs.getInt("START"));
				store.setClosingTime(rs.getInt("END"));
				store.setPriceRange(rs.getInt("PRICERANGE"));
				store.setCuisine(rs.getString("CUISINE"));
				store.setDescription(rs.getString("DESCRIPTION"));
				store.setMenuURL(rs.getString("MENUURL"));
				store.setShopPictureURL(rs.getString("IMAGEURL"));
				store.setWebsiteURL(rs.getString("WEBSITE"));
				storeSet.addStore(store);
			}
		}
		catch (SQLException ex) {
			System.err.println(ex);
		}

		
		
		return storeSet;
	}
	
	//get all store ids where field equals value
	public ArrayList<Integer> getStoreIDs(String field, String value) {

		ArrayList<Integer> storeIDs = new ArrayList<Integer>();
		
		String query =  "SELECT idStore FROM " + "stores" + 
				" WHERE " + field + " = " + value;

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				storeIDs.add(rs.getInt("idStore"));
			}
		}
		catch (SQLException ex) {
			System.err.println(ex);
		}
		
		return storeIDs;
		
	}
	
	//get all store ids where field equals value
	public ArrayList<Integer> getStoreIDsWithinRange(long latitude, 
			long longitude, long range) {
		

		long latMax = latitude + range;
		long latMin = latitude - range;
		long longMax = longitude + range;
		long longMin = longitude - range;

		ArrayList<Integer> storeIDs = new ArrayList<Integer>();
		
		String query =  "SELECT idStore FROM " + "stores" + 
				" WHERE LATITUDE > " + latMin + " AND LATITUDE < " + latMax +
				" AND LONGITUDE > " + longMin + " AND LONGITUDE < " +longMax;

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				storeIDs.add(rs.getInt("idStore"));
			}
		}
		catch (SQLException ex) {
			System.err.println(ex);
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
