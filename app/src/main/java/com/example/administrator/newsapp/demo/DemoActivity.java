package com.example.administrator.newsapp.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newsapp.R;
import com.example.administrator.newsapp.okhttp.MeCallBack;
import com.example.administrator.newsapp.okhttp.OkHttpClient;
import com.example.administrator.newsapp.okhttp.User;
import com.example.administrator.newsapp.retrofit.RetrofitNew;
import com.example.administrator.newsapp.retrofit.UserMult;
import com.example.administrator.newsapp.retrofit.UserReslute;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Callback;

public class DemoActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.edt1)
    EditText edt1;
    @BindView(R.id.edt2)
    EditText edt2;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.edt3)
    EditText edt3;
    @BindView(R.id.edt4)
    EditText edt4;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;

    @BindView(R.id.activity_demo)
    LinearLayout activityDemo;
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:

                //Call进行请求(Get请求)
                Call call = OkHttpClient.getInstance().getDataGet();
                //new一个自己封装的类
                call.enqueue(new MeCallBack() {
                    @Override
                    public void onNewFailureUi(Call call, IOException e) {
                        Log.e("e", "" + e.getMessage());
                    }

                    @Override
                    public void onNewResponeUi(Call call, Response response) {
                        try {
                            String json = response.body().string();
                            Log.e("e", "" + json);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.btn2:
                //Post请求
                User user = new User(edt1.getText().toString(), edt2.getText().toString());
                OkHttpClient.getInstance().getResgertDataPost(user).enqueue(new MeCallBack() {
                    @Override
                    public void onNewFailureUi(Call call, IOException e) {
                        Log.e("e", "" + e.getMessage());
                    }

                    @Override
                    public void onNewResponeUi(Call call, Response response) {
                        try {
                            String json = response.body().string();
                            Log.e("e", "" + json);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;

            case R.id.btn3://利用Retrofit获取网络数据
                //获取请求进行异步处理
                RetrofitNew.getInstance().retrofitApi.getDataGet().enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        //可以做UI的操作(如：Toast)
                        Toast.makeText(DemoActivity.this, "请求成功：" + response.code(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DemoActivity.this, "请求失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                break;
            case R.id.btn4://利用Retrofit注册
                User user1 = new User(edt3.getText().toString(), edt4.getText().toString());
                RetrofitNew.getInstance().retrofitApi.getResgertData(user1).enqueue(new Callback<UserReslute>() {
                    @Override
                    public void onResponse(retrofit2.Call<UserReslute> call, retrofit2.Response<UserReslute> response) {
                        //可以做UI的操作(如：Toast)
                        if (response.isSuccessful()) {
                            UserReslute userReslute = response.body();//获取响应体并返回；
                            if (userReslute != null) {
                                String errmsg = userReslute.getErrmsg();//获取响应体内的属性信息
                                Toast.makeText(DemoActivity.this, "请求成功：" + errmsg, Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<UserReslute> call, Throwable t) {
                        Toast.makeText(DemoActivity.this, "请求失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.btn5://表单获取数据(注册)
                User user2 = new User(edt3.getText().toString(), edt4.getText().toString());
                RetrofitNew.getInstance().retrofitApi.getFileData(user2).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DemoActivity.this, "请求成功：" + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DemoActivity.this, "请求失败：" + t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
                break;
            case R.id.btn6://多部分获取数据（修改昵称）
                UserMult mult = new UserMult("wjf222222222222222222", "xc62", "555", "123456", "0F8EC12223174657B2E842076D54C361");
                RetrofitNew.getInstance().retrofitApi.getMultData(mult).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DemoActivity.this, "请求成功：" + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DemoActivity.this, "请求失败：" + t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
                break;

        }

    }


}
