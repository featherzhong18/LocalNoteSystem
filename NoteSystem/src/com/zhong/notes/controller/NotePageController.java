package com.zhong.notes.controller;

import java.util.ArrayList;
import java.util.Date;

import com.zhong.notes.bean.Note;
import com.zhong.notes.dao.NoteDaoImpl;
import com.zhong.notes.service.NoteService;
import com.zhong.notes.util.EnterSceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TreeItem;

/**
 * Page.fxml的controller类，负责管理我的笔记系统界面
 */
public class NotePageController {

    //获得显示出来的笔记的信息
    private Note note;

    //新的NoteService对象
    NoteService ns = new NoteService();

    @FXML //笔记内容框
    private TextArea noteText;

    @FXML //删除笔记按钮
    private Button deleteButton;

    @FXML //笔记最新日期标签
    private Label noteDateLabel;

    @FXML //设置笔记可见性的复选框
    private CheckBox limitCheck;

    @FXML //笔记系统窗口
    private BorderPane notePane;

    @FXML //发布笔记按钮
    private Button releaseButton;

    @FXML //搜索菜单
    private MenuItem enterSearchItem;

    @FXML //保存成功提示
    private Label saveSuccessTip;

    @FXML //”更新日期“标签
    private Label dateLabel;

    @FXML //管理菜单
    private MenuItem enterManageItem;

    @FXML //笔记菜单
    private MenuItem enterNoteItem;

    @FXML //刷新按钮
    private Button refreshButton;

    @FXML //显示笔记标题的树视图
    private TreeView<String> showTitle;

    @FXML //修改笔记按钮
    private Button editButton;

    @FXML //笔记标题文本框
    private TextField titleText;

    @FXML //保存按钮
    private Button saveButton;

    @FXML //删除成功提示
    private Label deleteSuccessTip;

    @FXML //标题已存在提示
    private Label existTip;

    @FXML //个人信息菜单
    private MenuItem enterInformationItem;

    @FXML //标题框为空提示
    private Label emptyTextTip;

    @FXML //菜单
    private Menu manageOption;

    /**
     * treeitem的鼠标点击事件
     * @param event
     */
    @FXML
    public void selectItem(MouseEvent event) {
        TreeItem<String> treeItem = showTitle.getSelectionModel().getSelectedItem();
        //将选择点击的treeitem的笔记内容显示出来
        if(treeItem.getChildren().isEmpty()) {
            showNoteDetails(treeItem);
        }
    }

    /**
     * 修改笔记按钮事件
     * @param event
     */
    @FXML
    public void editButtonAction(ActionEvent event) {
        clearTips();
        //将文本框设置为可修改的
        titleText.setEditable(true);
        noteText.setEditable(true);
    }

    /**
     * 删除笔记按钮事件
     * @param event
     */
    @FXML
    public void deleteButtonAction(ActionEvent event) {
        //将提示信息设为不可见
        clearTips();
        //判断是否已经选择笔记
        if(titleText.getText().isBlank()) {
            //若没有选择笔记（笔记标题为空），则显示“请选择笔记再进行操作！”
            emptyTextTip.setVisible(true);
        } else {
            //如果选择笔记，将笔记从数据库删除并显示“删除成功！”
            ns.deleteNote(LoginController.conn, titleText.getText(), LoginController.user.getUserId());
            deleteSuccessTip.setVisible(true);
        }
    }

    /**
     * 保存笔记按钮事件
     * @param event
     */
    @FXML
    public void saveButtonAction(ActionEvent event) {
        clearTips();
        //保存笔记
        saveNote();
    }

    /**
     * 发布笔记按钮事件
     * @param event
     */
    @FXML
    public void releaseButtonAction(ActionEvent event) {
        clearTips();
        //清楚文本框
        titleText.clear();
        noteText.clear();
        limitCheck.setVisible(true);
        limitCheck.setSelected(false);
        titleText.setEditable(true);
        noteText.setEditable(true);
        //创建新的Note对象并设定id为0
        note = new Note();
        note.setNoteId(0);
    }

    /**
     * 进入用户信息界面的按钮事件
     * @param event
     */
    @FXML
    public void enterInformationAction(ActionEvent event) {
        EnterSceneUtil.enterScene("InformationPage.fxml", notePane);
    }

    /**
     * 进入编辑笔记的按钮事件
     * @param event
     */
    @FXML
    public void enterNoteAction(ActionEvent event) {
        EnterSceneUtil.enterScene("NotePage.fxml", notePane);
    }

    /**
     * 进入搜索笔记的按钮事件
     * @param event
     */
    @FXML
    public void enterSearchAction(ActionEvent event) {
        EnterSceneUtil.enterScene("SearchPage.fxml", notePane);
    }

    /**
     * 进入管理员系统的按钮事件
     * @param event
     */
    @FXML
    public void enterManageAction(ActionEvent event) {
        EnterSceneUtil.enterScene("ManagePage.fxml", notePane);
    }

    /**
     * 刷新界面按钮事件
     * @param event
     */
    @FXML
    public void refreshButton(ActionEvent event) {
        EnterSceneUtil.enterScene("NotePage.fxml", notePane);
    }

