package storage;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import model.Department;
import model.Employer;
import model.Position;


import service.DepartmentService;
import service.PositionService;
import storage.api.IEmployerStorage;
import storage.utils.AppDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployerStorage implements IEmployerStorage {
    private DataSource dataSource;
    private DepartmentService departmentService;
    private PositionService positionService;
    private static EmployerStorage instance=new EmployerStorage();
    private EmployerStorage(){
        this.dataSource=AppDataSource.getInstance();
        this.departmentService=DepartmentService.getInstance();
        this.positionService=PositionService.getInstance();
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
            try(PreparedStatement preparedStatement =connection.prepareStatement("SELECT employers.id, employers.name, " +
                    "employers.salary, employers.department, employers.position " +
                    "FROM application.employers WHERE employers.id = ?;")) {
                preparedStatement.setLong(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    employer.setId(resultSet.getLong(1));
                    employer.setName(resultSet.getString(2));
                    employer.setSalary(resultSet.getDouble(3));
                    Department department = this.departmentService.getDepartment(resultSet.getLong(4));
                    employer.setDepartment(department);
                    Position position = this.positionService.getPosition(resultSet.getLong(5));
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
        List<Employer> allEmployers=new ArrayList<>();
        try (Connection connection = dataSource.getConnection();){
            try (PreparedStatement preparedStatement=connection.prepareStatement("SELECT employers.id, employers.name, " +
                    "employers.salary, employers.department, employers.position FROM application.employers;")){
                final ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Employer employer = new Employer();
                    employer.setId(resultSet.getLong(1));
                    employer.setName(resultSet.getString(2));
                    employer.setSalary(resultSet.getDouble(3));
                    employer.setDepartment(this.departmentService.getDepartment(resultSet.getLong(4)));
                    employer.setPosition(this.positionService.getPosition(resultSet.getLong(5)));
                    allEmployers.add(employer);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return allEmployers;
    }

    public List<Employer> getLimit(long limit, long offset){
        List<Employer> limitEmployers=new ArrayList<>();
        try (Connection connection = dataSource.getConnection();){
            try(PreparedStatement preparedStatement=connection.prepareStatement("SELECT employers.id, " +
                    "employers.name, employers.salary, employers.department, employers.position " +
                    "FROM application.employers ORDER BY id ASC LIMIT ? OFFSET ?;")) {
                preparedStatement.setLong(1,limit);
                preparedStatement.setLong(2,offset);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Employer employer = new Employer();
                    employer.setId(resultSet.getLong(1));
                    employer.setName(resultSet.getString(2));
                    employer.setSalary(resultSet.getDouble(3));
                    employer.setDepartment(this.departmentService.getDepartment(resultSet.getLong(4)));
                    employer.setPosition(this.positionService.getPosition(resultSet.getLong(5)));
                    limitEmployers.add(employer);
                }
            }

        }  catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return limitEmployers;
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


    }
