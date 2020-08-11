package com.example.user.mvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvp.view.BaseActivity;
import com.example.router.RouterManager;
import com.example.router.RouterPath;
import com.example.storage.core.utils.SharePreferenceUtils;
import com.example.user.R;
import com.example.user.mvp.contract.LoginContract;
import com.example.user.mvp.entity.UserEntity;
import com.example.user.mvp.model.LoginModel;
import com.example.user.mvp.presenter.LoginPresenter;
import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

@Route(path = RouterPath.USER_LOGIN)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.loginView {


    private android.widget.EditText edtLoginUname;
    private android.widget.EditText edtLoginPwd;
    private android.widget.Button btnLogin;
    private android.widget.Button btnQq;

    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter(new LoginModel(), this);
    }

    @Override
    protected void initView() {
        MobSDK.submitPolicyGrantResult(true, null);
        edtLoginUname = findViewById(R.id.edt_login_uname);
        edtLoginPwd = findViewById(R.id.edt_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnQq = findViewById(R.id.btn_qq);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.login();
            }
        });
        btnQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Platform plat = ShareSDK.getPlatform(QQ.NAME);
//移除授权状态和本地缓存，下次授权会重新授权
                plat.removeAccount(true);
//SSO授权，传false默认是客户端授权
                plat.SSOSetting(false);
//授权回调监听，监听oncomplete，onerror，oncancel三种状态
                plat.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
//抖音登录适配安卓9.0
//ShareSDK.setActivity(MainActivity.this);
//要数据不要功能，主要体现在不会重复出现授权界面
                plat.showUser(null);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public UserEntity getEntity() {
        UserEntity entity = new UserEntity();
        entity.setUsername(edtLoginUname.getText().toString());
        entity.setPwd(edtLoginPwd.getText().toString());
        return entity;
    }

    @Override
    public void loginSuccess(String code) {
        SharePreferenceUtils.put(getApplication(),"uname",edtLoginUname.getText().toString());
        SharePreferenceUtils.put(getApplication(),"ucode",code);
        RouterManager.getInstance().route(RouterPath.Main);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showMsg("登录成功"+code);
            }
        });
    }

    @Override
    public LifecycleOwner getlife() {
        return this;
    }
}
