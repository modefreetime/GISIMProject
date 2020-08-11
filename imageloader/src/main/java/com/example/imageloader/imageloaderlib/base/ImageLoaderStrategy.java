package com.example.imageloader.imageloaderlib.base;

import android.content.Context;

import com.example.imageloader.imageloaderlib.base.setting.ImageSetting;

/**
 * image加载图片的策略
 * @param <Setting>
 */
public interface ImageLoaderStrategy<Setting extends ImageSetting> {
    /**
     * 加载图片
     * @param context 上下文
     * @param setting 图片加载设置
     */
    void loadImage(Context context, Setting setting);

}
