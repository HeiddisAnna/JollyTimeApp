package is.hi.hbv601g.jollytime.Decorators;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;


import java.util.Calendar;

import is.hi.hbv601g.jollytime.Activities.CalendarActivity;
import is.hi.hbv601g.jollytime.Activities.R;

public class CurrentDayDecorator implements DayViewDecorator {

    private Drawable drawable;

    Calendar calendar = Calendar.getInstance();

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH)+1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    CalendarDay currentDay = (CalendarDay) CalendarDay.from(year, month, day);

    public CurrentDayDecorator(CalendarActivity calendarActivity) {
        drawable = ContextCompat.getDrawable(calendarActivity, R.drawable.first_day_month);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(currentDay);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
