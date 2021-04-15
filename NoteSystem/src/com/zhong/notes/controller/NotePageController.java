package com.zhong.notes.controller;

import java.util.ArrayList;
import java.util.Date;

import com.zhong.notes.bean.Note;
import com.zhong.notes.dao.NoteDaoImpl;
import com.zhong.notes.service.NoteService;
import com.zhong.notes.util.EnterSceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TreeItem;

/**
 * Page.fxml��controller�࣬��������ҵıʼ�ϵͳ����
 */
public class NotePageController {

    //�����ʾ�����ıʼǵ���Ϣ
    private Note note;

    //�µ�NoteService����
    NoteService ns = new NoteService();

    @FXML //�ʼ����ݿ�
    private TextArea noteText;

    @FXML //ɾ���ʼǰ�ť
    private Button deleteButton;

    @FXML //�ʼ��������ڱ�ǩ
    private Label noteDateLabel;

    @FXML //���ñʼǿɼ��Եĸ�ѡ��
    private CheckBox limitCheck;

    @FXML //�ʼ�ϵͳ����
    private BorderPane notePane;

    @FXML //�����ʼǰ�ť
    private Button releaseButton;

    @FXML //�����˵�
    private MenuItem enterSearchItem;

    @FXML //����ɹ���ʾ
    private Label saveSuccessTip;

    @FXML //���������ڡ���ǩ
    private Label dateLabel;

    @FXML //����˵�
    private MenuItem enterManageItem;

    @FXML //�ʼǲ˵�
    private MenuItem enterNoteItem;

    @FXML //ˢ�°�ť
    private Button refreshButton;

    @FXML //��ʾ�ʼǱ��������ͼ
    private TreeView<String> showTitle;

    @FXML //�޸ıʼǰ�ť
    private Button editButton;

    @FXML //�ʼǱ����ı���
    private TextField titleText;

    @FXML //���水ť
    private Button saveButton;

    @FXML //ɾ���ɹ���ʾ
    private Label deleteSuccessTip;

    @FXML //�����Ѵ�����ʾ
    private Label existTip;

    @FXML //������Ϣ�˵�
    private MenuItem enterInformationItem;

    @FXML //�����Ϊ����ʾ
    private Label emptyTextTip;

    @FXML //�˵�
    private Menu manageOption;

    /**
     * treeitem��������¼�
     * @param event
     */
    @FXML
    public void selectItem(MouseEvent event) {
        TreeItem<String> treeItem = showTitle.getSelectionModel().getSelectedItem();
        //��ѡ������treeitem�ıʼ�������ʾ����
        if(treeItem.getChildren().isEmpty()) {
            showNoteDetails(treeItem);
        }
    }

    /**
     * �޸ıʼǰ�ť�¼�
     * @param event
     */
    @FXML
    public void editButtonAction(ActionEvent event) {
        clearTips();
        //���ı�������Ϊ���޸ĵ�
        titleText.setEditable(true);
        noteText.setEditable(true);
    }

    /**
     * ɾ���ʼǰ�ť�¼�
     * @param event
     */
    @FXML
    public void deleteButtonAction(ActionEvent event) {
        //����ʾ��Ϣ��Ϊ���ɼ�
        clearTips();
        //�ж��Ƿ��Ѿ�ѡ��ʼ�
        if(titleText.getText().isBlank()) {
            //��û��ѡ��ʼǣ��ʼǱ���Ϊ�գ�������ʾ����ѡ��ʼ��ٽ��в�������
            emptyTextTip.setVisible(true);
        } else {
            //���ѡ��ʼǣ����ʼǴ����ݿ�ɾ������ʾ��ɾ���ɹ�����
            ns.deleteNote(LoginController.conn, titleText.getText(), LoginController.user.getUserId());
            deleteSuccessTip.setVisible(true);
        }
    }

    /**
     * ����ʼǰ�ť�¼�
     * @param event
     */
    @FXML
    public void saveButtonAction(ActionEvent event) {
        clearTips();
        //����ʼ�
        saveNote();
    }

