package com.example.imageloader.imageloaderlib.base.setting;

import android.widget.ImageView;

/**
 * 通用的imageSetting
 */
public class NormalImageSetting extends ImageSetting{


    /**
     * 支持圆角图片
     */
    private int imgRadius;

    public NormalImageSetting(Builder builder) {
        mUrl=builder.imgUrl;
        mPlaceHolder=builder.placeHolder;
        mView=builder.imgView;
        this.imgRadius=builder.imgRadius;
    }

    public int getImgRadius() {
        return imgRadius;
    }


    public void setImgRadius(int imgRadius) {
        this.imgRadius = imgRadius;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder{
        private String imgUrl;
        private ImageView imgView;
        private int placeHolder;
        private int imgRadius;

        private Builder(){
        }

        public Builder url(String url){
            imgUrl =url;
            return this;
        }

        public Builder imgView(ImageView _img){
            imgView =_img;
            return this;
        }

        public Builder placeHolder(int _place){
            placeHolder =_place;
            return this;
        }

        public Builder imgRadius(int _radius){
            imgRadius=_radius;
            return this;
        }

        public NormalImageSetting build(){
            return new NormalImageSetting(this);
        }
    }

}
