package Hibernate.simple.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AppSessionFactory {
    private static SessionFactory sessionFactory=buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        try {
            if(sessionFactory == null){
                StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                        .configure("hibernateSimple.cfg.xml").build();
                Metadata metadata = new MetadataSources(standardServiceRegistry)
                        .getMetadataBuilder().build();
                sessionFactory=metadata.getSessionFactoryBuilder().build();
            }
            return sessionFactory;
        }catch (Throwable throwable){
            throw new ExceptionInInitializerError(throwable);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void shutDown(){
        getSessionFactory().close();
    }
}
