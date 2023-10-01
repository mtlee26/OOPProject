package com.example.oopproject.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class DictionaryManagement {
    private Dictionary dictionary = new Dictionary();

    public void insertFromCommandline(String wordTarget, String wordExplain) {
        dictionary.getWordList().put(wordTarget, wordExplain);
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

    public String dictionaryLookup(String wordTarget) {
        return dictionary.getMeaning(wordTarget);
    }

    public ObservableList<String> dictionarySearcher(String wordInput) {
        ObservableList<String> suggestList = FXCollections.observableArrayList();
        for (String x : dictionary.getWordList().keySet()) {
            if(x.startsWith(wordInput)) {
                suggestList.add(x);
            }
        }
        return suggestList;
    }

    public void addNewWord(String newWord, String wordExplain) {
        dictionary.getWordList().put(newWord, wordExplain);
        dictionaryExportToFile();
    }

    public void changeWord(String wordChange, String wordExplain) {
        dictionary.getWordList().replace(wordChange, wordExplain);
        dictionaryExportToFile();
    }

    public void deleteWord(String word) {
        dictionary.remove(word);
        dictionaryExportToFile();
    }

    public void dictionaryExportToFile() {
        try {
            String fileName = "./src/main/resources/dictionaries.txt";
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            for (String x : dictionary.getWordList().keySet()) {
                String wordExplain = dictionary.getWordList().get(x);
                String word = x + ": " + wordExplain + "\n";
                fileOutputStream.write(word.getBytes());
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void showAllWords() {

    }

    //test
    public static void main(String[] args) {
        DictionaryManagement dm = new DictionaryManagement();
        dm.insertFromFile();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String explain = sc.nextLine();
        dm.addNewWord(input, explain);
        //dm.changeWord(input, explain);

        System.out.println(dm.dictionaryLookup(input));
    }
}
