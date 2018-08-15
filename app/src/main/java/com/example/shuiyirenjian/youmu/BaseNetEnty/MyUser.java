package com.example.shuiyirenjian.youmu.BaseNetEnty;

import cn.bmob.v3.BmobUser;

/**
 * 用户账号表
 * Created by Administrator on 2017/9/10 0010.
 */
public class MyUser extends BmobUser {
         private String nick;//昵称
	     private Boolean sex;//性别
	     private Integer age;//年龄
         private Integer power;//权限
         private boolean isline;//在线状态

         private String UserInfoObjID;//信息id
         private String ChengShiNEObjID;//诚信id
    public String getUserInfoObjID() {
        return UserInfoObjID;
    }

    public void setUserInfoObjID(String userInfoObjID) {
        UserInfoObjID = userInfoObjID;
    }

    public String getChengShiNEObjID() {
        return ChengShiNEObjID;
    }

    public void setChengShiNEObjID(String chengShiNEObjID) {
        ChengShiNEObjID = chengShiNEObjID;
    }

 
    public boolean isline() {
        return isline;
    }

    public void setIsline(boolean isline) {
        this.isline = isline;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
