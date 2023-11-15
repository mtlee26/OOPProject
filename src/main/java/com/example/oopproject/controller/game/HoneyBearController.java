package com.example.oopproject.controller.game;

import com.example.oopproject.controller.MenuController;
import com.example.oopproject.game.Time;
import com.example.oopproject.game.bear.BearManagement;
import com.example.oopproject.game.bear.HoneyBear;
import com.example.oopproject.game.bear.Question;
import com.example.oopproject.game.entities.Bear;
import com.example.oopproject.game.entities.FootPrint;
import com.example.oopproject.game.entities.Rock;
import com.example.oopproject.game.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
    public static HoneyBear game;
    private BearManagement quiz = new BearManagement();
    private List<Question> questionList = quiz.getQuestionList();
    private int count;
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
//    private Media sound = new Media(new File("./src/main/resources/sound/music.wav").toURI().toString());
    private Media trueSound = new Media(new File("./src/main/resources/sound/true.wav").toURI().toString());
    private Media falseSound = new Media(new File("./src/main/resources/sound/false.wav").toURI().toString());
    //public MediaPlayer soundPlayer = new MediaPlayer(sound);
    public static Time time;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        totalTime = 300;
//        int minutes = totalTime / 60;
//        int seconds = totalTime % 60;
        //DecimalFormat df = new DecimalFormat("00");
        //timerLabel.setText("Time left: " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
        //isEnd = false;
        count = 0;
        cntWrong = 0;
        game = new HoneyBear();
        time = new Time(timerLabel);
        quiz.init(MenuController.database.getDictionary().getWordList().keySet().stream().toList());
        Collections.shuffle(questionList);
        Canvas canvas = new Canvas(320, 320);
        gc = canvas.getGraphicsContext2D();
        rootGame.getChildren().add(canvas);
        System.out.println("new bear fxml");
        System.out.println(game.isRunning());
//        sound = new Media(new File("./src/main/resources/music.wav").toURI().toString());
//        soundPlayer = new MediaPlayer(sound);
//        soundPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        gameLoop = new AnimationTimer() {
            //private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                //if (!isEnd) {
//                    if (lastUpdate == 0) {
//                        lastUpdate = now;
//                    }
//                    long elapsedNanos = now - lastUpdate;
//                    double elapsedSeconds = elapsedNanos / 1000000000.0;
//                    if (elapsedSeconds >= 1.0) {
//                        totalTime--;
//                        int minutes = totalTime / 60;
//                        int seconds = totalTime % 60;
//                        //DecimalFormat df = new DecimalFormat("00");
//                        timerLabel.setText("Time left: " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
//                        lastUpdate = now;
//                        if (totalTime <= 0) {
//                            isEnd = true;
//                        }
//                    }
//                    soundPlayer.play();
                //}
                gameUpdate();
                questionUpdate();
                game.render(gc);
            }
        };
        gameLoop.start();
        time.run();
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
            //soundPlayer.stop();
            gameLoop.stop();
            game.setRunning(false);
            win.setVisible(true);
            playAgain.setVisible(true);
            exit.setVisible(true);
        }
        if (cntWrong == 2 || time.isEnd()) {
            gameLoop.stop();
            //isEnd = true;
            time.setEnd(true);
            game.setRunning(false);
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
        //isEnd = false;
    }

    @FXML
    public void onExit() {
        setComponent("/Views/Game.fxml");
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
        Bear bear = game.getMap().getBear();
        if (bear.isQuestion()) {
            String answer = answerField.getText().trim().toUpperCase();

            int x = bear.getxUnit();
            int y = bear.getyUnit();
            if (questionList.get(count).isTrue(answer)) {
                MediaPlayer truePlayer = new MediaPlayer(trueSound);
                truePlayer.play();
                cntWrong = 0;
                game.getMap().updateMap(x, y, new FootPrint(x, y, Sprite.foot_print.getFxImage()));
                question.setText(questionList.get(count).getQuestion().replace('_', answer.charAt(0)));
            } else {
                MediaPlayer falsePlayer = new MediaPlayer(falseSound);
                falsePlayer.play();
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
    }

    public void refresh() {
        count = 0;
        cntWrong = 0;
        question.clear();
        answerField.clear();
        game = new HoneyBear();
        time = new Time(timerLabel);
        Collections.shuffle(questionList);
//        totalTime = 300;
//        int minutes = totalTime / 60;
//        int seconds = totalTime % 60;
//        //timerLabel.setText("Time left: " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
        gameLoop.start();
        time.run();
        System.out.println("refresh");
    }

    public static void setEnd() {
        if (game != null) {
            game.setRunning(false);
        }
        if (time != null) {
            time.setEnd(true);
        }
    }
}
