package controller.listener;

import service.MessageService;
import service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String storage = sce.getServletContext().getInitParameter("storage");
        UserService.getInstance().storageChoose(storage);
        MessageService.getInstance().storageChoose(storage);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
