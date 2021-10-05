package storage.utils;

import storage.*;
import storage.api.IDepartmentStorage;
import storage.api.IEmployerStorage;
import storage.api.IPositionStorage;

public class StorageFactory {
    private static StorageType type;

    public synchronized static void setType(StorageType type) {
        if (type!=null){
            StorageFactory.type=type;
        }else {
            throw new IllegalStateException("Настройки хранилища нельзя изменить");
        }
    }

    public static IEmployerStorage getEmployerStorage() {
        if (type == null) {
            throw new IllegalStateException("Не заданы настройки хранилища");
        }
        switch (type) {
            case JDBC:
                return EmployerStorage.getInstance();
            case HIBERNATE:
                return EmployerHibernateStorage.getInstance();
            default:
                throw new IllegalStateException("Настройки не известны");
        }
    }

        public static IDepartmentStorage getDepartmentStorage() {
            if (type == null) {
                throw new IllegalStateException("Не заданы настройки хранилища");
            }
            switch (type) {
                case JDBC:
                    return DepartmentStorage.getInstance();
                case HIBERNATE:
                    return DepartmentHibernateStorage.getInstance();
                default:
                    throw new IllegalStateException("Настройки не известны");
            }
        }

            public static IPositionStorage getPositionStorage(){
                if(type==null){
                    throw new IllegalStateException("Не заданы настройки хранилища");
                }
                switch (type){
                    case JDBC:
                        return PositionStorage.getInstance();
                    case HIBERNATE:
                        return PositionHibernateStorage.getInstance();
                    default:
                        throw new IllegalStateException("Настройки не известны");
                }
    }
}
