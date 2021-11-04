package storage;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import model.Position;
import storage.api.IPositionStorage;
import storage.utils.AppDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionStorage implements IPositionStorage {
    private final DataSource dataSource;

    public PositionStorage(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long add(Position position) {
        Long positionId;
        try (Connection connection = dataSource.getConnection();) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO application.positions" +
                    " (name) " +
                    "VALUES (?);", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, position.getName());
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                positionId =generatedKeys.getLong(1);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return positionId;
    }

    @Override
    public Position get(Long id) {
        Position position = null;
        try (Connection connection =dataSource.getConnection();){
            try(PreparedStatement preparedStatement=connection.prepareStatement("SELECT positions.id, positions.name\n" +
                    "FROM application.positions\n" +
                    "WHERE positions.id=?;")) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    position=new Position();
                    position.setId(resultSet.getLong(1));
                    position.setName(resultSet.getString(2));
                }
            }
        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return position;
    }

    @Override
    public List<Position> getAll() {
        List<Position> allPositions=new ArrayList<>();
        try (Connection connection = dataSource.getConnection();){
            try (PreparedStatement preparedStatement= connection.prepareStatement("SELECT positions.id, positions.name " +
                    "FROM application.positions;")){
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Position position = new Position();
                    position.setId(resultSet.getLong(1));
                    position.setName(resultSet.getString(2));
                    allPositions.add(position);
                }
            }
        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return allPositions;
    }


}
