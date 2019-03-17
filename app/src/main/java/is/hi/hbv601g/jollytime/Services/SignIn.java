package is.hi.hbv601g.jollytime.Services;

import java.util.ArrayList;

import is.hi.hbv601g.jollytime.Models.User;

public class SignIn {
   private String email;
   private String password;

   public SignIn(String email, String password) {
       this.email = email;
       this.password = password;
   }


   // returns true ef email exists in db
   // else false
   public boolean checkIfUserExists(ArrayList<User> users) {
       for (int i=0; i<users.size(); i++) {
           if (users.get(i).getEmail() == email) {
               return true;
           }
       }
       return false;
   }

   // returns true if email and password match for one user
   // else false
   public boolean doesPasswordMatchEmail(ArrayList<User> users) {
       for (int i=0; i<users.size(); i++) {
           if (users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)) {
               return true;
           }
       }
       return false;
   }

}
