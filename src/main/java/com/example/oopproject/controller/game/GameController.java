package com.example.oopproject.controller.game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import static com.example.oopproject.controller.MenuController.gameRoot;

public class GameController {
    @FXML
    private AnchorPane soccerMenu;
    @FXML
    private AnchorPane bearMenu;

    @FXML
    public void onSoccer() {
        soccerMenu.setVisible(true);
    }

    @FXML
    public void onClickHoneyBear() {
        bearMenu.setVisible(true);
    }

    @FXML
    public void onSoccerPlay() {
        soccerMenu.setVisible(false);
        setComponent("/Views/SoccerView.fxml");
    }

    @FXML
    public void onSoccerBack() {
        soccerMenu.setVisible(false);
    }

    @FXML
    public void onBearPlay() {
        bearMenu.setVisible(false);
        setComponent("/Views/HoneyBear.fxml");
    }

    @FXML
    public void onBearBack() {
        bearMenu.setVisible(false);
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
}
