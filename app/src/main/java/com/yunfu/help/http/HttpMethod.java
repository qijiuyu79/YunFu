package com.yunfu.help.http;

import com.yunfu.help.bean.BaseBean;
import com.yunfu.help.callback.NetCallBack;
import com.yunfu.help.http.base.Http;
import com.yunfu.help.utils.DialogUtil;
import com.yunfu.help.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpMethod{

    public static int pageSize=10;

    /**
     * 获取所有省集合
     */
    public static void getProvince(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getProvince().enqueue(new Callback<BaseBean>() {
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
