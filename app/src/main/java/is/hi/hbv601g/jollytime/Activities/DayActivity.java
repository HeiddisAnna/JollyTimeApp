package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import is.hi.hbv601g.jollytime.FirebaseServices.EventDatabaseService;
import is.hi.hbv601g.jollytime.Models.Date;
import is.hi.hbv601g.jollytime.Models.Event;

import static android.widget.TextView.*;

public class DayActivity extends AppCompatActivity implements EventDatabaseService.EventDatabaseServiceDelegate {

    private EventDatabaseService eventDatabaseService;
    private TextView dateTextView;
    TextView yearTextView;
    RelativeLayout mLayout;
    LinearLayout linearLayout;

    String date = null;
    String year = null;
    String selectedDateInMillis = null;
    private int eventIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        this.date = getIntent().getStringExtra("date");
        this.year = getIntent().getStringExtra("year");
        this.selectedDateInMillis = getIntent().getStringExtra("selectedDateInMillis");

        mLayout = (RelativeLayout)findViewById(R.id.left_event_column);
        eventIndex = mLayout.getChildCount();

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

    public boolean isEventOnDate(Event event) {
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
        int monthCurrent = currentCal.get(Calendar.MONTH)-1;
        int dayCurrent = currentCal.get(Calendar.DAY_OF_MONTH)-1;

        int yearEvent = eventStartcal.get(Calendar.YEAR);
        int monthEvent = eventStartcal.get(Calendar.MONTH);
        int dayEvent = eventStartcal.get(Calendar.DAY_OF_MONTH);


        if (yearCurrent == yearEvent && monthCurrent == monthEvent && dayCurrent == dayEvent) {
            sameDay = true;
        }

        return sameDay;
    }

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

    private void createEventView(int topMargin, int height, String message){
        TextView mEventView = new TextView(DayActivity.this);
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = topMargin * 2;
        lParam.leftMargin = 24;
        mEventView.setLayoutParams(lParam);
        mEventView.setPadding(24, 0, 24, 0);
        mEventView.setHeight(height * 2);
        mEventView.setGravity(0x11);
        mEventView.setTextColor(Color.parseColor("#ffffff"));
        mEventView.setText(message);
        mEventView.setBackgroundColor(Color.parseColor("#ffd149"));
        mLayout.addView(mEventView, eventIndex - 1);
    }

    @Override
    public void updateEvent(Event event) {
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();

        calStart.setTimeInMillis(Long.parseLong(event.getStartDate()));
        calEnd.setTimeInMillis(Long.parseLong(event.getEndDate()));

        // Get year, month and day of month to compare dates
        int yearStart = calStart.get(Calendar.YEAR);
        int monthStart = calStart.get(Calendar.MONTH);
        int dayStart = calStart.get(Calendar.DAY_OF_MONTH);
        int hourStart = calStart.get(Calendar.HOUR);
        int minStart = calStart.get(Calendar.MINUTE);

        int yearEnd = calEnd.get(Calendar.YEAR);
        int monthEnd = calEnd.get(Calendar.MONTH);
        int dayEnd = calEnd.get(Calendar.DAY_OF_MONTH);
        int hourEnd = calEnd.get(Calendar.HOUR);
        int minEnd = calEnd.get(Calendar.MINUTE);

        if (isEventOnDate(event)) {
            // TODO: implement
            String titleEvent = event.getTitle();
            String startDate = event.getStartDate();
            String endDate = event.getEndDate();
            int timeframe = getTimeFrame(startDate, endDate);

            createEventView(timeframe, timeframe, titleEvent);


        }

    }
}
