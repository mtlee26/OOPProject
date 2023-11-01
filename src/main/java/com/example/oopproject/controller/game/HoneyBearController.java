package com.example.oopproject.controller.game;

import com.example.oopproject.controller.Controller;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HoneyBearController extends Controller implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane rootGame;
    private boolean isRunning;
    private boolean isQuestion;
    private HoneyBear game;
    private GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isRunning = true;
        game = new HoneyBear();
        Canvas canvas = new Canvas(320, 320);
        gc = canvas.getGraphicsContext2D();
        rootGame.getChildren().add(canvas);
        //Scene scene1 = new Scene(rootGame, 320, 320);
        //KeyEvent keyEvent = scene.
        //game.render(gc);
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
//                scene.setOnKeyPressed(keyEvent1 -> {
//                    game.update(keyEvent1);
//                });
                game.render(gc);
            }
        };
        gameLoop.start();
    }

    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        //System.out.println("here");
        while (isRunning) {
            game.update(keyEvent);
            game.render(gc);
        }
    }

//    public void loop() {
//        while (isRunning) {
//            scene.setOnKeyPressed(keyEvent -> {
//                game.update(keyEvent);
//                game.render(gc);
//            });
//        }
//    }

}
