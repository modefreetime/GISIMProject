package com.example.user.mvp.contract;

import com.example.mvp.model.BaseModel;
import com.example.mvp.model.IModel;
import com.example.mvp.view.IView;
import com.example.net.protocol.BaseRespEntity;
import com.example.user.mvp.entity.UserEntity;

import io.reactivex.Observable;

public interface RegContract {

    interface regiModel extends IModel {
        Observable<BaseRespEntity<UserEntity>> Reg(UserEntity entity);
    }

    interface regiView extends IView{
        UserEntity getEntity();
        void regSuccess();
    }


}
