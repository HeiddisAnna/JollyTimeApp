package is.hi.hbv601g.jollytime.Services;

import java.util.ArrayList;

import is.hi.hbv601g.jollytime.Models.Date;
import is.hi.hbv601g.jollytime.Models.Event;
import is.hi.hbv601g.jollytime.Models.Notification;
import is.hi.hbv601g.jollytime.Models.User;

public class CreateAccount {

    private String email;
    private String name;
    private String password1;
    private String password2;


    public CreateAccount(String email, String name, String password1, String password2) {
        this.email = email;
        this.name = name;
        this.password1 = password1;
        this.password2 = password2;
    }

    // returns true if email exists in database,
    // else false
    public Boolean checkIfEmailExists(ArrayList<User> users) {
        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) return true;
        }
        return false;
    }

    // returns true if password1 == password2
    // else false
    public Boolean checkIfPasswordsMatch() {
        return (password1.equals(password2));
    }

    public void CreateUser(ArrayList<User> users) {
        users.add(new User(name, password1, email,
                new ArrayList<Event>(), new ArrayList<Date>(), new ArrayList<User>(),
                new ArrayList<Notification>()));
    }
}
