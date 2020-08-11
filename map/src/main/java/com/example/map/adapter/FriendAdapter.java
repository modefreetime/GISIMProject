package com.example.map.adapter;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.map.R;

import java.util.List;

public class FriendAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public FriendAdapter( @Nullable List<String> data) {
        super(R.layout.right_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        CardView cv = helper.getView(R.id.cv);

    }
}
