package com.example.msg.mvp.model;

import com.example.msg.mvp.api.MsgApi;
import com.example.msg.mvp.contract.MsgContract;
import com.example.msg.mvp.entity.msgEntity;
import com.example.mvp.model.BaseModel;
import com.example.net.RetrofitManager;
import com.example.net.protocol.BaseRespEntity;

import java.util.List;

import io.reactivex.Observable;

public class MsgModel extends BaseModel implements MsgContract.msgIModel {

    @Override
    public Observable<BaseRespEntity<List<msgEntity>>> getMsg(String u1, String u2, String page, String ps) {
        MsgApi msgApi = RetrofitManager.getInstance().create(MsgApi.class);
        io.reactivex.Observable<BaseRespEntity<List<msgEntity>>> observable = msgApi.get(u1, u2, page, ps);
        return observable;
    }
}
