package config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import storage.*;
import storage.api.IDepartmentStorage;
import storage.api.IEmployerStorage;
import storage.api.IPositionStorage;
import storage.utils.AppDataSource;

import javax.sql.DataSource;
@PropertySource("classpath:storage.properties")
@Configuration
public class DAOConfigs {

    @Value("${DAOConfigs.storage}")
    public String storage;

    @Bean
    public IEmployerStorage employerStorage(SessionFactory sessionFactory,DataSource dataSource,
                                            IDepartmentStorage departmentStorage,IPositionStorage positionStorage){
        if (storage.equalsIgnoreCase("JDBC")) {
            return new EmployerStorage(dataSource, departmentStorage, positionStorage);
        }
        return new EmployerHibernateStorage(sessionFactory);
    }

    @Bean
    public IDepartmentStorage departmentStorage(SessionFactory sessionFactory,DataSource dataSource){
        if (storage.equalsIgnoreCase("JDBC")) {
            return new DepartmentStorage(dataSource);
        }
        return new DepartmentHibernateStorage(sessionFactory);
    }

    @Bean
    public IPositionStorage positionStorage(SessionFactory sessionFactory,DataSource dataSource){
        if (storage.equalsIgnoreCase("JDBC")) {
            return new PositionStorage(dataSource);
        }
        return new PositionHibernateStorage(sessionFactory);
    }


    @Bean
    public SessionFactory sessionFactory(DataSource dataSource){
        LocalSessionFactoryBuilder builder=new LocalSessionFactoryBuilder(dataSource);
        builder.scanPackages("model");
        builder.setProperty("hibernate.show_sql","true");
        builder.setProperty("hibernate.default_schema","application");
        builder.setProperty("hibernate.hbm2ddl.auto","update");
        builder.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQL10Dialect");
        return builder.buildSessionFactory();
    }

    @Bean
    public DataSource dataSource(){
        return AppDataSource.getInstance();
    }

}
