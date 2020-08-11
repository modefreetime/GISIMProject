package com.example.msg.mvp.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.msg.R;
import com.example.msg.mvp.entity.msgEntity;

import java.util.List;

public class ChatAdapter extends BaseMultiItemQuickAdapter<msgEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChatAdapter(List<msgEntity> data) {
        super(data);
        addItemType(0, R.layout.msg_from_layout);
        addItemType(1, R.layout.msg_to_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, msgEntity item) {
        if(item.getType()==0){
            helper.setText(R.id.tv_finfo,item.getMsg());
            helper.setText(R.id.tv_Sendtime,item.getMsgtime());
        }else {
            helper.setText(R.id.tv_2info,item.getMsg());
            helper.setText(R.id.tv_send2time,item.getMsgtime());
        }
    }
}
