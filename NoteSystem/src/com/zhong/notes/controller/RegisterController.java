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
 * Register.fxml��controller�࣬�������ע��ҳ��
 */
public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML //URL��ַ
    private URL location;

    @FXML //ע��ʱ����ʾ
    private Label registerTip;

    @FXML //������
    private PasswordField passwordTextField;

    @FXML //ȷ��������
    private PasswordField confirmPasswordTextField;

    @FXML //�û�����
    private TextArea userNameTextArea;

    @FXML //ע�ᰴť
    private Button registerButton;

    @FXML //���ص�½��ť
    private Button backButton;

    @FXML //ע��ɹ���ʾ
    private Label registerSuccessTip;

    @FXML //���벻һ��ʱ����ʾ
    private Label passwordTip;

    @FXML //�û��������볬�����ȵ���ʾ
    private Label outOfLengthTip;

    @FXML //��ע�������֤���¼�������
    public void registerButtonAction(ActionEvent event) throws Exception {
        //������ʾ��ϢΪ��
        passwordTip.setVisible(false);
        registerTip.setVisible(false);
        registerSuccessTip.setVisible(false);
        outOfLengthTip.setVisible(false);
        //�ж�������Ƿ�Ϊ��
        if(userNameTextArea.getText().isBlank() || passwordTextField.getText().isBlank() || confirmPasswordTextField.getText().isBlank()) {
            registerTip.setText("�û��������벻��Ϊ�գ�");
            return;
        }
        //�ж��û����Ƿ񳬹�����
        if(userNameTextArea.getText().length() > 30) {
            outOfLengthTip.setVisible(true);
            outOfLengthTip.setText("������û������ֳ���30�����������룡");
            return;
        }
        //�ж������Ƿ񳬹�����
        if(passwordTextField.getText().length() > 20) {
            outOfLengthTip.setVisible(true);
            outOfLengthTip.setText("��������볤�ȳ���20�����������룡");
            return;
        }
        //�ж��û����Ƿ��Ѿ�����
        if(new UserService().isUserExist(userNameTextArea.getText())) {
            registerTip.setText("�û����ѱ�ע�ᣡ");
            return;
        }
        //�ж������Ƿ�һ��
        if(! passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
            passwordTip.setText("����������벻һ�£�");
        } else {
            UserService su = new UserService();
            String tip = su.register(userNameTextArea.getText(), passwordTextField.getText());
            registerSuccessTip.setText(tip);
        }
    }

    /**
     * ���ص�¼����İ�ť�¼�
     * @param event
     */
    @FXML
    public void backButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    /**
     * ��ʼ������
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

