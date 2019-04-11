package is.hi.hbv601g.jollytime.Models;

import java.sql.Timestamp;
import java.util.List;


public class Tuple<X, Y> {
    public final X x;
    public final Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}
