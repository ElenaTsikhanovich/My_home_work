package storage.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class AppDataSource {
    private static AppDataSource instance;
    private ComboPooledDataSource comboPooledDataSource;

    private AppDataSource() {
        comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new IllegalStateException("Ошибка загрузки драйвера");
        }
        comboPooledDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/crm");
        comboPooledDataSource.setUser("postgres");
        comboPooledDataSource.setPassword("6780911");
    }

    public static DataSource getInstance() {
        if(instance==null){
            synchronized (AppDataSource.class){
                instance=new AppDataSource();
            }
        }
        return instance.comboPooledDataSource;
    }
}
