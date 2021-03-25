package com.yunfu.help.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public abstract class BaseFragment extends Fragment {

    protected Activity activity;
    /**
     * 主布局view
     */
    protected View view;
    /**
     * 注解绑定
     **/
    private Unbinder unbinder;
    /**
     * fragment是否可见
     */
    protected boolean isVisibleToUser=false;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        // 返回一个Unbinder值（进行解绑）
        unbinder = ButterKnife.bind(this, view);

        //数据初始化
        initData();
        return view;
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

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }


    /**
     * 跳转页面
     * @param cls
     */
    protected void setClass(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        startActivity(intent);
    }


    /**
     * 跳转页面
     * @param cls
     */
    protected void setClass(Class<?> cls, int resultCode) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        startActivityForResult(intent,resultCode);
    }

    public void onDetach(){
        super.onDetach();
        activity = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
