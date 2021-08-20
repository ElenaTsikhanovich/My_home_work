package VoteApp.storage;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class VoteStorage implements Serializable {
    private static VoteStorage instance = new VoteStorage();

    static {
        try(FileInputStream fileInputStream=new FileInputStream("info.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream)) {
            instance= (VoteStorage) objectInputStream.readObject();
        }catch (FileNotFoundException e){
            instance=new VoteStorage();
        }catch (IOException |ClassNotFoundException e){
            e.getMessage();
        }
    }

    private final Map<String, Integer> artist;
    private final Map<String, Integer> music;
    private final Map<String,String> about;

    private VoteStorage() {
        this.artist = new HashMap<>();
        this.music = new HashMap<>();
        this.about = new LinkedHashMap<>();
    }

    public Map<String, Integer> getArtist() {
        return artist;
    }

    public Map<String, Integer> getMusic() {
        return music;
    }

    public Map<String,String> getAbout() {
        return about;
    }

    public static VoteStorage getInstance() {
        return instance;
    }

    public void saveFile(){
        try(FileOutputStream fileOutputStream=new FileOutputStream("info.txt");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(this);
        }catch (IOException e){
            e.getMessage();
        }
    }

}
