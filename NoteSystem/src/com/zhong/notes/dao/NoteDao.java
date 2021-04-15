package com.zhong.notes.dao;

import com.zhong.notes.bean.Note;

import java.sql.Connection;
import java.util.List;

/**
 * Note����й�CRUD�ӿ�
 */
public interface NoteDao {

    /**
     * �����û�id��ø��û������бʼǵķ���
     * @param conn
     * @param userId
     * @return ��List����ʽ�����������ıʼ�
     */
    List<Note> getAll(Connection conn, int userId);

    /**
     * ���ݱ�������������رʼ�
     * @param conn
     * @param searchTitle
     * @return ��List����ʽ�����������ıʼ�
     */
    List<Note> getAll(Connection conn, String searchTitle);

    /**
     * ���ݱʼǱ�����û�id��ñʼ���Ϣ�ķ���
     * @param conn
     * @param title
     * @param userId
     * @return Note
     */
    Note getNote(Connection conn, String title, int userId);

    /**
     * ���ݱʼ�id��ñʼ���Ϣ�ķ���
     * @param conn
     * @param noteId
     * @return Note
     */
    Note getNote(Connection conn, int noteId);

    /**
     * �����û�id�ͱʼ����ͻ��������бʼǵķ���
     * @param conn
     * @param userId
     * @param limit
     * @return ��List����ʽ�����������ıʼ�
     */
    List<Note> getNotesByUser(Connection conn, int userId, int limit);

    /**
     * �����û���Ϣ�ͱ����ñʼǵ�����
     * @param conn
     * @param userId
     * @param title
     * @return �ʼ�����������Ϊlong
     */
    long getCount(Connection conn, int userId, String title);

    /**
     * ���ݱʼǱ�����û�idɾ���ʼ�
     * @param conn
     * @param title
     * @param userId
     */
    void delete(Connection conn, String title, int userId);

    /**
     * ���±ʼǵķ���
     * @param conn
     * @param note
     */
    void update(Connection conn, Note note);

    /**
     * ����ʼǵķ���
     * @param conn
     * @param note
     */
    void insert(Connection conn, Note note);
}
