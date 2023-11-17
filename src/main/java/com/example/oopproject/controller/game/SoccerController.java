package com.example.oopproject.controller.game;

import com.example.oopproject.game.Time;
import com.example.oopproject.game.soccer.Soccer;
import com.example.oopproject.game.soccer.SoccerManagement;
import com.example.oopproject.game.soccer.MultipleChoiceQuestion;
import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.net.URL;
import java.util.*;

import static com.example.oopproject.game.entities.AnimationEntity.DIRECTION.LEFT;
import static com.example.oopproject.game.entities.AnimationEntity.DIRECTION.RIGHT;

public class SoccerController extends GameController implements Initializable {
    private SoccerManagement gm = new SoccerManagement();
    private List<MultipleChoiceQuestion> questionList = gm.getQuestionList();
    public static Soccer soccer;
    public static Time time;
    private int score = 0;
    private int count = 0;
    private Media true2Sound = new Media(new File("./src/main/resources/sound/true2.wav").toURI().toString());
    private Media false2Sound = new Media(new File("./src/main/resources/sound/false2.wav").toURI().toString());

    @FXML
    private AnchorPane soccerRoot;
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
            MediaPlayer truePlayer2 = new MediaPlayer(true2Sound);
            truePlayer2.play();
            soccer.setHit(true);
        } else {
            MediaPlayer falsePlayer2 = new MediaPlayer(false2Sound);
            falsePlayer2.play();
            soccer.setHit(false);
        }
        if (button.equals(option1) || button.equals(option3)) {
            soccer.setDirection(LEFT);
        } else {
            soccer.setDirection(RIGHT);
        }
        nextButton.setVisible(true);
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
        option1.setText(answer.get(0));
        option2.setText(answer.get(1));
        option3.setText(answer.get(2));
        option4.setText(answer.get(3));
    }

    public void displayQuestion() {
        if (count < gm.NUM_OF_QUESTIONS && !(time.isEnd())) {
            setQuestion(questionList.get(count));
        } else {
            //result
            System.out.println("end");
            gameLoop.stop();
            time.setEnd(true);
            soccer.setRunning(false);
            endgame.setVisible(true);
            scoreLabel.setText(String.valueOf(score));
        }
    }

    @FXML
    public void onNext() {
        count++;
        displayQuestion();
        nextButton.setVisible(false);
        soccer.reset();
    }

    @FXML
    public void onPlayAgain() {
        endgame.setVisible(false);
        refresh();
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
