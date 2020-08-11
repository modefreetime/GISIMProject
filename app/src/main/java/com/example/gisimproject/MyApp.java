package com.example.gisimproject;

import androidx.multidex.MultiDex;

import com.example.common.app.BaseAppcation;
import com.example.router.RouterManager;

public class MyApp extends BaseAppcation {
    @Override
    protected void initOtherConfig() {
        RouterManager.getInstance().init(this,true);
        MultiDex.install(this);
    }
}
