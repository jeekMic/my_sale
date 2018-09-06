package com.example.administrator.mywm.presenter;

import com.example.administrator.mywm.adapter.HomeRecyceViewAdapter;
import com.example.administrator.mywm.presenter.net.bean.HomeInfo;
import com.example.administrator.mywm.presenter.net.bean.ResponseInfo;
import com.example.administrator.mywm.utils.LG;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;

public class HomePresenter extends BasePresenter {
    public HomeRecyceViewAdapter adapter;
    @Override
    protected void showError(String message) {

    }

    public HomePresenter(HomeRecyceViewAdapter homeRecyceViewAdapter) {
        this.adapter = homeRecyceViewAdapter;
    }

    @Override
    protected void parseJson(String json) {
        //在此处解析JSON
        Gson gson = new Gson();
        HomeInfo homeInfo = gson.fromJson(json, HomeInfo.class);
        LG.I(json);
//        adapter.setData(homeInfo);
//        homeInfo.getHead(); 顶部轮播图需要用到的数据
//          homeInfo.getBody();列表需要用到的数据
    }
    //触发网络请求
    public void getHomeData(String lat,String lon){
        Call<ResponseInfo> homeInfo = responseInfoAPI.getHomeInfo(lat, lon);
        //触发callback中的成功或者失败的回调方法
        homeInfo.enqueue(new CallBackAdapter());
    }
}
