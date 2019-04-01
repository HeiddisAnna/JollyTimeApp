package is.hi.hbv601g.jollytime.Decorators;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;


import is.hi.hbv601g.jollytime.Activities.CalendarActivity;
import is.hi.hbv601g.jollytime.Activities.R;

public class CurrentDayDecorator implements DayViewDecorator {

    private Drawable drawable;

    CalendarDay currentDay = (CalendarDay) CalendarDay.from(2019, 4, 1);

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
