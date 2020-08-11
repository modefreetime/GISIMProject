package com.example.map.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.map.R;
import com.example.map.mvp.entity.PeopleEntity;

import java.util.List;

public class FriendRVAdapter extends BaseQuickAdapter<PeopleEntity, BaseViewHolder> {
    public FriendRVAdapter( @Nullable List<PeopleEntity> data) {
        super(R.layout.friend_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PeopleEntity item) {
        helper.setText(R.id.friend_item_name,item.getUsername());
    }
}
