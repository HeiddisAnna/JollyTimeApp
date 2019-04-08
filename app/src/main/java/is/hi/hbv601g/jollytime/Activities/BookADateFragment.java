package is.hi.hbv601g.jollytime.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import is.hi.hbv601g.jollytime.Services.BookDateService;
import is.hi.hbv601g.jollytime.Activities.SelectDateFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookADateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookADateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookADateFragment extends Fragment {
    private EditText mTitle;
    private EditText mDescription;
    Button mBookDateButton;
    TextView StartDatePicker;
    EditText startDate;
    DialogFragment startDateFragment;
    /* DatePicker mEndDate;
    TimePicker mStartTime;
    TimePicker mEndTime; */


    public BookADateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_book_adate, container, false);

        // Allar breytur hér
        mBookDateButton = (Button) v.findViewById(R.id.book_date_button);
        mTitle = (EditText) v.findViewById(R.id.title_input);
        mDescription = (EditText) v.findViewById(R.id.description_input);

        startDate = (EditText) v.findViewById(R.id.startDate);
        StartDatePicker = (TextView) v.findViewById(R.id.startDatePicker);
        startDateFragment = new SelectDateFragment();



        // eventDatabaseService = new EventDatabaseService(this);

        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        // Allar breytur hér

        StartDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDateFragment.show(getFragmentManager(), "StartDatePicker");

            }
        });


        mBookDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String test = ((SelectDateFragment) startDateFragment).getDate();

                String title = mTitle.getText().toString();
                String description = mDescription.getText().toString();

                /*

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

                */

                List<String> usersID = new ArrayList<String>();
                usersID.add("EAUUrzlCqFO5KbS8jP8dJnqAhVG2");
                usersID.add("aTd5KoHabeUZIyp3pcx9yNpqNTE3");

                // BookDateService bookDateService = new BookDateService(startTimePeriod, endTimePeriod, usersID);


                // Intent intent = new Intent(getActivity(), CalendarActivity.class);
                // startActivity(intent);

            }
        });

        return v;
    }

}

