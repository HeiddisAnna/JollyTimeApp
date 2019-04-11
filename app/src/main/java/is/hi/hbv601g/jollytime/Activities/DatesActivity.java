package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import is.hi.hbv601g.jollytime.FirebaseServices.EventDatabaseService;
import is.hi.hbv601g.jollytime.Models.Date;
import is.hi.hbv601g.jollytime.Models.Event;
import is.hi.hbv601g.jollytime.Models.ManyDates;
import is.hi.hbv601g.jollytime.Services.CreateEventService;

public class DatesActivity extends AppCompatActivity implements Serializable {

    private Button goToCalendar;
    private TextView availableDates;
    private List<Date> freeTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        String testText = getIntent().getExtras().getParcelable("dates");

        availableDates.setText(testText);


        goToCalendar = (Button) findViewById(R.id.goToCalendar);

        goToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DatesActivity.this, CalendarActivity.class);
                startActivity(intent);

            }
        });
    }

}
