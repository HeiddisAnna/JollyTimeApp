package is.hi.hbv601g.jollytime.Models;
import android.support.annotation.IdRes;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.io.Serializable;


public class Event implements Serializable {

    public long id;
    private String title;
    private String description;
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;


    private User user;
    //private accepted

    public Event() {}

    public Event(String title, String description,
                 GregorianCalendar startTime, GregorianCalendar endTime,
                 User user) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }

}
