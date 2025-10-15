package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {

    private static final String TRAIN_DB_PATH = "app/src/main/java/ticket/booking/localdb/trains.json";
    private Train train;
    private ObjectMapper mapper = new ObjectMapper();
    private List<Train> trainList;

    public TrainService() throws IOException {
        File file = new File(TRAIN_DB_PATH);
        trainList = mapper.readValue(file, new TypeReference<List<Train>>() {
        });
    }

    public List<Train> searchTrains(String source, String destination) {

        return trainList.stream().filter(train -> validTrain(train, source, destination)).collect(Collectors.toList());
    }

    private Boolean validTrain(Train train, String source, String destination) {
        List<String> stationOrder = train.getStations();
        int sourceIndex = stationOrder.indexOf(source.toLowerCase());
        int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex >= 0 && destinationIndex >= 0 && sourceIndex < destinationIndex;
    }
}
