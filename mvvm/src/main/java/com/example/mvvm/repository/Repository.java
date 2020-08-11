package com.example.mvvm.repository;

import androidx.lifecycle.LifecycleOwner;

import com.example.mvvm.model.IModel;

public abstract class Repository<T extends IModel> {

    protected T mModel;
    protected LifecycleOwner owner;
    public Repository(LifecycleOwner _owner){
        mModel=createModel();
        owner=_owner;
    }

    /**
     * 创建业务model
     * @return
     */
    protected abstract T createModel();

}
