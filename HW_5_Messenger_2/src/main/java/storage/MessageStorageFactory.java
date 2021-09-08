package storage;

import storage.api.IMessageStorage;

public class MessageStorageFactory {
    private static StorageType type=null;

    public static StorageType getType() {
        return type;
    }

    public static void setType(StorageType type) {
        if(type!=null){
            MessageStorageFactory.type = type;
        }else {
            throw new IllegalStateException("Тип хранилища нельзя изменить");
        }
    }

    public static IMessageStorage getInstance(){
        if (type==null){
            throw new IllegalStateException("Не задан тип хранилища");
        }
        switch (type){
            case FILE:
                return FileMessageStorage.getInstance();
            case MEMORY:
                return MessageStorage.getInstance();
            case DATABASE:
                return DataBaseMessageStorage.getInstance();
            default:
                throw new IllegalStateException("Тип хранилища неизвестен");
        }
    }
}
