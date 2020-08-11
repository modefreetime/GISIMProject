package com.example.user.mvp.presenter;

import com.baweigame.xmpplibrary.XmppManager;
import com.example.common.LogUtils;
import com.example.common.async.CacheThreadPool;
import com.example.mvp.presenter.BasePresenter;
import com.example.net.protocol.BaseRespEntity;
import com.example.net.rx.BaseObservable;
import com.example.net.rx.BaseObserver;
import com.example.user.mvp.contract.LoginContract;
import com.example.user.mvp.contract.RegContract;
import com.example.user.mvp.entity.UserEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.loginModel, LoginContract.loginView> {


    public LoginPresenter(LoginContract.loginModel mModel, LoginContract.loginView _V) {
        super(mModel, _V);
    }

    public void login() {
        UserEntity entity = mView.get().getEntity();
        Observable<BaseRespEntity<UserEntity>> login = mModel.login(entity);
//        BaseObservable.doObservable(login, new BaseObserver() {
//
//            @Override
//            public void onNext(Object o) {
//                super.onNext(o);
//                CacheThreadPool.getInstance().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        boolean success = XmppManager.getInstance().getXmppUserManager().login(entity.getUsername(), entity.getPwd());
//                        LogUtils.d(""+success);
//                        //双重判断
//                        if(success) {
//                            mView.get().loginSuccess();
//                        }
//                    }
//                });
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//            }
//        }, mView.get().getlife());
        Observable<BaseRespEntity<UserEntity>> ImOb = Observable.create(new ObservableOnSubscribe<BaseRespEntity<UserEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseRespEntity<UserEntity>> emitter) throws Exception {
                CacheThreadPool.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        boolean success = XmppManager.getInstance().getXmppUserManager().login(entity.getUsername(), entity.getPwd());
                        LogUtils.d("" + success);
                        //双重判断
                        if (success) {
                            emitter.onComplete();
                        } else {
                            emitter.onError(new Throwable("IM登录失败"));
                        }
                    }
                });
            }
        });
        Observable.merge(login, ImOb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseRespEntity<UserEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseRespEntity<UserEntity> o) {
                        if (o != null && o.getData() != null) {
                            if (o.getData().getUsercode() != null) {
                                mView.get().loginSuccess(o.getData().getUsercode());
                            }
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
