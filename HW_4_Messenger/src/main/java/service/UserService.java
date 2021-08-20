package service;

import model.User;
import storage.UserStorage;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UserService {
    private static String PARAM_NAME_LOGIN="login";
    private static String PARAM_NAME_PASSWORD="password";
    private static String PARAM_NAME_FIO="fio";
    private static String PARAM_NAME_BIRTH="birth";

    private static UserService instance =new UserService();
    private UserStorage userStorage;


    private UserService() {
        this.userStorage = UserStorage.getInstance();

    }


    public User addUser(HttpServletRequest request){
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String fio = request.getParameter(PARAM_NAME_FIO);
        String birth = request.getParameter(PARAM_NAME_BIRTH);
        boolean b =loginExist(login);
        if(b){
            return null;
        }else{
            User user = new User(login, password, fio, birth);
            this.userStorage.getAllUsers().put(login,user);
            return user;
        }
    }

    public User getUser(HttpServletRequest request){
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
