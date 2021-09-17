package storage;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import model.Department;
import storage.api.IDepartmentStorage;

import storage.utils.AppDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentStorage implements IDepartmentStorage {
    private DataSource dataSource;
    private static DepartmentStorage instance =new DepartmentStorage();
    private DepartmentStorage(){
        this.dataSource=AppDataSource.getInstance();
    }

    public static DepartmentStorage getInstance() {
        return instance;
    }

    @Override
    public Department get(Long id) {
        Department department = new Department();
        try (Connection connection = dataSource.getConnection();){
            try (PreparedStatement preparedStatement=connection.prepareStatement("SELECT departments.id, departments.name, departments.parent \n" +
                    "FROM application.departments WHERE departments.id=?;")){
                preparedStatement.setLong(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    department.setId(resultSet.getLong(1));
                    department.setName(resultSet.getString(2));
                    Department parent = get(resultSet.getLong(3));
                    department.setParent(parent);
                }
            }

        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return department;
    }

    @Override
    public List<Department> getAll() {
        List<Department> allDepartments = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();) {
            try(PreparedStatement preparedStatement=connection.prepareStatement("SELECT departments.id, " +
                    "departments.name, departments.parent FROM application.departments;")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Department department = new Department();
                    department.setId(resultSet.getLong(1));
                    department.setName(resultSet.getString(2));
                    Department parent = get(resultSet.getLong(3));
                    department.setParent(parent);
                    allDepartments.add(department);
                }
            }

        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return allDepartments;
    }

}
