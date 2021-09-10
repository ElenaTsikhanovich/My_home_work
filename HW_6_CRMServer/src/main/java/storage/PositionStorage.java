package storage;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import model.Position;
import storage.api.IPositionStorage;
import storage.utils.AppDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionStorage implements IPositionStorage {
    private DataSource dataSource;
    private static PositionStorage instance = new PositionStorage();
    private PositionStorage(){
        this.dataSource=AppDataSource.getInstance();
    }

    public static PositionStorage getInstance() {
        return instance;
    }

    @Override
    public Position get(Long id) {
        Position position = new Position();
        try (Connection connection =dataSource.getConnection();){
            try(PreparedStatement preparedStatement=connection.prepareStatement("SELECT positions.name\n" +
                    "FROM application.positions\n" +
                    "WHERE positions.id=?;")) {
                preparedStatement.setLong(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                position.setId(id);
                position.setName(resultSet.getString(1));
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
            try (PreparedStatement preparedStatement= connection.prepareStatement("SELECT positions.id, positions.name\n" +
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
