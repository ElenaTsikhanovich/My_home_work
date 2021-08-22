package storage;

import model.User;
import storage.api.IUserStorage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUserStorage implements IUserStorage {
    private static final String FILE_NAME = "user_base.txt";
    private static FileUserStorage instance=new FileUserStorage();
    private Map<String, User> allUsers=new HashMap<>();

    private FileUserStorage(){
        readAllUsers();
    }

    public static FileUserStorage getInstance() {
        return instance;
    }

    private void saveAllUsers(){
        try(FileOutputStream fileOutputStream=new FileOutputStream(FILE_NAME);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(allUsers);
        }catch (IOException e){
            e.getMessage();
        }
    }

    private void readAllUsers(){
        try(FileInputStream fileInputStream=new FileInputStream(FILE_NAME);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream)) {
          allUsers= (Map<String, User>) objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.getMessage();

        }    }

    @Override
    public void putUser(User user) {
        allUsers.put(user.getLogin(), user);
        saveAllUsers();
    }

    @Override
    public Map<String, User> getAllUsers() {
        readAllUsers();
        return allUsers;
    }
}




