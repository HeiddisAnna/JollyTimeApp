package is.hi.hbv601g.jollytime.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateEventActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mStartDate;
    private EditText mStartTime;
    private EditText mEndDate;
    private EditText mEndTime;
    private EditText mDescription;
    private Button mCreateEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Allar breytur hér
        mCreateEventButton = (Button) findViewById(R.id.create_event_button);
        mTitle = (EditText) findViewById(R.id.title_input);
        mStartDate = (EditText) findViewById(R.id.start_date_input);
        mStartTime = (EditText) findViewById(R.id.start_time_input);
        mEndDate = (EditText) findViewById(R.id.end_date_input);
        mEndTime = (EditText) findViewById(R.id.end_time_input);
        mDescription = (EditText) findViewById(R.id.description_input);

        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        // Allar breytur hér
    }
}
