package storage.api;

import model.Employer;

import java.util.List;

public interface IEmployerStorage {
    long add(Employer employer);
    Employer get(Long id);
    List<Employer> getAll();
    List<Employer> onDepartment(Long idDep);
    List<Employer> onPosition(Long idPos);
}
