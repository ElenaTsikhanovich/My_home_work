package Hibernate.simple;

import Hibernate.simple.dto.Employer;
import Hibernate.simple.utils.AppSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class SimpleMain {
    public static void main(String[] args) {
        Employer lena = new Employer("Lena", 57687.99);
        Employer mike = new Employer("Mike", 67834.77);
        Long lenaId = add(lena);
        Long mikeId = add(mike);
        Employer employer1 = get(lenaId);
        Employer employer2 = get(mikeId);
        System.out.println(employer1.toString());
        System.out.println(employer2.toString());


        update(lenaId,"Alena");
        Employer employer = get(lenaId);
        System.out.println(employer.toString());

        delete(lenaId);

        List<Employer> all = getAll();
        for (Employer emp:all){
            System.out.println(emp.toString());
        }


        AppSessionFactory.shutDown();
    }
    public static Long add(Employer employer){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(employer);
        transaction.commit();
        session.close();
        return id;
    }

    public static void update(Long id,String name){
        Session session = AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employer employer = session.get(Employer.class, id);
        employer.setName(name);
        session.update(employer);
        transaction.commit();
        session.close();
    }

    public static Employer get(Long id){
        Session session=AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Employer employer = session.get(Employer.class, id);
        transaction.commit();
        session.close();
        return employer;
    }

    public static List<Employer> getAll(){
         Session session = AppSessionFactory.getSessionFactory().openSession();
         Transaction transaction = session.beginTransaction();
         List from_employer = session.createQuery("FROM Employer").list();
         transaction.commit();
         session.close();
         return from_employer;
    }

    public static void delete(Long id){
        Session session=AppSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employer employer = session.get(Employer.class, id);
        session.delete(employer);
        transaction.commit();
        session.close();

    }
}
