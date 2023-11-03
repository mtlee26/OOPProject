package com.example.oopproject;

import com.example.oopproject.game.GameManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApplication extends Application {
    //public static Stage stage;
    //public static Scene scene;
    //public static Pane root;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //stage = primaryStage;
        Pane root = FXMLLoader.load(getClass().getResource("/Views/Menu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Views/style.css").toExternalForm());
        primaryStage.setTitle("Dictionary Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
