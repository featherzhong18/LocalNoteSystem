package com.zhong.notes.controller;

import java.util.ArrayList;

import com.zhong.notes.bean.Note;
import com.zhong.notes.bean.User;
import com.zhong.notes.dao.NoteDaoImpl;
import com.zhong.notes.dao.UserDaoImpl;
import com.zhong.notes.util.EnterSceneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class SearchPageController {

    //��ñʼ���Ϣ
    private Note note = null;

    //���choiceBox��������Ϣ
    ObservableList list = FXCollections.observableArrayList();

    //��Ż�õ����бʼ���Ϣ
    ArrayList<Note> noteList = null;

    //��Ż�õ������û���Ϣ
    ArrayList<User> userList = null;

    @FXML //�ʼ������ı���
    private TextArea noteText;

    @FXML //������ť
    private Button searchButton;

    @FXML //ѡ������ģʽ��
    private ChoiceBox<String> chooseSearch;

    @FXML //�����˵�
    private MenuItem enterSearchItem;

    @FXML //������
    private TextField searchText;

    @FXML //�����õ��ıʼǻ���������ͼ
    private TreeView<String> nameTreeView;

    @FXML //����˵�
    private MenuItem enterManageItem;

    @FXML //�ʼǲ˵�
    private MenuItem enterNoteItem;

    @FXML //��������
    private BorderPane searchPane;

    @FXML //�ʼǱ����
    private TextField titleText;

    @FXML //�����ı���
    private TextField authorText;

    @FXML //������Ϣ�˵�
    private MenuItem enterInformationItem;

    @FXML //�˵�
    private Menu manageOption;

    @FXML
    public void searchButton(ActionEvent event) {
        //����TreeView��ͷ�ڵ㼰��Ϣ
        TreeItem<String> treeItem = null;
        if(searchText.getText().isBlank()) {
            treeItem = new TreeItem<>("�������Ϊ�գ�");
            nameTreeView.setRoot(treeItem);
            return;
        }
        if(chooseSearch.getValue().equals("�����ʼǱ���")) {
            searchByTitle(treeItem);
        } else if(chooseSearch.getValue().equals("�����û��ǳ�")) {
            searchByUser(treeItem);
        }
    }

    /**
     * ���������Ϣ����İ�ť�¼�
     * @param event
     */
    @FXML
    public void enterInformationAction(ActionEvent event) {
        EnterSceneUtil.enterScene("InformationPage.fxml", searchPane);
    }

    /**
     * ����ʼ�ϵͳ�İ�ť�¼�
     * @param event
     */
    @FXML
    public void enterNoteAction(ActionEvent event) {
        EnterSceneUtil.enterScene("NotePage.fxml", searchPane);
    }

    /**
     * ��������ϵͳ�İ�ť�¼�
     * @param event
     */
    @FXML
    public void enterSearchAction(ActionEvent event) {
        EnterSceneUtil.enterScene("SearchPage.fxml", searchPane);
    }

    /**
     * �������ϵͳ�İ�ť�¼�
     * @param event
     */
    @FXML
    public void enterManageAction(ActionEvent event) {
        EnterSceneUtil.enterScene("ManagePage.fxml", searchPane);
    }

    /**
     * ��ʼ������
     */
    @FXML
    public void initialize() {
        assert noteText != null : "fx:id=\"noteText\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert nameTreeView != null : "fx:id=\"nameTreeView\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert enterManageItem != null : "fx:id=\"enterManageItem\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert enterNoteItem != null : "fx:id=\"enterNoteItem\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert chooseSearch != null : "fx:id=\"chooseSearch\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert searchPane != null : "fx:id=\"notePane\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert titleText != null : "fx:id=\"titleText\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert authorText != null : "fx:id=\"authorText\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert enterSearchItem != null : "fx:id=\"enterSearchItem\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert enterInformationItem != null : "fx:id=\"enterInformationItem\" was not injected: check your FXML file 'SearchPage.fxml'.";
        assert manageOption != null : "fx:id=\"manageOption\" was not injected: check your FXML file 'SearchPage.fxml'.";

        //������ͨ�û�������ԱȨ����Ϊ���ɵ��
        if(LoginController.user.getUserType() == 0) {
            manageOption.setDisable(true);
        }

        //��ʾѡ��������
        list.removeAll(list);
        chooseSearch.setValue("�����ʼǱ���");
        list.addAll("�����ʼǱ���", "�����û��ǳ�");
        chooseSearch.getItems().addAll(list);
    }

    /**
     * ѡ��ʼǵ�������¼�
     * @param mouseEvent
     */
    public void chooseName(MouseEvent mouseEvent) {
        //����������treeItem
        TreeItem<String> treeItem = nameTreeView.getSelectionModel().getSelectedItem();
        //��������ģʽ
        if(chooseSearch.getValue().equals("�����ʼǱ���")) {
            if(treeItem.getChildren().isEmpty()) {
                showNote(treeItem);
            }
        } else if(chooseSearch.getValue().equals("�����û��ǳ�")) { //�����û�ģʽ
            if(treeItem.getChildren().isEmpty()) {
                showNote(treeItem);
            }
        }
    }

    /**
     * ��ʾ�ʼ���Ϣ�ķ���
     * @param treeItem
     */
    public void showNote(TreeItem<String> treeItem) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        UserDaoImpl udi = new UserDaoImpl();
        //��õ�ǰѡ��ıʼǵ���Ϣ
        note = ndi.getNote(LoginController.conn, getId(treeItem.getValue()));
        noteText.setText(note.getNoteContent());
        titleText.setText(note.getNoteTitle());
        authorText.setText(udi.getUserName(LoginController.conn, note.getUserId()));
        //��ʾ�ʼ������޸�����
        //���ı�����Ϊ�����޸�
        titleText.setEditable(false);
        noteText.setEditable(false);
        authorText.setEditable(false);
    }

    /**
     * ���ݱʼǱ�����������ķ���
     * @param treeItem
     */
    public void searchByTitle(TreeItem<String> treeItem) {
        NoteDaoImpl ndi = new NoteDaoImpl();
        //��ȡ���û��ıʼ�
        noteList = (ArrayList<Note>) ndi.getAll(LoginController.conn, "%" + searchText.getText() + "%");
        //���������ıʼǱ�����ʾ������������Ҳ�������ʾ�����ʾ
        if(noteList.size() == 0) {
            treeItem = new TreeItem<>("�Ҳ��������Ϣ��");
            nameTreeView.setRoot(treeItem);
        } else {
            treeItem = new TreeItem<>("�������ıʼ�");
            nameTreeView.setRoot(treeItem);
            treeItem.setExpanded(true);
            for (int i = 0; i < noteList.size(); i++) {
                //����֮�٣���ͬ�û�֮��ʼǱ�������ظ���ֻ�ܽ��ʼ�id��ӵ����棬�����ȡ�ʼ���Ϣ
                treeItem.getChildren().add(new TreeItem<>(noteList.get(i).getNoteTitle() + "-" + noteList.get(i).getNoteId()));
            }
        }
    }

    /**
     * �����û��ǳƽ��������ķ���
     * @param treeItem
     */
    public void searchByUser(TreeItem<String> treeItem) {
        UserDaoImpl udi = new UserDaoImpl();
        NoteDaoImpl ndi = new NoteDaoImpl();
        userList = (ArrayList<User>) udi.getAll(LoginController.conn, "%" + searchText.getText() + "%");
        if(userList.size() == 0) {
            treeItem = new TreeItem<>("�Ҳ��������Ϣ��");
            nameTreeView.setRoot(treeItem);
        } else {
            treeItem = new TreeItem<>("���������û�");
            nameTreeView.setRoot(treeItem);
            treeItem.setExpanded(true);
            //��ʾ���������û���
            for(int i = 0; i < userList.size(); i ++) {
                TreeItem<String> item = new TreeItem<>(userList.get(i).getUserName());
                ArrayList<Note> noteListOfUser = null;
                if(userList.get(i).getUserId() == LoginController.user.getUserId()) {
                    noteListOfUser = (ArrayList<Note>) ndi.getAll(LoginController.conn, userList.get(i).getUserId());
                } else {
                    noteListOfUser = (ArrayList<Note>) ndi.getNotesByUser(LoginController.conn, userList.get(i).getUserId(), 1);
                }
                treeItem.getChildren().add(item);
                //���ÿ���û��Ŀɼ��ʼ�
                for (int j = 0; j < noteListOfUser.size(); j++) {
                    item.getChildren().add(new TreeItem<>(noteListOfUser.get(j).getNoteTitle() + "-" + noteListOfUser.get(j).getNoteId()));
                }
            }
        }
    }
    /**
     * ��ȡ�������ıʼǺ����id
     * @param noteTitle
     * @return �ʼǵ�id
     */
    public int getId(String noteTitle) {
        String str;
        int i;
        for(i = noteTitle.length() - 1; i >= 0; i --) {
            if((noteTitle.substring(i - 1, i)).equals("-")) {
                break;
            } else {
                //str += noteTitle.substring(i -1, i);
                continue;
            }
        }
        str = noteTitle.substring(i);
        return Integer.parseInt(str);
    }
}
