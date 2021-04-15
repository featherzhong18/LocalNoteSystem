package com.zhong.notes.controller;

/**
 * Sample Skeleton for 'ChangePassword.fxml' Controller Class
 */

import com.zhong.notes.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;

public class ChangePasswordController {

    @FXML //修改密码窗口
    private BorderPane changePasswordPane;

    @FXML //原密码栏
    private PasswordField oldPassword;

    @FXML //新密码栏
    private PasswordField newPassword;

    @FXML //确认新密码栏
    private PasswordField confirmNewPassword;

    @FXML //修改成功提示
    private Label changeSuccessTip;

    @FXML //密码不一致提示
    private Label samePasswordTip;

    @FXML //原密码错误提示
    private Label wrongPasswordTip;

    @FXML //输入为空提示
    private Label noEnterTip;

    @FXML //密码超过限制提示
    private Label outOfLengthTip;

    @FXML //确认修改按钮
    private Button confirmButton;

    @FXML //返回按钮
    private Button backButton;

    /**
     * 确认修改密码按钮事件处理
     * @param event
     */
    @FXML
    public void confirmButtonAction(ActionEvent event) {
        //将提示信息清除
        noEnterTip.setVisible(false);
        samePasswordTip.setVisible(false);
        wrongPasswordTip.setVisible(false);
        outOfLengthTip.setVisible(false);
        changeSuccessTip.setVisible(false);
        //判断输入框是否为空
        if(oldPassword.getText().isBlank() || newPassword.getText().isBlank() || confirmNewPassword.getText().isBlank()) {
            noEnterTip.setVisible(true);
            return;
        }
        //判断原密码是否正确
        if(! oldPassword.getText().equals(LoginController.user.getUserPassword())) {
            wrongPasswordTip.setVisible(true);
            return;
        }
        //判断新密码是否超过限制
        if(newPassword.getText().length() > 20) {
            outOfLengthTip.setVisible(true);
            return;
        }
        //判断两次输入的密码是否一致
        if(! newPassword.getText().equals(confirmNewPassword.getText())) {
            samePasswordTip.setVisible(true);
            return;
        } else {
            //修改密码
            UserService su = new UserService();
            su.changePassword(newPassword.getText(), outOfLengthTip);
            changeSuccessTip.setVisible(true);
        }

    }

    /**
     * 返回按钮事件处理
     * @param event
     */
    @FXML
    public void backButtonAction(ActionEvent event) {
        changePasswordPane.getScene().getWindow().hide();
    }

    /**
     * 初始化方法
     */
    @FXML
    public void initialize() {
        assert confirmNewPassword != null : "fx:id=\"confirmNewPassword\" was not injected: check your FXML file 'ChangePassword.fxml'.";
        assert changeSuccessTip != null : "fx:id=\"changeSuccessTip\" was not injected: check your FXML file 'ChangePassword.fxml'.";
        assert samePasswordTip != null : "fx:id=\"samePasswordTip\" was not injected: check your FXML file 'ChangePassword.fxml'.";
        assert oldPassword != null : "fx:id=\"oldPassword\" was not injected: check your FXML file 'ChangePassword.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ChangePassword.fxml'.";
        assert newPassword != null : "fx:id=\"newPassword\" was not injected: check your FXML file 'ChangePassword.fxml'.";
        assert wrongPasswordTip != null : "fx:id=\"wrongPasswordTip\" was not injected: check your FXML file 'ChangePassword.fxml'.";
        assert noEnterTip != null : "fx:id=\"noEnterTip\" was not injected: check your FXML file 'ChangePassword.fxml'.";
        assert outOfLengthTip != null : "fx:id=\"outOfLengthTip\" was not injected: check your FXML file 'ChangePassword.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'ChangePassword.fxml'.";
        assert changePasswordPane != null : "fx:id=\"changePasswordPane\" was not injected: check your FXML file 'ChangePassword.fxml'.";
    }
}

