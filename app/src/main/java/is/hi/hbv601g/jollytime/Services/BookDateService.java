package is.hi.hbv601g.jollytime.Services;

import java.util.GregorianCalendar;
import java.util.List;

public class BookDateService {

    private GregorianCalendar startTimePeriod;
    private GregorianCalendar endTimePeriod;
    private List<String> usersID;


    public BookDateService(GregorianCalendar startTimePeriod, GregorianCalendar endTimePeriod, List<String> usersID) {
        this.startTimePeriod = startTimePeriod;
        this.endTimePeriod = endTimePeriod;
        this.usersID = usersID;
    }



}
