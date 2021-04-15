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
 * InformationPage.fxml��controller�࣬��������û�������Ϣ����
 */
public class InformationPageController {

    @FXML //������Ϣ����
    private BorderPane informationPane;

    @FXML //�˵�
    private Menu manageOption;

    @FXML //�����˵�
    private MenuItem enterSearchItem;

    @FXML //�޸�����˵�
    private MenuItem changePassword;

    @FXML // fx:id="changeName"
    private MenuItem changeName;

    @FXML //������Ϣ�˵�
    private MenuItem enterInformationItem;

    @FXML //����˵�
    private MenuItem enterManageItem;

    @FXML //�ʼǲ˵�
    private MenuItem enterNoteItem;

    @FXML //�û��ǳƱ�ǩ
    private Label userNameLabel;

    @FXML //�û�ע�����ڱ�ǩ
    private Label userDateLabel;

    @FXML //�û����ͱ�ǩ
    private Label userTypeLabel;

    @FXML //ͷ��ͼƬ
    private ImageView headPhoto;

    /**
     * �޸��ǳƵİ�ť�¼�
     * @param event
     * @throws IOException
     */
    @FXML
    public void changeNameAction(ActionEvent event) throws IOException {
        EnterSceneUtil es = new EnterSceneUtil();
        es.openScene("ChangeName.fxml", "�޸��ǳ�", 600, 400);
    }

    /**
     * �޸����밴ť�¼�
     * @param event
     * @throws IOException
     */
    @FXML
    public void changePasswordAction(ActionEvent event) throws IOException {
        EnterSceneUtil.openScene("ChangePassword.fxml", "�޸�����", 600, 400);
    }

    /**
     * ���������Ϣϵͳ�¼�
     * @param event
     */
    @FXML
    public void enterInformationAction(ActionEvent event) {
        EnterSceneUtil.enterScene("InformationPage.fxml", informationPane);
    }

    /**
     * ����ʼ�ϵͳ�¼�
     * @param event
     */
    @FXML
    public void enterNoteAction(ActionEvent event) {
        EnterSceneUtil.enterScene("NotePage.fxml", informationPane);
    }

    //��������ϵͳ�¼�
    @FXML
    public void enterSearchAction(ActionEvent event) {
        EnterSceneUtil.enterScene("SearchPage.fxml", informationPane);
    }

    /**
     * �������ϵͳ�¼�
     * @param event
     */
    @FXML
    public void enterManageAction(ActionEvent event) {
        EnterSceneUtil.enterScene("ManagePage.fxml", informationPane);
    }

    /**
     * ��ʼ������
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

        //������ͨ�û�������ԱȨ����Ϊ���ɵ��
        if(LoginController.user.getUserType() == 0) {
            manageOption.setDisable(true);
        }

        //��ʾ�û���Ϣ
        userNameLabel.setText(LoginController.user.getUserName());
        userDateLabel.setText(LoginController.user.getUserDate());
        if(LoginController.user.getUserType() == 0) {
            userTypeLabel.setText("��ͨ�û�");
        } else if(LoginController.user.getUserType() == 1) {
            userTypeLabel.setText("����Ա");
        }
    }
}