package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import is.hi.hbv601g.jollytime.FirebaseServices.EventDatabaseService;
import is.hi.hbv601g.jollytime.Models.Date;
import is.hi.hbv601g.jollytime.Models.Event;

public class DayActivity extends AppCompatActivity implements EventDatabaseService.EventDatabaseServiceDelegate {

    private EventDatabaseService eventDatabaseService;
    TextView dateTextView;
    TextView yearTextView;

    String date = getIntent().getStringExtra("date");
    String year = getIntent().getStringExtra("year");
    String selectedDateInMillis = getIntent().getStringExtra("selectedDateInMillis");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        dateTextView = findViewById(R.id.date_DayTextView);
        yearTextView = findViewById(R.id.year_dayTextView);
        ImageButton backToCalendarButton = findViewById(R.id.backToCalendarButton);

        if(date!= null) {
            dateTextView.setText(date);
        }
        if(year!= null) {
            yearTextView.setText(year);
        }

        backToCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DayActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        this.eventDatabaseService = new EventDatabaseService(this);
        this.eventDatabaseService.getUserEvents();
    }

    /*public boolean isEventOnDate(Event event) {
        long selectedDate = Long.parseLong(selectedDateInMillis);

        long eventStartDate = Long.parseLong(event.getStartDate());

        boolean sameDay = false;

        // create new calendar and set the current date and event date
        Calendar currentCal = Calendar.getInstance();
        Calendar eventStartcal = Calendar.getInstance();

        currentCal.setTimeInMillis(selectedDate);
        eventStartcal.setTimeInMillis(eventStartDate);

        // Get year, month and day of month to compare dates
        int yearCurrent = currentCal.get(Calendar.YEAR);
        int monthCurrent = currentCal.get(Calendar.MONTH);
        int dayCurrent = currentCal.get(Calendar.DAY_OF_MONTH);

        int yearEvent = eventStartcal.get(Calendar.YEAR);
        int monthEvent = eventStartcal.get(Calendar.MONTH);
        int dayEvent = eventStartcal.get(Calendar.DAY_OF_MONTH);


        if (yearCurrent == yearEvent && monthCurrent == monthEvent && dayCurrent == dayEvent) {
            sameDay = true;
        } else {
            sameDay = false;
        }

        return sameDay;
    }*/

    public int getTimeFrame(String startDate, String endDate) {
        long startTimeLong = Long.parseLong(startDate);
        long endTimeLong = Long.parseLong(endDate);
        long timeDifference = endTimeLong - startTimeLong;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeDifference);
        int hours = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);

        int timeFrame = (hours * 60) + ((minutes * 60) / 100);

        return timeFrame;
    }

    @Override
    public void updateEvent(Event event) {
        TextView seven = findViewById(R.id.seven);
        //if (isEventOnDate(event)) {
            // TODO: implement
            String titleEvent = event.getTitle();
            String startDate = event.getStartDate();
            seven.setText(titleEvent + startDate);
        //}

    }
}
