package service.api;

import model.Department;

import java.util.List;

public interface IDepartmentService {
    long add(String depName, Long parentId);
    Department get(Long depId);
    List<Department> getAll();
}
