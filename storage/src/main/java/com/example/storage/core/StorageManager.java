package com.example.storage.core;

import com.example.storage.core.impl.FileStorage;
import com.example.storage.core.impl.SPStorage;

public class StorageManager {
    private static StorageManager instance=new StorageManager();
    private StorageManager(){
        storage=new SPStorage();
    }
    public static StorageManager getInstance(){
        return instance;
    }

    private IStorage storage;

    /**
     * 切换存储工具类型
     * @param storageType
     */
    public void init(int storageType){
        if (storageType== StorageType.SP){
            storage=new SPStorage();
        }else if (storageType== StorageType.FILE){
            storage=new FileStorage();
        }
    }

    /**
     * 存储
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean save(String key, T value) {
        return storage.save(key,value);
    }

    /**
     * 获取
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key) {
        return storage.get(key);
    }

}
