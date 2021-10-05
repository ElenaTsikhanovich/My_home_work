package storage;

import model.Employer;
import model.dto.EmployerParamsDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import storage.api.IEmployerStorage;
import storage.utils.AppHibernate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployerHibernateStorage implements IEmployerStorage {
    private SessionFactory sessionFactory;
    private static EmployerHibernateStorage instance=new EmployerHibernateStorage();

    private EmployerHibernateStorage(){
        this.sessionFactory=AppHibernate.getSessionFactory();
    }

    public static EmployerHibernateStorage getInstance() {
        return instance;
    }

    @Override
    public long add(Employer employer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id= (Long) session.save(employer);
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public Employer get(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Employer employer = session.get(Employer.class, id);
        transaction.commit();
        session.close();
        return employer;
    }

    @Override
    public List<Employer> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = AppHibernate.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employer> query = criteriaBuilder.createQuery(Employer.class);
        Root<Employer> itemRoot = query.from(Employer.class);
        CriteriaQuery<Employer> select = query.select(itemRoot);
        Query<Employer> query1 = session.createQuery(select);
        List<Employer> resultList = query1.getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    @Override
    public List<Employer> getLimit(int limit, int offset) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employer> query = criteriaBuilder.createQuery(Employer.class);
        Root<Employer> itemRoot = query.from(Employer.class);
        CriteriaQuery<Employer> select = query.select(itemRoot);
        Query<Employer> query1 = session.createQuery(select);
        query1.setFirstResult(offset);
        query1.setMaxResults(limit);
        List<Employer> resultList1 = query1.getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList1;
    }

    @Override
    public long getCount() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder =sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Employer> itemRoot = query.from(Employer.class);
        CriteriaQuery<Long> select = query.select(criteriaBuilder.count(itemRoot));
        Query<Long> query1 = session.createQuery(select);
        Long singleResult = query1.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return singleResult;
    }

    @Override
    public List<Employer> find(EmployerParamsDTO employerParamsDTO) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employer> query = criteriaBuilder.createQuery(Employer.class);
        Root<Employer> itemRoot = query.from(Employer.class);
        CriteriaQuery<Employer> where = query.where(criteriaBuilder.and(
                criteriaBuilder.like(itemRoot.get("name"), "%" + employerParamsDTO.getName() + "%"),
                criteriaBuilder.between(
                        itemRoot.get("salary"), employerParamsDTO.getSalaryFrom(), employerParamsDTO.getSalaryTo())));
        Query<Employer> queryOne = session.createQuery(where);
        Integer offset = employerParamsDTO.getOffset();
        Integer limit = employerParamsDTO.getLimit();
        queryOne.setFirstResult(offset);
        queryOne.setMaxResults(limit);
        List<Employer> resultList = queryOne.getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    @Override
    public Long getCountFromFind(EmployerParamsDTO employerParamsDTO) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Employer> itemRoot = query.from(Employer.class);
        CriteriaQuery<Long> where = query.select(criteriaBuilder.count(itemRoot)).where(criteriaBuilder.and(
                criteriaBuilder.like(itemRoot.get("name"), "%" + employerParamsDTO.getName() + "%"),
                criteriaBuilder.between(
                        itemRoot.get("salary"), employerParamsDTO.getSalaryFrom(), employerParamsDTO.getSalaryTo())));
        Query<Long> query1 = session.createQuery(where);
        Long singleResult = query1.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return singleResult;
    }
}
