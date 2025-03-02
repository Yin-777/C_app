package com.example.taobao.model;

import com.example.taobao.model.bean.HomePagerContent;
import com.example.taobao.model.bean.selected_root;
import com.example.taobao.model.bean.tabBean;
import com.example.taobao.model.bean.tb_root;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Service {
    @GET("discovery/categories")
    Call<tabBean> categories();

    @GET
    Call<HomePagerContent> getHomePagerCategories(@Url String url);


    @POST("tpwd")
    Call<tb_root> getword(@Body RequestBody info);

    //TODO 解决网络请求问题报错，可能时retrofit错了，另外可能是bean类写错了boolean类型可能错误
    @GET("shop/r/{page}")
    Call<selected_root> getSelectedInfo(@Path("page") String page);
}
