package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localdb/users.json";
    private User user;
    private ObjectMapper mapper = new ObjectMapper();
    private List<User> userList;

    public UserBookingService(User user) throws Exception {
        this.user = user;
        loadUsers();
    }

    public UserBookingService() throws Exception {
        loadUsers();
    }

    public List<User> loadUsers() throws Exception {
        File file = new File(USERS_PATH);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        userList = mapper.readValue(file, new TypeReference<List<User>>() {
        });
        return userList;
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user) {
        try {
            userList.add(user);
            saveUserListToFile();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        mapper.writeValue(usersFile, userList);
    }

    public void fetchBooking() {
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        Optional<User> userFetched = userList.stream().filter(user1 -> user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user1.getPassword(), user.getHashPassword())).findFirst();
        if (userFetched.isPresent()) {
            userFetched.get().printTickets();
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean bookSeat(Train train, int row, int seat) {
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seats.get(row).size() >= seat) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;

        }
    }

    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }
}
