package com.yunfu.help.activity.init;

import android.graphics.Paint;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.yunfu.help.R;
import com.yunfu.help.activity.TabActivity;
import com.yunfu.help.activity.init.info.EditHeightUI;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.bean.LoginBean;
import com.yunfu.help.persenter.LoginP;
import com.yunfu.help.persenter.SmsCodeP;
import com.yunfu.help.utils.LogUtils;
import com.yunfu.help.utils.SPUtil;
import com.yunfu.help.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginUI extends BaseActivity implements SmsCodeP.Face, LoginP.Face {

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
    //计数器
    private Timer mTimer;
    private int time = 0;
    private Handler handler=new Handler();
    //短信验证码的uuid
    private String uuid;

    private SmsCodeP smsCodeP=new SmsCodeP(this,this);
    private LoginP loginP=new LoginP(this);

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
        loginP.setFace(this);

        String des = "还没账号，去<font color=\"#00CFBD\">注册</strong></font>";
        tv_register.setText(Html.fromHtml(des));
        tv_register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }


    @OnClick({R.id.btn_forgetPwd, R.id.btn_login, R.id.tv_register, R.id.tv_loginSms, R.id.tv_loginPwd, R.id.tv_code,R.id.img_wx_login})
    public void onViewClicked(View view) {
        final String phone=et_phone.getText().toString().trim();
        switch (view.getId()) {
            //忘记密码
            case R.id.btn_forgetPwd:
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
                if(time>0){
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    ToastUtil.showLong("请输入手机号");
                    return;
                }
                if(phone.length()!=11){
                    ToastUtil.showLong("请输入正确的手机号");
                    return;
                }
                smsCodeP.getSmsCode(phone);
                break;
            //登录
            case R.id.btn_login:
//                setClass(EditHeightUI.class);
                final String code=et_code.getText().toString().trim();
                if(TextUtils.isEmpty(phone)){
                    ToastUtil.showLong("请输入手机号");
                    return;
                }
                if(phone.length()!=11){
                    ToastUtil.showLong("请输入正确的手机号");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showLong("请输入短信验证码");
                    return;
                }
                loginP.login(code,phone,uuid);
                break;
            //微信登录
            case R.id.img_wx_login:
                 break;
            default:
                break;
        }
    }


    /**
     * 获取到短信验证码
     */
    @Override
    public void getSmsCode(String uuid) {
        this.uuid=uuid;
        time=60;
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (time <= 0) {
                    handler.post(() -> {
                        mTimer.cancel();
                        tv_code.setText("获取验证码");
                    });
                } else {
                    --time;
                    handler.post(() -> tv_code.setText(time + "秒"));
                }
            }
        }, 0, 1000);
    }


    /**
     * 登录成功
     */
    @Override
    public void login(LoginBean.Content content) {
        if(content==null){
            return;
        }
        //存储用户信息
        SPUtil.getInstance(activity).addObject(SPUtil.USER,content);
        /**
         * 判断是否设置过基本信息
         */
        if(content.getStatus()==0){
            setClass(EditHeightUI.class);
        }else{
            setClass(TabActivity.class);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimer!=null){
            mTimer.cancel();
            mTimer.purge();
            mTimer=null;
        }
        handler.removeCallbacksAndMessages(null);
    }
}
