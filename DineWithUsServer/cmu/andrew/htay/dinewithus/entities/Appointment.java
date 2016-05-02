package cmu.andrew.htay.dinewithus.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by ashresth on 4/10/2016.
 */
public class Appointment implements Serializable {
	private String name;
    private int appointmentID = -1;
    private int[] memberIDs;
    private int restaurantID;
    private String appointmentDate;
    private int startTime;
    private int endTime;
    private String[] status;

    public Appointment(){
        //TODO: Create appointment in database
        //TODO: Get appointmentID from database
        this.appointmentID = 0;

        this.memberIDs = new int[2];
        this.status = new String[2];
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
  

    public void setStatus(String statusA, String statusB){
        this.status[0] = statusA;
        this.status[1] = statusB;
    }

    public void setDate(String date){
        this.appointmentDate = date;
    }
    
    public void setAppointmentID(int appointmentID){
        this.appointmentID = appointmentID;
    }

    public void setAppointmentName(String name){
        this.name = name;
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

    public String getDate(){
        return this.appointmentDate;
    }
    
    public int getStartTime(){
    	return this.startTime;
    }
    

    public int getEndTime(){
    	return this.endTime;
    }
  

    public String[] getStatus(){
    	return this.status;
    }

    

    public String getDateString(){
        //TODO: Remove timezone and year from this
        return this.appointmentDate.toString();
    }

    public String getName(){
        //TODO: Implement this
        return name;
    }

    
    
    public String toString(){
        //TODO: Remove timezone and year from this
        return this.appointmentDate.toString() + " | " + this.getName();
    }

}
