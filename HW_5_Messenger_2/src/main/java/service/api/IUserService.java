package service.api;

import model.User;
import storage.UserStorage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {
    User addUser(HttpServletRequest request);
    User getUser(HttpServletRequest request);
    List<User> allUsers();
    void storageChoose(String storage);

}
