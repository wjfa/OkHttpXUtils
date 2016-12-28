package com.example.administrator.newsapp.okhttp;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/12/7.
 *
 * 创建接口对请求链接和数据进行管理
 */

public interface  OkhttpApi {

    //网站链接
    static String URL_GET="https://api.github.com/search/repositories?q=language:java&page=1";

    //
    static String URL_POST="http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=register";
    public Call getDataGet();//获取网络数据(Get请求)

    Call getResgertDataPost(User user);//获取网络数据(Post请求)
}
