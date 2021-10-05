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
    private SessionFactory sessionFactory;
    private static DepartmentHibernateStorage instance=new DepartmentHibernateStorage();

    private DepartmentHibernateStorage(){
        this.sessionFactory=AppHibernate.getSessionFactory();
    }

    public static DepartmentHibernateStorage getInstance() {
        return instance;
    }

    @Override
    public long add(Department department) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long id= (Long) session.save(department);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public Department get(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Department department = session.get(Department.class, id);
        session.getTransaction().commit();
        session.close();
        return department;
    }

    @Override
    public List<Department> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
        Root<Department> itemRoot = query.from(Department.class);
        CriteriaQuery<Department> select = query.select(itemRoot);
        Query<Department> query1 = session.createQuery(select);
        List<Department> resultList = query1.getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }
}
