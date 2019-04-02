package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import android.widget.TextView;
import is.hi.hbv601g.jollytime.FirebaseServices.AuthenticationService;

import org.threeten.bp.DayOfWeek;

import java.util.ArrayList;

import is.hi.hbv601g.jollytime.Decorators.CurrentDayDecorator;
import is.hi.hbv601g.jollytime.Decorators.EventDecorator;
import is.hi.hbv601g.jollytime.Models.User;

public class CalendarActivity extends AppCompatActivity {

    private Button mSignOutButton;
    private AuthenticationService authenticationService;

    // Ná í vikudag, streng
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

    // Ná í mánuð, streng
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

    private ArrayList<User> user;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        authenticationService = new AuthenticationService();

        mSignOutButton = (Button) findViewById(R.id.sign_out_button);
        Calendar cal = Calendar.getInstance();

        final Intent i = getIntent();
        //user = (ArrayList<User>) i.getSerializableExtra("USER");

        // Skilgreina buttons og views frá xml skrá

        MaterialCalendarView calendarView;
        calendarView = findViewById(R.id.calendarView);

        calendarView.addDecorators(new EventDecorator(this));
        calendarView.addDecorator(new CurrentDayDecorator(this));

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

        calendarView.state().edit()
                .setFirstDayOfWeek(DayOfWeek.of(Calendar.MONDAY))
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2200, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        //final String selectedDate = String.valueOf(calendarView.getSelectedDate());
        //final String year = "2019";

        final String selectedDate = String.format("%s, %s %s", dayOfWeek.substring(0, 3), month.substring(0, 3), String.valueOf(dayOfMonth));

        yearText.setText(year);
        dateText.setText(selectedDate);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {

                String date = String.valueOf(calendarDay.getDate());

                String yearString = String.valueOf(calendarDay.getYear());

                yearText.setText(yearString);
                dateText.setText(date);
            }
        });

        /*calendarView.setOnDateChangeListener( new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull MaterialCalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                String monthName = getMonth(month);
                String dayOfWeek = getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));

                String date = String.format("%s, %s %s", dayOfWeek.substring(0, 3), monthName.substring(0, 3), String.valueOf(dayOfMonth));
                String yearString = Integer.toString(year);

                yearText.setText(yearString);
                dateText.setText(date);
            }
        });*/

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
