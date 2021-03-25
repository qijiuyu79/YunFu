package com.yunfu.help.activity.init;

import android.graphics.Paint;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.yunfu.help.R;
import com.yunfu.help.activity.init.info.EditHeightUI;
import com.yunfu.help.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginUI extends BaseActivity {

    /**
     * 验证码登录
     */
    @BindView(R.id.tv_loginSms)
    TextView tv_loginSms;
    @BindView(R.id.vw_loginSms)
    View vw_loginSms;
    /**
     * 密码登录
     */
    @BindView(R.id.tv_loginPwd)
    TextView tv_loginPwd;
    @BindView(R.id.vw_loginPwd)
    View vw_loginPwd;

    /**
     * 手机号
     */
    @BindView(R.id.et_phone)
    EditText et_phone;
    /**
     * 密码
     */
    @BindView(R.id.et_password)
    EditText et_password;
    /**
     * 验证码登录
     */
    @BindView(R.id.ll_loginSms)
    LinearLayout ll_loginSms;
    /**
     * 验证码
     */
    @BindView(R.id.et_code)
    EditText et_code;
    /**
     * 获取验证码
     */
    @BindView(R.id.tv_code)
    TextView tv_code;
    /**
     * 忘记密码
     */
    @BindView(R.id.btn_forgetPwd)
    TextView btn_forgetPwd;
    /**
     * 用户注册
     */
    @BindView(R.id.tv_register)
    TextView tv_register;

    //登录类型 1密码登录 2验证码登录
    private int loginType = 1;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        ImmersionBar.with(this).statusBarDarkFont(false).init();

        String des = "还没账号，去<font color=\"#00CFBD\">注册</strong></font>";
        tv_register.setText(Html.fromHtml(des));
        tv_register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }


    @OnClick({R.id.btn_forgetPwd, R.id.btn_login, R.id.tv_register, R.id.tv_loginSms, R.id.tv_loginPwd, R.id.tv_code,R.id.img_wx_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //忘记密码
            case R.id.btn_forgetPwd:
                break;
            //登录
            case R.id.btn_login:
                setClass(EditHeightUI.class);
                break;
            //注册
            case R.id.tv_register:
                setClass(RegisterUI.class);
                break;
            //验证码登录
            case R.id.tv_loginSms:
                tv_loginSms.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_loginPwd.setTextColor(getResources().getColor(R.color.color_666666));
                vw_loginSms.setVisibility(View.VISIBLE);
                vw_loginPwd.setVisibility(View.GONE);
                ll_loginSms.setVisibility(View.VISIBLE);
                et_password.setVisibility(View.GONE);
                btn_forgetPwd.setVisibility(View.GONE);
                tv_register.setVisibility(View.GONE);
                loginType = 2;
                break;

            case R.id.tv_loginPwd: //密码登录
                tv_loginPwd.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_loginSms.setTextColor(getResources().getColor(R.color.color_666666));
                vw_loginPwd.setVisibility(View.VISIBLE);
                et_password.setVisibility(View.VISIBLE);
                ll_loginSms.setVisibility(View.GONE);
                vw_loginSms.setVisibility(View.GONE);
                btn_forgetPwd.setVisibility(View.VISIBLE);
                tv_register.setVisibility(View.VISIBLE);
                loginType = 1;
                break;
            //获取验证码
            case R.id.tv_code:
                break;
            //微信登录
            case R.id.img_wx_login:
                 break;
            default:
                break;
        }
    }
}
