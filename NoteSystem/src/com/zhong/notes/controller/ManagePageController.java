package com.zhong.notes.controller;

import java.util.ArrayList;
import java.util.Date;

import com.zhong.notes.bean.User;
import com.zhong.notes.dao.UserDaoImpl;
import com.zhong.notes.util.EnterSceneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ManagePageController {

    //���������õ�����User
    private ObservableList<User> data = FXCollections.observableArrayList();

    @FXML //�û��ǳ���
    private TableColumn<User, String> userNameColumn;

    @FXML //����˵�
    private MenuItem enterManageItem;

    @FXML //�ʼǲ˵�
    private MenuItem enterNoteItem;

    @FXML //������
    private BorderPane managePane;

    @FXML //�û�id��
    private TableColumn<User, Integer> userIdColumn;

    @FXML //�û�ע��������
    private TableColumn<User, Date> userDateColumn;

    @FXML //������ʾ�����û���Ϣ�ı��
    private TableView<User> userTable;

    @FXML //�����˵�
    private MenuItem enterSearchItem;

    @FXML //�û�������
    private TableColumn<User, Integer> userTypeColumn;

    @FXML //������Ϣ�˵�
    private MenuItem enterInformationItem;

    @FXML //�û�������
    private TableColumn<User, String> userPasswordColumn;

    @FXML //�˵�
    private Menu manageOption;

    @FXML
    public void enterInformationAction(ActionEvent event) {
        EnterSceneUtil.enterScene("InformationPage.fxml", managePane);
    }

    @FXML
    public void enterNoteAction(ActionEvent event) {
        EnterSceneUtil.enterScene("NotePage.fxml", managePane);
    }

    @FXML
    public void enterSearchAction(ActionEvent event) {
        EnterSceneUtil.enterScene("SearchPage.fxml", managePane);
    }

    @FXML
    public void enterManageAction(ActionEvent event) {
        EnterSceneUtil.enterScene("ManagePage.fxml", managePane);
    }

    /**
     * ��ʼ������
     */
    @FXML
    public void initialize() {
        assert managePane != null : "fx:id=\"informationPane\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert userNameColumn != null : "fx:id=\"userNameColumn\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert enterManageItem != null : "fx:id=\"enterManageItem\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert enterNoteItem != null : "fx:id=\"enterNoteItem\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert userDateColumn != null : "fx:id=\"userDateColumn\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert userTable != null : "fx:id=\"userTable\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert enterSearchItem != null : "fx:id=\"enterSearchItem\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert userTypeColumn != null : "fx:id=\"userTypeColumn\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert userPasswordColumn != null : "fx:id=\"noteNumberColumn\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert enterInformationItem != null : "fx:id=\"enterInformationItem\" was not injected: check your FXML file 'ManagePage.fxml'.";
        assert manageOption != null : "fx:id=\"manageOption\" was not injected: check your FXML file 'ManagePage.fxml'.";

        //������ͨ�û�������ԱȨ����Ϊ���ɵ��
        if(LoginController.user.getUserType() == 0) {
            manageOption.setDisable(true);
        }
        showData();
    }

    /**
     * ��ʾtableview���û���Ϣ�ķ���
     */
    public void showData() {
        //��ȡ�����û�
        UserDaoImpl udi = new UserDaoImpl();
        ArrayList<User> userList = (ArrayList<User>) udi.getAll(LoginController.conn);
        for (int i = 0; i < userList.size(); i++) {
            data.add(userList.get(i));
        }
        //��ʾtableview�е��û���Ϣ
        userIdColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        userPasswordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userPassword"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("userType"));
        userDateColumn.setCellValueFactory(new PropertyValueFactory<User, Date>("userDate"));
        userTable.setItems(data);
    }
}
