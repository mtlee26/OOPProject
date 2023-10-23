package com.example.oopproject;

import com.example.oopproject.game.GameManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Menu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Views/style.css").toExternalForm());
        primaryStage.setTitle("Dictionary Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
