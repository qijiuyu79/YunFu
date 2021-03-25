package com.yunfu.help.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yunfu.help.R;
import com.yunfu.help.activity.found.SendDynamicUI;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordDialog extends Dialog {

    private Activity context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_record);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setWindowAnimations(R.style.DialogBottom); // 添加动画
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
    }

    public RecordDialog(Activity context) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
    }


    /**
     * 初始化
     */
    private void initView() {

    }


    @OnClick({R.id.img_yinshi, R.id.img_weight, R.id.img_xuetang, R.id.img_xueya, R.id.img_bushu, R.id.img_dongtai,R.id.img_close})
    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            //记录饮食
            case R.id.img_yinshi:
                dismiss();
                break;
            //记录体重
            case R.id.img_weight:
                dismiss();
                break;
            //记录血糖
            case R.id.img_xuetang:
                dismiss();
                break;
            //记录血压
            case R.id.img_xueya:
                dismiss();
                break;
            //记录步数
            case R.id.img_bushu:
                dismiss();
                break;
            //记录动态
            case R.id.img_dongtai:
                 intent.setClass(context, SendDynamicUI.class);
                 context.startActivity(intent);
                 dismiss();
                 break;
            case R.id.img_close:
                dismiss();
                break;
            default:
                break;
        }
    }
}
