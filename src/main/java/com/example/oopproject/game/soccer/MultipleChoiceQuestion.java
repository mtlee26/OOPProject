package com.example.oopproject.game.soccer;

import com.example.oopproject.game.bear.Question;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> answer;
    public static final int NUM_OF_ANS = 4;

    public MultipleChoiceQuestion(String question, List<String> answer, String key) {
        super(question, key);
        this.answer = answer;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
}
