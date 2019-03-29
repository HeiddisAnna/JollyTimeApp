package is.hi.hbv601g.jollytime.PojoModels;

import java.util.GregorianCalendar;
import java.util.List;

public class DatePojo {
    public long id;
    private String title;
    private String description;
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;

    private List<String> userIds;

    public DatePojo() {}

    public DatePojo(String title, String description, GregorianCalendar startTime, GregorianCalendar endTime, List<String> userIds) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userIds = userIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }

    public void setEndTime(GregorianCalendar endTime) {
        this.endTime = endTime;
    }
}
