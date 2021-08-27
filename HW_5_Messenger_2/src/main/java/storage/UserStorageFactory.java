package storage;

import storage.api.IUserStorage;

public class UserStorageFactory {

    private static StorageType type=null;

    public static StorageType getType() {
        return type;
    }

    public synchronized static void setType(StorageType type) {
        if(type!=null){
            UserStorageFactory.type = type;
        }else {
            throw new IllegalStateException("Тип хранилища нельзя изменить");
        }

    }

    public static IUserStorage getInstance(){
        if(type == null){
            throw new IllegalStateException("Не задан тип хранилища");
        }
        switch (type){
            case FILE:
                return FileUserStorage.getInstance();
            case MEMORY:
                return UserStorage.getInstance();
            default:
                throw new IllegalStateException("Тип хранилища неизвестен");
        }
    }



}
