package storage;
import model.User;
import java.util.HashMap;
import java.util.Map;


public class UserStorage {
    private static UserStorage instance = new UserStorage();

    private Map<String, User> allUsers;

    private UserStorage() {
        this.allUsers = new HashMap<>();
    }


    public static UserStorage getInstance() {
        return instance;
    }

    public Map<String, User> getAllUsers() {
        return allUsers;
    }
}