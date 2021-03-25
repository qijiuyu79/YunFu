package com.yunfu.help.activity.init.info;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gyf.immersionbar.ImmersionBar;
import com.yunfu.help.R;
import com.yunfu.help.adapter.init.LineAdapter;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.utils.TimeUtils;
import com.yunfu.help.view.CycleWheelView;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置出生年月
 */
public class EditBirthdayUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.wv_year)
    CycleWheelView wvYear;
    @BindView(R.id.wv_month)
    CycleWheelView wvMonth;
    @BindView(R.id.wv_date)
    CycleWheelView wvDate;
    private boolean isDay=false;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_birthday;
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
        listView.setAdapter(new LineAdapter(this,1));

        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        //年
        int intYear = calendar.get(Calendar.YEAR)-25;
        //月
        int intMonth = (calendar.get(Calendar.MONTH)+1);
        //日
        int intDay = calendar.get(Calendar.DAY_OF_MONTH);

        final StringBuffer sb=new StringBuffer(intYear+"-");
        if(intMonth<10){
            sb.append("0"+intMonth+"-");
        }else{
            sb.append(intMonth+"-");
        }
        if(intDay<10){
            sb.append("0"+intDay);
        }else{
            sb.append(intDay);
        }
        tvBirthday.setText(sb.toString());


        //设置列表数据
        wvYear.setLabels(TimeUtils.getYearList());
        wvMonth.setLabels(TimeUtils.getMonthList());
        initWheel(wvYear);
        initWheel(wvMonth);

        int i=-1;
        for(String s:TimeUtils.getYearList()){
            i++;
            if(Integer.parseInt(s.replace("年",""))==intYear){
                wvYear.setSelection(i);
                break;
            }
        }

        i=-1;
        for(String s:TimeUtils.getMonthList()){
            i++;
            if(Integer.parseInt(s.replace("月",""))==intMonth){
                wvMonth.setSelection(i);
                break;
            }
        }


        /**
         * 监听年份滑动
         */
        wvYear.setOnWheelItemSelectedListener((position, label) -> {
            if(isDay){
                final String year=wvYear.getSelectLabel().replace("年","");
                final String month=wvMonth.getSelectLabel().replace("月","");
                final String day=wvDate.getSelectLabel().replace("日","");
                tvBirthday.setText(year+"-"+month+"-"+day);
            }
        });


        /**
         * 监听月份滑动
         */
        wvMonth.setOnWheelItemSelectedListener((position, label) -> {
            final int year=Integer.parseInt(wvYear.getSelectLabel().replace("年",""));
            final int month=Integer.parseInt(wvMonth.getSelectLabel().replace("月",""));
            wvDate.setLabels(TimeUtils.getDateList(year,month));
            initWheel(wvDate);

            if(isDay){
                final String month2=wvMonth.getSelectLabel().replace("月","");
                final String day=wvDate.getSelectLabel().replace("日","");
                tvBirthday.setText(year+"-"+month2+"-"+day);
                return;
            }
            int k=-1;
            for(String s:TimeUtils.getDateList(year,month)){
                k++;
                if(Integer.parseInt(s.replace("日",""))==intDay){
                    isDay=true;
                    wvDate.setSelection(k);
                    break;
                }
            }
        });


        /**
         * 监听天滑动
         */
        wvDate.setOnWheelItemSelectedListener((position, label) -> {
            if(isDay){
                final String year=wvYear.getSelectLabel().replace("年","");
                final String month=wvMonth.getSelectLabel().replace("月","");
                final String day=wvDate.getSelectLabel().replace("日","");
                tvBirthday.setText(year+"-"+month+"-"+day);
            }
        });

    }


    @OnClick({R.id.lin_back, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_next:
                setClass(EditWeightUI.class);
                break;
        }
    }


    public void initWheel(CycleWheelView wheel) {
        wheel.setSelection(0);
        try {
            wheel.setWheelSize(5);
        } catch (CycleWheelView.CycleWheelViewException e) {
            e.printStackTrace();
        }
        wheel.setCycleEnable(false);
        wheel.setAlphaGradual(0.5f);
        wheel.setDivider(Color.parseColor("#EBEBEB"), 1);
        wheel.setSolid(Color.WHITE, Color.WHITE);
        wheel.setLabelColor(Color.GRAY);
        wheel.setLabelSelectColor(Color.BLACK);
    }

}
