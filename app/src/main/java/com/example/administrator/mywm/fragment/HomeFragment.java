package com.example.administrator.mywm.fragment;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mywm.R;
import com.example.administrator.mywm.adapter.HomeRecyceViewAdapter;
import com.example.administrator.mywm.presenter.HomePresenter;
import com.example.administrator.mywm.utils.LG;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.home_tv_address)
    TextView homeTvAddress;
    @BindView(R.id.ll_title_search)
    LinearLayout llTitleSearch;
    @BindView(R.id.ll_title_container)
    LinearLayout llTitleContainer;
    Unbinder unbinder;
    final float duration = 1000.0f;
    private int sumY = 0;
    private ArgbEvaluator argbEvaluator;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        argbEvaluator = new ArgbEvaluator();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        HomeRecyceViewAdapter homeRecyceViewAdapter = new HomeRecyceViewAdapter(getActivity());
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvHome.setAdapter(homeRecyceViewAdapter);
        //网络请求
        HomePresenter homePresenter = new HomePresenter(homeRecyceViewAdapter);
        homePresenter.getHomeData("","");
        rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //滚动发送改变调用
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               //滚动的时候调用
                //累计dy的值，根据累计值来改变顶部的颜色
                //如果dy累加的结果等于0，没有偏移量，颜色固定
                //如果dy累加结果大于300颜色为定制
                //如果dy的取值范围在0到300，根据滚动的距离多少来改变
                int bgColor = 0X553190E8;
                sumY +=dy;
                if (sumY<=0){
                    //没有移动
                    //开始位置颜色
                    bgColor = 0X553190E8;
                }else if(sumY>1000){
                    //终点颜色
                    bgColor = 0XFF3190E8;

                }else{
                    //设置颜色渐变效果,渐变颜色赋值给背景值
                    LG.I(sumY / duration+"");
                    bgColor = (int) argbEvaluator.evaluate(sumY / duration,0X553190E8,0XFF3190E8);
                }
                llTitleContainer.setBackgroundColor(bgColor);
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