    /**
     * 初始化方法
     */
    @FXML
    public void initialize() {
        assert noteText != null : "fx:id=\"noteText\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert limitCheck != null : "fx:id=\"limitCheck\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert emptyTextTip != null : "fx:id=\"deleteWrongTip\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert noteDateLabel != null : "fx:id=\"noteDateLabel\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert notePane != null : "fx:id=\"notePane\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert releaseButton != null : "fx:id=\"releaseButton\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert enterSearchItem != null : "fx:id=\"enterSearchItem\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert enterManageItem != null : "fx:id=\"enterManageItem\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert enterNoteItem != null : "fx:id=\"enterNoteItem\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert saveSuccessTip != null : "fx:id=\"saveSuccessTip\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert showTitle != null : "fx:id=\"showTitle\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert editButton != null : "fx:id=\"editButton\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert existTip != null : "fx:id=\"existTip\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert titleText != null : "fx:id=\"titleText\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert deleteSuccessTip != null : "fx:id=\"deleteSuccessTip\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert enterInformationItem != null : "fx:id=\"enterInformationItem\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert manageOption != null : "fx:id=\"manageOption\" was not injected: check your FXML file 'NotePage.fxml'.";
        //对于普通用户将管理员权限设为不可点击
        if(LoginController.user.getUserType() == 0) {
            manageOption.setDisable(true);
        }
        //将笔记标题显示在treeview上
        showTitles();
        //将复选框设为不可见
        limitCheck.setVisible(false);
        //将笔记日期设为不可见
        dateLabel.setVisible(false);
        noteDateLabel.setVisible(false);
    }

    /**
     * 在treeview中显示笔记标题的方法
     */
    public void showTitles() {
        NoteDaoImpl ndi = new NoteDaoImpl();
        //设置TreeView的头节点及信息
        TreeItem<String> noteItem = new TreeItem<>("我的笔记");
        showTitle.setRoot(noteItem);
        noteItem.setExpanded(true);
        //获取该用户的笔记
        ArrayList<Note> noteList = (ArrayList<Note>) ndi.getAll(LoginController.conn, LoginController.user.getUserId());
        //将该用户的笔记标题显示出来
        for(int i = 0; i < noteList.size(); i ++) {
            noteItem.getChildren().add(new TreeItem<>(noteList.get(i).getNoteTitle()));
        }
    }

    /**
     * 显示选择的笔记内容的方法
     * @param treeItem
     */
    public void showNoteDetails(TreeItem<String> treeItem) {
        note = ns.showNote(LoginController.conn, LoginController.user.getUserId(), treeItem);
        //显示笔记内容和标题，日期
        noteText.setText(note.getNoteContent());
        titleText.setText(note.getNoteTitle());
        dateLabel.setVisible(true);
        noteDateLabel.setVisible(true);
        limitCheck.setVisible(true);
        //判断用户类型并显示复选框
        if(note.getNoteLimit() == 0) limitCheck.setSelected(true);
        else limitCheck.setSelected(false);
        //显示笔记最后的修改日期
        noteDateLabel.setText(note.getNoteDate().toString());
        //将文本框设为不可修改
        titleText.setEditable(false);
        noteText.setEditable(false);
        //清楚提示信息
        clearTips();
    }

    public void saveNote() {
        //若没有选择笔记（笔记标题为空），则显示“请选择笔记再进行操作！”
        if(titleText.getText().isBlank()) {
            emptyTextTip.setVisible(true);
        }else {
            //设置新笔记的信息
            note.setNoteContent(noteText.getText());
            note.setNoteDate(new Date());
            note.setUserId(LoginController.user.getUserId());
            //根据复选框选择设定笔记权限
            if(limitCheck.isSelected()) {
                note.setNoteLimit(0); //不可见
            } else {
                note.setNoteLimit(1); //可见
            }
            //如果笔记id为0，即为新发布的笔记
            if(note.getNoteId() == 0) {
                //判断新笔记标题是否存在
                if(ns.isTitleExist(LoginController.conn, LoginController.user.getUserId(), titleText.getText())) {
                    //设置笔记标题并插入数据
                    note.setNoteTitle(titleText.getText());
                    ns.insertNote(LoginController.conn, note);
                } else {
                    //显示已存在的提示并取消保存操作
                    existTip.setVisible(true);
                    return;
                }
            } else {
                //当笔记标题被修改时
                if(! titleText.getText().equals(note.getNoteTitle())) {
                    if(ns.isTitleExist(LoginController.conn, LoginController.user.getUserId(), titleText.getText())) {
                        //设置笔记标题并更新数据
                        note.setNoteTitle(titleText.getText());
                        ns.updateNote(LoginController.conn, note);
                    } else {
                        //显示已存在的提示并取消保存操作
                        existTip.setVisible(true);
                        return;
                    }
                } else { //笔记标题没有被修改时
                    ns.updateNote(LoginController.conn, note);
                }
            }
            //显示保存成功提示信息
            saveSuccessTip.setVisible(true);
            //将文本框返回不可修改的状态
            titleText.setEditable(false);
            noteText.setEditable(false);
        }
    }

    /**
     * 清楚提示信息的方法
     */
    public void clearTips() {
        emptyTextTip.setVisible(false);
        deleteSuccessTip.setVisible(false);
        saveSuccessTip.setVisible(false);
        existTip.setVisible(false);
    }
}

