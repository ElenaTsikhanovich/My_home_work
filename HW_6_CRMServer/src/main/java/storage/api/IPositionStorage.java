package storage.api;

import model.Employer;
import model.Position;

import java.util.List;

public interface IPositionStorage {
    Position get(Long id);
    List<Position> getAll();
}
