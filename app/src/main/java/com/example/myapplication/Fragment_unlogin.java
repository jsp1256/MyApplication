package com.example.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 蒋星 on 2017/9/10.
 */

public class Fragment_unlogin extends Fragment implements View.OnClickListener{
    private Button btn_login,btn_register;
    private String username,password;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fg_unlogin,container,false);
        btn_login=view.findViewById(R.id.btn_login);
        btn_register=view.findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        //获取用户登录信息
        username=getActivity().getSharedPreferences("data",Context.MODE_PRIVATE).getString("user","unknown");
        password=getActivity().getSharedPreferences("data",Context.MODE_PRIVATE).getString("password","unknown");
        if(!username.equals("unknown")){
        }
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                fun1();
                break;
            case R.id.btn_register:
                Intent intent_2=new Intent(getActivity(),RegisterActivity.class);
                getActivity().startActivity(intent_2);
                break;
        }
    }

    public void fun1(){
        Intent intent_1=new Intent(getActivity(),LoginActivity.class);
        getActivity().startActivityForResult(intent_1,4);
    }
}
