package com.zhong.notes.bean;

import com.zhong.notes.dao.NoteDaoImpl;
import javafx.scene.control.TreeItem;

import java.util.Date;

/**
 * ����һ���ʼ���
 */
public class Note {
    private int noteId;         //�ʼǵ�ID
    private String noteTitle;   //�ʼǵı���
    private String noteContent; //�ʼǵ�����
    private int noteLimit;      //�ʼǵ�Ȩ�����ƣ�1�������ģ�0����˽�е�
    private Date noteDate;      //�ʼǷ������µ�����
    private int userId;         //�ʼ������û���id

    /**
     * �޲����Ĺ��췽��
     */
    public Note() { }

    //getter��setter����
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public int getNoteLimit() {
        return noteLimit;
    }

    public void setNoteLimit(int noteLimit) {
        this.noteLimit = noteLimit;
    }

    public Date getNoteDate() { return noteDate; }

    public void setNoteDate(Date noteDate) { this.noteDate = noteDate; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", noteLimit=" + noteLimit +
                ", noteDate=" + noteDate +
                ", userId=" + userId +
                '}';
    }
}
