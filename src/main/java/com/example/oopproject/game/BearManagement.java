package com.example.oopproject.game;

import java.util.ArrayList;
import java.util.List;

public class BearManagement {
    private List<Question> questionList = new ArrayList<>();
    //public static final int NUM_OF_QUESTIONS = 10;

    public List<Question> getQuestionList() {
        return questionList;
    }

//    public void insertFromFile() {
//        try {
//            String fileName = "./src/main/resources/question2.txt";
//            FileInputStream fileInputStream = new FileInputStream(fileName);
//            Scanner sc = new Scanner(fileInputStream);
//            while (sc.hasNextLine()) {
//                String line = sc.nextLine();
//                String[] quiz = line.split("-", -1);
//                String ques = quiz[0];
//                String key = quiz[1];
//                BearQuestion question = new BearQuestion(ques, key);
//                questionList.add(question);
//            }
//            sc.close();
//        } catch (FileNotFoundException e) {
//            System.out.println(e);
//        }
//    }

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
