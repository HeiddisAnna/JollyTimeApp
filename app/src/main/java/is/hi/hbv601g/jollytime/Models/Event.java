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

    private String startDate;
    private String endDate;


    private String userID;
    //private accepted

    public Event() {
    }

    public Event(String title, String description,
                 GregorianCalendar startTime, String startDate, GregorianCalendar endTime, String endDate,
                 String userID) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.startDate = startDate;
        this.endTime = endTime;
        this.endDate = endDate;
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

