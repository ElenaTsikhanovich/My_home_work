package storage;

import model.Department;
import model.Employer;
import model.Position;
import model.dto.EmployerParamsDTO;
import storage.api.IDepartmentStorage;
import storage.api.IEmployerStorage;
import storage.api.IPositionStorage;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployerStorage implements IEmployerStorage {
    private final DataSource dataSource;
    private IDepartmentStorage iDepartmentStorage;
    private IPositionStorage iPositionStorage;

    public EmployerStorage(DataSource dataSource, IDepartmentStorage iDepartmentStorage, IPositionStorage iPositionStorage) {
        this.dataSource = dataSource;
        this.iDepartmentStorage = iDepartmentStorage;
        this.iPositionStorage = iPositionStorage;
    }

    @Override
    public long add(Employer employer) {
        Long employerId;
        try (Connection connection = dataSource.getConnection();) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO application.employers" +
                    " (name, salary, department, position) " +
                    "VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, employer.getName());
                preparedStatement.setDouble(2, employer.getSalary());
                preparedStatement.setLong(3, employer.getDepartment().getId());
                preparedStatement.setLong(4, employer.getPosition().getId());
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                employerId =generatedKeys.getLong(1);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return employerId;
    }

    @Override
    public Employer get(Long id) {
        Employer employer = null;
        try(Connection connection = dataSource.getConnection();) {
            try(PreparedStatement preparedStatement =connection.prepareStatement("SELECT employers.id, employers.name, " +
                    "employers.salary, employers.department, employers.position " +
                    "FROM application.employers WHERE employers.id = ?;")) {
                preparedStatement.setLong(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    employer=new Employer();
                    employer.setId(resultSet.getLong(1));
                    employer.setName(resultSet.getString(2));
                    employer.setSalary(resultSet.getDouble(3));
                    Department department = this.iDepartmentStorage.get(resultSet.getLong(4));
                    employer.setDepartment(department);
                    Position position = this.iPositionStorage.get(resultSet.getLong(5));
                    employer.setPosition(position);
                }
            }
        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return employer;
    }

    @Override
    public List<Employer> getAll() {
        try (Connection connection = dataSource.getConnection();){
            try (PreparedStatement preparedStatement=connection.prepareStatement("SELECT employers.id, employers.name, " +
                    "employers.salary, employers.department, employers.position FROM application.employers;")){
                final ResultSet resultSet = preparedStatement.executeQuery();
                List<Employer> listEmployers = getListEmployers(resultSet);
                return listEmployers;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

    public List<Employer> getLimit(int limit, int offset){
        try (Connection connection = dataSource.getConnection();){
            try(PreparedStatement preparedStatement=connection.prepareStatement("SELECT employers.id, " +
                    "employers.name, employers.salary, employers.department, employers.position " +
                    "FROM application.employers ORDER BY id ASC LIMIT ? OFFSET ?;")) {
                preparedStatement.setInt(1,limit);
                preparedStatement.setInt(2,offset);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Employer> listEmployers = getListEmployers(resultSet);
                return listEmployers;
            }
        }  catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }


    public long getCount(){
        long count=0;
        try(Connection connection = dataSource.getConnection();) {
            try(Statement statement = connection.createStatement();) {
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) FROM application.employers;");
                while (resultSet.next()) {
                    count = resultSet.getLong(1);
                }
            }

        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return count;
    }

    @Override
    public List<Employer> find(EmployerParamsDTO employerParamsDTO) {
        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT " +
                    "employers.id, employers.name, employers.salary, employers.department, employers.position " +
                    "FROM application.employers " +
                    "WHERE employers.name LIKE ? " +
                    "AND employers.salary BETWEEN ? AND ?;")) {
                preparedStatement.setString(1,employerParamsDTO.getName());
                preparedStatement.setDouble(2,employerParamsDTO.getSalaryFrom());
                preparedStatement.setDouble(3,employerParamsDTO.getSalaryTo());
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Employer> listEmployers = getListEmployers(resultSet);
                return listEmployers;
            }
        }catch (SQLException e){
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

    @Override
    public Long getCount(EmployerParamsDTO employerParamsDTO) {
        long count=0;
        try(Connection connection = dataSource.getConnection();) {
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(id) " +
                    "FROM application.employers WHERE employers.name LIKE ? AND employers.salary BETWEEN ? AND ?;")){
                preparedStatement.setString(1, employerParamsDTO.getName());
                preparedStatement.setDouble(2,employerParamsDTO.getSalaryFrom());
                preparedStatement.setDouble(3,employerParamsDTO.getSalaryTo());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    count = resultSet.getLong(1);
                }
            }

        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return count;
    }


    private List<Employer> getListEmployers(ResultSet resultSet) throws SQLException {
        List<Employer> allEmployers=new ArrayList<>();
        while (resultSet.next()){
            Employer employer = new Employer();
            employer.setId(resultSet.getLong(1));
            employer.setName(resultSet.getString(2));
            employer.setSalary(resultSet.getDouble(3));
            employer.setDepartment(this.iDepartmentStorage.get(resultSet.getLong(4)));
            employer.setPosition(this.iPositionStorage.get(resultSet.getLong(5)));
            allEmployers.add(employer);
        }
        return allEmployers;

    }
}

