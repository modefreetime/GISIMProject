package com.example.map.mvp.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.map.R;
import com.example.map.adapter.addContractVpAdater;
import com.example.widget.TitleBar;
import com.google.android.material.tabs.TabLayout;

public class AddContractActivity extends AppCompatActivity {

    private TitleBar tbAddcontract;
    private TabLayout tlAdd;
    private ViewPager vpAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contract);
        initView();
        initEvent();
    }

    private void initEvent() {
        tbAddcontract.setTitleBarClickListener(new TitleBar.TitleBarClickListener() {
            @Override
            public void leftClick(View view) {
                finish();
            }

            @Override
            public void rightClick(View view) {

            }
        });
    }

    private void initView() {
        tbAddcontract = findViewById(R.id.tb_addcontract);
        tlAdd = findViewById(R.id.tl_add);
        vpAdd = findViewById(R.id.vp_add);
        addContractVpAdater adater = new addContractVpAdater(getSupportFragmentManager());
        vpAdd.setAdapter(adater);
    }
}
