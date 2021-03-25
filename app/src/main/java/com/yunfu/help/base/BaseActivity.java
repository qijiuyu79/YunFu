package com.yunfu.help.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public abstract class BaseActivity extends FragmentActivity {

    protected Activity activity;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        // 绑定初始化ButterKnife
        ButterKnife.bind(this);
        // 数据初始化
        initData();
    }


    /**
     * 设置UI界面布局
     *
     * @return UI
     */
    protected abstract int getLayoutId();

    /**
     * 数据初始化
     */
    protected void initData() {
        activity=this;
    }


    /**
     * 跳转Activity
     * @param cls
     */
    protected void setClass(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    protected void setClass(Class<?> cls, int resultCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivityForResult(intent,resultCode);
    }


    /**
     * 确保系统字体大小不会影响app中字体大小
     * @return
     */
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * 隐藏键盘
     */
    public void lockKey(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 删除handler中的消息
     * @param mHandler
     */
    public void removeHandler(Handler mHandler){
        if(null!=mHandler){
            mHandler.removeCallbacksAndMessages(null);
        }
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
