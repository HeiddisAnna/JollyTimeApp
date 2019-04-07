package is.hi.hbv601g.jollytime.Decorators;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import is.hi.hbv601g.jollytime.Activities.CalendarActivity;
import is.hi.hbv601g.jollytime.FirebaseServices.EventDatabaseService;

public class EventDecorator implements DayViewDecorator {

    private EventDatabaseService eventDatabaseService;

    public EventDecorator(CalendarActivity calendarActivity) {

    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        return false;
    }

    @Override
    public void decorate(DayViewFacade dayViewFacade) {

    }
}
