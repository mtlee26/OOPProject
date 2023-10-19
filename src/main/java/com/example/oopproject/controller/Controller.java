package com.example.oopproject.controller;

import com.example.oopproject.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {
    protected Database database = new Database();
    public void setScene(ActionEvent event, String pathName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathName));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/Views/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void setNode(Pane root, Node node) {
//        root.getChildren().clear();
//        root.getChildren().add(node);
//    }

//    public void setScene(String pathName, Pane root) {
//        try {
//            AnchorPane component = FXMLLoader.load(getClass().getResource(pathName));
//            setNode(root, component);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

