package storage;

import model.Employer;
import model.Position;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import storage.api.IPositionStorage;
import storage.utils.AppHibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PositionHibernateStorage implements IPositionStorage {
    private SessionFactory sessionFactory;
    private static PositionHibernateStorage instance=new PositionHibernateStorage();

    private PositionHibernateStorage(){
        this.sessionFactory=AppHibernate.getSessionFactory();
    }

    public static PositionHibernateStorage getInstance() {
        return instance;
    }

    @Override
    public long add(Position position) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long id= (Long) session.save(position);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public Position get(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Position position = session.get(Position.class, id);
        session.getTransaction().commit();
        session.close();
        return position;
    }

    @Override
    public List<Position> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Position> query = criteriaBuilder.createQuery(Position.class);
        Root<Position> itemRoot = query.from(Position.class);
        CriteriaQuery<Position> select = query.select(itemRoot);
        Query<Position> query1 = session.createQuery(select);
        List<Position> resultList = query1.getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }
}
