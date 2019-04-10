package is.hi.hbv601g.jollytime.Models;

import java.util.GregorianCalendar;
import java.util.List;


public class Date {

    public long id;
    private String title;
    private String description;
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;

    private List<User> user;

    public Date() {}

    public Date(String title, String description, GregorianCalendar startTime, GregorianCalendar endTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date(GregorianCalendar startTime, GregorianCalendar endTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
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
