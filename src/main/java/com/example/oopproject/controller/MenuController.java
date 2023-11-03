package com.example.oopproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends Controller implements Initializable {

    @FXML
    public AnchorPane content;
    public static AnchorPane dictRoot, gameRoot, menuGame;

    @FXML
    public void onDictionaryButtonClick() {
        dictRoot.setVisible(true);
        //gameRoot.getChildren()
        gameRoot.setVisible(false);
//        soccerRoot.setVisible(false);
//        bearRoot.setVisible(false);
    }

    @FXML
    public void onGameButtonClick() {
        dictRoot.setVisible(false);
        gameRoot.setVisible(true);
//        soccerRoot.setVisible(false);
//        bearRoot.setVisible(false);
        try {
            menuGame = FXMLLoader.load(getClass().getResource("/Views/Game.fxml"));
            gameRoot.getChildren().clear();
            gameRoot.getChildren().add(menuGame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dictRoot = FXMLLoader.load(getClass().getResource("/Views/DictionaryView.fxml"));
            gameRoot = FXMLLoader.load(getClass().getResource("/Views/Game.fxml"));
//            soccerRoot = FXMLLoader.load(getClass().getResource("/Views/SoccerView.fxml"));
//            bearRoot = FXMLLoader.load(getClass().getResource("/Views/HoneyBear.fxml"));
            content.getChildren().add(dictRoot);
            content.getChildren().add(gameRoot);
//            content.getChildren().add(soccerRoot);
//            content.getChildren().add(bearRoot);
            dictRoot.setVisible(false);
            gameRoot.setVisible(false);
//            soccerRoot.setVisible(false);
//            bearRoot.setVisible(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
