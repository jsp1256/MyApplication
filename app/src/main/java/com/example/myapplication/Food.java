package com.example.myapplication;

/**
 * Created by 蒋星 on 2017/10/10.
 * 新建Food类，用以保存一条食物项的数据
 */

public class Food {
    private String foodname;  //食物名称
    private Double foodprice,foodgrade;  //食物价格、评分
    private int foodicon;  //食物图片
    public Food(){
    }
    public Food(String foodname,Double foodprice,Double foodgrade,int foodicon){
        this.foodname=foodname;
        this.foodprice=foodprice;
        this.foodgrade=foodgrade;
        this.foodicon=foodicon;
    }
    public String getFoodname(){
        return foodname;
    }
    public Double getFoodprice(){
        return foodprice;
    }
    public Double getFoodgrade(){
        return foodgrade;
    }
    public int getFoodicon(){
        return foodicon;
    }
}
