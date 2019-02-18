package is.hi.hbv601g.jollytime.Models;
import java.util.ArrayList;

public class Notification {

    private String title;
    private String text;
    private Event event;
    private ArrayList<User> users;

    public Notification(String title, String text, Event event, ArrayList<User> users) {
        this.title = title;
        this.text = text;
        this.event = event;
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Event getEvent() {
        return event;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
