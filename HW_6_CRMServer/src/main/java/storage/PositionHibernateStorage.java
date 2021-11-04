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
    private final SessionFactory sessionFactory;

    public PositionHibernateStorage(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long add(Position position) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Long id= (Long) session.save(position);
            session.getTransaction().commit();
            return id;
        }catch (Exception e){
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }


    @Override
    public Position get(Long id) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Position position = session.get(Position.class, id);
            session.getTransaction().commit();
            return position;
        }catch (Exception e){
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }


    @Override
    public List<Position> getAll() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
            CriteriaQuery<Position> query = criteriaBuilder.createQuery(Position.class);
            Root<Position> itemRoot = query.from(Position.class);
            CriteriaQuery<Position> select = query.select(itemRoot);
            Query<Position> query1 = session.createQuery(select);
            List<Position> resultList = query1.getResultList();
            session.getTransaction().commit();
            return resultList;
        }catch (Exception e){
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }
}
