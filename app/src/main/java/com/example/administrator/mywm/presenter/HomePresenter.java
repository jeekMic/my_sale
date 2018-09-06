package com.example.administrator.mywm.presenter;

import com.example.administrator.mywm.presenter.net.bean.HomeInfo;
import com.example.administrator.mywm.presenter.net.bean.ResponseInfo;
import com.example.administrator.mywm.utils.LG;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;

public class HomePresenter extends BasePresenter {
    @Override
    protected void showError(String message) {

    }

    @Override
    protected void parseJson(String json) {
        //在此处解析JSON
        Gson gson = new Gson();
        gson.fromJson(json, HomeInfo.class)
    }
    //触发网络请求
    public void getHomeData(String lat,String lon){
        Call<ResponseInfo> homeInfo = responseInfoAPI.getHomeInfo(lat, lon);
        //触发callback中的成功或者失败的回调方法
        homeInfo.enqueue(new CallBackAdapter());
    }
}
