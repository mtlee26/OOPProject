package com.example.oopproject.controller.game;

import com.example.oopproject.controller.MenuController;
import com.example.oopproject.game.BearManagement;
import com.example.oopproject.game.Question;
import com.example.oopproject.game.entities.Bear;
import com.example.oopproject.game.entities.FootPrint;
import com.example.oopproject.game.entities.Rock;
import com.example.oopproject.game.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.oopproject.DictionaryApplication.scene;
import static com.example.oopproject.controller.MenuController.gameRoot;

public class HoneyBearController extends GameController implements Initializable {
//    @FXML
//    private AnchorPane root;
    @FXML
    private AnchorPane rootGame;
    @FXML
    private TextArea question;
    @FXML
    private TextField answerField;
    private GraphicsContext gc;
    //public boolean isQuestion;
    private HoneyBear game;
    private BearManagement quiz = new BearManagement();
    private List<Question> questionList = quiz.getQuestionList();
    private int score = 0;
    private int count = 0;
    //private boolean isWin;
    private boolean isEnd;
    private int cntWrong;
    private AnimationTimer gameLoop;
    @FXML
    private ImageView win;
    @FXML
    private ImageView lose;
    @FXML
    private Button exit;
    @FXML
    private Button playAgain;
    private int totalTime;
    @FXML
    private Label timerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalTime = 300;
        int minutes = totalTime / 60;
        int seconds = totalTime % 60;
        DecimalFormat df = new DecimalFormat("00");
        timerLabel.setText("Time left: " + df.format(minutes) + ":" + df.format(seconds));
        isEnd = false;
        cntWrong = 0;
        game = new HoneyBear();
        quiz.init(MenuController.database.getDictionary().getWordList().stream().toList());
        Collections.shuffle(questionList);
        Canvas canvas = new Canvas(320, 320);
        gc = canvas.getGraphicsContext2D();
        rootGame.getChildren().add(canvas);

        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (!isEnd) {
                    if (lastUpdate == 0) {
                        lastUpdate = now;
                    }
                    long elapsedNanos = now - lastUpdate;
                    double elapsedSeconds = elapsedNanos / 1000000000.0;
                    if (elapsedSeconds >= 1.0) {
                        totalTime--;
                        int minutes = totalTime / 60;
                        int seconds = totalTime % 60;
                        //DecimalFormat df = new DecimalFormat("00");
                        timerLabel.setText("Time left: " + df.format(minutes) + ":" + df.format(seconds));
                        lastUpdate = now;
                        if (totalTime <= 0) {
                            isEnd = true;
                        }
                    }
                }
                gameUpdate();
                questionUpdate();
                game.render(gc);
            }
        };
        gameLoop.start();
    }

    public void gameUpdate() {
        Bear bear = game.getMap().getBear();
        if (!bear.isQuestion()) {
            rootGame.requestFocus();
            rootGame.setOnKeyPressed(keyEvent -> {
                game.update(keyEvent);
            });
        }
        if (bear.getxUnit() == 7 && bear.getyUnit() == 7) {
            //isWin = true;
            win.setVisible(true);
            playAgain.setVisible(true);
            exit.setVisible(true);
        }
        if (isEnd || cntWrong == 3) {
            lose.setVisible(true);
            playAgain.setVisible(true);
            exit.setVisible(true);
        }
    }

    @FXML
    public void onPlayAgain() {
        win.setVisible(false);
        lose.setVisible(false);
        playAgain.setVisible(false);
        exit.setVisible(false);
        refresh();
        isEnd = false;
    }

    @FXML
    public void onExit() {
        setComponent(gameRoot, "/Views/Game.fxml");
    }

    public void questionUpdate() {
        Bear bear = game.getMap().getBear();
        if (bear.isQuestion()) {
            answerField.requestFocus();
            String ques = questionList.get(count).getQuestion();
            question.setText(ques);
        }
    }

    @FXML
    public void onSubmit() {
        String answer = answerField.getText().trim().toUpperCase();
        Bear bear = game.getMap().getBear();
        int x = bear.getxUnit();
        int y = bear.getyUnit();
        if (questionList.get(count).isTrue(answer)) {
            score += 10;
            game.getMap().updateMap(x, y, new FootPrint(x, y, Sprite.foot_print.getFxImage()));
            question.setText(questionList.get(count).getQuestion().replace('_', answer.charAt(0)));
        } else {
            cntWrong++;
            game.getMap().updateMap(x, y, new Rock(x, y, Sprite.rock.getFxImage()));
            bear.back();
            String ans = questionList.get(count).getQuestion();
            ans = "Correct answer: " + ans.replace('_', questionList.get(count).getKey().charAt(0));
            question.setText(ans);
        }
        count++;
        answerField.clear();
        bear.setQuestion(false);
        //questionUpdate();
    }

    public void refresh() {
        score = 0;
        count = 0;
        cntWrong = 0;
        question.clear();
        answerField.clear();
        game = new HoneyBear();
        Collections.shuffle(questionList);
        totalTime = 300;
        int minutes = totalTime / 60;
        int seconds = totalTime % 60;
        DecimalFormat df = new DecimalFormat("00");
        timerLabel.setText("Time left: " + df.format(minutes) + ":" + df.format(seconds));
        System.out.println("refresh");
    }
}
