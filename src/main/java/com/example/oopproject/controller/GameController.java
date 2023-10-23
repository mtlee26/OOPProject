package com.example.oopproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GameController extends Controller {
    @FXML
    public void onSoccer(ActionEvent actionEvent) {
        setScene(actionEvent, "/Views/SoccerView.fxml");
    }

//    public abstract void playAgain();
//
//    public abstract void Exit();
}
