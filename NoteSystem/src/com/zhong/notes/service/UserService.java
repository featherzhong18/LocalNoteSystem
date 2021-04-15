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
 * User���߼�������
 * @author zhong
 */
public class UserService {

    /**
     * �ж�������û������������Ƿ���ȷ�����е�¼
     * @param userName
     * @param password
     * @param userType
     * @return
     * @throws Exception
     */
    public boolean isCorrectLogin(String userName, String password, int userType) throws Exception {
        //������ݿ�����
        LoginController.conn = DBUtil.getConnection();
        //����getCount������ѯ�ҵ���ƥ����û����������Ϊ1�����¼�ɹ�
        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.getCount(LoginController.conn, userName, password, userType) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ����¼���û����û���Ϣ��װ��user�еķ���
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
     * ����ע���û���Ϣ�����������ʾ
     * @param userName
     * @param password
     * @return ���������ȷ�������ʾ
     */
    public String register(String userName, String password) {
        UserDaoImpl udi = new UserDaoImpl();
        Connection conn;
        try {
            conn = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return "��ȡ���ݿ�����ʧ�ܣ�";
        }
        Timestamp date = new Timestamp(new Date().getTime());
        User user = new User(userName, password, 0, date);
        udi.insert(conn, user.getUserName(), user.getUserPassword(), user.getUserDate());
        return "ע��ɹ���";
    }

    /**
     * �ж��û����Ƿ��Ѿ����ڵķ���
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
     * �޸��û�����ķ���
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
     * �ж��û����Ƿ���ڵķ���
     * @param newName
     * @return
     */
    public boolean isNameExist(String newName) {
        UserDaoImpl udi = new UserDaoImpl();
        if(udi.getCount(LoginController.conn, newName) == 1) return true;
        else return false;
    }

    /**
     * �޸��û����ķ���
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
