package com.example.myapplication.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Food;
import com.example.myapplication.FoodContentActivity;
import com.example.myapplication.FoodListActivity;
import com.example.myapplication.LocationActivity;
import com.example.myapplication.R;
import com.example.myapplication.SearchActivity;

import org.w3c.dom.Text;

import java.util.LinkedList;

/**
 * Created by 蒋星 on 2017/10/6.
 */

public class Fragment_homepage extends Fragment implements View.OnClickListener{
    TextView tv_1,Location;
    LinearLayout location,food_search,foodlist;
    LinkedList<Food> mData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View view=inflater.inflate(R.layout.fg_homepage,container,false);
        tv_1=(TextView) view.findViewById(R.id.foodcommend);  //关联控件
        Location=view.findViewById(R.id.Location);
        location=(LinearLayout)view.findViewById(R.id.location);
        food_search=(LinearLayout)view.findViewById(R.id.food_search);
        foodlist=(LinearLayout)view.findViewById(R.id.foodlist);
        init();
        setListener();
        addFoodList(mData);
        return view;
    }

    protected void init(){
        mData=new LinkedList<Food>();
        mData.add(new Food("重庆火锅",80.0,96.0,R.mipmap.ic_launcher));
        mData.add(new Food("酸菜鱼",70.0,94.0,R.mipmap.ic_launcher));
        mData.add(new Food("辣子鸡",80.0,90.0,R.mipmap.ic_launcher));
        mData.add(new Food("东北麻辣烫",40.0,89.0,R.mipmap.ic_launcher));
        //首页自动定位代码块
        {
            String str="后街";
            Location.setText(str);
        }
    }

    protected void setListener(){
        tv_1.setOnClickListener(this);
        location.setOnClickListener(this);
        food_search.setOnClickListener(this);
    }

    private void addFoodList(final LinkedList<Food> mData){
        for(int i=0;i<mData.size();i++) {
            final View view=(LinearLayout)LayoutInflater.from(getActivity()).inflate(R.layout.item_list_food, null);
            TextView foodname=(TextView)view.findViewById(R.id.foodname);
            TextView foodprice=(TextView)view.findViewById(R.id.foodprice);
            TextView foodgrade=(TextView)view.findViewById(R.id.foodgrade);
            ImageView foodimage=(ImageView)view.findViewById(R.id.foodimage);
            foodname.setText(mData.get(i).getFoodname());
            foodprice.setText("¥ "+Double.toString(mData.get(i).getFoodprice()));
            foodgrade.setText("好评度："+Double.toString(mData.get(i).getFoodgrade()));
            foodimage.setBackgroundResource(mData.get(i).getFoodicon());
            final String name=mData.get(i).getFoodname();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(),FoodContentActivity.class);
                    intent.putExtra("name",name);
                    getActivity().startActivity(intent);
                }
            });
            foodlist.addView(view);
        }
    }

    /**
     * 重写监听器onClick()方法
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.foodcommend:
                intent=new Intent(getActivity(),FoodListActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.location:
                intent=new Intent(getActivity(),LocationActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.food_search:
                intent=new Intent(getActivity(),SearchActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }
}
