package is.hi.hbv601g.jollytime.Models;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;


public class Date {

    public long id;
    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;

    private List<User> user;

    public Date() {}

    public Date(String title, String description, Timestamp startTime, Timestamp endTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date(Timestamp startTime, Timestamp endTime) {
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }
}
