package com.example.myapplication.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Food;
import com.example.myapplication.FoodContentActivity;
import com.example.myapplication.FoodListActivity;
import com.example.myapplication.LocationActivity;
import com.example.myapplication.R;
import com.example.myapplication.SearchActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import android.support.v4.view.*;

/**
 * Created by 蒋星 on 2017/10/6.
 */

public class Fragment_homepage extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private TextView Location;
    private LinearLayout location,food_search,foodlist,tv_1;
    private LinkedList<Food> mData;
    private ViewPager viewpager;
    private ArrayList<View> viewlist;
    private ArrayList<View> dotlist;
    private int currentIndex=0,viewCount=5;
    //设置显示图片的线性布局的资源ID数组
    private int[] viewsrc={R.layout.page_one,R.layout.page_two,R.layout.page_three,R.layout.page_four,R.layout.page_five};
    //设置线性布局的背景图片的资源ID数组
    private int[] imagesrc={R.drawable.food,R.mipmap.drink,R.mipmap.ic_launcher,R.drawable.food,R.mipmap.drink};
    //设置指针的资源ID数组
    private int[] dotsrc={R.id.dot_1,R.id.dot_2,R.id.dot_3,R.id.dot_4,R.id.dot_5};

    private Handler myHandler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            currentIndex = currentIndex % 5;
            viewpager.setCurrentItem(currentIndex);
            initAllDot();
            dotlist.get(currentIndex).setBackgroundResource(R.drawable.dot_focused);
            currentIndex += 1;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fg_homepage,container,false);
        initView(view);//关联UI控件
        setListener(); //为UI控件设置监听器
        initData(); //初始化数据
        addData(mData);
        viewpager.setCurrentItem(1);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(0);
            }
        }, 0, 3000);
        return view;
    }

    protected void initView(View view){
        tv_1=(LinearLayout) view.findViewById(R.id.foodcommend);  //关联控件
        Location=view.findViewById(R.id.Location);
        location=(LinearLayout)view.findViewById(R.id.location);
        food_search=(LinearLayout)view.findViewById(R.id.food_search);
        foodlist=(LinearLayout)view.findViewById(R.id.foodlist);
        viewpager=(ViewPager)view.findViewById(R.id.viewpager);
        viewlist = new ArrayList<View>();
        dotlist = new ArrayList<View>();
        LayoutInflater li = getActivity().getLayoutInflater();
        //循环获取用于显示图片的View和指示指针View以及给View设置背景图片
        for(int i=0;i<viewCount;i++){
            viewlist.add(li.inflate(viewsrc[i],null,false));
            viewlist.get(i).setBackgroundResource(imagesrc[i]);
            dotlist.add(view.findViewById(dotsrc[i]));
        }
    }

    protected void initData(){
        //初始化美食推送列表数据源
        mData=new LinkedList<Food>();
        mData.add(new Food("重庆火锅",80.0,96.0,R.mipmap.ic_launcher));
        mData.add(new Food("酸菜鱼",70.0,94.0,R.mipmap.ic_launcher));
        mData.add(new Food("辣子鸡",80.0,90.0,R.mipmap.ic_launcher));
        mData.add(new Food("东北麻辣烫",40.0,89.0,R.mipmap.ic_launcher));


        //首页自动定位代码块
        {
            String str="小堕落街";
            Location.setText(str);
        }
    }

    //为UI控件设置监听器
    protected void setListener(){
        tv_1.setOnClickListener(this);
        location.setOnClickListener(this);//定位框设置监听器
        food_search.setOnClickListener(this);//搜索框设置监听器
        viewpager.setAdapter(new mPagerAdapter());
        viewpager.addOnPageChangeListener(this);
    }

    /**
     * 动态添加美食列表至首页
     * @param mData
     */
    private void addData(final LinkedList<Food> mData){
        //轮播图实现

        //美食推送列表实现
        for(int i=0;i<mData.size();i++) {
            final View view=(LinearLayout)LayoutInflater.from(getActivity()).inflate(R.layout.item_list_food, null);

            //从布局文件中通过ID获取UI控件
            TextView foodname=(TextView)view.findViewById(R.id.foodname);
            TextView foodprice=(TextView)view.findViewById(R.id.foodprice);
            TextView foodgrade=(TextView)view.findViewById(R.id.foodgrade);
            ImageView foodimage=(ImageView)view.findViewById(R.id.foodimage);
            foodname.setText(mData.get(i).getFoodname());

            //从数据源获取美食详细数据
            foodprice.setText("¥ "+Double.toString(mData.get(i).getFoodprice()));
            foodgrade.setText("好评度："+Double.toString(mData.get(i).getFoodgrade()));
            foodimage.setBackgroundResource(mData.get(i).getFoodicon());

            //为每一条目设置监听器
            final String name=mData.get(i).getFoodname();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(),FoodContentActivity.class);
                    intent.putExtra("name",name);  //将本条目美食名称通过intent传送至美食内容页面
                    getActivity().startActivity(intent);
                }
            });
            foodlist.addView(view);
        }
    }

    //将所有的View圆点背景设为较淡颜色
    private void initAllDot() {
        for (int i = 0; i < dotlist.size(); i++) {
            dotlist.get(i).setBackgroundResource(R.drawable.dot_normal);
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

    /**
     * 重写OnPageChangeListener中的方法
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        initAllDot();
        dotlist.get(position).setBackgroundResource(R.drawable.dot_focused);
        currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 创建一个适配器用于处理数据
     */
    class mPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewlist.get(position));
            return viewlist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewlist.get(position));
        }

    }
}
