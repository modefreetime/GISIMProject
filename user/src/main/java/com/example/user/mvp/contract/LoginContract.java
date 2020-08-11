package com.example.user.mvp.contract;

import com.example.mvp.model.IModel;
import com.example.mvp.view.IView;
import com.example.net.protocol.BaseRespEntity;
import com.example.user.mvp.entity.UserEntity;

import io.reactivex.Observable;

public interface LoginContract {

    interface loginModel extends IModel {
        Observable<BaseRespEntity<UserEntity>> login(UserEntity entity);
    }

    interface loginView extends IView{
        UserEntity getEntity();
        void loginSuccess(String ucode);
    }


}
