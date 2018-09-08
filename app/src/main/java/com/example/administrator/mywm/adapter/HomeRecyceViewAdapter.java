package com.example.administrator.mywm.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.administrator.mywm.R;
import com.example.administrator.mywm.activity.BusinessActivity;
import com.example.administrator.mywm.presenter.net.bean.HomeInfo;
import com.example.administrator.mywm.presenter.net.bean.Promotion;
import com.example.administrator.mywm.utils.Contant;
import com.example.administrator.mywm.utils.LG;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeRecyceViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private HomeInfo data;
    public static final int ITEM_HEAD = 0;
    public static final int ITEM_ELLER = 1;
    public static final int ITEM_DIT = 2;

    public HomeRecyceViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEAD) {
            //头条目
            View view = View.inflate(context, R.layout.item_title, null);
            TitleViewHolder titleViewHolder = new TitleViewHolder(view);
            return titleViewHolder;
        } else if (viewType == ITEM_ELLER) {
            //商家条目
            View view = View.inflate(context, R.layout.item_seller, null);
            SellerViewHolder sellerViewHolder = new SellerViewHolder(view);

            return sellerViewHolder;
        } else {
            //分割线条目
            View view = View.inflate(context, R.layout.item_division, null);
            DividerViewHolder sellerHolder = new DividerViewHolder(view);
            return sellerHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //给viewHolder所有的view绑定数据
        if (position == 0) {
            setTitleData(holder);
        } else if (data.getBody().get(position-1).type == 0) {
            //一般条目
            setSellerData(holder,position-1);
            ((SellerViewHolder)holder).setPosition(position);
        } else {
            //推荐条目
            setRecommendData(holder,position-1);
        }

    }
    private void setTitleData(RecyclerView.ViewHolder holder) {
        //获取顶部轮播图的链接地址
        ArrayList<Promotion> promotionList = data.getHead().getPromotionList();
        for (int i=0;i<promotionList.size();i++){
            Promotion promotion = promotionList.get(i);
            //创建可以显示图片和链接地址的控件
            TextSliderView textSliderView = new TextSliderView(context);
            //设置描述和图片链接地址
            textSliderView.description(promotion.getInfo()).image(promotion.getPic());
            ((TitleViewHolder)holder).slider.addSlider(textSliderView);
        }
    }

    private void setSellerData(RecyclerView.ViewHolder holder, int position) {
        ((SellerViewHolder)holder).tvTitle.setText(data.getBody().get(position).getSeller().getName());
        ((SellerViewHolder)holder).tvCount.setText(data.getBody().get(position).getSeller().getSendPrice());

    }

    private void setRecommendData(RecyclerView.ViewHolder holder, int position) {
        ((DividerViewHolder)holder).tv1.setText(data.getBody().get(position).recommendInfos.get(0));
        ((DividerViewHolder)holder).tv2.setText(data.getBody().get(position).recommendInfos.get(1));
        ((DividerViewHolder)holder).tv3.setText(data.getBody().get(position).recommendInfos.get(2));
        ((DividerViewHolder)holder).tv4.setText(data.getBody().get(position).recommendInfos.get(3));
        ((DividerViewHolder)holder).tv5.setText(data.getBody().get(position).recommendInfos.get(4));
        ((DividerViewHolder)holder).tv6.setText(data.getBody().get(position).recommendInfos.get(5));
    }

    @Override
    public int getItemCount() {
        //依赖于此方法中的条目总数
        if (data != null && data.getBody() != null && data.getHead() != null && data.getBody().size() > 0) {
            return data.getBody().size() + 1;
        }
        return 0;
    }

    //通过索引获取返回的状态码
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_HEAD;
        } else if (data.getBody().get(position - 1).type == 0) {
            return ITEM_ELLER;
        } else {
            return ITEM_DIT;
        }
    }

    public void setData(HomeInfo data) {
        this.data = data;
        //刷新数据
        notifyDataSetChanged();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.slider)
        SliderLayout slider;
        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ArrayList<Promotion> promotionList = data.getHead().getPromotionList();
            for (int i = 0; i < promotionList.size(); i++) {
                TextSliderView textSliderView = new TextSliderView(context);
                // initialize a SliderLayout
                textSliderView
                        .description(promotionList.get(i).getInfo())
                        .image(Contant.change_url(promotionList.get(i).getPic()))
                        .setScaleType(BaseSliderView.ScaleType.Fit);
                //add your extra information
                slider.addSlider(textSliderView);
            }

            slider.setPresetTransformer(SliderLayout.Transformer.Stack);
            slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            slider.setCustomAnimation(new DescriptionAnimation());
            slider.setDuration(4000);
        }
    }

     class SellerViewHolder extends RecyclerView.ViewHolder {
         @BindView(R.id.tvCount)
         TextView tvCount;
         @BindView(R.id.tv_title)
         TextView tvTitle;
         @BindView(R.id.ratingBar)
         RatingBar ratingBar;
         public int position ;
         public  void  setPosition(int position){
             this.position = position;
         }
         public SellerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //每一个item的点击事件
                    Intent intent = new Intent(context, BusinessActivity.class);
                    //需要传递的对象所在的类，需要实现序列化的接口
                    intent.putExtra("seller",data.getBody().get(position).seller);
                    context.startActivity(intent);
                }
            });
        }
    }

     class DividerViewHolder extends RecyclerView.ViewHolder {
         @BindView(R.id.tv1)
         TextView tv1;
         @BindView(R.id.tv2)
         TextView tv2;
         @BindView(R.id.tv3)
         TextView tv3;
         @BindView(R.id.tv4)
         TextView tv4;
         @BindView(R.id.tv5)
         TextView tv5;
         @BindView(R.id.tv6)
         TextView tv6;
        public DividerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
