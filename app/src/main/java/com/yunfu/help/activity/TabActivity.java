package com.yunfu.help.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import com.yunfu.help.R;
import com.yunfu.help.activity.found.FoundUI;
import com.yunfu.help.activity.information.InformationUI;
import com.yunfu.help.activity.main.MainUI;
import com.yunfu.help.activity.mine.MineUI;
import com.yunfu.help.dialog.RecordDialog;
import com.yunfu.help.utils.ActivitysLifecycle;
import com.yunfu.help.utils.ToastUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabActivity extends android.app.TabActivity {

    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(android.R.id.tabhost)
    TabHost tabhost;
    private int[] notClick = new int[]{R.mipmap.tab_1_false, R.mipmap.tab_2_false, R.mipmap.tab_3_false, R.mipmap.tab_4_false};
    private int[] yesClick = new int[]{R.mipmap.tab_1_true, R.mipmap.tab_2_true, R.mipmap.tab_3_true, R.mipmap.tab_4_true};
    // 按两次退出
    protected long exitTime = 0;
    private List<TextView> tvList = new ArrayList<>();
    private List<ImageView> imgList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        initView();
    }


        /**
         * 初始化
         */
        private void initView() {
            imgList.add(img1);
            imgList.add(img2);
            imgList.add(img3);
            imgList.add(img4);
            tvList.add(tv1);
            tvList.add(tv2);
            tvList.add(tv3);
            tvList.add(tv4);
            tabhost = this.getTabHost();
            TabHost.TabSpec spec;
            spec = tabhost.newTabSpec("首页").setIndicator("首页").setContent(new Intent(this, MainUI.class));
            tabhost.addTab(spec);
            spec = tabhost.newTabSpec("发现").setIndicator("发现").setContent(new Intent(this, FoundUI.class));
            tabhost.addTab(spec);
            spec = tabhost.newTabSpec("资讯").setIndicator("资讯").setContent(new Intent(this, InformationUI.class));
            tabhost.addTab(spec);
            spec = tabhost.newTabSpec("我的").setIndicator("我的").setContent(new Intent(this, MineUI.class));
            tabhost.addTab(spec);
        }


        @OnClick({R.id.lin1, R.id.lin2, R.id.lin3, R.id.lin4,R.id.lin_add})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.lin1:
                    updateTag(0);
                    break;
                case R.id.lin2:
                    updateTag(1);
                    break;
                case R.id.lin3:
                    updateTag(2);
                    break;
                case R.id.lin4:
                    updateTag(3);
                    break;
                case R.id.lin_add:
                    new RecordDialog(this).show();
                     break;
                default:
                    break;
            }
        }


        /**
         * 切换tab时，更新图片和文字颜色
         */
        private void updateTag(int type) {
            for (int i = 0; i < imgList.size(); i++) {
                if (i == type) {
                    imgList.get(i).setImageDrawable(getResources().getDrawable(yesClick[i]));
                    tvList.get(i).setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    imgList.get(i).setImageDrawable(getResources().getDrawable(notClick[i]));
                    tvList.get(i).setTextColor(getResources().getColor(R.color.color_999999));
                }
            }
            tabhost.setCurrentTab(type);
        }


        /**
         * 连续点击两次返回退出
         *
         * @param event
         * @return
         */
        public boolean dispatchKeyEvent(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    ToastUtil.showLong("再按一次退出程序!");
                    exitTime = System.currentTimeMillis();
                } else {
                    EventBus.getDefault().unregister(this);
                    ActivitysLifecycle.getInstance().exit();
                }
                return false;
            }
            return super.dispatchKeyEvent(event);
        }
    }
