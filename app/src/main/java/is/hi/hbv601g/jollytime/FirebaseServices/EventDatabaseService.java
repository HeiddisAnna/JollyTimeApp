package is.hi.hbv601g.jollytime.FirebaseServices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import is.hi.hbv601g.jollytime.Activities.CreateAccountActivity;
import is.hi.hbv601g.jollytime.Activities.CreateEventActivity;
import is.hi.hbv601g.jollytime.Models.Event;
import is.hi.hbv601g.jollytime.Models.User;

public class EventDatabaseService {
    private DatabaseReference mDatabase;
    private DatabaseReference mUsersDatabase;
    private AuthenticationService authenticationService;
    private CreateEventActivity createEventActivity;


    public EventDatabaseService(CreateEventActivity createEventActivity) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("events");
        this.mUsersDatabase = FirebaseDatabase.getInstance().getReference("users");
        this.authenticationService = new AuthenticationService();
        this.createEventActivity = createEventActivity;
    }

    public void saveNewEvent(String title, String description, String meStartDate, String meEndDate) {


        String userID = authenticationService.getCurrentUserId();

        Event event = new Event(title, description, meStartDate, meEndDate, userID);

        DatabaseReference pushedEventRef = mDatabase.push();
        String eventID = pushedEventRef.getKey();

        event.setId(eventID);


        pushedEventRef.setValue(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // createEventActivity.onCreatingAccountSuccessfully();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("bla: ", e.getMessage());
                       //  createEventActivity.onCreatingAccountFailure();
                    }
                });

        mUsersDatabase.child(userID).child("eventsID").child(eventID).setValue(true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //    createAccountActivity.onCreatingAccountSuccessfully();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("bla: ", e.getMessage());
                        //   createAccountActivity.onCreatingAccountFailure();
                    }
                });

    }
}

