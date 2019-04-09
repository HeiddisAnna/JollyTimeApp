package is.hi.hbv601g.jollytime.FirebaseServices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import is.hi.hbv601g.jollytime.Activities.CalendarActivity;
import is.hi.hbv601g.jollytime.Activities.CreateEventActivity;
import is.hi.hbv601g.jollytime.Activities.DayActivity;
import is.hi.hbv601g.jollytime.Models.Event;

public class EventDatabaseService {
    public interface EventDatabaseServiceDelegate {
        void updateEvent(Event event);
    /*void onCreatingAccountSuccessfully();
    void onCreatingAccountFailure();*/
    }

    private DatabaseReference mDatabase;
    private DatabaseReference mUsersDatabase;
    private AuthenticationService authenticationService;


    /*private CreateEventActivity createEventActivity;
    private CalendarActivity calendarActivity;
    private DayActivity dayActivity;*/
    private EventDatabaseServiceDelegate delegate;


    public EventDatabaseService(EventDatabaseServiceDelegate activity) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("events");
        this.mUsersDatabase = FirebaseDatabase.getInstance().getReference("users");
        this.authenticationService = new AuthenticationService();
        this.delegate = delegate;
    }

    public void getUserEvents() {

        String userId = authenticationService.getCurrentUserId();
        final EventDatabaseServiceDelegate delegate = this.delegate;


        mUsersDatabase.child(userId).child("eventsID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    String eventsID = userSnapshot.getKey();
                    //createListOfEvents(eventsID);
                    mDatabase.child(eventsID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Event event = new Event(dataSnapshot.child("title").getKey(), dataSnapshot.child("description").getKey(),
                                    dataSnapshot.child("startDate").getKey(), dataSnapshot.child("endDate").getKey(),
                                    dataSnapshot.child("userID").getKey());
                            delegate.updateEvent(event);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

