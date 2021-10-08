package utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
    private static ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("service.xml","daos.xml");

    public static ClassPathXmlApplicationContext getContext() {
        return context;
    }
}
