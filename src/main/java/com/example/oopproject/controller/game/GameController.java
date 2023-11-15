package com.example.oopproject.controller.game;

import com.example.oopproject.controller.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import static com.example.oopproject.controller.MenuController.gameRoot;

public class GameController {
    //protected static AnchorPane soccerRoot, bearRoot;
    //protected static AnchorPane componentRoot;
    @FXML
    private AnchorPane soccerMenu;
    @FXML
    private AnchorPane bearMenu;

    @FXML
    public void onSoccer() {
        soccerMenu.setVisible(true);
        //setComponent("/Views/SoccerView.fxml");
    }

    @FXML
    public void onClickHoneyBear() {
        bearMenu.setVisible(true);
        //setComponent("/Views/HoneyBear.fxml");
    }

    @FXML
    public void onSoccerPlay() {
        soccerMenu.setVisible(false);
        setComponent("/Views/SoccerView.fxml");
    }

    @FXML
    public void onSoccerBack() {
        soccerMenu.setVisible(false);
        //setComponent("/Views/SoccerView.fxml");
    }

    @FXML
    public void onBearPlay() {
        bearMenu.setVisible(false);
        setComponent("/Views/HoneyBear.fxml");
    }

    @FXML
    public void onBearBack() {
        bearMenu.setVisible(false);
        //setComponent("/Views/SoccerView.fxml");
    }

    public void setComponent(String path) {
        try {
            AnchorPane componentRoot = FXMLLoader.load(getClass().getResource(path));
            gameRoot.getChildren().clear();
            gameRoot.getChildren().add(componentRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public abstract void playAgain();
//
//    public abstract void Exit();
}
