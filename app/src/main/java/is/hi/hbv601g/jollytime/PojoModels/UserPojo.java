package is.hi.hbv601g.jollytime.PojoModels;

import java.util.List;

import is.hi.hbv601g.jollytime.Models.Event;

public class UserPojo {

    private long id;

    private String name;
    private String password;
    private String email;

    private List<Event> events;

    private List<String> dateIds;

    private List<String> friendIds;

    private List<String> notificationIds;

    public UserPojo() {}

    public UserPojo(String name, String password, String email, List<Event> events, List<String> dateIds,
                List<String> friendIds, List<String> notificationIds) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.events = events;
        this.dateIds = dateIds;
        this.friendIds = friendIds;
        this.notificationIds = notificationIds;
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

    public void setName(String name) { this.name = name; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return password;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<String> getDates() {
        return dateIds;
    }

    public void setDates(List<String> dateIds) { this.dateIds = dateIds; }

    public void addDate(String dateId) {
        dateIds.add(dateId);
    }

    public List<String> getFriends() {
        return friendIds;
    }

    public void setFriends(List<String> friendIds) {
        this.friendIds = friendIds;
    }

    public void addFriend(String friendId) {
        friendIds.add(friendId);
    }

    public List<String> getNotifications() {
        return notificationIds;
    }

    public void setNotifications(List<String> notificationIds) {
        this.notificationIds = notificationIds;
    }

    public void addNotification(String notificationId) {
        notificationIds.add(notificationId);
    }

}
