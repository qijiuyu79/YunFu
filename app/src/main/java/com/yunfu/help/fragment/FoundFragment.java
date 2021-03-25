package com.yunfu.help.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yunfu.help.R;
import com.yunfu.help.activity.found.FoundDetailsUI;
import com.yunfu.help.adapter.found.FoundAdapter;
import com.yunfu.help.base.BaseFragment;

import butterknife.BindView;

/**
 * 朋友圈
 */
public class FoundFragment extends BaseFragment {
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;
    //适配器
    private FoundAdapter adapter;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.listview;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();

        listView.setAdapter(adapter=new FoundAdapter(activity));
    }
}
