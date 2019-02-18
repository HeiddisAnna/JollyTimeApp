package is.hi.hbv601g.jollytime.Models;
import java.util.ArrayList;
import java.util.Date;

public class Event {

    private String title;
    private String description;
    private Date startTime;
    private Date endTime;
    private ArrayList<User> friends;
    //private accepted

    public Event(String title, String description, Date startTime, Date endTime, ArrayList<User> friends) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.friends = friends;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

}
