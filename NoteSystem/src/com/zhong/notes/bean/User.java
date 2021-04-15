package com.zhong.notes.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ����һ���û���
 */
public class User {
    private int userId;          //�û�id
    private String userName;     //�û���
    private String userPassword; //�û�����
    private int userType;        //�û����ͣ�0������ͨ�û���1�������Ա
    private Date userDate;      //�û�ע�������

    /**
     * �޲����Ĺ��캯��
     */
    public User() {
    }

    /**
     * ���췽��
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
