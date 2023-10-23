package com.example.oopproject.controller.game;

import com.example.oopproject.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SoccerResultController extends Controller {
    @FXML
    public void onPlayAgain(ActionEvent actionEvent) {
        setScene(actionEvent, "/Views/SoccerView.fxml");
    }

    @FXML
    public void onExit(ActionEvent actionEvent) {
        setScene(actionEvent, "/Views/Game.fxml");
    }
}
