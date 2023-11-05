package com.example.oopproject.controller.game;

import com.example.oopproject.game.Direction;
import com.example.oopproject.game.SoccerManagement;
import com.example.oopproject.game.MultipleChoiceQuestion;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static com.example.oopproject.controller.MenuController.gameRoot;

public class SoccerController extends GameController implements Initializable {
    private SoccerManagement gm = new SoccerManagement();
    private List<MultipleChoiceQuestion> questionList = gm.getQuestionList();
    private Soccer soccer = new Soccer();
    private int score = 0;
    private int count = 0;

    @FXML
    private AnchorPane root; //xoa
    @FXML
    private Label questionField;
    @FXML
    private Label scoreField;
    @FXML
    private Button option1;
    @FXML
    private Button option2;
    @FXML
    private Button option3;
    @FXML
    private Button option4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gm.insertFromFile();
        Collections.shuffle(questionList);
        displayQuestion();
        Canvas canvas = new Canvas(600, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
//        scoreField.setText("Score: " + score);
//        soccer.render(gc);
        gameRoot.getChildren().add(canvas);
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                scoreField.setText("Score: " + score);
                soccer.render(gc);
            }
        };
        gameLoop.start();
    }

//    public void clickAnswer(Button button, ActionEvent actionEvent) {
//        String ans = button.getText();
//        //System.out.println(ans);
//        if (questionList.get(count).isTrue(ans)) {
//            score += 10;
//            //button.setStyle("-fx-border-color: green");
//            //am thanh
//            if (button.equals(option1) || button.equals(option3)) {
//                soccer.setDirection(Direction.DIRECTION.LEFT);
//            } else {
//                soccer.setDirection(Direction.DIRECTION.RIGHT);
//            }
//            soccer.setHit(true);
//        } else {
//            //
//            soccer.setHit(false);
//        }
//        scoreField.setText("Score: " + score);
//        //updateScore(ans, questionList.get(count));
//        count++;
//        displayQuestion();
//    }

    public void clickAnswer(Button button) {
        String ans = button.getText();
        if (questionList.get(count).isTrue(ans)) {
            score += 10;
            //button.setStyle("-fx-background-color: green");
            System.out.println(button.getStyle());
            //am thanh
            if (button.equals(option1) || button.equals(option3)) {
                soccer.setDirection(Direction.DIRECTION.LEFT);
            } else {
                soccer.setDirection(Direction.DIRECTION.RIGHT);
            }
            soccer.setHit(true);
        } else {
            //button.setStyle("-fx-background-color: red");
            //
            soccer.setHit(false);
        }
        count++;

        displayQuestion();
    }

    public void onOption1() {
        clickAnswer(option1);
    }

    public void onOption2() {
        clickAnswer(option2);
    }

    public void onOption3() {
        clickAnswer(option3);
    }

    public void onOption4() {
        clickAnswer(option4);
    }

    public void setQuestion(MultipleChoiceQuestion question) {
        questionField.setText(question.getQuestion());
        List<String> answer = question.getAnswer();
        Collections.shuffle(answer);
        //System.out.println(option1.getStyle());
        option1.setText(answer.get(0));
        option2.setText(answer.get(1));
        option3.setText(answer.get(2));
        option4.setText(answer.get(3));
    }

//    public void updateScore(String answer, MultipleChoiceQuestion question) {
//        if (question.isTrue(answer)) {
//            score += 10;
//
//            //am thanh
//        } else {
//
//        }
//        scoreField.setText(Integer.toString(score));
//    }

//    public void displayQuestion(ActionEvent actionEvent) {
//        if (count < gm.NUM_OF_QUESTIONS) {
//            setQuestion(questionList.get(count));
//        } else {
//            //result
////            setScene(actionEvent, "/Views/SoccerResultView.fxml");
//        }
//    }

    public void displayQuestion() {
//        try {
//            TimeUnit.SECONDS.sleep(2);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (count < gm.NUM_OF_QUESTIONS) {
            setQuestion(questionList.get(count));
        } else {
            //result
            setComponent(gameRoot, "/Views/SoccerResultView.fxml");
        }
    }

}
