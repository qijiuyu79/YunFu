package com.yunfu.help.persenter;

import android.app.Activity;
import com.yunfu.help.bean.BaseBean;
import com.yunfu.help.bean.SmsCode;
import com.yunfu.help.callback.NetCallBack;
import com.yunfu.help.http.HttpMethod;
import com.yunfu.help.utils.DialogUtil;
import com.yunfu.help.utils.LogUtils;
import com.yunfu.help.utils.ToastUtil;

public class SmsCodeP {

    private Activity activity;
    private Face face;

    public SmsCodeP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 获取手机验证码
     * @param phone
     */
    public void getSmsCode(String phone){
        DialogUtil.showProgress(activity,"获取验证码中");
        HttpMethod.getSmsCode(phone, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final SmsCode smsCode= (SmsCode) object;
                if(smsCode==null){
                    return;
                }
                if(smsCode.isSussess()){
                    face.getSmsCode(smsCode.getContent().getUuid());
                }else{
                    ToastUtil.showLong(smsCode.getMessage());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getSmsCode(String uuid);
    }

}
