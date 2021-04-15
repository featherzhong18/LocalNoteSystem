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
 * Login.fxml�ļ���Controller�࣬��������¼ҳ��
 */
public class LoginController {

    //���choiceBox���ѡ��
    ObservableList list = FXCollections.observableArrayList();

    //������õ�¼���û���Ϣ
    public static User user = null;

    //������ȡ���ݿ�����
    public static Connection conn = null;

    @FXML //�û��������
    private TextField userNameTextField;

    @FXML //���������
    private PasswordField enterPasswordField;

    @FXML //��¼��ť
    private Button loginButton;

    @FXML //ע�ᰴť
    private Button registerButton;

    @FXML //��¼��ʾ
    private Label loginTip;

    @FXML //��¼�ɹ���ʾ
    private Label loginSuccess;

    @FXML //�û�����Ա��ѡ��
    private ChoiceBox<String> choiceBox;

    @FXML //��¼����
    private AnchorPane loginPane;

    /**
     * ��ʼ������
     */
    @FXML
    public void initialize() {

        assert userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'Login.fxml'.";
        assert enterPasswordField != null : "fx:id=\"enterPasswordField\" was not injected: check your FXML file 'Login.fxml'.";

        //��ʾchoicebox���Ĭ��ֵ����ѡֵ
        list.removeAll(list);
        String user = "�û�";
        String admin = "����Ա";
        choiceBox.setValue(user);
        list.addAll(user, admin);
        choiceBox.getItems().addAll(list);
    }

    /**
     * ��¼��ť���¼�����
     * @param event
     * @throws Exception
     */
    @FXML
    public void loginButtonAction(ActionEvent event) throws Exception {
        UserService su = new UserService();
        //�ж��ı����������Ƿ�Ϊ��
        if(userNameTextField.getText().isBlank() || enterPasswordField.getText().isBlank()) {
            loginTip.setVisible(true);
            loginTip.setText("�û��������벻��Ϊ��!");
        //�ж��û���¼��¼�Ƿ���ȷ
        } else if(su.isCorrectLogin(userNameTextField.getText(), enterPasswordField.getText(), userIdentity(choiceBox.getValue()))) {
            //����¼���û����û���Ϣ��װ��user��
            user = su.setUser(userNameTextField.getText(), enterPasswordField.getText());
            //��ʾ��¼�ɹ������´���
            loginSuccess.setVisible(true);
            goToHomePage();
        } else if(! su.isCorrectLogin(userNameTextField.getText(), enterPasswordField.getText(), userIdentity(choiceBox.getValue()))) {
            loginTip.setVisible(true);
            loginTip.setText("��������û���������������������룡");
        }
    }

    /**
     * ע�ᰴť���¼�����
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void registerButtonAction(ActionEvent actionEvent) throws IOException {
        EnterSceneUtil es = new EnterSceneUtil();
        es.openScene("Register.fxml", "�û�ע��", 600, 400);
    }

    /**
     * ������ҳ�ķ���
     * @throws IOException
     */
    public void goToHomePage() throws Exception {
        //�رյ�¼����
        loginPane.getScene().getWindow().hide();
        //������ҳ����
        EnterSceneUtil es = new EnterSceneUtil();
        es.openScene("InformationPage.fxml", "���رʼ�ϵͳ", 1300, 750);
    }

    /**
     * �ж�choicebox��Ϊ�û����ǹ���Ա�ķ���
     * @return ����1���ǹ���Ա������0�����û��������򷵻�-1
     */
    public int userIdentity(String choiceBox) {
        if(choiceBox.equals("�û�")) {
            return 0;
        } else if(choiceBox.equals("����Ա")) {
            return 1;
        } else return -1;
    }
}
