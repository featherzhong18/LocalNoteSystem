package com.zhong.notes.dao;

import com.zhong.notes.bean.User;

import java.sql.Connection;
import java.util.List;

/**
 * 封装关于User表的通用操作的接口
 */
public interface UserDao {
    /**
     * 使用用户名、密码和用户类型查询记录数量的方法
     * @param conn
     * @param userName
     * @param password
     * @return 返回查询到的数量
     */
    long getCount(Connection conn, String userName, String password, int userType);

    /**
     * 根据用户名查找用户个数的方法
     * @param conn
     * @param userName
     * @return
     */
    long getCount(Connection conn, String userName);

    long getCount(Connection conn);

    /**
     * 插入到数据库中
     * @param conn
     * @param userName
     * @param password
     * @param date
     */
    void insert(Connection conn, String userName, String password, String  date);

    /**
     * 修改用户信息的方法
     * @param conn
     * @param newUserName
     * @param oldUserName
     */
    void updateName(Connection conn, String newUserName, String oldUserName);

    void updatePassword(Connection conn, String newPassword, String oldPassword);

    User getUser(Connection conn, String userName, String userPassword);

    String getUserName(Connection conn, int userId);

    List<User> getAll(Connection conn, String searchName);

    List<User> getAll(Connection conn);
}
