package com.example.oopproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController extends Controller {
    @FXML
    private AnchorPane root;
    @FXML
    public void onDictionaryButtonClick(ActionEvent event) {
//        try {
//            Parent rootDictionary = FXMLLoader.load(getClass().getResource("/Views/DictionaryView.fxml"));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene dictionaryScene = new Scene(rootDictionary);
//            stage.setTitle("Dictionary Application");
//            stage.setScene(dictionaryScene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        setScene(event, "/Views/DictionaryView.fxml");
        //setScene("/Views/DictionaryView.fxml", root);
    }

    @FXML
    public void onGameButtonClick() {
//        try {
//            Parent rootDictionary = FXMLLoader.load(getClass().getResource("/Views/AddWordView.fxml"));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene dictionaryScene = new Scene(rootDictionary);
//            stage.setTitle("Dictionary Application");
//            stage.setScene(dictionaryScene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //setScene(event, "/Views/AddWordView.fxml");
        setScene("/Views/DictionaryView.fxml", root);
    }
}
