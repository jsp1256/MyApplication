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
public class MainActivity extends CheckPermissionsActivity implements View.OnClickListener {

    //UI Object
    protected TextView tv_main_menu_homepage;   //定义主界面——菜单——主页TextView控件
    protected TextView tv_main_menu_perimeter;  //定义主界面——菜单——周边TextView控件
    protected TextView tv_main_menu_mine;       //定义主界面——菜单——我的TextView控件

    //Fragment Object
    private Fragment_homepage fg_homepage;      //定义Fragment主界面——主页碎片模块
    private Fragment_perimeter fg_perimeter;    //定义Fragment主界面——周边碎片模块
    private Fragment_unlogin fg_unlogin;        //定义Fragment主界面——未登录碎片模块
    private Fragment_mine fg_mine;              //定义Fragment主界面——我的碎片模块
    private FragmentManager fg_manager;         //定义Fragment主界面——管理碎片模块

    //确定是否退出程序的标识变量
    private static boolean isExit = false;
    public static boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉程序左上角的标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fg_manager = getFragmentManager();

        //实例化相关的控件与碎片模块
        init();

        //初始化第一次进入程序后，默认点击主界面——主页碎片模块控件
        tv_main_menu_homepage.performClick();
    }


    //对登录界面的返回结果进行处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            //自动登录个人信息功能
            autoLogin();
        }
    }

    //自动登录个人信息方法
    public void autoLogin() {
        isLogin = true;

        //重置所有主界面碎片模块不被点击
        setSelected();

        //主界面——我的碎片模块被点击并显示
        tv_main_menu_mine.setSelected(true);
        fg_mine = new Fragment_mine();
        fg_manager.beginTransaction().add(R.id.fl_main_content, fg_mine).commit();
    }

    //UI组件初始化与事件绑定
    private void init() {
        tv_main_menu_homepage = (TextView) findViewById(R.id.tv_main_menu_homepage);
        tv_main_menu_perimeter = (TextView) findViewById(R.id.tv_main_menu_perimeter);
        tv_main_menu_mine = (TextView) findViewById(R.id.tv_main_menu_mine);

        //为底部控件设置监听器
        tv_main_menu_homepage.setOnClickListener(this);
        tv_main_menu_perimeter.setOnClickListener(this);
        tv_main_menu_mine.setOnClickListener(this);

        //启动时初始化周边模块
        fg_perimeter = new Fragment_perimeter();
        fg_manager.beginTransaction().add(R.id.fl_main_content, fg_perimeter).commit();

    }

    //重置所有主界面碎片模块的选中状态
    protected void setSelected() {
        tv_main_menu_homepage.setSelected(false);
        tv_main_menu_perimeter.setSelected(false);
        tv_main_menu_mine.setSelected(false);
    }

    //隐藏所有主界面碎片模块
    protected void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg_homepage != null) fragmentTransaction.hide(fg_homepage);
        if (fg_perimeter != null) fragmentTransaction.hide(fg_perimeter);
        if (fg_unlogin != null) fragmentTransaction.hide(fg_unlogin);
        if (fg_mine != null) fragmentTransaction.hide(fg_mine);
    }

    //初始化默认点击主界面——我的碎片模块控件
    public void setFragment() {
        tv_main_menu_mine.performClick();
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
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        //初始化碎片管理者
        FragmentTransaction fTransaction = fg_manager.beginTransaction();
        hideAllFragment(fTransaction);
        setSelected();
        switch (v.getId()) {
            case R.id.tv_main_menu_homepage:
                tv_main_menu_homepage.setSelected(true);
                if (fg_homepage == null) {
                    fg_homepage = new Fragment_homepage();
                    fTransaction.add(R.id.fl_main_content, fg_homepage);
                } else {
                    fTransaction.show(fg_homepage);
                }
                break;
            case R.id.tv_main_menu_perimeter:
                tv_main_menu_perimeter.setSelected(true);
                fTransaction.show(fg_perimeter);
                break;
            case R.id.tv_main_menu_mine:
                tv_main_menu_mine.setSelected(true);
                if (isLogin) {
                    fTransaction.show(fg_mine);
                } else {
                    if (fg_unlogin == null) {
                        fg_unlogin = new Fragment_unlogin();
                        fTransaction.add(R.id.fl_main_content, fg_unlogin);
                    } else
                        fTransaction.show(fg_unlogin);
                }
                break;
        }
        fTransaction.commit();
    }
}
