package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.LinkedList;

/**
 * Created by 蒋星 on 2017/10/12.
 */

public class FoodListActivity extends Activity {
    private ListView foodlist;
    private LinkedList<Food> mData;  //Food对象列表
    private myAdapter mAdapter;  //ListView适配器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        foodlist=(ListView)findViewById(R.id.FoodList);  //关联控件
        mData=new LinkedList<Food>();
        mData.add(new Food("重庆火锅",80.0,96.0,R.mipmap.ic_launcher));
        mData.add(new Food("酸菜鱼",70.0,94.0,R.mipmap.ic_launcher));
        mData.add(new Food("辣子鸡",80.0,90.0,R.mipmap.ic_launcher));
        mAdapter=new myAdapter(mData,FoodListActivity.this);
        foodlist.setAdapter(mAdapter);  //为ListView绑定适配器
    }
}
