package is.hi.hbv601g.jollytime.Services;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import is.hi.hbv601g.jollytime.Models.Date;

public class BookDateService {

    private Timestamp startTimePeriod;
    private Timestamp endTimePeriod;
    private int timeLength;
    private List<String> usersID;
    private DatabaseReference mDatabase;
    private DatabaseReference mEventDatabase;
    private List<String> eventsID;
    private List<Date> freetime;


    public BookDateService(Timestamp startTimePeriod, Timestamp endTimePeriod, List<String> usersID, int timeLength) {
        this.startTimePeriod = startTimePeriod;
        this.endTimePeriod = endTimePeriod;

        this.usersID = usersID;
        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");
        this.mEventDatabase = FirebaseDatabase.getInstance().getReference("events");
        this.eventsID = new ArrayList<>();
        this.timeLength = timeLength;

        this.freetime = new ArrayList<Date>();
        Date firstFree = new Date(startTimePeriod, endTimePeriod);
        freetime.add(firstFree);
    }

    public void findCommonTimeperiod() {
        for(int i=0; i< usersID.size(); i++) {
            dateHelper(usersID.get(i));
        }
    }


    public Boolean rightDate() {
        boolean result = endTimePeriod.after(startTimePeriod);
        return endTimePeriod.after(startTimePeriod);
    }

    public void dateHelper(String id) {
        mDatabase.child(usersID.get(0)).child("eventsID").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot userDataSnapshot: dataSnapshot.getChildren()) {
                    String id = userDataSnapshot.getKey();
                    eventsID.add(id);
                }
                findFreetime();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

    }

    public void findFreetime() {
        int size = eventsID.size();

        for(int j= 0; j<eventsID.size(); j++){
            String event = eventsID.get(j);
            magigFunction(eventsID.get(j), freetime);
        }
        int i = 0;
    }

    public void magigFunction(String eventID, List<Date> timeperiod) {
        String mStartTime = mDatabase.child(eventID).child("startDate").getKey();
        Timestamp startTime = new Timestamp(Long.parseLong(mStartTime));

        String mEndTime = mDatabase.child(eventID).child("endDate").getKey();
        Timestamp endTime = new Timestamp(Long.parseLong(mEndTime));

        List<Date> result = new ArrayList<Date>();

        for(int i=0; i<timeperiod.size(); i++) {

            Timestamp startPeriod = timeperiod.get(i).getStartTime();
            Timestamp endPeriod = timeperiod.get(i).getEndTime();

            if(endTime.before(startPeriod) || endPeriod.before(startTime)) {
                break;
            } else {
                if(startPeriod.after(startTime)) {
                    result.add(new Date(endTime, endPeriod));
                } else {
                    if( endPeriod.before(endTime)){
                        result.add(new Date(startPeriod, startTime));
                    } else {
                        result.add(new Date(startPeriod,startTime));
                        result.add(new Date(endTime,endPeriod));
                    }
                }
            }
        }
        freetime = result;

    }
}
