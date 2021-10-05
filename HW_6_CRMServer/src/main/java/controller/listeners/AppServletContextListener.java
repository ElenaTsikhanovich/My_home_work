package controller.listeners;

import storage.utils.AppHibernate;
import storage.utils.StorageFactory;
import storage.utils.StorageType;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String storage = sce.getServletContext().getInitParameter("storage");
        StorageType storageType = StorageType.valueOf(storage.toUpperCase());
        StorageFactory.setType(storageType);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        AppHibernate.shutDown();
    }
}
