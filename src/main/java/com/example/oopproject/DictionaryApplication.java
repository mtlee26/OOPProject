package com.example.oopproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApplication extends Application {
    public static Stage stage;
    public static Scene scene;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        Pane root = FXMLLoader.load(getClass().getResource("/Views/Menu.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Views/style.css").toExternalForm());
        stage.setTitle("Dictionary Application");
        stage.setScene(scene);
        stage.show();
    }
}
