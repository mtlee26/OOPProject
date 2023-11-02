package com.example.oopproject.game;

public class BearQuestion {
    private String question;
    private String key;

    public BearQuestion(String question, String key) {
        this.question = question;
        this.key = key;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
