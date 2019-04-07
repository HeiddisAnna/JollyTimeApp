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

import java.util.Calendar;

import is.hi.hbv601g.jollytime.Decorators.CurrentDayDecorator;
import is.hi.hbv601g.jollytime.Decorators.EventDecorator;


public class CalendarFragment extends Fragment {

    Button goToDay_button;
    Button addEvents_button;

    public CalendarFragment() {
        // Required empty public constructor
    }

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        // Calendars
        MaterialCalendarView calendarView;
        calendarView = v.findViewById(R.id.calendarView);
        Calendar cal = Calendar.getInstance();

        // Decorators
        calendarView.addDecorators(new EventDecorator((CalendarActivity) getActivity()));
        calendarView.addDecorator(new CurrentDayDecorator((CalendarActivity) getActivity()));

        // Layouts from xml
        goToDay_button = v.findViewById(R.id.go_to_date_button);
        goToDay_button.setVisibility(Button.VISIBLE);
        final TextView yearText = v.findViewById(R.id.year_textView);
        final TextView dateText = v.findViewById(R.id.date_textView);
        addEvents_button = v.findViewById(R.id.addevents_button);
        addEvents_button.setVisibility(Button.VISIBLE);

        // get date from cal
        final String year = String.valueOf(cal.get(Calendar.YEAR));
        String dayOfWeek;
        String month;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        dayOfWeek = getDayOfWeek(cal.get(Calendar.DAY_OF_WEEK));
        month = getMonth(cal.get(Calendar.MONTH));

        // Initialize material calendarView
        calendarView.state().edit()
                .setFirstDayOfWeek(DayOfWeek.of(Calendar.MONDAY))
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2200, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        final String selectedDate = String.format("%s, %s %s", dayOfWeek.substring(0, 3), month.substring(0, 3), String.valueOf(dayOfMonth));

        yearText.setText(year);
        dateText.setText(selectedDate);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
                Calendar calendar = Calendar.getInstance();

                int year = calendarDay.getYear();
                int month = calendarDay.getMonth();
                int dayOfMonth = calendarDay.getDay();

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
                Intent intent = new Intent(getActivity(), DayActivity.class);
                intent.putExtra("date", selectedDate);
                intent.putExtra("year", year);
                startActivity(intent);
            }
        });

        addEvents_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateEventActivity.class);
                intent.putExtra("date", selectedDate);
                intent.putExtra("year", year);
                startActivity(intent);
            }
        });

        return v;
    }

}
