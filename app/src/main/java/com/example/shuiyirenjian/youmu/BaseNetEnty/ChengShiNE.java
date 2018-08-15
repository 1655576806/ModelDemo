package com.example.shuiyirenjian.youmu.BaseNetEnty;

import cn.bmob.v3.BmobObject;

/** 
 * 用户诚信表
 * Created by Administrator on 2017/9/11 0011.
 */

public class ChengShiNE extends BmobObject {
    private String username;//用户账号key
    private int pj;//品级
    private int number;//诚信指数1-81 男41  女40
    private int sum;//积分
    private boolean isH;//是否是黑户

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

    public int getPj() {
        return pj;
    }

    public void setPj(int pj) {
        this.pj = pj;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public boolean isH() {
        return isH;
    }

    public void setH(boolean h) {
        isH = h;
    }
}
