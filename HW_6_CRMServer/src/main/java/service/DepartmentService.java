package service;

import model.Department;
import service.api.IDepartmentService;
import storage.DepartmentHibernateStorage;
import storage.DepartmentStorage;
import storage.api.IDepartmentStorage;
import storage.utils.StorageFactory;

import java.util.List;

public class DepartmentService implements IDepartmentService {
    private static DepartmentService instance=new DepartmentService();
    private IDepartmentStorage iDepartmentStorage;

    public static DepartmentService getInstance() {
        return instance;
    }

    private DepartmentService(){
        this.iDepartmentStorage=StorageFactory.getDepartmentStorage();
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

    @Override
    public long add(Department department) {
        if (department.getParent()!=null){
            if(department.getParent().getId()==null){
                throw new IllegalStateException("Неверно передан параметр");
            }
            Department parentDep = this.iDepartmentStorage.get(department.getParent().getId());
            if(parentDep!=null) {
                department.setParent(parentDep);
            }else throw new IllegalStateException("Неверно указан руководящий отдел");
        }
        final long addId = this.iDepartmentStorage.add(department);
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
