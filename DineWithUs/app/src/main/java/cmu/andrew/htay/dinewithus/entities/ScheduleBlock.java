package cmu.andrew.htay.dinewithus.entities;

import java.util.Calendar;

/**
 * Created by HuiJun on 4/14/16.
 */
public class ScheduleBlock {
    private int startTime;
    private int endTime;
    private Calendar date;

    public ScheduleBlock() {
        this.startTime = 0;
        this.endTime = 24;
        this.date = Calendar.getInstance();
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Calendar getDate() {
        return date;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }


    public boolean isInRange(int rangeStart, int rangeEnd) {
        if(startTime >= rangeStart && endTime <= rangeEnd) {
            return true;
        }
        return false;
    }

}
