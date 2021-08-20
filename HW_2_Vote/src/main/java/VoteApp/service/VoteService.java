package VoteApp.service;

import VoteApp.storage.VoteStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class VoteService {
    private final static VoteService instance=new VoteService();
    private final VoteStorage storage;

    private VoteService(){
        this.storage=VoteStorage.getInstance();
    }

    public void addVote(String artist,String[]music,String about){
        Integer artistPosition = this.storage.getArtist().getOrDefault(artist, 0);
        this.storage.getArtist().put(artist,++artistPosition);

        if (music!=null){
        for(String ms:music) {
            Integer musicPosition = this.storage.getMusic().getOrDefault(ms, 0);
            this.storage.getMusic().put(ms, ++musicPosition);
        }
        }

        LocalDateTime date=LocalDateTime.now();
        this.storage.getAbout().put(date.format(DateTimeFormatter.ofPattern("hh:mm:ss dd:MM:yyyy")),about);

        this.storage.saveFile();
    }

    public Map<String,Integer> getArtistResult(){
        return this.storage.getArtist();
    }

    public Map<String,Integer> getMusicResult(){
        return this.storage.getMusic();
    }

    public Map<String,String> getAboutResult(){
        return this.storage.getAbout();
    }

    public static VoteService getInstance(){
        return instance;
    }

    public Map<String,Integer> sort(Map<String,Integer> unSortedMap){
        Map<String,Integer> result=new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> entries = new LinkedList<>(unSortedMap.entrySet());
        entries.sort((x,y)->y.getValue().compareTo(x.getValue()));
        for(Map.Entry<String,Integer>entry:entries){
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}
