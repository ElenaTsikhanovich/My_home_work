package service;

import model.Department;
import model.Employer;
import model.Position;
import service.api.IDepartmentService;
import service.api.IEmployerService;
import service.api.IPositionService;
import storage.EmployerStorage;
import storage.api.IEmployerStorage;

import java.util.ArrayList;
import java.util.List;

public class EmployerService implements IEmployerService {
    private static EmployerService instance=new EmployerService();
    private IEmployerStorage iEmployerStorage;
    private IDepartmentService iDepartmentService;
    private IPositionService iPositionService;

    public static EmployerService getInstance() {
        return instance;
    }

    private EmployerService(){
      this.iEmployerStorage= EmployerStorage.getInstance();
      this.iDepartmentService=DepartmentService.getInstance();
      this.iPositionService=PositionService.getInstance();

    }

    public long add(String name, Double salary, Long depId, Long posId){
        Employer employer = new Employer();
        employer.setName(name);
        employer.setSalary(salary);
        Department department = this.iDepartmentService.get(depId);
        employer.setDepartment(department);
        Position position = this.iPositionService.get(posId);
        employer.setPosition(position);
        final long id = this.iEmployerStorage.add(employer);
        return id;
    }

    public Employer get(Long id){
        Employer employer = this.iEmployerStorage.get(id);
        return employer;
    }

    public List<Employer> getAll(){
        List<Employer> allEmployers = this.iEmployerStorage.getAll();
        return allEmployers;
    }

    public List<Employer> getLimit(long limit, long page){
        long offset = (page-1)*limit;
        final List<Employer> limitEmployer = this.iEmployerStorage.getLimit(limit, offset);
        return limitEmployer;
    }

    public long getCount(){
        long count = this.iEmployerStorage.getCount();
        return count;
    }


    public long add(Employer employer){
        Department department = this.iDepartmentService.get(employer.getDepartment().getId());
        employer.setDepartment(department);
        Position position = this.iPositionService.get(employer.getPosition().getId());
        employer.setPosition(position);
        final long id = this.iEmployerStorage.add(employer);
        return id;
    }






}
