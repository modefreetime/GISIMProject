package com.example.map.mvp.presenter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.common.LogUtils;
import com.example.map.mvp.contract.FriendContract;
import com.example.map.mvp.entity.PeopleEntity;
import com.example.mvp.presenter.BasePresenter;
import com.example.net.protocol.BaseRespEntity;
import com.example.net.rx.BaseObservable;
import com.example.net.rx.BaseObserver;
import com.example.storage.core.FriendSql;
import com.example.storage.core.utils.SharePreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FriendPresenter extends BasePresenter<FriendContract.FriendModel,FriendContract.FriendView> {

    public FriendPresenter(FriendContract.FriendModel mModel, FriendContract.FriendView _V) {
        super(mModel, _V);
    }

    public void getFriend(){
        LogUtils.d("a");
        FriendSql gsim = new FriendSql(mView.get().getMyContext(), "GSIM", null, 1);
        Observable<BaseRespEntity<List<PeopleEntity>>> ucode = mModel.getFriend((String) SharePreferenceUtils.get(mView.get().getMyContext(), "ucode", ""));
        BaseObservable.doObservable(ucode,new BaseObserver<BaseRespEntity<List<PeopleEntity>>>(){
            @Override
            public void onNext(BaseRespEntity<List<PeopleEntity>> peopleEntities) {
                super.onNext(peopleEntities);
                SQLiteDatabase database = gsim.getWritableDatabase();
                List<PeopleEntity> data = peopleEntities.getData();
                for (int i = 0; i < data.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put("usercode",data.get(i).getUsercode());
                    values.put("username",data.get(i).getUsername());
                    database.insert("friendtb",null,values);
                }
                mView.get().initRv(peopleEntities.getData());
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        },mView.get().getlife());
    }

}
