
package controller.listener;


import service.MessageService;
import service.UserService;

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
        UserService.getInstance().storageChoose(storage);
        MessageService.getInstance().storageChoose(storage);

        String startDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyy в HH:mm"));
        sce.getServletContext().setAttribute("date",startDate);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

