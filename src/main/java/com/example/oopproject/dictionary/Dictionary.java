package com.example.oopproject.dictionary;

import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
    private Map<String, String> wordList = new TreeMap<String, String>();
    public void setWordList(Map<String, String> list) {
        wordList = list;
    }
    public Map<String, String> getWordList() {
        return wordList;
    }

    public void add(Word word) {
        wordList.put(word.getWord_target(), word.getWord_explain());
    }

    public void remove(String word) {
        wordList.remove(word);
    }

    public String getMeaning(String word_target) {
        return wordList.get(word_target);
    }

    public int size() {
        return wordList.size();
    }
}
