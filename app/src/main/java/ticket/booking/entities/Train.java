package ticket.booking.entities;

import java.util.List;
import java.util.Map;

public class Train {

    private String trainID;
    private String trainNum;
    private List<List<Integer>> seats;
    private Map<String, String> stationTime;
    private List<String> stations;

    public Train(String trainID, String trainNum, List<List<Integer>> seats, Map<String, String> stationTime, List<String> stations) {
        this.trainID = trainID;
        this.trainNum = trainNum;
        this.seats = seats;
        this.stationTime = stationTime;
        this.stations = stations;
    }

    public Train() {
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public Map<String, String> getStationTime() {
        return stationTime;
    }

    public void setStationTime(Map<String, String> stationTime) {
        this.stationTime = stationTime;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public String getTrainInfo() {
        return String.format("Train ID: %s Train NUmber: %s", trainID, trainNum);
    }

}
