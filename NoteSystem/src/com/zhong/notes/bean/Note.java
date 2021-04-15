package com.zhong.notes.bean;

import com.zhong.notes.dao.NoteDaoImpl;
import javafx.scene.control.TreeItem;

import java.util.Date;

/**
 * 定义一个笔记类
 */
public class Note {
    private int noteId;         //笔记的ID
    private String noteTitle;   //笔记的标题
    private String noteContent; //笔记的内容
    private int noteLimit;      //笔记的权限限制，1代表公开的，0代表私有的
    private Date noteDate;      //笔记发布更新的日期
    private int userId;         //笔记所属用户的id

    /**
     * 无参数的构造方法
     */
    public Note() { }

    //getter和setter方法
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
