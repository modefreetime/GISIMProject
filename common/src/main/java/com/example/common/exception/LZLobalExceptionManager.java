package com.example.common.exception;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.annotation.NonNull;

//全局异常捕获
public class LZLobalExceptionManager implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    private static LZLobalExceptionManager instance = new LZLobalExceptionManager();

    private LZLobalExceptionManager() {
    }

    public static LZLobalExceptionManager getInstance() {
        return instance;
    }

    //初始化方法
    public void init(Context context) {
        mContext = context;
        //获取系统提供的异常处理器
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置成我们自己实现的异常处理器,当前类
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        if(!handlException(thread,throwable) && defaultUncaughtExceptionHandler!=null){
            defaultUncaughtExceptionHandler.uncaughtException(thread,throwable);
        }else{
            SystemClock.sleep(3000);
            Process.killProcess(Process.myPid());
            System.exit(10);
        }

    }

    /**
     * 自定义处理异常
     * @param thread
     * @param throwable
     * @return
     */
    private boolean handlException(Thread thread, Throwable throwable) {
        if(thread==null &&throwable==null){
            return false;
        }
        String message = throwable.getMessage();
        String name = thread.getName();
        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(mContext, "程序崩了", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        return true;
    }
}
