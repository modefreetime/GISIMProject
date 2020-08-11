package com.example.user.mvp.view;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvp.view.BaseActivity;
import com.example.router.RouterPath;
import com.example.user.R;
import com.example.user.mvp.contract.RegContract;
import com.example.user.mvp.entity.UserEntity;
import com.example.user.mvp.model.RegModel;
import com.example.user.mvp.presenter.RegPresenter;

import java.util.logging.Handler;

@Route(path = RouterPath.USER_Reg)
public class RegiActivity extends BaseActivity<RegPresenter> implements RegContract.regiView ,LifecycleOwner{

    private android.widget.EditText edtRegUname;
    private android.widget.EditText edtRegPwd;
    private android.widget.Button btnReg;

    @Override
    protected void initPresenter() {
        mPresenter = new RegPresenter(new RegModel(),this);
    }

    @Override
    protected void initView() {

        edtRegUname = findViewById(R.id.edt_reg_uname);
        edtRegPwd = findViewById(R.id.edt_reg_pwd);
        btnReg = findViewById(R.id.btn_reg);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.reg();
            }
        });
    }

    @Override
    protected int getLayout() {

        return R.layout.activity_reg;
    }

    @Override
    public UserEntity getEntity() {
        UserEntity entity = new UserEntity();
        entity.setPwd(edtRegPwd.getText().toString());
        entity.setUsername(edtRegUname.getText().toString());
        return entity;
    }

    @Override
    public void regSuccess() {
        showMsg("注册成功,登录页面");
        startActivity(new Intent(this,LoginActivity.class));

    }

    @Override
    public LifecycleOwner getlife() {
        return this;
    }
}
