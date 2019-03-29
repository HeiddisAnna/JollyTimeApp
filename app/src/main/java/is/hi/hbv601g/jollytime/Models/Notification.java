package is.hi.hbv601g.jollytime.Models;
import java.util.ArrayList;
import java.io.Serializable;



@SuppressWarnings("serial")


public class Notification implements Serializable {

    public long id;
    private String title;
    private String text;


    private Event event;

    private User user;

    public Notification() {}

    public Notification(final int id, String title, String text, Event event) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.event = event;
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

    public String getText() {
        return text;
    }

    public Event getEvent() {
        return event;
    }

}
