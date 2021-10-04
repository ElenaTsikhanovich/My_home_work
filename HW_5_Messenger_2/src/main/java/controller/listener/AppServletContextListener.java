
package controller.listener;


import service.MessageService;
import service.UserService;
import storage.MessageStorageFactory;
import storage.StorageType;
import storage.UserStorageFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebListener
public class AppServletContextListener implements ServletContextListener {static {
    try {
        Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
        throw new IllegalStateException("ошибка загрузки драйвера", e);
    }
}



    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String user_storage = sce.getServletContext().getInitParameter("user_storage");
        String message_storage = sce.getServletContext().getInitParameter("message_storage");
        StorageType storageTypeUser = StorageType.valueOf(user_storage.toUpperCase());
        StorageType storageTypeMessage = StorageType.valueOf(message_storage.toUpperCase());
        UserStorageFactory.setType(storageTypeUser);
        MessageStorageFactory.setType(storageTypeMessage);

        String startDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyy в HH:mm"));
        sce.getServletContext().setAttribute("date",startDate);




    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

