package com.example.oopproject.controller.game;

//import com.example.oopproject.game.Direction;
import com.example.oopproject.game.Time;
import com.example.oopproject.game.bear.HoneyBear;
import com.example.oopproject.game.entities.Bear;
import com.example.oopproject.game.soccer.Soccer;
import com.example.oopproject.game.soccer.SoccerManagement;
import com.example.oopproject.game.soccer.MultipleChoiceQuestion;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.*;

import static com.example.oopproject.controller.MenuController.gameRoot;
import static com.example.oopproject.game.entities.AnimationEntity.DIRECTION.LEFT;
import static com.example.oopproject.game.entities.AnimationEntity.DIRECTION.RIGHT;

public class SoccerController extends GameController implements Initializable {
    private SoccerManagement gm = new SoccerManagement();
    private List<MultipleChoiceQuestion> questionList = gm.getQuestionList();
    public static Soccer soccer;
    public static Time time;
    private int score = 0;
    private int count = 0;

    @FXML
    private AnchorPane soccerRoot; //xoa
    @FXML
    private Label timerLabel;
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
    @FXML
    private Button nextButton;
    @FXML
    private ImageView endGame;
    @FXML
    private Button playAgain;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane endgame;
    @FXML
    private Label scoreLabel;
    private AnimationTimer gameLoop;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        soccer = new Soccer();
        gm.insertFromFile();
        Collections.shuffle(questionList);
        Canvas canvas = new Canvas(600, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        soccerRoot.getChildren().add(canvas);
        time = new Time(timerLabel);
        displayQuestion();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!time.isEnd()) {
                    scoreField.setText("Score: " + score);
                    //game.render(gc);
                    //gameUpdate();
                    //questionUpdate();
                    soccer.update();
                    soccer.render(gc);
                }
            }
        };
        gameLoop.start();
        time.run();
    }

    public void clickAnswer(Button button) {
        String ans = button.getText();
        if (questionList.get(count).isTrue(ans)) {
            score += 10;
            //am thanh
            soccer.setHit(true);
        } else {

            soccer.setHit(false);
        }
        if (button.equals(option1) || button.equals(option3)) {
            soccer.setDirection(LEFT);
        } else {
            soccer.setDirection(RIGHT);
        }
        nextButton.setVisible(true);
//        count++;
//        displayQuestion();
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

//    public void displayQuestion(ActionEvent actionEvent) {
//        if (count < gm.NUM_OF_QUESTIONS) {
//            setQuestion(questionList.get(count));
//        } else {
//            //result
////            setScene(actionEvent, "/Views/SoccerResultView.fxml");
//        }
//    }

    public void displayQuestion() {
        if (count < gm.NUM_OF_QUESTIONS && !(time.isEnd())) {
            setQuestion(questionList.get(count));
            //System.out.println("here");
        } else {
            //result
            System.out.println("end");
            gameLoop.stop();
            time.setEnd(true);
            soccer.setRunning(false);
//            endGame.setVisible(true);
//            playAgain.setVisible(true);
//            exit.setVisible(true);
            endgame.setVisible(true);
            scoreLabel.setText(String.valueOf(score));
        }
    }

//    public void gameUpdate() {
////        if (!bear.isQuestion()) {
////            rootGame.requestFocus();
////            rootGame.setOnKeyPressed(keyEvent -> {
////                game.update(keyEvent);
////            });
////        }
////        if (count == 10) {
////            //isWin = true;
////            //soundPlayer.stop();
////            gameLoop.stop();
////            game.setRunning(false);
////            win.setVisible(true);
////            playAgain.setVisible(true);
////            exit.setVisible(true);
////        }
//        if (time.isEnd()) {
//            gameLoop.stop();
//            //isEnd = true;
//            //game.setRunning(false);
//            //lose.setVisible(true);
//            //playAgain.setVisible(true);
//            //exit.setVisible(true);
//        }
//    }

    @FXML
    public void onNext() {
        count++;
        displayQuestion();
        nextButton.setVisible(false);
        soccer.reset();
    }

    @FXML
    public void onPlayAgain() {
        //soundPlayer.play();
        //game.setRunning(true);
//        endGame.setVisible(false);
//        playAgain.setVisible(false);
//        exit.setVisible(false);
        endgame.setVisible(false);
        refresh();
        //isEnd = false;
    }

    @FXML
    public void onExit() {
        setComponent("/Views/Game.fxml");
    }

    public void refresh() {
        score = 0;
        count = 0;
        soccer = new Soccer();
        time = new Time(timerLabel);
        Collections.shuffle(questionList);
        gameLoop.start();
        time.run();
        System.out.println("refresh");
    }

    public static void setEnd() {
        if (soccer != null) {
            soccer.setRunning(false);
        }
        if (time != null) {
            time.setEnd(true);
        }
    }

}
