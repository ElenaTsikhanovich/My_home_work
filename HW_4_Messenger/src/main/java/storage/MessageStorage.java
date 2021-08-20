package storage;

import model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageStorage {
    private static MessageStorage instance=new MessageStorage();
    private List<Message> allMessages;

    private MessageStorage() {
        this.allMessages = new ArrayList<>();
    }

    public static MessageStorage getInstance() {
        return instance;
    }

    public List<Message> getAllMessages() {
        return allMessages;
    }


}
