package is.hi.hbv601g.jollytime.Models;

import java.sql.Timestamp;
import java.util.List;


public class ManyDates {

    public long id;

    private List<Date> dates;

    public ManyDates() {}

    public ManyDates(List<Date> dates) {
        this.dates = dates;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Date> getDates(){
        return this.dates;
    }

    public void setDates(List<Date> dates){
        this.dates = dates;
    }
}
