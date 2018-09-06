package com.example.administrator.mywm.presenter.net;

import com.example.administrator.mywm.presenter.net.bean.ResponseInfo;
import com.example.administrator.mywm.utils.Contant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ResponseInfoAPI {
    //指定请求方式
    //请求的完整链接地址
    //请求的方法，请求参数
    //请求的相应结果
    //http://10.0.2.2:8080/TakeoutServiceVersion2

    @GET(Contant.HOME_URL)
    Call<ResponseInfo> getHomeInfo(@Query("latitude") String latitude, @Query("longitude") String longitude);
}
