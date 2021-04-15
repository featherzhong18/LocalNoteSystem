package com.zhong.notes.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * �������ݿ�����Ӽ��رյĹ�����
 */
public class DBUtil {
    /**
     * ��ȡ���ݿ����ӵķ���
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //��ȡ�����ļ��еĻ�����Ϣ
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //��������
        Class.forName(driverClass);

        //��ȡ����
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * �ر����Ӻ���Դ�ķ���
     * @param conn
     * @param ps
     */
    public static void close(Connection conn, Statement ps) {
        try {
            if(conn != null) conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null) ps.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * �ر����Ӻ���Դ�ķ���
     * @param conn
     * @param ps
     * @param rs
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        try {
            if(conn != null) conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null) ps.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs != null) rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
