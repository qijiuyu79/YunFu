package com.yunfu.help.http.base;


import android.os.Handler;
import android.os.Message;
import com.yunfu.help.http.HttpConstant;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lyn on 2017/4/13.
 */

public class Http {
    public static String baseUrl = HttpConstant.IP;
    private static Retrofit retrofit;

    public static void afreshRetrofit(){
        retrofit=null;
    }

    public static synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(baseUrl);
            builder.addConverterFactory(GsonConverterFactory.create());
            OkHttpClient.Builder okBuilder = new OkHttpClient().newBuilder();
            okBuilder.connectTimeout(15, TimeUnit.SECONDS);
            okBuilder.writeTimeout(15, TimeUnit.SECONDS);
            okBuilder.readTimeout(15, TimeUnit.SECONDS);
            okBuilder.addInterceptor(new LogInterceptor());
            builder.callFactory(okBuilder.build());
            retrofit = builder.build();
        }
        return retrofit;
    }



    /**
     * 上传文件--post方式
     */
    public static void upLoadFile(String url, List<File> list, Map<String, String> map, Callback callback) {
        //创建RequestBody
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(null!=list){
            for (int i=0;i<list.size();i++){
                RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), list.get(i));
                builder.addFormDataPart(list.get(i).getName(), list.get(i).getName(), body);
            }
        }
        for (String key : map.keySet()) {
            builder.addFormDataPart(key, map.get(key));
        }
        RequestBody requestBody = builder.build();
        //创建Request
        Request request = new Request.Builder()
                .url(Http.baseUrl + url)
                .post(requestBody)
                .build();
        Call call = new OkHttpClient.Builder().writeTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(callback);
    }


    /**
     * 下载文件
     */
    public static void dowload(String dowloadUrl, final String outPath, final Handler mHandler, final Callback callback) {
        Request request = new Request.Builder().url(dowloadUrl).build();
        Call call = new OkHttpClient.Builder().readTimeout(60 * 5, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                    return;
                }
                InputStream is = response.body().byteStream();
                long length = response.body().contentLength();
                File file = new File(outPath);
                FileOutputStream fos = new FileOutputStream(file);
                int len = 0;
                byte[] buf = new byte[1024];
                long l = 0;
                final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
                while ((len = is.read(buf)) != -1) {
                    l += len;
                    fos.write(buf, 0, len);
                    if (null != mHandler) {
                        format.setMinimumFractionDigits(0);// 设置小数位
                        Message msg = new Message();
                        msg.what = 1000;
                        msg.obj = format.format((float) l / (float) length);
                        mHandler.sendMessage(msg);
                    }
                }
                fos.flush();
                is.close();
                if (callback != null) {
                    callback.onResponse(call, response);
                }
            }

        });
    }
}
