package storage.api;

import model.User;

import java.util.Map;

public interface IUserStorage {
    void putUser(User user);
    Map<String, User> getAllUsers();
}
