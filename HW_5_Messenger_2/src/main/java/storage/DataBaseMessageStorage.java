package storage;

import model.Message;
import storage.api.IMessageStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseMessageStorage implements IMessageStorage {
    private static DataBaseMessageStorage instance=new DataBaseMessageStorage();

    public static DataBaseMessageStorage getInstance() {
        return instance;
    }

    @Override
    public void putMessage(Message message) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/messanger",
                "postgres", "6780911");){
            try(PreparedStatement preparedStatement =
                        connection.prepareStatement("INSERT INTO application.messages (recipient, sender, message, time) " +
                    "VALUES ((SELECT id FROM application.users WHERE login = ?), " +
                    "(SELECT id FROM application.users WHERE login = ?),?,?)")){
                preparedStatement.setString(1, message.getRecipient());
                preparedStatement.setString(2, message.getSender());
                preparedStatement.setString(3, message.getText());
                preparedStatement.setString(4, message.getTime());
                preparedStatement.executeUpdate();
            }
        }catch (SQLException e){

            throw new IllegalStateException("Ошибка работы с базой данных");
        }

    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> allMessages = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/messanger",
                "postgres", "6780911")){
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement("SELECT recipient.login AS recipient_login, " +
                                 "sender.login AS sender_login, message, time " +
                    "FROM application.messages " +
                    "JOIN application.users AS recipient ON messages.recipient =recipient.id " +
                    "JOIN application.users AS sender ON messages.sender = sender.id;")){
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Message message = new Message();
                    message.setRecipient(resultSet.getString(1));
                    message.setSender(resultSet.getString(2));
                    message.setText(resultSet.getString(3));
                    message.setTime(resultSet.getString(4));
                    allMessages.add(message);
                }
            }
        }catch (SQLException e){
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
        return allMessages;
    }

}

