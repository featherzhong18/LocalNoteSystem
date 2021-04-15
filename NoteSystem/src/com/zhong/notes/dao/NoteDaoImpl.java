package com.zhong.notes.dao;

import com.zhong.notes.bean.Note;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class NoteDaoImpl extends BaseDao<Note> implements NoteDao{
    @Override
    public List<Note> getAll(Connection conn, int userId) {
        String sql = "select note_id as noteId," +
                "note_title as noteTitle," +
                "note_content as noteContent," +
                "note_limit as noteLimit," +
                "note_date as noteDate," +
                "user_id as userId " +
                "from note where user_id=?";
        List<Note> list = getRecords(conn, sql, userId);
        return list;
    }

    @Override
    public List<Note> getAll(Connection conn, String searchTitle) {
        String sql = "select note_id as noteId," +
                "note_title as noteTitle," +
                "note_content as noteContent," +
                "note_limit as noteLimit," +
                "note_date as noteDate," +
                "user_id as userId " +
                "from note where note_title like ? and note_limit=1";
        List<Note> list = getRecords(conn, sql, searchTitle);
        return list;
    }

    @Override
    public Note getNote(Connection conn, String title, int userId) {
        String sql = "select note_id as noteId," +
                "note_title as noteTitle," +
                "note_content as noteContent," +
                "note_limit as noteLimit," +
                "note_date as noteDate," +
                "user_id as userId " +
                "from note where note_title=? and user_id=?";
        Note note = getOneRecord(conn, sql, title, userId);
        return note;
    }

    public Note getNote(Connection conn, int noteId) {
        String sql = "select note_id as noteId," +
                "note_title as noteTitle," +
                "note_content as noteContent," +
                "note_limit as noteLimit," +
                "note_date as noteDate," +
                "user_id as userId " +
                "from note where note_id=?";
        Note note = getOneRecord(conn, sql, noteId);
        return note;
    }

    @Override
    public List<Note> getNotesByUser(Connection conn, int userId, int limit) {
        String sql = "select note_id as noteId," +
                "note_title as noteTitle," +
                "note_content as noteContent," +
                "note_limit as noteLimit," +
                "note_date as noteDate," +
                "user_id as userId " +
                "from note where user_id=? and note_limit=?";
        List<Note> list = getRecords(conn, sql, userId, limit);
        return list;
    }

    @Override
    public long getCount(Connection conn, int userId, String title) {
        String sql = "select count(1) from note where user_id=? and note_title=?";
        long count = getValue(conn, sql, userId, title);
        return count;
    }

    public void delete(Connection conn, String title, int userId) {
        String sql = "delete from note where note_title=? and user_id=?";
        update(conn, sql, title, userId);
    }

    public void update(Connection conn, Note note) {
        String sql = "update note set note_title=?,note_content=?,note_limit=?,note_date=? where note_id=?";
        update(conn, sql, note.getNoteTitle(), note.getNoteContent(), note.getNoteLimit(), note.getNoteDate(), note.getNoteId());
    }

    @Override
    public void insert(Connection conn, Note note) {
        String sql = "insert into note(note_title,note_content,note_limit,note_date,user_id) values(?,?,?,?,?)";
        update(conn, sql, note.getNoteTitle(), note.getNoteContent(),note.getNoteLimit(), note.getNoteDate(), note.getUserId());
    }


}
