package com.zhong.notes.controller;

import java.util.ArrayList;
import java.util.Date;

import com.zhong.notes.bean.User;
import com.zhong.notes.dao.UserDaoImpl;
import com.zhong.notes.util.EnterSceneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ManagePageController {

    //用来储存获得的所有User
    private ObservableList<User> data = FXCollections.observableArrayList();

    @FXML //用户昵称列
    private TableColumn<User, String> userNameColumn;

    @FXML //管理菜单
    private MenuItem enterManageItem;

    @FXML //笔记菜单
    private MenuItem enterNoteItem;

    @FXML //管理窗口
    private BorderPane managePane;

    @FXML //用户id列
    private TableColumn<User, Integer> userIdColumn;

    @FXML //用户注册日期列
    private TableColumn<User, Date> userDateColumn;

    @FXML //用来显示所有用户信息的表格
    private TableView<User> userTable;

    @FXML //搜索菜单
    private MenuItem enterSearchItem;

    @FXML //用户类型列
    private TableColumn<User, Integer> userTypeColumn;

    @FXML //个人信息菜单
    private MenuItem enterInformationItem;

    @FXML //用户密码列
    private TableColumn<User, String> userPasswordColumn;

    @FXML //菜单
    private Menu manageOption;

    @FXML
    public void enterInformationAction(ActionEvent event) {
        EnterSceneUtil.enterScene("InformationPage.fxml", managePane);
    }

    @FXML
    public void enterNoteAction(ActionEvent event) {
        EnterSceneUtil.enterScene("NotePage.fxml", managePane);
    }

    @FXML
    public void enterSearchAction(ActionEvent event) {
        EnterSceneUtil.enterScene("SearchPage.fxml", managePane);
    }

    @FXML
    public void enterManageAction(ActionEvent event) {
        EnterSceneUtil.enterScene("ManagePage.fxml", managePane);
    }

    /**
     * 初始化方法
     */
    @FXML
    public void initialize() {
        assert managePane != null : "fx:id=\"informationPane\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert userNameColumn != null : "fx:id=\"userNameColumn\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert enterManageItem != null : "fx:id=\"enterManageItem\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert enterNoteItem != null : "fx:id=\"enterNoteItem\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert userDateColumn != null : "fx:id=\"userDateColumn\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert userTable != null : "fx:id=\"userTable\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert enterSearchItem != null : "fx:id=\"enterSearchItem\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert userTypeColumn != null : "fx:id=\"userTypeColumn\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert userPasswordColumn != null : "fx:id=\"noteNumberColumn\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert enterInformationItem != null : "fx:id=\"enterInformationItem\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert manageOption != null : "fx:id=\"manageOption\" was not injected: check your FXML file 'ManagePage.fxml'.";

        //对于普通用户将管理员权限设为不可点击
        if(LoginController.user.getUserType() == 0) {
            manageOption.setDisable(true);
        }
        showData();
    }

    /**
     * 显示tableview中用户信息的方法
     */
    public void showData() {
        //获取所有用户
        UserDaoImpl udi = new UserDaoImpl();
        ArrayList<User> userList = (ArrayList<User>) udi.getAll(LoginController.conn);
        for (int i = 0; i < userList.size(); i++) {
            data.add(userList.get(i));
        }
        //显示tableview中的用户信息
        userIdColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        userPasswordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userPassword"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("userType"));
        userDateColumn.setCellValueFactory(new PropertyValueFactory<User, Date>("userDate"));
        userTable.setItems(data);
    }
}
