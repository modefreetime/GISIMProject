package com.example.mvp.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

public abstract class BaseActivity<P> extends AppCompatActivity implements IView {

    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
//        ARouter.getInstance().inject(this);

        initView();
        initPresenter();
        initData();
        initEvent();
    }

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    protected abstract int getLayout();

    public void showMsg(String msg){
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
