package com.yunfu.help.activity.mine;

import android.widget.ListView;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yunfu.help.R;
import com.yunfu.help.adapter.user.MyDynamicAdapter;
import com.yunfu.help.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的动态
 */
public class MyDynamicUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;
    //适配器
    private MyDynamicAdapter adapter;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_dynamic;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvHead.setText("我的动态");

        listView.setAdapter(adapter=new MyDynamicAdapter(this));
    }


    @OnClick(R.id.lin_back)
    public void onClick() {
        finish();
    }
}
