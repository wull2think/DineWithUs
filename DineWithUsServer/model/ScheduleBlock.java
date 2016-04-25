package model;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HuiJun on 4/14/16.
 */
public class ScheduleBlock implements Serializable {
	private int sbID;
    private int startTime;
    private int endTime;
    private String date;

    public ScheduleBlock() {
        this.startTime = 0;
        this.endTime = 24;
    }


    public int getID() {
        return sbID;
    }

    
    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }


    public void setID(int id) {
        this.sbID = id;
    }

    
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public boolean isInRange(int rangeStart, int rangeEnd) {
        if(startTime >= rangeStart && endTime <= rangeEnd) {
            return true;
        }
        return false;
    }

}
