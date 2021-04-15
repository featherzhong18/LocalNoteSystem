package com.zhong.notes.controller;

import java.io.IOException;
import com.zhong.notes.util.EnterSceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * InformationPage.fxml的controller类，负责管理用户个人信息界面
 */
public class InformationPageController {

    @FXML //个人信息窗口
    private BorderPane informationPane;

    @FXML //菜单
    private Menu manageOption;

    @FXML //搜索菜单
    private MenuItem enterSearchItem;

    @FXML //修改密码菜单
    private MenuItem changePassword;

    @FXML // fx:id="changeName"
    private MenuItem changeName;

    @FXML //个人信息菜单
    private MenuItem enterInformationItem;

    @FXML //管理菜单
    private MenuItem enterManageItem;

    @FXML //笔记菜单
    private MenuItem enterNoteItem;

    @FXML //用户昵称标签
    private Label userNameLabel;

    @FXML //用户注册日期标签
    private Label userDateLabel;

    @FXML //用户类型标签
    private Label userTypeLabel;

    @FXML //头像图片
    private ImageView headPhoto;

    /**
     * 修改昵称的按钮事件
     * @param event
     * @throws IOException
     */
    @FXML
    public void changeNameAction(ActionEvent event) throws IOException {
        EnterSceneUtil es = new EnterSceneUtil();
        es.openScene("ChangeName.fxml", "修改昵称", 600, 400);
    }

    /**
     * 修改密码按钮事件
     * @param event
     * @throws IOException
     */
    @FXML
    public void changePasswordAction(ActionEvent event) throws IOException {
        EnterSceneUtil.openScene("ChangePassword.fxml", "修改密码", 600, 400);
    }

    /**
     * 进入个人信息系统事件
     * @param event
     */
    @FXML
    public void enterInformationAction(ActionEvent event) {
        EnterSceneUtil.enterScene("InformationPage.fxml", informationPane);
    }

    /**
     * 进入笔记系统事件
     * @param event
     */
    @FXML
    public void enterNoteAction(ActionEvent event) {
        EnterSceneUtil.enterScene("NotePage.fxml", informationPane);
    }

    //进入搜索系统事件
    @FXML
    public void enterSearchAction(ActionEvent event) {
        EnterSceneUtil.enterScene("SearchPage.fxml", informationPane);
    }

    /**
     * 进入管理系统事件
     * @param event
     */
    @FXML
    public void enterManageAction(ActionEvent event) {
        EnterSceneUtil.enterScene("ManagePage.fxml", informationPane);
    }

    /**
     * 初始化方法
     */
    @FXML
    public void initialize() {
        assert enterSearchItem != null : "fx:id=\"enterSearchItem\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert headPhoto != null : "fx:id=\"headPhoto\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert changePassword != null : "fx:id=\"changePassword\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert informationPane != null : "fx:id=\"informationPane\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert changeName != null : "fx:id=\"changeName\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert enterManageItem != null : "fx:id=\"enterManageItem\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert enterNoteItem != null : "fx:id=\"enterNoteItem\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert userNameLabel != null : "fx:id=\"userNameLabel\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert userDateLabel != null : "fx:id=\"userDateLabel\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert enterInformationItem != null : "fx:id=\"enterInformationItem\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert userTypeLabel != null : "fx:id=\"userTypeLabel\" was not injected: check your FXML file 'InformationPage.fxml'.";
        assert manageOption != null : "fx:id=\"manageOption\" was not injected: check your FXML file 'InformationPage.fxml'.";

        //对于普通用户将管理员权限设为不可点击
        if(LoginController.user.getUserType() == 0) {
            manageOption.setDisable(true);
        }

        //显示用户信息
        userNameLabel.setText(LoginController.user.getUserName());
        userDateLabel.setText(LoginController.user.getUserDate());
        if(LoginController.user.getUserType() == 0) {
            userTypeLabel.setText("普通用户");
        } else if(LoginController.user.getUserType() == 1) {
            userTypeLabel.setText("管理员");
        }
    }
}