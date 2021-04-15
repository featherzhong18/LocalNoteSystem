package com.zhong.notes.dao;

import com.zhong.notes.bean.User;

import java.sql.Connection;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override //使用用户名、密码和用户类型查询记录数量的方法
    public long getCount(Connection conn, String userName, String password, int userType) {
        String sql = "select count(1) from user where user_name=? and user_password=? and user_type=?";
        long count = getValue(conn, sql, userName, password,userType);
        return count;
    }

    @Override //根据用户名查找用户个数的方法
    public long getCount(Connection conn, String userName) {
        String sql = "select count(1) from user where user_name=?";
        long count = getValue(conn, sql, userName);
        return count;
    }

    @Override
    public long getCount(Connection conn) {
        String sql = "select count(1) from user";
        long count = getValue(conn, sql);
        return count;
    }

    @Override //
    public void insert(Connection conn, String userName, String password, String date) {
        String sql = "insert into user(user_name,user_password,user_type,user_date) values(?,?,0,?)";
        update(conn, sql, userName, password, date);
    }

    @Override //更改用户名的方法
    public void updateName(Connection conn, String newUserName, String oldUserName) {
        String sql = "update user set user_name=? where user_name=?";
        update(conn, sql, newUserName, oldUserName);
    }

    @Override //更改密码的方法
    public void updatePassword(Connection conn, String newPassword, String oldPassword) {
        String sql = "update user set user_password=? where user_password=?";
        update(conn, sql, newPassword, oldPassword);
    }

    /**
     * 根据用户名和密码获得数据库中user的数据
     * @param conn
     * @param userName
     * @param userPassword
     * @return
     */
    public User getUser(Connection conn, String userName, String userPassword) {
        String sql = "select user_id as userId," +
                     "user_name as userName," +
                     "user_password as userPassword," +
                     "user_type as userType," +
                     "user_date as userDate" +
                     " from user where user_name=? and user_password=?";
        User user = getOneRecord(conn, sql, userName, userPassword);
        return user;
    }

    @Override
    public String getUserName(Connection conn, int userId) {
        String sql = "select user_name as userName from user where user_id=?";
        User user = getOneRecord(conn, sql, userId);
        return user.getUserName();
    }

    @Override
    public List<User> getAll(Connection conn, String searchName) {
        String sql = "select user_id as userId,user_name as userName,user_password as userPassword,user_type as userType,user_date as userDate from user where user_name like ?";
        List<User> list = getRecords(conn, sql, searchName);
        return list;
    }

    @Override
    public List<User> getAll(Connection conn) {
        String sql = "select user_id as userId,user_name as userName,user_password as userPassword,user_type as userType,user_date as userDate from user";
        List<User> list= getRecords(conn,sql);
        return list;
    }
}
