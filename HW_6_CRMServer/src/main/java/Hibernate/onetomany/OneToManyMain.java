package Hibernate.onetomany;

import Hibernate.onetomany.dto.Department;
import Hibernate.onetomany.dto.Employer;
import Hibernate.onetomany.utils.AppSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.List;

public class OneToManyMain {
    public static void main(String[] args) {
       Employer mike = new Employer("Mike", 67889.869);
       Employer lena = new Employer("Lena", 686.67);
       Employer gary = new Employer("Gary", 676895.89);
       saveEmployer(mike);
       saveEmployer(lena);
       saveEmployer(gary);
       List<Employer> employers = Arrays.asList(mike, lena, gary);

       Department department = new Department("Бухгалтерия", employers);
       Long depId = addDepartmentWithEmployers(department);

        Department department1 = getDepartmentById(depId);
        System.out.println(department1.toString());

        Employer andrey = new Employer("Andrey", 689.78);
        saveEmployer(andrey);
        addEmployerInDepartment(andrey,depId);
        System.out.println(getDepartmentById(depId).toString());

        Employer sasha = new Employer("Sasha", 67899.99);
        saveEmployer(sasha);
        updateEmployersInDepartment(sasha,depId);
        System.out.println(getDepartmentById(depId).toString());

        List<Employer> employersFromDepartment = getEmployersFromDepartment(depId);
        for(Employer employer:employersFromDepartment){
            System.out.printf("Id: %d Name:%s\n",employer.getId(),employer.getName());
        }

        deleteDepartment(depId);

        Employer taty = new Employer("Taty", 6686.99);
        saveEmployer(taty);
        Department department2 = new Department("Отдел кадров", Arrays.asList(taty));
        Long depId2 = addDepartmentWithEmployers(department2);

        System.out.println(getDepartmentById(depId2).toString());

        AppSessionFactory.shutDown();
    }

    public static void saveEmployer(Employer employer){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employer);
        transaction.commit();
        session.close();
    }

    public static Long addDepartmentWithEmployers(Department department){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long depId= (Long) session.save(department);
        transaction.commit();
        session.close();
        return depId;
    }

    public static Department getDepartmentById(Long depId){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Department department = session.get(Department.class, depId);
        transaction.commit();
        session.close();
        return department;
    }

    public static List<Employer> getEmployersFromDepartment(Long depId){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Department department = session.get(Department.class, depId);
        List<Employer> employers = department.getEmployers();
        transaction.commit();
        session.close();
        return employers;
    }

    public static void addEmployerInDepartment(Employer employer, Long depId){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Department department = session.get(Department.class, depId);
        department.getEmployers().add(employer);
        session.update(department);
        transaction.commit();
        session.close();
    }
    public static void updateEmployersInDepartment(Employer employer, Long depId){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Department department = session.get(Department.class, depId);
        department.setEmployers(Arrays.asList(employer));
        session.update(department);
        transaction.commit();
        session.close();
    }

    public static void deleteDepartment(Long depId){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Department department = session.get(Department.class, depId);
        session.delete(department);
        transaction.commit();
        session.close();
    }
}
