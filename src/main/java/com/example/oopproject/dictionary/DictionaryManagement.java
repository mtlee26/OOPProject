package com.example.oopproject.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class DictionaryManagement {
    private Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < num; i++) {
            String target = sc.nextLine().toLowerCase();
            String explain = sc.nextLine().toLowerCase();
            Word wordInput = new Word(target, explain);
            dictionary.add(wordInput);
        }
    }

    public void insertFromFile() {
        try {
            String fileName = "./src/main/resources/dictionaries.txt";
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Scanner sc = new Scanner(fileInputStream);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] word = line.split(": ");
                Word wordInput = new Word(word[0], word[1]);
                dictionary.add(wordInput);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        //test
        System.out.println(dictionary.size());
    }

    public String dictionaryLookup(String word_target) {
        return dictionary.getMeaning(word_target);
    }

    public ObservableList<String> dictionarySearcher(String word_input) {
        ObservableList<String> suggestList = FXCollections.observableArrayList();
        for (String x : dictionary.getWordList().keySet()) {
            if(x.startsWith(word_input)) {
                suggestList.add(x);
            }
        }
        return suggestList;
    }

    public void addNewWord() {

    }

    public void changeWord() {

    }

    public void deleteWord(String word) {
        dictionary.remove(word);
    }

    public void dictionaryExportToFile() {

    }

    public void showAllWords() {

    }

    //test
//    public static void main(String[] args) {
//        DictionaryManagement dm = new DictionaryManagement();
//        dm.insertFromFile();
//        Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine();
//        ArrayList<String> ans = dm.dictionarySearcher(input);
//        System.out.println(ans);
//    }
}
