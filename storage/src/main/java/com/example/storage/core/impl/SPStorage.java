package com.example.storage.core.impl;

import com.example.common.LogUtils;
import com.example.common.app.BaseAppcation;
import com.example.storage.core.IStorage;
import com.example.storage.core.utils.SharePreferenceUtils;

public class SPStorage implements IStorage {
    @Override
    public <T> boolean save(String key, T value) {
        try {
            SharePreferenceUtils.put(BaseAppcation.getAppContext(),key,value);
        }
        catch (Exception ex){
            LogUtils.d(ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public <T> T get(String key) {
        T result = (T) SharePreferenceUtils.get(BaseAppcation.getAppContext(), key, "");
        return result;
    }
}