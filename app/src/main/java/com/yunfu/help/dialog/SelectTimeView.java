package com.yunfu.help.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yunfu.help.R;
import com.yunfu.help.callback.TimeCallBack;
import com.yunfu.help.utils.TimeUtils;
import com.yunfu.help.view.CycleWheelView;

import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectTimeView extends Dialog {

    @BindView(R.id.wv_year)
    CycleWheelView wvYear;
    @BindView(R.id.wv_month)
    CycleWheelView wvMonth;
    @BindView(R.id.wv_date)
    CycleWheelView wvDate;
    @BindView(R.id.wv_hour)
    CycleWheelView wvHour;
    @BindView(R.id.wv_minute)
    CycleWheelView wvMinute;
    @BindView(R.id.wv_seconds)
    CycleWheelView wvSeconds;
    private Activity context;
    //是否显示日
    private boolean showDate=true;
    //是否显示小时
    private boolean showHour=true;
    //是否显示分钟
    private boolean showMinute=true;
    //是否显示秒
    private boolean showSeconds=true;
    private boolean isDay=false;
    private TimeCallBack timeCallBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_time);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setWindowAnimations(R.style.DialogBottom); // 添加动画
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
    }

    public SelectTimeView(Activity context, boolean showDate, boolean showHour, boolean showMinute, boolean showSeconds, TimeCallBack timeCallBack) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.showDate=showDate;
        this.showHour=showHour;
        this.showMinute=showMinute;
        this.showSeconds=showSeconds;
        this.timeCallBack=timeCallBack;
    }


    /**
     * 初始化
     */
    private void initView() {
       try {
           if(!showDate){
               wvDate.setVisibility(View.GONE);
           }
           if(!showHour){
               wvHour.setVisibility(View.GONE);
           }
           if(!showMinute){
               wvMinute.setVisibility(View.GONE);
           }
           if(!showSeconds){
               wvSeconds.setVisibility(View.GONE);
           }
           //获取当前年月日
           Calendar calendar = Calendar.getInstance();
           //年
           int intYear = calendar.get(Calendar.YEAR);
           //月
           int intMonth = (calendar.get(Calendar.MONTH)+1);
           //日
           int intDay = calendar.get(Calendar.DAY_OF_MONTH);
           //小时
           int intHour = calendar.get(Calendar.HOUR_OF_DAY);
           //分钟
           int intMinute=calendar.get(Calendar.MINUTE);
           //秒钟
           int intSeconds=calendar.get(Calendar.SECOND);

           //设置列表数据
           wvYear.setLabels(TimeUtils.getYearList());
           wvMonth.setLabels(TimeUtils.getMonthList());
           wvHour.setLabels(TimeUtils.getHourList());
           wvMinute.setLabels(TimeUtils.getMinuteList());
           wvSeconds.setLabels(TimeUtils.getSecondsList());
           initWheel(wvYear);
           initWheel(wvMonth);
           initWheel(wvHour);
           initWheel(wvMinute);
           initWheel(wvSeconds);

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

           i=-1;
           for(String s:TimeUtils.getHourList()){
               i++;
               if(Integer.parseInt(s.replace("时",""))==intHour){
                   wvHour.setSelection(i);
                   break;
               }
           }

           i=-1;
           for(String s:TimeUtils.getMinuteList()){
               i++;
               if(Integer.parseInt(s.replace("分",""))==intMinute){
                   wvMinute.setSelection(i);
                   break;
               }
           }

           i=-1;
           for(String s:TimeUtils.getSecondsList()){
               i++;
               if(Integer.parseInt(s.replace("秒",""))==intSeconds){
                   wvSeconds.setSelection(i);
                   break;
               }
           }


           /**
            * 监听月份滑动
            */
           wvMonth.setOnWheelItemSelectedListener((position, label) -> {
               final int year=Integer.parseInt(wvYear.getSelectLabel().replace("年",""));
               final int month=Integer.parseInt(label.replace("月",""));
               wvDate.setLabels(TimeUtils.getDateList(year,month));
               initWheel(wvDate);

               if(isDay){
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
       }catch (Exception e){
           e.printStackTrace();
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




    @OnClick({R.id.rel,R.id.tv_cancle, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel:
                 dismiss();
                 break;
            case R.id.tv_cancle:
                 timeCallBack.getTime("");
                 dismiss();
                break;
            case R.id.tv_confirm:
                 try {
                     final String year=wvYear.getSelectLabel().replace("年","");
                     final String month=wvMonth.getSelectLabel().replace("月","");
                     final String day=wvDate.getSelectLabel().replace("日","");
                     final String hour=wvHour.getSelectLabel().replace("时","");
                     final String minute=wvMinute.getSelectLabel().replace("分","");
                     final String seconds=wvSeconds.getSelectLabel().replace("秒","");

                     StringBuilder sb=new StringBuilder(year+"-"+month);
                     if(showDate){
                         sb.append("-"+day);
                     }
                     if(showHour){
                         sb.append(" "+hour);
                     }
                     if(showMinute){
                         sb.append(":"+minute);
                     }
                     if(showSeconds){
                         sb.append(":"+seconds);
                     }
                     timeCallBack.getTime(sb.toString());
                 }catch (Exception e){
                     e.printStackTrace();
                 }
                 dismiss();
                break;
            default:
                break;
        }
    }
}
