package com.yunfu.help.activity.found;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.yunfu.help.R;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.fragment.FoundFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 发现页面
 */
public class FoundUI extends BaseActivity {
    @BindView(R.id.lin_back)
    LinearLayout linBack;
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private final ArrayList<Fragment> fragments=new ArrayList<Fragment>(){{add(new FoundFragment());add(new FoundFragment());}};
    private String[] titles=new String[]{"精选","好友圈"};

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_found;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        linBack.setVisibility(View.GONE);
        tvHead.setText("发现");

        //显示全部，附近的列表数据
        tabLayout.setViewPager(viewPager, titles, FoundUI.this, fragments);
    }
}
