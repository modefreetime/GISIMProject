package com.example.imageloader.imageloaderlib.base.setting;

import android.widget.ImageView;

/**
 * 图片组件设置
 */
public abstract class ImageSetting {
    /**
     * 图片url
     */
    protected String mUrl;
    /**
     * view
     */
    protected ImageView mView;
    /**
     * 站位图
     */
    protected int mPlaceHolder;



    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public ImageView getmView() {
        return mView;
    }

    public void setmView(ImageView mView) {
        this.mView = mView;
    }

    public int getmPlaceHolder() {
        return mPlaceHolder;
    }

    public void setmPlaceHolder(int mPlaceHolder) {
        this.mPlaceHolder = mPlaceHolder;
    }
}
