package com.yunfu.help.activity.mine;

import android.view.View;
import android.widget.TextView;
import com.hjq.permissions.Permission;
import com.yunfu.help.R;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.callback.PermissionCallBack;
import com.yunfu.help.utils.PermissionUtil;
import com.yunfu.help.view.click.ClickImageView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 */
public class MineUI extends BaseActivity {
    @BindView(R.id.img_head)
    ClickImageView imgHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_remark)
    TextView tvRemark;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
    }


    @OnClick({R.id.img_news, R.id.img_setting, R.id.img_head, R.id.lin_common, R.id.lin_coll, R.id.lin_medal, R.id.lin_ranking, R.id.rel_info,R.id.rel_scan,R.id.rel_qrcode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_news:
                break;
            //设置
            case R.id.img_setting:
                 setClass(SettingUI.class);
                break;
            //设置用户信息
            case R.id.img_head:
                 setClass(UserInfoUI.class);
                break;
            //我的动态
            case R.id.lin_common:
                setClass(MyDynamicUI.class);
                break;
            //我的收藏
            case R.id.lin_coll:
                break;
            //我的勋章
            case R.id.lin_medal:
                break;
            //我的排名
            case R.id.lin_ranking:
                break;
            //基本信息
            case R.id.rel_info:
                setClass(UserBaseInfoUI.class);
                break;
            //扫一扫
            case R.id.rel_scan:
                if(!PermissionUtil.isPermission(this, new PermissionCallBack() {
                    @Override
                    public void onSuccess() {
                        setClass(ScanUI.class);
                    }
                    @Override
                    public void onFail() {

                    }
                }, Permission.CAMERA)){}
                 break;
            //我的二维码
            case R.id.rel_qrcode:
                setClass(MyQrCodeUI.class);
                 break;
            default:
                break;
        }
    }
}
