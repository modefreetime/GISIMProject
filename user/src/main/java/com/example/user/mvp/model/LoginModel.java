package com.example.user.mvp.model;

import com.example.mvp.model.BaseModel;
import com.example.net.RetrofitManager;
import com.example.net.protocol.BaseRespEntity;
import com.example.user.mvp.api.UserCenterApi;
import com.example.user.mvp.contract.LoginContract;
import com.example.user.mvp.contract.RegContract;
import com.example.user.mvp.entity.UserEntity;

import io.reactivex.Observable;

public class LoginModel extends BaseModel implements LoginContract.loginModel {

    @Override
    public Observable<BaseRespEntity<UserEntity>> login(UserEntity entity) {
        RetrofitManager manager = RetrofitManager.getInstance();
        UserCenterApi centerApi = manager.create(UserCenterApi.class);
        Observable<BaseRespEntity<UserEntity>> userEntityBaseRespEntity = centerApi.login(entity);
        return userEntityBaseRespEntity;
    }
}
