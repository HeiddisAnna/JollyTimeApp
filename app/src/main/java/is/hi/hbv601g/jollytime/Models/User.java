package is.hi.hbv601g.jollytime.Models;
import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")

public class User implements Serializable {

    private String id;
    private String name;

    private String email;

    // EKKI GLEYMA AÐ TAKA!!!!!
    private String password;

    private List<Event> events;


    private List<Date> dates;

    private List<User> friends;


    private List<Notification> notifications;

    public User() {}

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }


    public User(String email, String name, String id) {
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public User(String email, String password, String name, List<Event> events, List<Date> dates,
                List<User> friends, List<Notification> notifications) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.events = events;
        this.dates = dates;
        this.friends = friends;
        this.notifications = notifications;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Date> getDates() {
        return dates;
    }

    public void addDate(Date date) {
        dates.add(date);
    }

    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

}
