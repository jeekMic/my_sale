package com.example.administrator.mywm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mywm.R;
import com.example.administrator.mywm.presenter.net.bean.HomeInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeRecyceViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private HomeInfo data;
    public static final int ITEM_HEAD = 0;
    public static final int ITEM_ELLER = 0;
    public static final int ITEM_DIT = 0;

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
            new SellerViewHolder(view);
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

    private class TitleViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.slider)
//        SliderLayout slider;//实现顶部轮播图的自定义控件

        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private class SellerViewHolder extends RecyclerView.ViewHolder {

        public SellerViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class DividerViewHolder extends RecyclerView.ViewHolder {
        public DividerViewHolder(View itemView) {
            super(itemView);
        }
    }
}
