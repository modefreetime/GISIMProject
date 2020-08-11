package com.example.user.mvp.presenter;

import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.impl.DefaultXmppUserImpl;
import com.example.common.LogUtils;
import com.example.common.async.CacheThreadPool;
import com.example.mvp.presenter.BasePresenter;
import com.example.net.protocol.BaseRespEntity;
import com.example.net.rx.BaseObservable;
import com.example.net.rx.BaseObserver;
import com.example.user.mvp.contract.RegContract;
import com.example.user.mvp.entity.UserEntity;

import io.reactivex.Observable;

public class RegPresenter extends BasePresenter<RegContract.regiModel, RegContract.regiView> {
    public RegPresenter(RegContract.regiModel mModel, RegContract.regiView mView) {
        super(mModel, mView);
    }

    public void reg() {
        UserEntity entity = mView.get().getEntity();
        Observable<BaseRespEntity<UserEntity>> reg = mModel.Reg(entity);

        BaseObservable.doObservable(reg, new BaseObserver() {
            @Override
            public void onNext(Object o) {
                super.onNext(o);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        CacheThreadPool.getInstance().execute(new Runnable() {
                            @Override
                            public void run() {
                                XmppManager instance = XmppManager.getInstance();
                                instance.getXmppUserManager().createAccount(entity.getUsername(), entity.getPwd());
                            }
                        });
                    }
                }.start();
                mView.get().regSuccess();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LogUtils.d("失败");
            }
        }, mView.get().getlife());
    }

}
