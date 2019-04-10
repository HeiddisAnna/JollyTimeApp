package is.hi.hbv601g.jollytime.Services;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import is.hi.hbv601g.jollytime.Models.Date;

public class BookDateService {

    private GregorianCalendar startTimePeriod;
    private GregorianCalendar endTimePeriod;
    private List<String> usersID;
    private DatabaseReference mDatabase;
    private List<String> eventsID;
    private List<Date> freetime;


    public BookDateService(GregorianCalendar startTimePeriod, GregorianCalendar endTimePeriod, List<String> usersID) {
        this.startTimePeriod = startTimePeriod;
        this.endTimePeriod = endTimePeriod;
        this.usersID = usersID;
        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");
        this.freetime = new ArrayList<Date>();
        this.eventsID = new ArrayList<>();
    }

    public void findCommonTimeperiod() {
        for(int i=0; i< usersID.size(); i++) {
            AtomicInteger result = dateHelper(usersID.get(i));
            int size = eventsID.size();
            for(int j= 0; j<eventsID.size(); j++){
                magigFunction(eventsID.get(i));
            }
        }
    }

    public AtomicInteger dateHelper(String id) {
        final AtomicBoolean done = new AtomicBoolean(false);
        final AtomicInteger message1 = new AtomicInteger(0);

        mDatabase.child(usersID.get(0)).child("eventsID").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userDataSnapshot: dataSnapshot.getChildren()) {
                    String id = userDataSnapshot.getKey();
                    eventsID.add(id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        while (!done.get());

        return message1;
    }

    public void magigFunction(String event) {
        int p = 5;
    }
}
