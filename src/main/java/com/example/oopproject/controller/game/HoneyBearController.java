package com.example.oopproject.controller.game;

import com.example.oopproject.controller.Controller;
import com.example.oopproject.game.BearManagement;
import com.example.oopproject.game.BearQuestion;
import com.example.oopproject.game.entities.Bear;
import com.example.oopproject.game.entities.FootPrint;
import com.example.oopproject.game.entities.Rock;
import com.example.oopproject.game.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class HoneyBearController extends Controller implements Initializable {
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
    private List<BearQuestion> questionList = quiz.getQuestionList();
    private int score = 0;
    private int count = 0;
    private boolean isWin;
    private int cntWrong;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isWin = false;
        cntWrong = 0;
        game = new HoneyBear();
        //quiz.insertFromFile();
        quiz.init(database.getDictionary().getWordList().stream().toList());
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
        Bear bear = game.getMap().getBear();
        if (!bear.isQuestion()) {
            rootGame.requestFocus();
            rootGame.setOnKeyPressed(keyEvent -> {
                game.update(keyEvent);
            });
        }
        if (bear.getxUnit() == 7 && bear.getyUnit() == 7) {
            isWin = true;
            //TODO render endgame
        }
    }

    public void questionUpdate() {
        if (game.getMap().getBear().isQuestion()) {
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
            ans = ans + "\n\n" + "Correct answer: " + ans.replace('_', questionList.get(count).getKey().charAt(0));
            question.setText(ans);
        }
        count++;
        answerField.clear();
        bear.setQuestion(false);
        //isQuestion = false;
    }

    public void refresh() {
        score = 0;
        count = 0;
        Collections.shuffle(questionList);
        game = new HoneyBear();
        quiz.init(database.getDictionary().getWordList().stream().toList());
        Collections.shuffle(questionList);
    }
}
