package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getPassword());
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
        user.printTickets();
    }

//    public List<Train> getTrains(String source, String destination) throws IOException {
//        TrainService trainService = new TrainService();
//
//    }
}
