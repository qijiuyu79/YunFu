package com.yunfu.help.activity.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunfu.help.R;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.utils.CleanCacheUtil;
import com.yunfu.help.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.img_news)
    ImageView imgNews;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvHead.setText("设置");

        try {
            tvCache.setText(CleanCacheUtil.getTotalCacheSize(this));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @OnClick({R.id.lin_back, R.id.rel_edit_pwd, R.id.rel_wx,R.id.img_news, R.id.rel_cache, R.id.rel_feedback, R.id.rel_score, R.id.rel_privacy, R.id.rel_agrement, R.id.rel_version, R.id.tv_login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //修改密码
            case R.id.rel_edit_pwd:
                break;
            //绑定微信
            case R.id.rel_wx:
                 break;
            //消息开关
            case R.id.img_news:
                break;
            //清除缓存
            case R.id.rel_cache:
                CleanCacheUtil.clearAllCache(this);
                ToastUtil.showLong("缓存清理完成");
                tvCache.setText("0.0M");
                break;
            //意见反馈
            case R.id.rel_feedback:
                break;
            //评分
            case R.id.rel_score:
                Uri uri = Uri.parse("market://details?id="+getPackageName());
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
            //隐私政策
            case R.id.rel_privacy:
                break;
            //用户协议
            case R.id.rel_agrement:
                break;
            //版本更新
            case R.id.rel_version:
                break;
            //退出登录
            case R.id.tv_login_out:
                break;
            default:
                break;
        }
    }
}
