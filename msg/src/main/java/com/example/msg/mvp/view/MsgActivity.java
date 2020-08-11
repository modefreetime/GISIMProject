package com.example.msg.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.msg.R;
import com.example.msg.mvp.adapter.MsgListAdapter;
import com.example.msg.mvp.contract.MsgContract;
import com.example.msg.mvp.entity.MsgListEntity;
import com.example.msg.mvp.model.MsgModel;
import com.example.msg.mvp.presenter.MsgPresenter;
import com.example.mvp.view.BaseActivity;
import com.example.router.RouterPath;
import com.example.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.MSG)
public class MsgActivity extends BaseActivity<MsgPresenter> implements MsgContract.msgIView {

    private MsgListAdapter adapter;
    private RecyclerView rvMsgList;
    private List<MsgListEntity> list = new ArrayList<>();

    @Override
    protected void initPresenter() {
        mPresenter = new MsgPresenter(new MsgModel(), this);
    }

    @Override
    protected void initView() {

        rvMsgList = findViewById(R.id.rv_msg_list);
        adapter = new MsgListAdapter(R.layout.msg_item, list);
        rvMsgList.setLayoutManager(new LinearLayoutManager(this));
        rvMsgList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MsgActivity.this, "a", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MsgActivity.this, ChatActivity.class);
                intent.putExtra("code",list.get(position).getCode());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getMsg();

    }

    @Override
    protected void initEvent() {
      
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_msg;
    }

    @Override
    public LifecycleOwner getlife() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void initList(MsgListEntity entity) {
        list.add(entity);
        adapter.notifyDataSetChanged();
    }
}
