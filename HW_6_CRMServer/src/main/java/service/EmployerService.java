package service;

import model.Department;
import model.Employer;
import model.Position;
import storage.EmployerStorage;
import storage.api.IEmployerStorage;

import java.util.ArrayList;
import java.util.List;

public class EmployerService {
    private static EmployerService instance=new EmployerService();
    private IEmployerStorage iEmployerStorage;
    private DepartmentService departmentService;
    private PositionService positionService;

    public static EmployerService getInstance() {
        return instance;
    }

    private EmployerService(){
      this.iEmployerStorage= EmployerStorage.getInstance();
      this.departmentService=DepartmentService.getInstance();
      this.positionService=PositionService.getInstance();

    }

    public long add(String name, Double salary, Long depId, Long posId){
        Employer employer = new Employer();
        employer.setName(name);
        employer.setSalary(salary);
        Department department = this.departmentService.getDepartment(depId);
        employer.setDepartment(department);
        Position position = this.positionService.getPosition(posId);
        employer.setPosition(position);
        final long id = this.iEmployerStorage.add(employer);
        return id;
    }

    public Employer getEmployer(Long id){
        Employer employer = this.iEmployerStorage.get(id);
        return employer;
    }

    public List<Employer> getEmployers(){
        List<Employer> allEmployers = this.iEmployerStorage.getAll();
        return allEmployers;
    }

    public List<Employer> getLimitEmployers(long limit, long page){
        long offset = (page-1)*limit;
        final List<Employer> limitEmployer = this.iEmployerStorage.getLimit(limit, offset);
        return limitEmployer;
    }

    public long getCountOfEmployers(){
        long count = this.iEmployerStorage.getCount();
        return count;
    }


    public long addFromJason(Employer employer){
        Department department = this.departmentService.getDepartment(employer.getDepartment().getId());
        employer.setDepartment(department);
        Position position = this.positionService.getPosition(employer.getPosition().getId());
        employer.setPosition(position);
        final long id = this.iEmployerStorage.add(employer);
        return id;
    }






}
