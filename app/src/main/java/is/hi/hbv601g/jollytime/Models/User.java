package is.hi.hbv601g.jollytime.Models;
import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")

public class User implements Serializable {

    private long id;

    private String name;
    private String password;
    private String email;

    private List<Event> events;


    private List<Date> dates;

    private List<User> friends;


    private List<Notification> notifications;

    public User() {}

    public User(String name, String password, String email, List<Event> events, List<Date> dates,
                List<User> friends, List<Notification> notifications) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.events = events;
        this.dates = dates;
        this.friends = friends;
        this.notifications = notifications;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
