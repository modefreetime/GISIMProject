package com.example.mvvm.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.mvvm.viewmodel.BaseViewModel;

/**
 * 基础的activity
 * @param <Binding>
 * @param <VM>
 */
public abstract class BaseActivity<Binding extends ViewDataBinding,VM extends BaseViewModel> extends AppCompatActivity {

    protected Binding binding;
    protected VM vm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        vm=createVm();
        initBinding();
    }

    /**
     * 创建viewmodel
     * @return
     */
    protected abstract VM createVm();

    /**
     * 设置布局id
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void initBinding();

    protected  void showMsg(Context context,String msg){
        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
    }

}
