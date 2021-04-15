package com.zhong.notes.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 用于设置屏幕的工具类
 */
public class EnterSceneUtil {

    /**
     * 进入窗口的方法
     * @param fileName
     * @param newPane
     */
    public static void enterScene(String fileName, Pane newPane) {
        BorderPane pane = null;
        try {
            pane = FXMLLoader.load(EnterSceneUtil.class.getResource("/com/zhong/notes/view/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("找不到文件！");
        }
        newPane.getChildren().setAll(pane);
    }

    /**
     * 新打开一个窗口的方法
     * @param filename
     * @param sceneTitle
     * @param length
     * @param width
     * @throws IOException
     */
    public static void openScene(String filename, String sceneTitle, double length, double width) throws IOException {
        Parent root = FXMLLoader.load(EnterSceneUtil.class.getResource("/com/zhong/notes/view/" + filename));
        Stage registerStage = new Stage();
        registerStage.setTitle(sceneTitle);
        registerStage.setScene(new Scene(root, length, width));
        registerStage.show();
    }
}
