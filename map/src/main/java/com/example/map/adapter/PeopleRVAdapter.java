package com.example.map.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.map.R;
import com.example.map.mvp.entity.PeopleEntity;

import java.util.List;

public class PeopleRVAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PeopleRVAdapter(@Nullable List<String> data) {
        super(R.layout.phone_people_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.phone_item_name,item);
    }
}
