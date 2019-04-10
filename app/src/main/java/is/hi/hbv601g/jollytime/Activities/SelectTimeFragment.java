package is.hi.hbv601g.jollytime.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private int hh;
    private int min;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        populateSetDate(hourOfDay, minute);
    }

    public void populateSetDate(int hh, int min) {
        this.hh = hh;
        this.min = min;
    }

    public String getTime(){
        return hh + ":" + min;
    }

    public int getHour(){
        return hh;
    }

    public int getMin(){
        return min;
    }



}