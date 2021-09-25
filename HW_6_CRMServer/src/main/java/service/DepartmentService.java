package service;

import model.Department;
import service.api.IDepartmentService;
import storage.DepartmentStorage;
import storage.api.IDepartmentStorage;

import java.util.List;

public class DepartmentService implements IDepartmentService {
    private static DepartmentService instance=new DepartmentService();
    private IDepartmentStorage iDepartmentStorage;

    public static DepartmentService getInstance() {
        return instance;
    }

    private DepartmentService(){
        this.iDepartmentStorage= DepartmentStorage.getInstance();
    }

    public long add(String depName, Long parentId){
        Department department = new Department();
        department.setName(depName);
        if(parentId==-1){
            department.setParent(null);
        }else {
            Department parentDep = this.iDepartmentStorage.get(parentId);
            department.setParent(parentDep);
        }
        long addId = this.iDepartmentStorage.add(department);
        return addId;
    }


    public Department get(Long id){
        Department department = this.iDepartmentStorage.get(id);
        return department;
    }

    public List<Department> getAll(){
        List<Department> allDepartments = this.iDepartmentStorage.getAll();
        return allDepartments;
    }



}
