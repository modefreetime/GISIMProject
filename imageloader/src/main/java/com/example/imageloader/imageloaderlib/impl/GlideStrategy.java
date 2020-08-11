package com.example.imageloader.imageloaderlib.impl;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.imageloader.imageloaderlib.base.ImageLoaderStrategy;
import com.example.imageloader.imageloaderlib.base.setting.NormalImageSetting;

/**
 * Glide的图片加载策略
 */
public class GlideStrategy implements ImageLoaderStrategy<NormalImageSetting> {

    @Override
    public void loadImage(Context context, NormalImageSetting setting) {
        RequestOptions requestOptions = null;
        if (setting.getImgRadius() > 0) {
            requestOptions = RequestOptions.bitmapTransform(new RoundedCorners(setting.getImgRadius()));
        } else {
            requestOptions = new RequestOptions();
        }
        if (setting.getmPlaceHolder() > 0) {
            requestOptions.placeholder(setting.getmPlaceHolder());
            requestOptions.error(setting.getmPlaceHolder());
        }
        //关闭内存缓存
//        requestOptions.skipMemoryCache(true);
        //都缓存
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);


        Glide.with(context).load(setting.getmUrl())
                .apply(requestOptions)
                .into(setting.getmView());
    }
}
