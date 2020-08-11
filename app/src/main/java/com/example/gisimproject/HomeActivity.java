package com.example.gisimproject;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.router.RouterManager;
import com.example.router.RouterPath;

public class HomeActivity extends AppCompatActivity {

    private Button btnHomeReg;
    private Button btnHomeLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initEvent();
        ApplicationInfo info = getApplicationInfo();

//        enabledStrictMode();
//
    }

    private void enabledStrictMode() {
        //开启Thread策略模式
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork()//监测主线程使用网络io
                .detectCustomSlowCalls()//监测自定义运行缓慢函数
                .detectDiskReads() // 检测在UI线程读磁盘操作
                .detectDiskWrites() // 检测在UI线程写磁盘操作
                .penaltyLog() //写入日志
                .penaltyDialog()//监测到上述状况时弹出对话框
                .build());
        //开启VM策略模式
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()//监测sqlite泄露
                .detectLeakedClosableObjects()//监测没有关闭IO对象
                .setClassInstanceLimit(MainActivity.class, 1) // 设置某个类的同时处于内存中的实例上限，可以协助检查内存泄露
                .detectActivityLeaks()
                .penaltyLog()//写入日志
                .penaltyDeath()//出现上述情况异常终止
                .build());
    }

    private void initEvent() {
        btnHomeReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RouterManager.getInstance().route(RouterPath.USER_Reg);
            }
        });
        btnHomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RouterManager.getInstance().route(RouterPath.USER_LOGIN);
            }
        });
    }

    private void initView() {
        btnHomeReg = findViewById(R.id.btn_home_reg);
        btnHomeLogin = findViewById(R.id.btn_home_login);
    }
}
