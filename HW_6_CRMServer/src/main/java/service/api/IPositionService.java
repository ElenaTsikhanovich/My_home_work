package service.api;

import model.Position;

import java.util.List;

public interface IPositionService {
    long add(Position position);
    Position get(Long posId);
    List<Position> getAll();

}
