package com.yunfu.help.activity.init;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.yunfu.help.R;
import com.yunfu.help.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置密码
 */
public class EditPasswordUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_newPwd)
    EditText etNewPwd;
    @BindView(R.id.et_rePwd)
    EditText etRePwd;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_pwd;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvHead.setText("设置密码");
    }


    @OnClick({R.id.lin_back, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.btn_register:
                break;
            default:
                break;
        }
    }
}
