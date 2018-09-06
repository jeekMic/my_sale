package com.example.administrator.mywm.presenter;

import com.example.administrator.mywm.presenter.net.ResponseInfoAPI;
import com.example.administrator.mywm.presenter.net.bean.ResponseInfo;
import com.example.administrator.mywm.utils.Contant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract  class BasePresenter {
    public ResponseInfoAPI responseInfoAPI;
    //网络请求
    private  final HashMap<String, String> errormap;
    public BasePresenter() {
        errormap = new HashMap<>();
        errormap.put("1","服务器数据没有更新");
        errormap.put("2","服务器忙");
        errormap.put("3","服务器挂掉了");
        //初始化 Retrofit对象和
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        /**
         *制定具体的网络请求的实体对象
         * 发送一个网络请求（完整地址，请求方式）
         */
        responseInfoAPI = retrofit.create(ResponseInfoAPI.class);

    }
    class CallBackAdapter implements Callback<ResponseInfo>{

        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
            //请求成功,获取服务器返回
            ResponseInfo body = response.body();
            if (body.getCode().equals("0")){
                //请求成功
                String json = body.getData();
                //抽象的解析json的方法
                parseJson(json);
            }else {
                //请求失败
                String errorMessage = errormap.get(body.getCode());
                onFailure(call,new RuntimeException(errorMessage));

            }
        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
            if (t instanceof RuntimeException){
                //请求失败
                String message = t.getMessage();
                showError(message);
            }
        }
    }
    protected abstract void showError(String message);

    protected abstract void parseJson(String json);
}
