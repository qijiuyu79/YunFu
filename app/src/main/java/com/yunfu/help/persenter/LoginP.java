package com.yunfu.help.persenter;

import android.app.Activity;

import com.yunfu.help.bean.BaseBean;
import com.yunfu.help.bean.LoginBean;
import com.yunfu.help.callback.NetCallBack;
import com.yunfu.help.http.HttpMethod;
import com.yunfu.help.utils.DialogUtil;
import com.yunfu.help.utils.ToastUtil;

public class LoginP {

    private Activity activity;
    private Face face;
    private Face2 face2;

    public LoginP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }

    public void setFace2(Face2 face2){
        this.face2=face2;
    }


    /**
     * 登录
     */
    public void login(String code,String phone,String uuid){
        DialogUtil.showProgress(activity,"登录中");
        HttpMethod.login(code, phone, uuid, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final LoginBean loginBean= (LoginBean) object;
                if(loginBean==null){
                    return;
                }
                if(loginBean.isSussess()){
                    face.login(loginBean.getContent());
                }else{
                    ToastUtil.showLong(loginBean.getMessage());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 设置其他信息设置
     */
    public void setOtherInfo(String id,String birthday,String bodyHeight,String bodyWeight,String otherInfo){
        DialogUtil.showProgress(activity,"登录中");
        HttpMethod.setOtherInfo(id, birthday, bodyHeight, bodyWeight,otherInfo,new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean==null){
                    return;
                }
                if(baseBean.isSussess()){
                }else{
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void login(LoginBean.Content content);
    }

    public interface Face2{
        void setOtherInfo();
    }
}