    /**
     * �����ʼǰ�ť�¼�
     * @param event
     */
    @FXML
    public void releaseButtonAction(ActionEvent event) {
        clearTips();
        //����ı���
        titleText.clear();
        noteText.clear();
        limitCheck.setVisible(true);
        limitCheck.setSelected(false);
        titleText.setEditable(true);
        noteText.setEditable(true);
        //�����µ�Note�����趨idΪ0
        note = new Note();
        note.setNoteId(0);
    }

    /**
     * �����û���Ϣ����İ�ť�¼�
     * @param event
     */
    @FXML
    public void enterInformationAction(ActionEvent event) {
        EnterSceneUtil.enterScene("InformationPage.fxml", notePane);
    }

    /**
     * ����༭�ʼǵİ�ť�¼�
     * @param event
     */
    @FXML
    public void enterNoteAction(ActionEvent event) {
        EnterSceneUtil.enterScene("NotePage.fxml", notePane);
    }

    /**
     * ���������ʼǵİ�ť�¼�
     * @param event
     */
    @FXML
    public void enterSearchAction(ActionEvent event) {
        EnterSceneUtil.enterScene("SearchPage.fxml", notePane);
    }

    /**
     * �������Աϵͳ�İ�ť�¼�
     * @param event
     */
    @FXML
    public void enterManageAction(ActionEvent event) {
        EnterSceneUtil.enterScene("ManagePage.fxml", notePane);
    }

    /**
     * ˢ�½��水ť�¼�
     * @param event
     */
    @FXML
    public void refreshButton(ActionEvent event) {
        EnterSceneUtil.enterScene("NotePage.fxml", notePane);
    }

    /**
     * ��ʼ������
     */
    @FXML
    public void initialize() {
        assert noteText != null : "fx:id=\"noteText\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert limitCheck != null : "fx:id=\"limitCheck\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert emptyTextTip != null : "fx:id=\"deleteWrongTip\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert noteDateLabel != null : "fx:id=\"noteDateLabel\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert notePane != null : "fx:id=\"notePane\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert releaseButton != null : "fx:id=\"releaseButton\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert enterSearchItem != null : "fx:id=\"enterSearchItem\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert enterManageItem != null : "fx:id=\"enterManageItem\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert enterNoteItem != null : "fx:id=\"enterNoteItem\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert saveSuccessTip != null : "fx:id=\"saveSuccessTip\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert showTitle != null : "fx:id=\"showTitle\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert editButton != null : "fx:id=\"editButton\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert existTip != null : "fx:id=\"existTip\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert titleText != null : "fx:id=\"titleText\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert deleteSuccessTip != null : "fx:id=\"deleteSuccessTip\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert enterInformationItem != null : "fx:id=\"enterInformationItem\" was not injected: check your FXML file 'NotePage.fxml'.";
        assert manageOption != null : "fx:id=\"manageOption\" was not injected: check your FXML file 'NotePage.fxml'.";
        //������ͨ�û�������ԱȨ����Ϊ���ɵ��
        if(LoginController.user.getUserType() == 0) {
            manageOption.setDisable(true);
        }
        //���ʼǱ�����ʾ��treeview��
        showTitles();
        //����ѡ����Ϊ���ɼ�
        limitCheck.setVisible(false);
        //���ʼ�������Ϊ���ɼ�
        dateLabel.setVisible(false);
        noteDateLabel.setVisible(false);
    }

