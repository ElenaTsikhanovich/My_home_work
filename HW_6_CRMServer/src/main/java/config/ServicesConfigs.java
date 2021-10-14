package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.DepartmentService;
import service.EmployerService;
import service.PositionService;
import service.api.IDepartmentService;
import service.api.IEmployerService;
import service.api.IPositionService;
import storage.api.IDepartmentStorage;
import storage.api.IEmployerStorage;
import storage.api.IPositionStorage;

@Configuration
public class ServicesConfigs {

    @Bean
    public IEmployerService employerService(IEmployerStorage employerStorage,IDepartmentService departmentService,IPositionService positionService){
        return new EmployerService(employerStorage,departmentService,positionService);
    }

    @Bean
    public IDepartmentService departmentService(IDepartmentStorage departmentStorage){
        return new DepartmentService(departmentStorage);
    }

    @Bean
    public IPositionService positionService(IPositionStorage positionStorage){
        return new PositionService(positionStorage);
    }
}
