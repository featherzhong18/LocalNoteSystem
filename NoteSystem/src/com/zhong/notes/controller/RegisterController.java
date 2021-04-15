package com.zhong.notes.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.zhong.notes.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Register.fxml的controller类，负责管理注册页面
 */
public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML //URL地址
    private URL location;

    @FXML //注册时的提示
    private Label registerTip;

    @FXML //密码栏
    private PasswordField passwordTextField;

    @FXML //确认密码栏
    private PasswordField confirmPasswordTextField;

    @FXML //用户名栏
    private TextArea userNameTextArea;

    @FXML //注册按钮
    private Button registerButton;

    @FXML //返回登陆按钮
    private Button backButton;

    @FXML //注册成功提示
    private Label registerSuccessTip;

    @FXML //密码不一致时的提示
    private Label passwordTip;

    @FXML //用户名或密码超过长度的提示
    private Label outOfLengthTip;

    @FXML //对注册进行验证的事件处理方法
    public void registerButtonAction(ActionEvent event) throws Exception {
        //重置提示信息为空
        passwordTip.setVisible(false);
        registerTip.setVisible(false);
        registerSuccessTip.setVisible(false);
        outOfLengthTip.setVisible(false);
        //判断输入框是否为空
        if(userNameTextArea.getText().isBlank() || passwordTextField.getText().isBlank() || confirmPasswordTextField.getText().isBlank()) {
            registerTip.setText("用户名和密码不能为空！");
            return;
        }
        //判断用户名是否超过限制
        if(userNameTextArea.getText().length() > 30) {
            outOfLengthTip.setVisible(true);
            outOfLengthTip.setText("输入的用户名长吨超过30，请重新输入！");
            return;
        }
        //判断密码是否超过限制
        if(passwordTextField.getText().length() > 20) {
            outOfLengthTip.setVisible(true);
            outOfLengthTip.setText("输入的密码长度超过20，请重新输入！");
            return;
        }
        //判断用户名是否已经存在
        if(new UserService().isUserExist(userNameTextArea.getText())) {
            registerTip.setText("用户名已被注册！");
            return;
        }
        //判断密码是否一致
        if(! passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
            passwordTip.setText("您输入的密码不一致！");
        } else {
            UserService su = new UserService();
            String tip = su.register(userNameTextArea.getText(), passwordTextField.getText());
            registerSuccessTip.setText(tip);
        }
    }

    /**
     * 返回登录界面的按钮事件
     * @param event
     */
    @FXML
    public void backButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    /**
     * 初始化方法
     */
    @FXML
    public void initialize() {
        assert registerTip != null : "fx:id=\"registerTip\" was not injected: check your FXML file 'Register.fxml'.";
        assert userNameTextArea != null : "fx:id=\"userNameTextArea\" was not injected: check your FXML file 'Register.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'Register.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'Register.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextArea\" was not injected: check your FXML file 'Register.fxml'.";
        assert confirmPasswordTextField != null : "fx:id=\"confirmPasswordTextArea\" was not injected: check your FXML file 'Register.fxml'.";
        assert outOfLengthTip != null : "fx:id=\"outOfLengthTip\" was not injected: check your FXML file 'Register.fxml'.";
    }

}

