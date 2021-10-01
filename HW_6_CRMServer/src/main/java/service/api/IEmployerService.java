package service.api;

import model.Employer;
import model.dto.EmployerParamsDTO;

import java.util.List;

public interface IEmployerService {
    long add(Employer employer);
    long add(String name, Double salary, Long depId, Long posId);
    Employer get(Long empId);
    List<Employer> getAll();
    List<Employer> getLimit(long limit, long page);
    long getCount();
    List<Employer> find(EmployerParamsDTO employerParamsDTO);


}
