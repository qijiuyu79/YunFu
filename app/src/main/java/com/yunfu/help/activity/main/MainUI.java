package com.yunfu.help.activity.main;


import androidx.annotation.NonNull;

import com.yunfu.help.R;
import com.yunfu.help.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;

public class MainUI extends BaseActivity implements OnRefreshLoadMoreListener {


    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initData() {
        super.initData();

        smartRefresh.setOnRefreshLoadMoreListener(this);
        smartRefresh.setRefreshFooter(new ClassicsFooter(this));//设置Footer
        smartRefresh.autoRefresh();
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        smartRefresh.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        smartRefresh.finishRefresh();
    }
}
