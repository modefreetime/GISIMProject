package com.example.msg.mvp.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.msg.R;
import com.example.msg.mvp.entity.MsgListEntity;

import java.util.List;

public class MsgListAdapter extends BaseQuickAdapter<MsgListEntity, BaseViewHolder> {
    public MsgListAdapter(int layoutResId, @Nullable List<MsgListEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgListEntity item) {
        helper.setText(R.id.msg_item_name,item.getName());
        helper.setText(R.id.msg_item_last,item.getMsg());
    }
}
