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
    private AnchorPane content;
    private AnchorPane dictRoot, gameRoot;
    @FXML
    public void onDictionaryButtonClick(ActionEvent actionEvent) {
//        setScene(content, "/Views/DictionaryView.fxml");
        //setScene("/Views/DictionaryView.fxml", root);
        dictRoot.setVisible(true);
        gameRoot.setVisible(false);

    }

    @FXML
    public void onGameButtonClick(ActionEvent actionEvent) {
//        setScene(content, "/Views/Game.fxml");
        //setScene("/Views/DictionaryView.fxml", root);
        dictRoot.setVisible(false);
        gameRoot.setVisible(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dictRoot = FXMLLoader.load(getClass().getResource("/Views/DictionaryView.fxml"));
            gameRoot = FXMLLoader.load(getClass().getResource("/Views/Game.fxml"));
            content.getChildren().add(dictRoot);
            content.getChildren().add(gameRoot);
            dictRoot.setVisible(true);
            gameRoot.setVisible(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
