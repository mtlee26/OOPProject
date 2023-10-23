package com.example.oopproject.game;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> answer = new ArrayList<>();
    private String key;
    public static final int NUM_OF_ANS = 4;

    public Question(String question, List<String> answer, String key) {
        this.question = question;
        this.answer = answer;
        this.key = key;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isTrue(String answer) {
        if (answer.equals(key)) {
            return true;
        }
        return false;
    }
}
