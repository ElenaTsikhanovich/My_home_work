package storage;
import model.User;
import storage.api.IUserStorage;

import java.util.HashMap;
import java.util.Map;


public class UserStorage implements IUserStorage {
    private static UserStorage instance = new UserStorage();

    private Map<String, User> allUsers;

    private UserStorage() {
        this.allUsers = new HashMap<>();
    }

    public static UserStorage getInstance() {
        return instance;
    }

    @Override
    public void putUser(User user) {
        allUsers.put(user.getLogin(),user);
    }

    @Override
    public Map<String, User> getAllUsers() {
        return allUsers;
    }
}

