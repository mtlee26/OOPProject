package com.example.oopproject.controller.game;

import com.example.oopproject.controller.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

import static com.example.oopproject.controller.MenuController.gameRoot;

public class SoccerResultController extends GameController {
    @FXML
    public void onPlayAgain() {
        setComponent("/Views/SoccerView.fxml");
    }

    @FXML
    public void onExit() {
//        try {
//            AnchorPane soccerRoot = FXMLLoader.load(getClass().getResource("/Views/DictionaryView.fxml"));
//            gameRoot.getChildren().clear();
//            gameRoot.getChildren().add(soccerRoot);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        setComponent("/Views/Game.fxml");
    }
}
