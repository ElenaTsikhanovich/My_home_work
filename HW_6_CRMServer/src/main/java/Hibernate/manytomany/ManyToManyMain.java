package Hibernate.manytomany;

import Hibernate.manytomany.dto.Department;
import Hibernate.manytomany.dto.Employer;
import Hibernate.manytomany.utils.AppSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.List;

public class ManyToManyMain {
    public static void main(String[] args) {
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Employer employer = new Employer();
        employer.setName("Mike");
        employer.setSalary(676868.6868);
        Long empId= (Long) session.save(employer);

        Employer employer1 = new Employer();
        employer1.setName("Lena");
        employer1.setSalary(7668.78);
        Long empId1= (Long) session.save(employer1);

        List<Employer> employers = Arrays.asList(employer, employer1);

        Department department = new Department();
        department.setName("Транспортный отдел");
        department.setEmployers(employers);

        Long depId= (Long) session.save(department);
        transaction.commit();
        session.close();


        Session session1 = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction1 = session1.beginTransaction();

        Department department1 = session1.get(Department.class, depId);
        List<Employer> employers1 = department1.getEmployers();
        for (Employer emp:employers1){
            System.out.printf("Id: %d Name: %s Salary: %s\n",
                    emp.getId(),emp.getName(),emp.getSalary());
        }

        Employer employer2 = session1.get(Employer.class, empId);
        List<Department> departments = employer2.getDepartments();
        for (Department dep:departments){
            System.out.printf("Id: %d Name: %s \n",
                    dep.getId(),dep.getName());

        }

        transaction1.commit();
        session1.close();
        AppSessionFactory.shutDown();

    }
}
