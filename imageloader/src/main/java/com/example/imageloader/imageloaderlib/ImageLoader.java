package com.example.imageloader.imageloaderlib;

import android.content.Context;

import com.example.imageloader.imageloaderlib.base.ImageLoaderStrategy;
import com.example.imageloader.imageloaderlib.base.setting.ImageSetting;
import com.example.imageloader.imageloaderlib.impl.GlideStrategy;


public class ImageLoader {

    private ImageLoaderStrategy imageLoaderStrategy;

    private volatile static ImageLoader imageLoader = null;

    private ImageLoader(){
        //默认启用Glide策略
        imageLoaderStrategy = new GlideStrategy();
    }

    public static ImageLoader getInstance(){
        if(null==imageLoader){
            synchronized (ImageLoader.class){
                if(null==imageLoader){
                    imageLoader = new ImageLoader();
                }
            }
        }
        return imageLoader;
    }

    public void loadImage(Context context, ImageSetting setting){
        if(null==imageLoaderStrategy){
            throw  new NullPointerException("imageLoaderStrategy is null");
        }
        if(null == context){
            throw new IllegalArgumentException("context is null");
        }
        if(null == setting){
            throw new IllegalArgumentException("setting is null");
        }
        imageLoaderStrategy.loadImage(context,setting);
    }

    /**
     * 初始化策略
     * @param _imgLoader
     */
    public void initStrategy(ImageLoaderStrategy _imgLoader){
        this.imageLoaderStrategy = _imgLoader;
    }
    public ImageLoaderStrategy getImageLoaderStrategy(){
        return this.imageLoaderStrategy;
    }
}
