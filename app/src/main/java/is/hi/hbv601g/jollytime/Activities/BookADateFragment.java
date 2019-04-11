package is.hi.hbv601g.jollytime.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import java.util.concurrent.CompletableFuture;

import is.hi.hbv601g.jollytime.Services.BookDateService;
import is.hi.hbv601g.jollytime.Activities.SelectDateFragment;
import is.hi.hbv601g.jollytime.Activities.SelectTimeFragment;


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
    TextView startDatePicker;
    TextView startTimePicker;
    TextView endDatePicker;
    TextView endTimePicker;
    DialogFragment startDateFragment;
    DialogFragment startTimeFragment;
    DialogFragment endDateFragment;
    DialogFragment endTimeFragment;


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

        startDatePicker = (TextView) v.findViewById(R.id.startDatePicker);
        startDateFragment = new SelectDateFragment();

        startTimePicker = (TextView) v.findViewById(R.id.startTimePicker);
        startTimeFragment = new SelectTimeFragment();

        endDatePicker = (TextView) v.findViewById(R.id.endDatePicker);
        endDateFragment = new SelectDateFragment();

        endTimePicker = (TextView) v.findViewById(R.id.endTimePicker);
        endTimeFragment = new SelectTimeFragment();



        // eventDatabaseService = new EventDatabaseService(this);

        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        // Allar breytur hér

        startDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDateFragment.show(getFragmentManager(), "startDatePicker");
            }
        });

        startTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimeFragment.show(getFragmentManager(), "startTimePicker");

            }
        });

        endDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDateFragment.show(getFragmentManager(), "endDatePicker");
            }
        });

        endTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTimeFragment.show(getFragmentManager(), "endTimePicker");
            }
        });


        mBookDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String startDate = ((SelectDateFragment) startDateFragment).getDate();
                String startTime = ((SelectTimeFragment) startTimeFragment).getTime();
                String endDate = ((SelectDateFragment) endDateFragment).getDate();
                String endTime = ((SelectTimeFragment) endTimeFragment).getTime();

                String title = mTitle.getText().toString();
                String description = mDescription.getText().toString();

                int startYear = ((SelectDateFragment) startDateFragment).getYear();
                int startMonth = ((SelectDateFragment) startDateFragment).getMonth();
                int startDay = ((SelectDateFragment) startDateFragment).getDay();
                int startHour = ((SelectTimeFragment) startTimeFragment).getHour();
                int startMin = ((SelectTimeFragment) startTimeFragment).getMin();

                int endYear = ((SelectDateFragment) endDateFragment).getYear();
                int endMonth = ((SelectDateFragment) endDateFragment).getMonth();
                int endDay = ((SelectDateFragment) endDateFragment).getDay();
                int endHour = ((SelectTimeFragment) endTimeFragment).getHour();
                int endMin = ((SelectTimeFragment) endTimeFragment).getMin();

                Timestamp startTimePeriod = new Timestamp(startYear, startMonth, startDay, startHour, startMin, 0 ,0);
                Timestamp endTimePeriod = new Timestamp(endYear, endMonth, endDay, endHour, endMin, 0, 0);


                List<String> usersID = new ArrayList<String>();
                usersID.add("1T09ujju4SU0GlfHgwgjD45vqpp1");
                usersID.add("BEdYO9QpOFhDRZz16DKbdFS9HWj1");

                BookDateService bookDateService = new BookDateService(startTimePeriod, endTimePeriod, usersID, 180);

                if(bookDateService.rightDate()) {
                    bookDateService.findCommonTimeperiod();
                    Intent intent = new Intent(getActivity(), DatesActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), CalendarActivity.class);
                    startActivity(intent);
                }
            }
        });

        return v;
    }
}

