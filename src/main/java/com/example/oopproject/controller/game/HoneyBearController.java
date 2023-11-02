package com.example.oopproject.controller.game;

import com.example.oopproject.controller.Controller;
import com.example.oopproject.game.BearManagement;
import com.example.oopproject.game.BearQuestion;
import com.example.oopproject.game.Direction;
import com.example.oopproject.game.entities.Bear;
import com.example.oopproject.game.entities.FootPrint;
import com.example.oopproject.game.entities.Rock;
import com.example.oopproject.game.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class HoneyBearController extends Controller implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane rootGame;
    @FXML
    private TextArea question;
    @FXML
    private TextField answerField;
    private GraphicsContext gc;
    public static boolean isQuestion;
    private HoneyBear game;
    private BearManagement quiz = new BearManagement();
    private List<BearQuestion> questionList = quiz.getQuestionList();
    private int score = 0;
    private int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //isRunning = true;
        isQuestion = false;
        game = new HoneyBear();
        quiz.insertFromFile();
        Collections.shuffle(questionList);
        Canvas canvas = new Canvas(320, 320);
        gc = canvas.getGraphicsContext2D();
        rootGame.getChildren().add(canvas);
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameUpdate();
                questionUpdate();
                game.render(gc);
            }
        };
        gameLoop.start();
    }

    public void gameUpdate() {
        if (!isQuestion) {
            root.requestFocus();
            root.setOnKeyPressed(keyEvent -> {
                game.update(keyEvent);
            });
        }
    }

    public void questionUpdate() {
        if (isQuestion) {
            String ques = questionList.get(count).getQuestion();
            question.setText(ques);
        }
    }

    @FXML
    public void onSubmit() {
        String answer = answerField.getText().trim().toLowerCase();
        Bear bear = game.getMap().getBear();
        int x = bear.getxUnit();
        int y = bear.getyUnit();
        if (questionList.get(count).isTrue(answer)) {
            score += 10;
            game.getMap().updateMap(x, y, new FootPrint(x, y, Sprite.foot_print.getFxImage()));
        } else {
            game.getMap().updateMap(x, y, new Rock(x, y, Sprite.rock.getFxImage()));
            bear.back();
        }
        count++;
        answerField.clear();
        isQuestion = false;
    }

}
