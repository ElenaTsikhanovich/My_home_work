package utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
   /*  если используем настройку через xml-файл
   private static ClassPathXmlApplicationContext context;

    static {
        context= new ClassPathXmlApplicationContext("service.xml","daos.xml");
    }

    */

    private static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("config");

    public static ApplicationContext getContext() {
        return context;
    }

    public static void close(){
        context.close();
    }
}
