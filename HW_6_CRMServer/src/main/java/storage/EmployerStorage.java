package storage;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import model.Department;
import model.Employer;
import model.Position;


import storage.api.IEmployerStorage;
import storage.utils.AppDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployerStorage implements IEmployerStorage {
    private DataSource dataSource;
    private static EmployerStorage instance=new EmployerStorage();
    private EmployerStorage(){
        this.dataSource=AppDataSource.getInstance();
    }

    public static EmployerStorage getInstance() {
        return instance;
    }

    @Override
    public long add(Employer employer) {
        long employerId=0;
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
        Employer employer = new Employer();
        try(Connection connection = dataSource.getConnection();) {
            try(PreparedStatement preparedStatement =connection.prepareStatement("SELECT employers.name,\n" +
                    "employers.salary, \n" +
                    "departments.name AS department, \n" +
                    "positions.name AS position\n" +
                    "FROM application.employers\n" +
                    "JOIN application.departments ON employers.department=departments.id\n" +
                    "JOIN application.positions ON employers.position=positions.id\n" +
                    "WHERE employers.id = ?;")) {
                preparedStatement.setLong(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                employer.setName(resultSet.getString(1));
                employer.setSalary(Double.parseDouble(resultSet.getString(2)));
                employer.setId(id);
                Department department = new Department();
                department.setName(resultSet.getString(3));
                employer.setDepartment(department);
                Position position = new Position();
                position.setName(resultSet.getString(4));
                employer.setPosition(position);
            }

        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return employer;
    }

    @Override
    public List<Employer> getAll() {
        List<Employer> allEmployers=new ArrayList<>();
        try (Connection connection = dataSource.getConnection();){
            try (PreparedStatement preparedStatement=connection.prepareStatement("SELECT employers.id, employers.name\n" +
                    "FROM application.employers;")){
                final ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Employer employer = new Employer();
                    employer.setId(resultSet.getLong(1));
                    employer.setName(resultSet.getString(2));
                    allEmployers.add(employer);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return allEmployers;
    }

    @Override
    public List<Employer> onPosition(Long idPos) {
        List<Employer> employersOnPosition=new ArrayList<>();
        try(Connection connection = dataSource.getConnection();) {
            try(PreparedStatement preparedStatement=connection.prepareStatement("SELECT positions.name,\n" +
                    "employers.name, employers.id FROM application.positions\n" +
                    "JOIN application.employers ON positions.id=employers.position\n" +
                    "WHERE positions.id=?;")) {
                preparedStatement.setLong(1,idPos);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Employer employer = new Employer();
                    employer.setName(resultSet.getString(2));
                    employer.setId(resultSet.getLong(3));
                    employersOnPosition.add(employer);
                }
            }
        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return employersOnPosition;
    }

    @Override
    public List<Employer> onDepartment(Long idDep) {
        List<Employer> employersInDepartment=new ArrayList<>();
        try (Connection connection = dataSource.getConnection();){
            try (PreparedStatement preparedStatement=connection.prepareStatement("SELECT departments.name,\n" +
                    "employers.name AS employers,\n" +
                    "employers.id AS employer_id\n" +
                    "FROM application.departments\n" +
                    "JOIN application.employers ON employers.department=departments.id\n" +
                    "WHERE departments.id=?;")){
                preparedStatement.setLong(1,idDep);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Employer employer = new Employer();
                    employer.setName(resultSet.getString(2));
                    employer.setId(resultSet.getLong(3));
                    employersInDepartment.add(employer);
                }
            }
        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return employersInDepartment;
    }
}
