package com.yunfu.help.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import com.xuexiang.xqrcode.XQRCode;
import com.xuexiang.xqrcode.ui.CaptureActivity;
import com.yunfu.help.R;
import com.yunfu.help.utils.LogUtils;
import com.yunfu.help.utils.ToastUtil;

/**
 * 扫一扫
 */
public class ScanUI extends CaptureActivity implements View.OnClickListener {
    //二维码
    public static final int REQUEST_CODE = 1;
    private AppCompatImageView mIvFlashLight;
    private AppCompatImageView mIvFlashLight1;

    private boolean mIsOpen;

    /**
     * 开始二维码扫描
     *
     * @param fragment
     * @param requestCode 请求码
     * @param theme       主题
     */
    public static void start(Fragment fragment, int requestCode, int theme) {
        Intent intent = new Intent(fragment.getContext(), ScanUI.class);
        intent.putExtra(KEY_CAPTURE_THEME, theme);
        fragment.startActivityForResult(intent, requestCode);


        /**
         * 生成二维码图片
         */
//        Bitmap b = ((BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
//        Bitmap bitmap=XQRCode.createQRCodeWithLogo("123456", 400, 400, b);
    }

    /**
     * 开始二维码扫描
     *
     * @param activity
     * @param requestCode 请求码
     * @param theme       主题
     */
    public static void start(Activity activity, int requestCode, int theme) {
        Intent intent = new Intent(activity, ScanUI.class);
        intent.putExtra(KEY_CAPTURE_THEME, theme);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected int getCaptureLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    protected void handleAnalyzeSuccess(Bitmap bitmap, String result) {
        super.handleAnalyzeSuccess(bitmap, result);
        if (TextUtils.isEmpty(result)) {
            ToastUtil.showLong("不是本平台的二维码！");
            return;
        }

        LogUtils.e(result+"+++++++++++");
    }


    @Override
    protected void beforeCapture() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        mIvFlashLight = findViewById(R.id.iv_flash_light);
        mIvFlashLight1 = findViewById(R.id.iv_flash_light1);
    }

    @Override
    protected void onCameraInitSuccess() {
        mIvFlashLight.setVisibility(View.VISIBLE);
        mIvFlashLight1.setVisibility(View.VISIBLE);

        mIsOpen = XQRCode.isFlashLightOpen();
        refreshFlashIcon();
        mIvFlashLight.setOnClickListener(this);
        mIvFlashLight1.setOnClickListener(this);
    }

    @Override
    protected void onCameraInitFailed() {
        mIvFlashLight.setVisibility(View.GONE);
        mIvFlashLight1.setVisibility(View.GONE);
    }

    private void refreshFlashIcon() {
        if (mIsOpen) {
            mIvFlashLight.setImageResource(R.drawable.ic_flash_light_on);
            mIvFlashLight1.setImageResource(R.drawable.ic_flash_light_open);
        } else {
            mIvFlashLight.setImageResource(R.drawable.ic_flash_light_off);
            mIvFlashLight1.setImageResource(R.drawable.ic_flash_light_close);
        }
    }

    private void switchFlashLight() {
        mIsOpen = !mIsOpen;
        try {
            XQRCode.switchFlashLight(mIsOpen);
            refreshFlashIcon();
        } catch (RuntimeException e) {
            e.printStackTrace();
            ToastUtil.showLong("设备不支持闪光灯");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String stringExtra = data.getStringExtra(XQRCode.RESULT_DATA);
            Log.e("TAG", "onActivityResult: " + stringExtra);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_flash_light:
            case R.id.iv_flash_light1:
                switchFlashLight();
                break;
            default:
                break;
        }
    }
}
