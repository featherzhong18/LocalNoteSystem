package com.zhong.notes.dao;

import com.zhong.notes.util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;

/**
 * 封装了对于不同表的通用的操作的类
 */
public abstract class BaseDao<T> {
    private Class<T> clazz = null;

    //照抄的代码，还是有点不懂
    {
        //获取当前BaseDao的子类继承的父类中的泛型
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        Type[] typeArguments = paramType.getActualTypeArguments();//获取父类的泛型参数
        clazz = (Class<T>) typeArguments[0];//泛型的第一个参数
    }

    /**
     * 增删改操作的方法
     * @param conn
     * @param sql
     * @param args
     * @return int型参数
     */
    public int update(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        try {
            //预编译sql语句
            ps = conn.prepareStatement(sql);
            //填充占位符
            for(int i = 0; i < args.length; i ++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            DBUtil.close(null, ps);
        }
        return 0;
    }

    /**
     * 查询表中查询的一条记录的方法
     * @param conn
     * @param sql
     * @param args
     * @return 返回相应类型的一条数据，如果查询不到，则返回null
     */
    public T getOneRecord(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //预编译sql语句
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            //获得结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获得结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()) {
                //进行实例化
                T t = clazz.getDeclaredConstructor().newInstance();
                for(int i = 0; i < columnCount; i ++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //通过反射获得columnValue的值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            DBUtil.close(null, ps, rs);
        }
        return null;
    }

    /**
     * 返回表中查询的多条记录的方法
     * @param conn
     * @param sql
     * @param args
     * @return Arraylist<T>，如果查询不到，则返回null
     */
    public List<T> getRecords(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //预编译sql语句
            ps = conn.prepareStatement(sql);
            //填充占位符
            for(int i = 0; i < args.length; i ++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            //获得结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            //创建集合对象
            List<T> list = new ArrayList<>();
            while(rs.next()) {
                T t = clazz.getDeclaredConstructor().newInstance();
                for(int i = 0; i < columnCount; i ++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //通过反射获得columnValue的值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            DBUtil.close(null, ps, rs);
        }
        return null;
    }

    /**
     * 用于返回不确定值的sql查询操作的方法
     * @param conn
     * @param sql
     * @param args
     * @param <E>
     * @return (E) E
     */
    public <E> E getValue(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < args.length; i ++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()) {
                return (E)rs.getObject(1);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, ps, rs);
        }
        return null;
    }
}
