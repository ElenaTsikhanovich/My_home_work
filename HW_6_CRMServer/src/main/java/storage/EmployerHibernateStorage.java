package storage;

import model.Employer;
import model.dto.EmployerParamsDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import storage.api.IEmployerStorage;
import storage.utils.AppHibernate;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class EmployerHibernateStorage implements IEmployerStorage {
    private final SessionFactory sessionFactory;

    public EmployerHibernateStorage(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long add(Employer employer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Long id = (Long) session.save(employer);
            transaction.commit();
            return id;
        } catch (Exception e) {
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

    @Override
    public Employer get(Long id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employer employer = session.get(Employer.class, id);
            transaction.commit();
            return employer;
        }catch (Exception e){
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

    @Override
    public List<Employer> getAll() {
        return find(null);
    }


    @Override
    public List<Employer> getLimit(int limit, int offset) {
        EmployerParamsDTO employerParamsDTO = new EmployerParamsDTO();
        employerParamsDTO.setLimit(limit);
        employerParamsDTO.setOffset(offset);
        return find(employerParamsDTO);
    }

    @Override
    public long getCount() {
        return getCount(null);
    }

    @Override
    public List<Employer> find(EmployerParamsDTO employerParamsDTO) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employer> query = criteriaBuilder.createQuery(Employer.class);
        Root<Employer> itemRoot = query.from(Employer.class);
        CriteriaQuery<Employer> select = query.select(itemRoot);
        select.orderBy(criteriaBuilder.asc(itemRoot.get("id")));
        if(employerParamsDTO!=null) {
            Expression<Boolean> booleanExpression = generateWhere(employerParamsDTO, criteriaBuilder, itemRoot);
            if(booleanExpression!=null) {
                query.where(booleanExpression);
            }
        }
        Query<Employer> query1 = session.createQuery(query);
        setLimitOffset(employerParamsDTO,query1);
        List<Employer> resultList = query1.getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    @Override
    public Long getCount(EmployerParamsDTO employerParamsDTO) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Employer> itemRoot = query.from(Employer.class);
        query.select(criteriaBuilder.count(itemRoot));
        if(employerParamsDTO!=null) {
            Expression<Boolean> booleanExpression = generateWhere(employerParamsDTO, criteriaBuilder, itemRoot);
            if(booleanExpression!=null) {
                query.where(booleanExpression);
            }
        }
        Query<Long> query1 = session.createQuery(query);
        Long singleResult= query1.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return singleResult;
    }

    private void setLimitOffset(EmployerParamsDTO employerParamsDTO,Query<?> query ){
        if(employerParamsDTO!=null){
            Integer offset = employerParamsDTO.getOffset();
            Integer limit = employerParamsDTO.getLimit();
            if(offset!=null) {
                query.setFirstResult(offset);
            }
            if(limit!=null){
                query.setMaxResults(limit);
            }
        }
    }


    private Expression<Boolean> generateWhere(EmployerParamsDTO employerParamsDTO,
                                             CriteriaBuilder criteriaBuilder, Root<Employer> itemRoot){
        List<Predicate>predicates=new ArrayList<>();
        if(employerParamsDTO.getName()!=null && !employerParamsDTO.getName().isEmpty()){
            predicates.add(criteriaBuilder.like(itemRoot.get("name"), "%" + employerParamsDTO.getName() + "%"));
        }
        if(employerParamsDTO.getSalaryFrom()!=null && employerParamsDTO.getSalaryTo()!=null){
            predicates.add(criteriaBuilder.between(
                    itemRoot.get("salary"), employerParamsDTO.getSalaryFrom(), employerParamsDTO.getSalaryTo()));
        }else if(employerParamsDTO.getSalaryFrom()==null && employerParamsDTO.getSalaryTo()!=null){
            predicates.add(criteriaBuilder.le(itemRoot.get("salary"),employerParamsDTO.getSalaryTo()));

        }else if(employerParamsDTO.getSalaryTo()==null && employerParamsDTO.getSalaryFrom()!=null){
            predicates.add(criteriaBuilder.ge(itemRoot.get("salary"),employerParamsDTO.getSalaryFrom()));
        }
        if(predicates.size()==0){
            return null;
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }

}
