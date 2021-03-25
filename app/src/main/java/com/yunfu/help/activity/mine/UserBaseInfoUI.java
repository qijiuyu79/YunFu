package com.yunfu.help.activity.mine;

import android.view.View;
import android.widget.TextView;
import com.yunfu.help.R;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.dialog.RuleDialog;
import com.yunfu.help.dialog.SelectTimeView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户基本信息
 */
public class UserBaseInfoUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_height)
    TextView tvHeight;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_weight2)
    TextView tvWeight2;
    @BindView(R.id.tv_due_date)
    TextView tvDueDate;
    @BindView(R.id.tv_special)
    TextView tvSpecial;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_baseinfo;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvHead.setText("基本信息");
    }


    @OnClick({R.id.lin_back, R.id.tv_birthday, R.id.tv_height, R.id.tv_weight, R.id.tv_weight2, R.id.tv_due_date, R.id.tv_special, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //出生日期
            case R.id.tv_birthday:
                 new SelectTimeView(this, true, false, false, false, time -> {
                     tvBirthday.setText(time);
                 }).show();
                break;
            //身高
            case R.id.tv_height:
                 new RuleDialog(this, 1, (object, object1) -> {
                     tvHeight.setText((String)object+"cm");
                 }).show();
                break;
            //体重
            case R.id.tv_weight:
                new RuleDialog(this, 2, (object, object1) -> {
                    tvWeight.setText((String)object+"Kg");
                }).show();
                break;
            //孕前体重
            case R.id.tv_weight2:
                new RuleDialog(this, 2, (object, object1) -> {
                    tvWeight2.setText((String)object+"Kg");
                }).show();
                break;
            case R.id.tv_due_date:
                new SelectTimeView(this, true, false, false, false, time -> {
                    tvDueDate.setText(time);
                }).show();
                break;
            case R.id.tv_special:
                break;
            case R.id.tv_save:
                break;
            default:
                break;
        }
    }
}
