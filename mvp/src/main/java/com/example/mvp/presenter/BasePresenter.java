package com.example.mvp.presenter;

import com.example.mvp.model.BaseModel;
import com.example.mvp.model.IModel;
import com.example.mvp.view.IView;

import java.lang.ref.WeakReference;

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter{

    protected M mModel;
    protected WeakReference<V> mView;
    public BasePresenter(M mModel, V _V) {
        this.mModel = mModel;
        mView = new WeakReference<>(_V);
    }


    @Override
    public void destroy() {
        if(mModel!=null){
            mModel=null;
        }
    }
}
