package com.example.oopproject.controller.game;

import com.example.oopproject.controller.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import static com.example.oopproject.controller.MenuController.gameRoot;

public class GameController {
    //protected static AnchorPane soccerRoot, bearRoot;
    //protected static AnchorPane componentRoot;

    @FXML
    public void onSoccer() {
        setComponent("/Views/SoccerView.fxml");
//        try {
//            soccerRoot = FXMLLoader.load(getClass().getResource("/Views/SoccerView.fxml"));
//            gameRoot.getChildren().clear();
//            gameRoot.getChildren().add(soccerRoot);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    public void onClickHoneyBear() {
        setComponent("/Views/HoneyBear.fxml");
    }

    public void setComponent(String path) {
        try {
            AnchorPane componentRoot = FXMLLoader.load(getClass().getResource(path));
            System.out.println(componentRoot);
            gameRoot.getChildren().clear();
            //System.out.println(gameRoot.getChildren());
            gameRoot.getChildren().add(componentRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public abstract void playAgain();
//
//    public abstract void Exit();
}
