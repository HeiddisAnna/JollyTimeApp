package is.hi.hbv601g.jollytime.Services;

public class CreateEventService {

    private int startYear;
    private int startMonth;
    private int startDay;
    private int startHour;
    private int startMin;
    private int endYear;
    private int endMonth;
    private int endDay;
    private int endHour;
    private int endMin;


    public CreateEventService(int startYear, int startMonth, int startDay, int startHour, int startMin,
                              int endYear, int endMonth, int endDay, int endHour, int endMin) {
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.endHour = endHour;
        this.endMin = endMin;
    }

    public Boolean rightDate() {
        if (startYear > endYear){ return false; }
        if (startYear < endYear){ return true; }
        if (startMonth > endMonth){ return false; }
        if (startMonth < endMonth){ return true; }
        if (startDay > endDay) { return false; }
        if (startDay < endDay) { return true; }
        if (startHour > endHour) { return false; }
        if (startHour < endHour) { return true; }
        if (startMin > endMin) { return false; }
        if (startMin < endMin) { return true; }
        return false;
    }


}
