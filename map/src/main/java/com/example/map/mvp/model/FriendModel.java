package com.example.map.mvp.model;

import com.example.map.mvp.api.ContractApi;
import com.example.map.mvp.contract.FriendContract;
import com.example.map.mvp.entity.PeopleEntity;
import com.example.mvp.model.BaseModel;
import com.example.net.RetrofitManager;
import com.example.net.protocol.BaseRespEntity;

import java.util.List;

import io.reactivex.Observable;

public class FriendModel extends BaseModel implements FriendContract.FriendModel {

    @Override
    public Observable<BaseRespEntity<List<PeopleEntity>>> getFriend(String code){
        ContractApi contractApi = RetrofitManager.getInstance().create(ContractApi.class);
        Observable<BaseRespEntity<List<PeopleEntity>>> observable = contractApi.get(code);
        return observable;
    }

}
