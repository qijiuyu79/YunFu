package com.yunfu.help.http;

import com.yunfu.help.bean.BaseBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HttpApi {

    @GET(HttpConstant.IP+"api/sys/region/getPAllList")
    Call<BaseBean> getProvince();



}
