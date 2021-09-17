package storage.api;

import model.Department;
import model.Employer;

import java.util.List;

public interface IDepartmentStorage {
    long add(Department department);
    Department get(Long id);
    List<Department> getAll();
}
