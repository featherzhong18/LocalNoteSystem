package com.zhong.notes.dao;

import com.zhong.notes.util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;

/**
 * ��װ�˶��ڲ�ͬ���ͨ�õĲ�������
 */
public abstract class BaseDao<T> {
    private Class<T> clazz = null;

    //�ճ��Ĵ��룬�����е㲻��
    {
        //��ȡ��ǰBaseDao������̳еĸ����еķ���
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        Type[] typeArguments = paramType.getActualTypeArguments();//��ȡ����ķ��Ͳ���
        clazz = (Class<T>) typeArguments[0];//���͵ĵ�һ������
    }

    /**
     * ��ɾ�Ĳ����ķ���
     * @param conn
     * @param sql
     * @param args
     * @return int�Ͳ���
     */
    public int update(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        try {
            //Ԥ����sql���
            ps = conn.prepareStatement(sql);
            //���ռλ��
            for(int i = 0; i < args.length; i ++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //�ر���Դ
            DBUtil.close(null, ps);
        }
        return 0;
    }

    /**
     * ��ѯ���в�ѯ��һ����¼�ķ���
     * @param conn
     * @param sql
     * @param args
     * @return ������Ӧ���͵�һ�����ݣ������ѯ�������򷵻�null
     */
    public T getOneRecord(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Ԥ����sql���
            ps = conn.prepareStatement(sql);
            //���ռλ��
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            //��ý������Ԫ����
            ResultSetMetaData rsmd = rs.getMetaData();
            //��ý�����е�����
            int columnCount = rsmd.getColumnCount();
            if(rs.next()) {
                //����ʵ����
                T t = clazz.getDeclaredConstructor().newInstance();
                for(int i = 0; i < columnCount; i ++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //ͨ��������columnValue��ֵ
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //�ر���Դ
            DBUtil.close(null, ps, rs);
        }
        return null;
    }

    /**
     * ���ر��в�ѯ�Ķ�����¼�ķ���
     * @param conn
     * @param sql
     * @param args
     * @return Arraylist<T>�������ѯ�������򷵻�null
     */
    public List<T> getRecords(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Ԥ����sql���
            ps = conn.prepareStatement(sql);
            //���ռλ��
            for(int i = 0; i < args.length; i ++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            //��ý������Ԫ����
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            //�������϶���
            List<T> list = new ArrayList<>();
            while(rs.next()) {
                T t = clazz.getDeclaredConstructor().newInstance();
                for(int i = 0; i < columnCount; i ++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //ͨ��������columnValue��ֵ
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
            //�ر���Դ
            DBUtil.close(null, ps, rs);
        }
        return null;
    }

    /**
     * ���ڷ��ز�ȷ��ֵ��sql��ѯ�����ķ���
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
