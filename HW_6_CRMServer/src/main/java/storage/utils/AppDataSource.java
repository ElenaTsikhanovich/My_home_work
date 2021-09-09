package storage.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;

public class AppDataSource {
    private final static AppDataSource instance=new AppDataSource();

    private static ComboPooledDataSource comboPooledDataSource;

    public ComboPooledDataSource getComboPooledDataSource() {
        return comboPooledDataSource;
    }

    public static AppDataSource getInstance() {
        return instance;
    }

    private AppDataSource(){
        getConnection();
    }

    private void getConnection(){
        comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e){
            throw new IllegalStateException("Ошибка загрузки драйвера");
        }
        comboPooledDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/crm");
        comboPooledDataSource.setUser("postgres");
        comboPooledDataSource.setPassword("6780911");
    }

}
