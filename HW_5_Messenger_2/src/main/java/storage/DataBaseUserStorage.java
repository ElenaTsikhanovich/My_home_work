package storage;

import model.User;
import storage.api.IUserStorage;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseUserStorage implements IUserStorage {
    private static DataBaseUserStorage instance = new DataBaseUserStorage();

    public static DataBaseUserStorage getInstance() {
        return instance;
    }


    @Override
    public void putUser(User user) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/messanger",
                    "postgres", "6780911");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO application.users " +
                    "(login, password, name, birth, registration) VALUES (?,?,?,?,?)");

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFio());
            preparedStatement.setString(4, user.getBirth());
            preparedStatement.setString(5, user.getDateOfRegistration());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, User> getAllUsers() {
        Map<String, User> allUsers = new HashMap<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/messanger", "postgres", "6780911");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT login, password, name, birth, registration FROM application.users");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setFio(resultSet.getString(3));
                user.setBirth(resultSet.getString(4));
                user.setDateOfRegistration(resultSet.getString(5));
                allUsers.put(user.getLogin(), user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
}
