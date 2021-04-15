package com.zhong.notes.service;

import com.zhong.notes.bean.Note;
import com.zhong.notes.dao.NoteDaoImpl;
import javafx.scene.control.TreeItem;

import java.sql.Connection;

/**
 * Note的逻辑处理类
 */
public class NoteService {

    /**
     * 获得对应的Note的方法
     * @param treeItem
     */
    public Note showNote(Connection conn, int userId, TreeItem<String> treeItem) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        //获得当前选择的笔记的信息
        Note note = ndi.getNote(conn, treeItem.getValue(), userId);
        return note;
    }

    /**
     * 删除笔记
     * @param conn
     * @param title
     * @param userId
     */
    public void deleteNote(Connection conn, String title, int userId) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        ndi.delete(conn, title, userId);
    }

    /**
     * 修改笔记
     * @param conn
     * @param note
     */
    public void updateNote(Connection conn, Note note) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        ndi.update(conn, note);
    }

    /**
     * 将新的笔记插入到数据库中
     * @param conn
     * @param note
     */
    public void insertNote(Connection conn, Note note) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        ndi.insert(conn, note);
    }

    /**
     * 验证标题是否已经存在在该用户中
     * @param conn
     * @param userId
     * @param title
     * @return 返回true如果标题不存在，否则返回false
     */
    public boolean isTitleExist(Connection conn, int userId, String title) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        if(ndi.getCount(conn, userId, title) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
