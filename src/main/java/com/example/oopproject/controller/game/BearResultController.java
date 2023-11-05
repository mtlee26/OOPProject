package com.example.oopproject.controller.game;

import javafx.fxml.FXML;

import static com.example.oopproject.controller.MenuController.gameRoot;

public class BearResultController extends GameController {
    @FXML
    public void onPlayAgain() {
        setComponent(gameRoot, "/Views/HoneyBear.fxml");
    }

    @FXML
    public void onExit() {
        setComponent(gameRoot, "/Views/Game.fxml");
    }
}
