package ticket.booking.entities;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {

    private String trainID;
    private String trainNum;
    private  List<List<Boolean>>seats;
    private Map<String, Time> stationTime;
    private List<String> stations;
}
