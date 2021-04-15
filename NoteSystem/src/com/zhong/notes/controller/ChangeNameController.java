package com.zhong.notes.controller;

/**
 * Sample Skeleton for 'ChangeName.fxml' Controller Class
 */

import com.zhong.notes.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ChangeNameController {

    @FXML //修改用户名窗口
    private BorderPane changeNamePane;

    @FXML //新用户名输入框
    private TextField newNameText;

    @FXML //输入框为空提示
    private Label noEnterTip;

    @FXML //用户名已存在提示
    private Label wrongTip;

    @FXML //修改成功提示
    private Label changeSuccessTip;

    @FXML //确认按钮
    private Button confirmButton;

    @FXML //返回按钮
    private Button backButton;

    /**
     * 确认按钮事件
     * @param event
     */
    @FXML
    public void confirmButtonAction(ActionEvent event) {
        UserService su = new UserService();
        //将提示语句清空
        wrongTip.setVisible(false);
        changeSuccessTip.setVisible(false);
        noEnterTip.setVisible(false);
        //判断用户名框是否为空
        if(newNameText.getText().isBlank()) {
            noEnterTip.setVisible(true);
            return ;
        }
        //判断新用户名是否已经存在
        if(su.isNameExist(newNameText.getText())) wrongTip.setVisible(true);
        else {
            //修改用户昵称
            su.changeName(LoginController.conn, newNameText.getText(), LoginController.user);
            changeSuccessTip.setVisible(true);
        }
    }

    /**
     * 返回按钮事件
     * @param event
     */
    @FXML
    public void backButtonAction(ActionEvent event) {
        changeNamePane.getScene().getWindow().hide();
    }

    /**
     * 初始化方法
     */
    @FXML
    void initialize() {
        assert wrongTip != null : "fx:id=\"wrongTip\" was not injected: check your FXML file 'ChangeName.fxml'.";
        assert changeSuccessTip != null : "fx:id=\"changeSuccessTip\" was not injected: check your FXML file 'ChangeName.fxml'.";
        assert noEnterTip != null : "fx:id=\"noEnterTip\" was not injected: check your FXML file 'ChangeName.fxml'.";
        assert newNameText != null : "fx:id=\"newNameText\" was not injected: check your FXML file 'ChangeName.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ChangeName.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'ChangeName.fxml'.";
        assert changeNamePane != null : "fx:id=\"changeNamePane\" was not injected: check your FXML file 'ChangeName.fxml'.";

    }
}
