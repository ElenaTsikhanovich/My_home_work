package storage.api;

import model.Employer;
import model.dto.EmployerParamsDTO;

import java.util.List;

public interface IEmployerStorage {
    long add(Employer employer);
    Employer get(Long id);
    List<Employer> getAll();
    List<Employer> getLimit(int limit, int offset);
    long getCount();
    List<Employer> find(EmployerParamsDTO employerParamsDTO);
    Long getCount(EmployerParamsDTO employerParamsDTO);
}
