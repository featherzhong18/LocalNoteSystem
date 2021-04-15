package com.zhong.notes.dao;

import com.zhong.notes.bean.User;

import java.sql.Connection;
import java.util.List;

/**
 * ��װ����User���ͨ�ò����Ľӿ�
 */
public interface UserDao {
    /**
     * ʹ���û�����������û����Ͳ�ѯ��¼�����ķ���
     * @param conn
     * @param userName
     * @param password
     * @return ���ز�ѯ��������
     */
    long getCount(Connection conn, String userName, String password, int userType);

    /**
     * �����û��������û������ķ���
     * @param conn
     * @param userName
     * @return
     */
    long getCount(Connection conn, String userName);

    long getCount(Connection conn);

    /**
     * ���뵽���ݿ���
     * @param conn
     * @param userName
     * @param password
     * @param date
     */
    void insert(Connection conn, String userName, String password, String  date);

    /**
     * �޸��û���Ϣ�ķ���
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
