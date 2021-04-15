package com.zhong.notes.controller;

import java.util.ArrayList;

import com.zhong.notes.bean.Note;
import com.zhong.notes.bean.User;
import com.zhong.notes.dao.NoteDaoImpl;
import com.zhong.notes.dao.UserDaoImpl;
import com.zhong.notes.util.EnterSceneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class SearchPageController {

    //获得笔记信息
    private Note note = null;

    //存放choiceBox的所有信息
    ObservableList list = FXCollections.observableArrayList();

    //存放获得的所有笔记信息
    ArrayList<Note> noteList = null;

    //存放获得的所有用户信息
    ArrayList<User> userList = null;

    @FXML //笔记内容文本框
    private TextArea noteText;

    @FXML //搜索按钮
    private Button searchButton;

    @FXML //选择搜索模式框
    private ChoiceBox<String> chooseSearch;

    @FXML //搜索菜单
    private MenuItem enterSearchItem;

    @FXML //搜索框
    private TextField searchText;

    @FXML //搜索得到的笔记或姓名树视图
    private TreeView<String> nameTreeView;

    @FXML //管理菜单
    private MenuItem enterManageItem;

    @FXML //笔记菜单
    private MenuItem enterNoteItem;

    @FXML //搜索窗口
    private BorderPane searchPane;

    @FXML //笔记标题框
    private TextField titleText;

    @FXML //作者文本框
    private TextField authorText;

    @FXML //个人信息菜单
    private MenuItem enterInformationItem;

    @FXML //菜单
    private Menu manageOption;

    @FXML
    public void searchButton(ActionEvent event) {
        //设置TreeView的头节点及信息
        TreeItem<String> treeItem = null;
        if(searchText.getText().isBlank()) {
            treeItem = new TreeItem<>("输入框不能为空！");
            nameTreeView.setRoot(treeItem);
            return;
        }
        if(chooseSearch.getValue().equals("搜索笔记标题")) {
            searchByTitle(treeItem);
        } else if(chooseSearch.getValue().equals("搜索用户昵称")) {
            searchByUser(treeItem);
        }
    }

    /**
     * 进入个人信息界面的按钮事件
     * @param event
     */
    @FXML
    public void enterInformationAction(ActionEvent event) {
        EnterSceneUtil.enterScene("InformationPage.fxml", searchPane);
    }

    /**
     * 进入笔记系统的按钮事件
     * @param event
     */
    @FXML
    public void enterNoteAction(ActionEvent event) {
        EnterSceneUtil.enterScene("NotePage.fxml", searchPane);
    }

    /**
     * 进入搜索系统的按钮事件
     * @param event
     */
    @FXML
    public void enterSearchAction(ActionEvent event) {
        EnterSceneUtil.enterScene("SearchPage.fxml", searchPane);
    }

    /**
     * 进入管理系统的按钮事件
     * @param event
     */
    @FXML
    public void enterManageAction(ActionEvent event) {
        EnterSceneUtil.enterScene("ManagePage.fxml", searchPane);
    }

    /**
     * 初始化方法
     */
    @FXML
    public void initialize() {
        assert noteText != null : "fx:id=\"noteText\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert nameTreeView != null : "fx:id=\"nameTreeView\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert enterManageItem != null : "fx:id=\"enterManageItem\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert enterNoteItem != null : "fx:id=\"enterNoteItem\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert chooseSearch != null : "fx:id=\"chooseSearch\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert searchPane != null : "fx:id=\"notePane\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert titleText != null : "fx:id=\"titleText\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert authorText != null : "fx:id=\"authorText\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert enterSearchItem != null : "fx:id=\"enterSearchItem\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert enterInformationItem != null : "fx:id=\"enterInformationItem\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert manageOption != null : "fx:id=\"manageOption\" was not injected: check your FXML file 'SearchPage.fxml'.";

        //对于普通用户将管理员权限设为不可点击
        if(LoginController.user.getUserType() == 0) {
            manageOption.setDisable(true);
        }

        //显示选择框的内容
        list.removeAll(list);
        chooseSearch.setValue("搜索笔记标题");
        list.addAll("搜索笔记标题", "搜索用户昵称");
        chooseSearch.getItems().addAll(list);
    }

    /**
     * 选择笔记的鼠标点击事件
     * @param mouseEvent
     */
    public void chooseName(MouseEvent mouseEvent) {
        //获得鼠标点击的treeItem
        TreeItem<String> treeItem = nameTreeView.getSelectionModel().getSelectedItem();
        //搜索标题模式
        if(chooseSearch.getValue().equals("搜索笔记标题")) {
            if(treeItem.getChildren().isEmpty()) {
                showNote(treeItem);
            }
        } else if(chooseSearch.getValue().equals("搜索用户昵称")) { //搜索用户模式
            if(treeItem.getChildren().isEmpty()) {
                showNote(treeItem);
            }
        }
    }

    /**
     * 显示笔记信息的方法
     * @param treeItem
     */
    public void showNote(TreeItem<String> treeItem) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        UserDaoImpl udi = new UserDaoImpl();
        //获得当前选择的笔记的信息
        note = ndi.getNote(LoginController.conn, getId(treeItem.getValue()));
        noteText.setText(note.getNoteContent());
        titleText.setText(note.getNoteTitle());
        authorText.setText(udi.getUserName(LoginController.conn, note.getUserId()));
        //显示笔记最后的修改日期
        //将文本框设为不可修改
        titleText.setEditable(false);
        noteText.setEditable(false);
        authorText.setEditable(false);
    }

    /**
     * 根据笔记标题进行搜索的方法
     * @param treeItem
     */
    public void searchByTitle(TreeItem<String> treeItem) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        //获取该用户的笔记
        noteList = (ArrayList<Note>) ndi.getAll(LoginController.conn, "%" + searchText.getText() + "%");
        //将搜索到的笔记标题显示出来，如果查找不到，显示相关提示
        if(noteList.size() == 0) {
            treeItem = new TreeItem<>("找不到相关信息！");
            nameTreeView.setRoot(treeItem);
        } else {
            treeItem = new TreeItem<>("搜索到的笔记");
            nameTreeView.setRoot(treeItem);
            treeItem.setExpanded(true);
            for (int i = 0; i < noteList.size(); i++) {
                //无奈之举，不同用户之间笔记标题可能重复，只能将笔记id添加到后面，方便获取笔记信息
                treeItem.getChildren().add(new TreeItem<>(noteList.get(i).getNoteTitle() + "-" + noteList.get(i).getNoteId()));
            }
        }
    }

    /**
     * 根据用户昵称进行搜索的方法
     * @param treeItem
     */
    public void searchByUser(TreeItem<String> treeItem) {
        UserDaoImpl udi = new UserDaoImpl();
        NoteDaoImpl ndi = new NoteDaoImpl();
        userList = (ArrayList<User>) udi.getAll(LoginController.conn, "%" + searchText.getText() + "%");
        if(userList.size() == 0) {
            treeItem = new TreeItem<>("找不到相关信息！");
            nameTreeView.setRoot(treeItem);
        } else {
            treeItem = new TreeItem<>("搜索到的用户");
            nameTreeView.setRoot(treeItem);
            treeItem.setExpanded(true);
            //显示搜索到的用户名
            for(int i = 0; i < userList.size(); i ++) {
                TreeItem<String> item = new TreeItem<>(userList.get(i).getUserName());
                ArrayList<Note> noteListOfUser = null;
                if(userList.get(i).getUserId() == LoginController.user.getUserId()) {
                    noteListOfUser = (ArrayList<Note>) ndi.getAll(LoginController.conn, userList.get(i).getUserId());
                } else {
                    noteListOfUser = (ArrayList<Note>) ndi.getNotesByUser(LoginController.conn, userList.get(i).getUserId(), 1);
                }
                treeItem.getChildren().add(item);
                //获得每个用户的可见笔记
                for (int j = 0; j < noteListOfUser.size(); j++) {
                    item.getChildren().add(new TreeItem<>(noteListOfUser.get(j).getNoteTitle() + "-" + noteListOfUser.get(j).getNoteId()));
                }
            }
        }
    }
    /**
     * 获取搜索到的笔记后面的id
     * @param noteTitle
     * @return 笔记的id
     */
    public int getId(String noteTitle) {
        String str;
        int i;
        for(i = noteTitle.length() - 1; i >= 0; i --) {
            if((noteTitle.substring(i - 1, i)).equals("-")) {
                break;
            } else {
                //str += noteTitle.substring(i -1, i);
                continue;
            }
        }
        str = noteTitle.substring(i);
        return Integer.parseInt(str);
    }
}