    /**
     * ��treeview����ʾ�ʼǱ���ķ���
     */
    public void showTitles() {
        NoteDaoImpl ndi = new NoteDaoImpl();
        //����TreeView��ͷ�ڵ㼰��Ϣ
        TreeItem<String> noteItem = new TreeItem<>("�ҵıʼ�");
        showTitle.setRoot(noteItem);
        noteItem.setExpanded(true);
        //��ȡ���û��ıʼ�
        ArrayList<Note> noteList = (ArrayList<Note>) ndi.getAll(LoginController.conn, LoginController.user.getUserId());
        //�����û��ıʼǱ�����ʾ����
        for(int i = 0; i < noteList.size(); i ++) {
            noteItem.getChildren().add(new TreeItem<>(noteList.get(i).getNoteTitle()));
        }
    }

    /**
     * ��ʾѡ��ıʼ����ݵķ���
     * @param treeItem
     */
    public void showNoteDetails(TreeItem<String> treeItem) {
        note = ns.showNote(LoginController.conn, LoginController.user.getUserId(), treeItem);
        //��ʾ�ʼ����ݺͱ��⣬����
        noteText.setText(note.getNoteContent());
        titleText.setText(note.getNoteTitle());
        dateLabel.setVisible(true);
        noteDateLabel.setVisible(true);
        limitCheck.setVisible(true);
        //�ж��û����Ͳ���ʾ��ѡ��
        if(note.getNoteLimit() == 0) limitCheck.setSelected(true);
        else limitCheck.setSelected(false);
        //��ʾ�ʼ������޸�����
        noteDateLabel.setText(note.getNoteDate().toString());
        //���ı�����Ϊ�����޸�
        titleText.setEditable(false);
        noteText.setEditable(false);
        //�����ʾ��Ϣ
        clearTips();
    }

    public void saveNote() {
        //��û��ѡ��ʼǣ��ʼǱ���Ϊ�գ�������ʾ����ѡ��ʼ��ٽ��в�������
        if(titleText.getText().isBlank()) {
            emptyTextTip.setVisible(true);
        }else {
            //�����±ʼǵ���Ϣ
            note.setNoteContent(noteText.getText());
            note.setNoteDate(new Date());
            note.setUserId(LoginController.user.getUserId());
            //���ݸ�ѡ��ѡ���趨�ʼ�Ȩ��
            if(limitCheck.isSelected()) {
                note.setNoteLimit(0); //���ɼ�
            } else {
                note.setNoteLimit(1); //�ɼ�
            }
            //����ʼ�idΪ0����Ϊ�·����ıʼ�
            if(note.getNoteId() == 0) {
                //�ж��±ʼǱ����Ƿ����
                if(ns.isTitleExist(LoginController.conn, LoginController.user.getUserId(), titleText.getText())) {
                    //���ñʼǱ��Ⲣ��������
                    note.setNoteTitle(titleText.getText());
                    ns.insertNote(LoginController.conn, note);
                } else {
                    //��ʾ�Ѵ��ڵ���ʾ��ȡ���������
                    existTip.setVisible(true);
                    return;
                }
            } else {
                //���ʼǱ��ⱻ�޸�ʱ
                if(! titleText.getText().equals(note.getNoteTitle())) {
                    if(ns.isTitleExist(LoginController.conn, LoginController.user.getUserId(), titleText.getText())) {
                        //���ñʼǱ��Ⲣ��������
                        note.setNoteTitle(titleText.getText());
                        ns.updateNote(LoginController.conn, note);
                    } else {
                        //��ʾ�Ѵ��ڵ���ʾ��ȡ���������
                        existTip.setVisible(true);
                        return;
                    }
                } else { //�ʼǱ���û�б��޸�ʱ
                    ns.updateNote(LoginController.conn, note);
                }
            }
            //��ʾ����ɹ���ʾ��Ϣ
            saveSuccessTip.setVisible(true);
            //���ı��򷵻ز����޸ĵ�״̬
            titleText.setEditable(false);
            noteText.setEditable(false);
        }
    }

    /**
     * �����ʾ��Ϣ�ķ���
     */
    public void clearTips() {
        emptyTextTip.setVisible(false);
        deleteSuccessTip.setVisible(false);
        saveSuccessTip.setVisible(false);
        existTip.setVisible(false);
    }
}

