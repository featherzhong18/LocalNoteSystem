package com.zhong.notes.dao;

import com.zhong.notes.bean.Note;

import java.sql.Connection;
import java.util.List;

/**
 * Note类的有关CRUD接口
 */
public interface NoteDao {

    /**
     * 根据用户id获得该用户的所有笔记的方法
     * @param conn
     * @param userId
     * @return 以List的形式返回搜索到的笔记
     */
    List<Note> getAll(Connection conn, int userId);

    /**
     * 根据标题搜索所有相关笔记
     * @param conn
     * @param searchTitle
     * @return 以List的形式返回搜索到的笔记
     */
    List<Note> getAll(Connection conn, String searchTitle);

    /**
     * 根据笔记标题和用户id获得笔记信息的方法
     * @param conn
     * @param title
     * @param userId
     * @return Note
     */
    Note getNote(Connection conn, String title, int userId);

    /**
     * 根据笔记id获得笔记信息的方法
     * @param conn
     * @param noteId
     * @return Note
     */
    Note getNote(Connection conn, int noteId);

    /**
     * 根据用户id和笔记类型获得相关所有笔记的方法
     * @param conn
     * @param userId
     * @param limit
     * @return 以List的形式返回搜索到的笔记
     */
    List<Note> getNotesByUser(Connection conn, int userId, int limit);

    /**
     * 根据用户信息和标题获得笔记的数量
     * @param conn
     * @param userId
     * @param title
     * @return 笔记数量，类型为long
     */
    long getCount(Connection conn, int userId, String title);

    /**
     * 根据笔记标题和用户id删除笔记
     * @param conn
     * @param title
     * @param userId
     */
    void delete(Connection conn, String title, int userId);

    /**
     * 更新笔记的方法
     * @param conn
     * @param note
     */
    void update(Connection conn, Note note);

    /**
     * 插入笔记的方法
     * @param conn
     * @param note
     */
    void insert(Connection conn, Note note);
}
