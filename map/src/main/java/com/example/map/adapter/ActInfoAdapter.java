package com.example.map.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.map.R;
import com.example.map.entity.ActEntity;

import java.util.List;

public class ActInfoAdapter extends BaseQuickAdapter<ActEntity, BaseViewHolder> {
    public ActInfoAdapter(int layoutResId, @Nullable List<ActEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActEntity item) {
        helper.setText(R.id.tv_item_time,item.getTime());
        helper.setText(R.id.tv_name,item.getName());
    }

}
