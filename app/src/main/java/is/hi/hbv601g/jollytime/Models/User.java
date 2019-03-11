package is.hi.hbv601g.jollytime.Models;
import java.util.ArrayList;
import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
    private String name;
    private String password;
    private String email;
    private ArrayList<Event> events;
    private ArrayList<Event> dates;
    private ArrayList<User> friends;
    private ArrayList<Notification> notifications;

    public User(String name, String password, String email, ArrayList<Event> events, ArrayList<Event> dates,
                ArrayList<User> friends, ArrayList<Notification> notifications) {
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

    public String getPassword() {
        return password;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public ArrayList<Event> getDates() {
        return dates;
    }

    public void addDate(Event date) {
        dates.add(date);
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }
}
