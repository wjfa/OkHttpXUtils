package com.example.administrator.newsapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.newsapp.okhttp.MeCallBack;
import com.example.administrator.newsapp.okhttp.OkHttpClient;
import com.feicui.app.adapter.NewsAdapter;
import com.feicui.app.adapter.entiry.News;
import com.feicui.app.adapter.entiry.ParserNews;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.IOException;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    private ListView listView;
    private ImageView imgB_home;
    private NewsAdapter newsAdapter;
    private List<News> list;



    String url="http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20\n";


    private PtrClassicFrameLayout refreshLayout;//上拉加载，下拉刷新框架
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.listV_item);
        imgB_home= (ImageView) findViewById(R.id.img_1);
        newsAdapter=new NewsAdapter(this,listView);
        listView.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();


//       initOkHttp();//利用OKHttp网络请求

       initOkHttpUtils();//利用okhttputils进行网络请求


        //创建自定义的拦截器对象进行数据请求打印
        OkHttpClient okhttpApi= OkHttpClient.getInstance();
    }

//利用okhttputils进行网络请求(需要在Application进行实例化)
    private void initOkHttpUtils(){
        RequestCall requestCall= OkHttpUtils.get().url(url).build();
        requestCall.execute(new StringCallback() {
           @Override
           public void onError(Call call, Exception e, int id) {
               Toast.makeText(MainActivity.this, "获取数据失败", Toast.LENGTH_LONG);
           }
           @Override
           public void onResponse(String response, int id) {
               list = ParserNews.parserNewsList(response);
               newsAdapter.appendData(list,false);
               newsAdapter.notifyDataSetChanged();
           }
       });

    }

    //利用okhttp进行网络请求
    private void initOkHttp(){
        //实例化OKHttp
        okhttp3.OkHttpClient okHttpClient=new okhttp3.OkHttpClient();
        //请求网络连接
        Request request=new Request.Builder()
                .url(url)
                .build();
        //创建call存储请求数据
        Call call=okHttpClient.newCall(request);
        //进行异步请求（获得的响应体）
        call.enqueue(new MeCallBack() {
            @Override
            public void onNewFailureUi(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "获取数据失败", Toast.LENGTH_LONG);
            }
            @Override
            public void onNewResponeUi(Call call, Response response) {
                //将响应体进行GSon解析
                try {  //将请求的响应体转换从json字符串
                   String json = response.body().string();
                    list = ParserNews.parserNewsList(json);
                    newsAdapter.appendData(list, false);
                    newsAdapter.notifyDataSetChanged();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
