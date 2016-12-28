package com.feicui.app.adapter.entiry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 */

public class ParserNews {
    //Gson解析
    public static List<News> parserNewsList(String json){
        NewsResult<List<News>> result=new Gson().fromJson(json,new TypeToken<NewsResult<List<News>>>(){
        }.getType());
        return result.getData();

    }
}
