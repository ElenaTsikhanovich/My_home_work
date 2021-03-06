package service;

import model.Department;
import model.Employer;
import model.Position;
import model.dto.EmployerParamsDTO;
import service.api.IDepartmentService;
import service.api.IEmployerService;
import service.api.IPositionService;
import storage.api.IEmployerStorage;
import java.util.List;

public class EmployerService implements IEmployerService {
    private final IEmployerStorage iEmployerStorage;
    private final IDepartmentService iDepartmentService;
    private final IPositionService iPositionService;

    public EmployerService(IEmployerStorage iEmployerStorage, IDepartmentService iDepartmentService, IPositionService iPositionService) {
        this.iEmployerStorage = iEmployerStorage;
        this.iDepartmentService = iDepartmentService;
        this.iPositionService = iPositionService;
    }

    public long add(String name, Double salary, Long depId, Long posId){
        Employer employer = new Employer();
        employer.setName(name);
        employer.setSalary(salary);
        Department department = this.iDepartmentService.get(depId);
        if(department!=null) {
            employer.setDepartment(department);
        }else throw new IllegalStateException("В базе данных нет отдела с таким id");
        Position position = this.iPositionService.get(posId);
        if(position!=null) {
            employer.setPosition(position);
        }else throw new IllegalStateException("В базе данных нет должности с таким id");
        long id = this.iEmployerStorage.add(employer);
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

    public List<Employer> getLimit(int limit, int page){
        int offset = (page-1)*limit;
        List<Employer> limitEmployer = this.iEmployerStorage.getLimit(limit, offset);
        return limitEmployer;
    }

    public long getCount(){
        long count = this.iEmployerStorage.getCount();
        return count;
    }

    public long add(Employer employer){
        Department department = this.iDepartmentService.get(employer.getDepartment().getId());
        if(department!=null) {
            employer.setDepartment(department);
        } else throw new IllegalStateException("В базе данных нет отдела с таким id");
        Position position = this.iPositionService.get(employer.getPosition().getId());
        if(position!=null) {
            employer.setPosition(position);
        } else throw new IllegalStateException("В базе данных нет должности с таким id");
        long id = this.iEmployerStorage.add(employer);
        return id;
    }

    @Override
    public List<Employer> find(EmployerParamsDTO employerParamsDTO) {
        int offset = (employerParamsDTO.getPage()-1)*employerParamsDTO.getLimit();
        employerParamsDTO.setOffset(offset);
        List<Employer> employers = this.iEmployerStorage.find(employerParamsDTO);
        return employers;
    }

    public Long getCountFromFind(EmployerParamsDTO employerParamsDTO){
        Long countFromFind = this.iEmployerStorage.getCount(employerParamsDTO);
        return countFromFind;

    }

}
