package com.example.administrator.newsapp.okhttp;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2016/12/6.
 */

public class OkHttpClient implements OkhttpApi{


    private final okhttp3.OkHttpClient okHttpClient;
    private Gson gson;

    public OkHttpClient() {
        //日志拦截器
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //将拦截器放到okhttp中
        okHttpClient = new okhttp3.OkHttpClient.Builder()
                .addInterceptor(interceptor)//将拦截器放到okhttp中
                .build();

        //实例化
        gson=new Gson();

    }
    //单实例
    private static OkHttpClient mOkHttpApi;
    public static synchronized OkHttpClient getInstance(){
        if (mOkHttpApi == null){
            mOkHttpApi=new OkHttpClient();
        }
        return mOkHttpApi;
    }

//网络请求数据
    public Call getDataGet(){
        //获取请求（get）
        Request request=new Request.Builder()
                .get()
                .url(OkhttpApi.URL_GET)//链接
              .build();
        //用Call进行请求和响应
      Call call = okHttpClient.newCall(request);
        return call;
    }


    @Override
    public Call getResgertDataPost(User user) {
        //用Gson将实体类转换成json字符串
        RequestBody requestBody=RequestBody.create(null,gson.toJson(user));
        Request request=new Request.Builder()
                .url(OkhttpApi.URL_POST)
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);
    }

}
