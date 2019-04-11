package is.hi.hbv601g.jollytime.Decorators;

import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

import is.hi.hbv601g.jollytime.Activities.CalendarActivity;
import is.hi.hbv601g.jollytime.FirebaseServices.EventDatabaseService;

public class EventDecorator implements DayViewDecorator {

    private EventDatabaseService eventDatabaseService;

    private int color;
    private HashSet<CalendarDay> dates;

    public EventDecorator( Collection<CalendarDay> dates) {
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, Color.parseColor("#2980b9")));
    }
}
