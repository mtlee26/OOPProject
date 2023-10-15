package com.example.oopproject;

import com.example.oopproject.database.Database;
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
        Parent rootDictionary = FXMLLoader.load(getClass().getResource("/Views/Menu.fxml"));
        Scene dictionaryScene = new Scene(rootDictionary);
        primaryStage.setTitle("Dictionary Application");
        primaryStage.setScene(dictionaryScene);
        primaryStage.show();
    }
}
