package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import is.hi.hbv601g.jollytime.Decorators.CurrentDayDecorator;
import is.hi.hbv601g.jollytime.Decorators.EventDecorator;
import is.hi.hbv601g.jollytime.FirebaseServices.EventDatabaseService;
import is.hi.hbv601g.jollytime.Models.Event;


public class CalendarFragment extends Fragment implements EventDatabaseService.EventDatabaseServiceDelegate {

    Button goToDay_button;
    Button addEvents_button;

    EventDatabaseService eventDatabaseService;

    public CalendarFragment() {
        // Required empty public constructor
    }

    private String getDayOfWeek(int value) {
        String day = "";
        switch(value){
            case 3:
                day = "Sunday";
                break;
            case 4:
                day = "Monday";
                break;
            case 5:
                day = "Tuesday";
                break;
            case 6:
                day = "Wednesday";
                break;
            case 7:
                day = "Thursday";
                break;
            case 1:
                day = "Friday";
                break;
            case 2:
                day = "Saturday";
                break;
        }
        return day;
    }

    private String getMonth(int value) {
        String monthName = "";
        switch(value){
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        return monthName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    MaterialCalendarView calendarView;
    Calendar calendar = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        // Calendars
        calendarView = v.findViewById(R.id.calendarView);

        // Decorators
        //calendarView.addDecorators(new EventDecorator( getActivity()));
        calendarView.addDecorator(new CurrentDayDecorator((CalendarActivity) getActivity()));

        // Layouts from xml
        final TextView yearText = v.findViewById(R.id.year_textView);
        final TextView dateText = v.findViewById(R.id.date_textView);
        addEvents_button = v.findViewById(R.id.addevents_button);

        int year = calendar.get(Calendar.YEAR);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String dayOfWeek;
        String monthText;
        final String yearString = String.valueOf(year);
        dayOfWeek = getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
        monthText = getMonth(calendar.get(Calendar.MONTH));

        // Initialize material calendarView
        calendarView.state().edit()
                .setFirstDayOfWeek(DayOfWeek.of(Calendar.MONDAY))
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2200, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        final String selectedDate = String.format("%s, %s %s", dayOfWeek.substring(0, 3), monthText.substring(0, 3), String.valueOf(dayOfMonth));

        yearText.setText(yearString);
        dateText.setText(selectedDate);


        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {

                int yearSelected = calendarDay.getYear();
                int monthSelected = calendarDay.getMonth();
                int dayOfMonthSelected = calendarDay.getDay();

                calendar.set(yearSelected, monthSelected, dayOfMonthSelected);

                String monthName = getMonth(monthSelected);
                String dayOfWeek = getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));


                final String date = String.format("%s, %s %s", dayOfWeek.substring(0, 3), monthName.substring(0, 3), String.valueOf(dayOfMonthSelected));
                final String yearString = Integer.toString(yearSelected);

                String selectedDateInMillis = String.valueOf(calendar.getTimeInMillis());

                yearText.setText(yearString);
                dateText.setText(date);

                Intent intent = new Intent(getActivity(), DayActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("year", yearString);
                intent.putExtra("selectedDateInMillis", selectedDateInMillis);
                startActivity(intent);

            }
        });

        addEvents_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateEventActivity.class);
                intent.putExtra("date", selectedDate);
                intent.putExtra("year", yearString);
                startActivity(intent);
            }
        });

        this.eventDatabaseService = new EventDatabaseService(this);
        this.eventDatabaseService.getUserEvents();

        return v;
    }

    @Override
    public void updateEvent(Event event) {
        // TODO: Implement
        String startDate = event.getStartDate();

    }
}
