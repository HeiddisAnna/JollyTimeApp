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

        import java.util.ArrayList;
        import java.util.GregorianCalendar;
        import java.util.List;

        import is.hi.hbv601g.jollytime.FirebaseServices.EventDatabaseService;
        import is.hi.hbv601g.jollytime.Services.BookDateService;

public class BookDateActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mDescription;
    private Button mBookDateButton;
    private DatePicker mStartDate;
    private DatePicker mEndDate;
    private TimePicker mStartTime;
    private TimePicker mEndTime;
    private BookDateService bookDateService;
   //  private BookDateDatabaseService bookDateDatabaseService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Allar breytur hér
        mBookDateButton = (Button) findViewById(R.id.book_date_button);
        mTitle = (EditText) findViewById(R.id.title_input);
        mDescription = (EditText) findViewById(R.id.description_input);

        // eventDatabaseService = new EventDatabaseService(this);

        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        // Allar breytur hér


        mBookDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStartDate = (DatePicker)findViewById(R.id.startDatePicker);
                mEndDate = (DatePicker)findViewById(R.id.endDatePicker);
                mStartTime = (TimePicker)findViewById(R.id.startTimePicker);
                mEndTime = (TimePicker)findViewById(R.id.endTimePicker);

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


                GregorianCalendar startTimePeriod = new GregorianCalendar(startYear, startMonth, startDay, startHour, startMin);
                GregorianCalendar endTimePeriod = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMin);

                String meStartDate =  "" + startYear + "-" + startMonth + "-" + startDay + ' ' + startHour + ":" + startMin + "" ;
                String meEndDate = "" + endYear + "-" + endMonth + "-" + endDay + ' ' + endHour + ":" + endMin + "" ;

                List<String> usersID = new ArrayList<String>();
                usersID.add("EAUUrzlCqFO5KbS8jP8dJnqAhVG2");
                usersID.add("aTd5KoHabeUZIyp3pcx9yNpqNTE3");

                bookDateService = new BookDateService(startTimePeriod, endTimePeriod, usersID);

            }
        });
    }
}

