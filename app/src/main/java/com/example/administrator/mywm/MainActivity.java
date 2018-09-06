package com.example.administrator.mywm;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.administrator.mywm.fragment.HomeFragment;
import com.example.administrator.mywm.fragment.MoreFragment;
import com.example.administrator.mywm.fragment.OrderFragment;
import com.example.administrator.mywm.fragment.UserFragment;
import com.example.administrator.mywm.utils.LG;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Fragment> fragmentList;
    private LinearLayout mainBottomeSwitcherContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();//初始化view
        init();//
        setListener();//设置监听
        View view = mainBottomeSwitcherContainer.getChildAt(0);
        onClick(view);
    }

    private void initView() {
        mainBottomeSwitcherContainer = (LinearLayout) findViewById(R.id.main_bottome_switcher_container);
    }

    /**
     * 初始化fragment
     */
    private void init() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new UserFragment());
        fragmentList.add(new MoreFragment());
    }

    /**
     * 底部tab注册点击事件
     */
    private void setListener() {
        int childCount = mainBottomeSwitcherContainer.getChildCount();
        LG.I(childCount+"");
        for (int i = 0; i < childCount; i++) {
            FrameLayout frameLayout = (FrameLayout) mainBottomeSwitcherContainer.getChildAt(i);
            frameLayout.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        //获得点击的子项
        int index = mainBottomeSwitcherContainer.indexOfChild(v);
        //根据选中的项修改ui
        changeUI(index);
        //根据选中索引,切换fragment
        changeFragment(index);

    }

    private void changeUI(int index) {
        int childCout = mainBottomeSwitcherContainer.getChildCount();
        for (int i=0;i<childCout;i++){
            //获取每一个孩子的节点，如果选中索引和子节点的索引一致，则设置该空间为不可用,不可用对应为蓝色的效果
            FrameLayout frameLayout = (FrameLayout) mainBottomeSwitcherContainer.getChildAt(i);
            if (i == index){
                setEnable(frameLayout,false);
            }else {
                setEnable(frameLayout,true);
            }
        }
    }
/**
 * 是指是否可用控件
 */
private void setEnable(View layout,Boolean isEnable){
    layout.setEnabled(isEnable);
    //如果View是一个容器,即为ViewGroup
    if (layout instanceof ViewGroup){
        //可以查询viewgroup里面的子项有多少个 这里是进行递归操作
        int childCount = ((ViewGroup) layout).getChildCount();
        for (int i=0;i<childCount;i++){
            setEnable(((ViewGroup) layout).getChildAt(i), isEnable);
        }
    }
}


    /**
     * 切换fragment
     */
    private void changeFragment(int index) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, fragmentList.get(index)).commit();
    }
}
