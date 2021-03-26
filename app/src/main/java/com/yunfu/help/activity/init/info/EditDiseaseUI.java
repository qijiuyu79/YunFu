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
import com.yunfu.help.application.MyApplication;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.bean.DiseaseBean;
import com.yunfu.help.persenter.DictP;
import com.yunfu.help.persenter.LoginP;
import com.yunfu.help.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置疾病
 */
public class EditDiseaseUI extends BaseActivity implements DictP.Face, LoginP.Face2 {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.list_disease)
    ListView listDisease;
    //疾病数据
    private List<DiseaseBean> list=new ArrayList<>();
    //身高
    private String height;
    //出生日期
    private String birthday;
    //体重
    private String weight;

    private DictP dictP=new DictP(this);
    private LoginP loginP=new LoginP(this);

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
        loginP.setFace2(this);

        height=getIntent().getStringExtra("height");
        birthday=getIntent().getStringExtra("birthday");
        weight=getIntent().getStringExtra("weight");

        listView.setLayoutManager(new GridLayoutManager(activity, 4));
        listView.setAdapter(new LineAdapter(this, 3));

        //获取疾病信息
        dictP.setFace(this);
        dictP.getOtherInfo();
    }


    @OnClick({R.id.lin_back, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_next:
                final StringBuffer sb=new StringBuffer();
                for (DiseaseBean data:list){
                     if(data.isSelect()){
                         sb.append(data.getName()+",");
                     }
                }
                if(sb.length()==0){
                    ToastUtil.showLong("请选择特殊情况");
                    return;
                }
                loginP.setOtherInfo(MyApplication.getLogin().getId(),birthday,height,weight,sb.substring(0,sb.length()-1));
                break;
            default:
                break;
        }
    }


    /**
     * 获取疾病信息
     * @param result
     */
    @Override
    public void getOtherInfo(List<String> result) {
        if(result==null){
            return;
        }
        for (String data:result){
            list.add(new DiseaseBean(data));
        }
        listDisease.setAdapter(new EditDiseaseAdapter(this,list));
    }


    /**
     * 基本信息设置成功
     */
    @Override
    public void setOtherInfo() {

    }
}
