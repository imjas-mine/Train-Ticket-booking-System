package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;

import java.io.File;
import java.util.List;

public class UserBookingService {
    private static final String USERS_PATH = "../localdb/users.json";
    private User user;
    private ObjectMapper mapper = new ObjectMapper();
    private List<User> users;

    public UserBookingService(User user) throws Exception {
        this.user = user;
        File file = new File(USERS_PATH);
        users = mapper.readValue(file, new TypeReference<List<User>>() {
        });
    }
}
