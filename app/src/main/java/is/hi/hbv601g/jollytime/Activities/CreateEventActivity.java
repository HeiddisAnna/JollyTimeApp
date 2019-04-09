package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.GregorianCalendar;

import is.hi.hbv601g.jollytime.FirebaseServices.EventDatabaseService;
import is.hi.hbv601g.jollytime.Models.Event;
import is.hi.hbv601g.jollytime.Services.CreateEventService;

public class CreateEventActivity extends AppCompatActivity implements EventDatabaseService.EventDatabaseServiceDelegate {

    private EditText mTitle;
    private EditText mDescription;
    private Button mCreateEventButton;
    private DatePicker mStertDate;
    private DatePicker mEndDate;
    private TimePicker mStertTime;
    private TimePicker mEndTime;
    private CreateEventService createEventService;
    private EventDatabaseService eventDatabaseService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Allar breytur hér
        mCreateEventButton = (Button) findViewById(R.id.create_event_button);
        mTitle = (EditText) findViewById(R.id.title_input);
        mDescription = (EditText) findViewById(R.id.description_input);

        eventDatabaseService = new EventDatabaseService(this);

        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        // Allar breytur hér


        mCreateEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePicker mStartDate = (DatePicker)findViewById(R.id.startDatePicker);
                DatePicker mEndDate = (DatePicker)findViewById(R.id.endDatePicker);
                TimePicker mStartTime = (TimePicker)findViewById(R.id.startTimePicker);
                TimePicker mEndTime = (TimePicker)findViewById(R.id.endTimePicker);

                String title = mTitle.getText().toString();
                String description = mDescription.getText().toString();

                int startYear = mStartDate.getYear();
                int startMonth = mStartDate.getMonth();
                int startDay = mStartDate.getDayOfMonth();
                int startHour = mStartTime.getHour();
                int startMin = mStartTime.getMinute();

                int endYear = mEndDate.getYear();
                int endMonth = mEndDate.getMonth();
                int endDay = mEndDate.getDayOfMonth();
                int endHour = mEndTime.getHour();
                int endMin = mEndTime.getMinute();

                String meStartDate =  "" + startYear + "-" + startMonth + "-" + startDay + ' ' + startHour + ":" + startMin + "" ;
                String meEndDate = "" + endYear + "-" + endMonth + "-" + endDay + ' ' + endHour + ":" + endMin + "" ;

                createEventService = new CreateEventService(startYear, startMonth, startDay,
                        startHour, startMin, endYear, endMonth, endDay, endHour, endMin);


                if(createEventService.rightDate()) {
                    eventDatabaseService.saveNewEvent(title, description, meStartDate, meEndDate);
                    Intent intent = new Intent(CreateEventActivity.this, CalendarActivity.class);
                    startActivity(intent);
                } else {
                    startAfterEnd();
                }
            }
        });
    }
    public void startAfterEnd() {
        Toast.makeText(CreateEventActivity.this, "Start date must be before end date.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateEvent(Event event) {
        // Ignore this dummy empty implementation.
        // Needed to implement EventDatabaseService.EventDatabaseServiceDelegate interface
    }
}
