package service;

import model.Department;
import storage.DepartmentStorage;
import storage.api.IDepartmentStorage;

import java.util.List;

public class DepartmentService {
    private static DepartmentService instance=new DepartmentService();
    private IDepartmentStorage iDepartmentStorage;

    public static DepartmentService getInstance() {
        return instance;
    }

    private DepartmentService(){
        this.iDepartmentStorage= DepartmentStorage.getInstance();
    }

    public Department getDepartment(Long id){
        Department department = this.iDepartmentStorage.get(id);
        return department;
    }

    public List<Department> getDepartments(){
        List<Department> allDepartments = this.iDepartmentStorage.getAll();
        return allDepartments;
    }



}
