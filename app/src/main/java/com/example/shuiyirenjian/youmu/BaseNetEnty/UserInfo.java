package com.example.shuiyirenjian.youmu.BaseNetEnty;

import cn.bmob.v3.BmobObject;

/**
 * 用户信息表
 * Created by Administrator on 2017/9/10 0010.
 */

public class UserInfo extends BmobObject {
    public String username;//用户账号key

    public String country;//国家
    public String area;//地区
    public String city;//城市

    public int  maried;//婚姻
    public int  edution;//教育
    public int   sex;//性需求 目标
    public MyUser  getMyuser() {
        return myuser;
    }

    public void setMyuser(MyUser  myuser) {
        this.myuser = myuser;
    }

    private MyUser  myuser;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

     

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMaried() {
        return maried;
    }

    public void setMaried(int maried) {
        this.maried = maried;
    }

    public int getEdution() {
        return edution;
    }

    public void setEdution(int edution) {
        this.edution = edution;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
