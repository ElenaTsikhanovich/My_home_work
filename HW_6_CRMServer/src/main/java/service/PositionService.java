package service;

import model.Position;
import service.api.IPositionService;
import storage.api.IPositionStorage;

import java.util.List;

public class PositionService implements IPositionService {
    private IPositionStorage iPositionStorage;

    public PositionService(IPositionStorage iPositionStorage) {
        this.iPositionStorage = iPositionStorage;
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
