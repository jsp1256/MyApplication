package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 蒋星 on 2017/9/14.
 */

public class LoginActivity extends Activity implements View.OnClickListener{
    Button btn;
    EditText user,password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn=findViewById(R.id.button_login);
        user=findViewById(R.id.user);
        password=findViewById(R.id.password);
        btn.setOnClickListener(this);
    }

    protected void login(String str1,String str2){         //登录功能实现代码
        if(str1.equals("jiangxing")){
            if(str2.equals("123456")){
                SharedPreferences.Editor editer=getSharedPreferences("data",MODE_PRIVATE).edit();
                editer.putString("user",str1);
                editer.putString("password",str2);
                editer.putBoolean("state",true);
                editer.commit();
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }else
                Toast.makeText(getApplicationContext(),"密码错误",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_SHORT).show();
            password.setText(null);
            user.requestFocus();
            user.selectAll();
        }
    }


    @Override
    public void onClick(View view) {
        String str1=user.getText().toString();
        String str2=password.getText().toString();
        if(str1.isEmpty()){
            Toast.makeText(getApplicationContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
        }else if(str2.isEmpty()){
            Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
        }else
            login(str1,str2);
    }
}
