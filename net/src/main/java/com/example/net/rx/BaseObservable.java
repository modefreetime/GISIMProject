package com.example.net.rx;

import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseObservable  {
    public static  <T> void doObservable(Observable<T> tObservable, BaseObserver observer, LifecycleOwner lifecycle){
        tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
                .subscribe(observer);
    }
}
