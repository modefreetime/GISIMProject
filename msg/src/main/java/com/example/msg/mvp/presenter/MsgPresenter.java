package com.example.msg.mvp.presenter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.msg.mvp.contract.MsgContract;
import com.example.msg.mvp.entity.MsgListEntity;
import com.example.msg.mvp.entity.msgEntity;
import com.example.mvp.presenter.BasePresenter;
import com.example.net.protocol.BaseRespEntity;
import com.example.storage.core.FriendSql;
import com.example.storage.core.MsgSql;
import com.example.storage.core.utils.SharePreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MsgPresenter extends BasePresenter<MsgContract.msgIModel, MsgContract.msgIView> {
    public MsgPresenter(MsgContract.msgIModel mModel, MsgContract.msgIView _V) {
        super(mModel, _V);
    }

    public void getMsg() {

        HashMap<String, String> codeList = new HashMap<>();
        FriendSql friendSql = new FriendSql(mView.get().getMyContext(), "GSIM", null, 1);
        SQLiteDatabase writableDatabase = friendSql.getWritableDatabase();
        Cursor query = writableDatabase.query("friendtb", null, null, null, "usercode", null, null);
        while (query.moveToNext()) {
            String usercode = query.getString(query.getColumnIndex("usercode"));
            String username = query.getString(query.getColumnIndex("username"));
            codeList.put(usercode, username);
        }
        MsgSql msgSql = new MsgSql(mView.get().getMyContext(), "GSIM1", null, 1);
        SQLiteDatabase msgSqlWritableDatabase = msgSql.getWritableDatabase();
//        msgSqlWritableDatabase.delete("msgtb",null,null);
        Set<String> strings = codeList.keySet();
        for (String key : strings) {
            Observable<BaseRespEntity<List<msgEntity>>> ucode = mModel.getMsg((String) SharePreferenceUtils.get(mView.get().getMyContext(), "ucode", ""), key, "0", "10");
            ucode.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseRespEntity<List<msgEntity>>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseRespEntity<List<msgEntity>> listBaseRespEntity) {
                            List<msgEntity> data = listBaseRespEntity.getData();
                            for (int i = 0; i < data.size(); i++) {
                                msgEntity msgEntity = data.get(i);
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("fucode", msgEntity.getFromuser());
                                contentValues.put("funame", codeList.get(strings));
                                contentValues.put("tocode", msgEntity.getTouser());
                                contentValues.put("info", msgEntity.getMsg());
                                contentValues.put("sendtime", msgEntity.getMsgtime());
                                contentValues.put("type", msgEntity.getMsgtype());
                                msgSqlWritableDatabase.insert("msgtb", null, contentValues);
                            }
                            if (data.size() > 0) {
                                MsgListEntity entity = new MsgListEntity(codeList.get(key), data.get(data.size() - 1).getMsg(),key);
                                mView.get().initList(entity);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

}
