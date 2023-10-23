package com.example.oopproject.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.oopproject.game.Question.NUM_OF_ANS;

public class GameManagement {
    private List<Question> questionList = new ArrayList<>();
    public static final int NUM_OF_QUESTIONS = 10;

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void insertFromFile() {
        try {
            String fileName = "./src/main/resources/question.txt";
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Scanner sc = new Scanner(fileInputStream);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] quiz = line.split("-", -1);
                String ques = quiz[0];
                List<String> ans = new ArrayList<>();
                for (int i = 0; i < NUM_OF_ANS; i++) {
                    ans.add(quiz[i+1]);
                }
                String key = quiz[5];
                Question question = new Question(ques, ans, key);
                questionList.add(question);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
