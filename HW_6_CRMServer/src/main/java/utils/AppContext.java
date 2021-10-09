package utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
    private static ClassPathXmlApplicationContext context;

    static {
        context= new ClassPathXmlApplicationContext("service.xml","daos.xml");
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static void close(){
        context.close();
    }
}
