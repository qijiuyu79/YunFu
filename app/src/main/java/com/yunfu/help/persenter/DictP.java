package com.yunfu.help.persenter;

import android.app.Activity;

import com.yunfu.help.bean.OtherInfoBean;
import com.yunfu.help.callback.NetCallBack;
import com.yunfu.help.http.HttpMethod;
import com.yunfu.help.utils.DialogUtil;
import com.yunfu.help.utils.ToastUtil;

import java.util.List;

public class DictP {

    private Activity activity;
    private Face face;

    public DictP(Activity activity){
        this.activity=activity;
    }


    public void setFace(Face face){
        this.face=face;
    }


    /**
     * 获取疾病信息
     */
    public void getOtherInfo(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getOtherInfo(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final OtherInfoBean otherInfoBean= (OtherInfoBean) object;
                if(otherInfoBean==null){
                    return;
                }
                if(otherInfoBean.isSussess()){
                    face.getOtherInfo(otherInfoBean.getContent());
                }else{
                    ToastUtil.showLong(otherInfoBean.getMessage());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getOtherInfo(List<String> list);
    }
}
