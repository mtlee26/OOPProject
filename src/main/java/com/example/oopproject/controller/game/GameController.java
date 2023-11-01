package com.example.oopproject.controller.game;

import com.example.oopproject.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GameController extends Controller {
    @FXML
    public void onSoccer(ActionEvent actionEvent) {
        setScene(actionEvent, "/Views/SoccerView.fxml");
    }

    @FXML
    public void onClickHoneyBear(ActionEvent actionEvent) {
        setScene(actionEvent, "/Views/HoneyBear.fxml");
    }

//    public abstract void playAgain();
//
//    public abstract void Exit();
}
