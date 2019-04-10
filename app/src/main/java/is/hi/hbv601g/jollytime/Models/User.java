package is.hi.hbv601g.jollytime.Models;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;


@SuppressWarnings("serial")

public class User implements Serializable {

    private String id;
    private String name;

    private String email;

    // EKKI GLEYMA AÐ TAKA!!!!!
    private String password;

    private List<String> events;
    //private String eventsID;

    private List<String> dates;

    private List<String> friends;
    //private String friendsID;

    private HashMap<String, Boolean> eventsID;
    private HashMap<String, Boolean> friendsID;


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
        this.eventsID = new HashMap<String, Boolean>();
        this.friendsID = new HashMap<String, Boolean>();
    }

    public User(String email, String name, String id, HashMap<String, Boolean> eventsID, HashMap<String, Boolean> friendsID) {
        this.email = email;
        this.name = name;
        this.id = id;
        this.eventsID = eventsID;
        this.friendsID = friendsID;
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


    public HashMap<String, Boolean> getEventsID() {
        return this.eventsID;
    }

    public void setEventsID ( HashMap<String, Boolean> eventsID) {
        this.eventsID = eventsID;
    }

    public HashMap<String, Boolean> getFriendsID() {
        return this.friendsID;
    }

    public void setFriendsID (HashMap<String, Boolean> friendsID) {
        this.friendsID = friendsID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public void addEvent(String event) {
        this.events.add(event);
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

}
