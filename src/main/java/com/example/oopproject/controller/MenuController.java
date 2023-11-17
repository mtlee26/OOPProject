package com.example.oopproject.controller;

import com.example.oopproject.controller.game.HoneyBearController;
import com.example.oopproject.controller.game.SoccerController;
import com.example.oopproject.dictionary.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.oopproject.controller.game.HoneyBearController.game;
import static com.example.oopproject.controller.game.HoneyBearController.time;

public class MenuController implements Initializable {

    @FXML
    public AnchorPane content;
    public static AnchorPane dictRoot, gameRoot, addRoot;
    public static Database database = new Database();

    @FXML
    public void onDictionaryButtonClick() {
        dictRoot.setVisible(true);
        gameRoot.setVisible(false);
        HoneyBearController.setEnd();
        SoccerController.setEnd();
    }

    @FXML
    public void onGameButtonClick() {
        dictRoot.setVisible(false);
        gameRoot.setVisible(true);
        HoneyBearController.setEnd();
        SoccerController.setEnd();
        try {
            AnchorPane menuGame = FXMLLoader.load(getClass().getResource("/Views/Game.fxml"));
            gameRoot.getChildren().clear();
            gameRoot.getChildren().add(menuGame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onHomeClick() {
        dictRoot.setVisible(false);
        gameRoot.setVisible(false);
        HoneyBearController.setEnd();
        SoccerController.setEnd();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dictRoot = FXMLLoader.load(getClass().getResource("/Views/DictionaryView.fxml"));
            gameRoot = FXMLLoader.load(getClass().getResource("/Views/Game.fxml"));
            addRoot = FXMLLoader.load(getClass().getResource("/Views/AddWordView.fxml"));
            dictRoot.getChildren().add(addRoot);
            addRoot.setVisible(false);
            content.getChildren().add(dictRoot);
            content.getChildren().add(gameRoot);
            dictRoot.setVisible(false);
            gameRoot.setVisible(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
