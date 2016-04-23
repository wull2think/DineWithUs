package model;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by HuiJun on 4/14/16.
 */
public class ScheduleBlock {
    private int startTime;
    private int endTime;
    private Date date;

    public ScheduleBlock() {
        this.startTime = 0;
        this.endTime = 24;
        Calendar time = Calendar.getInstance();
        this.date = time.getTime();
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public boolean isInRange(int rangeStart, int rangeEnd) {
        if(startTime >= rangeStart && endTime <= rangeEnd) {
            return true;
        }
        return false;
    }

}
