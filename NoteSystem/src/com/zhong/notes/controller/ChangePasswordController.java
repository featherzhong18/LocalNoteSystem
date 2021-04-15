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

    @FXML //�޸����봰��
    private BorderPane changePasswordPane;

    @FXML //ԭ������
    private PasswordField oldPassword;

    @FXML //��������
    private PasswordField newPassword;

    @FXML //ȷ����������
    private PasswordField confirmNewPassword;

    @FXML //�޸ĳɹ���ʾ
    private Label changeSuccessTip;

    @FXML //���벻һ����ʾ
    private Label samePasswordTip;

    @FXML //ԭ���������ʾ
    private Label wrongPasswordTip;

    @FXML //����Ϊ����ʾ
    private Label noEnterTip;

    @FXML //���볬��������ʾ
    private Label outOfLengthTip;

    @FXML //ȷ���޸İ�ť
    private Button confirmButton;

    @FXML //���ذ�ť
    private Button backButton;

    /**
     * ȷ���޸����밴ť�¼�����
     * @param event
     */
    @FXML
    public void confirmButtonAction(ActionEvent event) {
        //����ʾ��Ϣ���
        noEnterTip.setVisible(false);
        samePasswordTip.setVisible(false);
        wrongPasswordTip.setVisible(false);
        outOfLengthTip.setVisible(false);
        changeSuccessTip.setVisible(false);
        //�ж�������Ƿ�Ϊ��
        if(oldPassword.getText().isBlank() || newPassword.getText().isBlank() || confirmNewPassword.getText().isBlank()) {
            noEnterTip.setVisible(true);
            return;
        }
        //�ж�ԭ�����Ƿ���ȷ
        if(! oldPassword.getText().equals(LoginController.user.getUserPassword())) {
            wrongPasswordTip.setVisible(true);
            return;
        }
        //�ж��������Ƿ񳬹�����
        if(newPassword.getText().length() > 20) {
            outOfLengthTip.setVisible(true);
            return;
        }
        //�ж���������������Ƿ�һ��
        if(! newPassword.getText().equals(confirmNewPassword.getText())) {
            samePasswordTip.setVisible(true);
            return;
        } else {
            //�޸�����
            UserService su = new UserService();
            su.changePassword(newPassword.getText(), outOfLengthTip);
            changeSuccessTip.setVisible(true);
        }

    }

    /**
     * ���ذ�ť�¼�����
     * @param event
     */
    @FXML
    public void backButtonAction(ActionEvent event) {
        changePasswordPane.getScene().getWindow().hide();
    }

    /**
     * ��ʼ������
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

