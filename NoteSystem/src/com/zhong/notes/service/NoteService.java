package com.zhong.notes.service;

import com.zhong.notes.bean.Note;
import com.zhong.notes.dao.NoteDaoImpl;
import javafx.scene.control.TreeItem;

import java.sql.Connection;

/**
 * Note���߼�������
 */
public class NoteService {

    /**
     * ��ö�Ӧ��Note�ķ���
     * @param treeItem
     */
    public Note showNote(Connection conn, int userId, TreeItem<String> treeItem) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        //��õ�ǰѡ��ıʼǵ���Ϣ
        Note note = ndi.getNote(conn, treeItem.getValue(), userId);
        return note;
    }

    /**
     * ɾ���ʼ�
     * @param conn
     * @param title
     * @param userId
     */
    public void deleteNote(Connection conn, String title, int userId) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        ndi.delete(conn, title, userId);
    }

    /**
     * �޸ıʼ�
     * @param conn
     * @param note
     */
    public void updateNote(Connection conn, Note note) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        ndi.update(conn, note);
    }

    /**
     * ���µıʼǲ��뵽���ݿ���
     * @param conn
     * @param note
     */
    public void insertNote(Connection conn, Note note) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        ndi.insert(conn, note);
    }

    /**
     * ��֤�����Ƿ��Ѿ������ڸ��û���
     * @param conn
     * @param userId
     * @param title
     * @return ����true������ⲻ���ڣ����򷵻�false
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
