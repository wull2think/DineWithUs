package model;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by ashresth on 4/10/2016.
 */
public class Appointment {
    private int appointmentID;
    private int[] memberIDs;
    private int restaurantID;
    private Date appointmentDate;
    private int startTime;
    private int endTime;
    private String status;

    public Appointment(){
        //TODO: Create appointment in database
        //TODO: Get appointmentID from database
        this.appointmentID = 0;

        this.memberIDs = new int[2];
    }

    public void setMemberIDs(int ID1, int ID2){
        this.memberIDs[0] = ID1;
        this.memberIDs[1] = ID2;
    }

    public void setRestaurantID(int ID){
        this.restaurantID = ID;
    }

    public void setStartTime(int startTime){
        this.startTime = startTime;
    }
    

    public void setEndTime(int endTime){
        this.endTime = endTime;
    }
  

    public void setStatus(String status){
        this.status = status;
    }

    public void setDate(Date date){
        this.appointmentDate = date;
    }



    public int[] getMemberIDs(){
        return this.memberIDs;
    }

    public int getRestaurantID(){
        return this.restaurantID;
    }

    public int getAppointmentID(){
        return this.appointmentID;
    }

    public Date getDate(){
        return this.appointmentDate;
    }
    
    public int getStartTime(int startTime){
    	return this.startTime;
    }
    

    public int getEndTime(int endTime){
    	return this.endTime;
    }
  

    public String getStatus(String status){
    	return this.status;
    }

    

    public String getDateString(){
        //TODO: Remove timezone and year from this
        return this.appointmentDate.toString();
    }

    public String getRestaurantName(){
        //TODO: Implement this
        return "Orient-Express";
    }

    
    
    
    public String toString(){
        //TODO: Remove timezone and year from this
        return this.appointmentDate.toString() + " | " + this.getRestaurantName();
    }

    public static LinkedHashMap<String, Appointment> getAllAppointments(int userID){
        //TODO: Access database
        LinkedHashMap<String, Appointment> appLHM = new LinkedHashMap<String, Appointment>();
        Appointment A = new Appointment();
        A.setDate(new Date(1460424489));
        Appointment B = new Appointment();
        B.setDate(new Date(1459468800));
        B.appointmentID = 2;
        appLHM.put(A.toString(), A);
        appLHM.put(B.toString(), B);
        return appLHM;
    }

    /*
    public static ArrayList<Appointment> getAllAppointments(int userID){
        //TODO: Access database
        ArrayList<Appointment> appList = new ArrayList<Appointment>();
        Appointment A = new Appointment();
        A.setDate(new Date(1460424489));
        Appointment B = new Appointment();
        B.setDate(new Date(1460424489));
        B.appointmentID = 2;
        appList.add(A);
        appList.add(B);
        return appList;
    }
    */

}