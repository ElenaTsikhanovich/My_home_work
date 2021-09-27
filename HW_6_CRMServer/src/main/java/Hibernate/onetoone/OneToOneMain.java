package Hibernate.onetoone;

import Hibernate.onetoone.dto.Department;
import Hibernate.onetoone.dto.Employer;
import Hibernate.onetoone.utils.AppSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OneToOneMain {
    public static void main(String[] args) {

        Employer employer = new Employer("Mike", 67684.89, new Department("отдел маркетинга"));
        Employer employer1 = new Employer("Lena", 7675859.58, new Department("юридический отдел"));
        Employer employer2 = new Employer("Masha", 566768.89, new Department("отдел продаж"));
        Long empId = add(employer);
        Long empId1 = add(employer1);
        Long empId2 = add(employer2);

        Employer employer3 = get(empId);
        Employer employer4 = get(empId1);
        Employer employer5 = get(empId2);
        System.out.println(employer3.toString());
        System.out.println(employer4.toString());
        System.out.println(employer5.toString());


        update(empId,2L);
        Employer employer6 = get(empId);
        System.out.println(employer6.toString());

        List<Employer> all = getAll();
        for (Employer emp:all){
            System.out.println(emp.toString());
        }

        delete(empId2);
        List<Employer> all2 = getAll();
        for (Employer emp:all2){
            System.out.println(emp.toString());
        }


        AppSessionFactory.shutDown();
    }

    public static Long add(Employer employer){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employer.getDepartment());
        Long empId= (Long) session.save(employer);
        transaction.commit();
        session.close();
        return empId;
    }

    public static Employer get(Long id){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employer employer = session.get(Employer.class, id);
        transaction.commit();
        session.close();
        return employer;
    }

    public static void update(Long empId, Long depId){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Department department = session.get(Department.class, depId);
        Employer employer = session.get(Employer.class, empId);
        employer.setDepartment(department);
        session.update(employer);
        transaction.commit();
        session.close();
    }

    public static List<Employer> getAll(){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List from_employer = session.createQuery("FROM Employer").list();
        transaction.commit();
        session.close();
        return from_employer;
    }

    public static void delete(Long empId){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employer employer = session.get(Employer.class, empId);
        session.delete(employer);
        transaction.commit();
        session.close();
    }

}
