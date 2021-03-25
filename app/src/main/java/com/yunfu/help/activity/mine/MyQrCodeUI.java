package com.yunfu.help.activity.mine;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.xuexiang.xqrcode.XQRCode;
import com.yunfu.help.R;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.utils.OtherUtils;
import com.yunfu.help.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的二维码
 */
public class MyQrCodeUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.img_qrcode)
    ImageView imgQrcode;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_qrcode;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvHead.setText("我的二维码");
        ImmersionBar.with(this).statusBarColor(android.R.color.white).statusBarDarkFont(true).init();

        Bitmap b = ((BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
        Bitmap bitmap= XQRCode.createQRCodeWithLogo("123434534234234256", 400, 400, b);
        imgQrcode.setImageBitmap(bitmap);
    }


    @OnClick(R.id.lin_back)
    public void onClick() {
        finish();
    }
}
