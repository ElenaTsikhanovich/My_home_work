package Hibernate.manytoone;

import Hibernate.manytoone.dto.Department;
import Hibernate.manytoone.dto.Employer;
import Hibernate.manytoone.utils.AppSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManyToOneMain {
    public static void main(String[] args) {

        //некоторые методы для проверки правильности связей в таблицах

        //создали отдел
        Department department = new Department("Бухгалтерия", new ArrayList<>());
        Long depId = saveDepartment(department);
        //создали Майка
        Employer employer = new Employer("Mike", 6789.89, null);
        Long empId = saveEmployer(employer);

        //сперва в отделе никто не работал
        //проверяем
        List<Employer> employersByDepartment = getEmployersByDepartment(depId);
        for (Employer emp:employersByDepartment){
            System.out.printf("Id: %d Name: %s Salary: %s Department: %s\n",
                    emp.getId(),emp.getName(),emp.getSalary(),emp.getDepartment().getName());
        }

        //Майк устроился в этот отдел
        updateEmployer(empId,depId);

        //теперь в нем только он
        //проверяем
        List<Employer> employersByDepartment1 = getEmployersByDepartment(depId);
        for (Employer emp:employersByDepartment1){
            System.out.printf("Id: %d Name: %s Salary: %s Department: %s\n",
                    emp.getId(),emp.getName(),emp.getSalary(),emp.getDepartment().getName());
        }

        //появилась Лена и тоже устроилась в этот же отдел
        Employer employer1 = new Employer("Lena", 67890.89, null);
        Long empId2 = saveEmployer(employer1);
        updateEmployer(empId2,depId);

        //теперь в отделе два сотрудника
        //проверяем
        List<Employer> employersByDepartment2 = getEmployersByDepartment(depId);
        for (Employer emp:employersByDepartment2){
            System.out.printf("Id: %d Name: %s Salary: %s Department: %s\n",
                    emp.getId(),emp.getName(),emp.getSalary(),emp.getDepartment().getName());
        }

        //создали второй отдел и в него перевели Майка
        Department department2 = new Department("Отдел управления", new ArrayList<>());
        Long depId1 = saveDepartment(department2);
        updateEmployer(empId,depId1);

        //в старом отделе осталась только Лена
        //проверяем
        List<Employer> employersByDepartment3 = getEmployersByDepartment(depId);
        for (Employer emp:employersByDepartment3){
            System.out.printf("Id: %d Name: %s Salary: %s Department: %s\n",
                    emp.getId(),emp.getName(),emp.getSalary(),emp.getDepartment().getName());
        }

        //а в новом отделе только Майкл
        //проверяем
        List<Employer> employersByDepartment4 = getEmployersByDepartment(depId1);
        for (Employer emp:employersByDepartment4){
            System.out.printf("Id: %d Name: %s Salary: %s Department: %s\n",
                    emp.getId(),emp.getName(),emp.getSalary(),emp.getDepartment().getName());
        }
    }

    public static Long saveDepartment(Department department){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long depId = (Long) session.save(department);
        transaction.commit();
        session.close();
        return depId;
    }

    public static Long saveEmployer(Employer employer){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long empId= (Long) session.save(employer);
        transaction.commit();
        session.close();
        return empId;
    }

    public static void updateEmployer(Long empId,Long depId){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employer employer = session.get(Employer.class, empId);
        Department department = session.get(Department.class, depId);
        employer.setDepartment(department);
        session.update(employer);
        transaction.commit();
        session.close();
    }

    public static List<Employer> getEmployersByDepartment(Long depId){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Department department = session.get(Department.class, depId);
        List<Employer> employers = department.getEmployers();
        transaction.commit();
        session.close();
        return employers;
    }




}
