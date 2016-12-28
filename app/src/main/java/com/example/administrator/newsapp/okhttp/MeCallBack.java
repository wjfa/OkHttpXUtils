package com.example.administrator.newsapp.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/6.
 * 新建自定义回调接口继承CallBack
 */

public abstract class MeCallBack implements Callback {
    //拿到主线程的handler
    private Handler handler=new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(final Call call, final IOException e) {
        //通过主线程handler.post方法做一个在主线程中运行的方法
        handler.post(new Runnable() {
            @Override
            public void run() {
                onNewFailureUi(call,e);
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
            if (!response.isSuccessful()){
                throw new IOException("失败 code"+response.code());
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onNewResponeUi(call,response);
                }
            });



    }

    //自定义抽象方法，供外部调用
    public abstract void onNewFailureUi(Call call,IOException e);
    public abstract void onNewResponeUi(Call call, Response response);

}
