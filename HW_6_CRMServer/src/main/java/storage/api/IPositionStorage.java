package storage.api;

import model.Department;
import model.Position;

import java.util.List;

public interface IPositionStorage {
    long add(Position position);
    Position get(Long id);
    List<Position> getAll();
}
