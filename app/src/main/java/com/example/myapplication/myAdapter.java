package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 蒋星 on 2017/10/10.
 */

public class myAdapter extends BaseAdapter{
    private LinkedList<Food> mData;
    private Context mContext;

    public myAdapter(LinkedList<Food> mData,Context mContext) {
        this.mData=mData;
        this.mContext=mContext;
    }
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

    /**
     * 重写getView()方法，实现数据与ListView的绑定
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View covertView=LayoutInflater.from(mContext).inflate(R.layout.item_list_food,null);
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

