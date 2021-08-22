package storage;

import model.Message;
import storage.api.IMessageStorage;

import java.util.ArrayList;
import java.util.List;

public class MessageStorage implements IMessageStorage {
    private static MessageStorage instance=new MessageStorage();
    private List<Message> allMessages;

    private MessageStorage() {
        this.allMessages = new ArrayList<>();
    }

    public static MessageStorage getInstance() {
        return instance;
    }

    @Override
    public void putMessage(Message message) {
        allMessages.add(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return allMessages;
    }
}
