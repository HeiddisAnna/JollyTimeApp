package is.hi.hbv601g.jollytime.Models;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Event implements Serializable {

    private String title;
    private String description;
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;
    private ArrayList<User> friends;
    //private accepted

    public Event(String title, String description, GregorianCalendar startTime, GregorianCalendar endTime, ArrayList<User> friends) {
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

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

}
