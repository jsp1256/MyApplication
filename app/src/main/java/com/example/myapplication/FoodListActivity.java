package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
        init();
        mAdapter=new myAdapter();
        foodlist.setAdapter(mAdapter);  //为ListView绑定适配器
    }

    //初始化设置
    protected void init(){
        //初始化推荐美食数据
        mData=new LinkedList<Food>();
        mData.add(new Food("重庆火锅",80.0,96.0,R.mipmap.ic_launcher));
        mData.add(new Food("酸菜鱼",70.0,94.0,R.mipmap.ic_launcher));
        mData.add(new Food("辣子鸡",80.0,90.0,R.mipmap.ic_launcher));
    }

    /**
     * 将适配器整合到Activity内部，便于直接获取数据源，加强模块内聚
     */
    class myAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View covertView, ViewGroup viewGroup) {
            covertView= LayoutInflater.from(FoodListActivity.this).inflate(R.layout.item_list_food,null);
            TextView foodname=(TextView)covertView.findViewById(R.id.foodname);
            TextView foodprice=(TextView)covertView.findViewById(R.id.foodprice);
            TextView foodgrade=(TextView)covertView.findViewById(R.id.foodgrade);
            ImageView foodimage=(ImageView)covertView.findViewById(R.id.foodimage);
            foodname.setText(mData.get(position).getFoodname());
            foodprice.setText("¥ "+Double.toString(mData.get(position).getFoodprice()));
            foodgrade.setText("好评度："+Double.toString(mData.get(position).getFoodgrade()));
            foodimage.setBackgroundResource(mData.get(position).getFoodicon());
            return covertView;
        }
    }
}
