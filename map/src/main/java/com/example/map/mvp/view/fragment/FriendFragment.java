package com.example.map.mvp.view.fragment;

import android.content.Context;
import android.widget.RelativeLayout;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.LogUtils;
import com.example.map.R;
import com.example.map.adapter.FriendRVAdapter;
import com.example.map.mvp.contract.FriendContract;
import com.example.map.mvp.entity.PeopleEntity;
import com.example.map.mvp.model.FriendModel;
import com.example.map.mvp.presenter.FriendPresenter;
import com.example.mvp.view.BaseFragment;

import java.util.List;

public class FriendFragment extends BaseFragment<FriendPresenter> implements FriendContract.FriendView {
    private RecyclerView rvFriend;
    private RecyclerView rvFriendWord;
    private FriendRVAdapter adapter;
    @Override
    protected void initPresenter() {
        mPresenter = new FriendPresenter(new FriendModel(),this);
    }

    @Override
    protected void initView() {

        rvFriend = view.findViewById(R.id.rv_friend);
        LogUtils.d(rvFriend.toString());
        rvFriendWord = view.findViewById(R.id.rv_friend_word);
    }

    @Override
    protected void initData() {
        mPresenter.getFriend();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected int getLayout() {
        return R.layout.friend_layout;
    }

    @Override
    public LifecycleOwner getlife() {
        return getActivity();
    }

    @Override
    public Context getMyContext() {
        return getActivity();
    }

    @Override
    public void initRv(List<PeopleEntity> peopleEntities) {
        if(adapter==null){
            adapter = new FriendRVAdapter(peopleEntities);
            rvFriend.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvFriend.setAdapter(adapter);
        }else{
            adapter.addData(peopleEntities);
            adapter.notifyDataSetChanged();
        }
    }
}
