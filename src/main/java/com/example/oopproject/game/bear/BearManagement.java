package com.example.oopproject.game.bear;

import java.util.ArrayList;
import java.util.List;

public class BearManagement {
    private List<Question> questionList = new ArrayList<>();

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void init(List<String> wordList) {
        for (String x : wordList) {
            if (!(x.trim().equals(""))) {
                int random = (int) (Math.random() * x.length());
                String key = String.valueOf(x.charAt(random)).toUpperCase();
                String ques = x.replace(x.charAt(random), '_');
                Question question = new Question(ques.toUpperCase(), key);
                questionList.add(question);
            }
        }
    }
}
