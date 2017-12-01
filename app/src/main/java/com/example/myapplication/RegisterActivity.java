package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 蒋星 on 2017/9/18.
 */

public class RegisterActivity extends Activity implements View.OnClickListener{
    private EditText user,password,repassword,tele;
    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindViews();
        btn_register.setOnClickListener(this);
    }

    private void bindViews(){
        user=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        repassword=findViewById(R.id.et_password_confirm);
        tele=findViewById(R.id.et_phone_num);
        btn_register=findViewById(R.id.bt_register);
    }

    @Override
    public void onClick(View view) {
        String str1=user.getText().toString();
        String str2=password.getText().toString();
        String str3=repassword.getText().toString();
        String str4=tele.getText().toString();
        if(str1.length()<4||str1.length()>16){
            Toast.makeText(this,"用户名格式错误",Toast.LENGTH_SHORT).show();
        }else if(str2.length()<6||str2.length()>16){
            Toast.makeText(this, "密码格式错误", Toast.LENGTH_SHORT).show();
        }else if(!str2.equals(str3)){
            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
        }
    }
}
