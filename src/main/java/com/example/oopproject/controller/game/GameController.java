package com.example.oopproject.controller.game;

import com.example.oopproject.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import static com.example.oopproject.controller.MenuController.*;

public class GameController extends Controller {
    private AnchorPane soccerRoot, bearRoot;

    @FXML
    public void onSoccer() {
        try {
            soccerRoot = FXMLLoader.load(getClass().getResource("/Views/SoccerView.fxml"));
            gameRoot.getChildren().clear();
            gameRoot.getChildren().add(soccerRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        dictRoot.setVisible(false);
//        gameRoot.setVisible(false);
//        soccerRoot.setVisible(true);
//        bearRoot.setVisible(false);
    }

    @FXML
    public void onClickHoneyBear() {
        try {
            bearRoot = FXMLLoader.load(getClass().getResource("/Views/HoneyBear.fxml"));
            gameRoot.getChildren().clear();
            gameRoot.getChildren().add(bearRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        dictRoot.setVisible(false);
//        gameRoot.setVisible(false);
//        soccerRoot.setVisible(false);
//        bearRoot.setVisible(true);

    }

//    public abstract void playAgain();
//
//    public abstract void Exit();
}
