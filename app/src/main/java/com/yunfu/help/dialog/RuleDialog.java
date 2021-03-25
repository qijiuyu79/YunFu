package com.yunfu.help.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yunfu.help.R;
import com.yunfu.help.callback.SelectCallBack;
import com.zkk.view.rulerview.RulerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RuleDialog extends Dialog {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.ruler)
    RulerView ruler;
    private Activity context;
    /**
     * 1：身高
     * 2：体重
     */
    private int type;
    private SelectCallBack selectCallBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rule);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setWindowAnimations(R.style.DialogBottom); // 添加动画
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
    }

    public RuleDialog(Activity context, int type, SelectCallBack selectCallBack) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.type=type;
        this.selectCallBack=selectCallBack;
    }


    /**
     * 初始化
     */
    private void initView() {
        if(type==1){
            tvTitle.setText("身高");
            tvUnit.setText("cm");
            tvData.setText("165");
            ruler.setOnValueChangeListener(value -> tvData.setText(String.valueOf(value)));
            ruler.setValue(165, 80, 300, 1);
        }else{
            tvTitle.setText("体重");
            tvUnit.setText("Kg");
            tvData.setText("55");
            ruler.setOnValueChangeListener(value -> tvData.setText(String.valueOf(value)));
            ruler.setValue(55, 30, 150, (float) 0.1);
        }
    }


    @OnClick({R.id.img_colse, R.id.tv_save, R.id.rel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_colse:
                dismiss();
                break;
            case R.id.tv_save:
                selectCallBack.getSelect(tvData.getText().toString(),null);
                dismiss();
                break;
            case R.id.rel:
                dismiss();
                break;
            default:
                break;
        }
    }
}
