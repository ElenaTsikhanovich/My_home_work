package storage.api;

import model.Department;
import model.Position;

import java.util.List;

public interface IPositionStorage {
    Position get(Long id);
    List<Position> getAll();
}
