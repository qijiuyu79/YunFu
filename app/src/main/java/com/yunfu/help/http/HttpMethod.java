package com.yunfu.help.http;

import com.yunfu.help.bean.BaseBean;
import com.yunfu.help.bean.LoginBean;
import com.yunfu.help.bean.OtherInfoBean;
import com.yunfu.help.bean.SmsCode;
import com.yunfu.help.callback.NetCallBack;
import com.yunfu.help.http.base.Http;
import com.yunfu.help.utils.DialogUtil;
import com.yunfu.help.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpMethod{


    /**
     * 获取手机验证码
     */
    public static void getSmsCode(String phone,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSmsCode(phone).enqueue(new Callback<SmsCode>() {
            public void onResponse(Call<SmsCode> call, Response<SmsCode> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SmsCode> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 登录
     */
    public static void login(String code,String phone,String uuid,final NetCallBack netCallBack) {
        final Map<String,String> map=new HashMap<>();
        map.put("code",code);
        map.put("phone",phone);
        map.put("uuid",uuid);
        Http.getRetrofit().create(HttpApi.class).login(map).enqueue(new Callback<LoginBean>() {
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<LoginBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取疾病信息
     */
    public static void getOtherInfo(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getOtherInfo().enqueue(new Callback<OtherInfoBean>() {
            public void onResponse(Call<OtherInfoBean> call, Response<OtherInfoBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<OtherInfoBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 设置其他信息设置
     */
    public static void setOtherInfo(String id,String birthday,String bodyHeight,String bodyWeight,String otherInfo,final NetCallBack netCallBack) {
        final Map<String,String> map=new HashMap<>();
        map.put("id",id);
        map.put("birthday",birthday);
        map.put("bodyHeight",bodyHeight);
        map.put("bodyWeight",bodyWeight);
        map.put("otherInfo",otherInfo);
        Http.getRetrofit().create(HttpApi.class).setOtherInfo(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



}
