package com.yunfu.help.http.base;

import com.yunfu.help.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * HTTP拦截器
 * Created by lyn on 2017/4/13.
 */
public class LogInterceptor implements Interceptor {

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        if (request.method().equals("GET")) {
            request = addGetParameter(request);
        }else if(request.method().equals("POST")){
            request = addPostParameter(request);
        }else if(request.method().equals("PUT")){
            request = addPutParameter(request);
        }
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        String body = response.body().string();
        //如果ACCESS_TOKEN失效，自动重新获取一次
        final int code = getCode(body);
        LogUtils.e(String.format("response %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, body));
        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), body)).build();
    }


    /**
     * 传递GET请求的全局参数
     * @param request
     * @return
     */
    public Request addGetParameter(Request request){
        Request newRequest = request.newBuilder()
                .method(request.method(), request.body())
                .url(request.url())
                .build();
        return newRequest;
    }


    /***
     * 添加POST的公共参数
     */
    public Request addPostParameter(Request request) throws IOException {
        request = request.newBuilder()
                .post(request.body())
                .build();
        return request;
    }


    /***
     * 添加PUT的公共参数
     */
    public Request addPutParameter(Request request) throws IOException {
        request = request.newBuilder()
                .put(request.body())
                .build();
        return request;
    }




    public int getCode(String json) {
        int code = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }

}
