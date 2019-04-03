package is.hi.hbv601g.jollytime.Models;
import android.support.annotation.IdRes;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.io.Serializable;


public class Event implements Serializable {

    public String id;
    private String title;
    private String description;
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;

    private int startYear;
    private int startMonth;
    private int startDay;
    private int startHour;
    private int startMin;

    private int endYear;
    private int endMonth;
    private int endDay;
    private int endHour;
    private int endMin;


    private String userID;
    //private accepted

    public Event() {}

    public Event(String title, String description,
                 GregorianCalendar startTime, GregorianCalendar endTime,
                 String userID) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userID = userID;
    }

    public Event(String title, String description, int startYear, int startMonth, int startDay,
                 int startHour, int startMin, int endYear, int endMonth, int endDay, int endHour,
                 int endMin, String userID) {
        this.title = title;
        this.description = description;

        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.startHour = startHour;
        this.startMin = startMin;

        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.endHour = endHour;
        this.endMin = endMin;

        this.userID = userID;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(GregorianCalendar endTime) {
        this.endTime = endTime;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return description;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMin() {
        return startMin;
    }

    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMin() {
        return endMin;
    }

    public void setEndMin(int endMin) {
        this.endMin = endMin;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }

}
