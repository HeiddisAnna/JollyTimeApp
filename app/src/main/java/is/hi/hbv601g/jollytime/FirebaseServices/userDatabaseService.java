package is.hi.hbv601g.jollytime.FirebaseServices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.jollytime.Activities.CreateAccountActivity;
import is.hi.hbv601g.jollytime.Models.Event;
import is.hi.hbv601g.jollytime.Models.User;


public class userDatabaseService {

    private DatabaseReference mDatabase;
    private AuthenticationService authenticationService;

    private CreateAccountActivity createAccountActivity;


    public userDatabaseService(AuthenticationService authenticationService) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");
        this.authenticationService = authenticationService;
    }


    public userDatabaseService(CreateAccountActivity createAccountActivity, AuthenticationService authenticationService) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");
        this.authenticationService = authenticationService;
        this.createAccountActivity = createAccountActivity;
    }

    public void saveNewUser(String email, String name) {


        String userID = authenticationService.getCurrentUserId();
        List<Event> events = new ArrayList<Event>();

        User user = new User(email, name, userID);

        mDatabase.child(userID).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        createAccountActivity.onCreatingAccountSuccessfully();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        createAccountActivity.onCreatingAccountFailure();
                    }
                });

    }


}
