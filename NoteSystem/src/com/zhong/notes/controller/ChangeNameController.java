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

    @FXML //�޸��û�������
    private BorderPane changeNamePane;

    @FXML //���û��������
    private TextField newNameText;

    @FXML //�����Ϊ����ʾ
    private Label noEnterTip;

    @FXML //�û����Ѵ�����ʾ
    private Label wrongTip;

    @FXML //�޸ĳɹ���ʾ
    private Label changeSuccessTip;

    @FXML //ȷ�ϰ�ť
    private Button confirmButton;

    @FXML //���ذ�ť
    private Button backButton;

    /**
     * ȷ�ϰ�ť�¼�
     * @param event
     */
    @FXML
    public void confirmButtonAction(ActionEvent event) {
        UserService su = new UserService();
        //����ʾ������
        wrongTip.setVisible(false);
        changeSuccessTip.setVisible(false);
        noEnterTip.setVisible(false);
        //�ж��û������Ƿ�Ϊ��
        if(newNameText.getText().isBlank()) {
            noEnterTip.setVisible(true);
            return ;
        }
        //�ж����û����Ƿ��Ѿ�����
        if(su.isNameExist(newNameText.getText())) wrongTip.setVisible(true);
        else {
            //�޸��û��ǳ�
            su.changeName(LoginController.conn, newNameText.getText(), LoginController.user);
            changeSuccessTip.setVisible(true);
        }
    }

    /**
     * ���ذ�ť�¼�
     * @param event
     */
    @FXML
    public void backButtonAction(ActionEvent event) {
        changeNamePane.getScene().getWindow().hide();
    }

    /**
     * ��ʼ������
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
