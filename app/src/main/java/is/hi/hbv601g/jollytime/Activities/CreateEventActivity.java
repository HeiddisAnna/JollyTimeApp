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

import is.hi.hbv601g.jollytime.Services.CreateEventService;

public class CreateEventActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mDescription;
    private Button mCreateEventButton;
    private DatePicker mStertDate;
    private DatePicker mEndDate;
    private TimePicker mStertTime;
    private TimePicker mEndTime;
    /* private int startYear;
    private int startMonth;
    private int startDay;
    private int startHour;
    private int startMin;
    private int endYear;
    private int endMonth;
    private int endDay;
    private int endHour;
    private int endMin;
    */
    private CreateEventService createEventService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Allar breytur hér
        mCreateEventButton = (Button) findViewById(R.id.create_event_button);
        mTitle = (EditText) findViewById(R.id.title_input);
        mDescription = (EditText) findViewById(R.id.description_input);



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

                createEventService = new CreateEventService(startYear, startMonth, startDay,
                        startHour, startMin, endYear, endMonth, endDay, endHour, endMin);

                if(createEventService.rightDate()) {
                    boolean a = createEventService.insertEvent();
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
}
