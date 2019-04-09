package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import is.hi.hbv601g.jollytime.FirebaseServices.EventDatabaseService;
import is.hi.hbv601g.jollytime.Models.Event;

public class DayActivity extends AppCompatActivity implements EventDatabaseService.EventDatabaseServiceDelegate {

    private EventDatabaseService eventDatabaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        TextView dateTextView = findViewById(R.id.date_DayTextView);
        TextView yearTextView = findViewById(R.id.year_dayTextView);
        ImageButton backToCalendarButton = findViewById(R.id.backToCalendarButton);

        String date = getIntent().getStringExtra("date");
        String year = getIntent().getStringExtra("year");

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
        TextView dateTextView = findViewById(R.id.date_DayTextView);
        TextView yearTextView = findViewById(R.id.year_dayTextView);
        // ...
        return false;
    }

    @Override
    public void updateEvent(Event event) {
        if (isEventOnDate(event)) {
            // TODO: implement
        }
    }
}
