package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 蒋星 on 2017/9/7.
 */

public class Fragment_mine extends Fragment implements View.OnClickListener{
    private TextView tv_1,tv_2,tv_3,tv_4,tv_5,user_name;
    private LinearLayout personMessage;
    private SharedPreferences sharepf;
    Button btn;
    ImageView imageview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_mine, container,false);
        tv_1=view.findViewById(R.id.textview_1);
        tv_2=view.findViewById(R.id.textview_2);
        tv_3=view.findViewById(R.id.textview_3);
        tv_4=view.findViewById(R.id.textview_4);
        tv_5=view.findViewById(R.id.textview_5);
        personMessage=view.findViewById(R.id.message);
        user_name=view.findViewById(R.id.user_name);
        btn=view.findViewById(R.id.exit);
        imageview=view.findViewById(R.id.imagebtn);
        sharepf=getActivity().getSharedPreferences("data",Context.MODE_PRIVATE);
        String name=sharepf.getString("user","unkonwn");
        user_name.setText(name);
        start();
        return view;
    }

    public void start(){
        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
        tv_5.setOnClickListener(this);
        btn.setOnClickListener(this);
        personMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.textview_1:     //“我的收藏”功能实现
               Toast.makeText(getActivity().getApplicationContext(),"我的收藏",Toast.LENGTH_SHORT).show();
               break;
           case R.id.textview_2:     //“美食相册”功能实现
               Toast.makeText(getActivity().getApplicationContext(),"美食相册",Toast.LENGTH_SHORT).show();
               break;
           case R.id.textview_3:     //“我的团约”功能实现
               Toast.makeText(getActivity().getApplicationContext(),"我的团约",Toast.LENGTH_SHORT).show();
               break;
           case R.id.textview_4:     //“我的轨迹”按钮功能实现
               Toast.makeText(getActivity().getApplicationContext(),"我的轨迹",Toast.LENGTH_SHORT).show();
               break;
           case R.id.textview_5:     //“设置”按钮功能实现
               Toast.makeText(getActivity().getApplicationContext(),"设置",Toast.LENGTH_SHORT).show();
               break;
           case R.id.exit:           //“退出”按钮功能实现
               new AlertDialog.Builder(getActivity()).setTitle("提示")
                       .setMessage("确认退出？")
                       .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               SharedPreferences.Editor editer=sharepf.edit();
                               editer.putString("user",null);
                               editer.putString("password",null);
                               editer.putBoolean("autologin",false);
                               editer.commit();
                               MainActivity ma=(MainActivity)getActivity();
                               ma.isLogin=false;
                               ma.setFragment();
                           }
                       })
                       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                           }
                       }).show();
               break;
           case R.id.message:
               Toast.makeText(getActivity().getApplicationContext(),"设置个人信息",Toast.LENGTH_SHORT).show();
               break;
       }
    }
}
