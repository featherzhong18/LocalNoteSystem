package com.zhong.notes.controller;

import java.io.IOException;
import java.sql.Connection;

import com.zhong.notes.bean.User;
import com.zhong.notes.service.UserService;
import com.zhong.notes.util.EnterSceneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 * Login.fxml文件的Controller类，负责管理登录页面
 */
public class LoginController {

    //存放choiceBox里的选项
    ObservableList list = FXCollections.observableArrayList();

    //用来获得登录的用户信息
    public static User user = null;

    //用来获取数据库连接
    public static Connection conn = null;

    @FXML //用户名输入框
    private TextField userNameTextField;

    @FXML //密码输入框
    private PasswordField enterPasswordField;

    @FXML //登录按钮
    private Button loginButton;

    @FXML //注册按钮
    private Button registerButton;

    @FXML //登录提示
    private Label loginTip;

    @FXML //登录成功提示
    private Label loginSuccess;

    @FXML //用户管理员复选框
    private ChoiceBox<String> choiceBox;

    @FXML //登录窗口
    private AnchorPane loginPane;

    /**
     * 初始化方法
     */
    @FXML
    public void initialize() {

        assert userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'Login.fxml'.";
        assert enterPasswordField != null : "fx:id=\"enterPasswordField\" was not injected: check your FXML file 'Login.fxml'.";

        //显示choicebox里的默认值及可选值
        list.removeAll(list);
        String user = "用户";
        String admin = "管理员";
        choiceBox.setValue(user);
        list.addAll(user, admin);
        choiceBox.getItems().addAll(list);
    }

    /**
     * 登录按钮的事件处理
     * @param event
     * @throws Exception
     */
    @FXML
    public void loginButtonAction(ActionEvent event) throws Exception {
        UserService su = new UserService();
        //判断文本框和密码框是否为空
        if(userNameTextField.getText().isBlank() || enterPasswordField.getText().isBlank()) {
            loginTip.setVisible(true);
            loginTip.setText("用户名和密码不能为空!");
        //判断用户登录登录是否正确
        } else if(su.isCorrectLogin(userNameTextField.getText(), enterPasswordField.getText(), userIdentity(choiceBox.getValue()))) {
            //将登录的用户的用户信息封装在user中
            user = su.setUser(userNameTextField.getText(), enterPasswordField.getText());
            //显示登录成功并打开新窗口
            loginSuccess.setVisible(true);
            goToHomePage();
        } else if(! su.isCorrectLogin(userNameTextField.getText(), enterPasswordField.getText(), userIdentity(choiceBox.getValue()))) {
            loginTip.setVisible(true);
            loginTip.setText("您输入的用户名或密码错误，请重新输入！");
        }
    }

    /**
     * 注册按钮的事件处理
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void registerButtonAction(ActionEvent actionEvent) throws IOException {
        EnterSceneUtil es = new EnterSceneUtil();
        es.openScene("Register.fxml", "用户注册", 600, 400);
    }

    /**
     * 进入主页的方法
     * @throws IOException
     */
    public void goToHomePage() throws Exception {
        //关闭登录窗口
        loginPane.getScene().getWindow().hide();
        //生成主页窗口
        EnterSceneUtil es = new EnterSceneUtil();
        es.openScene("InformationPage.fxml", "本地笔记系统", 1300, 750);
    }

    /**
     * 判断choicebox里为用户还是管理员的方法
     * @return 返回1则是管理员，返回0则是用户，出错则返回-1
     */
    public int userIdentity(String choiceBox) {
        if(choiceBox.equals("用户")) {
            return 0;
        } else if(choiceBox.equals("管理员")) {
            return 1;
        } else return -1;
    }
}
