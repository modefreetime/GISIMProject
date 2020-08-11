package com.example.map.mvp.contract;

import android.content.Context;

import com.example.map.mvp.entity.PeopleEntity;
import com.example.mvp.model.IModel;
import com.example.mvp.view.IView;
import com.example.net.protocol.BaseRespEntity;

import java.util.List;

import io.reactivex.Observable;

public interface FriendContract {

    interface FriendModel extends IModel{
        Observable<BaseRespEntity<List<PeopleEntity>>> getFriend(String code);
    }

    interface FriendView extends IView{
        Context getMyContext();
        void initRv(List<PeopleEntity> peopleEntities);
    }

}
