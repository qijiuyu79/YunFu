package com.yunfu.help.activity.init.info;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.yunfu.help.R;
import com.yunfu.help.adapter.init.LineAdapter;
import com.yunfu.help.base.BaseActivity;
import com.zkk.view.rulerview.RulerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置体重
 */
public class EditWeightUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.ruler_height)
    RulerView rulerHeight;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_weight;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvHead.setText("基本信息");
        ImmersionBar.with(this).statusBarColor(android.R.color.white).statusBarDarkFont(true).init();

        listView.setLayoutManager(new GridLayoutManager(activity, 4));
        listView.setAdapter(new LineAdapter(this,2));

        tvWeight.setText("55");
        rulerHeight.setOnValueChangeListener(value -> tvWeight.setText(String.valueOf(value)));
        rulerHeight.setValue(55, 30, 150, (float) 0.1);
    }


    @OnClick({R.id.lin_back, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_next:
                setClass(EditDiseaseUI.class);
                break;
            default:
                break;
        }
    }
}
