package com.example.map.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.LifecycleOwner;

import com.example.map.R;
import com.example.map.mvp.view.PhonePeopleActivity;
import com.example.mvp.view.BaseFragment;

public class FindPeopleFragment extends BaseFragment {


    private TextView tvAddContract;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        tvAddContract = view.findViewById(R.id.tv_addContract);
    }

    @Override
    protected void initData() {
        tvAddContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PhonePeopleActivity.class));
            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected int getLayout() {
        return R.layout.find_people;
    }

    @Override
    public LifecycleOwner getlife() {
        return getActivity();
    }
}
