
package controller.listener;


import service.MessageService;
import service.UserService;
import storage.MessageStorageFactory;
import storage.StorageType;
import storage.UserStorageFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebListener
public class AppServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String storage = sce.getServletContext().getInitParameter("storage");
        StorageType storageType = StorageType.valueOf(storage.toUpperCase());
        UserStorageFactory.setType(storageType);
        MessageStorageFactory.setType(storageType);

        String startDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyy в HH:mm"));
        sce.getServletContext().setAttribute("date",startDate);




    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

