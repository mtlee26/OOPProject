package com.example.oopproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MenuController extends Controller {
    //    @FXML
//    private AnchorPane root;
    @FXML
    public void onDictionaryButtonClick(ActionEvent actionEvent) {
        setScene(actionEvent, "/Views/DictionaryView.fxml");
        //setScene("/Views/DictionaryView.fxml", root);
    }

    @FXML
    public void onGameButtonClick(ActionEvent actionEvent) {
        setScene(actionEvent, "/Views/Game.fxml");
        //setScene("/Views/DictionaryView.fxml", root);
    }



}
