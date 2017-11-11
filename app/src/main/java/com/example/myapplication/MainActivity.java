package com.example.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Fragment.Fragment_homepage;
import com.example.myapplication.Fragment.Fragment_mine;
import com.example.myapplication.Fragment.Fragment_unlogin;
import com.example.myapplication.Fragment.Fragment_perimeter;


/**
 * changed in2017.9.6
 */
public class MainActivity extends CheckPermissionsActivity implements View.OnClickListener{

    //UI Object
    protected TextView txt_homepage;
    protected TextView txt_perimeter;
    protected TextView txt_user;

    //Fragment Object
    private Fragment_homepage fg1;
    private Fragment_perimeter fg2;
    private Fragment_unlogin fg3;
    private Fragment_mine fg4;
    private FragmentManager fManager;

    //确定是否退出程序的标识变量
    private static boolean isExit=false;
    public static boolean isLogin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        bindViews();
        txt_homepage.performClick();   //模拟一次点击，既进去后选择第一项
    }


    //对登录界面的返回结果进行处理
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode==RESULT_OK) {
            autoLogin();
        }
    }

    public void autoLogin(){
        isLogin=true;
        setSelected();
        txt_user.setSelected(true);
        fg4 = new Fragment_mine();
        fManager.beginTransaction().add(R.id.ly_content, fg4).commit();
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_homepage = (TextView) findViewById(R.id.txt_homepage);
        txt_perimeter = (TextView) findViewById(R.id.txt_perimeter);
        txt_user = (TextView) findViewById(R.id.txt_user);

        //启动时初始化周边模块
        fg2=new Fragment_perimeter();
        fManager.beginTransaction().add(R.id.ly_content,fg2).commit();

        //为底部控件设置监听器
        txt_homepage.setOnClickListener(this);
        txt_perimeter.setOnClickListener(this);
        txt_user.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    protected void setSelected(){
        txt_homepage.setSelected(false);
        txt_perimeter.setSelected(false);
        txt_user.setSelected(false);
    }

    //隐藏所有Fragment
    protected void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }

    //模拟一次点击
    public void setFragment(){
        txt_user.performClick();
    }

    //通过handler修改标识变量
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    //通过两次点击返回键后标识变量的状态确定是否退出程序
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                // 利用handler延迟发送更改状态信息，等待时间为2秒
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                finish();
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);}

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        setSelected();
        switch (v.getId()) {
            case R.id.txt_homepage:
                txt_homepage.setSelected(true);
                if (fg1 == null) {
                    fg1 = new Fragment_homepage();
                    fTransaction.add(R.id.ly_content, fg1);
                } else {
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_perimeter:
                txt_perimeter.setSelected(true);
                fTransaction.show(fg2);
                break;
            case R.id.txt_user:
                txt_user.setSelected(true);
                if(isLogin){
                    fTransaction.show(fg4);
                }else {
                    if (fg3 == null) {
                        fg3 = new Fragment_unlogin();
                        fTransaction.add(R.id.ly_content, fg3);
                    } else
                        fTransaction.show(fg3);
                }
                break;
        }
            fTransaction.commit();
        }
}
