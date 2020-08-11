package com.example.common.async;

import java.util.concurrent.Executors;

public class CacheThreadPool {

    private static CacheThreadPool getInstance = new CacheThreadPool();

    private CacheThreadPool(){}

    public static CacheThreadPool getInstance(){
        return getInstance;
    }

    public void execute(Runnable runnable){
        Executors.newCachedThreadPool().submit(runnable);
    }

}
