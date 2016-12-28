package com.example.administrator.newsapp.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/7.
 *
 * Retrofit是基于OkHttp再封装的类：
 * 1.Retrofit是利用@注解！！！来进行请求的构建
 * 2.可以有OKHttp中所有的功能。
 * 3.Call模型的功能更加的完善一些.
 * 4.新增加很多的功能：转换器
 * 5.关于Call的后台线程的问题，Retrofit已经处理了
 *
 *
 * 注：结合Retrofit+接口文档一起使用；
 */

public class RetrofitNew {
    private static RetrofitNew mRetrofitNew;
    public final RetrofitApi retrofitApi;

    public RetrofitNew() {
        //日志拦截器
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //初始化OkHttpClient
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(interceptor)//添加拦截器
                .build();

        //1.初始化Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com")//必须有Retrofit添加baseUrl（和网络链接Url进行拼接：baseUtl+Url）
                .client(okHttpClient)//可以不添加：给Retrofit添加OKHttpClient的日志拦截器
                .addConverterFactory(GsonConverterFactory.create())//添加转换器自动将实体类转换成json数据类型
                .build();
        //2.实现自定义的接口的RetrofitApi.class
        retrofitApi = retrofit.create(RetrofitApi.class);
        //3.将自定义retrofitApi创建出的Call可以做同步或异步处理并返回Call出去，我们可以在Activity中完成
    }
    //单实例模式
    public static synchronized RetrofitNew getInstance(){
        if (mRetrofitNew==null){
            mRetrofitNew=new RetrofitNew();
        }
        return mRetrofitNew;
    }
}
