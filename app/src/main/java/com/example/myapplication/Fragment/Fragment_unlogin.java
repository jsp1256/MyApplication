package com.example.myapplication.Fragment;

import android.app.Fragment;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity;

/**
 * Created by 蒋星 on 2017/9/10.
 */

public class Fragment_unlogin extends Fragment implements View.OnClickListener{
    private Button btn_login,btn_register;
    private String username,password;
    private SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fg_unlogin,container,false);
        //获取用户上次登录的保留信息
        sp=getActivity().getSharedPreferences("data",Context.MODE_PRIVATE);
        if(sp.getBoolean("autologin",false)) {
            //设置自动登录进度条
            final ProgressDialog progressdialog=new ProgressDialog(getActivity());
            progressdialog.setMessage("正在登陆");
            progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressdialog.show();
            //登陆成功后跳转到个人中心界面
            Handler mHander = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    MainActivity ma = (MainActivity) getActivity();
                    ma.autoLogin();
                    progressdialog.dismiss();
                }
            };
            mHander.sendEmptyMessageDelayed(0, 2000);//在2000毫秒后发送消息
        }
        btn_login = view.findViewById(R.id.btn_login);
        btn_register = view.findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                Intent intent_1=new Intent(getActivity(),LoginActivity.class);
                getActivity().startActivityForResult(intent_1,4);
                break;
            case R.id.btn_register:
                Intent intent_2=new Intent(getActivity(),RegisterActivity.class);
                getActivity().startActivity(intent_2);
                break;
        }
    }

}
