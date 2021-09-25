package service;

import model.Position;
import service.api.IPositionService;
import storage.PositionStorage;
import storage.api.IPositionStorage;

import java.util.List;

public class PositionService implements IPositionService {
    private static PositionService instance=new PositionService();
    private IPositionStorage iPositionStorage;

    public static PositionService getInstance() {
        return instance;
    }
    private PositionService(){
        this.iPositionStorage= PositionStorage.getInstance();
    }

    @Override
    public long add(String name) {
        Position position = new Position();
        position.setName(name);
        long addId = this.iPositionStorage.add(position);
        return addId;
    }

    public long add(Position position){
        long addId = this.iPositionStorage.add(position);
        return addId;
    }
    public List<Position> getAll(){
        List<Position> allPositions = this.iPositionStorage.getAll();
        return allPositions;
    }

    public Position get(Long id){
        Position position = this.iPositionStorage.get(id);
        return position;
    }
}
