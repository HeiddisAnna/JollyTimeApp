package is.hi.hbv601g.jollytime.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class CreateEventActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mStartDate;
    private EditText mEndDate;
    private EditText mDescription;
    private Button mCreateEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Allar breytur hér
        mCreateEventButton = (Button) findViewById(R.id.create_event_button);
        mTitle = (EditText) findViewById(R.id.title_input);
        mDescription = (EditText) findViewById(R.id.description_input);
        DatePicker mStartDate = (DatePicker)findViewById(R.id.starTimePicker);
        DatePicker mEndDate = (DatePicker)findViewById(R.id.endDatePicker);

        String date = getIntent().getStringExtra("date");
        String year = getIntent().getStringExtra("year");

        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        // Allar breytur hér


    }
}
