package service;

import model.User;
import service.api.IUserService;
import storage.FileUserStorage;
import storage.StorageType;
import storage.UserStorage;
import storage.UserStorageFactory;
import storage.api.IUserStorage;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UserService implements IUserService {
    private static String PARAM_NAME_LOGIN="login";
    private static String PARAM_NAME_PASSWORD="password";
    private static String PARAM_NAME_FIO="fio";
    private static String PARAM_NAME_BIRTH="birth";


    private static UserService instance =new UserService();
    private IUserStorage userStorage;

    private UserService() {
       this.userStorage=UserStorageFactory.getInstance();
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
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss"));
            User user = new User(login, password, fio, birth,date);
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

    @Override
    public List<User> allUsers() {
        List<User> allUsers=new ArrayList<>();
        Map<String, User> users = this.userStorage.getAllUsers();
        for(Map.Entry<String,User> item:users.entrySet()){
            allUsers.add(item.getValue());
        }
        return allUsers;

    }

    public boolean loginExist(String param){
        boolean isExist = userStorage.getAllUsers().containsKey(param);
        return isExist;
    }


    public static UserService getInstance() {
        return instance;
    }

}
