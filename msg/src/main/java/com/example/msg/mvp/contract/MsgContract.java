package com.example.msg.mvp.contract;


import android.content.Context;

import com.example.msg.mvp.entity.MsgListEntity;
import com.example.msg.mvp.entity.msgEntity;
import com.example.mvp.model.IModel;
import com.example.mvp.view.IView;
import com.example.net.protocol.BaseRespEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public interface MsgContract {

    interface msgIView extends IView{
        Context getMyContext();

        void initList(MsgListEntity entity);
    }

    interface msgIModel extends IModel{
        Observable<BaseRespEntity<List<msgEntity>>> getMsg(String u1, String u2, String page, String ps);
    }

}
