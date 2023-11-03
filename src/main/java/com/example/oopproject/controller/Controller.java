package com.example.oopproject.controller;

import com.example.oopproject.database.Database;

public abstract class Controller {
    protected Database database = new Database();
//    public void setScene(AnchorPane content, String pathName) {
//        try {
//
////            root.getChildren().clear();
//            AnchorPane root = FXMLLoader.load(getClass().getResource(pathName));
//            //content.getChildren().clear();
//            content.getChildren().add(root);
////            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            //Scene scene = new Scene(root);
//            //stage.setScene(scene);
////            scene.getStylesheets().add(getClass().getResource("/Views/style.css").toExternalForm());
//////            stage.setScene(scene);
////            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void setNode(Pane root, Node node) {
//        root.getChildren().clear();
//        root.getChildren().add(node);
//    }

    }

