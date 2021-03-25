package com.yunfu.help.activity.init;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.yunfu.help.R;
import com.yunfu.help.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 */
public class RegisterUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvHead.setText("注册");

    }


    @OnClick({R.id.lin_back, R.id.tv_code, R.id.ll_clause, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_code:
                break;
            case R.id.ll_clause:
                break;
            case R.id.btn_next:
                setClass(EditPasswordUI.class);
                break;
            default:
                break;
        }
    }
}
