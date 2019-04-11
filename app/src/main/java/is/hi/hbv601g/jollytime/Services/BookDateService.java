package is.hi.hbv601g.jollytime.Services;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import is.hi.hbv601g.jollytime.Activities.BookADateFragment;
import is.hi.hbv601g.jollytime.Models.Date;
import is.hi.hbv601g.jollytime.Models.Event;
import is.hi.hbv601g.jollytime.Models.Tuple;

public class BookDateService {

    private Timestamp startTimePeriod;
    private Timestamp endTimePeriod;
    private int timeLength;
    private List<String> usersID;
    private DatabaseReference mDatabase;
    private DatabaseReference mEventDatabase;
    private Fragment BookADateFragment;


    public BookDateService(Timestamp startTimePeriod, Timestamp endTimePeriod, List<String> usersID, int timeLength) {
        this.startTimePeriod = startTimePeriod;
        this.endTimePeriod = endTimePeriod;

        this.usersID = usersID;
        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");
        this.mEventDatabase = FirebaseDatabase.getInstance().getReference("events");
        this.timeLength = timeLength;
    }

    public CompletableFuture<List<List<String>>> findEventsIDList() {
        List<CompletableFuture<List<String>>> completableList = new ArrayList<>();

        for(int i=0; i< usersID.size(); i++) {
            CompletableFuture<List<String>> eventCompletable= dateHelper(usersID.get(i));
            completableList.add(eventCompletable);
        }

        return AsyncUtils.sequenceFuture(completableList);
    }


    public CompletableFuture<List<Tuple<String, String>>> getEvents(List<String> eventsID) {
        List<CompletableFuture<Tuple<String, String>>> completableList = new ArrayList<>();

        for(int i=0; i< eventsID.size(); i++) {
            CompletableFuture<Tuple<String, String>> eventCompletable= getStartAndEndDay(eventsID.get(i));
            completableList.add(eventCompletable);
        }

        return AsyncUtils.sequenceFuture(completableList);
    }


    public Boolean rightDate() {
        boolean result = endTimePeriod.after(startTimePeriod);
        return endTimePeriod.after(startTimePeriod);
    }




    public CompletableFuture<List<String>> dateHelper(String id) {
        final CompletableFuture<List<String>>  completableFuture = new CompletableFuture<>();

        mDatabase.child(usersID.get(0)).child("eventsID").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<String> eventsID = new ArrayList<>();

                for(DataSnapshot userDataSnapshot: dataSnapshot.getChildren()) {
                    String id = userDataSnapshot.getKey();
                    eventsID.add(id);
                }
                completableFuture.complete(eventsID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                completableFuture.cancel(true);
            }

        });
        return completableFuture;
    }




    public CompletableFuture<Tuple<String, String>> getStartAndEndDay(String eventID) {

        final CompletableFuture<Tuple<String, String>>  completableFuture = new CompletableFuture<>();

        mEventDatabase.child(eventID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String startDate = dataSnapshot.child("startDate").getValue(String.class);
                String endDate = dataSnapshot.child("endDate").getValue(String.class);

                Tuple<String, String> eventDays = new Tuple<>(startDate, endDate);
                completableFuture.complete(eventDays);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
        return completableFuture;
    }


    public List<Date> magigFunction(Tuple<String, String> event, List<Date> freetime) {
        List<Date> timeperiod = freetime;

        Timestamp startTime = new Timestamp(Long.parseLong(event.x));
        Timestamp endTime = new Timestamp(Long.parseLong(event.y));

        List<Date> result = new ArrayList<Date>();

        for(int i=0; i<timeperiod.size(); i++) {

            Timestamp startPeriod = timeperiod.get(i).getStartTime();
            Timestamp endPeriod = timeperiod.get(i).getEndTime();

            Boolean test1 = endTime.before(startPeriod);
            Boolean test2 = endPeriod.before(startTime);

            if(endTime.before(startPeriod) || endPeriod.before(startTime) || endPeriod.equals(startTime) || endTime.equals(startPeriod)) {
                result.add(timeperiod.get(i));
            } else {
                if(startPeriod.after(startTime) && startPeriod.before(endTime)) {
                    result.add(new Date(startPeriod, endTime));
                } else {
                    if(startTime.before(startPeriod) && endPeriod.before(endTime)) {
                        // Fer ekkert á listann
                    } else {
                        if (endPeriod.before(endTime)) {
                            result.add(new Date(startPeriod, startTime));
                        } else {
                            result.add(new Date(startPeriod, startTime));
                            result.add(new Date(endTime, endPeriod));
                        }
                    }
                }
            }
        }
        freetime = result;
        return freetime;
    }
}
