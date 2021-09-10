package service;

import model.Position;
import storage.PositionStorage;
import storage.api.IPositionStorage;

import java.util.List;

public class PositionService {
    private static PositionService instance=new PositionService();
    private IPositionStorage iPositionStorage;

    public static PositionService getInstance() {
        return instance;
    }
    private PositionService(){
        this.iPositionStorage= PositionStorage.getInstance();
    }

    public List<Position> getPositions(){
        List<Position> allPositions = this.iPositionStorage.getAll();
        return allPositions;
    }

    public Position getPosition(Long id){
        Position position = this.iPositionStorage.get(id);
        return position;
    }
}
