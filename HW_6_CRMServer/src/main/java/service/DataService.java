package service;

import model.Department;
import model.Employer;
import model.Position;
import storage.DataStorage;

import java.util.List;
import java.util.Map;

public class DataService {
    private static DataService instance=new DataService();
    private DataStorage dataStorage;

    public static DataService getInstance() {
        return instance;
    }

    private DataService() {
        this.dataStorage=DataStorage.getInstance();
    }

    public long add(String name, Double salary, Long depId, Long posId){
        Employer employer = new Employer();
        employer.setName(name);
        employer.setSalary(salary);
        Department department = new Department();
        department.setId(depId);
        Position position = new Position();
        position.setId(posId);
        employer.setDepartment(department);
        employer.setPosition(position);
        long id = this.dataStorage.addEmployer(employer);
        return id;
    }

    public Employer getEmployer(Long id){
        Employer employer = this.dataStorage.getEmployer(id);
        return employer;
    }

    public Department getDepartment(Long id){
        Department department = this.dataStorage.getDepartment(id);
        return department;
    }

    public List<Employer> getEmployerOnPosition(Long id){
        List<Employer> employersOnPosition = this.dataStorage.getEmployersOnPosition(id);
        return employersOnPosition;
    }

    public List<Employer> getEmployers(){
        List<Employer> allEmployers = this.dataStorage.getAllEmployers();
        return allEmployers;
    }

    public List<Department> getDepartments(){
        List<Department> allDepartments = this.dataStorage.getAllDepartments();
        return allDepartments;
    }

    public List<Employer> getEmployerInDep(Long id){
        List<Employer> employersInDepartment = this.dataStorage.getEmployersInDepartment(id);
        return employersInDepartment;
    }

    public List<Position> getPositions(){
        List<Position> allPositions = this.dataStorage.getAllPositions();
        return allPositions;
    }

    public Position getPosition(Long id){
        Position position = this.dataStorage.getPosition(id);
        return position;
    }
}
