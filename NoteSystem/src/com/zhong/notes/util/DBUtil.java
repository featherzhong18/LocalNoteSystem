package com.zhong.notes.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的连接及关闭的工具类
 */
public class DBUtil {
    /**
     * 获取数据库连接的方法
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //读取配置文件中的基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //加载驱动
        Class.forName(driverClass);

        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 关闭连接和资源的方法
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
     * 关闭连接和资源的方法
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
