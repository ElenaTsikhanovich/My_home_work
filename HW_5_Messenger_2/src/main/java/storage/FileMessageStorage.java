package storage;

import model.Message;
import storage.api.IMessageStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileMessageStorage implements IMessageStorage {
    private static final String FILE_NAME = "message_base.txt";
    private static FileMessageStorage instance=new FileMessageStorage();
    private List<Message> allMessages=new ArrayList<>();

    private FileMessageStorage(){
        readAllMessages();
    }

    public static FileMessageStorage getInstance() {
        return instance;
    }

    private void saveAllMessages(){
        try(FileOutputStream fileOutputStream=new FileOutputStream(FILE_NAME);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(allMessages);
        }catch (IOException e){
            e.getMessage();

        }    }

    private void readAllMessages(){
        try(FileInputStream fileInputStream=new FileInputStream(FILE_NAME);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream)) {
            allMessages= (List<Message>) objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.getMessage();

        }    }

    @Override
    public void putMessage(Message message) {
        allMessages.add(message);
        saveAllMessages();
    }

    @Override
    public List<Message> getAllMessages() {
        readAllMessages();
        return allMessages;
    }
}




