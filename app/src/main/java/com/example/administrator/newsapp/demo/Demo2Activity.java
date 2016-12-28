package com.example.administrator.newsapp.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.newsapp.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class Demo2Activity extends AppCompatActivity {

    @BindView(R.id.btn_okhttps)
    Button btnOkhttps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        ButterKnife.bind(this);
    }

    String url="http://42.62.73.125:8080/yellow/mytest";

    @OnClick(R.id.btn_okhttps)
    public void onClick() {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(Demo2Activity.this, "获取数据失败", Toast.LENGTH_LONG);
            }
            @Override
            public void onResponse(String response, int id) {
                Log.d("TAG","请求数据成功："+response);
            }
        });
    }
}
