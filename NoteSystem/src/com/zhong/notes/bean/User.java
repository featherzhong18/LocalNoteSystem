package com.zhong.notes.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定义一个用户类
 */
public class User {
    private int userId;          //用户id
    private String userName;     //用户名
    private String userPassword; //用户密码
    private int userType;        //用户类型，0代表普通用户，1代表管理员
    private Date userDate;      //用户注册的日期

    /**
     * 无参数的构造函数
     */
    public User() {
    }

    /**
     * 构造方法
     * @param userName
     * @param userPassword
     * @param userType
     * @param userDate
     */
    public User(String userName, String userPassword, int userType, Date userDate) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
        this.userDate = userDate;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean setUserPassword(String userPassword) {
        if(userPassword.length() > 20) {
            return true;
        } else {
            this.userPassword = userPassword;
            return  false;
        }
    }

    public int getUserType() {
        return userType;
    }

    public String getUserDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(userDate);
        return date;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userType=" + userType +
                ", userDate=" + userDate +
                '}';
    }
}
