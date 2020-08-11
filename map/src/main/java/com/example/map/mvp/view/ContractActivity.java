package com.example.map.mvp.view;

import androidx.lifecycle.LifecycleOwner;

import android.content.Intent;
import android.view.View;

import com.example.map.R;
import com.example.map.adapter.ContractVpAdapter;
import com.example.mvp.view.BaseActivity;
import com.example.widget.TitleBar;

public class ContractActivity extends BaseActivity {

    private TitleBar tbContract;
    private android.widget.RelativeLayout rvContract;
    private com.google.android.material.tabs.TabLayout tlContract;
    private androidx.viewpager.widget.ViewPager vpContract;
    private ContractVpAdapter adapter;
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        tbContract = findViewById(R.id.tb_contract);
        rvContract = findViewById(R.id.rv_Contract);
        tlContract = findViewById(R.id.tl_contract);
        vpContract = findViewById(R.id.vp_contract);
        adapter = new ContractVpAdapter(getSupportFragmentManager());
        vpContract.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        tbContract.setTitleBarClickListener(new TitleBar.TitleBarClickListener() {
            @Override
            public void leftClick(View view) {

            }

            @Override
            public void rightClick(View view) {
                startActivity(new Intent(ContractActivity.this,AddContractActivity.class));
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_contract;
    }

    @Override
    public LifecycleOwner getlife() {
        return this;
    }
}
