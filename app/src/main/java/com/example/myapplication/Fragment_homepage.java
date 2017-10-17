package com.example.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 蒋星 on 2017/10/6.
 */

public class Fragment_homepage extends Fragment implements View.OnClickListener{
    TextView tv_1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View view=inflater.inflate(R.layout.fg_homepage,container,false);
        tv_1=(TextView) view.findViewById(R.id.foodcommend);  //关联控件
        tv_1.setOnClickListener(this);  //为控件设置监听器
        return view;
}

    /**
     * 重写监听器onClick()方法
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.foodcommend:
                Intent intent=new Intent(getActivity(),FoodListActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }
}
