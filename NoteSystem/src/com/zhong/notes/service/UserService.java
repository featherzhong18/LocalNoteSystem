package com.zhong.notes.service;

import com.zhong.notes.bean.User;
import com.zhong.notes.controller.LoginController;
import com.zhong.notes.dao.UserDaoImpl;
import com.zhong.notes.util.DBUtil;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

/**
 * User的逻辑处理类
 * @author zhong
 */
public class UserService {

    /**
     * 判断输入的用户、密码和身份是否正确并进行登录
     * @param userName
     * @param password
     * @param userType
     * @return
     * @throws Exception
     */
    public boolean isCorrectLogin(String userName, String password, int userType) throws Exception {
        //获得数据库连接
        LoginController.conn = DBUtil.getConnection();
        //调用getCount方法查询找到的匹配的用户个数，如果为1，则登录成功
        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.getCount(LoginController.conn, userName, password, userType) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将登录的用户的用户信息封装在user中的方法
     * @param userName
     * @param userPassword
     * @return user
     */
    public User setUser(String userName, String userPassword) {
        UserDaoImpl udi = new UserDaoImpl();
        User user = udi.getUser(LoginController.conn, userName, userPassword);
        return user;
    }

    /**
     * 用于注册用户信息并返回相关提示
     * @param userName
     * @param password
     * @return 返回相关正确或错误提示
     */
    public String register(String userName, String password) {
        UserDaoImpl udi = new UserDaoImpl();
        Connection conn;
        try {
            conn = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return "获取数据库连接失败！";
        }
        Timestamp date = new Timestamp(new Date().getTime());
        User user = new User(userName, password, 0, date);
        udi.insert(conn, user.getUserName(), user.getUserPassword(), user.getUserDate());
        return "注册成功！";
    }

    /**
     * 判断用户名是否已经存在的方法
     * @param userName
     * @return
     * @throws Exception
     */
    public boolean isUserExist(String userName) throws Exception {
        Connection conn = DBUtil.getConnection();
        UserDaoImpl udi = new UserDaoImpl();
        if(udi.getCount(conn, userName) == 1) return true;
        else return false;
    }

    /**
     * 修改用户密码的方法
     * @param newPassword
     * @param outOfLengthTip
     */
    public void changePassword(String newPassword, Label outOfLengthTip) {
        UserDaoImpl udi = new UserDaoImpl();
        if(LoginController.user.setUserPassword(newPassword)) {
            outOfLengthTip.setVisible(true);
        } else {
            udi.updatePassword(LoginController.conn, newPassword, LoginController.user.getUserPassword());
        }
    }

    /**
     * 判断用户名是否存在的方法
     * @param newName
     * @return
     */
    public boolean isNameExist(String newName) {
        UserDaoImpl udi = new UserDaoImpl();
        if(udi.getCount(LoginController.conn, newName) == 1) return true;
        else return false;
    }

    /**
     * 修改用户名的方法
     * @param conn
     * @param newName
     * @param user
     */
    public void changeName(Connection conn, String newName, User user) {
        UserDaoImpl udi = new UserDaoImpl();
        udi.updateName(conn, newName, user.getUserName());
        LoginController.user.setUserName(newName);
    }
}
