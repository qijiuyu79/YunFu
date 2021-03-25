package com.yunfu.help.application;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.yunfu.help.utils.ActivitysLifecycle;

public class MyApplication extends Application {

    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;

        //初始化百度地图
        initMap();

        /**
         * 管理Activity
         */
        registerActivityLifecycleCallbacks(ActivitysLifecycle.getInstance());


        /**
         * android 7.0系统解决拍照的问题
         */
        initPhotoError();
    }


    public static MyApplication getApplication(){
        return application;
    }


    /**
     * 初始化百度地图
     */
    private void initMap(){
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.GCJ02);
    }


    /**
     * android 7.0系统解决拍照的问题
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static void initPhotoError(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
