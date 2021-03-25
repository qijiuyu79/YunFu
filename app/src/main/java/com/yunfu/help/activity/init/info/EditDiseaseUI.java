package com.yunfu.help.activity.init.info;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gyf.immersionbar.ImmersionBar;
import com.yunfu.help.R;
import com.yunfu.help.activity.TabActivity;
import com.yunfu.help.adapter.init.EditDiseaseAdapter;
import com.yunfu.help.adapter.init.LineAdapter;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.bean.DiseaseBean;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置疾病
 */
public class EditDiseaseUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.list_disease)
    ListView listDisease;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_disease;
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
        listView.setAdapter(new LineAdapter(this, 3));

        List<DiseaseBean> list=new ArrayList<>();
        list.add(new DiseaseBean("便秘"));
        list.add(new DiseaseBean("高血压"));
        list.add(new DiseaseBean("糖尿病"));
        list.add(new DiseaseBean("甲亢"));
        list.add(new DiseaseBean("贫血"));
        list.add(new DiseaseBean("高血脂"));
        list.add(new DiseaseBean("乙肝"));
        list.add(new DiseaseBean("便秘"));
        listDisease.setAdapter(new EditDiseaseAdapter(this,list));
    }


    @OnClick({R.id.lin_back, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_next:
                setClass(TabActivity.class);
                break;
            default:
                break;
        }
    }
}
