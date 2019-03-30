package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import is.hi.hbv601g.jollytime.FirebaseServices.Authentication;

public class CalendarActivity extends AppCompatActivity {

    private Button mSignOutButton;
    private Authentication authenticationService;

    private String getDayOfWeek(int value) {
        String day = "";
        switch(value){
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

    private String getMonth(int value) {
        String monthName = "";
        switch(value){
            case 0:
                monthName = "January";
                break;
            case 1:
                monthName = "February";
                break;
            case 2:
                monthName = "March";
                break;
            case 3:
                monthName = "April";
                break;
            case 4:
                monthName = "May";
                break;
            case 5:
                monthName = "June";
                break;
            case 6:
                monthName = "July";
                break;
            case 7:
                monthName = "August";
                break;
            case 8:
                monthName = "September";
                break;
            case 9:
                monthName = "October";
                break;
            case 10:
                monthName = "November";
                break;
            case 11:
                monthName = "December";
                break;
        }
        return monthName;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        authenticationService = new Authentication();

        mSignOutButton = (Button) findViewById(R.id.sign_out_button);

        CalendarView calendarView = findViewById(R.id.calendarView);
        Calendar cal = Calendar.getInstance();
        Button goToDay_button = findViewById(R.id.go_to_date_button);
        final TextView yearText = findViewById(R.id.year_textView);
        final TextView dateText = findViewById(R.id.date_textView);
        Button addevents_button = findViewById(R.id.addevents_button);

        final String year = String.valueOf(cal.get(Calendar.YEAR));
        String dayOfWeek;
        String month;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        dayOfWeek = getDayOfWeek(cal.get(Calendar.DAY_OF_WEEK));
        month = getMonth(cal.get(Calendar.MONTH));

        final String selectedDate = String.format("%s, %s %s", dayOfWeek.substring(0, 3), month.substring(0, 3), String.valueOf(dayOfMonth));

        yearText.setText(String.valueOf(year));
        dateText.setText(selectedDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@android.support.annotation.NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                String monthName = getMonth(month);
                String dayOfWeek = getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));

                String date = String.format("%s, %s %s", dayOfWeek.substring(0, 3), monthName.substring(0, 3), String.valueOf(dayOfMonth));
                String yearString = Integer.toString(year);

                yearText.setText(yearString);
                dateText.setText(date);
            }
        });

        goToDay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, DayActivity.class);
                //intent.putExtra("date", selectedDate);
                //intent.putExtra("year", year);
                startActivity(intent);
            }
        });

        addevents_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, CreateEventActivity.class);
                intent.putExtra("date", selectedDate);
                intent.putExtra("year", year);
                startActivity(intent);
            }
        });

        mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticationService.signOut();
                finish();
            }
        });


    }
}
