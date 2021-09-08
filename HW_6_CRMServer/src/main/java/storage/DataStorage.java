package storage;

import model.Department;
import model.Employer;
import model.Position;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {
    private static DataStorage instance = new DataStorage();

    public static DataStorage getInstance() {
        return instance;
    }

    public long addEmployer(Employer employer) {
        long employerId=0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "6780911");) {
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

    public Employer getEmployer(Long id){
        Employer employer = new Employer();
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "6780911");) {
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

    public List<Employer> getEmployersOnPosition(Long id){
        List<Employer> employersOnPosition=new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "6780911");) {
            try(PreparedStatement preparedStatement=connection.prepareStatement("SELECT positions.name,\n" +
                    "employers.name, employers.id FROM application.positions\n" +
                    "JOIN application.employers ON positions.id=employers.position\n" +
                    "WHERE positions.id=?;")) {
                preparedStatement.setLong(1,id);
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

    public List<Employer> getEmployersInDepartment(Long id){
        List<Employer> employersInDepartment=new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "6780911");){
            try (PreparedStatement preparedStatement=connection.prepareStatement("SELECT departments.name,\n" +
                    "employers.name AS employers,\n" +
                    "employers.id AS employer_id\n" +
                    "FROM application.departments\n" +
                    "JOIN application.employers ON employers.department=departments.id\n" +
                    "WHERE departments.id=?;")){
                preparedStatement.setLong(1,id);
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


    public List<Employer> getAllEmployers(){
        List<Employer> allEmployers=new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "6780911");){
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

    public List<Department> getAllDepartments(){
        List<Department> allDepartments = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "6780911");) {
            try(PreparedStatement preparedStatement=connection.prepareStatement("SELECT departments.id, " +
                    "departments.name FROM application.departments;")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Department department = new Department();
                    department.setId(resultSet.getLong(1));
                    department.setName(resultSet.getString(2));
                    allDepartments.add(department);
                }
            }

        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return allDepartments;
    }

    public Department getDepartment(Long id){
        Department department = new Department();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "6780911");){
            try (PreparedStatement preparedStatement=connection.prepareStatement("SELECT departments.id, departments.name, parent_dep.name \n" +
                    "FROM application.departments\n" +
                    "JOIN application.departments AS parent_dep ON departments.parent=parent_dep.id\n" +
                    "WHERE departments.id=?;")){
                preparedStatement.setLong(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                department.setId(resultSet.getLong(1));
                department.setName(resultSet.getString(2));
                department.setParent(resultSet.getString(3));
            }

        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
        return department;
    }


    public List<Position> getAllPositions(){
        List<Position> allPositions=new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "6780911");){
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

    public Position getPosition(Long id){
        Position position = new Position();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "6780911");){
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

    public void fullBase(){
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "6780911");){
            try (FileReader fileReader=new FileReader("names.txt");
                 BufferedReader bufferedReader=new BufferedReader(fileReader)){
                String name;
                while ((name=bufferedReader.readLine())!=null){

                    try (PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO application.employers \n" +
                            "(name, salary, department, position)\n" +
                            "VALUES (?,?,?,?);")){
                        preparedStatement.setString(1,name);
                        preparedStatement.setDouble(2,Math.random()*99999999);
                        preparedStatement.setLong(3,(long) (Math.random()*5)+11);
                        preparedStatement.setLong(4, (long) (Math.random()*9)+2);
                        preparedStatement.executeUpdate();
                    }
                }
            }catch (IOException e){
                e.getMessage();

            }
        }catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }

    }

}
