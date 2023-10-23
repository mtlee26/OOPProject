package com.example.oopproject.controller.game;

import com.example.oopproject.controller.GameController;
import com.example.oopproject.game.GameManagement;
import com.example.oopproject.game.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class SoccerController extends GameController implements Initializable {
    private GameManagement gm = new GameManagement();
    private List<Question> questionList = gm.getQuestionList();
    private int score = 0;
    private int count = 0;

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
        displayQuestion(new ActionEvent());
        scoreField.setText(Integer.toString(score));
    }

    public void clickAnswer(Button button, ActionEvent actionEvent) {
        String ans = button.getText();
        updateScore(ans, questionList.get(count));
        count++;
        displayQuestion(actionEvent);
    }

    public void onOption1(ActionEvent actionEvent) {
        clickAnswer(option1, actionEvent);
    }

    public void onOption2(ActionEvent actionEvent) {
        clickAnswer(option2, actionEvent);
    }

    public void onOption3(ActionEvent actionEvent) {
        clickAnswer(option3, actionEvent);
    }

    public void onOption4(ActionEvent actionEvent) {
        clickAnswer(option4, actionEvent);
    }

    public void setQuestion(Question question) {
        questionField.setText(question.getQuestion());
        List<String> answer = question.getAnswer();
        Collections.shuffle(answer);
        option1.setText(answer.get(0));
        option2.setText(answer.get(1));
        option3.setText(answer.get(2));
        option4.setText(answer.get(3));
    }

    public void updateScore(String answer, Question question) {
        if (question.isTrue(answer)) {
            score += 10;
        }
        scoreField.setText(Integer.toString(score));
    }

    public void displayQuestion(ActionEvent actionEvent) {
        if (count < gm.NUM_OF_QUESTIONS) {
            setQuestion(questionList.get(count));
        } else {
            //result
            setScene(actionEvent, "/Views/SoccerResultView.fxml");
        }
    }

}
