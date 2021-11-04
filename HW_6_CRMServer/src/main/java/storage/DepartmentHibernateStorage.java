package storage;

import model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import storage.api.IDepartmentStorage;
import storage.utils.AppHibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DepartmentHibernateStorage implements IDepartmentStorage {
    private final SessionFactory sessionFactory;

    public DepartmentHibernateStorage(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long add(Department department) {
        try ( Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Long id = (Long) session.save(department);
            session.getTransaction().commit();
            return id;
        }catch (Exception e){
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }


    @Override
    public Department get(Long id) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Department department = session.get(Department.class, id);
            session.getTransaction().commit();
            return department;
        }catch (Exception e){
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }


    @Override
    public List<Department> getAll() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
            CriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
            Root<Department> itemRoot = query.from(Department.class);
            CriteriaQuery<Department> select = query.select(itemRoot);
            Query<Department> query1 = session.createQuery(select);
            List<Department> resultList = query1.getResultList();
            session.getTransaction().commit();
            return resultList;

        }catch (Exception e){
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

}


