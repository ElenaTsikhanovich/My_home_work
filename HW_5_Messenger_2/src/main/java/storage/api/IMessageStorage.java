package storage.api;

import model.Message;

import java.util.List;

public interface IMessageStorage {
    void putMessage(Message message);
    List<Message> getAllMessages();
}
