package com.yunfu.help.http;

import com.yunfu.help.application.MyApplication;
import com.yunfu.help.bean.BaseBean;
import com.yunfu.help.bean.LoginBean;
import com.yunfu.help.bean.OtherInfoBean;
import com.yunfu.help.bean.SmsCode;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface HttpApi {

    @GET(HttpConstant.IP+"")
    Call<SmsCode> getSmsCode(@Query("phone")String phone);

    @POST(HttpConstant.IP+"login")
    Call<LoginBean> login(@Body Map<String,String> map);

    @GET(HttpConstant.IP+"other_info")
    Call<OtherInfoBean> getOtherInfo();

    @PUT(HttpConstant.IP+"register/info")
    Call<BaseBean> setOtherInfo(@Body Map<String,String> map);


}
