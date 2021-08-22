package service;

import model.User;
import service.api.IUserService;
import storage.FileUserStorage;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UserService implements IUserService {
    private static String PARAM_NAME_LOGIN="login";
    private static String PARAM_NAME_PASSWORD="password";
    private static String PARAM_NAME_FIO="fio";
    private static String PARAM_NAME_BIRTH="birth";

    private static UserService instance =new UserService();
    private FileUserStorage userStorage;


    private UserService() {
        this.userStorage = FileUserStorage.getInstance();

    }

    @Override
    public User addUser(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        boolean loginExist =loginExist(login);
        if(loginExist){
            return null;
        }else{
            String password = request.getParameter(PARAM_NAME_PASSWORD);
            String fio = request.getParameter(PARAM_NAME_FIO);
            String birth = request.getParameter(PARAM_NAME_BIRTH);
            User user = new User(login, password, fio, birth);
            this.userStorage.putUser(user);
            return user;
        }
    }

    @Override
    public User getUser(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        Map<String, User> allUsers = this.userStorage.getAllUsers();

        return allUsers.values().stream().filter(x->x.getLogin().equalsIgnoreCase(login)
                &&x.getPassword().equalsIgnoreCase(password)).findFirst().orElse(null);
    }


    public boolean loginExist(String param){
        boolean isExist = userStorage.getAllUsers().containsKey(param);
        return isExist;
    }


    public static UserService getInstance() {
        return instance;
    }
}
