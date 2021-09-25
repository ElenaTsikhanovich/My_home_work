package service.api;

import model.Department;

import java.util.List;

public interface IDepartmentService {
    long add(String depName, Long parentId);
    long add(Department department);
    Department get(Long depId);
    List<Department> getAll();
}
