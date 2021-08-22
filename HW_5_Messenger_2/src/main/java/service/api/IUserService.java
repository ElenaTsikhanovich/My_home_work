package service.api;

import model.User;
import storage.UserStorage;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    User addUser(HttpServletRequest request);
    User getUser(HttpServletRequest request);
}
