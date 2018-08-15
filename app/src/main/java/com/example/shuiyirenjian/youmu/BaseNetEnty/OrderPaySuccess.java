package com.example.shuiyirenjian.youmu.BaseNetEnty;

import cn.bmob.v3.BmobObject;

/**
 * 订单支付成功表
 * Created by Administrator on 2017/9/10 0010.
 */

public class OrderPaySuccess   extends BmobObject {
    private String username;//用户账号key
    private String orderid ;//订单号
    private String name ;//商品名
    private int number ;//数量
    private Double price ;//单价
    private Double sum ;//金额
    private Long timetmp;//时间戳
    private  boolean  isNetUSerread;//账号是否已处理

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Long getTimetmp() {
        return timetmp;
    }

    public void setTimetmp(Long timetmp) {
        this.timetmp = timetmp;
    }

    public boolean isNetUSerread() {
        return isNetUSerread;
    }

    public void setNetUSerread(boolean netUSerread) {
        isNetUSerread = netUSerread;
    }
}
