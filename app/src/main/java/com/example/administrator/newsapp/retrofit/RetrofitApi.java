package com.example.administrator.newsapp.retrofit;

import com.example.administrator.newsapp.okhttp.User;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/12/7.
 */

public interface RetrofitApi {

    /**
     *Retrofit的使用：需要创建一个接口，来构建请求，通过Retrofit的Create方法添加
     * 通过@注解来构建请求。
     */

//
//    获取请求（get）
//    Request request=new Request.Builder()
//            .get()
//            .url(OkhttpApi.URL_GET)//链接
//            .build();

//    与之相同：
    //网站链接
    static String URL_GET="https://api.github.com/search/repositories?q=language:java&page=1";
    @GET(URL_GET)//请求方式：Get
    Call<ResponseBody> getDataGet();//泛型：请求成功时的响应体“类型”；也可以是实体类及集合；ResponseBody是他们的附体

/**
 * 关于call模型结合转换器：
 * 1.自动解析（实现json和实体类或集合之间的转换）
 * 2.添加依赖：compile 'com.squareup.retrofit2:converter-gson:2.1.0'
 * 设置给Retrofit（在自定义RetrofitNew类中设置）
 *
 *添加请求体@Body：1.实体类User：添加转化器“自动”将实体类转换成json字符串
 *
 * 2.泛型：请求成功时的响应体“类型”：实体类UserReslute
 *
 */
    //post注册(根据Demo所使用的接口)
    static String URL_POST="http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=register";
    @POST(URL_POST)
    Call<UserReslute> getResgertData(@Body User user);

    //表单，注册(根据易淘接口获得路径链接)：泛型应该是响应体中的三个属性，user：实体类，这里只操作以下
    @FormUrlEncoded
    @POST("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=register")
    Call<ResponseBody> getFileData(@Field("")User user);

    //多部分.修改昵称(根据易淘接口获得路径链接) ：泛型应该是响应体中的三个属性，UserMult：实体类，这里只操作以下
    @Multipart
    @POST("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=update")
    Call<ResponseBody> getMultData(@Path("")UserMult userMult);




    /**************************************以下为Retrofit使用讲解**************************************/

    /**
     * @注解；
     * 请求头：1.@Headers({}):添加多个请求头
     *          2.@Header():添加单个请求头，写在方法参数类型中,
     *                       动态修改请求头的信息（如："Accept_Language:en"转换成调用方法时输入String类型的字符串type）
     * 请求体@POST： @Body User user
     *  @GET
     *
     *
     *
     * 注：通过转换器（Converter）转换成对应的对象，如果Retrofit没有添加任何转换器，则只能使用ResponseBody类
     * 如：Call<ResponseBody> getDataGet()；
     */
    @GET()
    @Headers({"Accept_Language:en","X-type:zxc","",""})
    @POST("")
    Call<ResponseBody> getA();
    Call<ResponseBody> getA1(@Header("Accept_Language:en") String type, @Body User user);
    Call<User> getA(@Body User user);

    /**
     * Url处理方面注解：
     * 1.动态可替换块(@Path)：可动态的设置，通过“可替换块”和对方参数的注解来实现
     *  “可替换块“是指"{····}"的字符串，相对应的方法参数使用@Path注解
     *  （如："repos"和“id”转换成调用方法时相对应的输入String类型的字符串rep和Id）
     *  @GET("https://api.github.com/{name}/{23}")
     *  Call<ResponseBody> getB("name",23);
     */
    @GET("https://api.github.com/{repos}/{id}")
    Call<ResponseBody> getB(@Path("repos") String rep,@Path("id") int Id);

    /**
     * Url处理方面注解：Get请求
     * 2.动态查询参数(@Query)：可动态传入参数的设置，相对应的方法参数使用@Query注解
     *  （如："q"和“page”转换成调用方法时相对应的输入String类型的字符串speak和page）
     *  @GET("https://api.github.com/search/repositories?q=Chance:java&page=2")
     *  Call<ResponseBody> getB("Chance:java",2);
     */
    @GET("https://api.github.com/search/repositories")
    Call<ResponseBody> getC(@Query("q") String speak ,@Query("page") int page);
    //传入多个参数时
    Call<ResponseBody> getC1(@QueryMap Map<String,String> maps);

    // 注:以上所有的注解可以一起使用，无影向  他们是为了对链接网址进行改变和拼接
    @GET("https://api.github.com/search/{repo}")
    Call<ResponseBody> getXXX(@Path("repo") String repo,@Query("q") String q,@Query("page") int page);


    /**
     *3.表单注解（@From encoded）和多部分注解(@Multipart)：可以通过注解让方法发送form-encded和multipart数据
     *  一：要发送form-encoded表单数据，在方法上使用@FormUrlEncoded注解。每一个键值对是通过参数上的@Field注解实现的。
     *  二：要发送multipart多部分数据，在方法上使用@Multipart注解。每一个键值对是通过参数上的@Part注解实现的。
     *
     *
     *  注：这里的泛型和参数注解类型是需要根据要求写出对应的，这里只是讲解；
     */
//    表单注解
    @FormUrlEncoded
    @POST(URL_POST)
    Call<ResponseBody> getD(@Field("username")String name, @Field("password") String pass, @FieldMap Map<String,String> map);

//    多部分注解
    @Multipart
    @POST(URL_POST)
    Call<ResponseBody> getE(@Path("photo") String pohot, @Path("description") User user, @PartMap Map<String,User> map);
}
